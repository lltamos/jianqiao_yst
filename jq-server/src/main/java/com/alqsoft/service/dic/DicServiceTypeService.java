package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicServiceType;
import com.alqsoft.utils.BootStrapResult;


public interface DicServiceTypeService extends BaseService<DicServiceType>{
	public BootStrapResult<List<DicServiceType>> getDicHospitalTypePage(Integer page, Integer length, HttpServletRequest request);
	/**
	 * 查询所有服务包类型
	 * @return
	 */
	public List<DicServiceType> findAll();
	/**
	 * 
	* @Title: getOne 
	* @Description: 根究id查找服务包类型 
	* @return DicServiceType    返回类型 
	* @author 腾卉 
	* @throws
	 */
	public DicServiceType getOne(Long id);
	}
