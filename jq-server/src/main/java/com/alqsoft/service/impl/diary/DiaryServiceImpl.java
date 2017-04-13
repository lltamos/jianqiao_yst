package com.alqsoft.service.impl.diary;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.diary.DiaryDao;
import com.alqsoft.dao.dic.DicServiceTypeDao;
import com.alqsoft.dao.dic.ProductTypeDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.dao.product.ProductDao;
import com.alqsoft.dao.productorder.ProductOrderDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.Product;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.ProductType;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.diary.DiaryService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月22日 下午4:03:03
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryDao diaryDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private ProductOrderDao productOrderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private DicServiceTypeDao dicServiceTypeDao;
	
	@Autowired
	private MerchantDao merchantDao;

	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Diary get(Long arg0) {
		return diaryDao.findOne(arg0);
	}

	@Override
	@Transactional
	public Diary saveAndModify(Diary arg0) {
		return diaryDao.save(arg0);
	}

	@Override
	@Transactional
	public Result saveDiaryContent(DiaryBook diaryBook, String content, Customer customer) {
		Result result = new Result();
		Diary diary = new Diary();
		Long productOrderId = diaryBook.getProductOrderId();
		Long doctorId = diaryBook.getDoctorId();
		Doctors doctors = doctorDao.getDoctorById(doctorId);
		diary.setDoctorName(doctors.getName());
		diary.setDoctorId(doctorId);
		ProductOrder productOrder = productOrderDao.getProductOrderById(productOrderId);
		Merchant merchant = merchantDao.getMerchentById(diaryBook.getMerchantId());
		diary.setMerchantName(merchant.getName());
		diary.setProductName(productOrder.getProductName());
		Long productId = productOrder.getProductId();
		Product product = productDao.getProductById(productId);
		DicServiceType productType = dicServiceTypeDao.findOne(product.getServiceTypeId());
		diary.setProductTypeName(productType.getServiceName());
		diary.setProductTypeId(productType.getId());
		diary.setDiaryBook(diaryBook);
		diary.setDiaryBookName(diaryBook.getDiaryBookName());
		diary.setProductOrderId(productOrderId);
		diary.setCustomerAddress(customer.getAddress());
		diary.setCustomerId(Integer.valueOf(customer.getId()+""));
		diary.setContent(content);
		try {
			Diary diary2 = this.diaryDao.save(diary);
			result.setCode(0);
			result.setMsg("保存成功");
			result.setContent(diary2);
		} catch (Exception e) {
			
			result.setCode(1);
			result.setMsg("保存失败");
		}
		return result;
	}

	@Override
	public Diary getDiaryById(Long diaryId) {
		return diaryDao.findOne(diaryId);
	}

}
