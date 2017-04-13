package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicRelationDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicRelation;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicRelationService;
import com.yst.web.utils.PageModelContext;
@Service("dicRelationService")
@Transactional
public class DicRelationServiceImpl implements DicRelationService{
	
	@Resource(name="dicRelationDao")
	private DicRelationDao dicRelationDao;
	
	@Override
	public List<DicRelation> queryList() {
		String hql = "from DicRelation as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicRelation> dicRelationList = this.dicRelationDao.query(hql, pm, null);
		return dicRelationList;
	}
	@Override
	public DicRelation findDicRelationInfo(Integer relation_id) {
		DicRelation dicRelation = this.dicRelationDao.findByColumnValue(DicRelation.class, "relation_id", relation_id);
		return dicRelation;
	}
	@Override
	public AppResult updateDicRelation(DicRelation dicRelation) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicRelationDao.update(dicRelation);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDicRelation(Integer relation_id) {
		this.dicRelationDao.delete(DicRelation.class, relation_id);
	}
	
	@Override
	public AppResult addDicRelation(DicRelation dicRelation) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicRelationDao.save(dicRelation);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
