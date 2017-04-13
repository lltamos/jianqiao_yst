package com.alqsoft.service.impl.imlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.imlog.ImlogDao;
import com.alqsoft.service.imlog.ImlogService;

@Service
@Transactional(readOnly=true)
public class ImlogServiceImpl implements ImlogService {


	@Autowired
	private ImlogDao imlogDao;

	@Override
	public List<Map<String, Object>> findAllByDoctorId(Long doctorId, Integer page, Integer rows) {
		Map<String, Object> map = new HashMap<String, Object>();
		int index=(page-1)*rows;
		map.put("doctorId", doctorId);
		map.put("startIndex", index);
		map.put("endIndex", rows);
		List<Map<String, Object>> list = imlogDao.findAllByDoctorId(map);
		return list;
	}

	@Override
	public int getCountByDoctorId(Long doctorId) {
		return imlogDao.getCountByDoctorId(doctorId);
	}

	

	
}
