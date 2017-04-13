package com.alqsoft.service.impl.patientmedicationrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.alqsoft.dao.patientmedicationrecord.PatientMedicationRecordDao;
import com.alqsoft.entity.patient.PatientMedicationRecord;
import com.alqsoft.service.patientmedicationrecord.PatientMedicationRecordService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

public class PatientMedicationRecordServiceImpl implements PatientMedicationRecordService{

	@Autowired
	private PatientMedicationRecordDao patientMedicationRecordDao;
	@Override
	public boolean delete(Long arg0) {
		patientMedicationRecordDao.delete(arg0);
		return true;
	}

	@Override
	public PatientMedicationRecord get(Long arg0) {
		return patientMedicationRecordDao.findOne(arg0);
	}

	@Override
	public PatientMedicationRecord saveAndModify(PatientMedicationRecord arg0) {
		return patientMedicationRecordDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<PatientMedicationRecord>> findPatientMedicationRecordByPage(String doctorname,
			Integer page, Integer length) {
			/*Map<String, Object> params = new HashMap<String, Object>();
			params.put("EQ_name", patientMedicationRecordname);
			Map<String, SearchFilter> filter = SearchFilter.parse(params);
			Specification<PatientMedicationRecord> specification = DynamicSpecifications.bySearchFilter(filter.values(),PatientMedicationRecord.class);*/
			Page<PatientMedicationRecord> patientMedicationRecord = patientMedicationRecordDao.findAll(new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
			return BootStrapResultUtils.returnPage(PatientMedicationRecord.class, patientMedicationRecord);
	}

}
