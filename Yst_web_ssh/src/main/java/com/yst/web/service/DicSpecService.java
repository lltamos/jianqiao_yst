package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicSpec;

public interface DicSpecService {
	public List<DicSpec> selectAll();
	
	public List<DicSpec> queryList();
	public DicSpec findDicSpecInfo(Integer spec_id);
	public AppResult updateDicSpec(DicSpec dicSpec);
	public void deleteDicSpec(Integer spec_id);
	public AppResult addDicSpec(DicSpec dicSpec);
}
