package com.alqsoft.service.impl.diary;

import java.util.List;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.diary.DiaryBookDao;
import com.alqsoft.dao.productimage.ProductImageDao;
import com.alqsoft.dao.productorder.ProductOrderDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.productimage.ProductImage;
import com.alqsoft.service.diary.DiaryBookService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午2:13:59
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryBookServiceImpl implements DiaryBookService {

	@Autowired
	private DiaryBookDao diaryBookDao;
	
	@Autowired
	private ProductOrderDao productOrderDao;
	
	@Autowired
	private ProductImageDao ProductImageDao;
	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryBook get(Long arg0) {
		return null;
	}

	@Override
	public DiaryBook saveAndModify(DiaryBook arg0) {
		return null;
	}

	@Override
	@Transactional
	public Result saveDiaryBook(Integer scorep, ProductOrder productOrder, String bookName, Customer customer, String fabulousval, String steponval) {
		Result result = new Result();
		Long productId = productOrder.getProductId();
		List<ProductImage> imageByProductId = ProductImageDao.getImageByProductId(productId);
		ProductImage productImage = imageByProductId.get(0);
		DiaryBook db = new DiaryBook();
		db.setCustomerId(customer.getId());
		db.setProductId(productOrder.getProductId());
		db.setMerchantId(productOrder.getMerchantId());
		db.setDiaryBookName(bookName);
		db.setSatisfaction(scorep);
		db.setDoctorId(productOrder.getDoctorId());
		db.setProductOrderId(productOrder.getId());
		db.setFabulousval(fabulousval);
		db.setSteponval(steponval);
		db.setContent(productImage.getAddress());
		productOrder.setBookType(1);
		try {
			productOrderDao.save(productOrder);
			this.diaryBookDao.save(db);
			result.setCode(0);
			result.setMsg("保存成功");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("保存失败");
		}
		return result;
	}

}
