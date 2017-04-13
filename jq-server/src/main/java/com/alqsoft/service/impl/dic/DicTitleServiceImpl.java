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

import com.alqsoft.dao.dic.DicTitleDao;
import com.alqsoft.entity.DicTitle;
import com.alqsoft.service.dic.DicTitleService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.google.common.collect.Lists;
@Service
@Transactional(readOnly=true)
public class DicTitleServiceImpl implements DicTitleService{
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(DicTitleServiceImpl.class);
	@Autowired
	private DicTitleDao dicTitleDao;
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			dicTitleDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public DicTitle get(Long arg0) {
		return dicTitleDao.findOne(arg0);
	}

	@Override
	@Transactional
	public DicTitle saveAndModify(DicTitle arg0) {
		return dicTitleDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<DicTitle>> getDicHospitalTypePage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicTitle> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicTitle.class);
		Page<DicTitle> accountPage = dicTitleDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicTitle.class, accountPage);
	}
	
	@Override
	public List<DicTitle> findAll() {
		Iterable<DicTitle> iterable = dicTitleDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<DicTitle> DicTitlelist = Lists.newArrayList(iterable.iterator());
		return DicTitlelist;
	}
}
