package com.yst.web.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CashReceiveStationDao;
import com.yst.web.entity.cashReceiveStation.CashReceiveStation;
import com.yst.web.init.InitParam;
import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;
import com.yst.web.model.Customer;
import com.yst.web.service.BalanceService;
import com.yst.web.service.CashReceiveStationService;
import com.yst.web.service.CustomerService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.BootStrapResultUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.ServerParam;

import chinapay.Base64;
import chinapay.SecureLink;



/**
 * 提现记录service层实现
 */
@Service
@Transactional
public class CashReceiveStationServiceImpl implements CashReceiveStationService {

    @Autowired
	private CashReceiveStationDao cashReceiveStationDao;
    
    @Autowired
	private InitParam initParam;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BalanceService balanceService;
    
    /***
     * 根据id 查询提现记录详情
     */
	@Override
	public CashReceiveStation selectCashReceiveById(Integer id) {
		CashReceiveStation cashReceiveStation=new CashReceiveStation();
		
		return cashReceiveStation;
	}

	/***
	 * 提现记录列表（带分页）
	 */
	@Override
	public BootStrapResult<List<CashReceiveStation>> getCashReceiveStationPage(Map<String, Object> map, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<CashReceiveStation> specification = DynamicSpecifications.bySearchFilter(filter.values(),CashReceiveStation.class);
		Page<CashReceiveStation> accountPage = cashReceiveStationDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		return BootStrapResultUtils.returnPage(CashReceiveStation.class, accountPage);
	}

