package com.alqsoft.service.impl.product;

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
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.product.ProductDao;
import com.alqsoft.dao.productdoctor.ProductDoctorDao;
import com.alqsoft.dao.productimage.ProductImageDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.Product;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.productdoctor.ProductDoctor;
import com.alqsoft.entity.productimage.ProductImage;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.dic.DicServiceTypeService;
import com.alqsoft.service.product.ProductService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

/**
 * 服务包管理实体
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
public class ProductServiceImpl implements ProductService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private MerchantDao merchantDao;
	@Autowired
	private DoctorDao doctorDao;
	@Autowired
	private ProductDoctorDao productDoctorDao;
	@Autowired
	private ProductImageDao productImageDao;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private DicServiceTypeService dicServiceTypeService;
	
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			productDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Product get(Long arg0) {
		return productDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Product saveAndModify(Product arg0) {
		return productDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<Product>> getProductList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<Product> specification = DynamicSpecifications.bySearchFilter(filter.values(), Product.class);
		Page<Product> accountPage = productDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(Product.class, accountPage);
	}

	@Override
	@Transactional
	public Result save(Product product, String aids, Long[] doctorId) {
		Attachment attachment = new Attachment();
		if( StringUtils.isNotBlank(aids) ){
			attachment = attachmentService.getOneById(Long.valueOf(aids));
		}
		String name = product.getName();
		String des1 = product.getDes1();
		Long merchantId=product.getMerchantId();
		Long serviceTypeId=product.getServiceTypeId();
		String customerPhone = product.getCustomerPhone();
		Long marketPrice = product.getMarketPrice();
		Long totalPrice=product.getTotalPrice();
		Long depositePrice=product.getDepositePrice();
		Integer divide=product.getDivide();
		Long fee1=product.getFee1();
		Long fee2=product.getFee2();
		Long fee3=product.getFee3();
		Long fee4=product.getFee4();
		Long fee5=product.getFee5();
		String detailHtml = product.getDetailHtml();
		String customerServiceId=product.getCustomerServiceId();
//		String serviceProcess = product.getServiceProcess();
//		String refundProcess = product.getRefundProcess();
//		String special = product.getSpecial();
//		String appointment = product.getAppointment();
		DicServiceType one = dicServiceTypeService.getOne(serviceTypeId);
		if(merchantId==null){
			return SpringMVCUtils.returnError("所属总院不能为空!");
		}
		if(doctorId.length<1){
			return SpringMVCUtils.returnError("项目医生不能为空!");
		}
		if(serviceTypeId==null){
			return SpringMVCUtils.returnError("项目类别不能为空!");
		}
		if (StringUtils.isBlank(name)) {
			return SpringMVCUtils.returnError("项目名称不能为空!");
		}
		if (StringUtils.isBlank(customerPhone)) {
			return SpringMVCUtils.returnError("项目推荐人手机号不能为空!");
		}
		if (StringUtils.isBlank(des1)) {
			return SpringMVCUtils.returnError("项目简介不能为空!");
		}
		if(marketPrice==null){
			return SpringMVCUtils.returnError("市场价不能为空!");
		}
		if(totalPrice==null){
			return SpringMVCUtils.returnError("项目总价不能为空!");
		}
		if(depositePrice==null){
			return SpringMVCUtils.returnError("项目定金不能为空!");
		}
		if(divide==null){
			return SpringMVCUtils.returnError("分期数不能为空!");
		}
		if(fee1==null){
			return SpringMVCUtils.returnError("一期金额不能为空!");
		}
		if(fee2==null){
			return SpringMVCUtils.returnError("二期金额不能为空!");
		}
		if(fee3==null){
			return SpringMVCUtils.returnError("三期金额不能为空!");
		}
		if(fee4==null){
			return SpringMVCUtils.returnError("四期金额不能为空!");
		}
		if(fee5==null){
			return SpringMVCUtils.returnError("五期金额不能为空!");
		}
		if (StringUtils.isBlank(detailHtml)) {
			return SpringMVCUtils.returnError("图文详情不能为空!");
		}
//		if (StringUtils.isBlank(serviceProcess)) {
//			return SpringMVCUtils.returnError("服务流程不能为空!");
//		}
//		if (StringUtils.isBlank(refundProcess)) {
//			return SpringMVCUtils.returnError("退款流程不能为空!");
//		}
//		if (StringUtils.isBlank(special)) {
//			return SpringMVCUtils.returnError("特别提示不能为空!");
//		}
//		if (StringUtils.isBlank(appointment)) {
//			return SpringMVCUtils.returnError("如何预约不能为空!");
//		}
		Merchant dbMerchant=merchantDao.findOne(merchantId);
		if(dbMerchant==null){
			return SpringMVCUtils.returnError("所属总院不存在!");
		}
		String merchantName=dbMerchant.getName();
		Customer dbCustomer=customerDao.getCustomerByPhone(customerPhone);
		if(dbCustomer==null){
			return SpringMVCUtils.returnError("推荐人不存在或手机号错误!");
		}
		String customerName=dbCustomer.getNickName();
		Long customerId = dbCustomer.getId();
		Long id = product.getId();
		Long productId = null;
		if( id == null ){
			product.setProductTypeName(one.getServiceName());
			product.setMerchantName(merchantName);
			product.setCustomerName(customerName);
			product.setCustomerId(customerId);
			product.setDeleted(0);
			product.setSaleCount(0L);
			product.setViewCount(0L);
			Product product2 = productDao.save(product);
			if( product2 != null ){
				dbCustomer.setIsRecommender(1L);
				customerDao.save(dbCustomer);
			}
			productId=product.getId();
		}else{
			Product dbProduct=productDao.findOne(id);
			productId=dbProduct.getId();
			dbProduct.setProductTypeName(one.getServiceName());
			dbProduct.setMerchantName(merchantName);
			dbProduct.setCustomerName(customerName);
			dbProduct.setCustomerId(customerId);
			dbProduct.setName(name);
			dbProduct.setDes1(des1);
			dbProduct.setMerchantId(merchantId);
			dbProduct.setServiceTypeId(serviceTypeId);
			dbProduct.setCustomerPhone(customerPhone);
			dbProduct.setMarketPrice(marketPrice);
			dbProduct.setTotalPrice(totalPrice);
			dbProduct.setDepositePrice(depositePrice);
			dbProduct.setDivide(divide);
			dbProduct.setFee1(fee1);
			dbProduct.setFee2(fee2);
			dbProduct.setFee3(fee3);
			dbProduct.setFee4(fee4);
			dbProduct.setFee5(fee5);
			dbProduct.setDetailHtml(detailHtml);
			dbProduct.setCustomerServiceId(customerServiceId);
//			dbProduct.setServiceProcess(serviceProcess);
//			dbProduct.setRefundProcess(refundProcess);
//			dbProduct.setSpecial(special);
//			dbProduct.setAppointment(appointment);
			productDao.save(dbProduct);
		}
		if(doctorId.length>0){
			this.productDoctorDao.deleteByProductId(productId);
		}
		for (Long long1 : doctorId) {
			Doctors dbDoctor=doctorDao.findOne(long1);
			if(dbDoctor==null){
				return SpringMVCUtils.returnError("编号："+long1+"医生不存在!");
			}
			dbDoctor.setProductTypeId(Integer.valueOf(productId+""));
			this.doctorDao.save(dbDoctor);
			ProductDoctor pd = new ProductDoctor();
			pd.setProductId(productId);
			pd.setDoctorId(long1);
			this.productDoctorDao.save(pd);
		}
		if( StringUtils.isNotBlank(aids) ){
			this.productImageDao.deleteByProductId(productId);
			ProductImage pi = new ProductImage();
			pi.setProductId(productId);
			pi.setAddress(attachment.getAddress());
			this.productImageDao.save(pi);
		}
		return SpringMVCUtils.returnSuccess("保存成功！");
	}

	@Override
	@Transactional
	public Result deleted(Product product) {
		Long id = product.getId();
		Integer deleted = product.getDeleted();
		if (id==null) {
			return SpringMVCUtils.returnError("服务包ID不能为空!");
		}
		if(deleted==null){
			return SpringMVCUtils.returnError("标志不能为空!");
		}
		Product dbProduct = productDao.findOne(id);
		if(dbProduct==null){
			return SpringMVCUtils.returnError("服务包不存在!");
		}
		dbProduct.setDeleted(deleted);
		productDao.save(dbProduct);
		return SpringMVCUtils.returnSuccess("保存成功！");
	}

}
