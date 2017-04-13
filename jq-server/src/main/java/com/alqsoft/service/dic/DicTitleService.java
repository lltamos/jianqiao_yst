package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicTitle;
import com.alqsoft.utils.BootStrapResult;

public interface DicTitleService extends BaseService<DicTitle>{
	public BootStrapResult<List<DicTitle>> getDicHospitalTypePage(Integer page, Integer length, HttpServletRequest request);

	/*
	 * 查询所有职称
	 */
	public List<DicTitle> findAll();
}
