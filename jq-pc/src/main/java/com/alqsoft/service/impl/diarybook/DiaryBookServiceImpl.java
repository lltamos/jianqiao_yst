package com.alqsoft.service.impl.diarybook;

import java.util.List;
import java.util.Map;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.diarybook.DiaryBookDao;
import com.alqsoft.dao.dicsrtvicetype.DicServiceTypeDao;
import com.alqsoft.dao.order.ProductOrderDao;
import com.alqsoft.dao.product.ProductDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.Product;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.rpc.diary.RpcDiaryService;
import com.alqsoft.service.diarybook.DiaryBookService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:18:55
 * 
 */
@Service
@Transactional
public class DiaryBookServiceImpl implements DiaryBookService {

	@Autowired
	private DiaryBookDao diaryBookDao;
	
	@Autowired
	private ProductOrderDao productOrderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private RpcDiaryService rpcDiaryService;
	
	@Autowired
	private DicServiceTypeDao dicServiceTypeDao;

	@Override
	public List<DiaryBook> getBookServicePcList(Map<String, Object> map) {
		
		return diaryBookDao.getBookServicePcList(map);
	}

	@Override
	public int getBookServicePcListCount(Long cid) {
		return diaryBookDao.getBookServicePcListCount(cid);
	}

	@Override
	public List<ProductOrder> getProductorderByCid(Map<String, Object> map) {
		List<ProductOrder> orderslist =  productOrderDao.getProductorderByCid(map);
		for (ProductOrder productOrder : orderslist) {
			Long productId = productOrder.getProductId();
			Product product = productDao.getProductById(productId);
			String address = productDao.getProductImageById(productId);
			Long serviceTypeId = product.getServiceTypeId();
			DicServiceType dicServiceType = dicServiceTypeDao.getServiceTypeInfo(serviceTypeId);
			//ProductType productType = productTypeDao.findProductTypeInfo(serviceTypeId);
			productOrder.setTotalPrice(product.getMarketPrice());
			productOrder.setProductName(product.getName());
			productOrder.setMerchantName(product.getMerchantName());
			productOrder.setPriductOrderTypeName(dicServiceType.getServiceName());
			productOrder.setProductImage(address);
		}
		return orderslist;
	}

	/*@Override
	public int getProductorderByCidCount(long cid) {
		return productOrderDao.getProductorderByCidCount(cid);
	}*/

	@Override
	public Result saveDiaryBook(Integer scorep, Long product, String bookName, long cid, String fabulousval, String steponval) {
		Result result = new Result();
		if(scorep == null){
			result.setCode(0);
			result.setMsg("满意度为空");
			return result;
		}
		if(product == null){
			result.setCode(0);
			result.setMsg("未选择已做个的项目");
			return result;
		}
		ProductOrder productOrder = productOrderDao.getProductOrderByProductOrderId(product);
		if(productOrder == null){
			result.setCode(0);
			result.setMsg("未选择已做个的项目");
			return result;
		}
		if(bookName == null || bookName == ""){
			result.setCode(0);
			result.setMsg("未填写日记本名称");
			return result;
		}
		Customer customer = customerDao.getCustomerById(cid);
		result = rpcDiaryService.saveDiaryBook(scorep, productOrder, bookName, customer, fabulousval, steponval);
		return result;
	}

	@Override
	public DiaryBook getBookById(Long diarybookid) {
		return diaryBookDao.findOne(diarybookid);
	}
	

}
