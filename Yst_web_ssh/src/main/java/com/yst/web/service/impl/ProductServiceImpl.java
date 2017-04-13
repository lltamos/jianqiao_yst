package com.yst.web.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.MerchantDao;
import com.yst.web.dao.ProductDao;
import com.yst.web.dao.StoreDao;
import com.yst.web.entity.order.PatientOrder;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.model.Merchant;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.ProductStat;
import com.yst.web.service.ProductService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.FreemarkerUtil;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.UpLoadUtils;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
	private static Log logger = LogFactory.getLog(ProductServiceImpl.class);
	@Resource(name = "productDao")
	private ProductDao productDao;

	@Resource(name = "storeDao")
	private StoreDao storeDao;

	@Resource(name = "merchantDao")
	private MerchantDao merchantDao;

	@Override
	public Product findById(int id) {
		return this.productDao.get(Product.class, id);
	}

	@Override
	public List<Product> selectAll() {
		return this.productDao.query(Product.class);
	}

	@Override
	public void deleteById(int id) {
		this.productDao.delete(Product.class, id);
	}

	@Override
	public void update(Product product) {
		this.productDao.update(product);
	}

	@Override
	public void add(Product product) {
		this.productDao.save(product);
	}

	// 以下为接口方法
	@Override
	public AppResult reg(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = product.getMerchant_id();
		Integer doctor_id = product.getDoctor_id();
		String recomm_phone =product.getRecomm_phone();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属总院id不能为空");
			return appResult;
		}
		if (doctor_id == null || doctor_id.equals("")) {
			appResult.setError_info("医生id不能为空");
			return appResult;
		}
		Merchant dbMerchant = this.merchantDao.get(Merchant.class,
				merchant_id);
		if (dbMerchant == null) {
			appResult.setError_info("所属总院id错误或未注册");
			return appResult;
		}
		Doctor dbDoctor=this.merchantDao.get(Doctor.class, doctor_id);
		if (dbDoctor == null) {
			appResult.setError_info("医生id错误或未注册");
			return appResult;
		}
		if(recomm_phone!=null && !recomm_phone.equals("")){
			Customer customer = this.merchantDao.findByColumnValue(Customer.class, "phone", recomm_phone);
			if(customer==null){
				appResult.setError_info("推荐人错误或未注册");
				return appResult;
			}
			product.setRecomm_customer(customer);
		}
		product.setMerchant(dbMerchant);
		product.setDoctor(dbDoctor);
		appResult = BeanUtils.uploadImage(product, "product");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		String detail_html = product.getDetail_html();
		String url = "";
		if (detail_html != null && !detail_html.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product", product);
			url = FreemarkerUtil.make("html2.ftl", "UTF-8", map);
			product.setDetail_url(url);
			logger.debug(url);
		}
		ProductStat ps = new ProductStat();
		ps.setProduct(product);
		product.setProductStat(ps);
		this.productDao.saveOrUpdate(product);
		appResult.setError_info("申请成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult getProductList(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = product.getMerchant_id();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属总院id不能为空");
		} else {
			Merchant dbMerchant = this.merchantDao.get(Merchant.class,
					merchant_id);
			if (dbMerchant == null) {
				appResult.setError_info("所属总院id错误或未注册");
			} else {
				String hql = "from Product as o where o.deleted<>? and o.merchant.merchant_id=?";
				PageModel pm = PageModelContext.getPageModel();
				List<Product> list = this.productDao.query(hql, pm,
						ServerParam.DELETE_YES, merchant_id);
				if (list.size() == 0) {
					appResult.setError_info("所属总院没有添加项目");
				} else {
					appResult.setMerchant_id(merchant_id);
					appResult.setPage_model(pm);
					appResult.setData(list);
					appResult.setError_info("获取列表成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult getInfo(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_id = product.getProduct_id();
		if (product_id == null || product_id.equals("")) {
			appResult.setError_info("项目id不能为空");
		} else {
			Product dbProduct = this.productDao.get(Product.class, product_id);
			if (dbProduct == null) {
				appResult.setError_info("项目id错误或未注册");
			} else {
				if (dbProduct.getDeleted() == 1) {
					appResult.setError_info("已删除");
				} else {
					dbProduct.setMerchant_id(dbProduct.getMerchant()
							.getMerchant_id());
					appResult.setData(dbProduct);
					appResult.setError_info("获取成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult updateGetInfo(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_id = product.getProduct_id();
		if (product_id == null || product_id.equals("")) {
			appResult.setError_info("项目id不能为空");
		} else {
			Product dbProduct = this.productDao.get(Product.class, product_id);
			if (dbProduct == null) {
				appResult.setError_info("项目id错误或未注册");
			} else {
				if (dbProduct.getDeleted() == 1) {
					appResult.setError_info("已删除");
				} else {
					ProductStat ps = dbProduct.getProductStat();
					ps.setView_count(ps.getView_count() + 1);
					this.productDao.saveOrUpdate(ps);
					dbProduct.setMerchant_id(dbProduct.getMerchant()
							.getMerchant_id());
					dbProduct.setProduct_type_name(dbProduct.getProductType()
							.getName());
					appResult.setData(dbProduct);
					appResult.setError_info("获取成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult deleteProduct(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_id = product.getProduct_id();
		if (product_id == null || product_id.equals("")) {
			appResult.setError_info("项目id不能为空");
		} else {
			Product dbProduct = this.productDao.get(Product.class, product_id);
			if (dbProduct == null) {
				appResult.setError_info("项目id错误或未注册");
			} else {
				if (dbProduct.getDeleted() == 1) {
					appResult.setError_info("已删除");
				} else {
					dbProduct.setDeleted(1);
					this.productDao.update(dbProduct);
					appResult.setError_info("删除成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AppResult getAllList(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer order_type = product.getOrder_type();
		Double lat1 = product.getLatitude();
		Double long1 = product.getLongitude();
		Integer is_real = product.getIs_real();
		Integer product_type_id = product.getProduct_type_id();
		if (is_real == null || is_real.equals("")) {
			appResult.setError_info("项目is_real为空");
			return appResult;
		}
		PageModel pm = PageModelContext.getPageModel();
		String ids = "";
		if (product_type_id != null && !product_type_id.equals("")) {
			ids = " and p.productType.product_type_id in (" + product_type_id
					+ ")";
		}
		String hql = "select new map(p as product,p.merchant.merchant_id as merchant_id,p.merchant.name as merchant_name,p.productType.name as product_type_name) from Product as p where p.deleted<>? and p.off<>? and p.is_real=?"
				+ ids;
		List resultlist = new ArrayList();
		if (order_type == null)
			order_type = 0;
		if (order_type == 1) {// 最近距离
			if (lat1 == null || long1 == null || lat1.equals("")
					|| long1.equals("")) {
				appResult.setError_info("坐标为空");
				return appResult;
			}
			hql = "from Product as p,Store as s where p.merchant.merchant_id =s.merchant.merchant_id and s.latitude is not null and s.longitude is not null and p.deleted<>? and p.off<>? and p.is_real=?"
					+ ids;
			// hql =
			// "from Store as s where s.store_id in(select o.store_id from Store as o,Product as p where o.merchant.merchant_id=p.merchant.merchant_id and o.latitude is not null and o.longitude is not null group by o.store_id) ";
			hql = getDistanceHql(hql, lat1, long1);
			List<Product> productList = this.productDao.query(hql, pm,
					ServerParam.DELETE_YES, ServerParam.OFF, is_real);
			if (productList.size() == 0) {
				appResult.setError_info("没有找到数据");
				return appResult;
			}
			resultlist = productList;
		} else if (order_type == 2) {// 人气最高
			hql = hql + " order by p.productStat.view_count desc";
			List<Product> productList = this.productDao.query(hql, pm,
					ServerParam.DELETE_YES, ServerParam.OFF, is_real);
			if (productList.size() == 0) {
				appResult.setError_info("没有找到数据");
				return appResult;
			}
			resultlist = productList;
		} else if (order_type == 3) {// 销量最高
			hql = hql + " order by p.productStat.sale_count desc";
			List<Product> productList = this.productDao.query(hql, pm,
					ServerParam.DELETE_YES, ServerParam.OFF, is_real);
			if (productList.size() == 0) {
				appResult.setError_info("没有找到数据");
				return appResult;
			}
			resultlist = productList;
		} else {// 默认
			List storeList = this.productDao.query(hql, pm,
					ServerParam.DELETE_YES, ServerParam.OFF, is_real);
			if (storeList.size() == 0) {
				appResult.setError_info("没有找到数据");
				return appResult;
			}
			List pList = new ArrayList();
			for (Object object2 : storeList) {
				Map productMap = new HashMap();
				Map map = (Map) object2;
				Product p1 = (Product) map.get("product");
				String merchant_id = map.get("merchant_id").toString();
				String merchant_name = map.get("merchant_name").toString();
				String product_type_name = map.get("product_type_name")
						.toString();
				productMap.put("product", p1);
				productMap.put("merchant_id", merchant_id);
				productMap.put("merchant_name", merchant_name);
				productMap.put("product_type_name", product_type_name);
				pList.add(productMap);
			}
			resultlist = pList;
		}
		appResult.setPage_model(pm);
		appResult.setData(resultlist);
		appResult.setError_info("获取列表成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	public String getDistanceHql(String hql, Double lat1, Double long1) {
		// "select new map(((ACOS(SIN(("+lat1+" * 3.1415) / 180 ) *SIN((s.latitude * 3.1415) / 180 ) +COS(("+lat1+" * 3.1415) / 180 ) * COS((s.latitude * 3.1415) / 180 ) *COS(("+long1+" * 3.1415) / 180 - (s.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as juli) "
		// ,(ROUND(6378.138*2*ASIN(SQRT(POW(SIN(("
		// + lat1 + "*PI()/180-s.latitude*PI()/180)/2),2)+COS(" + lat1
		// + "*PI()/180)*COS(s.latitude*PI()/180)*POW(SIN((" + long1
		// + "*PI()/180-s.longitude*PI()/180)/2),2)))*1000)) as juli
		String str = "select  new map(((ACOS(SIN(("
				+ lat1
				+ " * 3.1415) / 180 ) *SIN((s.latitude * 3.1415) / 180 ) +COS(("
				+ lat1
				+ " * 3.1415) / 180 ) * COS((s.latitude * 3.1415) / 180 ) *COS(("
				+ long1
				+ " * 3.1415) / 180 - (s.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) as distance,s.merchant.merchant_id as merchant_id,s.merchant.name as merchant_name,p.productType.name as product_type_name,s as store,p as product)  "
				+ hql
				+ " ORDER BY ((ACOS(SIN(("
				+ lat1
				+ " * 3.1415) / 180 ) *SIN((s.latitude * 3.1415) / 180 ) +COS(("
				+ lat1
				+ " * 3.1415) / 180 ) * COS((s.latitude * 3.1415) / 180 ) *COS(("
				+ long1
				+ " * 3.1415) / 180 - (s.longitude * 3.1415) / 180 ) ) * 6378.140)*1000) asc";
		return str;
	}

	@Override
	public List<Product> selectAllByPage() {
		String hql = "from Product as o";
		return this.productDao.query(hql, PageModelContext.getPageModel(),
				Product.class, null);
	}

	@Override
	public AppResult updateInfo(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer product_id = product.getProduct_id();
		if (product_id == null || product_id.equals("")) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		Product dbProduct = this.productDao.get(Product.class, product_id);
		if (dbProduct != null) {
			appResult = BeanUtils.uploadImage(product, "product");
			if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
				if (appResult.getResult().equals(AppResult.FAILED)) {
					return appResult;
				}
			}
			Doctor dbDoctor=this.productDao.get(Doctor.class, product.getDoctor_id());
			product.setDoctor(dbDoctor);
			BeanUtils.copy(product, dbProduct);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("product", dbProduct);
			String url = FreemarkerUtil.make("html2.ftl", "UTF-8", map);
			Product newProduct = new Product();
			newProduct.setDetail_url(url);
			logger.debug(url);
			BeanUtils.copy(newProduct, dbProduct);
			this.productDao.update(dbProduct);
			appResult.setData(dbProduct);
			appResult.setResult(AppResult.SUCCESS);
			appResult.setError_info("修改成功");
		} else {
			appResult.setError_info("项目id错误或不存在");
		}
		return appResult;
	}

	@Override
	public AppResult uploadFile(Product product) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		logger.debug(product.getUpload());
		String upload = product.getUpload();
		if (upload == null || upload.equals("")) {
			appResult.setError_info("图片不能为空");
			return appResult;
		}
		String upload64 = UpLoadUtils.getImageBase64(upload);
		Map<String, String> imageMap = new HashMap<String, String>();// 初始化imageMap
		imageMap.put("upload", upload64);
		appResult = UpLoadUtils.saveFile(imageMap, "product_detail");
		return appResult;
	}

	@Override
	public void initIndex() {
		this.productDao.index();
	}

	@Override
	public void testSearch(Product product) {
		this.productDao.search(Product.class, PageModelContext.getPageModel());
	}

	@Override
	public AppResult selectProductList(Product product,HttpServletRequest request) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String where_sql = "";
		Integer product_type_id = product.getProduct_type_id();
		if (product_type_id != null && !product_type_id.equals("")) {
			where_sql = " and o.productType.product_type_id=" + product_type_id;
		}
		String hql = "from Product as o where 1=1"+where_sql;
		List<Product> list = this.productDao.query(hql,
				PageModelContext.getPageModel(), Product.class, null);
		PageModel pm = PageModelContext.getPageModel();
		String uri = request.getRequestURI();
		uri=uri.substring(uri.indexOf("product!"));
		appResult.setUri(uri);
		appResult.setPage_model(pm);
		appResult.setData(list);
		appResult.setError_info("获取列表成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public List<Product> findByOtherColumn(String column, Object value) {
		return this.productDao.selectByColumnValue(Product.class, column, value);
	}
}
