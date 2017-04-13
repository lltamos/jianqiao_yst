package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicOffice;
import com.alqsoft.utils.BootStrapResult;


public interface DicOfficeService extends BaseService<DicOffice>{

	/**
	 * 查询所有科室
	 * @return
	 */
	public List<DicOffice> findAll();
	public BootStrapResult<List<DicOffice>> getDicOfficePage(Integer page, Integer length, HttpServletRequest request);
}
