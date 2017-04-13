package com.alqsoft.service.impl.patientorder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.patientorder.PatientOrderDao;
import com.alqsoft.entity.order.PatientOrder;
import com.alqsoft.service.patientorder.PatientOrderService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
@Service
@Transactional
public class PatientOrderServiceImpl implements PatientOrderService{

	@Autowired
	private PatientOrderDao patientOrderDao;
	
	@Override
	public boolean delete(Long arg0) {
		patientOrderDao.delete(arg0);
		return true;
	}

	@Override
	public PatientOrder get(Long arg0) {
		return patientOrderDao.findOne(arg0);
	}

	@Override
	public PatientOrder saveAndModify(PatientOrder arg0) {
		PatientOrder patientOrder = patientOrderDao.save(arg0);
		return patientOrder;
	}

	@Override
	public BootStrapResult<List<PatientOrder>> findPatientOrderByPage(Integer patientId, Integer page, Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_patientId", patientId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<PatientOrder> specification = DynamicSpecifications.bySearchFilter(filter.values(),PatientOrder.class);
		Page<PatientOrder> doctor = patientOrderDao.findAll(specification,new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(PatientOrder.class, doctor);
	}

}
