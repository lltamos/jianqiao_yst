package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicTitle;

public interface DicTitleService {
	public List<DicTitle> selectAll();
	
	public List<DicTitle> queryList();
	public DicTitle findDicTitleInfo(Integer title_id);
	public AppResult updateDicTitle(DicTitle dicTitle);
	public void deleteDicTitle(Integer title_id);
	public AppResult addDicTitle(DicTitle dicTitle);
}
