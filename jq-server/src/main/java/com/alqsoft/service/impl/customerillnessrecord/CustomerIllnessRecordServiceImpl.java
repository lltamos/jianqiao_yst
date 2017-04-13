package com.alqsoft.service.impl.customerillnessrecord;

import java.util.List;

import javax.transaction.Transactional;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.customerillnessrecord.CustomerIllnessRecordDao;
import com.alqsoft.dao.customerrelative.CustomerRelativeDao;
import com.alqsoft.entity.customerillnessrecord.CustomerIllnessRecord;
import com.alqsoft.entity.customerrelative.CustomerRelative;
import com.alqsoft.service.customerillnessrecord.CustomerIllnessRecordService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
@Service
@Transactional
public class CustomerIllnessRecordServiceImpl implements CustomerIllnessRecordService{

	
	@Autowired
	private CustomerIllnessRecordDao  customerIllnessRecordDao;
	@Autowired
	private CustomerRelativeDao customerRelativeDao;
	@Override 
	public boolean delete(Long arg0) {
		customerIllnessRecordDao.delete(arg0);
		return true;
	}

	@Override
	public CustomerIllnessRecord get(Long arg0) {
		return customerIllnessRecordDao.findOne(arg0);
	}

	@Override
	public CustomerIllnessRecord saveAndModify(CustomerIllnessRecord arg0) {
		return customerIllnessRecordDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<CustomerIllnessRecord>> findCustomerIllnessRecordByPage(String doctorname, Integer page,
			Integer length) {
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_name", doctorname);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<CustomerIllnessRecord> specification = DynamicSpecifications.bySearchFilter(filter.values(),CustomerIllnessRecord.class);*/
		Page<CustomerIllnessRecord> customerIllnessRecordpage = customerIllnessRecordDao.findAll(new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(CustomerIllnessRecord.class, customerIllnessRecordpage);
	}

	@Override
	public Result customerillnessrecordAdd(CustomerIllnessRecord customerillnessrecord) {
		CustomerRelative customerRelative = customerRelativeDao.findOne(new Long(customerillnessrecord.getRelativeId()));
		customerillnessrecord.setRelativeName(customerRelative.getName());
		customerIllnessRecordDao.save(customerillnessrecord);
		return ResultUtils.returnSuccess("保存成功");
	}

	@Override
	public Result customerillnessrecordRemove(Long cirid) {
		customerIllnessRecordDao.delete(cirid);
		return ResultUtils.returnSuccess("删除成功");
	}

	@Override
	public Result customerillnessrecordUpdate(CustomerIllnessRecord customerillnessrecord) {
		
		String relativeName = customerillnessrecord.getRelativeName();
		String[] rns = relativeName.split("_");
		customerillnessrecord.setRelativeId(new Long(rns[0]));
		customerillnessrecord.setRelativeName(rns[1]);
		customerIllnessRecordDao.save(customerillnessrecord);
		return ResultUtils.returnSuccess("修改成功");
	}

}
