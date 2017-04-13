package com.alqsoft.service.merchant;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.Merchant;
import com.alqsoft.utils.BootStrapResult;

/**
 * 总院管理业务
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface MerchantService extends BaseService<Merchant> {
	/**
	 * 总院列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<Merchant>> getMerchantList(Integer page, Integer length, HttpServletRequest request);
	/**
	 * 保存总院
	 * @param merchant
	 * @param imageCred
	 * @return
	 */
	Result save(String aids,Long id,String name,String customerPhone, String des);
	/**
	 * 恢复/禁用总院
	 * @param merchant
	 * @return
	 */
	Result deleted(Merchant merchant);
	/**
	 * 查询所有总院
	 * @return
	 */
	List<Merchant> findAll();
}
