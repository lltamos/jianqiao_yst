package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicSpec;
import com.alqsoft.utils.BootStrapResult;

public interface DicSpecService extends BaseService<DicSpec>{

	/**
	 * 查询所有专长
	 * @return
	 */
	public List<DicSpec> findAll();
	public BootStrapResult<List<DicSpec>> getDicSpecPage(Integer page, Integer length, HttpServletRequest request);
}
