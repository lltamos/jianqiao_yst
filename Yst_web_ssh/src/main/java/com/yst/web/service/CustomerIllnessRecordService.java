package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerIllnessRecord;

public interface CustomerIllnessRecordService {
	public List<CustomerIllnessRecord> selectAll();
	public List<CustomerIllnessRecord> findCustomerIllnessRecordList();
	public CustomerIllnessRecord findCustomerIllnessRecordInfo(Integer record_id);
	public AppResult updateCustomerIllnessRecord(CustomerIllnessRecord customerIllnessRecord);
	public AppResult queryRecordList(CustomerIllnessRecord customerIllnessRecord);
	public AppResult findDetails(int id);
	public AppResult addIllnessRecord(CustomerIllnessRecord customerIllnessRecord);
	public AppResult deleteIllnessRecord(Integer record_id);
	public void deleteByParame(Integer relative_id);
}
