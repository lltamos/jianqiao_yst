package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicOffice;

public interface DicOfficeService {
	
	public List<DicOffice> selectAll();
	
	public List<DicOffice> queryList();
	public DicOffice findDicOfficeInfo(Integer office_id);
	public AppResult updateDicOffice(DicOffice dicOffice);
	public void deleteDicOffice(Integer office_id);
	public AppResult addDicOffice(DicOffice dicOffice);
	
}