	/***
	 * 发起提现请求  生成一条提现记录
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public AppResult luanch(CashReceiveStation crs, HttpServletRequest request,
			Map session) throws Exception {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认为1
		
		if (session.get(ServerParam.USER_SESSION) == null && session.get(ServerParam.CUSTOMER_SESSION) == null) {
			appResult.setError_info("请重新登录");
			return appResult;
		}
		int overage = 0;
		int alreadyCashMoney=0;
		Customer customer = (Customer) session.get(ServerParam.CUSTOMER_SESSION);
		
		Customer info=customerService.findById(customer.getCustomer_id());
		Balance balance=balanceService.findByCustomerId(info.getCustomer_id());
		if(balance.getDeposit_fee()==null)
		{
			balance.setDeposit_fee(0);
		}if(balance.getHave_withdrawal_fee()==null)
		{
			balance.setHave_withdrawal_fee(0);
		}
		overage = balance.getDeposit_fee();//可提现金额 
		alreadyCashMoney= Integer.parseInt(balance.getHave_withdrawal_fee()+"") ;//已提现金额
		if (session.get(ServerParam.USER_SESSION) != null) { // 系统用户
			//
		} else if (session.get(ServerParam.CUSTOMER_SESSION) != null) { // 个人
			// 检查提现金额是否小于余额
			if (crs.getMoney()/100 > overage/100) {
				appResult.setError_info("提现金额多于账户余额");
				appResult.setResult("CashMoneyMore");
				return appResult;
			}else//供货商账户余额等于 余额减去提现金额   然后保存
			{
				balance.setDeposit_fee(overage-crs.getMoney());
				balance.setHave_withdrawal_fee(alreadyCashMoney+crs.getMoney());
				//baseService.update(info, params);
				balanceService.update(balance);
			
				// 写入提现记录
				CashReceiveStation c=new CashReceiveStation();
				c.setBankCity(crs.getBankCity());
				c.setBankName(crs.getBankName());
				c.setBankPrv(crs.getBankPrv());
				c.setBankSub(crs.getBankSub());
				c.setCardNo(crs.getCardNo());
				c.setComments(crs.getComments());
				c.setMoney(crs.getMoney());
				c.setPersonName(crs.getPersonName());
				c.setPersonNo(crs.getPersonNo());
				c.setUserId(info.getCustomer_id());
				c.setName(info.getName());
				c.setTel(info.getPhone());
				c.setStatus(1);
				c.setCreatedIp(CommUtils.getIpAddr(request));
				c.setCreatedTime(new Date());
				c.setUpdateTime(new Date());
				c.setCreateTime(new Date());
				cashReceiveStationDao.save(c);
				appResult=this.upload1(c);
			}
		}
		appResult.setError_info("申请提现成功");
		return appResult;
	}

	/***
	 * 打款操作
	 */
	@Override
	public AppResult upload1(CashReceiveStation crs) throws Exception {
		String merId = (String) initParam.getProperties().get("chinapay.merid");
		String tmpPath = (String) initParam.getProperties().get("chinapay.BatchContent.filepath");
		String merKeyPath = (String) initParam.getProperties().get("chinapay.merkey.filepath");
		String pubKeyPath = (String) initParam.getProperties().get("chinapay.pubkey.filepath");
		//String uploadUrl = (String) initParam.getProperties().get("chinapay.oraBatchSer.url");
		String url=initParam.getProperties().getProperty("chinapay.auch.url");//请求的测试环境
		
		AppResult appResult=new AppResult();
		//merId，merDate merSeqId cardNo usrName openBank prov city transAmt purpose subBank flag version signFlag termType chkValue
		//merId + merDate + merSeqId + cardNo + usrName + openBank + prov + city + transAmt + purpose + subBank + flag + version + termType
	    //crs.setMoney(crs.getMoney()-500);//扣除5元手续费
		HttpClient httpClient = new HttpClient();
		System.out.println("HttpClient方法创建！");
		httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
		PostMethod postMethod = new PostMethod(url);
		System.out.println("Post方法创建！");
		SimpleDateFormat sf1 = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sf2 = new SimpleDateFormat("HHmmss");
		Date dt = new Date();
		String MerSeqId = sf2.format(dt); // 批次号
		String TransDate = sf1.format(dt); // 交易日期
		//签名
		String d=merId+TransDate+MerSeqId+crs.getCardNo()+crs.getPersonName()+crs.getBankName()+crs.getBankPrv()
				+crs.getBankCity()+crs.getMoney()+"存款"+crs.getBankSub()+"00"+"20150304"+"07";
		String chkValue =null;
		String b=new String(Base64.encode(d.toString().getBytes()));
        System.out.println("要签名的数据进行Base64编码后为:"+ b);
        chinapay.PrivateKey key=new chinapay.PrivateKey(); 
        boolean flag1=key.buildKey(merId,0,merKeyPath); 
		  if (flag1==false) 
			{ 
			System.out.println("build key error!"); 
			}else
			{
				//签名
		        SecureLink s = new SecureLink(key);
				chkValue = s.Sign(b);
				System.out.println("signFlag=1 时签名内容:"+ chkValue);
			}
 		// 填入各个表单域的值
		NameValuePair[] data = { 
				new NameValuePair("merId", merId), 
				new NameValuePair("merDate", TransDate), 
				new NameValuePair("merSeqId", MerSeqId), 
				new NameValuePair("cardNo", crs.getCardNo()), 
				new NameValuePair("usrName", crs.getPersonName()),
				new NameValuePair("openBank", crs.getBankName()),
				new NameValuePair("prov", crs.getBankPrv()),
				new NameValuePair("city", crs.getBankCity()),
				new NameValuePair("transAmt", crs.getMoney()+""),
				new NameValuePair("purpose", "存款"),
				new NameValuePair("subBank", crs.getBankSub()),
				new NameValuePair("flag", "00"),
				new NameValuePair("version", "20150304"),
				new NameValuePair("signFlag", "1"),
				new NameValuePair("termType", "07"),
				new NameValuePair("chkValue", chkValue),
				};
		System.out.println(data);
		// 将表单的值放入postMethod中
		postMethod.setRequestBody(data);
		// 执行postMethod
		try {
			httpClient.executeMethod(postMethod);
			// 读取内容
			InputStream resInputStream = postMethod.getResponseBodyAsStream();
			// 对收到的ChinaPay应答传回的域段进行验签
			BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream));
			String tempBf = null;

