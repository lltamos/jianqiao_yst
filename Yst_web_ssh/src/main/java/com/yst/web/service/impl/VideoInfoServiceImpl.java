package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.VideoInfoDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgTag;
import com.yst.web.model.PageModel;
import com.yst.web.model.VideoInfo;
import com.yst.web.service.VideoInfoService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
@Service("videoInfoService")
@Transactional
public class VideoInfoServiceImpl implements VideoInfoService{

	@Resource(name="videoInfoDao")
	private VideoInfoDao videoInfoDao;
	
	@Override
	public AppResult getAllList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String hql ="from VideoInfo as o ";
		PageModel pm = PageModelContext.getPageModel();
		List<VideoInfo> videoInfoList = this.videoInfoDao.query(hql, pm, VideoInfo.class, null);
		int length = videoInfoList.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				VideoInfo videoInfo = videoInfoList.get(i);
				videoInfo.setStr_tag_name(videoInfo.getKnowlgTag().getName());
			}
			appResult.setData(videoInfoList);
			appResult.setPage_model(pm);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setData("");
			appResult.setError_info("无数据");
		}
		return appResult;
	}

	@Override
	public void deleteVideoInfo(Integer id) {
		this.videoInfoDao.delete(VideoInfo.class, id);
	}

	@Override
	public VideoInfo findVideoInfo(Integer id) {
		VideoInfo videoInfo = this.videoInfoDao.get(VideoInfo.class, id);
		return videoInfo;
	}
	
	@Override
	public AppResult updateVideoInfo(VideoInfo videoInfo){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer id = videoInfo.getId();
		if(id==null){
			appResult.setError_info("视频id不能为空");
			return appResult;
		}
		appResult = BeanUtils.uploadImage(videoInfo, "videoInfo");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		VideoInfo v = this.videoInfoDao.findByColumnValue(VideoInfo.class, "id", id);
		BeanUtils.copy(videoInfo, v);
		Integer tag_id = videoInfo.getTag_id();
		if(tag_id!=null){
			KnowlgTag knowlgTag = new KnowlgTag();
			knowlgTag.setId(tag_id);
			v.setKnowlgTag(knowlgTag);
		}
		this.videoInfoDao.update(v);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult addVideoInfo(VideoInfo videoInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		appResult = BeanUtils.uploadImage(videoInfo, "videoInfo");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		this.videoInfoDao.save(videoInfo);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult updateVideoHitsCount(Integer id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(id==null){
			appResult.setError_info("视频id不能为空");
			return appResult;
		}
		VideoInfo videoInfo = this.videoInfoDao.findByColumnValue(VideoInfo.class, "id", id);
		if(videoInfo!=null){
			videoInfo.setView_count(videoInfo.getView_count()+1);
			this.videoInfoDao.update(videoInfo);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setData("");
			appResult.setError_info("所属视频为空");
		}
		return appResult;
	}

}
