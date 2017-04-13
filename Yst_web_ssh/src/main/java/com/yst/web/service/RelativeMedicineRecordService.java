package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.RelativeMedicineRecord;

public interface RelativeMedicineRecordService {
	public RelativeMedicineRecord findRelativeMedicineRecordInfo(Integer record_id);
	public AppResult updateRelativeMedicineRecord(RelativeMedicineRecord relativeMedicineRecord);
	public List<RelativeMedicineRecord> findRelativeMedicineRecordList();
	public AppResult addRelativeMedicineRecord(RelativeMedicineRecord relativeMedicineRecord);
	public AppResult getRelativeMedicineRecord(int relative_id);
	public AppResult deleteRelativeMedicineRecord(int record_id);
}
