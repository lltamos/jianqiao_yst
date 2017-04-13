package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.VideoInfo;

public interface VideoInfoService {

	public AppResult getAllList();
	public void deleteVideoInfo(Integer id);
	public VideoInfo findVideoInfo(Integer id);
	public AppResult updateVideoInfo(VideoInfo videoInfo);
	public AppResult updateVideoHitsCount(Integer id);
	public AppResult addVideoInfo(VideoInfo videoInfo);
}
