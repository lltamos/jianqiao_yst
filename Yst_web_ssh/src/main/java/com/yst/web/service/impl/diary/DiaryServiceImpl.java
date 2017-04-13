package com.yst.web.service.impl.diary;

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
import com.yst.web.dao.CustomerAddressDao;
import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.ProductDao;
import com.yst.web.dao.ProductTypeDao;
import com.yst.web.dao.diary.DiaryDao;
import com.yst.web.dao.diaryAttachment.DiaryAttachmentDao;
import com.yst.web.dao.diaryBook.DiaryBookDao;
import com.yst.web.dao.diaryBookPraisetrad.DiaryBookPraisetradDao;
import com.yst.web.dao.satisfactionPraisetread.SatisfactionPraisetreadDao;
import com.yst.web.entity.diary.Diary;
import com.yst.web.entity.diaryattachment.DiaryAttachment;
import com.yst.web.entity.diarybook.DiaryBook;
import com.yst.web.entity.diarybookpraisetread.DiaryBookPraisetread;
import com.yst.web.entity.satisfactionpraisetread.SatisfactionPraiseTread;
import com.yst.web.model.Customer;
import com.yst.web.model.CustomerAddress;
import com.yst.web.model.Product;
import com.yst.web.model.ProductType;
import com.yst.web.service.diary.DiaryService;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.Customers;

