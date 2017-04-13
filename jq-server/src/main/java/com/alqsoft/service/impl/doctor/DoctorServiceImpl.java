package com.alqsoft.service.impl.doctor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.dic.DicOfficeDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.UpLoadUtils;
@Service
@Transactional(readOnly=true)
@SuppressWarnings("unused")
public class DoctorServiceImpl implements DoctorService{

	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private DicOfficeDao dicOfficeDao;
	
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		doctorDao.delete(arg0);
		return true;
	}

	@Override
	public Doctors get(Long arg0) {
		return doctorDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Doctors saveAndModify(Doctors arg0) {
		return doctorDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<Doctors>> findDoctorByPage(String phone,Long status ,Integer start,Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_customerPhone", phone);
		params.put("EQ_deleted", status);
		params.put("EQ_applyStatus", 1);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Doctors> specification = DynamicSpecifications.bySearchFilter(filter.values(),Doctors.class);
		Page<Doctors> doctor = doctorDao.findAll(specification,new PageRequest(start, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Doctors.class, doctor);
	}

	@Override
	@Transactional
	public Result doctorApply(Doctors doctor,MultipartFile[] imageDoctor) {
		Customer customer = customerDao.findOne(new Long(doctor.getCustomerId()));
		if(customer == null){
			return ResultUtils.returnError("用户信息不存在");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_name", doctor.getName());
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Doctors> specification = DynamicSpecifications.bySearchFilter(filter.values(),Doctors.class);
		Doctors dbdoctors = doctorDao.findOne(specification);
		if(dbdoctors!=null){
			return ResultUtils.returnError("该名称已被使用！");
		}
		try {
			String onstr = doctor.getOfficeName();
			String[] offices = onstr.split("_");
			doctor.setOfficeId(new Long(offices[0]));
			doctor.setOfficeName(offices[1]);
			String specNames = doctor.getSpecName();
			String[] sns = specNames.split("_");
			doctor.setSpecId(new Long(sns[0]));
			doctor.setSpecName(sns[1]);
			String titleName = doctor.getTitleName();
			String[] tns = titleName.split("_");
			doctor.setTitleId(new Long(tns[0]));
			doctor.setTitleName(tns[1]);
			String hospitalname = doctor.getHospitalypeName();
			String[] hts = hospitalname.split("_");
			doctor.setHospitalypeId(new Long(hts[0]));
			doctor.setHospitalypeName(hts[1]);
			String merchantName = doctor.getMerchantName();
			String[] mns = merchantName.split("_");
			doctor.setMerchantId(new Long(mns[0]));
			doctor.setMerchantName(mns[1]);
			
			if(imageDoctor != null && imageDoctor.length>0){
				for (int i = 0; i < imageDoctor.length; i++) {
					String content = (String) UpLoadUtils.springUploadFile(imageDoctor[i], "doctorImage").getContent();
					int num = 0;
					num += i;
					if(num == 0){
						doctor.setImage_header(content);
					}
					if(num == 1){
						doctor.setImageWork1(content);
					}
					if(num == 2){
						doctor.setImageWork2(content);
					}
				}
			}
			//等待验证
			doctor.setVerify(0);
			doctorDao.save(doctor);
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.returnError("保存失败！");
		}
		return ResultUtils.returnSuccess("保存成功！");
	}

	@Override
	public Doctors findByDoctorId(Long doctorid) {
		Doctors doctors = doctorDao.findOne(doctorid);
		return doctors;
	}

	@Override
	@Transactional
	public Result doctorUpdate(Doctors doctor) {
		Long did = doctor.getId();
		Doctors doctors = doctorDao.findOne(did);
		if(doctors == null){
			return ResultUtils.returnError("改医生不存在");
		}
		Customer customer = customerDao.findOne(new Long(doctors.getCustomerId()));
		if(customer == null){
			return ResultUtils.returnError("用户信息不存在");
		}
		if(!doctor.getName().equals(doctors.getName())){
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("EQ_name", doctor.getName());
			Map<String, SearchFilter> filter = SearchFilter.parse(params);
			Specification<Doctors> specification = DynamicSpecifications.bySearchFilter(filter.values(),Doctors.class);
			Doctors dbdoctors = doctorDao.findOne(specification);
			if(dbdoctors!=null){
				return ResultUtils.returnError("该名称已被使用！");
			}
		}
		try {
			
			String onstr = doctor.getOfficeName();
			String[] offices = onstr.split("_");
			doctor.setOfficeId(new Long(offices[0]));
			doctor.setOfficeName(offices[1]);
			String specNames = doctor.getSpecName();
			String[] sns = specNames.split("_");
			doctor.setSpecId(new Long(sns[0]));
			doctor.setSpecName(sns[1]);
			String titleName = doctor.getTitleName();
			String[] tns = titleName.split("_");
			doctor.setTitleId(new Long(tns[0]));
			doctor.setTitleName(tns[1]);
			String hospitalname = doctor.getHospitalypeName();
			String[] hts = hospitalname.split("_");
			doctor.setHospitalypeId(new Long(hts[0]));
			doctor.setHospitalypeName(hts[1]);
			String merchantName = doctor.getMerchantName();
			String[] mns = merchantName.split("_");
			doctor.setMerchantId(new Long(mns[0]));
			doctor.setMerchantName(mns[1]);
			doctorDao.save(doctor);
			return ResultUtils.returnSuccess("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return ResultUtils.returnError("保存失败！");
		}
	}

	@Override
	@Transactional
	public Result doctorApproval(Long doctorid, Integer verify) {
		Doctors dbdoctor = doctorDao.findOne(doctorid);
		if(dbdoctor == null){
			return ResultUtils.returnError("医生信息有误");
		}
		Customer customer = customerDao.findOne(dbdoctor.getCustomerId());
		if(customer == null){
			return ResultUtils.returnError("医生信息有误");
		}
		
		dbdoctor.setDeleted(0);
		dbdoctor.setType(1);
		dbdoctor.setVerify(verify);
		doctorDao.save(dbdoctor);
		
		//verify 1审核 0取消审核
		customer.setType(verify);
		customerDao.save(customer);
		
		return ResultUtils.returnSuccess("操作成功");
	}

	@Override
	public List<Doctors> findSelect(HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		searchParams.put("EQ_applyStatus", 1);
		searchParams.put("EQ_deleted", 0);
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<Doctors> specification = DynamicSpecifications.bySearchFilter(filter.values(), Doctors.class);
		return doctorDao.findAll(specification);
	}

	@Override
	public Doctors getDoctorByCustomerID(Long id) {
		Doctors doctors = doctorDao.getDoctorsByCustomerId(id);
		return doctors;
	}

	@Override
	@Transactional
	public Result saveApplyDoctor(Doctors doctor) {
		Result result = new Result();
		doctor.setDeleted(0);
		try {
			doctorDao.save(doctor);
			result.setCode(0);
			result.setMsg("申请成功");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("申请失败");
		}
		return result;
	}

	@Override
	public BootStrapResult<List<Doctors>> findApplyDoctorByPage(String phone,Integer applyStatus, Integer page, Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_customerPhone", phone);
		params.put("EQ_applyStatus", applyStatus);
		params.put("OR_applyStatus", "0,2");
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Doctors> specification = DynamicSpecifications.bySearchFilter(filter.values(),Doctors.class);
		Page<Doctors> doctor = doctorDao.findAll(specification,new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Doctors.class, doctor);
	}

	@Override
	@Transactional
	public Result examineDoctor(Integer apply, Long id, String reason) {
		Result result = new Result();
		if(id == null){
			result.setCode(1);
			result.setMsg("医生不存在");
			return result;
		}
		Doctors doctors = doctorDao.getlock(id);
		if(doctors == null){
			result.setCode(1);
			result.setMsg("医生不存在");
			return result;
		}
		if(apply == null){
			result.setCode(1);
			result.setMsg("审核状态不明");
			return result;
		}
		Long customerId = doctors.getCustomerId();
		Customer findOne = customerDao.findOne(customerId);
		if(findOne == null){
			result.setCode(1);
			result.setMsg("医生不存在");
			return result;
		}
		if(apply == 1){
			findOne.setType(1);
			doctors.setReason("通过");
		}else if(apply == 2){
			doctors.setReason(reason);
			findOne.setType(0);
		}
		try {
			customerDao.save(findOne);
			result.setCode(0);
			result.setMsg("审核完成");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("审核失败");
		}
		doctors.setApplyStatus(apply);
		doctors.setDeleted(0);
		try {
			doctorDao.save(doctors);
			result.setCode(0);
			if(apply == 1){
				result.setMsg("审核通过成功");
			}else if(apply == 2){
				result.setMsg("审核未通过成功");
			}
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("审核失败");
		}
		return result;
	}

	@Override
	@Transactional
	public Result doctorDisable(Long doctorid) {
		Result result = new Result();
		Doctors doctors = doctorDao.findOne(doctorid);
		if(doctors.getDeleted() == 1){
			doctors.setDeleted(0);
			try {
				doctorDao.save(doctors);
				result.setCode(0);
				result.setMsg("解禁成功");
			} catch (Exception e) {
				result.setCode(1);
				result.setMsg("解禁失败");
			}
		}else if(doctors.getDeleted() == 0){
			doctors.setDeleted(1);
			try {
				doctorDao.save(doctors);
				result.setCode(0);
				result.setMsg("禁用成功");
			} catch (Exception e) {
				result.setCode(1);
				result.setMsg("禁用失败");
			}
		}
		return result;
	}


}
