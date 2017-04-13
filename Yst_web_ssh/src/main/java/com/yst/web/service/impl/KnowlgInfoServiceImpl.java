package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.KnowlgInfoDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgInfo;
import com.yst.web.model.KnowlgTag;
import com.yst.web.model.PageModel;
import com.yst.web.service.KnowlgInfoService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
@Service("knowlgInfoService")
@Transactional
public class KnowlgInfoServiceImpl implements KnowlgInfoService{

	@Resource(name="knowlgInfoDao")
	private KnowlgInfoDao knowlgInfoDao;
	
	@Override
	public List<KnowlgInfo> queryList() {
		String hql = "from KnowlgInfo as o ";
		PageModel pm = PageModelContext.getPageModel();
		List<KnowlgInfo> knowlgInfoList = this.knowlgInfoDao.query(hql, pm,KnowlgInfo.class, null);
		return knowlgInfoList;
	}
	@Override
	public KnowlgInfo findKnowlgInfoInfo(Integer id) {
		KnowlgInfo knowlgInfo = this.knowlgInfoDao.findByColumnValue(KnowlgInfo.class, "id", id);
		return knowlgInfo;
	}
	@Override
	public AppResult updateKnowlgInfo(KnowlgInfo knowlgInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer id = knowlgInfo.getId();
		if(id==null){
			appResult.setError_info("知识库信息id不能为空");
			return appResult;
		}
		appResult = BeanUtils.uploadImage(knowlgInfo, "knowlgInfo");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		KnowlgInfo k = this.knowlgInfoDao.findByColumnValue(KnowlgInfo.class, "id", id);
		BeanUtils.copy(knowlgInfo, k);
		Integer tag_id = knowlgInfo.getTag_id();
		if(tag_id!=null){
			KnowlgTag knowlgTag = new KnowlgTag();
			knowlgTag.setId(tag_id);
			k.setKnowlgTag(knowlgTag);
		}
		this.knowlgInfoDao.update(k);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteKnowlgInfo(Integer id) {
		this.knowlgInfoDao.delete(KnowlgInfo.class, id);
	}
	
	@Override
	public AppResult addKnowlgInfo(KnowlgInfo knowlgInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		appResult = BeanUtils.uploadImage(knowlgInfo, "knowlgInfo");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		Integer tag_id = knowlgInfo.getTag_id();
		if(tag_id!=null){
			KnowlgTag knowlgTag = new KnowlgTag();
			knowlgTag.setId(tag_id);
			knowlgInfo.setKnowlgTag(knowlgTag);
		}
		this.knowlgInfoDao.save(knowlgInfo);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public AppResult getKnowlgInfoList(KnowlgInfo knowlgInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer tag_id = knowlgInfo.getTag_id();
		if(tag_id==null){
			appResult.setError_info("标签id不能为空");
			return appResult;
		}
		PageModel pm = PageModelContext.getPageModel();
		List<KnowlgInfo> knowlgInfoList;
		if(tag_id==-1){
			String hql = "from KnowlgInfo as d ";
			knowlgInfoList = this.knowlgInfoDao.query(hql, pm, null);
		}else{
			String hql = "from KnowlgInfo as d where knowlgTag.id=?";
			knowlgInfoList = this.knowlgInfoDao.query(hql, pm, tag_id);
		}
		if(knowlgInfoList.size()>0){
			appResult.setData(knowlgInfoList);
			appResult.setPage_model(pm);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("无数据");
			appResult.setData("");
		}
		return appResult;
	}
	@Override
	public AppResult reKnowlgInfo(Integer id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(id==null){
			appResult.setError_info("知识库id不能为空");
			return appResult;
		}
		KnowlgInfo knowlgInfo = this.knowlgInfoDao.findByColumnValue(KnowlgInfo.class, "id", id);
		if(knowlgInfo!=null){
			knowlgInfo.setView_count(knowlgInfo.getView_count()+1);
			this.knowlgInfoDao.update(knowlgInfo);
			appResult.setData(knowlgInfo);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("所属知识库不存在");
			appResult.setData("");
		}
		return appResult;
	}
}
