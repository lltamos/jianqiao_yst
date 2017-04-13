package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicSpecDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicSpec;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicSpecService;
import com.yst.web.utils.PageModelContext;
@Service("dicSpecService")
@Transactional
public class DicSpecServiceImpl implements DicSpecService{
	private static Log logger = LogFactory.getLog(DicSpecServiceImpl.class);
	@Resource(name = "dicSpecDao")
	private DicSpecDao dicSpecDao;
	
	@Override
	public List<DicSpec> selectAll() {
		return this.dicSpecDao.query(DicSpec.class);
	}

	@Override
	public List<DicSpec> queryList() {
		String hql = "from DicSpec as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicSpec> dicSpecList = this.dicSpecDao.query(hql, pm, null);
		return dicSpecList;
	}
	@Override
	public DicSpec findDicSpecInfo(Integer spec_id) {
		DicSpec dicSpec = this.dicSpecDao.findByColumnValue(DicSpec.class, "spec_id", spec_id);
		return dicSpec;
	}
	@Override
	public AppResult updateDicSpec(DicSpec dicSpec) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicSpecDao.update(dicSpec);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDicSpec(Integer spec_id) {
		this.dicSpecDao.delete(DicSpec.class, spec_id);
	}
	
	@Override
	public AppResult addDicSpec(DicSpec dicSpec) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicSpecDao.save(dicSpec);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
