package com.alqsoft.service.impl.dic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.dic.DicRelationDao;
import com.alqsoft.entity.DicRelation;
import com.alqsoft.service.dic.DicRelationService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
@Service
@Transactional(readOnly=true)
public class DicRelationServiceImpl implements DicRelationService{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DicRelationServiceImpl.class);
	@Autowired
	private DicRelationDao dicRelationDao;
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			dicRelationDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public DicRelation get(Long arg0) {
		
		return dicRelationDao.findOne(arg0);
	}

	@Override
	@Transactional
	public DicRelation saveAndModify(DicRelation arg0) {
		
		return dicRelationDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<DicRelation>> getDicHospitalTypePage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicRelation> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicRelation.class);
		Page<DicRelation> accountPage = dicRelationDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicRelation.class, accountPage);
	}
}
