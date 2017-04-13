package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgInfo;

public interface KnowlgInfoService {

	
	public List<KnowlgInfo> queryList();
	public AppResult getKnowlgInfoList(KnowlgInfo knowlgInfo);
	public KnowlgInfo findKnowlgInfoInfo(Integer id);
	public AppResult reKnowlgInfo(Integer id);
	public AppResult updateKnowlgInfo(KnowlgInfo knowlgInfo);
	public void deleteKnowlgInfo(Integer id);
	public AppResult addKnowlgInfo(KnowlgInfo knowlgInfo);
}
