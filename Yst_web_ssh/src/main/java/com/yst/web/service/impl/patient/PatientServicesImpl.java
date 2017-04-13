package com.yst.web.service.impl.patient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.CustomerRelativeDao;
import com.yst.web.dao.DicRelationDao;
import com.yst.web.dao.patient.PatientsDao;
import com.yst.web.entity.patient.Patient;
import com.yst.web.model.Customer;
import com.yst.web.model.DicRelation;
import com.yst.web.service.patient.PatientService;
import com.yst.web.utils.CommUtils;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月19日 下午2:45:08
 * 
 */
@Service
@Transactional(readOnly=true)
public class PatientServicesImpl implements PatientService{

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private PatientsDao patientsDao;
	
	@Resource(name="dicRelationDao")
	private DicRelationDao dicRelationDao;
	
	@Autowired
	private CustomerRelativeDao customerRelativeDao;
	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Patient get(Long arg0) {
		return null;
	}

	@Override
	public Patient saveAndModify(Patient arg0) {
		return null;
	}

	@Override
	public Result findPatientArchivesByCid(Integer customerId, Integer page,Integer rows) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_custmoerId", customerId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Patient> specification = DynamicSpecifications.bySearchFilter(filter.values(),Patient.class);
		Page<Patient> patientPages = patientsDao.findAll(specification, new PageRequest(page,rows));
		List<Patient> patientList = patientPages.getContent();
		for (Patient patient : patientList) {
			String relationId = patient.getRelationId();
			Integer rid = Integer.valueOf(relationId);
			DicRelation dicRelation = this.dicRelationDao.findByColumnValue(DicRelation.class, "relation_id", rid);
			patient.setRelationId(dicRelation.getName());
		}
		if(patientList.size() == 0){
			return ResultUtils.returnError("暂无记录");
		}
		return ResultUtils.returnSuccess("获取成功", patientPages);
	}

	@Override
	@Transactional(readOnly=false)
	public Result savePatientArchives(Patient patient, Integer customerId, Integer relation_id) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(relation_id == null){
			return ResultUtils.returnError("关系id不能为空");
		}
		patient.setCustmoerId(customerId);
		Patient patient1 = this.patientsDao.save(patient);
		return ResultUtils.returnSuccess("添加成功", patient1);
	}

}
