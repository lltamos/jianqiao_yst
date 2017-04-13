package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicRelation;
import com.alqsoft.utils.BootStrapResult;

public interface DicRelationService extends BaseService<DicRelation>{
	public BootStrapResult<List<DicRelation>> getDicHospitalTypePage(Integer page, Integer length, HttpServletRequest request);
}
