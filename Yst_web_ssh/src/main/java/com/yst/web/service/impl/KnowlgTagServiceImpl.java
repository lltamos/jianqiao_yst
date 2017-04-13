package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.KnowlgTagDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgTag;
import com.yst.web.model.PageModel;
import com.yst.web.service.KnowlgTagService;
import com.yst.web.utils.PageModelContext;
@Service("knowlgTagService")
@Transactional
public class KnowlgTagServiceImpl implements KnowlgTagService{

	@Resource(name="knowlgTagDao")
	private KnowlgTagDao knowlgTagDao;
	@Override
	public List<KnowlgTag> queryList() {
		String hql = "from KnowlgTag as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<KnowlgTag> knowlgTagList = this.knowlgTagDao.query(hql, pm, null);
		return knowlgTagList;
	}
	
	@Override
	public AppResult getKnowlgTagList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String hql = "from KnowlgTag as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<KnowlgTag> knowlgTagList = this.knowlgTagDao.query(hql, pm, null);
		if(knowlgTagList.size()>0){
			appResult.setData(knowlgTagList);
			appResult.setPage_model(pm);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("无数据");
			appResult.setData("");
		}
		return appResult;
	}
	
	@Override
	public KnowlgTag findKnowlgTagInfo(Integer id) {
		KnowlgTag knowlgTag = this.knowlgTagDao.findByColumnValue(KnowlgTag.class, "id", id);
		return knowlgTag;
	}
	@Override
	public AppResult updateKnowlgTag(KnowlgTag knowlgTag) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.knowlgTagDao.update(knowlgTag);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteKnowlgTag(Integer id) {
		this.knowlgTagDao.delete(KnowlgTag.class, id);
	}
	
	@Override
	public AppResult addKnowlgTag(KnowlgTag knowlgTag) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.knowlgTagDao.save(knowlgTag);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
}
