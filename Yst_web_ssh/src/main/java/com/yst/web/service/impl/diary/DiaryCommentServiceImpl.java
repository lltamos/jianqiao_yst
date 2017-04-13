package com.yst.web.service.impl.diary;

import java.text.SimpleDateFormat;
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
import com.yst.web.dao.diary.DiaryDao;
import com.yst.web.dao.diaryAttachment.DiaryAttachmentDao;
import com.yst.web.dao.diaryBook.DiaryBookDao;
import com.yst.web.dao.diaryBookPraisetrad.DiaryBookPraisetradDao;
import com.yst.web.dao.diaryComment.DiaryCommentDao;
import com.yst.web.dao.doctor.DoctorDao;
import com.yst.web.dao.satisfactionPraisetread.SatisfactionPraisetreadDao;
import com.yst.web.entity.dairycomment.DiaryComment;
import com.yst.web.entity.diary.Diary;
import com.yst.web.entity.diaryattachment.DiaryAttachment;
import com.yst.web.entity.diarybook.DiaryBook;
import com.yst.web.entity.diarybookpraisetread.DiaryBookPraisetread;
import com.yst.web.entity.satisfactionpraisetread.SatisfactionPraiseTread;
import com.yst.web.model.Customer;
import com.yst.web.model.CustomerAddress;
import com.yst.web.model.Doctor;
import com.yst.web.service.diary.DiaryCommentService;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.Customers;

