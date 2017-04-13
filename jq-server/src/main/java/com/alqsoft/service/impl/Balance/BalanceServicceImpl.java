package com.alqsoft.service.impl.Balance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.balance.BalanceDao;
import com.alqsoft.dao.balance.CashReceiveStationDao;
import com.alqsoft.entity.Balance;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.rpc.pay.impl.RpcWeChatPayServiceImpl;
import com.alqsoft.service.balance.BalanceService;
import com.alqsoft.service.balance.CashReceiveStationService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.CodeConstExt;
import com.alqsoft.utils.MobileCheck;
import com.google.common.collect.Lists;

@Service
@Transactional
public class BalanceServicceImpl implements BalanceService {

	@Autowired
	private BalanceDao balanceDao;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private UserBankCardService userBankCardService;
	@Autowired
	private CashReceiveStationService cashReceiveStationService;
	@Autowired
	private RpcPayService rpcPayService;
	@Autowired
	private MobileCheck mobileCheck;

	private static Log logger = LogFactory.getLog(BalanceServicceImpl.class);

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		balanceDao.delete(arg0);
		return true;
	}

	@Override
	public Balance get(Long arg0) {
		// TODO Auto-generated method stub
		return balanceDao.findOne(arg0);
	}

	@Override
	public Balance saveAndModify(Balance arg0) {
		// TODO Auto-generated method stub
		return balanceDao.save(arg0);
	}

	@Override
	public List<Balance> findAll() {
		// TODO Auto-generated method stub
		Iterable<Balance> iterable = balanceDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<Balance> balancelist = Lists.newArrayList(iterable.iterator());
		return balancelist;
	}

	@Override
	public BootStrapResult<List<Balance>> findBalanceByPage(Map<String, Object> map, Integer page, Integer length,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("EQ_customerId", customerId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Balance> specification = DynamicSpecifications.bySearchFilter(filter.values(), Balance.class);
		Page<Balance> balance = balanceDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Balance.class, balance);
	}

	@Override
	public Balance findByCustomerId(Long customerId, Integer type) {
		// TODO Auto-generated method stub
		return balanceDao.findByCustomerIdAndType(customerId, type);
	}

	@Override
	public Balance getBalanceById(Long id) {
		// TODO Auto-generated method stub
		Balance balance = balanceDao.findOne(id);

		return balance;
	}

	@Override
	public Result confirmMoney(Map<String, Object> map, HttpServletRequest request) {
		// TODO Auto-generated method stub

		CashReceiveStation cashReceiveStation = new CashReceiveStation();

		Balance balance = null;
		Long cssId = null;
		String tel = null;

		// 提现金额
		Long usermoney = (Long) map.get("money");
		Long money =usermoney *100;
		// 银行卡号
		String cardNo = (String) map.get("cardNo");
		// 开户人姓名
		String personName = (String) map.get("personName");
		// 开户行名称
		String bankName = (String) map.get("bankName");
		// 手机号码
		String phone = (String) map.get("phone");
		// 验证码
		String code = (String) map.get("code");
		// 用户角色
		Integer customerType = (Integer) map.get("customerType");

		if (customerType != null && customerType == 1) {
			Doctors doctors = (Doctors) request.getSession().getAttribute("doctors");
			Long customerId = doctors.getCustomerId();
			cssId = customerId;
			Customer customer = customerService.getCustomerById(customerId);
			tel = customer.getPhone();
			//balanceDao.findByCustomerIdAndType(customerId, type);
			balance = this.findByCustomerId(customerId, customerType);
		} else if (customerType != null && customerType == 2) {
			Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
			Long customerId = merchant.getCustomerId();
			Customer customer = customerService.getCustomerById(customerId);
			tel = customer.getPhone();
			cssId = customerId;
			balance = this.findByCustomerId(customerId, customerType);
		} else if (customerType != null && customerType == 3) {
			Customer customer = (Customer) request.getSession().getAttribute("recommender");
			Long customerId = customer.getId();
			Customer customer1 = customerService.getCustomerById(customerId);
			tel = customer1.getPhone();
			cssId = customerId;
			balance = this.findByCustomerId(customerId, customerType);
		} else {
			return ResultUtils.returnError("当前登录信息有误!");
		}
		
		logger.info("当前提现角色的权限查询完毕!");
		
		if (phone == null || phone.equals("") || !(phone.equals(tel))) {
			logger.info("手机号码错误");
			return ResultUtils.returnError("手机号码有误!");

		}
		if (code == null || code.equals("")) {
			logger.info("验证码为空");
			return ResultUtils.returnError("验证码不能为空!");

		}

		
		 Result checkMsg = mobileCheck.checkMsg(phone, code, "JQ2017031605");
		 Integer checkcode = checkMsg.getCode(); if (checkcode == 1) { String
		  msg = checkMsg.getMsg(); } else { return
		  ResultUtils.returnError("验证码发送异常!"); }
		 
		
		logger.info("本次提现金额开始进行校验======================");
		
		// 剩余可提现金额
		Long balanceRemain = balance.getBalanceRemain();
		if(balanceRemain ==null || balanceRemain.equals("")){
			balanceRemain=0L;
		}
		// 可提现金额
		Long depositFee = balance.getDepositFee();
		if(depositFee ==null || depositFee.equals("")){
			depositFee=0L;
		}
		// 已提现金额
		Long haveWithdrawalFee = balance.getHaveWithdrawalFee();
		if(haveWithdrawalFee ==null || haveWithdrawalFee.equals("")){
			haveWithdrawalFee=0L;
		}

		// TODO 判断当前的提现最小额度 ---最小额度是10块钱
		if (money == null || money < 1) {
			logger.info("提现金额小于1!");
			return ResultUtils.returnError("提现金额不能为空!");

		}
		//判断余额是否小于0
		if(balanceRemain <0){
			logger.info("余额小于0,余额不足");
			return ResultUtils.returnError("余额不足");
		}
		//判断剩余可提现额度 + 已提现额度 是否等于 可提现额度 
		if ((balanceRemain + haveWithdrawalFee) != depositFee) {
			logger.info("余额数据不正常!");
			return ResultUtils.returnError("当前账户信息有误,请联系管理员进行查询!");

		}
		//判断可提现额 - 剩余可提现金额小于提现金额
		if ((depositFee - haveWithdrawalFee) < money) {
			logger.info("可提现额减去剩余可提现金额小于提现金额!");
			return ResultUtils.returnError("当前账户信息有误,请联系管理员进行查询!");

		}
		//判断当前提现金额是否大于可提现金额
		if (money > balanceRemain) {
			logger.info("提现额超出剩余可提现金额!");
			return ResultUtils.returnError("当前账户信息有误,请联系管理员进行查询!");

		}
		
		logger.info("本次提现金额校验完毕================");

		// TODO 根据当前的提现表查询当前当前的用户ID,加锁查询
		Long customerId = balance.getCustomerId();
		// TODO 加锁查询当前用户
		Customer customer = customerService.getCustomerById(customerId);
		
		logger.info("本次提现银行卡信息校验开始================");
		//绑卡状态
		Integer bankStatus = customer.getBankStatus();
		if (bankStatus == null || bankStatus != 1) {
			logger.info("绑卡状态不正常");
			return ResultUtils.returnError("当前用户未绑定银行卡,请先到个人中心进行绑卡操作!");

		}
		if (cardNo == null || cardNo.equals("")) {
			logger.info("前台传卡号为空");
			return ResultUtils.returnError("银行卡错误,请检查银行卡!");

		}
		// TODO 加锁查询当前用户的银行卡信息
		try {
			UserBankCard bankCard = userBankCardService.findUserBankCardByUserId(customerId);
			// 卡号
			String cardNum = bankCard.getCardNum();
			if (null == cardNum || !(cardNum.equals(cardNo))) {
				logger.info("前台传卡号和后台查询不匹配");
				return ResultUtils.returnError("银行卡信息有误,请检查!");

			}

			if (bankName == null || !(bankName.equals(bankCard.getBankName()))) {
				logger.info("前台传银行名称和后台查询不匹配");
				return ResultUtils.returnError("银行卡信息有误,请检查!");

			}
			if (personName == null || !(personName.equals(bankCard.getOwnName()))) {
				logger.info("前台传开户人和后台查询不匹配");
				return ResultUtils.returnError("银行卡信息有误,请检查!");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		logger.info("本次提现银行卡信息校验完毕================");
		
		
		logger.info("本次提现记录实体生成开始================");
		
		// 生成订单编号---唯一值
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String str = sdf.format(new Date());
		Integer i = (int) ((Math.random() * 9 + 1) * 1000);
		String n = i.toString();
		String merSeq = "JQTX" + str + n;
		
		// 订单号
		cashReceiveStation.setMerSeqId(merSeq);
		// 钱
		cashReceiveStation.setMoney(Integer.valueOf(money + ""));
		// 会员id
		cashReceiveStation.setCssId(Integer.valueOf(cssId + ""));
		// 提现用户角色
		cashReceiveStation.setCssType(customerType);
		// 当前提现状态
		cashReceiveStation.setStatus(1);
		// 提现卡银行
		cashReceiveStation.setBankName(bankName);
		// 卡号
		cashReceiveStation.setCardNo(cardNo);
		cashReceiveStation.setPersonName(personName);
		// TODO 用户电话
		cashReceiveStation.setTel(tel);
		
		cashReceiveStation.setIp(request.getRemoteAddr());
		// 时间
		cashReceiveStation.setCreatedTime(new Date());
		cashReceiveStation.setFromSystem(CodeConstExt.PAY_SYSTEM_FROM_JQ);
		cashReceiveStation.setThirdPayType(CodeConstExt.GZYLpay);
		// 可提现金额 
		cashReceiveStation.setCurrentBalance(balanceRemain );
		// 操作后提现余额
		cashReceiveStation.setAfterBalance(depositFee - haveWithdrawalFee);
		//手续费
		cashReceiveStation.setFeeIncome(0);
		
		logger.info("本次提现记录实体生成结束并开始进行保存实体================");
		Balance saveAndModify2= null;
		try {
			CashReceiveStation saveAndModify = cashReceiveStationService.saveAndModify(cashReceiveStation);
			if (saveAndModify == null) {
				logger.info("保存提现记录实体出错!+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
				return ResultUtils.returnError("系统错误,请联系管理员!");

			}else{
				//保存操作成功之后,修改剩余可提现金额和已提现金额
				balance.setBalanceRemain(balanceRemain - money);
				balance.setHaveWithdrawalFee(haveWithdrawalFee + money);
				logger.info("当前的balance数据"+balance+"当前的可提现余额为:"+balance.getBalanceRemain()+"当前的已提现余额"+balance.getHaveWithdrawalFee());
				
				//Balance saveAndModify2 = this.saveAndModify(balance);
				saveAndModify2 = balanceDao.save(balance);
				if(saveAndModify2 == null){
					logger.info("修改提现后数据,操作失败!++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					return ResultUtils.returnError("系统错误,请联系管理员!");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//
		
		logger.info("本次提现记录实体保存完成并开始请求pay工程================");
		Map<String,String> parem = new HashMap<String, String>();
		Result result =null;
		try {
			result = rpcPayService.cashService(cashReceiveStation,parem);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("本次提现请求pay出现异常,将执行还原钱的操作!");
			logger.info("请求pay异常,执行钱还原操作,当前的可提现余额为:"+balance.getBalanceRemain()+"+++++++"+"当前的已提现余额为:"+balance.getHaveWithdrawalFee());
			Long balanceRemain2 = balance.getBalanceRemain();
			Long haveWithdrawalFee2 = balance.getHaveWithdrawalFee();
			logger.info("balanceRemain2"+balanceRemain2+"++++++++"+"haveWithdrawalFee2"+haveWithdrawalFee2);
			balance.setBalanceRemain(balanceRemain2 + money);
			balance.setHaveWithdrawalFee(haveWithdrawalFee2 - money);
			Balance saveAndModify = this.saveAndModify(balance);
			logger.info("本次提现请求pay出现异常后,执行还原钱的操作后当前的钱余额"+saveAndModify+"+++++++++++并且将当前的请求状态改为3,失败!");
			
			if(saveAndModify ==null){
				logger.info("本次请求pay异常,本次请求参数错误,需要修改提现后数据,并且操作失败!++++++++++++++++++++++++++++++++++++++++");
				return ResultUtils.returnError("系统错误,请联系管理员!");
			}
			cashReceiveStation.setStatus(3);
			cashReceiveStationService.saveAndModify(cashReceiveStation);
			e.printStackTrace();
		}
		
		logger.info("pay工程响应结果,开始进行响应结果的处理!"+result.getCode()+"+++++++"+result.getMsg());
		
		//判断当前的响应状态.code=1,为请求参数错误,没有连接到第三方,需要将操作的余额等数据返回
		//code=0 ,为当前通信状态成功,需要判断返回的MSG,FAIL为充值失败,余额等需要加回.SUCCESS为成功.不做处理
		Integer returnCode = result.getCode();
		String msg = result.getMsg();
	
			if(returnCode ==0){
				logger.info("本次提现请求成功,已经连接到第三方,需要继续判断当前的返回信息!");
				if("FAIL".equals(msg)){
					logger.info("本次提现请求,三方返回的结果是失败,需要修改提现后数据,本次请求失败!++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
					logger.info("请求pay异常,执行钱还原操作,当前的可提现余额为:"+balance.getBalanceRemain()+"+++++++"+"当前的已提现余额为:"+balance.getHaveWithdrawalFee());
					Long balanceRemain2 = balance.getBalanceRemain();
					Long haveWithdrawalFee2 = balance.getHaveWithdrawalFee();
					logger.info("balanceRemain2"+balanceRemain2+"++++++++"+"haveWithdrawalFee2"+haveWithdrawalFee2);
					balance.setBalanceRemain(balanceRemain2 + money);
					balance.setHaveWithdrawalFee(haveWithdrawalFee2 - money);
					Balance saveAndModify = this.saveAndModify(saveAndModify2);
					logger.info("本次提现请求,三方返回的结果是失败,需要修改提现后数据,修改后的提现数据为"+saveAndModify+"在修改状态后,修改本条记录的提现状态,体现状态更改为提现失败!!");
					
					if(saveAndModify ==null){
						logger.info("根据pay工程的返回状态,本次请求参数错误,需要修改提现后数据,并且操作失败!++++++++++++++++++++++++++++++++++++++++++++++++++");
						return ResultUtils.returnError("系统错误,请联系管理员!");
					}
					cashReceiveStation.setStatus(3);
					cashReceiveStationService.saveAndModify(cashReceiveStation);
				}
				if("SUCCESS".equals(msg)){
					logger.info("本次提现请求,三方返回的结果是成功,本次操作server端处理结束,不做操作,后续操作由pay工程继续完成!");
				}
			}
			if(returnCode == 1){
				
				logger.info("请求pay异常,执行钱还原操作,当前的可提现余额为:"+balance.getBalanceRemain()+"+++++++"+"当前的已提现余额为:"+balance.getHaveWithdrawalFee());
				Long balanceRemain2 = balance.getBalanceRemain();
				Long haveWithdrawalFee2 = balance.getHaveWithdrawalFee();
				logger.info("balanceRemain2"+balanceRemain2+"++++++++"+"haveWithdrawalFee2"+haveWithdrawalFee2);
				balance.setBalanceRemain(balanceRemain2 + money);
				balance.setHaveWithdrawalFee(haveWithdrawalFee2 - money);
				Balance saveAndModify = this.saveAndModify(saveAndModify2);
				logger.info("根据pay工程的返回状态,本次请求参数错误,需要修改提现后数据,修改提现后当前的余额实体"+saveAndModify+"修改完参数后并且修改本条提现记录的状态,更改为失败!!!");
				
				if(saveAndModify ==null){
					logger.info("根据pay工程的返回状态,本次请求参数错误,需要修改提现后数据,并且操作失败!++++++++++++++++++++++++++++++++++++++++++++++++++");
					return ResultUtils.returnError("系统错误,请联系管理员!");
				}
				cashReceiveStation.setStatus(3);
				cashReceiveStationService.saveAndModify(cashReceiveStation);
			}
			
	
		
		return result;

	}
	
	@Test
	public void test(){
		Long a =1L;
		Long b = 3L;
		Long c = b-a;
		Long d ;
		
		System.out.println(c);
	}

	@Override
	public Balance findByCustomerId(Long customerId) {
		return this.balanceDao.findByCustomerId(customerId);
	}

	@Override
	public Object getByCustomerIdAndType(Long customerId, Integer type) {
		return this.balanceDao.getByCustomerIdAndType(customerId,type);
	}

}
