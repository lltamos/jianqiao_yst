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

import com.alqsoft.dao.dic.DicSpecDao;
import com.alqsoft.entity.DicSpec;
import com.alqsoft.service.dic.DicSpecService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.google.common.collect.Lists;
@Service
@Transactional
public class DicSpecServiceImpl implements DicSpecService{

	@Autowired
	private DicSpecDao dicSpecDao;
	@Override
	public boolean delete(Long arg0) {
		dicSpecDao.delete(arg0);
		return true;
	}

	@Override
	public DicSpec get(Long arg0) {
		return dicSpecDao.findOne(arg0);
	}

	@Override
	public DicSpec saveAndModify(DicSpec arg0) {
		return dicSpecDao.save(arg0);
	}

	@Override
	public List<DicSpec> findAll() {
		Iterable<DicSpec> iterable = dicSpecDao.findAll(new Sort(Direction.DESC, new String[] { "createdTime" }));
		List<DicSpec> dicspeclist = Lists.newArrayList(iterable.iterator());
		return dicspeclist;
	}

	@Override
	public BootStrapResult<List<DicSpec>> getDicSpecPage(Integer page, Integer length,
			HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<DicSpec> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				DicSpec.class);
		Page<DicSpec> accountPage = dicSpecDao.findAll(specification,  new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		
		return BootStrapResultUtils.returnPage(DicSpec.class, accountPage);
	}

}
