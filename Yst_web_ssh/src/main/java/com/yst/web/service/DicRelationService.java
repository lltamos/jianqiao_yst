package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicRelation;

public interface DicRelationService {
	
	public List<DicRelation> queryList();
	public DicRelation findDicRelationInfo(Integer relation_id);
	public AppResult updateDicRelation(DicRelation dicRelation);
	public void deleteDicRelation(Integer relation_id);
	public AppResult addDicRelation(DicRelation dicRelation);
}
