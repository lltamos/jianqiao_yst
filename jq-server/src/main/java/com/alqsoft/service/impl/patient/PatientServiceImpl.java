package com.alqsoft.service.impl.patient;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.dao.patient.PatientDao;
import com.alqsoft.dao.patientcase.PatientCaseDao;
import com.alqsoft.dao.patienthistory.PatientHistoryDao;
import com.alqsoft.dao.patientimage.PatientImageDao;
import com.alqsoft.dao.patientmedicationrecord.PatientMedicationRecordDao;
import com.alqsoft.entity.patient.Patient;
import com.alqsoft.entity.patient.PatientCase;
import com.alqsoft.entity.patient.PatientHistory;
import com.alqsoft.entity.patient.PatientImage;
import com.alqsoft.entity.patient.PatientMedicationRecord;
import com.alqsoft.entity.patient.PatientParams;
import com.alqsoft.model.AppResult;
import com.alqsoft.service.patient.PatientService;
import com.alqsoft.utils.BeanUtils;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.CommUtils;
import com.alqsoft.utils.oss.UpLoadUtils;
@Service
@Transactional
public class PatientServiceImpl implements PatientService{

	
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientCaseDao patientCaseDao;
	@Autowired
	private PatientHistoryDao patientHistoryDao;
	@Autowired
	private PatientMedicationRecordDao patientMedicationRecordDao;
	@Autowired
	private PatientImageDao patientImageDao;
	
	@Override
	public boolean delete(Long arg0) {
		patientDao.delete(arg0);
		return true;
	}

	@Override
	public Patient get(Long arg0) {
		return patientDao.findOne(arg0);
	}

	@Override
	public Patient saveAndModify(Patient arg0) {
		return patientDao.save(arg0);
	}
	
	@Override
	public BootStrapResult<List<Patient>> findPatientByPage(String doctorname,Integer start,Integer length) {
		/*Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_name", doctorname);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Patient> specification = DynamicSpecifications.bySearchFilter(filter.values(),Patient.class);*/
		Page<Patient> patient = patientDao.findAll(new PageRequest(start, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Patient.class, patient);
	}

	@Override
	public AppResult addOrUpdatePatient(Patient patient,MultipartFile[] imagePatients,PatientParams patientParams,HttpServletRequest request,
			HttpServletResponse response) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Long id =patient.getId();
		if(!CommUtils.isNull(id)){
			Patient dbPatient =this.patientDao.findOne(id);
			BeanUtils.copy(patient, dbPatient);
		}
		if(patient.getCustmoerId()==null){
			patient.setCustmoerId(null);
		}
		patient.setCreatedTime(new Date());
		this.patientDao.save(patient);
		if(!CommUtils.isNull(patientParams)){
			for (String str : patientParams.getPatientCases()) {
				PatientCase pc = new PatientCase();
				pc.setPatient(patient);
				pc.setContent(str);
				this.patientCaseDao.save(pc);
			}
			for (String str : patientParams.getPatientHistorys()) {
				PatientHistory ph = new PatientHistory();
				ph.setPatient(patient);
				ph.setContent(str);
				this.patientHistoryDao.save(ph);
			}
			for (String str : patientParams.getPatientMedicationRecords()) {
				PatientMedicationRecord pmr = new PatientMedicationRecord();
				pmr.setPatient(patient);
				pmr.setContent(str);
				this.patientMedicationRecordDao.save(pmr);
			}
		}
		if(imagePatients != null && imagePatients.length>0){
			for (MultipartFile urlfile : imagePatients) {
				PatientImage pi=(PatientImage) UpLoadUtils.springSaveFile(urlfile, "PatientImage").getContent();
				pi.setPatient(patient);
				this.patientImageDao.save(pi);
			}
		}
		appResult.setData(patient);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public Map<String, Object> getArchivesById(Long patientid) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Patient patient =this.patientDao.findOne(patientid);
		resultMap.put("patient", patient);
		List<PatientImage> patientImageList=this.patientImageDao.findPatientImageByPatientId(patientid);
		if(patientImageList.size()>0){
			resultMap.put("patientImage", patientImageList);
		}
		List<PatientCase> patientCaseList=this.patientCaseDao.findPatientCaseByPatientId(patientid);
		if(patientCaseList.size()>0){
			resultMap.put("patientCase", patientCaseList);
		}
		List<PatientHistory> patientHistoryList=this.patientHistoryDao.findPatientHistoryByPatientId(patientid);
		if(patientHistoryList.size()>0){
			resultMap.put("patientHistory", patientHistoryList);
		}
		List<PatientMedicationRecord> patientMedicationRecordList=this.patientMedicationRecordDao.findPatientMedicationRecordByPatientId(patientid);
		if(patientMedicationRecordList.size()>0){
			resultMap.put("patientMedicationRecord", patientMedicationRecordList);
		}
		return resultMap;
	}

}
