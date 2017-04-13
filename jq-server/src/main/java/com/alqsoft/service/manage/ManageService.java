package com.alqsoft.service.manage;

import java.util.List;

import org.alqframework.easyui.EasyuiResult;
import org.alqframework.orm.BaseService;

import com.alqsoft.entity.manager.Manager;

/**
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年10月13日 下午5:08:09
 * 
 */
public interface ManageService extends BaseService<Manager>{

	
	/**
	 * 分页查询
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyuiResult<List<Manager>> getManageListAll(Integer page, Integer rows);

	/**
	 * 根据名称查询用户
	 * @param account
	 * @return
	 */
	public Manager getManageByAccount(String account);


}