/**
 * 
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:05:01
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryCommentServiceImpl implements DiaryCommentService{

	private static Logger logger = LoggerFactory.getLogger(DiaryCommentServiceImpl.class);
	
	@Autowired
	private DiaryDao diaryDao;
	@Autowired
	private DiaryCommentDao diaryCommentDao;
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
	private DiaryAttachmentDao diaryAttachmentDao;
	@Autowired
	private DiaryBookPraisetradDao diaryBookPraisetradDao;
	@Autowired
	private DoctorDao doctorDao;
	
	
	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryComment get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public DiaryComment saveAndModify(DiaryComment arg0) {
		return null;
	}

	@Override
	public Result getDiaryCommentParticulars(Integer dairyId,Integer customerId,Integer page,Integer rows) {
		if(dairyId == null){
			return ResultUtils.returnError("日记id不能为空");
		}
		Map<String,Object> map = new HashMap<String,Object>();
		Diary diary = diaryDao.findOne(new Long(dairyId));
		if(CommUtils.isNull(diary)){
			return ResultUtils.returnError("日记不存在");
		}else{
			Map<String, Object> mapAttachment = new HashMap<String, Object>();
			mapAttachment.put("EQ_diary.id", dairyId);
			Map<String, SearchFilter> filterAttachment = SearchFilter.parse(mapAttachment);//
			Specification<DiaryAttachment> attachmentSpec = DynamicSpecifications.bySearchFilter(filterAttachment.values(),DiaryAttachment.class);
			List<DiaryAttachment> attachmentList = diaryAttachmentDao.findAll(attachmentSpec,new Sort(Direction.ASC, new String[] { "createdTime" }));
			List<Object> lists = new ArrayList<Object>();
			for(DiaryAttachment diaryAttachment : attachmentList){
					String address = diaryAttachment.getAddress();
					lists.add(address);
				}
			String[] imgAddress = new String [lists.size()];
			for (int i = 0; i < lists.size(); i++) {
				imgAddress[i] = (String) lists.get(i);
			}
			diary.setAttachmentAddress(imgAddress);
		}
		Customer customerDiary = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customerDiary)){
			return ResultUtils.returnError("用户不存在");
		}else{
			CustomerAddress diaryCustomerAddress = customerAddressDao.findByColumnValue(CustomerAddress.class, "id", customerDiary.getAddress_id());
			if(CommUtils.isNull(diaryCustomerAddress)){
				diary.setCustomerAddress(null);
			}else{
				diary.setCustomerAddress(diaryCustomerAddress.getAddress());
			}
			diary.setCustomerName(customerDiary.getName());
			diary.setCustomerlogoimg(customerDiary.getImage());
		}
		String hql = "from Doctor where customer_id="+customerId;
		Doctor doctor = (Doctor) doctorDao.queryForObject(hql);
		if(CommUtils.isNull(doctor)){
			map.put("hospital", "");
		}else{
			if("".equals(doctor.getHospital())){
				map.put("hospital", "");
			}else{
				map.put("hospital", doctor.getHospital());
			}
		}
		map.put("diary", diary);
		Map<String,Object> param2 = new HashMap<String,Object>();
		Map<String,Object> book = new HashMap<String,Object>();
		Integer bookId = new Long(diary.getDiaryBook().getId()).intValue();
		DiaryBook diaryBook = diaryBookDao.findOne(new Long(bookId));
		book.put("diaryBookName", diaryBook.getDiaryBookName());
		book.put("diaryBookNum", diaryBook.getDairyNum());
		book.put("diaryBookComment", diaryBook.getCommenterNum());
		param2.put("EQ_dairyBook.id", bookId);
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
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("EQ_diary.id", dairyId);
		Map<String,SearchFilter> filter = SearchFilter.parse(param);
		Specification<DiaryComment> specification = DynamicSpecifications.bySearchFilter(filter.values(), DiaryComment.class);
		List<DiaryComment> commentList = diaryCommentDao.findAll(specification);
		if(commentList.size() == 0){
			return ResultUtils.returnSuccess("无评论", map);
		}else{
			if(page == 0){
				page = 0;
			}else{
				double p = Math.ceil((double)commentList.size()/(double)rows);
				Integer pages = (int) p;
				if(page > pages-1){
					page=pages-1;
				}
			}
			List<DiaryComment> diaryCommentList = null;
			Map<String,Object> mapPage = new HashMap<String,Object>();
			Page<DiaryComment> diaryCommentPages = diaryCommentDao.findAll(specification, new PageRequest(page, rows,new Sort(Direction.DESC, new String[] { "updateTime" })));
			diaryCommentList = diaryCommentPages.getContent();
			mapPage.put("Number",diaryCommentPages.getNumber());
			mapPage.put("NumberOfElements",diaryCommentPages.getNumberOfElements());
			mapPage.put("Size",diaryCommentPages.getSize());
			map.put("Sort",diaryCommentPages.getSort());
			mapPage.put("TotalElements",diaryCommentPages.getTotalElements());
			mapPage.put("TotalPages",diaryCommentPages.getTotalPages());
			List<Object> list = new ArrayList<Object>();
			for(int i = 0; i < diaryCommentList.size(); i++){
				Customers comment = new Customers();
				for (int j = 0; j <= i; j++) {
					DiaryComment diaryComment = diaryCommentList.get(i);
					Customer custome = customerDao.findByColumnValue(Customer.class, "customer_id", diaryComment.getCustomer().getCustomer_id());
					CustomerAddress customerAddress = customerAddressDao.findByColumnValue(CustomerAddress.class, "id", custome.getAddress_id());
					comment.setName(custome.getName());
					if(CommUtils.isNull(customerAddress)){
						comment.setAddress(null);
					}else{
						comment.setAddress(customerAddress.getAddress());
					}
					SimpleDateFormat fr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					comment.setCreateTime(fr.format(diaryComment.getCreatedTime()));
					comment.setContent(diaryComment.getContent());
					comment.setImgAddress(custome.getImage());
				}
				list.add(comment);
			}
			map.put("diaryComment", list);
			map.put("page", mapPage);
			map.put("diaryBook", book);
		}
		return ResultUtils.returnSuccess("获取成功",map);
	}


	@Override
	@Transactional(readOnly = false)
	public Result saveDiaryComment(Integer dairyId, String content, Integer customerId) {
		if(dairyId == null){
			return ResultUtils.returnError("日记id不能为空");
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(content)){
			return ResultUtils.returnError("评价内容不能为空");
		}
		Customer customer = customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("所属用户不存在");
		}
		Diary diary = diaryDao.findOne(new Long(dairyId));
		if(diary.getCommenterNum() == null){
			diary.setCommenterNum(0);
		}
		diary.setCommenterNum(diary.getCommenterNum()+1);
		DiaryBook diaryBook = diaryBookDao.findOne(diary.getDiaryBook().getId());
		if(diaryBook.getCommenterNum() == null){
			diaryBook.setCommenterNum(0);
		}
		diaryBook.setCommenterNum(diaryBook.getCommenterNum()+1);
		diaryBookDao.save(diaryBook);
		DiaryComment dc = new DiaryComment();
		dc.setContent(content);
		dc.setCustomer(customer);
		dc.setDiary(diary);
		diaryCommentDao.save(dc);
		return ResultUtils.returnSuccess("添加成功");
	}

}
