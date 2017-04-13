package com.alqsoft.service.impl.merchant;

import java.util.HashMap;
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

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

/**
 * 总院管理实体
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
public class MerchantServiceImpl implements MerchantService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MerchantServiceImpl.class);

	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private AttachmentService attachmentService;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			merchantDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Merchant get(Long arg0) {
		return merchantDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Merchant saveAndModify(Merchant arg0) {
		return merchantDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<Merchant>> getMerchantList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<Merchant> specification = DynamicSpecifications.bySearchFilter(filter.values(), Merchant.class);
		Page<Merchant> accountPage = merchantDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Merchant.class, accountPage);
	}

	@Override
	@Transactional
	public Result save(String aids,Long id,String name,String customerPhone, String des) {
 		Attachment attachment = new Attachment();
		if( StringUtils.isNotBlank(aids) ){
			attachment = attachmentService.getOneById(Long.valueOf(aids));
		}
		if ( StringUtils.isBlank(name) ) {
			return SpringMVCUtils.returnError("总院名称不能为空!");
		}
		if ( StringUtils.isBlank(des) ) {
			return SpringMVCUtils.returnError("总院简介不能为空!");
		}
		if ( id == null ) {// 新增
			if (StringUtils.isBlank(customerPhone)) {
				return SpringMVCUtils.returnError("所属用户手机号不能为空!");
			}
			Customer customer = this.customerDao.getCustomerByPhone(customerPhone);
			if ( customer == null ) {
				return SpringMVCUtils.returnError("所属用户不存在!");
			}
			Long customerId=customer.getId();
			Integer deleted = customer.getDeleted();
			if ( deleted == null || deleted == 1  ) {
				return SpringMVCUtils.returnError("所属用户已被禁用!");
			}
			Map<String, Object> searchParams = new HashMap<String,Object>();
			searchParams.put("EQ_name", name);
			Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
			Specification<Merchant> specification = DynamicSpecifications.bySearchFilter(filter.values(),
					Merchant.class);
			List<Merchant> reList=merchantDao.findAll(specification);
			if( reList.size() > 0 ){
				return SpringMVCUtils.returnError("总院名称已被注册!");
			}
			searchParams = new HashMap<String,Object>();
			searchParams.put("EQ_customerId", customerId);
			filter = SearchFilter.parse(searchParams);
			specification = DynamicSpecifications.bySearchFilter(filter.values(),
					Merchant.class);
			reList=merchantDao.findAll(specification);
			if( reList.size() > 0 ){
				return SpringMVCUtils.returnError("所属用户已注册总院!");
			}
			Merchant merchant = new Merchant();
			merchant.setName(name);
			merchant.setCustomerPhone(customerPhone);
			merchant.setDes(des);
			merchant.setImageCred1(attachment.getAddress());
			merchant.setDeleted(0);
			merchant.setCustomerName(customer.getName());
			merchant.setCustomerId(customerId);
			customer.setIsMerchant(1L);
			merchantDao.save(merchant);
			customerDao.save(customer);
			return SpringMVCUtils.returnSuccess("保存成功！");
		} else {// 修改
			Merchant dbMerchant = merchantDao.findOne(id);
			if( dbMerchant == null ){
				return SpringMVCUtils.returnError("总院不存在!");
			}
			Map<String, Object> searchParams = new HashMap<String,Object>();
			searchParams.put("EQ_name", name);
			searchParams.put("NQ_id", id);
			Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
			Specification<Merchant> specification = DynamicSpecifications.bySearchFilter(filter.values(),
					Merchant.class);
			List<Merchant> reList=merchantDao.findAll(specification);
			if( reList.size() > 0 ){
				return SpringMVCUtils.returnError("总院名称已被注册!");
			}
			if( StringUtils.isNotBlank(aids) ){
				dbMerchant.setImageCred1(attachment.getAddress());
			}
			dbMerchant.setName(name);
			dbMerchant.setDes(des);
			merchantDao.save(dbMerchant);
			return SpringMVCUtils.returnSuccess("修改成功！");
		}
	}

	@Override
	@Transactional
	public Result deleted(Merchant merchant) {
		Long id = merchant.getId();
		Integer deleted = merchant.getDeleted();
		if (id==null) {
			return SpringMVCUtils.returnError("总院ID不能为空!");
		}
		if(deleted==null){
			return SpringMVCUtils.returnError("标志不能为空!");
		}
		Merchant dbMerchant = merchantDao.findOne(id);
		if(dbMerchant==null){
			return SpringMVCUtils.returnError("总院不存在!");
		}
		dbMerchant.setDeleted(deleted);
		merchantDao.save(dbMerchant);
		return SpringMVCUtils.returnSuccess("保存成功！");
	}

	@Override
	public List<Merchant> findAll() {
		return merchantDao.findAllMerchant();
	}
}
