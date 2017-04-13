package com.alqsoft.service.customer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Customer;
import com.alqsoft.utils.BootStrapResult;

public interface CustomerService extends BaseService<Customer> {

	/**
	 * 查询所有用户列表
	 * 
	 * @return
	 */
	public List<Customer> findAll();

	/**
	 * 会员列表分页
	 * 
	 * @param doctorname
	 * @return
	 */
	public BootStrapResult<List<Customer>> findCustomerByPage(String phone, Integer start,
			Integer length);

	public Result add(Customer customer);

	/**
	 * 保存会员
	 * 
	 * @param customer
	 * @param imageCred
	 * @return
	 */
	// Result save(Customer customer, MultipartFile[] imageCred);
	Result saveCustomer(Customer customer);

	// 修改会员密码
	public int updateCustomerPasswd(Long id, String newpasswd);

	// 修改会员密码
	public int updateCustomerPhone(Long id, String phone);

	public Result updateCustomer(Customer customer);

	Result deleted(Long id,Integer deleted);

	/**
	 * 后台用户登录
	 * 
	 * @param cusotmer
	 * @param imageCode
	 * @param session
	 * @param request
	 * @return
	 */
	public Result login(Customer cusotmer, String imageCode, HttpSession session,
			HttpServletRequest request);
	/**
	 * 根据用户id加锁查询
	 * @param id
	 * @return
	 */
	public Customer getCustomerById( Long id);

	/**
	 * 实名认证
	 * @param phone
	 * @param name
	 * @param idcard
	 * @param customerId
	 * @param customerPhone 
	 * @return
	 */
	public Result realName(String phone, String name, String idcard, Long customerId, String customerPhone) throws Exception ;

	/**
	 * 绑定银行卡
	 * @param customerId
	 * @param bankNo
	 * @param code
	 * @param update 
	 * @return
	 */
	public Result realbrank(Long customerId, String bankNo, String code, Integer update) throws Exception;


}
