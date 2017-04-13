package com.alqsoft.service.relativemedicinerecord;

import java.util.List;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.relativemedicinerecord.RelativeMedicineRecord;
import com.alqsoft.utils.BootStrapResult;

public interface RelativeMedicineRecordService extends BaseService<RelativeMedicineRecord>{

	/**
	 * 用药记录列表
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<RelativeMedicineRecord>> findRelativeMedicineRecordByPage(Integer page, Integer length);

	/**
	 * 添加病例记录
	 * @param relativeMedicineRecord
	 * @return
	 */
	public Result relativeMedicineRecordAdd(RelativeMedicineRecord relativeMedicineRecord);

	/**
	 * 按照编号删除
	 * @param cirid
	 * @return
	 */
	public Result relativeMedicineRecordRemove(Long cirid);

	/**
	 * 修改病例
	 * @param relativeMedicineRecord
	 * @return
	 */
	public Result relativeMedicineRecordUpdate(RelativeMedicineRecord relativeMedicineRecord);

}