/**
 * 
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:10:52
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryServiceImpl implements DiaryService{
	
	private static Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	@Autowired
	private DiaryDao diaryDao;
	@Autowired
	private DiaryAttachmentDao diaryAttachmentDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CustomerAddressDao customerAddressDao;
	@Autowired
	private SatisfactionPraisetreadDao satisfactionPraisetreadDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private DiaryBookDao diaryBookDao;
	@Autowired
	private DiaryBookPraisetradDao diaryBookPraisetradDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Diary get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Diary saveAndModify(Diary arg0) {
		return null;
	}

	
	@Override
	public Result getDiaryList(Integer page, Integer rows, Integer customerId) {
		/*if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}*/
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("EQ_customerId", customerId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Diary> specification = DynamicSpecifications.bySearchFilter(filter.values(),Diary.class);
		Page<Diary> diaryPages = diaryDao.findAll(specification, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		List<Diary> diaryList = diaryPages.getContent();
		if(diaryList.size() == 0){
			return ResultUtils.returnSuccess("无日记信息");
		}else{
			for (Diary diary : diaryList) {
				long id = diary.getId();
				Integer customerIds = diary.getCustomerId();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("EQ_diary.id", id);
				Map<String, SearchFilter> filterAttachment = SearchFilter.parse(map);//
				Specification<DiaryAttachment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DiaryAttachment.class);
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findAll(attachmentSpec,new Sort(Direction.ASC, new String[] { "createdTime" }));
				List<Object> list = new ArrayList<Object>();
				for (DiaryAttachment diaryAttachment : attachmentList) {
					String img = diaryAttachment.getAddress();
					list.add(img);
					}
				String[] imgAddress = new String[1];
				if(list.size()>0){
					imgAddress[0] = (String) list.get(0);
				}
				diary.setAttachmentAddress(imgAddress);
				//设置用户信息
				Customer dbCustomer = customerDao.get(Customer.class, customerIds);
				if(!CommUtils.isNull(dbCustomer)){
					diary.setCustomerName(dbCustomer.getName());
					diary.setCustomerlogoimg(dbCustomer.getImage());
				if(dbCustomer.getAddress_id()!=null){
					CustomerAddress customerAddress = customerAddressDao.findByColumnValue(CustomerAddress.class, "id", dbCustomer.getAddress_id());
						diary.setCustomerAddress(customerAddress.getAddress());
					}
				}
			}
			return ResultUtils.returnSuccess("获取成功", diaryPages);
		}
	}

	@Override
	public Result getDiaryListByDiaryBookId(Integer diaryBookId,Integer page, Integer rows) {
		if(diaryBookId == null){
			return ResultUtils.returnError("日记本编号不能为空");
		}
		DiaryBook diaryBook = diaryBookDao.findOne(new Long(diaryBookId));
		if(CommUtils.isNull(diaryBook)){
			return ResultUtils.returnError("日记本不存在");
		}
		Customer dbCustomer = customerDao.get(Customer.class, diaryBook.getCustomerId());
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> param1 = new HashMap<String,Object>();
		Map<String,Object> param2 = new HashMap<String,Object>();
		//获取日记列表
		param1.put("EQ_diaryBook.id", diaryBookId);
		Map<String,SearchFilter> filter = SearchFilter.parse(param1);
		Specification<Diary> specification1 = DynamicSpecifications.bySearchFilter(filter.values(), Diary.class);
		Page<Diary> diaryPages = diaryDao.findAll(specification1, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		List<Diary> diaryList = diaryPages.getContent();
		//获取踩或赞
		param2.put("EQ_dairyBook.id", diaryBookId);
		Map<String,SearchFilter> filter2 = SearchFilter.parse(param2);
		Specification<DiaryBookPraisetread> specification2 = DynamicSpecifications.bySearchFilter(filter2.values(), DiaryBookPraisetread.class);
		Page<DiaryBookPraisetread> diaryBookPraisetreadPages = diaryBookPraisetradDao.findAll(specification2, new PageRequest(page, rows));
		List<DiaryBookPraisetread> diaryBookPraisetreadList = diaryBookPraisetreadPages.getContent();
		List<Object> listd = new ArrayList<Object>();
		for (DiaryBookPraisetread diaryBookPraisetread : diaryBookPraisetreadList) {
			Long id = diaryBookPraisetread.getSatisfactionPraiseTread().getId();
			SatisfactionPraiseTread sptList = satisfactionPraisetreadDao.findOne(id);
			listd.add(sptList);
		}
		map.put("satisfactionPraiseTread", listd);
		//获取日记附件
		String productTypeName = null;
		if(diaryList.size() != 0){
			for (Diary diary : diaryList) {
				Map<String, Object> mapAttachment = new HashMap<String, Object>();
				mapAttachment.put("EQ_diary.id", diary.getId());
				Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
				Specification<DiaryAttachment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DiaryAttachment.class);
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findAll(attachmentSpec,new Sort(Direction.ASC, new String[] { "createdTime" }));
				List<Object> list = new ArrayList<Object>();
				for (DiaryAttachment diaryAttachment : attachmentList) {
					String img = diaryAttachment.getAddress();
					list.add(img);
					}
				String[] imgAddress = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					imgAddress[i] = (String) list.get(i);
				}
				diary.setAttachmentAddress(imgAddress);
				productTypeName = diary.getProductTypeName();
				}
			map.put("productTypeName", productTypeName);
		}
		//获取用户信息
		Customers customers = new Customers();
		CustomerAddress customerAddress = customerAddressDao.findByColumnValue(CustomerAddress.class, "id", dbCustomer.getAddress_id());
		customers.setName(dbCustomer.getName());
		if(CommUtils.isNull(customerAddress)){
			customers.setAddress(null);
		}else{
			customers.setAddress(customerAddress.getAddress());
		}
		Integer productId = diaryBook.getProductId();
		Product product = productDao.findByColumnValue(Product.class, "product_id", productId);
		if(CommUtils.isNull(product)){
			return ResultUtils.returnError("项目不存在");
		}else{
			String diaryBookProductTypeName = product.getProductType().getName();
			map.put("diaryBookProductTypeName", diaryBookProductTypeName);
		}
		customers.setImgAddress(dbCustomer.getImage());
		Map<String,Object> mapPage = new HashMap<String,Object>();
		mapPage.put("Number",diaryPages.getNumber());
		mapPage.put("NumberOfElements",diaryPages.getNumberOfElements());
		mapPage.put("Size",diaryPages.getSize());
		map.put("Sort",diaryPages.getSort());
		mapPage.put("TotalElements",diaryPages.getTotalElements());
		mapPage.put("TotalPages",diaryPages.getTotalPages());
		map.put("page", mapPage);
		map.put("customer",customers);
		map.put("diary", diaryList);
		if(!CommUtils.isNull(map)){
			return ResultUtils.returnSuccess("获取成功",map);
		}else{
			return null;
		}
	}

	@Override
	@Transactional(readOnly = false)
	public Result saveDiary(
			Integer productId, String content, 
			String imageId,Integer diaryBookId,
			Integer customerId
			) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		if(diaryBookId == null){
			return ResultUtils.returnError("日记本id不能为空");
		}
		if(productId == null){
			return ResultUtils.returnError("项目id不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(content)){
			return ResultUtils.returnError("日记内容不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("所属用户不存在");
		}
		Product droduct = productDao.findByColumnValue(Product.class, "product_id", productId);
		Diary diary = new Diary();
		diary.setProductId(productId);
		diary.setContent(content);
		if(!CommUtils.isNull(droduct)){
			diary.setProductName(droduct.getName());
			if(!CommUtils.isNull(droduct.getProductType())){
				diary.setProductTypeName(droduct.getProductType().getName());
				diary.setProductTypeId(droduct.getProductType().getProduct_type_id());
			}
		}
		//关联用户
		diary.setCustomerId(customer.getCustomer_id());
		//关联日记本
		DiaryBook diaryBook = diaryBookDao.findOne(new Long(diaryBookId));
		if(diaryBook.getDairyNum() == null){
			diaryBook.setDairyNum(0);
		}
		diaryBook.setDairyNum(diaryBook.getDairyNum()+1);
		diary.setDiaryBook(diaryBook);
		Diary dbDiary = diaryDao.save(diary);
		String[] attachents = imageId.split(",");
		//关联日记附件
		if(StringUtils.isEmptyOrWhitespaceOnly(imageId)){
			return ResultUtils.returnSuccess("无图片，添加成功");
		}
		for (String attachentId : attachents) {
			DiaryAttachment dbDiaryAttachment = diaryAttachmentDao.findOne(Long.parseLong(attachentId));
			if(CommUtils.isNull(dbDiaryAttachment)){
				return ResultUtils.returnError("所属日记图片编号"+attachentId+"不存在");
			}
			dbDiaryAttachment.setDiary(dbDiary);
			diaryAttachmentDao.save(dbDiaryAttachment);
		}
		return ResultUtils.returnSuccess("添加成功");
	}

	@Override
	public Result finaAllDiaryByDiaryBookId(Integer diaryId,Integer page,Integer rows) {
		if(diaryId == null){
			return ResultUtils.returnError("日记id不能为空");
		}else{
			Diary diary = diaryDao.findOne(new Long(diaryId));
			Long DiaryBookId = diary.getDiaryBook().getId();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("EQ_diaryBook.id", DiaryBookId);
			Map<String, SearchFilter> filter = SearchFilter.parse(params);
			Specification<Diary> specification = DynamicSpecifications.bySearchFilter(filter.values(),Diary.class);
			Page<Diary> diaryPages = diaryDao.findAll(specification, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
			List<Diary> diaryList = diaryPages.getContent();
			if(diaryList.size() != 0){
				for (Diary diarys : diaryList) {
					Map<String, Object> mapAttachment = new HashMap<String, Object>();
					mapAttachment.put("EQ_diary.id", diarys.getId());
					Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
					Specification<DiaryAttachment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DiaryAttachment.class);
					List<DiaryAttachment> attachmentList = diaryAttachmentDao.findAll(attachmentSpec,new Sort(Direction.ASC, new String[] { "createdTime" }));
					List<Object> list = new ArrayList<Object>();
					for (DiaryAttachment diaryAttachment : attachmentList) {
						String img = diaryAttachment.getAddress();
						list.add(img);
						}
					String[] imgAddress = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						imgAddress[i] = (String) list.get(i);
					}
					diarys.setAttachmentAddress(imgAddress);
					}
			}
			if(!CommUtils.isNull(diaryPages)){
				return ResultUtils.returnSuccess("获取成功",diaryPages);
			}else{
				return null;
			}
		}
	}

	@Override
	public Result finaDiaryByProductTypeId(Integer productTypeId, Integer page, Integer rows) {
		if(productTypeId == null){
			return ResultUtils.returnError("项目分类id不能为空");
		}
		ProductType productType = productTypeDao.findByColumnValue(ProductType.class, "product_type_id", productTypeId);
		if(CommUtils.isNull(productType)){
			return ResultUtils.returnError("所选项目不存在");
		}
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("EQ_productTypeId", productTypeId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<Diary> specification = DynamicSpecifications.bySearchFilter(filter.values(),Diary.class);
		Page<Diary> diaryPages = diaryDao.findAll(specification, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		List<Diary> diaryList = diaryPages.getContent();
		if(diaryList.size() == 0){
			return ResultUtils.returnSuccess("无日记信息");
		}else{
			for (Diary diary : diaryList) {
				long id = diary.getId();
				Integer customerIds = diary.getCustomerId();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("EQ_diary.id", id);
				Map<String, SearchFilter> filterAttachment = SearchFilter.parse(map);//
				Specification<DiaryAttachment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DiaryAttachment.class);
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findAll(attachmentSpec,new Sort(Direction.ASC, new String[] { "createdTime" }));
				List<Object> list = new ArrayList<Object>();
				for (DiaryAttachment diaryAttachment : attachmentList) {
					String img = diaryAttachment.getAddress();
					list.add(img);
					}
				String[] imgAddress = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					imgAddress[i] = (String) list.get(0);
				}
				diary.setAttachmentAddress(imgAddress);
				//设置用户信息
				Customer dbCustomer = customerDao.get(Customer.class, customerIds);
				if(!CommUtils.isNull(dbCustomer)){
					diary.setCustomerName(dbCustomer.getName());
					diary.setCustomerlogoimg(dbCustomer.getImage());
				if(dbCustomer.getAddress_id()!=null){
					CustomerAddress customerAddress = customerAddressDao.findByColumnValue(CustomerAddress.class, "id", dbCustomer.getAddress_id());
						diary.setCustomerAddress(customerAddress.getAddress());
					}
				}
			}
			return ResultUtils.returnSuccess("获取成功", diaryPages);
		}
	}
	

}
