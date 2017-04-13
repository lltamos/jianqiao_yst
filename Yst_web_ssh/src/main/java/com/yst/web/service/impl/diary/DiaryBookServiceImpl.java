package com.yst.web.service.impl.diary;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.yst.web.dao.ProductDao;
import com.yst.web.dao.ProductTypeDao;
import com.yst.web.dao.diary.DiaryDao;
import com.yst.web.dao.diaryBook.DiaryBookDao;
import com.yst.web.dao.diaryBookPraisetrad.DiaryBookPraisetradDao;
import com.yst.web.dao.satisfactionPraisetread.SatisfactionPraisetreadDao;
import com.yst.web.entity.diary.Diary;
import com.yst.web.entity.diarybook.DiaryBook;
import com.yst.web.entity.diarybookpraisetread.DiaryBookPraisetread;
import com.yst.web.entity.satisfactionpraisetread.SatisfactionPraiseTread;
import com.yst.web.model.Customer;
import com.yst.web.model.Merchant;
import com.yst.web.model.Product;
import com.yst.web.service.diary.DiaryBookService;
import com.yst.web.utils.CommUtils;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:10:17
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryBookServiceImpl implements DiaryBookService{

	private static Logger logger = LoggerFactory.getLogger(DiaryServiceImpl.class);
	
	@Autowired
	private DiaryBookDao diaryBookDao;
	@Autowired
	private DiaryDao diaryDao;
	@Autowired
	private DiaryBookPraisetradDao diaryBookPraisetradDao;
	@Resource(name = "productDao")
	private ProductDao productDao;
	@Resource(name = "productTypeDao")
	private ProductTypeDao productTypeDao;
	@Autowired
	private SatisfactionPraisetreadDao satisfactionPraisetreadDao;
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryBook get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public DiaryBook saveAndModify(DiaryBook arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Result saveDiaryBook(DiaryBook diaryBook,String satisfactionPraiseTreadId) {
		if(CommUtils.isNull(diaryBook)){
			return ResultUtils.returnError("参数不能为空");
		}
		Integer customerId = diaryBook.getCustomerId();
		Integer productId = diaryBook.getProductId();
		Integer merchanId = diaryBook.getMerchantId();
		Date buyDate = diaryBook.getBuyDate();
		String diaryBookName = diaryBook.getDiaryBookName();
		Integer satisfaction = diaryBook.getSatisfaction();
		if(customerId == null){
			return ResultUtils.returnError("用户ID不能为空");
		}
		Customer dbCustomer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(dbCustomer)){
			return ResultUtils.returnError("该用户不存在");
		}
		if(productId == null){
			return ResultUtils.returnError("项目ID不能为空");
		}
		Product dbProduct = customerDao.findByColumnValue(Product.class, "product_id", productId);
		if(CommUtils.isNull(dbProduct)){
			return ResultUtils.returnError("该项目不存在");
		}
		if(merchanId == null){
			return ResultUtils.returnError("机构ID不能为空");
		}
		Merchant dbMerchant = customerDao.findByColumnValue(Merchant.class, "merchant_id", merchanId);
		if(CommUtils.isNull(dbMerchant)){
			return ResultUtils.returnError("该机构不存在");
		}
		if(buyDate == null){
			return ResultUtils.returnError("购买时间不能为空");
		}
		if(satisfaction == null){
			return ResultUtils.returnError("满意程度不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(satisfactionPraiseTreadId)){
			return ResultUtils.returnError("赞和踩不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(diaryBookName)){
			return ResultUtils.returnError("日记本名称不能为空");
		}
		String[] satisfactionPraiseTreadItem = satisfactionPraiseTreadId.split(",");
		
		DiaryBook dbDiaryBook = diaryBookDao.save(diaryBook);
		//关联操作
		for (String spt_id : satisfactionPraiseTreadItem) {
			DiaryBookPraisetread dbp = new DiaryBookPraisetread();
			dbp.setCustomer(dbCustomer);
			SatisfactionPraiseTread dbSatisfactionPraiseTread = satisfactionPraisetreadDao.findOne(Long.parseLong(spt_id));
			if(CommUtils.isNull(dbSatisfactionPraiseTread)){
				return ResultUtils.returnError("satisfactionPraiseTreadId="+spt_id+"的赞和踩编号不存在");
			}
			dbp.setSatisfactionPraiseTread(dbSatisfactionPraiseTread);
			dbp.setDairyBook(dbDiaryBook);
			diaryBookPraisetradDao.save(dbp);
		}
		return ResultUtils.returnSuccess("添加成功");
	}

	@Override
	public Result findDiaryBookByBookId(Integer dairyBookId) {
		if(CommUtils.isNull(dairyBookId)){
			return ResultUtils.returnError("日记id不能为空");
		}
		DiaryBook dbDiaryBook = diaryBookDao.findOne(new Long(dairyBookId));
		if(!CommUtils.isNull(dbDiaryBook)){
			return ResultUtils.returnSuccess("获取日记信息成功",dbDiaryBook);
		}else{
			return ResultUtils.returnError("所属日记不存在");
		}
	}

	@Override
	public Result queryDiaryBookList(Integer customer_id,Integer page,Integer rows) {
		if(customer_id == null){
			return ResultUtils.returnError("用户编号不能为空。");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("EQ_customerId", customer_id);
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<DiaryBook> specification = DynamicSpecifications.bySearchFilter(filter.values(),DiaryBook.class);
		Page<DiaryBook> diaryBookPage = diaryBookDao.findAll(specification, new PageRequest(page,rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
		List<DiaryBook> diaryBookList = diaryBookPage.getContent();
		if(diaryBookList.size() == 0){
			return ResultUtils.returnError("所属用户无日记数据");
		}else{
			for (DiaryBook diaryBook : diaryBookList) {
				Long diary_book_id= diaryBook.getId();
				List<Diary> diaryList  = diaryDao.findToOne(diary_book_id);
				if(diaryList.isEmpty()){
					diaryBook.setContent(null);
				}else{
					Diary diary = diaryList.get(0);
					diaryBook.setContent(diary.getContent());
				}
				Integer productId = diaryBook.getProductId();
				Product dbProduct = productDao.findByColumnValue(Product.class, "product_id", productId);
				diaryBook.setProductName(dbProduct.getName());
				diaryBook.setProductTypeName(dbProduct.getProductType().getName());
			}
			return ResultUtils.returnSuccess("获取成功", diaryBookPage);
		}
	}

	@Override
	public Result selectWriteDiaryBookPageInfo(Integer product_id) {
		if(product_id == null){
			return ResultUtils.returnError("项目编号不能为空");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Product dbProduct = productDao.findByColumnValue(Product.class, "product_id", product_id);
		if(!CommUtils.isNull(dbProduct)){
			map.put("product_name", dbProduct.getName());	//项目名称
			if(!CommUtils.isNull(dbProduct.getProductType())){
				map.put("product_type_name", dbProduct.getProductType().getName());		//项目分类名称
			}
			if(!CommUtils.isNull(dbProduct.getMerchant())){
				map.put("merchant_name", dbProduct.getMerchant().getName());	//机构名称
			}
		}else{
			return ResultUtils.returnError("所属项目不存在");
		}
		Iterable<SatisfactionPraiseTread> sptIterable = satisfactionPraisetreadDao.findAll();
		List<SatisfactionPraiseTread> sptList = (List<SatisfactionPraiseTread>) sptIterable;
		map.put("satisfactionPraiseTread", sptList);
		return ResultUtils.returnSuccess("获取成功", map);
	}

}
