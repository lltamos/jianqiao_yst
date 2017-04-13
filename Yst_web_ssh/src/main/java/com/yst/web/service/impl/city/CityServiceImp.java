package com.yst.web.service.impl.city;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
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

import com.yst.web.dao.city.CityDao;
import com.yst.web.dao.prov.ProvDao;
import com.yst.web.entity.city.City;
import com.yst.web.entity.diary.Diary;
import com.yst.web.entity.prov.Prov;
import com.yst.web.service.city.CityService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年5月5日 上午9:52:24
 * 
 */
@Service
@Transactional(readOnly=true)
public class CityServiceImp implements CityService{

	private static Logger logger = LoggerFactory.getLogger(CityServiceImp.class);
	
	@Autowired
	private CityDao cityDao;
	@Autowired
	private ProvDao provDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public City get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public City saveAndModify(City arg0) {
		return null;
	}

	@Override
	public Result getProv() {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Prov> specification = DynamicSpecifications.bySearchFilter(filter.values(),Prov.class);
		List<Prov> provList = provDao.findAll(specification);
		return ResultUtils.returnSuccess("获取成功",provList);
	}

	@Override
	public Result getCityByProvId(Integer pid) {
		if(pid == null){
			return ResultUtils.returnError("省份id不能为空");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_provId", pid);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<City> specification = DynamicSpecifications.bySearchFilter(filter.values(),City.class);
		List<City> cityList = cityDao.findAll(specification);
		return ResultUtils.returnSuccess("获取成功",cityList);
	}

	@Override
	public Result findCityWhetherOrNotProv(Integer provId, Integer id) {
		if(id == null){
			return ResultUtils.returnError("省份id不能为空");
		}
		if(provId == null){
			return ResultUtils.returnError("市provId不能为空");
		}
		City city = cityDao.findOne(new Long(provId));
		Integer procid = city.getProvId();
		if(id == procid){
			return ResultUtils.returnSuccess("城市与省份匹配");
		}else {
			return ResultUtils.returnError("城市与省份不匹配");
		}
	}

}
