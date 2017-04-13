package com.yst.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.MerchantDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Merchant;
import com.yst.web.model.Role;
import com.yst.web.model.Store;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.service.MerchantService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Service("merchantService")
@Transactional
public class MerchantServiceImpl implements MerchantService {
	private static Log logger = LogFactory.getLog(MerchantServiceImpl.class);
	@Resource(name = "merchantDao")
	private MerchantDao merchantDao;
	@Resource(name = "customerDao")
	private CustomerDao customerDao;

	@Override
	public Merchant findById(int id) {
		return this.merchantDao.get(Merchant.class, id);
	}

	@Override
	public List<Merchant> selectAll() {
		return this.merchantDao.query(Merchant.class);
	}

	@Override
	public void deleteById(int id) {
		this.merchantDao.delete(Merchant.class, id);
	}

	@Override
	public void update(Merchant merchant) {
		this.merchantDao.saveOrUpdate(merchant);
	}

	@Override
	public AppResult add(Merchant merchant) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Customer dbCustomer = this.customerDao.findByColumnValue(
				Customer.class, "phone", merchant.getCustomer().getPhone());
		if (dbCustomer != null) {
			String hql = "from Merchant as m where m.customer.customer_id=?";
			Merchant dbMerchant = (Merchant) this.merchantDao.queryForObject(
					hql, dbCustomer.getCustomer_id());
			if (dbMerchant == null) {
				merchant.setCustomer_id(dbCustomer.getCustomer_id());
				merchant.setCustomer(dbCustomer);
				appResult = this.reg(merchant);
			} else {
				appResult.setError_info("所属用户已申请过商铺");
			}
		} else {
			// 所属用户手机号不存在
			appResult.setError_info("所属用户手机号不存在");
		}
		return appResult;
	}

	/**
	 * 
	 * 名称 类型 备注 client_type String A 为安卓 I 为IOS version int 接口版本号 默认为1 name
	 * String 商户姓名 customer_id int 所属商家ID image_cred_1 String 营业执证件1
	 * image_cred_2 String 营业执证件2 desc String 店家简介
	 */
	@Override
	public AppResult reg(Merchant merchant) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = merchant.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不能为空");
		} else {
			Customer dbCustomer = this.customerDao.get(Customer.class,
					customer_id);
			if (dbCustomer == null) {
				appResult.setError_info("用户id错误或未注册");
			} else {
				if (dbCustomer.getMerchant() != null) {
					appResult.setError_info("用户已经申请过商户");
				} else {
					try {
						merchant.setCustomer(dbCustomer);
						appResult = BeanUtils.uploadImage(merchant, "merchant");
						if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
							if (appResult.getResult().equals(AppResult.FAILED)) {
								return appResult;
							}
						}
						this.merchantDao.save(merchant);
						User dbUser = new User();
						dbUser.setLogin_name(dbCustomer.getPhone());
						dbUser.setPassword(dbCustomer.getPassword());
						dbUser.setCreate_time(new Date());
						dbUser.setUpdate_time(new Date());
						dbUser.setCreate_by("商户注册创建");
						Set<UserRole> roleUsers =new HashSet<UserRole>();
						Role role =this.customerDao.get(Role.class, 5);//商户角色id=5
						UserRole userRole = new UserRole();
						userRole.setUser(dbUser);
						userRole.setRole(role);
						roleUsers.add(userRole);
						dbUser.setRoleUsers(roleUsers);
						this.customerDao.saveOrUpdate(dbUser);
						appResult.setError_info("申请成功");
						appResult.setResult(AppResult.SUCCESS);
					} catch (Exception e) {
						appResult.setError_info("添加数据异常:" + e.getMessage());
					}

				}
			}
		}
		return appResult;
	}

	@Override
	public List<Merchant> selectByName(Merchant merchant) {
		return this.merchantDao.selectByColumnLike(Merchant.class, "name",
				merchant.getName());
	}

	@Override
	public AppResult getVerify(Merchant merchant) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (merchant.getCustomer_id() != null
				&& !merchant.getCustomer_id().equals("")) {
			String hql = "from Merchant as m where m.customer.customer_id=?";
			Merchant dbMerchant = (Merchant) this.merchantDao.queryForObject(
					hql, merchant.getCustomer_id());
			if (dbMerchant == null) {
				appResult.setError_info("用户没有商户");
			} else {
				if (dbMerchant.getDeleted() == 1) {
					appResult.setError_info("已禁用");
				} else {
					if (dbMerchant.getVerify() == 0) {
						appResult.setError_info("正在审核");
						appResult.setData(dbMerchant);
					} else if (dbMerchant.getVerify() == 1) {
						appResult.setError_info("审核通过");
						appResult.setResult(AppResult.SUCCESS);
						appResult.setData(dbMerchant);
					} else {
						appResult.setError_info("已拒绝");
						appResult.setData(dbMerchant);
					}
				}
			}
		} else {
			appResult.setError_info("id不能为空");
		}
		return appResult;
	}

	@Override
	public AppResult getInfo(Merchant merchant) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Merchant dbMerchant = this.merchantDao.get(Merchant.class,
				merchant.getMerchant_id());
		if (dbMerchant != null) {
			if (dbMerchant.getDeleted() == 1) {
				appResult.setError_info("已禁用");
			} else {
				List<Store> list=this.merchantDao.selectByColumnValue(Store.class, "merchant.merchant_id", dbMerchant.getMerchant_id());
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("merchant", dbMerchant);
				map.put("store", list);
				appResult.setData(map);
				appResult.setResult(AppResult.SUCCESS);
				appResult.setError_info("获取成功");
			}
		} else {
			appResult.setError_info("商户id不存在");
		}
		return appResult;
	}

	@Override
	public AppResult updateInfo(Merchant merchant) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		if (merchant.getCustomer() != null) {
			String phone = merchant.getCustomer().getPhone();
			if (phone != null && !phone.equals("")) {
				Customer dbCustomer = this.customerDao.findByColumnValue(
						Customer.class, "phone", phone);
				if (dbCustomer == null) {
					appResult.setError_info("手机号未注册或错误");
					return appResult;
				}
				merchant.setCustomer(dbCustomer);
			}
		}
		Merchant dbMerchant = this.merchantDao.get(Merchant.class,
				merchant.getMerchant_id());
		if (dbMerchant != null) {
			if (dbMerchant.getDeleted() == 1) {
				appResult.setError_info("已禁用");
			} else {
				appResult = BeanUtils.uploadImage(merchant, "merchant");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				BeanUtils.copy(merchant, dbMerchant);
				this.merchantDao.update(dbMerchant);
				appResult.setData(dbMerchant);
				appResult.setResult(AppResult.SUCCESS);
				appResult.setError_info("修改成功");
			}
		} else {
			appResult.setError_info("商户id不存在");
		}
		return appResult;
	}

	@Override
	public List<Merchant> selectAllByPage(Merchant merchant,Map session) {
		Integer merchant_id = merchant.getMerchant_id();
		String order_str = "";
		if(merchant_id!=null && !merchant_id.equals("")){
			order_str=" where o.merchant_id="+merchant_id;
		}
		String hql = "from Merchant as o"+order_str;
		return this.merchantDao.query(hql, PageModelContext.getPageModel(),
				Merchant.class,null);
	}

	@Override
	public void deleteById(Integer merchant_id, Integer deleted) {
		Merchant merchant = this.merchantDao.load(Merchant.class, merchant_id);
		merchant.setDeleted(deleted);
		this.merchantDao.update(merchant);
	}

	@Override
	public AppResult getAllMerchant() {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		List<Merchant> list = this.merchantDao.query(Merchant.class);
		if (list.size() == 0) {
			appResult.setError_info("没有找到数据");
			return appResult;
		}
		appResult.setData(list);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("获取列表成功");
		return appResult;
	}
}
