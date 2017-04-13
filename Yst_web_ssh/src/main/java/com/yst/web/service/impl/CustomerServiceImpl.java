package com.yst.web.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;
import com.yst.web.dao.BalanceDao;
import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.doctor.DoctorDao;
import com.yst.web.huanxin.utils.Constants;
import com.yst.web.huanxin.utils.EasemobIMUsers;
import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.model.ExpressOrderInfo;
import com.yst.web.model.Merchant;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.ProductOrder;
import com.yst.web.model.Role;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.service.CustomerService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.HttpClientObject;
import com.yst.web.utils.MD5;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ResultInfo;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.SubmitResultInfo;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {
	private static Log logger = LogFactory.getLog(CustomerServiceImpl.class);
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	@Resource(name = "balanceDao")
	private BalanceDao balanceDao;
	@Resource(name = "doctorDao")
	private DoctorDao doctorDao;
	
	@Override
	public Customer findById(int id) {
		return this.customerDao.get(Customer.class, id);
	}

	@Override
	public List<Customer> selectAll() {
		return this.customerDao.query(Customer.class);
	}

	@Override
	public void deleteById(int id, int deleted) {
		Customer customer = this.customerDao.load(Customer.class, id);
		customer.setDeleted(deleted);
		this.customerDao.update(customer);
	}

	/**
	 * 以下方法为接口业务
	 */
	@Override
	public AppResult updatePhonePassword(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPassword())) {
			appResult.setError_info("密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				// 传入未加密密码
				// String passwordMD5 =
				// com.yst.web.utils.MD5.getMD5ofStr(customer
				// .getPassword());
				// 传入MD5
				if (dbCustomer.getDeleted() == 1) {
					appResult.setError_info("该账号已禁用");
				} else {
					String passwordMD5 = customer.getPassword();
					dbCustomer.setPassword(passwordMD5);
					dbCustomer.setUpdate_time(new Date(System
							.currentTimeMillis()));
					this.customerDao.update(dbCustomer);
					appResult.setError_info("修改成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			} else {
				appResult.setError_info("手机号不存在");
			}
		}
		return appResult;
	}

	@Override
	public AppResult updatePhonePayPassword(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPay_password())) {
			appResult.setError_info("密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				// 传入未加密密码
				// String passwordMD5 =
				// com.yst.web.utils.MD5.getMD5ofStr(customer
				// .getPassword());
				// 传入MD5
				if (dbCustomer.getDeleted() == 1) {
					appResult.setError_info("该账号已禁用");
				} else {
					String passwordMD5 = customer.getPay_password();
					dbCustomer.setPay_password(passwordMD5);
					dbCustomer.setUpdate_time(new Date(System
							.currentTimeMillis()));
					this.customerDao.update(dbCustomer);
					appResult.setError_info("修改成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			} else {
				appResult.setError_info("手机号不存在");
			}
		}
		return appResult;
	}

	@Override
	public void add(Customer customer) {
		this.customerDao.save(customer);
	}

	@Override
	public void saveXML(int telcode, String mobile) {
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		Document document = null;
		Element root = null;
		try {
			File file = new File(ServerParam.XML_FILE);
			if (file.exists()) {
				document = reader.read(file);
				root = document.getRootElement();
				List<Element> nodes = root.elements();
				// 如果存在删除原有节点
				for (Element element : nodes) {
					if (mobile.equals(element.attributeValue("tel"))) {
						element.detach();
					}
				}
			} else {
				document = DocumentHelper.createDocument();
				root = document.addElement("TLCODE");// 添加文档根

			}
			Element request = root.addElement("Node"); // 添加TLCODE的子节点
			request.addAttribute("tel", mobile);
			Element pro = request.addElement("CODE");
			pro.addText(telcode + "");
			Element cd = request.addElement("TEL");
			cd.addText(mobile);

			// 输出全部原始数据，在编译器中显示
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			// 输出全部原始数据，并用它生成新的我们需要的XML文件
			writer = new XMLWriter(new FileWriter(file), format);
			writer.write(document); // 输出到文件
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public AppResult checkXML(Customer customer) {
		String mobile = customer.getPhone();
		String telcode = customer.getCode();
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		File file = new File(ServerParam.XML_FILE);
		SAXReader reader = new SAXReader();
		XMLWriter writer = null;
		Document document = null;
		Element root = null;
		try {
			if (file.exists()) {// xml存在
				document = reader.read(file);
				root = document.getRootElement();
				List<Element> nodes = root.elements();
				// 如果存在删除原有节点
				boolean flg = false;
				String tel = "";
				String code = "";
				for (Element element : nodes) {
					if (mobile.equals(element.attributeValue("tel"))) {
						flg = true;
						Element eCode = element.element("CODE");
						code = eCode.getText();
						Element eTel = element.element("TEL");
						tel = eTel.getText();
						if (StringUtils.isEmptyOrWhitespaceOnly(tel)) {
							appResult.setError_info("手机号异常");
							break;
						} else if (StringUtils.isEmptyOrWhitespaceOnly(code)) {
							appResult.setError_info("验证码异常");
							break;
						} else if (!tel.equals(mobile)) {
							appResult.setError_info("手机号错误");
							break;
						} else if (!code.equals(telcode)) {
							appResult.setError_info("验证码错误");
							break;
						} else {
							appResult.setError_info("验证成功");
							appResult.setResult(AppResult.SUCCESS);
							element.detach();
						}
					}
				}
				if (!flg) {
					appResult.setError_info("未获取验证码");
				} else {

					// 输出全部原始数据，在编译器中显示
					OutputFormat format = OutputFormat.createPrettyPrint();
					format.setEncoding("UTF-8");
					// 输出全部原始数据，并用它生成新的我们需要的XML文件
					writer = new XMLWriter(new FileWriter(file), format);
					writer.write(document); // 输出到文件
					writer.close();
				}
			} else {// xml不存在
				appResult.setError_info("文件不存在");
			}
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return appResult;
	}

	@Override
	public AppResult login(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败

		// 得到用户名和密码
		String phone = customer.getPhone();
		String password = customer.getPassword();
		
		// 检查输入是否正确
		if (StringUtils.isEmptyOrWhitespaceOnly(phone)
				|| StringUtils.isEmptyOrWhitespaceOnly(password)) {
			appResult.setError_info("手机号或密码为空");
		} else {
			// 根据手机号查询用户信息
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			Integer customerId = dbCustomer.getCustomer_id();
			if (dbCustomer == null) {
				appResult.setError_info("手机号未注册或错误");
			} else {
				// 验证密码
				String dbPassword = dbCustomer.getPassword();
				// 传入未加密密码
				// String passwordMD5 = MD5.getMD5ofStr(password);
				// 传入MD5
				String hql = "from Doctor where customer_id="+customerId;
				Doctor doctor = (Doctor) doctorDao.queryForObject(hql);
				if(!CommUtils.isNull(doctor)){
					dbCustomer.setType(1);
					dbCustomer.setDoctor_id(doctor.getDoctor_id());
					dbCustomer.setImage(doctor.getImage_header());
				}
				String passwordMD5 = password;
				if (dbPassword.equals(passwordMD5)) {
					Integer status = dbCustomer.getDeleted();
					if (AppResult.STATUS_ENABLED.equals(status)) {
						appResult.setResult(AppResult.SUCCESS);
						appResult.setError_info("登陆成功");
						dbCustomer.setLast_login_time(new Date());
						this.customerDao.saveOrUpdate(dbCustomer);
						appResult.setData(dbCustomer);
					} else {
						appResult.setError_info("账号已被禁用");
					}
				} else {
					appResult.setError_info("密码错误");
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult reg(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPhone())) {
			appResult.setError_info("手机号为空");
		} else if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPassword())) {
			appResult.setError_info("密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				appResult.setError_info("手机号已存在");
			} else {
				// 传入未加密密码
				// String passwordMD5 =
				// com.yst.web.utils.MD5.getMD5ofStr(customer
				// .getPassword());
				// 传入MD5
				String passwordMD5 = customer.getPassword();
				customer.setPassword(passwordMD5);
				customer.setPay_password(passwordMD5);// 默认支付密码为登录密码
				this.customerDao.save(customer);
				dbCustomer = this.customerDao.findByColumnValue(Customer.class,
						"phone", customer.getPhone());
				Balance balance = new Balance();
				balance.setCustomer(dbCustomer);
				balance.setLast_update_time(new Date());
				balance.setLast_update_user(0);// system身份
				dbCustomer.setBalance(balance);
				JsonNode node = regHuanXin(dbCustomer.getCustomer_id());
				if (node.findValue("username") != null) {// 环信注册成功
					dbCustomer.setHuanxin_id(node.findValue("username")
							.textValue());
					dbCustomer.setHuanxin_password(Constants.DEFAULT_PASSWORD);
					if (customer.getName() == null
							|| customer.getName().equals("")) {
						dbCustomer.setName("请设置昵称");
					}
					dbCustomer.setCreate_time(new Date());
					this.customerDao.saveOrUpdate(dbCustomer);
					appResult.setData(dbCustomer);
					appResult.setError_info("注册成功");
					appResult.setResult(AppResult.SUCCESS);
				} else {
					appResult.setError_info("环信注册错误："+node.textValue());
					TransactionAspectSupport.currentTransactionStatus()
							.setRollbackOnly();// 环信注册异常 事务回滚
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult updatePassword(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPassword())) {
			appResult.setError_info("旧密码为空");
		} else if (StringUtils.isEmptyOrWhitespaceOnly(customer
				.getNew_password())) {
			appResult.setError_info("新密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				if (dbCustomer.getDeleted() == 1) {
					appResult.setError_info("该账号已禁用");
				} else {
					// 验证密码
					String dbPassword = dbCustomer.getPassword();
					// 传入未加密密码
					// String passwordMD5 = MD5.getMD5ofStr(customer
					// .getPassword());
					// 传入MD5
					String passwordMD5 = customer.getPassword();
					if (dbPassword.equals(passwordMD5)) {
						// 传入未加密新密码
						// String newPasswordMD5 = MD5.getMD5ofStr(customer
						// .getNew_password());
						// 传入新密码MD5
						String newPasswordMD5 = customer.getNew_password();
						dbCustomer.setPassword(newPasswordMD5);
						dbCustomer.setUpdate_time(new Date(System
								.currentTimeMillis()));
						this.customerDao.update(dbCustomer);
						appResult.setError_info("修改密码成功");
						appResult.setResult(AppResult.SUCCESS);
					} else {
						appResult.setError_info("密码错误");
					}
				}
			} else {
				appResult.setError_info("手机号错误或未注册");
			}
		}
		return appResult;
	}

	@Override
	public AppResult updatePayPassword(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPassword())) {
			appResult.setError_info("旧密码为空");
		} else if (StringUtils.isEmptyOrWhitespaceOnly(customer
				.getNew_password())) {
			appResult.setError_info("新密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				if (dbCustomer.getDeleted() == 1) {
					appResult.setError_info("该账号已禁用");
				} else {
					// 验证密码
					String dbPayPassword = dbCustomer.getPay_password();// 支付宝密码
					if (dbPayPassword == null || dbPayPassword.equals("")) {
						appResult.setError_info("未设置支付密码");
						return appResult;
					}
					// 传入未加密密码
					// String passwordMD5 = MD5.getMD5ofStr(customer
					// .getPassword());
					// 传入MD5
					String passwordMD5 = customer.getPassword();
					if (dbPayPassword.equals(passwordMD5)) {
						// 传入未加密新密码
						// String newPasswordMD5 = MD5.getMD5ofStr(customer
						// .getNew_password());
						// 传入新密码MD5
						String newPasswordMD5 = customer.getNew_password();
						dbCustomer.setPay_password(newPasswordMD5);
						dbCustomer.setUpdate_time(new Date(System
								.currentTimeMillis()));
						this.customerDao.update(dbCustomer);
						appResult.setError_info("修改密码成功");
						appResult.setResult(AppResult.SUCCESS);
					} else {
						appResult.setError_info("密码错误");
					}
				}
			} else {
				appResult.setError_info("手机号错误或未注册");
			}
		}
		return appResult;
	}

	@Override
	public AppResult updateInfo(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = customer.getCustomer_id();
		String phone = customer.getPhone();
		if (customer_id == null) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		if (customer_id == 0 || customer_id.equals("")) {
			appResult.setError_info("id无效");
			return appResult;
		}
		Customer dbCustomer = this.customerDao.get(Customer.class, customer_id);
		if (dbCustomer != null) {
			// 传入未加密密码
			// String passwordMD5 = com.yst.web.utils.MD5.getMD5ofStr(customer
			// .getPassword());
			// 传入MD5
			String dbPhone=dbCustomer.getPhone();
			if (phone != null && !phone.equals("") && !phone.equals(dbPhone)) {
				Customer phoneCustomer = this.customerDao.findByColumnValue(
						Customer.class, "phone", phone);
				if (phoneCustomer != null
						&& phoneCustomer.getCustomer_id() != customer_id) {
					appResult.setError_info("手机号已存在");
					return appResult;
				}
			}
			if (dbCustomer.getDeleted() == 1) {
				appResult.setError_info("该账号已禁用");
			} else {
				appResult = BeanUtils.uploadImage(customer, "customer");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				BeanUtils.copy(customer, dbCustomer);
				dbCustomer.setUpdate_time(new Date());
				this.customerDao.update(dbCustomer);
				appResult.setData(dbCustomer);
				appResult.setResult(AppResult.SUCCESS);
				appResult.setError_info("修改成功");
			}
		} else {
			appResult.setError_info("用户未注册或id错误");
		}

		return appResult;
	}

	@Override
	public AppResult getInfo(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Customer dbCustomer = this.customerDao.findByColumnValue(
				Customer.class, "phone", customer.getPhone());
		if (dbCustomer != null) {
			if (dbCustomer.getDeleted() == 1) {
				appResult.setError_info("该账号已禁用");
			} else {
				appResult.setData(dbCustomer);
				appResult.setResult(AppResult.SUCCESS);
				appResult.setError_info("获取成功");
			}
		} else {
			appResult.setError_info("手机号未注册或错误");
		}
		return appResult;
	}

	public AppResult setHuanXin(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败

		long start = System.currentTimeMillis();
		/**
		 * 注册IM用户[单个]
		 */
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("username", "kenshinnuser010");
		datanode.put("password", Constants.DEFAULT_PASSWORD);
		ObjectNode createNewIMUserSingleNode = EasemobIMUsers
				.createNewIMUserSingle(datanode);
		if (null != createNewIMUserSingleNode) {
			System.out.println("注册IM用户[单个]: "
					+ createNewIMUserSingleNode.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("____________用时：" + (end - start));
		String str = createNewIMUserSingleNode.toString();
		System.out.println(str + "   结束注册。。。。。。");
		JsonNode error = createNewIMUserSingleNode.get("error");
		if (error != null) {
			System.out.println(error.textValue());
		}
		String username = createNewIMUserSingleNode.findValue("username")
				.textValue();

		if (!StringUtils.isEmptyOrWhitespaceOnly(str)) {
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	public JsonNode regHuanXin(Integer id) {
		long start = System.currentTimeMillis();
		/**
		 * 注册IM用户[单个]
		 */
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("username", "yst_" + id);
		datanode.put("password", Constants.DEFAULT_PASSWORD);
		ObjectNode createNewIMUserSingleNode = EasemobIMUsers.createNewIMUserSingle(datanode);
		if (null != createNewIMUserSingleNode) {
			System.out.println("注册IM用户[单个]: "
					+ createNewIMUserSingleNode.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("____________用时：" + (end - start));
		JsonNode error = createNewIMUserSingleNode.get("error");
		if (error != null) {
			System.out.println(error.textValue());
			return error;
		}
		return createNewIMUserSingleNode.get("entities");
	}

	@Override
	public List<Customer> selectAllByPage() {
		String hql = "from Customer as o";
		return this.customerDao.query(hql, PageModelContext.getPageModel(),
				Customer.class, null);
	}

	@Override
	public Object sendMessage(Customer customer) {
		ResultInfo ri = new ResultInfo();
		ri.setType(ResultInfo.TYPE_RESULT_FAIL);
		SubmitResultInfo resultInfo = new SubmitResultInfo(ri);
		System.out.println("开始发送短信。。。。。。");
		int telcode = getRandom();
		String mobile = customer.getPhone();

		String url = "http://sms.1xinxi.cn/asmx/smsservice.aspx?";
		String param = "";
//		String name = "wbhsqfwzx";
//		String pwd = "9FA6EB4D075A98F84F896CF0D451";
		String name="yishangtong";
		String pwd ="9FA6EB4D075A98F84F896CF0D451";
		String type = "pt";
		String content = "验证码为：" + telcode + "【社区服务中心】";
		param = "name=" + name + "&pwd=" + pwd + "&type=" + type + "&Mobile="
				+ mobile + "&Content=" + content;
		HttpClientObject hco = new HttpClientObject();
		hco.setGet(url + param);
		hco.submit();
		String str = hco.getResponseString();
		System.out.println(str + "   结束发送短信。。。。。。");
		if (StringUtils.isEmptyOrWhitespaceOnly(str)) {
			return 1;
		}
		String[] results = str.split(",");
		int result = Integer.parseInt(results[0]);
		if (result > 0) {
			ri.setMessage(results[1]);
		} else {
			saveXML(telcode, mobile);
		}
		return resultInfo;
	}

	public int getRandom() {
		int num = (int) (Math.random() * 9000 + 1000);
		return num;
	}

	@Override
	public AppResult deleteHuanXin(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败

		long start = System.currentTimeMillis();
		/**
		 * 注册IM用户[单个]
		 */
		ObjectNode datanode = JsonNodeFactory.instance.objectNode();
		datanode.put("username", "kenshinnuser010");
		datanode.put("password", Constants.DEFAULT_PASSWORD);
		ObjectNode createNewIMUserSingleNode = EasemobIMUsers
				.createNewIMUserSingle(datanode);
		if (null != createNewIMUserSingleNode) {
			System.out.println("注册IM用户[单个]: "
					+ createNewIMUserSingleNode.toString());
		}
		long end = System.currentTimeMillis();
		System.out.println("____________用时：" + (end - start));
		String str = createNewIMUserSingleNode.toString();
		System.out.println(str + "   结束注册。。。。。。");
		JsonNode error = createNewIMUserSingleNode.get("error");
		if (error != null) {
			System.out.println(error.textValue());
		}
		String username = createNewIMUserSingleNode.findValue("username")
				.textValue();

		if (!StringUtils.isEmptyOrWhitespaceOnly(str)) {
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public AppResult getProductOrderList(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = customer.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
		} else {
			Customer dbCustomer = this.customerDao.get(Customer.class,
					customer_id);
			if (dbCustomer == null) {
				appResult.setError_info("所属商家id错误或未注册");
			} else {
				String hql = "from ProductOrder as po,Product as p where po.deleted<>1 and po.customer.customer_id=? and po.product.product_id=p.product_id order by po.create_date desc";
				PageModel pm = PageModelContext.getPageModel();
				List list = this.customerDao.query(hql, pm, customer_id);
				if (list.size() == 0) {
					appResult.setError_info("用户没有订单");
				} else {
					logger.debug(((Object[]) list.get(0))[0]);
					List resultList = new ArrayList();
					for (Object object : list) {
						Object[] objs = (Object[]) object;
						Map map = new HashMap();
						ProductOrder productOrder = (ProductOrder) objs[0];
						Product product = (Product) objs[1];
						String merchant_name = productOrder.getMerchant()
								.getName();
						map.put("productOrder", productOrder);
						map.put("product", product);
						map.put("merchant_name", merchant_name);
						Integer product_for = productOrder.getProduct_for();
						Integer send_status = productOrder.getSend_status();
						String pay_relative_id = productOrder
								.getPay_relative_id();
						if (product_for == 1 && send_status == 1) {
							ExpressOrderInfo express = this.customerDao
									.findByColumnValue(ExpressOrderInfo.class,
											"order_id", pay_relative_id);
							if (express != null) {
								map.put("express", express);
							}
						}
						resultList.add(map);
					}
					appResult.setPage_model(pm);
					appResult.setData(resultList);
					appResult.setError_info("获取列表成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult loginWeb(Customer customer,Map session,Long loginRole) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败

		// 得到用户名和密码
		String phone = customer.getPhone();
		String password = customer.getPassword();
		String imageCode = customer.getImageCode();
		// 检查输入是否正确
		if (StringUtils.isEmptyOrWhitespaceOnly(phone)
				|| StringUtils.isEmptyOrWhitespaceOnly(password)) {
			appResult.setError_info("手机号或密码为空");
		}else if (StringUtils.isEmptyOrWhitespaceOnly(imageCode)) {
			appResult.setError_info("验证码为空！");
		} else if (!imageCode.equals(session.get("sRand"))) {
			appResult.setError_info("验证码错误！");
		} else {
			// 根据手机号查询用户信息
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer == null) {
				appResult.setError_info("手机号未注册或错误");
			} else {
				// 验证密码
				String dbPassword = dbCustomer.getPassword();
				// 传入未加密密码
				String passwordMD5 = MD5.getMD5ofStr(password);
				// 传入MD5
//				String passwordMD5 = password;
				if (dbPassword.equals(passwordMD5)) {
					Integer status = dbCustomer.getDeleted();
					if (AppResult.STATUS_ENABLED.equals(status)) {
						Integer customer_id =dbCustomer.getCustomer_id();
						if(loginRole==5)
						{
							Merchant dbMerchant=this.customerDao.findByColumnValue(Merchant.class, "customer.customer_id",customer_id );
							if(dbMerchant==null)
							{
								appResult.setError_info("请选择普通用户登录");
								return appResult;
							}
						}
						//医生
						Doctor doctor = this.doctorDao.findByColumnValue(Doctor.class, "customer.customer_id", customer_id);
						if(loginRole == 7){
							if(doctor == null){
								appResult.setError_info("该医生不存在");
								return appResult;
							}
						}
						dbCustomer.setLast_login_time(new Date());
						User dbUser =this.customerDao.findByColumnValue(User.class, "login_name", phone);
						if(dbUser==null){
							dbUser = new User();
							dbUser.setLogin_name(phone);
							dbUser.setPassword(passwordMD5);
							dbUser.setCreate_time(new Date());
							dbUser.setUpdate_time(new Date());
							if(loginRole == 7){
								dbUser.setCreate_by("医生后台用户");
							}
							if(loginRole == 5){
								dbUser.setCreate_by("商户登陆创建");
							}
							Set<UserRole> roleUsers =new HashSet<UserRole>();
							Role role =this.customerDao.get(Role.class, Integer.parseInt(loginRole+""));//我是商户角色id=5
							UserRole userRole = new UserRole();
							userRole.setUser(dbUser);
							userRole.setRole(role);
							roleUsers.add(userRole);
							dbUser.setRoleUsers(roleUsers);
							this.customerDao.saveOrUpdate(dbUser);
						}
						if(loginRole == 7){
							session.put(ServerParam.DOCTOR_SESSION, doctor);
							session.put(ServerParam.USER_SESSION, dbUser);
							session.put(ServerParam.CUSTOMER_SESSION, dbCustomer);
						}else if(loginRole == 5){
							session.put(ServerParam.USER_SESSION, dbUser);
							session.put(ServerParam.CUSTOMER_SESSION, dbCustomer);
						}else{
							session.put(ServerParam.USER_SESSION, dbUser);
							session.put(ServerParam.CUSTOMER_SESSION, dbCustomer);
						}
						
						appResult.setResult(AppResult.SUCCESS);
						appResult.setError_info("登陆成功");
						this.customerDao.saveOrUpdate(dbCustomer);
					} else {
						appResult.setError_info("账号已被禁用");
					}
				} else {
					appResult.setError_info("密码错误");
				}
			}
		}
		return appResult;
	}

	@Override
	public List<Customer> selectPatientPage() {
		String hql = "from Customer as o where type=3";
		return this.customerDao.query(hql, PageModelContext.getPageModel(),
				Customer.class);
	}

	@Override
	public AppResult addPatient(Customer customer) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Customer dbCustomer = this.customerDao.findByColumnValue(
				Customer.class, "phone", customer.getPhone());
		if (dbCustomer != null) {
			appResult.setError_info("手机号已存在");
			return appResult;
		}
		customer.setType(3);//患者
		customer.setPassword(MD5.getMD5ofStr(customer.getPhone()));
		this.customerDao.save(customer);
		appResult.setData(customer);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult userLogin(String phone, String password) {
		AppResult appResult = new AppResult();
		if("".equals(phone) || phone == null){
			appResult.setError_info("手机号码不能为空");
			return appResult;
		}
		if("".equals(password) || password == null){
			appResult.setError_info("密码不能为空");
			return appResult;
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "phone", phone);
		if(customer != null && !"".equals(customer)){
			if(password.equals(customer.getPassword())){
				appResult.setError_info("登录成功");
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				appResult.setError_info("登录失败，用户名或密码不正确");
				return appResult;
			}
		}else {
			appResult.setError_info("该用户不存在");
			return appResult;
		}
		/*if(customer == null ){
			appResult.setError_info("该用户不存在");
			return appResult;
		}
		if(password.equals(customer.getPassword())){
			appResult.setError_info("登录成功");
			appResult.setResult(AppResult.SUCCESS);
			return appResult;
		}else{
			appResult.setError_info("登录失败，用户名或密码不正确");
			return appResult;
		}*/
	}

	/**
	 * 注册
	 */
	@Override
	public Result register(Customer customer) {
		if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPhone())) {
			return ResultUtils.returnError("手机号为空");
		} else if (StringUtils.isEmptyOrWhitespaceOnly(customer.getPassword())) {
			return ResultUtils.returnError("密码为空");
		} else {
			Customer dbCustomer = this.customerDao.findByColumnValue(
					Customer.class, "phone", customer.getPhone());
			if (dbCustomer != null) {
				return ResultUtils.returnError("手机号已存在");
			} else {
				// 传入未加密密码
				// String passwordMD5 =
				// com.yst.web.utils.MD5.getMD5ofStr(customer
				// .getPassword());
				// 传入MD5
				String passwordMD5 = customer.getPassword();
				customer.setPassword(MD5.getMD5ofStr(passwordMD5));
				customer.setPay_password(MD5.getMD5ofStr(passwordMD5));// 默认支付密码为登录密码
				this.customerDao.save(customer);
				dbCustomer = this.customerDao.findByColumnValue(Customer.class,
						"phone", customer.getPhone());
				Balance balance = new Balance();
				balance.setCustomer(dbCustomer);
				balance.setLast_update_time(new Date());
				balance.setLast_update_user(0);// system身份
				dbCustomer.setBalance(balance);
				JsonNode node = regHuanXin(dbCustomer.getCustomer_id());
				if (node.findValue("username") != null) {// 环信注册成功
					dbCustomer.setHuanxin_id(node.findValue("username")
							.textValue());
					dbCustomer.setHuanxin_password(Constants.DEFAULT_PASSWORD);
					if (customer.getName() == null
							|| customer.getName().equals("")) {
						dbCustomer.setName("请设置昵称");
					}
					dbCustomer.setCreate_time(new Date());
					this.customerDao.saveOrUpdate(dbCustomer);
					return ResultUtils.returnSuccess("注册成功");
				} else {
					return ResultUtils.returnError("环信注册错误："+node.textValue());
				}
			}
		}
	}

	@Override
	public Result getCustomerById(String customerId) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		return ResultUtils.returnSuccess("获取成功", customer);
	}

	@Override
	public Result updateCustomerById(String customerId,String name, String image) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(name == null){
			return ResultUtils.returnError("用户名称不能为空");
		}
		if(image == null){
			return ResultUtils.returnError("用户头像不能为空");
		}
		customer.setName(name);
		customer.setImage(image);
		customerDao.update(customer);
		return ResultUtils.returnSuccess("修改成功");
	}

	/**
	 * 修改支付密码
	 */
	@Override
	public Result updateCustomerPayPwdById(Integer customerId, String oldpassword,String newpassword) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(oldpassword == null){
			return ResultUtils.returnError("旧的密码不能为空");
		}
		String oldpwd = MD5.getMD5ofStr(oldpassword);
		String pwd = customer.getPay_password();
		if(pwd == null){
			return ResultUtils.returnError("用户尚未设置支付密码");
		}
		if(!oldpwd.equals(pwd)){
			return ResultUtils.returnError("旧的密码输入错误");
		}
		if(newpassword == null){
			return ResultUtils.returnError("新的密码不能为空");
		}
		String newPwd = MD5.getMD5ofStr(newpassword);
		customer.setPay_password(newPwd);
		customerDao.update(customer);
		return ResultUtils.returnSuccess("修改成功");
	}

	/**
	 * 修改登录密码
	 */
	@Override
	public Result updateCustomerPwdById(Integer customerId, String oldpassword,String newpassword) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(oldpassword == null){
			return ResultUtils.returnError("旧的密码不能为空");
		}
		String oldpwd = MD5.getMD5ofStr(oldpassword);
		String pwd = customer.getPassword();
		if(!oldpwd.equals(pwd)){
			return ResultUtils.returnError("旧的密码输入错误");
		}
		if(newpassword == null){
			return ResultUtils.returnError("新的密码不能为空");
		}
		String newPwd = MD5.getMD5ofStr(newpassword);
		customer.setPassword(newPwd);
		customerDao.update(customer);
		return ResultUtils.returnSuccess("修改成功");
	}

	@Override
	public Result saveCustomerFeedback(String customerId, String content) {
		return null;
	}

}
