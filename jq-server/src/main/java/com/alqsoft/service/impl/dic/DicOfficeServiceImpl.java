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

import com.alqsoft.dao.dic.DicOfficeDao;
import com.alqsoft.entity.DicOffice;
import com.alqsoft.service.dic.DicOfficeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.google.common.collect.Lists;
@Service
@Transactional
public class DicOfficeServiceImpl implements DicOfficeService{

	@Autowired
	private DicOfficeDao dicOfficeDao;
	@Override
	public boolean delete(Long arg0) {
		dicOfficeDao.delete(arg0);
		return true;
	}

	@Override
	public DicOffice get(Long arg0) {
		return dicOfficeDao.findOne(arg0);
	}

	@Override
	public DicOffice saveAndModify(DicOffice arg0) {
		return dicOfficeDao.save(arg0);
	}

	@Override
	public List<DicOffice> findAll() {
		Iterable<DicOffice> iterable = dicOfficeDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<DicOffice> dicOfficelist = Lists.newArrayList(iterable.iterator());
		return dicOfficelist;
	}

	@Override
	public BootStrapResult<List<DicOffice>> getDicOfficePage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicOffice> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicOffice.class);
		Page<DicOffice> accountPage = dicOfficeDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicOffice.class, accountPage);
	}
	
	

}
