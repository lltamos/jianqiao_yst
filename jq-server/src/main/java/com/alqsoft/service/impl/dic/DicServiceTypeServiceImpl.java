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

import com.alqsoft.dao.dic.DicServiceTypeDao;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.service.dic.DicServiceTypeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.google.common.collect.Lists;
@Service
@Transactional(readOnly=true)
public class DicServiceTypeServiceImpl implements DicServiceTypeService{
	@SuppressWarnings("unused")
	private static Logger logger=LoggerFactory.getLogger(DicServiceTypeServiceImpl.class);
	@Autowired
	private DicServiceTypeDao dicServiceTypeDao;
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			dicServiceTypeDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	@Override
	public DicServiceType get(Long arg0) {
		
		return dicServiceTypeDao.findOne(arg0);
	}

	@Override
	@Transactional
	public DicServiceType saveAndModify(DicServiceType arg0) {
		
		return dicServiceTypeDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<DicServiceType>> getDicHospitalTypePage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicServiceType> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicServiceType.class);
		Page<DicServiceType> accountPage = dicServiceTypeDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicServiceType.class, accountPage);
	}

	@Override
	public List<DicServiceType> findAll() {
		Iterable<DicServiceType> iterable = dicServiceTypeDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<DicServiceType> dicServiceTypelist = Lists.newArrayList(iterable.iterator());
		return dicServiceTypelist;
	}

	@Override
	public DicServiceType getOne(Long id) {
		return dicServiceTypeDao.findOne(id);
	}
}
