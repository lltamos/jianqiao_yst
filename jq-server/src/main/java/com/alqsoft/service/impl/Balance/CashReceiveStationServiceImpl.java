package com.alqsoft.service.impl.Balance;


import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.UniqueUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.alqsoft.dao.balance.CashReceiveStationDao;
import com.alqsoft.entity.Balance;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.service.balance.BalanceService;
import com.alqsoft.service.balance.CashReceiveStationService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;




/**
 * 提现记录service层实现
 */
@Service
@Transactional
public class CashReceiveStationServiceImpl implements CashReceiveStationService {

	private static Log logger = LogFactory.getLog(CashReceiveStationServiceImpl.class);
    @Autowired
	private CashReceiveStationDao cashReceiveStationDao;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BalanceService balanceService;
    @Autowired
	private RpcPayService rpcPayService;
    @Autowired
    private  UserBankCardService bankCardService;
    
    
    
    
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
	public BootStrapResult<List<CashReceiveStation>> getCashReceiveStationPage(Map<String, Object> map, Integer page, Integer rows,HttpServletRequest request) {
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<CashReceiveStation> specification = DynamicSpecifications.bySearchFilter(filter.values(),CashReceiveStation.class);
		Page<CashReceiveStation> accountPage = cashReceiveStationDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(CashReceiveStation.class, accountPage);
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


	@Override
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Map<String, Object> param, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(param);
		Specification<CashReceiveStation> specification = DynamicSpecifications.bySearchFilter(filter.values(),CashReceiveStation.class);
		Page<CashReceiveStation> accountPage = cashReceiveStationDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		return BootStrapResultUtils.returnPage(CashReceiveStation.class, accountPage);
	}

	
	/**
	 * 健桥service贵州银联提现
	 * @author Yaowei
	 * @param  
	 * @return Result
	 * @Time 2017年3月23日
	 */
	@Override
	public Result cashReceiveStationMoney(CashReceiveStation cashReceiveStation, String phone,
			String code, Long cardId) {
        logger.info("进入server服务提现方法   用户ip:"+cashReceiveStation.getCreatedIp()+"用户ID为："+cashReceiveStation.getCssId()+"手机号："+phone+"银行卡号："+cardId+"时间："+new Date());
		
       Integer customerId = cashReceiveStation.getCssId();
        Integer customerType = cashReceiveStation.getCssType();
		Result result=new Result();
	/*	CashReceiveStation crsEntity=new CashReceiveStation();*/
		/*String ipAddr=cashReceiveStation.getCreatedIp();//用户ip
*/		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String today = df.format(new Date());
		
		Map<String,String> parem = new HashMap<String, String>();
		/*CashReceiveStation cashReceiveStation1=null;*/
		//Hunter hunter1=null;
	/*	Customer customer=null;*/
		//Hunter hunter=null;
		//MemberMoney memberMoney1=null;
		//MemberMoney memberMoney=new MemberMoney();
		Long leftDepositMoney=0L;
		Long money=0L;
		Long haveDepositMoney=0L;
		try {
		
				//(1)验证验证码
				 /*result=this.checkMsg(phone, code, "LT2017030801");//提现手机号
				 
				 if(result.getCode().intValue()!=1){
					 logger.info(phone+"提现手机验证码有误"+code+",时间:"+new Date());
					 return ResultUtils.returnError("提现手机验证码有误");
				 }*/
				 
				 //(2)验证是否注入pay工程
				 if (rpcPayService==null) {
						logger.info("pay工程请求失败,时间:"+new Date());
					 return ResultUtils.returnError("pay工程请求失败");
				 }
				 
				//(3)验证身份
				Long hunterId=cashReceiveStation.getCssId().longValue();//用户id
				Customer customer=customerService.get(hunterId);//获取Customer
				if(null==customer){//用户信息是否存在
					logger.info("用户信息不存在,时间:"+new Date());
					return ResultUtils.returnError("用户信息不存在");
				}
					/*String idNumber = customer.getIDNumber();//身份证号
					String name=customer.getName();//姓名
*/					
				/*	if("".equals(idNumber) || "".equals(name) || idNumber ==null || name==null ){//是否认证了身份
						logger.info("未进行身份认证,时间:"+new Date());
						return ResultUtils.returnError("您未进行身份认证，请先进行身份认证");
					}
					*/
				//(4)验证金额	
				/* money=cashReceiveStation.getMoney().longValue();//用户输入提现金额*/
					Integer money3 = cashReceiveStation.getMoney();
					money=(long)money3;//金额，以分为单位
				/*Balance balance = customer.getBalance();*/
				/*	
				
*/				Balance balance = balanceService.findByCustomerId((long)customerId,customerType);
				Long balanceRemain = balance.getBalanceRemain();//剩余可提现金额
					if(null==balanceRemain){ balanceRemain=0L;}
				Long money2=balanceRemain-money;
				if(money2<0){
					logger.info("用户剩余可提现金额不足,用户输入提现金额 为："+money+"单位 分,"+"剩余可提现金额为："+balanceRemain+"单位 分,"+"可提现减输入金额的差额为："+money2+"单位 分,"+"时间:"+new Date());
					 return ResultUtils.returnError("剩余可提现金额不足");
				 }
				//*******判断单笔提现限额5万 5000000 单位 分*******
				Long dateMoney=5000000L;
				if(money>dateMoney){
					logger.info("单笔提现金额不能超过5万,金额为:"+money+"单位 分,"+"时间:"+new Date());
					 return ResultUtils.returnError("单笔提现金额不能超过5万");
				 }
				//************判断单日限额*************提现金额加今日已提现金额不能超过10000000 单位分 十万
				Long Single_day_limit_customer=10000000L;//10万 单位 分
				
			    ParsePosition pos2 = new ParsePosition(0); 
			    Date strtodate = df.parse(today, pos2);
			  	
			   //查询今日已提现金额
				/*Long todayCash = cashReceiveStationDao.getTodayCashMoneyByDate(Integer.parseInt(cashReceiveStation.getCssId()+""), strtodate);
					logger.info("查询今日已提现金额为:"+todayCash+"单位 分,"+"时间:"+new Date());
				if(null==todayCash){ todayCash=0L;}
				
				Long totalMoney=money+todayCash;//
				if (totalMoney > Single_day_limit_customer) {
					logger.info("单日提现金额不能超过10万,提现金额为:"+money+"单位 分,"+"今日已提现金额为:"+todayCash+"单位 分,"+"提现金额加今日已提现金额为:"+totalMoney+"单位 分,"+"时间:"+new Date());
					 return ResultUtils.returnError("单日提现金额不能超过10万,您已超额");
				}*/
				
				
				//生成订单号
					/*String merSeqId = getMerSeqId();*/
						
				//(5)验证（银行信息是否正确）
				UserBankCard userBankCard = bankCardService.get(cardId);//根据银行卡id获取银行卡信息
				if(null == userBankCard){
					logger.info("未绑定银行卡,银行卡ID为："+cardId+"的信息不存在,时间:"+new Date());
					return ResultUtils.returnError("请先绑定银行卡");
				}
				
				
				
				String cardNo = userBankCard.getCardNum();//银行卡号
			    String bankName = userBankCard.getBankName();//银行名称		
			    String OwnName = userBankCard.getOwnName();//开户人姓名
			    
			   //******************保存记录表信息******************// 
			    //1.提现记录表（回调之前）
			    /* Integer type = customer.getType();//用户类型 1 医生 2 总院(分院) 3 推荐人
			     crsEntity.setCreatedIp(ipAddr);//用户ip
			    crsEntity.setMoney((int) money.intValue());//提现金额
			    crsEntity.setMerSeqId(merSeqId);//订单号
			    crsEntity.setCreatedTime(new Date());
			    crsEntity.setName(customer.getName());//用户姓名
			    crsEntity.setTel(customer.getPhone());//电话
*/			    
			    Integer type = cashReceiveStation.getCssType();
			   /* 
			    if (type==1) {
			    	crsEntity.setCssType(1);//提现类型   1 医生 2 总院(分院) 3 推荐人
			    	crsEntity.setCssId(cashReceiveStation.getCssId());//用户id
					if (customer.getDoctorId() != userBankCard.getUserId()) {
						crsEntity.setStatus(3);//1已申请 2打款成功 3打款失败
						//crsEntity.setUpdateTime(new Date());//完成时间
						crsEntity.setFeeIncome(0);//手续费5块
						crsEntity.setDescriber("非法操作,用户银行信息与传递信息不符.");
						this.saveAndModify(crsEntity);
						logger.info("非法操作,用户银行信息与传递信息不符,银行卡ID为："+cardId+"用户ID为："+hunterId+"银行卡绑定的用户ID为："+userBankCard.getUserId()+",时间:"+new Date());
						return ResultUtils.returnError("非法操作,用户银行信息与传递信息不符");
					}
				}else if (type==2) {
					crsEntity.setCssType(2);//提现类型   1 医生 2 总院(分院) 3 推荐人
					crsEntity.setCssId(cashReceiveStation.getCssId());//用户id
					if (customer.getMerchantId() != userBankCard.getUserId()) {
						crsEntity.setStatus(3);//1已申请 2打款成功 3打款失败
						//crsEntity.setUpdateTime(new Date());//完成时间
						crsEntity.setFeeIncome(0);//手续费5块
						crsEntity.setDescriber("非法操作,用户银行信息与传递信息不符.");
						this.saveAndModify(crsEntity);
						logger.info("非法操作,用户银行信息与传递信息不符,银行卡ID为："+cardId+"用户ID为："+hunterId+"银行卡绑定的用户ID为："+userBankCard.getUserId()+",时间:"+new Date());
						return ResultUtils.returnError("非法操作,用户银行信息与传递信息不符");
					}
				}else if (type==3) {
					crsEntity.setCssType(3);//提现类型   1 医生 2 总院(分院) 3 推荐人
					crsEntity.setCssId(cashReceiveStation.getCssId());//用户id
					if (customer.getId() != userBankCard.getUserId()) {
						crsEntity.setStatus(3);//1已申请 2打款成功 3打款失败
						//crsEntity.setUpdateTime(new Date());//完成时间
						crsEntity.setFeeIncome(0);//手续费5块
						crsEntity.setDescriber("非法操作,用户银行信息与传递信息不符.");
						this.saveAndModify(crsEntity);
						logger.info("非法操作,用户银行信息与传递信息不符,银行卡ID为："+cardId+"用户ID为："+hunterId+"银行卡绑定的用户ID为："+userBankCard.getUserId()+",时间:"+new Date());
						return ResultUtils.returnError("非法操作,用户银行信息与传递信息不符");
					}
				}*/
			   /* crsEntity.setBankName(bankName);//银行名称
			    crsEntity.setPersonName(OwnName);//收款人姓名
			    crsEntity.setCardNo(cardNo);//银行卡号
				crsEntity.setStatus(1);//1已申请 2打款成功 3打款失败
				crsEntity.setDescriber("提现申请中");
				crsEntity.setTel(customer.getPhone());//电话
				crsEntity.setFeeIncome(500);//手续费5块
				crsEntity.setMoney((int) money.intValue());//提现金额
				
				
				 cashReceiveStation1=this.saveAndModify(crsEntity);//保存提现记录表
*/			/*	if(null==cashReceiveStation1){
					logger.error("保存提现记录表发生异常,用户ID为:"+crsEntity.getCssId()+"姓名："+crsEntity.getPersonName()+",时间:"+new Date());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return ResultUtils.returnError("保存提现记录表发生异常");
				}else{
					logger.info("保存提现记录表成功,用户ID为："+crsEntity.getCssId()+"姓名："+crsEntity.getPersonName()+",时间:"+new Date());
				}*/
					
				//（2）扣除用户金额  保存用户信息（回调之前）
				leftDepositMoney=balance.getBalanceRemain();//余额
				//crsEntity.setCurrentBalance(leftDepositMoney);
				if(null==leftDepositMoney){ leftDepositMoney=0L;}
				leftDepositMoney=leftDepositMoney-money;
				//balance.setBalanceRemain(leftDepositMoney);//设置提现后的余额 
				//crsEntity.setAfterBalance(leftDepositMoney);
				//haveDepositMoney=Long.parseLong(balance.getHaveWithdrawalFee()+"");//已提现金额
				if(null==haveDepositMoney){ haveDepositMoney=0L;}
				//TODO 更新用户余额
				haveDepositMoney=haveDepositMoney+money;
				balance.setBalanceRemain(leftDepositMoney);//保存已余额
				balance.setHaveWithdrawalFee(haveDepositMoney);//已提现金额
				/*customer.setBalance(balance);*/
				Balance balance1 = this.balanceService.saveAndModify(balance);
						
				if(null==balance1){
/*					logger.error("保存用户提现变动金额发生异常,用户ID为:"+saveAndModify.getId()+"姓名："+saveAndModify.getName()+",时间:"+new Date());
*/					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return ResultUtils.returnError("保存提现记录表发生异常");
				}else{
/*					logger.info("保存用户提现变动金额成功,用户ID为："+saveAndModify.getId()+"姓名："+saveAndModify.getName()+",时间:"+new Date());
*/				}
				
				//(3)维护收支表（回调之前）
			/*	memberMoney.setHunter(hunter);//猎头id
				memberMoney.setFee(500L);//手续费5块
				memberMoney.setType(4);//0 支付订单  1支付定金  2定金退货  3退货  4提现  5分润
				memberMoney.setStatus(0);//状态 0申请中  1成功   2失败
				memberMoney.setMoney(money);//金额
				memberMoney.setOrderNo(merSeqId);//订单号
				memberMoney.setAfterMoney(money2);//变动后金额
				memberMoney.setBeforeMoney(money1);//变动前金额
				memberMoney1=memberMoneyService.saveAndModify(memberMoney);//保存
				if(null==memberMoney1){
					logger.error("保存用户收支明细表发生异常,用户ID为:"+memberMoney.getHunter().getId()+"姓名："+memberMoney.getHunter().getName()+",时间:"+new Date());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					return ResultUtils.returnError("保存提现记录表发生异常");
				}else{
					logger.info("保存用户收支明细表成功,用户ID为："+memberMoney.getHunter().getId()+"姓名："+memberMoney.getHunter().getName()+",时间:"+new Date());
				}*/
				
				/*crsEntity.setThirdPayType(CodeConstExt.GZYLpay);//接口
				crsEntity.setFromSystem(CodeConstExt.PAY_SYSTEM_FROM_JQ);//发起来源
				crsEntity.setCurrentBalance(leftDepositMoney);//提现时余额
*/				
				
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			/*logger.info("调用pay工程之前，保存记录 出现异常，京东代付,方法异常,提现事务回滚,订单号:"+crsEntity.getMerSeqId()+
					",卡号"+crsEntity.getCardNo()+",用户id:"+crsEntity.getCssId());
			logger.error("订单号:"+crsEntity.getMerSeqId()+",提现发生异常:"+e.getMessage());*/
			return ResultUtils.returnError("申请提现失败");
		}
		
		try{
			

			//调用打款方法
		/*	logger.info("调用pay工程打款方法 订单号:"+crsEntity.getMerSeqId()+"提现金额："+crsEntity.getMoney()+"用户ID为："+crsEntity.getCssId()+"用户ip:"+crsEntity.getCreatedIp()
						+"用户姓名："+customer.getName()+"银行卡号："+crsEntity.getCardNo()+"时间："+new Date());*/
				/*//发请求扣手续费
				Long feeMoney=money-500;
				crsEntity.setMoney(feeMoney.intValue());//提现金额*/	
			
				result=rpcPayService.cashService(cashReceiveStation,parem);
				
				logger.info("调用pay工程,请求返回结果处理开始**********");
				if (null !=result) {
					//回调结果
					if (1 == result.getCode().intValue()) {

						/*logger.info("调用pay工程，提现异常,用户ID为：" + customer.getId() + "返回参数Code：" + result.getCode() + "返回参数Msg："
								+ result.getMsg() + "返回参数Content:" + result.getContent() + ",时间:" + new Date());*/
						//失败
						CashReceiveStation cashReceiveStation2 = (CashReceiveStation) result.getContent();
						//1.提现记录表(修改状态)
						cashReceiveStation2.setStatus(3);//1已申请 2打款成功 3打款失败
						cashReceiveStation2.setDescriber("打款失败");
						this.cashReceiveStationDao.save(cashReceiveStation2);
						/*logger.info("调用pay工程请求失败业务处理，保存提现记录表成功,ID为:" + cashReceiveStation1.getId() + ",订单号："
								+ cashReceiveStation1.getMerSeqId());*/
						//2.用户表(给用户加钱)
						/*Long leftDepositMoney1 = customer.getBalance().getBalanceRemain();//剩余可提现
*/						Integer cssId = cashReceiveStation2.getCssId();
						Integer type = cashReceiveStation.getCssType();
						Balance findByCustomerId = balanceService.findByCustomerId((long)customerId, type);
						Long balanceRemain = findByCustomerId.getBalanceRemain();//剩余可提现
						if (null == findByCustomerId) {
							balanceRemain = 0L;
						}
						leftDepositMoney = balanceRemain + money;

						/*Long haveDepositMoney1 = customer.getBalance().getHaveWithdrawalFee();//已提现
*/						Long haveWithdrawalFee = findByCustomerId.getHaveWithdrawalFee();

						
						if (null == haveWithdrawalFee) {
							haveWithdrawalFee = 0L;
						}
						haveDepositMoney = haveWithdrawalFee - money;
						/*Balance balance = customer.getBalance();*/
						findByCustomerId.setBalanceRemain(leftDepositMoney);
						findByCustomerId.setHaveWithdrawalFee(haveDepositMoney);
						/*findByCustomerId.setBalance(balance);*/
						/*this.customerService.saveAndModify(customer);*/
						Balance saveAndModify = this.balanceService.saveAndModify(findByCustomerId);
						
/*						logger.info("调用pay工程请求失败业务处理，保存用户信息成功,用户ID:" + customer.getId() + "用户姓名：" + customer.getName());
*/						//3.收支明细表(修改状态，修改变动金额)
					/*	memberMoney1.setFee(0L);//手续费
						memberMoney1.setType(4);//0 支付订单  1支付定金  2定金退货  3退货  4提现  5分润
						memberMoney1.setStatus(2);//状态 0申请中  1成功   2失败
						memberMoneyService.saveAndModify(memberMoney1);
						logger.info("调用pay工程请求失败业务处理，保存收支明细表成功,ID:" + memberMoney1.getId() + "用户ID"
								+ memberMoney1.getHunter().getId());*/
						return ResultUtils.returnError("提现发生异常");
					} else if (0 == result.getCode().intValue()) {
						CashReceiveStation cashReceiveStation2 = (CashReceiveStation) result.getContent();
						//成功
						/*logger.info("调用pay工程，提现成功,用户ID为：" + customer.getId() + "返回参数Code：" + result.getCode() + "返回参数Msg："
								+ result.getMsg() + "返回参数Content:" + result.getContent() + ",时间:" + new Date());*/
						//1.提现记录表(修改状态)
						cashReceiveStation2.setStatus(1);//1已申请 2打款成功 3打款失败
						cashReceiveStation2.setDescriber("提现申请中");
						this.cashReceiveStationDao.save(cashReceiveStation2);
						/*logger.info("调用pay工程请求成功业务处理，保存提现记录表成功,ID为:" + cashReceiveStation1.getId() + ",订单号："
								+ cashReceiveStation1.getMerSeqId());*/
						//2.用户表(不变)

						//3.收支明细表(修改状态)
					/*	memberMoney.setType(4);//0 支付订单  1支付定金  2定金退货  3退货  4提现  5分润
						memberMoney.setStatus(1);//状态 0申请中  1成功   2失败
						memberMoneyService.saveAndModify(memberMoney);
						logger.info("调用pay工程请求成功业务处理，保存收支明细表成功,ID:" + memberMoney1.getId() + "用户ID"
								+ memberMoney1.getHunter().getId());*/

						result.setCode(1);
						result.setMsg("提现申请成功");
					}else{
						
						logger.info("调用pay工程,请求返回结果状态code有误，(1:请求失败;2:请求成功)code为："+result.getCode());
					return ResultUtils.returnError("调用pay工程,请求返回结果状态code有误");
					} 
					
					
				}else {
					//失败处理
					logger.info("调用pay工程,请求返回结果result为空");
					return ResultUtils.returnError("调用pay工程,请求返回结果result为空");
				}
				
		}catch(Exception e){
			e.printStackTrace();
			/*logger.info("调用pay工程之后  出现异常，京东代付,方法异常,提现事务回滚,订单号:"+crsEntity.getMerSeqId()+
					",卡号"+crsEntity.getCardNo()+",用户id:"+crsEntity.getCssId());
			logger.error("订单号:"+crsEntity.getMerSeqId()+",提现发生异常:"+e.getMessage());*/
			return ResultUtils.returnError("申请提现失败");
		}
		
		
		return result;
	}

	

	@Override
	public Long getIDByMerSeqId(String custOrderId) {
		return cashReceiveStationDao.getIDByMerSeqId(custOrderId);
	}

	@Override
	public CashReceiveStation getRowLock(Long id) {
		
		return cashReceiveStationDao.getRowLock(id);
	}

	/**
	 * 获取健桥流水号
	 * 拼接"J" 保证唯一
	 * @return
	 */
	private String getMerSeqId(){
		String merSeqId = "J"+UniqueUtils.getOrder();
		return merSeqId;
	}
	/**
	 * 根据订单号查看订单数据
	 * @author Yaowei
	 * @param  
	 * @return CashReceiveStation
	 * @Time 2017年3月27日
	 */
	public CashReceiveStation getCashReceiveStationByCustOrderId( String custOrderId) {
		// cashReceiveStationDao.getCashReceiveStationByCustOrderId(custOrderId);
		return  cashReceiveStationDao.getCashReceiveStationByCustOrderId(custOrderId);
	}


}
