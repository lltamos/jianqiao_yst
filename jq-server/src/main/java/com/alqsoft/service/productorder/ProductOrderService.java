package com.alqsoft.service.productorder;

import java.util.List;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.UserTable;
import com.alqsoft.utils.BootStrapResult;

/**
 * 
 * @Description: TODO
 * @author 王海龙
 * @e-mail 76217866@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月12日 下午3:30:07 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
public interface ProductOrderService extends BaseService<ProductOrder> {
	/**
	 * 服务包订单分页查询
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	public BootStrapResult<List<ProductOrder>> getProductOrderList(String payRelativeId,String customerPhone,
			String recommPhone,String productRecommPhone,Integer page, Integer length);
	/**
	 * 保存服务包订单
	 * @param productorder
	 * @param dbUser 
	 * @return
	 */
	public Result save(ProductOrder productorder, UserTable dbUser);
	/**
	 * 查询分润订单
	 * @return
	 */
	public Result getCheckProductOrderShareMoney();
}
