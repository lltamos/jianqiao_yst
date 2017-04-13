package com.alqsoft.service.impl.doctorimage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.doctorimage.DoctorImageDao;
import com.alqsoft.entity.DoctorImage;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.doctorimage.DoctorImageService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

@Service
@Transactional
public class DoctorImageServiceImpl implements DoctorImageService{

	@Autowired
	private DoctorImageDao doctorImageDao;
	
	@Override 
	public boolean delete(Long arg0) {
		doctorImageDao.delete(arg0);
		return true;
	}

	@Override
	public DoctorImage get(Long arg0) {
		return (DoctorImage) doctorImageDao.findOne(arg0);
	}


	@Override
	public DoctorImage saveAndModify(DoctorImage arg0) {
		return doctorImageDao.save(arg0);
	}

	@Override
	public List<DoctorImage> findById(Long doctoreId) {
		return doctorImageDao.findById(doctoreId);
	}

	@Override
	public BootStrapResult<List<DoctorImage>> findDoctorImageByPage(String doctorid, Integer page, Integer length) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_doctorId", doctorid);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<DoctorImage> specification = DynamicSpecifications.bySearchFilter(filter.values(),DoctorImage.class);
		Page<DoctorImage> doctorImage = doctorImageDao.findAll(specification,new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(DoctorImage.class, doctorImage);
	}

	@Override
	public Result saveDoctorImage(DoctorImage doctorImage) {
		Doctors doctors = doctorImageDao.getDoctorImageByDoctorId(new Long(doctorImage.getDoctorId()));
		if(doctors == null){
			return ResultUtils.returnError("所属医生不存在");
		}
		doctorImage.setDoctorName(doctors.getName());
		doctorImageDao.save(doctorImage);
		return ResultUtils.returnSuccess("保存成功");
	}

	@Override
	public Result deleteDoctorImage(Integer doctorimageid) {
		doctorImageDao.delete(new Long(doctorimageid));
		return ResultUtils.returnSuccess("删除成功");
	}

}
