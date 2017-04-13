package com.alqsoft.service.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.Product;
import com.alqsoft.utils.BootStrapResult;

/**
 * 服务包管理业务
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface ProductService extends BaseService<Product> {
	/**
	 * 服务包列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<Product>> getProductList(Integer page, Integer length, HttpServletRequest request);
	/**
	 * 保存服务包
	 * @param merchant
	 * @param image 
	 * @param doctorId 
	 * @param imageCred
	 * @return
	 */
	Result save(Product product, String aids, Long[] doctorId);
	/**
	 * 恢复/禁用服务包
	 * @param merchant
	 * @return
	 */
	Result deleted(Product merchant);
}
