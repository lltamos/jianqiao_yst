package com.alqsoft.service.impl.dic;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.dic.DicHospitalTypeDao;
import com.alqsoft.entity.DicHospitalType;
import com.alqsoft.service.dic.DicHospitalTypeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.google.common.collect.Lists;
@Service
@Transactional
public class DicHospitalTypeServiceImpl implements DicHospitalTypeService{

	@Autowired
	private DicHospitalTypeDao dicHospitalTypeDao;
	@Override
	public boolean delete(Long arg0) {
		dicHospitalTypeDao.delete(arg0);
		return true;
	}

	@Override
	public DicHospitalType get(Long arg0) {
		return dicHospitalTypeDao.findOne(arg0);
	}

	@Override
	public DicHospitalType saveAndModify(DicHospitalType arg0) {
		return dicHospitalTypeDao.save(arg0);
	}

	@Override
	public List<DicHospitalType> findAll() {
		Iterable<DicHospitalType> iterable = dicHospitalTypeDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<DicHospitalType> dicHospitalTypelist = Lists.newArrayList(iterable.iterator());
		return dicHospitalTypelist;
	}

	@Override
	public BootStrapResult<List<DicHospitalType>> getDicHospitalTypePage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicHospitalType> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicHospitalType.class);
		Page<DicHospitalType> accountPage = dicHospitalTypeDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicHospitalType.class, accountPage);
	}

}
