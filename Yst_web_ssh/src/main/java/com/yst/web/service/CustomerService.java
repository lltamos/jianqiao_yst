package com.yst.web.service;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;

import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;

public interface CustomerService {
	public Customer findById(int id);
	public List<Customer> selectAll();
	public void add(Customer customer);
	public void deleteById(int id,int deleted);
	public AppResult updatePhonePassword(Customer customer);
	public void saveXML(int telcode,String mobile);
	public AppResult checkXML(Customer customer);
	public AppResult login(Customer customer);
	public AppResult reg(Customer customer) ;
	public AppResult updatePassword(Customer customer);
	public AppResult updateInfo(Customer customer);
	public AppResult getInfo(Customer customer);
	public AppResult setHuanXin(Customer customer);
	public List<Customer> selectAllByPage();
	public Object sendMessage(Customer customer);
	public AppResult deleteHuanXin(Customer customer);
	public AppResult updatePayPassword(Customer customer);
	public AppResult updatePhonePayPassword(Customer customer);
	public AppResult getProductOrderList(Customer customer);
	public AppResult loginWeb(Customer customer, Map session,Long loginRole);
	public List<Customer> selectPatientPage();
	public AppResult addPatient(Customer customer);
	public AppResult userLogin(String phone, String password);
	
	public Result register(Customer customer);
	public Result getCustomerById(String customerId);
	public Result updateCustomerById(String customerId, String name,String image);
	/**
	 * 修改支付密码
	 * @param customerId
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	public Result updateCustomerPayPwdById(Integer customerId, String oldpassword,String newpassword);
	/**
	 * 修改登录密码
	 * @param customerId
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	public Result updateCustomerPwdById(Integer customerId, String oldpassword,String newpassword);
	public Result saveCustomerFeedback(String customerId, String content);
}
