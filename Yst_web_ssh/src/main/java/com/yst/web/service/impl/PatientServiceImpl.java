package com.yst.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yst.web.dao.PatientDao;
import com.yst.web.entity.patient.Patient;
import com.yst.web.entity.patient.PatientCase;
import com.yst.web.entity.patient.PatientHistory;
import com.yst.web.entity.patient.PatientImage;
import com.yst.web.entity.patient.PatientMedicationRecord;
import com.yst.web.entity.patient.PatientParams;
import com.yst.web.model.AppResult;
import com.yst.web.model.PageModel;
import com.yst.web.service.PatientService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.UpLoadUtils;
@Service
@Transactional
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientDao patientDao;
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Patient> selectPatientPage(HttpServletRequest request, HttpServletResponse response) {
		
		return (List<Patient>) this.patientDao.findByPage(Patient.class);
	}
	@Override
	public AppResult addOrUpdatePatient(Patient patient,MultipartFile[] imagePatients,PatientParams patientParams,HttpServletRequest request,
			HttpServletResponse response) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Long id =patient.getId();
		if(!CommUtils.isNull(id)){
			Patient dbPatient =this.patientDao.get(Patient.class, id.intValue());
			BeanUtils.copy(patient, dbPatient);
		}
		if(patient.getCustmoerId()==null){
			patient.setCustmoerId(null);
		}
		patient.setCreatedTime(new Date());
		this.patientDao.saveOrUpdate(patient);
		if(!CommUtils.isNull(patientParams)){
			for (String str : patientParams.getPatientCases()) {
				PatientCase pc = new PatientCase();
				pc.setPatient(patient);
				pc.setContent(str);
				this.patientDao.save(pc);
			}
			for (String str : patientParams.getPatientHistorys()) {
				PatientHistory ph = new PatientHistory();
				ph.setPatient(patient);
				ph.setContent(str);
				this.patientDao.save(ph);
			}
			for (String str : patientParams.getPatientMedicationRecords()) {
				PatientMedicationRecord pmr = new PatientMedicationRecord();
				pmr.setPatient(patient);
				pmr.setContent(str);
				this.patientDao.save(pmr);
			}
		}
		if(imagePatients != null && imagePatients.length>0){
			for (MultipartFile urlfile : imagePatients) {
				PatientImage pi=(PatientImage) UpLoadUtils.springSaveFile(urlfile, "PatientImage").getContent();
				pi.setPatient(patient);
				this.patientDao.save(pi);
			}
		}
		appResult.setData(patient);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public Map<String, Object> findByID(Long id) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Patient patient =this.patientDao.get(Patient.class, id);
		resultMap.put("patient", patient);
		List<PatientImage> patientImageList=this.patientDao.selectByColumnValue(PatientImage.class,"patient.id", id);
		if(patientImageList.size()>0){
			resultMap.put("patientImage", patientImageList);
		}
		List<PatientCase> patientCaseList=this.patientDao.selectByColumnValue(PatientCase.class,"patient.id", id);
		if(patientCaseList.size()>0){
			resultMap.put("patientCase", patientCaseList);
		}
		List<PatientHistory> patientHistoryList=this.patientDao.selectByColumnValue(PatientHistory.class,"patient.id", id);
		if(patientHistoryList.size()>0){
			resultMap.put("patientHistory", patientHistoryList);
		}
		List<PatientMedicationRecord> patientMedicationRecordList=this.patientDao.selectByColumnValue(PatientMedicationRecord.class,"patient.id", id);
		if(patientMedicationRecordList.size()>0){
			resultMap.put("patientMedicationRecord", patientMedicationRecordList);
		}
		return resultMap;
	}
	@Override
	public Patient getPatient(Long patientId) {
		return this.patientDao.get(Patient.class, patientId);
	}
	@Override
	public List<Patient> findPatientManagerPage(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> params = new HashMap<String,Object>();
		//params.put("", value);
		List<Patient> patientList = patientDao.findByPage(Patient.class, null);
		return patientList;
	}
	@Override
	public BootStrapResult<List<Map<String, Object>>> findIllnessRecordPage(Integer patient_id) {
		String sql = "select cir.* from customer_relative cr, customer_illness_record cir where cr.relative_id = cir.relative_id and cr.patient_id = "+patient_id;
		PageModel pm = PageModelContext.getPageModel();
		Session session = (Session) this.entityManager.getDelegate();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		int count = sqlQuery.list().size();
		sqlQuery.setFirstResult(pm.getStartIndex());
		sqlQuery.setMaxResults(pm.getPageSize());
		List<Map<String, Object>> list = sqlQuery.list();

		BootStrapResult<List<Map<String, Object>>> br = new BootStrapResult<List<Map<String, Object>>>();
		br.setResult("获取成功！");
		br.setData(list);
		br.setRecordsFiltered(count);
		br.setRecordsTotal(count);
		entityManager.close();
		return br;
	}
	@Override
	public BootStrapResult<List<Map<String, Object>>> findRelativeMedicineRecordPage(Integer patient_id) {
		String sql = "select rmr.* from customer_relative cr, relative_medicine_record rmr where cr.relative_id = rmr.relative_id and cr.patient_id = "+patient_id;
		PageModel pm = PageModelContext.getPageModel();
		Session session = (Session) this.entityManager.getDelegate();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		int count = sqlQuery.list().size();
		sqlQuery.setFirstResult(pm.getStartIndex());
		sqlQuery.setMaxResults(pm.getPageSize());
		List<Map<String, Object>> list = sqlQuery.list();

		BootStrapResult<List<Map<String, Object>>> br = new BootStrapResult<List<Map<String, Object>>>();
		br.setResult("获取成功！");
		br.setData(list);
		br.setRecordsFiltered(count);
		br.setRecordsTotal(count);
		entityManager.close();
		return br;
	}
}
