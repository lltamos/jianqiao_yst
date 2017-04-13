package com.yst.web.service.impl.patient;

import java.util.ArrayList;
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

import com.mysql.jdbc.StringUtils;
import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.PatientDao;
import com.yst.web.dao.PatientDiseaseDao;
import com.yst.web.dao.PatientImageDao;
import com.yst.web.dao.ProductTypeDao;
import com.yst.web.dao.city.CityDao;
import com.yst.web.dao.prov.ProvDao;
import com.yst.web.entity.city.City;
import com.yst.web.entity.patient.PatientDisease;
import com.yst.web.entity.patient.PatientImage;
import com.yst.web.entity.prov.Prov;
import com.yst.web.model.Customer;
import com.yst.web.model.ProductType;
import com.yst.web.service.impl.diary.DiaryServiceImpl;
import com.yst.web.service.patient.PatientDiseaseService;
import com.yst.web.utils.CommUtils;

/**
 * 
 * @Description: TODO
 * @author 黄鑫
 * @e-mail zhangzhaocan@yeah.net
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年4月22日 下午6:13:12
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class PatientDiseaseServiceImpl implements PatientDiseaseService{

	private static Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	@Autowired
	private PatientDiseaseDao patientDiseaseDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	@Autowired
	private PatientDao patientDao;
	@Autowired
	private PatientImageDao patientImageDao;
	@Autowired
	private CityDao cityDao;
	@Autowired
	private ProvDao provDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public PatientDisease get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public PatientDisease saveAndModify(PatientDisease arg0) {
		return null;
	}

	@Override
	public Result getPatientDiseaseList(Integer page, Integer rows) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<PatientDisease> specification = DynamicSpecifications.bySearchFilter(filter.values(),PatientDisease.class);
		Page<PatientDisease> patientDiseasePages = patientDiseaseDao.findAll(specification, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		List<PatientDisease> patientDiseaseList = patientDiseasePages.getContent();
		if(patientDiseaseList.size() == 0){
			return ResultUtils.returnError("暂无求医内容");
		}else{
			for (PatientDisease patientDisease : patientDiseaseList) {
				Integer provId = patientDisease.getProvId();
				Prov prov = provDao.findOne(new Long(provId));
				patientDisease.setProvName(prov.getProvname());
				Integer cityId = patientDisease.getCityId();
				City city = cityDao.findOne(new Long(cityId));
				patientDisease.setCityName(city.getCityname());
				Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", patientDisease.getCustomerId());
				String img = customer.getImage();
				patientDisease.setLogoImg(img);
				Long id = patientDisease.getId();
				map.put("EQ_patientDisease.id", id);
				Map<String, SearchFilter> imger = SearchFilter.parse(map);
				Specification<PatientImage> patientImageSpec = DynamicSpecifications.bySearchFilter(imger.values(), PatientImage.class);
				List<PatientImage> patientImageList = patientImageDao.findAll(patientImageSpec,new Sort(Direction.DESC, new String[] { "createdTime" }));
				List<Object> lists = new ArrayList<Object>();
				if(patientImageList.size() !=0){
					for (PatientImage patientImage : patientImageList) {
						lists.add(patientImage.getAddress());
					}
				}
				String[] imgAddress = new String [lists.size()];
				for (int i = 0; i < lists.size(); i++) {
					imgAddress[i] = (String) lists.get(i);
				}
				patientDisease.setImgAddress(imgAddress);
			}
				
		} 
			return ResultUtils.returnSuccess("获取成功",patientDiseasePages);
		}
	

	@Override
	public Result getPatientDisease(Integer patientDiseaseId, Integer customerId) {
		Map<String,Object> map = new HashMap<String,Object>();
		if(patientDiseaseId == null){
			return ResultUtils.returnError("求医id不能为空");
		}
		if(customerId == null){
			map.put("delete", 0);
		}
			PatientDisease patientDisease = patientDiseaseDao.findOne(new Long(patientDiseaseId));
			if(CommUtils.isNull(patientDisease)){
				map.put("delete", 0);
			}else{
				Integer pid = patientDisease.getCustomerId();
				Integer a=null;
				Integer ppid = customerId;
				if(ppid == pid){
					a = 1;
				}else{
					a = 0;
				}
				map.put("delete", a);
			}
		if(CommUtils.isNull(patientDisease)){
			return ResultUtils.returnError("无此内容");
		}else {
			Map<String, Object> mapAttachment = new HashMap<String, Object>();
			mapAttachment.put("EQ_patientDisease.id", patientDiseaseId);
			Map<String, SearchFilter> imger = SearchFilter.parse(mapAttachment);
			Specification<PatientImage> patientImageSpec = DynamicSpecifications.bySearchFilter(imger.values(), PatientImage.class);
			List<PatientImage> patientImageList = patientImageDao.findAll(patientImageSpec,new Sort(Direction.DESC, new String[] { "createdTime" }));
			List<Object> list = new ArrayList<Object>();
			if(patientImageList.size() !=0){
				for (int i = 0; i < patientImageList.size(); i++) {
					PatientImage patientImage = patientImageList.get(i);
					list.add(patientImage.getAddress());
				}
			}
			String[] imgAddress = new String [list.size()];
			for (int i = 0; i < list.size(); i++) {
				imgAddress[i] = (String) list.get(i);
			}
			patientDisease.setImgAddress(imgAddress);
			Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", patientDisease.getCustomerId());
			String img = customer.getImage();
			patientDisease.setLogoImg(img);
			Integer provId = patientDisease.getProvId();
			Prov prov = provDao.findOne(new Long(provId));
			patientDisease.setProvName(prov.getProvname());
			Integer cityId = patientDisease.getCityId();
			City city = cityDao.findOne(new Long(cityId));
			patientDisease.setCityName(city.getCityname());
			map.put("patientDisease", patientDisease);
			if(!CommUtils.isNull(map)){
				return ResultUtils.returnSuccess("获取成功",map);
			}else {
				return ResultUtils.returnError("获取失败，该条求医信息不存在");
			}
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Result deletePatientDisease(Integer patientDiseaseId, Integer customerId) {
		if(patientDiseaseId == null){
			return ResultUtils.returnError("求医信息ID不能为空");
		}
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
			Integer id = patientDiseaseId;
			Map<String, Object> mapAttachment = new HashMap<String, Object>();
			mapAttachment.put("EQ_patientDisease.id", patientDiseaseId);
			Map<String, SearchFilter> imger = SearchFilter.parse(mapAttachment);
			Specification<PatientImage> patientImageSpec = DynamicSpecifications.bySearchFilter(imger.values(), PatientImage.class);
			List<PatientImage> patientImageList = patientImageDao.findAll(patientImageSpec,new Sort(Direction.DESC, new String[] { "createdTime" }));
			for (PatientImage patientImage : patientImageList) {
				patientImageDao.delete(patientImage.getId());
			}
			patientDiseaseDao.delete(new Long(id));
			return ResultUtils.returnSuccess("删除成功");
		
	}

	@Override
	@Transactional(readOnly = false)
	public Result addPatientDisease(PatientDisease patientDisease,String imageId,Integer customerId) {
		if(CommUtils.isNull(patientDisease)){
			return ResultUtils.returnError("参数不能为空");
		}
		String nickName = patientDisease.getNickName();
		Integer age = patientDisease.getAge(); 
		Integer sex = patientDisease.getSex();   
		String patientAddress = patientDisease.getPatientAddress();
		String tell = patientDisease.getTell();   
		String diseaseDesc = patientDisease.getDiseaseDesc();
		Integer productTypeId = patientDisease.getProductTypeId();
		Integer provId = patientDisease.getProvId();
		Integer cityId = patientDisease.getCityId();
		if(StringUtils.isEmptyOrWhitespaceOnly(nickName)){
			return ResultUtils.returnError("昵称不能为空");
		}
		if(age == null){
			return ResultUtils.returnError("年龄不能为空");
		}
		if(sex == null){
			return ResultUtils.returnError("性别不能为空");
		}
		if(provId == null){
			return ResultUtils.returnError("省份id不能为空");
		}
		if(cityId == null){
			return ResultUtils.returnError("城市id不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(patientAddress)){
			return ResultUtils.returnError("地址不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(tell)){
			return ResultUtils.returnError("电话不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(diseaseDesc)){
			return ResultUtils.returnError("病情描述不能为空");
		}
		if(productTypeId == null){
			return ResultUtils.returnError("分类id不能为空");
		}
		ProductType productType = productTypeDao.findByColumnValue(ProductType.class, "product_type_id",productTypeId);
		if(CommUtils.isNull(productType)){
			return ResultUtils.returnError("分类不存在");
		}
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		//关联患者
		PatientDisease dbPatientDisease = patientDiseaseDao.save(patientDisease);
		if(StringUtils.isEmptyOrWhitespaceOnly(imageId)){
			return ResultUtils.returnSuccess("信息发布成功，无图片");
		}
		String[] attachents = imageId.split(",");
		//关联图片
		for(String id : attachents){
			PatientImage patientImage = patientImageDao.findOne(Long.parseLong(id));
			if(CommUtils.isNull(patientImage)){
				return ResultUtils.returnError("所属患者求医图片编号"+id+"不存在");
			}
			patientImage.setPatientDisease(dbPatientDisease);
			patientImageDao.save(patientImage);
		}
		return ResultUtils.returnSuccess("添加成功");
	}

}