			StringBuffer html = new StringBuffer();
			try{
				while ((tempBf = reader.readLine()) != null) {
					html.append(tempBf);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
			
			String resultData = new String(html.toString().getBytes(), "GBK");
			System.out.println("response message：" + resultData);
			
			String[] splits = resultData.split("&");
			//crs.setMoney(crs.getMoney()+500);//保存记录是加上5元手续费
			/*if (splits.length == 10) {*/
				String responseCode=splits[0];
				System.out.println("responseCode=[" + responseCode.split("=")[1] + "]");
				String reschkValue = splits[9].split("=")[1];
				System.out.println("reschkValue="+reschkValue);
				String merDate= splits[2].split("=")[1];
				System.out.println("merDate="+merDate);
				String merSeqId = splits[3].split("=")[1];
				System.out.println("merSeqId="+merSeqId);
				String stat = splits[7].split("=")[1];
				System.out.println("stat="+stat);
				if(responseCode.equals("responseCode=0000"))
				{
					//检验签名文件
					chinapay.PrivateKey key1=new chinapay.PrivateKey(); 
					boolean flag,flag2;  
				    flag=key1.buildKey("999999999999999",0,pubKeyPath); 
				    if (flag==false) 
				    { 
				    	System.out.println("build key error!"); 
				    	appResult.setError_info("获取公钥文件失败");
				    	return appResult;
				    }
				    chinapay.SecureLink t;
					t=new chinapay.SecureLink (key1); 
				    flag2=t.verifyAuthToken(b, reschkValue);  // chkValueTemp为ChinaPay应答传回的域段
				    if(flag2=false){
					   //签名验证错误处理
					   System.out.println("验签失败！False!!");
					   appResult.setError_info("验签失败！False!!");
					   appResult.setResult("FAIL");  
				    }else{
						System.out.println("验签成功！True!!");
						
						//判断银行实时处理的状态
						if(stat.equals("2")||stat.equals("3")||stat.equals("4")||stat.equals("5")||stat.equals("7")||stat.equals("8"))
						{
							crs.setMerSeqId(merSeqId);
							crs.setMerDate(merDate);
							crs.setChkValue(reschkValue);
							crs.setId(crs.getId());
							//crs.setDescriber("正在处理");
							this.saveAndModify(crs);
							appResult.setResult("waiting");//正在处理
						}else if(stat.equals("6") || stat.equals("9"))
						{
							 //修改打款记录状态 打款失败
							 crs.setStatus(3);
							 crs.setId(crs.getId());
							// crs.setDescriber("银行退单，交易失败");
							 //crs.setFeeIncome(0);//失败0元手续费
							 this.saveAndModify(crs);
							appResult.setResult("0104");//失败
						}else if(stat.equals("s"))
						{
							//修改打款记录状态
							crs.setStatus(2);
							//保存流水号，商户日期，签名值 到提现记录表中 供单笔查询使用
							crs.setMerSeqId(merSeqId);
							crs.setMerDate(merDate);
							crs.setChkValue(reschkValue);
							crs.setId(crs.getId());
							//crs.setDescriber("打款成功");
							crs.setCreatedTime(new Date());//打款时间
							this.saveAndModify(crs);
							//打款成功
							appResult.setResult("SUCCESS");
						}
					 }
					
				}else if(responseCode.equals("responseCode=0104"))//操作拒绝
				{
					 //修改打款记录状态 打款失败
					 crs.setStatus(3);
					 crs.setId(crs.getId());
					// crs.setDescriber("接收失败：操作拒绝");
					 //crs.setFeeIncome(0);//失败0元手续费
					 this.saveAndModify(crs);
					 appResult.setError_info("操作拒绝");
					 appResult.setResult("0104");
				}else if(responseCode.equals("responseCode=0100"))
				{
					//修改打款记录状态 打款失败
					crs.setStatus(3);
					crs.setId(crs.getId());
					 //crs.setDescriber("接收失败：商户提交的字段长度、格式错误");
					// crs.setFeeIncome(0);//失败0元手续费
					this.saveAndModify(crs);
					 appResult.setError_info("商户提交的字段长度、格式错误");
					 appResult.setResult("0100");
				}else if(responseCode.equals("responseCode=0101"))
				{
					//修改打款记录状态 打款失败
					crs.setStatus(3);
					crs.setId(crs.getId());
					// crs.setDescriber("接收失败：商户验签错误");
					// crs.setFeeIncome(0);//失败0元手续费
					this.saveAndModify(crs);
					 appResult.setError_info("商户验签错误");
					 appResult.setResult("0101");
				}
				else if(responseCode.equals("responseCode=0102"))
				{
					//修改打款记录状态 打款失败
					crs.setStatus(3);
					crs.setId(crs.getId());
					//crs.setFeeIncome(0);//失败0元手续费
					 //crs.setDescriber("接收失败：手续费计算出错");
					this.saveAndModify(crs);
					 appResult.setError_info("手续费计算出错");
					 appResult.setResult("0102");
				}
				else if(responseCode.equals("responseCode=0103"))
				{
					//修改打款记录状态 打款失败
					crs.setStatus(3);
					crs.setId(crs.getId());
					//crs.setDescriber("接收失败：商户备付金帐户金额不足");
					//crs.setFeeIncome(0);//失败0元手续费
					this.saveAndModify(crs);
					 appResult.setError_info("商户备付金帐户金额不足");
					 appResult.setResult("0103");
				}
				else if(responseCode.equals("responseCode=0105"))
				{
					//修改打款记录状态 打款失败
					crs.setStatus(3);
					crs.setId(crs.getId());
					//crs.setFeeIncome(0);//失败0元手续费
					//crs.setDescriber("重复交易");
					this.saveAndModify(crs);
					 appResult.setError_info("重复交易");
					 appResult.setResult("0105");
				}
			
			
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return appResult;
	}

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CashReceiveStation get(Long arg0) {
		CashReceiveStation cashReceiveStation=cashReceiveStationDao.findOne(arg0);
		return cashReceiveStation;
	}

	@Override
	public CashReceiveStation saveAndModify(CashReceiveStation arg0) {
		return cashReceiveStationDao.save(arg0);
	}

	/***
	 * 单笔查询
	 */
	@Override
	public AppResult getBankReturnStatus() throws Exception {
		String queryUrl = (String) initParam.getProperties().get("chinapay.StatQuery.url");
		String merId = (String) initParam.getProperties().get("chinapay.merid");
		String merKeyPath = (String) initParam.getProperties().get("chinapay.merkey.filepath");
		String pubKeyPath = (String) initParam.getProperties().get("chinapay.pubkey.filepath");
		AppResult appResult=new AppResult();
		
		//String sql="select * FROM cash_receive_station as a where a.status=1 ORDER BY a.update_time limit 0,30";
		try {
			Pageable pageable= new PageRequest(0, 30);
			List<CashReceiveStation> list =cashReceiveStationDao.getBankStatus(pageable);
			if(list.size()>0)
			{
				for (CashReceiveStation cash : list) {
					String mer_seq_id=cash.getMerSeqId();
					String mer_date=cash.getMerDate();
					String chk_value=cash.getChkValue();
					Integer userid=cash.getUserId();
					Integer money=cash.getMoney();
					if(chk_value!=null && mer_seq_id!=null &&mer_date!=null )
					{
						//进行单笔查询
					   //签名：	merId + merDate + merSeqId + version 
	                   String qianMing=merId + mer_date + mer_seq_id + "20090501";
	                   String b=new String(Base64.encode(qianMing.toString().getBytes()));
	                   System.out.println("要签名的数据进行Base64编码后为:"+ b);
	                   chinapay.PrivateKey key=new chinapay.PrivateKey(); 
	                   boolean flag1=key.buildKey(merId,0,merKeyPath); 
	                   String sign =null;
	           		    if (flag1==false) 
	           			{ 
	           			System.out.println("build key error!"); 
	           			}else
	           			{
	           				//签名
	           		        SecureLink s = new SecureLink(key);
	           				sign = s.Sign(b);
	           				System.out.println("signFlag=1 时签名内容:"+ sign);
	           			}
						
						HttpClient httpClient = new HttpClient();
						System.out.println("HttpClient方法创建！");
						httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "GBK");
						PostMethod postMethod = new PostMethod(queryUrl);
						System.out.println("Post方法创建！");
						// 填入各个表单域的值
						NameValuePair[] data = { 
								new NameValuePair("merId", merId), 
								new NameValuePair("merDate", mer_date), 
								new NameValuePair("merSeqId", mer_seq_id), 
								new NameValuePair("version", "20090501"), 
								new NameValuePair("signFlag", "1"),
								new NameValuePair("chkValue", sign),};
		
						System.out.println(data);
		
						// 将表单的值放入postMethod中
						postMethod.setRequestBody(data);
						// 执行postMethod
						try {
							httpClient.executeMethod(postMethod);
						} catch (HttpException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						// 读取内容
						InputStream resInputStream = postMethod.getResponseBodyAsStream();
						// 对收到的ChinaPay应答传回的域段进行验签
						BufferedReader reader = new BufferedReader(new InputStreamReader(resInputStream));
						String tempBf = null;
		
						StringBuffer html = new StringBuffer();
						try{
							while ((tempBf = reader.readLine()) != null) {
								html.append(tempBf);
							}
						}catch(Exception e){
							e.printStackTrace();
						}
						
						String resultData = new String(html.toString().getBytes(), "GBK");
						System.out.println("response message：" + resultData);
		
						String[] spilts = resultData.split("\\|");
						System.out.println("stat="+spilts[14]);
						//交易成功  更改状态
						Balance balance=balanceService.findByCustomerId(userid);
						if(spilts[0].equals("000"))
						{
							if(spilts[14].equals("s"))
							{
								cash.setCreatedTime(new Date());//打款时间
								cash.setStatus(2); //打款成功
								//cash.setDescriber("打款成功");
								this.saveAndModify(cash);
								appResult.setResult("SUCCESS");
								
							}else if(spilts[14].equals("6") ||spilts[14].equals("9") )//银行退单，交易失败。
							{
								//把提现金额返回供应商
								//查询供货商
								balance.setDeposit_fee(balance.getDeposit_fee()+money);
								balance.setHave_withdrawal_fee(balance.getHave_withdrawal_fee()-money);
								this.balanceService.update(balance);
								cash.setStatus(3);//打款失败
								cash.setUpdateTime(new Date());
								this.saveAndModify(cash);
								appResult.setError_info("银行退单，交易失败");
								appResult.setResult("FAIL");
							}else
							{
								cash.setUpdateTime(new Date());
								this.saveAndModify(cash);
								appResult.setError_info("银联正在处理，请稍后。。。");
								appResult.setResult("FAIL");
							}
						}else
						{
							cash.setUpdateTime(new Date());
							this.saveAndModify(cash);
							appResult.setError_info("查询失败");
							appResult.setResult("FAIL");
						}
						
					}else
					{
						cash.setStatus(3);
						cash.setUpdateTime(new Date());
						this.saveAndModify(cash);
						appResult.setError_info("查询失败，没有商务号和流水号");
						appResult.setResult("FAIL");
					}
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return appResult;
	}

	@Override
	public BootStrapResult<List<CashReceiveStation>> findDoctorCashReceivePage(Map<String, Object> param, Integer page,
			Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(param);
		Specification<CashReceiveStation> specification = DynamicSpecifications.bySearchFilter(filter.values(),CashReceiveStation.class);
		Page<CashReceiveStation> accountPage = cashReceiveStationDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		return BootStrapResultUtils.returnPage(CashReceiveStation.class, accountPage);
	}

	@Override
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Map<String, Object> param, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(param);
		Specification<CashReceiveStation> specification = DynamicSpecifications.bySearchFilter(filter.values(),CashReceiveStation.class);
		Page<CashReceiveStation> accountPage = cashReceiveStationDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		return BootStrapResultUtils.returnPage(CashReceiveStation.class, accountPage);
	}


}
