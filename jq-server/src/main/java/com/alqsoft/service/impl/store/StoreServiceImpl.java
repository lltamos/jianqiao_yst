package com.alqsoft.service.impl.store;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang.StringUtils;
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

import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.store.StoreDao;
import com.alqsoft.entity.DicHospitalType;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.Store;
import com.alqsoft.service.dic.DicHospitalTypeService;
import com.alqsoft.service.store.StoreService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

/**
 * 分院管理实体
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午10:53:50
 * 
 */
@Service
@Transactional(readOnly = true)
public class StoreServiceImpl implements StoreService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(StoreServiceImpl.class);

	@Autowired
	private StoreDao storeDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private DicHospitalTypeService dicHospitalService;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try{
			storeDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Store get(Long arg0) {
		return storeDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Store saveAndModify(Store arg0) {
		return storeDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<Store>> getStoreList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<Store> specification = DynamicSpecifications.bySearchFilter(filter.values(),Store.class);
		Page<Store> accountPage = storeDao.findAll(specification, new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Store.class, accountPage);
	}

	@Override
	@Transactional
	public Result save(Store store) {
		Long id = store.getId();
		Integer merchantId = store.getMerchantId();
		String name = store.getName();
		String des = store.getDes();
		String storePhone = store.getStorePhone();
		String province = store.getProvince();
		String city = store.getCity();
		String address = store.getStorePhone();
		String lat = store.getLatitude();
		String lng = store.getLongitude();
		Long hospitalTypeId = store.getHospitalTypeId();
		
		if (merchantId==null) {
			return SpringMVCUtils.returnError("所属总院不能为空!");
		}
		Merchant dbMerchant=merchantDao.findOne(merchantId.longValue());
		if(dbMerchant==null){
			return SpringMVCUtils.returnError("所属总院不存在!");
		}
		String merchantName=dbMerchant.getName();
		if (StringUtils.isBlank(name)) {
			return SpringMVCUtils.returnError("分院名称不能为空!");
		}
		if (StringUtils.isBlank(hospitalTypeId.toString())) {
			return SpringMVCUtils.returnError("分院等级不能为空!");
		}
		DicHospitalType dicHospitalType = dicHospitalService.get(hospitalTypeId);
		if(dicHospitalType==null){
			return SpringMVCUtils.returnError("分院等级不存在!");
		}
		String hospitalTypeName = dicHospitalType.getName();
		if (StringUtils.isBlank(des)) {
			return SpringMVCUtils.returnError("分院简介不能为空!");
		}
		if (StringUtils.isBlank(storePhone)) {
			return SpringMVCUtils.returnError("分院手机号不能为空!");
		}
		if (StringUtils.isBlank(province)) {
			return SpringMVCUtils.returnError("省份不能为空!");
		}
		if (StringUtils.isBlank(city)) {
			return SpringMVCUtils.returnError("市级不能为空!");
		}
		if (StringUtils.isBlank(address)) {
			return SpringMVCUtils.returnError("详细地址不能为空!");
		}
		if (StringUtils.isBlank(lat)) {
			return SpringMVCUtils.returnError("经度不能为空!");
		}
		if (StringUtils.isBlank(lng)) {
			return SpringMVCUtils.returnError("纬度不能为空!");
		}
		if(id==null){//添加
			store.setMerchantName(merchantName);
			store.setDeleted(0);
			store.setHospitalTypeName(hospitalTypeName);
			this.storeDao.save(store);
		}else{//修改
			Store dbStore=this.storeDao.findOne(id);
			if(dbStore==null){
				return SpringMVCUtils.returnError("分院不存在!");
			}
			dbStore.setMerchantId(merchantId);
			dbStore.setMerchantName(merchantName);
			dbStore.setHospitalTypeId(hospitalTypeId);
			dbStore.setHospitalTypeName(hospitalTypeName);
			dbStore.setName(name);
			dbStore.setDes(des);
			dbStore.setStorePhone(storePhone);
			dbStore.setProvince(province);
			dbStore.setCity(city);
			dbStore.setAddress(address);
			dbStore.setLatitude(lat);
			dbStore.setLongitude(lng);
			this.storeDao.save(dbStore);
		}
		return SpringMVCUtils.returnSuccess("保存成功！");
	}

	@Override
	@Transactional
	public Result deleted(Store store) {
		Long id = store.getId();
		Integer deleted = store.getDeleted();
		if (id==null) {
			return SpringMVCUtils.returnError("分院ID不能为空!");
		}
		if(deleted==null){
			return SpringMVCUtils.returnError("标志不能为空!");
		}
		Store dbStore = storeDao.findOne(id);
		if(dbStore==null){
			return SpringMVCUtils.returnError("分院不存在!");
		}
		dbStore.setDeleted(deleted);
		storeDao.save(dbStore);
		return SpringMVCUtils.returnSuccess("保存成功！");
	}

	@Override
	public Result storeCheck(Integer merchantId, String name) {
		if(merchantId==null){
			return SpringMVCUtils.returnError("总院id不能为空");
		}
		if(name==null){
			return SpringMVCUtils.returnError("分院名称不能为空");
		}
		Store store = storeDao.storeCheck(merchantId, name);
		if(store==null){
			return SpringMVCUtils.returnSuccess("当前分院唯一");
		}
		return SpringMVCUtils.returnError("不可添加重复分院");
	}
}
