package com.yst.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicHospitalTypeDao;
import com.yst.web.dao.DicOfficeDao;
import com.yst.web.dao.DicRelationDao;
import com.yst.web.dao.DicTitleDao;
import com.yst.web.dao.ProductDao;
import com.yst.web.dao.ProductDaos;
import com.yst.web.dao.ProductTypeDao;
import com.yst.web.dao.doctor.DoctorDao;
import com.yst.web.dao.doctor.DoctorsDao;
import com.yst.web.dao.productType.ProductTypeDaos;
import com.yst.web.entity.doctor.Doctors;
import com.yst.web.model.AppResult;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.ProductType;
import com.yst.web.service.ProductTypeService;
import com.yst.web.utils.PageModelContext;
@Service("productTypeService")
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService{
	
	@Resource(name="productTypeDao")
	private ProductTypeDao productTypeDao;
	@Resource(name="productTypeDaos")
	private ProductTypeDaos productTypeDaos;
	@Resource(name="productDao")
	private ProductDao productDao;
	@Resource(name="productDaos")
	private ProductDaos productDaos;
	@Resource
	private DoctorsDao doctorsDao;
	@Resource
	private DoctorDao doctorDao;
	//数据字典
	@Resource(name = "dicOfficeDao")
	private DicOfficeDao dicOfficeDao;
	@Resource(name = "dicTitleDao")
	private DicTitleDao dicTitleDao;
	@Resource(name = "dicHospitalTypeDao")
	private DicHospitalTypeDao dicHospitalTypeDao;
	@Resource(name = "dicRelationDao")
	private DicRelationDao dicRelationDao;
	
	@Override
	public List<ProductType> queryList() {
		String hql  ="from ProductType as pt ";
		PageModel pm = PageModelContext.getPageModel();
		List<ProductType> productTypeList = this.productTypeDao.query(hql, pm, null);
		return productTypeList;
	}

	@Override
	public ProductType findProductTypeInfo(Integer product_type_id) {
		ProductType productType = this.productTypeDao.findByColumnValue(ProductType.class, "product_type_id", product_type_id);
		return productType;
	}

	@Override
	public AppResult updateProductType(ProductType productType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		productType.setDeleted(0);
		this.productTypeDao.update(productType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public void deleteProductType(Integer product_type_id) {
		this.productTypeDao.delete(ProductType.class, product_type_id);
	}

	@Override
	public AppResult addProductType(ProductType productType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.productTypeDao.save(productType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult getProductTypeList() {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		List<ProductType> productTypesList = this.productTypeDao.query(ProductType.class);
		if(productTypesList.size()>0){
			appResult.setData(productTypesList);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setData("");
			appResult.setError_info("无数据");
		}
		return appResult;
	}

	@Override
	public List<ProductType> selectAll() {
		return this.productTypeDao.query(ProductType.class);
	}

	@Override
	public Result findProductTypeAll(Integer page,Integer rows) {
		Map<String, Object> mapAttachment = new HashMap<String, Object>();
		Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);
		Specification<ProductType> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),ProductType.class);
		Page<ProductType> productTypePages = productTypeDaos.findAll(attachmentSpec, new PageRequest(page,rows));
		List<ProductType> productTypeList = productTypePages.getContent();
		if(productTypeList.isEmpty()){
			return ResultUtils.returnError("无数据");
		}else{
			for (ProductType productType : productTypeList) {
				Integer productTypeId = productType.getProduct_type_id();
				List<Doctors> doctorList = this.doctorsDao.getDoctorByProductTypeId(productTypeId);
				List<Product> productList = this.productDao.query(Product.class);
				String img = null;
				for (Product product : productList) {
					if(product.getProductType().getProduct_type_id() == productTypeId){
						img = product.getImage1();
						productType.setProductId(product.getProduct_id().toString());
					}
					productType.setImg(img);
				}
				Doctors doctors = doctorList.get(0);
				if("".equals(doctors.getHospital())){
					productType.setHospital_name("");
				}else{
					productType.setHospital_name(doctors.getHospital());
				}
				productType.setHuanXinId("yst_100");
			}
		}
	
		return ResultUtils.returnSuccess("获取成功",productTypePages);
	}
	
}
