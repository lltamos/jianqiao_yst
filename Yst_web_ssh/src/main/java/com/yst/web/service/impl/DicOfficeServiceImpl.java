package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicOfficeDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicOffice;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicOfficeService;
import com.yst.web.utils.PageModelContext;
@Service("dicOfficeService")
@Transactional
public class DicOfficeServiceImpl implements DicOfficeService{
	private static Log logger = LogFactory.getLog(DicOfficeServiceImpl.class);
	@Resource(name= "dicOfficeDao")
	private DicOfficeDao dicOfficeDao;
	@Override
	public List<DicOffice> selectAll() {
		return this.dicOfficeDao.query(DicOffice.class);
	}
	
	@Override
	public List<DicOffice> queryList() {
		String hql = "from DicOffice as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicOffice> dicOfficeList = this.dicOfficeDao.query(hql, pm, null);
		return dicOfficeList;
	}
	@Override
	public DicOffice findDicOfficeInfo(Integer office_id) {
		DicOffice dicOffice = this.dicOfficeDao.findByColumnValue(DicOffice.class, "office_id", office_id);
		return dicOffice;
	}
	@Override
	public AppResult updateDicOffice(DicOffice dicOffice) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicOfficeDao.update(dicOffice);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDicOffice(Integer office_id) {
		this.dicOfficeDao.delete(DicOffice.class, office_id);
	}
	
	@Override
	public AppResult addDicOffice(DicOffice dicOffice) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicOfficeDao.save(dicOffice);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
