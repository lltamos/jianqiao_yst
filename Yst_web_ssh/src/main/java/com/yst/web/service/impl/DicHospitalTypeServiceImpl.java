package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicHospitalTypeDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicHospitalType;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicHospitalTypeService;
import com.yst.web.utils.PageModelContext;
@Service("dicHospitalTypeService")
@Transactional
public class DicHospitalTypeServiceImpl implements DicHospitalTypeService{
	private static Log logger = LogFactory.getLog(DicHospitalTypeServiceImpl.class);
	@Resource(name = "dicHospitalTypeDao")
	private DicHospitalTypeDao dicHospitalTypeDao;
	
	@Override
	public List<DicHospitalType> selectAll() {
		return this.dicHospitalTypeDao.query(DicHospitalType.class);
	}

	@Override
	public List<DicHospitalType> queryList() {
		String hql ="from DicHospitalType as dt ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicHospitalType> list = this.dicHospitalTypeDao.query(hql, pm, null);
		return list;
	}

	@Override
	public DicHospitalType findDicHospitalTypeInfo(Integer hospital_type_id) {
		DicHospitalType dicHospitalType = this.dicHospitalTypeDao.findByColumnValue(DicHospitalType.class, "hospital_type_id", hospital_type_id);
		return dicHospitalType;
	}

	@Override
	public AppResult updateDicHospitalType(DicHospitalType dicHospitalType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicHospitalTypeDao.update(dicHospitalType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult deleteDicHospitalType(Integer hospital_type_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicHospitalTypeDao.delete(DicHospitalType.class, hospital_type_id);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult addDicHospitalType(DicHospitalType dicHospitalType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicHospitalTypeDao.save(dicHospitalType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
