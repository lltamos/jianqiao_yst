package com.alqsoft.service.impl.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.patient.PatientDao;
import com.alqsoft.dao.userInfo.UserBankCardDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.CheckElement;
import com.alqsoft.utils.MD5;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.SystemRole;
import com.google.common.collect.Lists;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private MobileCheck mobileCheck;
	@Autowired
	private UserBankCardDao userBankCardDao;
	@Autowired
	private UserBankCardService userBankCardService;

	@Override
	public boolean delete(Long arg0) {
		customerDao.delete(arg0);
		return true;
	}

	@Override
	public Customer get(Long arg0) {
		return customerDao.findOne(arg0);
	}

	@Override
	public Customer saveAndModify(Customer arg0) {
		return customerDao.save(arg0);
	}

	@Override
	public List<Customer> findAll() {
		Iterable<Customer> iterable = customerDao
				.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<Customer> customerlist = Lists.newArrayList(iterable.iterator());
		return customerlist;
	}

	@Override
	@Transactional
	public BootStrapResult<List<Customer>> findCustomerByPage(String phone, Integer start,
			Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_phone", phone);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Customer> specification = DynamicSpecifications
				.bySearchFilter(filter.values(), Customer.class);
		Page<Customer> customer = customerDao.findAll(specification, new PageRequest(start, length,
				new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Customer.class, customer);
	}

	@Override
	@Transactional
	public Result add(Customer customer) {
		Result result = new Result();

		Customer customer2 = this.customerDao.save(customer);
		if (customer2 != null) {
			result.setCode(0);
			result.setMsg("添加成功!");
		} else {
			result.setCode(1);
			result.setMsg("添加失败!");

		}
		return result;
	}
	// , MultipartFile[] imageCred

	@Override
	@Transactional
	public Result saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String customerPhone = customer.getPhone();
		String password = customer.getPassword();
		String code = customer.getCode();

		if (StringUtils.isBlank(customerPhone)) {
			return ResultUtils.returnError("所属用户手机号不能为空!");
		}

		if (StringUtils.isBlank(code)) {
			return ResultUtils.returnError("验证码不能为空!");
		}
		Result checkMsg = mobileCheck.checkMsg(customerPhone, code, "JQ2017031602");

		Integer checkcode = checkMsg.getCode();
		if (checkcode == 1) {
			String msg = checkMsg.getMsg();
		} else {
			return ResultUtils.returnError("验证码发送异常!");
		}

		String md5ofStr = MD5.getMD5ofStr(password);
		Customer phone = customerDao.getCustomerByPhone(customerPhone);
		if (phone != null) {
			return ResultUtils.returnError("当前用户手机已经存在!");
		}

		if (StringUtils.isBlank(password)) {
			return ResultUtils.returnError("密码不能为空!");
		}
		customer.setPassword(md5ofStr);
		customer.setType(0);
		customer.setDeleted(0);
		customerDao.save(customer);
		
		return ResultUtils.returnSuccess("保存成功！");
	}

	@Override
	@Transactional
	public int updateCustomerPasswd(Long id, String newpasswd) {
		if (id == null && newpasswd == null) {
			return 0;
		}
		return customerDao.updateCustomerPasswByid(id, newpasswd);
	}

	@Override
	@Transactional
	public int updateCustomerPhone(Long id, String phone) {
		if (id == null && phone == null) {
			return 0;
		}
		return customerDao.updateCustomerPhoneByid(id, phone);
	}

	@Override
	@Transactional
	public Result updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Long did = customer.getId();
		Customer customer1 = customerDao.findOne(did);
		if (customer1 == null) {
			return ResultUtils.returnError("当前修改的用户不存在!");
		}
		String name = customer.getName();
		String password = customer.getPassword();
		Integer sex = customer.getSex();
		// String birthDay = customer.getBirthDay();
		Integer isLock = customer.getIsLock();
		Integer deleted = customer.getDeleted();
		if (StringUtils.isBlank(name)) {
			return ResultUtils.returnError("姓名不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			return ResultUtils.returnError("密码不能为空!");
		}
		/*
		 * if(StringUtils.isBlank(birthDay)){ return
		 * ResultUtils.returnError("生日不能为空!"); }
		 */
		if (sex == null || sex.equals("")) {
			return ResultUtils.returnError("性别不能为空!");
		}
		String md5ofStr = MD5.getMD5ofStr(password);
		customer1.setPassword(md5ofStr);
		customer1.setName(name);
		customer1.setSex(sex);
		// customer1.setBirthDay(birthDay);
		customer1.setIsLock(isLock);
		customer1.setDeleted(deleted);
		customerDao.save(customer1);
		return ResultUtils.returnSuccess("修改成功！");
	}

	/**
	 * 禁用和恢复
	 */
	@Override
	@Transactional
	public Result deleted(Long id,Integer deleted) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.findOne(id);
		if (id == null) {
			return ResultUtils.returnSuccess("当前用户不存在");
		}
		if (deleted == null) {
			return ResultUtils.returnError("标志不能为空!");
		}
		if (customer == null) {
			return ResultUtils.returnError("用户不存在!");
		}
		if(deleted ==1){
			customer.setDeleted(1);
			Customer save = customerDao.save(customer);
			return ResultUtils.returnSuccess("禁用成功！");
		}else{
			
			customer.setDeleted(0);
			Customer save = customerDao.save(customer);
			return ResultUtils.returnSuccess("恢复成功！");
		}
		
	}

	@Override
	public Result login(Customer c, String imageCode, HttpSession session,
			HttpServletRequest request) {
		
		String phone = c.getPhone();
		String password = c.getPassword();
		Integer type = c.getType();
		if (StringUtils.isBlank(phone)) {
			return ResultUtils.returnError("用户名不能为空!");
		}
		if (StringUtils.isBlank(password)) {
			return ResultUtils.returnError("密码不能为空!");
		}
		if (StringUtils.isBlank(imageCode)) {
			return ResultUtils.returnError("验证码不能为空!");
		}
		Object sRand = session.getAttribute("sRand");
		if (sRand == null) {
			return ResultUtils.returnError("验证码异常!");
		}
		if (!imageCode.equals(sRand)) {
			return ResultUtils.returnError("验证码错误！");
		}
		Customer cusotmer = customerDao.getCustomerByPhone(phone);
		if (cusotmer == null) {
			return ResultUtils.returnError("登录用户不存在！");
		}
		Integer isLocked = cusotmer.getDeleted();
		if (isLocked == null || isLocked == 1) {
			return ResultUtils.returnError("用户已禁用！");
		}
		if (cusotmer.getType() == null) {
			cusotmer.setType(0);
		}
		//是否为总院
		Long isMerchant = cusotmer.getIsMerchant();
		/*if(null == isMerchant || "".equals(isMerchant) || isMerchant != 1){
			return ResultUtils.returnError("该用户不是总院！");
		}*/
		//是否为推荐人
		Long isRecommender = cusotmer.getIsRecommender();
		/*if(null == isRecommender || "".equals(isRecommender) || isRecommender != 1){
			return ResultUtils.returnError("该用户不是推荐人！");
		}*/
		Integer type2 = cusotmer.getType();
		Long id = cusotmer.getId();
		Integer nameAuthentication = cusotmer.getNameAuthentication();
		// 医生登录
		if (type.intValue() == 1 ) {
			if(null == type2 || type2 !=1){
				return ResultUtils.returnError("该用户不是医生！");
			}
			
			Doctors doctors = doctorDao.getDoctorsByCustomerId(id);
			
			if (doctors == null) {
				return ResultUtils.returnError("该用户未申请医生！");
			} else {
				
				String dbPassword = cusotmer.getPassword();
				String passwordMD5 = MD5.getMD5ofStr(password);
				if (!dbPassword.equals(passwordMD5)) {
					return ResultUtils.returnError("密码错误！");
				}
				session.setAttribute(SystemRole.DOCTOR.getName(),doctors);
				session.setAttribute(SystemRole.SESSIONROLE.getName(), SystemRole.DOCTOR.getName());
				session.setAttribute("customertype", 1);
				session.setAttribute("doctors", doctors);
				session.setAttribute("nameAuthentication", nameAuthentication);
				// session.setAttribute(SystemRole.DOCTOR.getName(), doctors);
			}
		}
		// 总院后台登录
		if (type.intValue() == 2 ) {
			if(null == isMerchant && isMerchant ==1){
				return ResultUtils.returnError("该用户不是总院！");
			}
			Merchant merchant = merchantDao.findMerchantByCustomer(id);
			if (merchant == null) {
				return ResultUtils.returnError("该用户不是商户！");
			} else {
				String dbPassword = cusotmer.getPassword();
				String passwordMD5 = MD5.getMD5ofStr(password);
				if (!dbPassword.equals(passwordMD5)) {
					return ResultUtils.returnError("密码错误！");
				}
				session.setAttribute(SystemRole.HOSPITAL.getName(), merchant);
				session.setAttribute(SystemRole.SESSIONROLE.getName(),
						SystemRole.HOSPITAL.getName());
				session.setAttribute("customertype", 2);
				session.setAttribute("merchant", merchant);
				session.setAttribute("nameAuthentication", nameAuthentication);
				// session.setAttribute(SystemRole.DOCTOR.getName(), doctors);
			}
		}
		// 推荐人后台登录
		/*if (type.intValue() == 3 && isRecommender ==1) {*/
		if (type.intValue() == 3 ) {
			if(null == isRecommender || isRecommender !=1){
				return ResultUtils.returnError("该用户不是推荐人！");
			}
			if (cusotmer.getIsRecommender() == null) {
				cusotmer.setIsRecommender(new Long(0));
			}
			String dbPassword = cusotmer.getPassword();
			String passwordMD5 = MD5.getMD5ofStr(password);
			if (!dbPassword.equals(passwordMD5)) {
				return ResultUtils.returnError("密码错误！");
			}
			session.setAttribute(SystemRole.RECOMMENDER.getName(), cusotmer);
			session.setAttribute(SystemRole.SESSIONROLE.getName(),
					SystemRole.RECOMMENDER.getName());
			session.setAttribute("customertype", 3);
			session.setAttribute("recommender", cusotmer);
			session.setAttribute("nameAuthentication", nameAuthentication);
			// session.setAttribute(SystemRole.DOCTOR.getName(), doctors);
		}
		if(nameAuthentication == null || nameAuthentication == 0 ){
			return ResultUtils.returnSuccess("登录成功！为保证财产安全！请进行身份信息实名认证！");
		}else if(nameAuthentication == 1){
			return ResultUtils.returnSuccess("登录成功！");
		}
		return ResultUtils.returnSuccess("登录成功！");
	}

	@Override
	public Customer getCustomerById(Long id) {
		// TODO Auto-generated method stub
		Customer customerById = customerDao.getCustomerById(id);
		return customerById;
	}

	@Override
	public Result realName(String phone, String name, String idcard, Long customerId, String customerPhone) throws Exception {
		Result result = new Result();
		Pattern checkPhong = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$");
		Pattern checkName = Pattern.compile("[\u4E00-\u9FA5]+");
		Pattern checkIdcard = Pattern.compile("^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$");
		if(phone == null || phone == ""){
			result.setCode(1);	
			result.setMsg("手机号不能为空");
			return result;
		}
		Matcher matcherPhone = checkPhong.matcher(phone);
		if(!matcherPhone.matches()){
			result.setCode(1);	
			result.setMsg("手机号格式错误");
			return result;
		}
		if(customerId == null){
			result.setCode(1);	
			result.setMsg("用户不存在");
			return result;
		}
		Customer customer = customerDao.findOne(customerId);
		if(customer == null){
			result.setCode(1);	
			result.setMsg("用户不存在");
			return result;
		}
		if(!phone.equals(customerPhone)){
			result.setCode(1);	
			result.setMsg("手机号与登陆用户手机号不匹配");
			return result;
		}
		if(name == null || name == ""){
			result.setCode(1);	
			result.setMsg("姓名不能为空");
			return result;
		}
		Matcher matcherName = checkName.matcher(name);
		if(!matcherName.matches()){
			result.setCode(1);	
			result.setMsg("姓名只能是中文");
			return result;
		}
		if(idcard == null || idcard == ""){
			result.setCode(1);	
			result.setMsg("身份证号不能为空");
			return result;
		}
		Matcher matcherIdcard = checkIdcard.matcher(idcard);
		if(!matcherIdcard.matches()){
			result.setCode(1);	
			result.setMsg("身份证号格式错误");
			return result;
		}
		int idcount = customerDao.getCountByIdcard(idcard);
		if(idcount > 0 ){
			result.setCode(1);	
			result.setMsg("该实名信息已被认证，请核实信息");
			return result;
		}
		CheckElement checkElement = new CheckElement();
		result = checkElement.checkTwoElement(name, idcard);
		Integer code = result.getCode();
		if(code == 1){
			customer.setName(name);
			customer.setIDNumber(idcard);
			customer.setNameAuthentication(1);
			try {
				customerDao.save(customer);
				result.setCode(0);
				result.setMsg("实名认证成功，系统将自动注销当前登录用户，请再次登陆");
			} catch (Exception e) {
				result.setCode(1);
				result.setMsg("实名认证失败，请确认数据后再次申请");
			}
		}else{
			result.setCode(1);
			result.setMsg("实名认证失败，请确认数据后再次申请");
		}
		return result;
	}

	@Override
	public Result realbrank(Long customerId, String bankNo, String code, Integer update) throws Exception {
		Result result = new Result();
		if(customerId == null){
			result.setCode(1);	
			result.setMsg("用户不存在");
			return result;
		}
		Customer customer = customerDao.findOne(customerId);
		if(customer == null){
			result.setCode(1);	
			result.setMsg("用户不存在");
			return result;
		}
		if(bankNo == null){
			result.setCode(1);	
			result.setMsg("银行卡号不能为空");
			return result;
		}
		if(update == null){
			Integer bankStatus = customer.getBankStatus();
			if(bankStatus == null) {
				bankStatus = 0;
			}
			if(bankStatus == 1){
				result.setCode(1);	
				result.setMsg("用户已绑定银行卡");
				return result;
			}
			UserBankCard bankCard = userBankCardDao.findOneByCustomerId(customerId);
			if(bankCard != null){
				result.setCode(1);	
				result.setMsg("用户已绑定银行卡");
				return result;
			}
		}
		if(code == null){
			result.setCode(1);	
			result.setMsg("验证码不能为空");
			return result;
		}
		
		String phone = customer.getPhone();
		Result checkMsg = mobileCheck.checkMsg(phone, code, "JQ2017030331");
		Integer checkcode = checkMsg.getCode();
		if (checkcode == 1) {
			String msg = checkMsg.getMsg();
		} else {
			result.setCode(1);
			result.setMsg("验证码错误或失效!");
			return result;
		}
		String name = customer.getName();
		String idCard = customer.getIDNumber();
		CheckElement checkElement = new CheckElement();
		result = checkElement.checkThreeElement(name, idCard, bankNo);
		UserBankCard userBankCard = null;
		if(update == null){
			userBankCard = new UserBankCard();
		} else {
			userBankCard = userBankCardService.findUserBankCardByUserId(customerId);
		}
		Integer codes = result.getCode();
		if(codes == 1){
			customer.setBankStatus(1);
			userBankCard.setUserId(customerId);
			userBankCard.setCustomerType(customer.getType());
			userBankCard.setCardNum(bankNo);
			userBankCard.setTelPhone(phone);
			try {
				userBankCardDao.save(userBankCard);
				customerDao.save(customer);
				result.setCode(0);
				result.setMsg("银行卡绑定成功");
			} catch (Exception e) {
				result.setCode(1);
				result.setMsg("银行卡绑定失败，请确认数据后再次绑定");
			}
		}else{
			result.setCode(1);
			result.setMsg("银行卡绑定失败，请确认数据后再次绑定");
		}
		return result;
	}

}
