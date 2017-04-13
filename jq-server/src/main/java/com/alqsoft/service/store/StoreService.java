package com.alqsoft.service.store;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Store;
import com.alqsoft.utils.BootStrapResult;

/**
 * 分院管理业务
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:36:42
 * 
 */
public interface StoreService extends BaseService<Store> {
	/**
	 * 分院列表分页
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	BootStrapResult<List<Store>> getStoreList(Integer page, Integer length, HttpServletRequest request);

	/**
	 * 保存分院
	 * @param store
	 * @return
	 */
	Result save(Store store);
	/**
	 * 恢复/禁用分院
	 * @param store
	 * @return
	 */
	Result deleted(Store store);
	
	/**
	 * 
	* @Title: storeCheck 
	* @Description: 新增分院之前检查分院是否存在
	* @return Store    返回类型 
	* @author 腾卉 
	* @throws
	 */
	Result storeCheck(@Param("merchantId") Integer merchantId,@Param("name") String name);
}
