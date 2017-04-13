package com.alqsoft.service.customerillnessrecord;

import java.util.List;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.customerillnessrecord.CustomerIllnessRecord;
import com.alqsoft.utils.BootStrapResult;

public interface CustomerIllnessRecordService extends BaseService<CustomerIllnessRecord>{

	/**
	 * 病例记录列表
	 * @param doctorname
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<CustomerIllnessRecord>> findCustomerIllnessRecordByPage(String doctorname, Integer page,
			Integer length);

	/**
	 * 添加病例记录
	 * @param customerillnessrecord
	 * @return
	 */
	public Result customerillnessrecordAdd(CustomerIllnessRecord customerillnessrecord);

	/**
	 * 按照编号删除
	 * @param cirid
	 * @return
	 */
	public Result customerillnessrecordRemove(Long cirid);

	/**
	 * 修改病例
	 * @param customerillnessrecord
	 * @return
	 */
	public Result customerillnessrecordUpdate(CustomerIllnessRecord customerillnessrecord);

}
