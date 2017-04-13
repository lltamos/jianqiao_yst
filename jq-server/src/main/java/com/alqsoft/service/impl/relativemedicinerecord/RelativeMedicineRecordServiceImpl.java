package com.alqsoft.service.impl.relativemedicinerecord;

import java.util.List;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.customerrelative.CustomerRelativeDao;
import com.alqsoft.dao.relativemedicinerecord.RelativeMedicineRecordDao;
import com.alqsoft.entity.relativemedicinerecord.RelativeMedicineRecord;
import com.alqsoft.service.relativemedicinerecord.RelativeMedicineRecordService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
@Service
@Transactional
public class RelativeMedicineRecordServiceImpl implements RelativeMedicineRecordService{

	@Autowired
	private RelativeMedicineRecordDao relativeMedicineRecordDao;
	@Autowired
	private CustomerRelativeDao customerRelativeDao;
	
	@Override
	public boolean delete(Long arg0) {
		relativeMedicineRecordDao.delete(arg0);
		return true;
	}

	@Override
	public RelativeMedicineRecord get(Long arg0) {
		return relativeMedicineRecordDao.findOne(arg0);
	}

	@Override
	public RelativeMedicineRecord saveAndModify(RelativeMedicineRecord arg0) {
		return relativeMedicineRecordDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<RelativeMedicineRecord>> findRelativeMedicineRecordByPage(Integer page,
			Integer length) {
		Page<RelativeMedicineRecord> doctor = relativeMedicineRecordDao.findAll(new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(RelativeMedicineRecord.class, doctor);
	}
	

	@Override
	public Result relativeMedicineRecordAdd(RelativeMedicineRecord relativeMedicineRecord) {
		/*CustomerRelative customerRelative = customerRelativeDao.findOne(new Long(relativeMedicineRecord.getRelativeId()));
		relativeMedicineRecord.setRelativeName(customerRelative.getName());*/
		String relativeName = relativeMedicineRecord.getRelativeName();
		String[] split = relativeName.split("_");
		relativeMedicineRecord.setRelativeId(new Long(split[0]));
		relativeMedicineRecord.setRelativeName(split[1]);
		relativeMedicineRecordDao.save(relativeMedicineRecord);
		return ResultUtils.returnSuccess("保存成功");
	}

	@Override
	public Result relativeMedicineRecordRemove(Long cirid) {
		relativeMedicineRecordDao.delete(cirid);
		return ResultUtils.returnSuccess("删除成功");
	}

	@Override
	public Result relativeMedicineRecordUpdate(RelativeMedicineRecord relativeMedicineRecord) {
		RelativeMedicineRecord dbRelativeMedicineRecord = relativeMedicineRecordDao.findOne(relativeMedicineRecord.getId());
		String relativeName = relativeMedicineRecord.getRelativeName();
		String[] rns = relativeName.split("_");
		dbRelativeMedicineRecord.setRelativeId(new Long(rns[0]));
		dbRelativeMedicineRecord.setRelativeName(rns[1]);
		dbRelativeMedicineRecord.setDes(relativeMedicineRecord.getDes());
		dbRelativeMedicineRecord.setDoctorName(relativeMedicineRecord.getDoctorName());
		dbRelativeMedicineRecord.setOfficeName(relativeMedicineRecord.getOfficeName());
		dbRelativeMedicineRecord.setDuring(relativeMedicineRecord.getDuring());
		dbRelativeMedicineRecord.setStartDate(relativeMedicineRecord.getStartDate());
		relativeMedicineRecordDao.save(dbRelativeMedicineRecord);
		return ResultUtils.returnSuccess("修改成功");
	}
}
