package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgTag;

public interface KnowlgTagService {
	
	public List<KnowlgTag> queryList();
	public AppResult getKnowlgTagList();
	public KnowlgTag findKnowlgTagInfo(Integer id);
	public AppResult updateKnowlgTag(KnowlgTag knowlgTag);
	public void deleteKnowlgTag(Integer id);
	public AppResult addKnowlgTag(KnowlgTag knowlgTag);
}
