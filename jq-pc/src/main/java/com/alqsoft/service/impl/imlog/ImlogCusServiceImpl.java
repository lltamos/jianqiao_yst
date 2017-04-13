package com.alqsoft.service.impl.imlog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.imlog.ImlogDao;
import com.alqsoft.service.imlog.ImlogCusService;

@Service
@Transactional(readOnly = true)
public class ImlogCusServiceImpl implements ImlogCusService {

	@Autowired
	private ImlogDao imlogDao;
	
	@Override
	public List<Map<String, Object>> findAllByCustomerId(Long cid,int page,int row) {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		int index=(page-1)*row;
		map.put("startIndex", index);
		map.put("endIndex", row);
		map.put("id", cid);
	
		return imlogDao.findAllByCustomerId(map);
	}

	@Override
	public int getCountByCustomerId(Long cid) {
		
		return imlogDao.getCountByCustomerId(cid);
	}

	
	
}
