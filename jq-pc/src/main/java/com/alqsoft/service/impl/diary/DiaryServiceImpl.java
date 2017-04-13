package com.alqsoft.service.impl.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.customeraddress.CustomerAddressDao;
import com.alqsoft.dao.diary.DiaryDao;
import com.alqsoft.dao.diaryattachment.DiaryAttachmentDao;
import com.alqsoft.dao.diarybook.DiaryBookDao;
import com.alqsoft.dao.diaryfavour.DiaryFavourDao;
import com.alqsoft.dao.dicsrtvicetype.DicServiceTypeDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.CustomerAddress;
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diaryattachment.DiaryAttachment;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.diarycomment.DiaryComment;
import com.alqsoft.rpc.RpcAttachmentService;
import com.alqsoft.rpc.diary.RpcDiaryService;
import com.alqsoft.service.diary.DiaryService;
import com.alqsoft.utils.CommUtils;


/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:21:08
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryServiceImpl implements DiaryService {

	@Autowired
	private DiaryDao diaryDao;
	
	@Autowired
	private DiaryBookDao diaryBookDao;
	
	@Autowired
	private DiaryAttachmentDao diaryAttachmentDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CustomerAddressDao customerAddressDao;
	
	@Autowired
	private DiaryFavourDao diaryFavourDao;
	
	@Autowired
	private RpcDiaryService rpcDiaryService;
	
	@Autowired
	private RpcAttachmentService rpcAttachmentService;
	
	@Autowired
	private DicServiceTypeDao dicServiceTypeDao;
	
	/**
	 * pc首页健康分享
	 */
	@Override
	public List<Diary> getDiaryListByPc(Integer page, Integer rows) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("page", page);
		map.put("rows", rows);
		List<Diary> diaryList = diaryDao.getHomeDiaryList(map);
		
		if(diaryList.size() == 0){
			return diaryList;
		}else{
			for (Diary diary : diaryList) {
				long id = diary.getId();
				Long diaryBookId = diary.getDiaryBookId();
				DiaryBook diaryBook = this.diaryBookDao.findOne(diaryBookId);
				diary.setDiaryBookName(diaryBook.getDiaryBookName());
				diary.setSatisfaction(diaryBook.getSatisfaction());
				long customerIds = diary.getCustomerId();
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findDairyImgAllById(id);
				if(attachmentList.size() > 0){
					List<Object> list = new ArrayList<Object>();
					DiaryAttachment diaryAttachment = attachmentList.get(0);
					String img = diaryAttachment.getAddress();
					list.add(img);
					String[] imgAddress = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						imgAddress[i] = (String) list.get(i);
					}
					diary.setAttachmentAddress(imgAddress);
				}
				//设置用户信息
				Customer dbCustomer = customerDao.getCustomerById(Long.valueOf(customerIds));
				if(!CommUtils.isNull(dbCustomer)){
					diary.setCustomerName(dbCustomer.getNickName());
					diary.setCustomerlogoimg(dbCustomer.getImage());
				/*if(dbCustomer.getAddressId() != null){
					Long aid = dbCustomer.getAddressId();
					CustomerAddress customerAddress = customerAddressDao.findByColumnValue(aid);
						diary.setCustomerAddress(customerAddress.getAddress());
					}*/
				}
			}
			return diaryList;
		}
	}

	@Override
	public List<Diary> getDiaryListByPcAll(Integer page, Integer rows) {
		return null;
	}

	@Override
	public Diary getDiaryDateils(Long diaryid) {
		Diary diary = diaryDao.getDiaryDateils(diaryid);
		long id = diary.getId();
		List<DiaryAttachment> attachmentList = diaryAttachmentDao.findDairyImgAllById(id);
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
		return diary;
	}

	@Override
	public int getDiaryListByPcCount(Integer page, Integer rows) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("page", page);
		map.put("rows", rows);
		return diaryDao.getDiaryListByPcCount(map);
	}

	@Override
	public Integer getDiaryFavourCountByDairyId(Long diaryid) {
		return diaryFavourDao.getDiaryFavourCountByDairyId(diaryid);
	}

	@Override
	public DiaryBook getDiaryBookById(Long diarybookid) {
		return diaryBookDao.findOne(diarybookid);
	}

	@Override
	public Result saveDiaryComment(Customer customer, Long diaryid, String content, Integer niming) {
		Result result = new Result();
		if(content == null ||  "".equals(content)){
			result.setMsg("评论内容不能为空");
			result.setCode(1);
			return result;
		}
		if(diaryid == null){
			result.setMsg("日记id为空");
			result.setCode(1);
			return result;
		}
		Diary diary = diaryDao.getDiaryDateils(diaryid);
		if(diary == null){
			result.setMsg("评论日记不存在");
			result.setCode(1);
			return result;
		}
		content = StringFilter(content);
		DiaryComment dc = new DiaryComment();
		if(customer != null){
			dc.setCustomerId(customer.getId());
			dc.setCustomerName(customer.getName());
		}else{
			dc.setAnonymous(niming);
			dc.setCustomerName("匿名");
		}
		dc.setContent(content);
		result = rpcDiaryService.saveDiaryComment(dc,diaryid,customer);
		return result;
	}

	// 过滤特殊字符 
	public String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？_]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	@Override
	public Result saveUpdateDiary(Long diarybookid, String content, String aids, long customerId) {
		Result result = new Result();
		System.out.println("第二步");
		if(content == null ||  "".equals(content)){
			result.setMsg("评论内容不能为空");
			result.setCode(1);
			return result;
		}
		content = StringFilter(content);
		DiaryBook diaryBook = diaryBookDao.findOne(diarybookid);
		if(diaryBook == null){
			result.setMsg("当前日记本不存在");
			result.setCode(1);
			return result;
		}
		Customer customer = customerDao.getCustomerById(customerId);
		if(customer == null){
			result.setMsg("用户不存在");
			result.setCode(1);
			return result;
		}
		System.out.println("第三步");
		result = rpcDiaryService.saveDiaryContent(diaryBook, content, customer);
		String[] attachents = aids.split(",");
		if(aids == null || aids == ""){
			result.setMsg("日记添加成功");
		} else {
			Diary diary = (Diary) result.getContent();
			Long diaryId = diary.getId();
			for (String string : attachents) {
				System.out.println("第四步");
				DiaryAttachment attachment = diaryAttachmentDao.getAttachmentById(Long.parseLong(string));
				if(attachment == null){
					result.setCode(0);
					result.setMsg("日记保存成功");
				}
					System.out.println("第五步");
					result = rpcAttachmentService.saveDiaryAttachment(attachment, diaryId);
					if(result.getCode() == 1){
						result.setCode(0);
						result.setMsg("日记保存成功,图片保存失败");
					}else{
						result.setCode(0);
						result.setMsg("日记保存成功");
					}
				} 
			}
		return result;
	}

	@Override
	public List<Diary> toDiaryListByBookId(int page, Integer rows, Long diarybookid) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("page", page);
		map.put("rows", rows);
		map.put("bookId", diarybookid);
		List<Diary> diaryList = diaryDao.getDiaryListByBookId(map);
		
		if(diaryList.size() == 0){
			return diaryList;
		}else{
			for (Diary diary : diaryList) {
				long id = diary.getId();
				Long diaryBookId = diary.getDiaryBookId();
				DiaryBook diaryBook = this.diaryBookDao.findOne(diaryBookId);
				diary.setDiaryBookName(diaryBook.getDiaryBookName());
				diary.setSatisfaction(diaryBook.getSatisfaction());
				long customerIds = diary.getCustomerId();
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findDairyImgAllById(id);
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
				//设置用户信息
				Customer dbCustomer = customerDao.getCustomerById(Long.valueOf(customerIds));
				if(!CommUtils.isNull(dbCustomer)){
					diary.setCustomerName(dbCustomer.getNickName());
					diary.setCustomerlogoimg(dbCustomer.getImage());
				}
			}
			return diaryList;
		}
	}

	@Override
	public int toDiaryListByBookIdCount(Long diarybookids) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("diarybookids", diarybookids);
		return diaryDao.getDiaryListByBookIdCount(map);
	}

	@Override
	public List<Diary> getDiaryListByList(int page, Integer rows) {
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("page", page);
		map.put("rows", rows);
		List<Diary> diaryList = diaryDao.getHomeDiaryList(map);
		
		if(diaryList.size() == 0){
			return diaryList;
		}else{
			for (Diary diary : diaryList) {
				long id = diary.getId();
				Long diaryBookId = diary.getDiaryBookId();
				DiaryBook diaryBook = this.diaryBookDao.findOne(diaryBookId);
				diary.setDiaryBookName(diaryBook.getDiaryBookName());
				diary.setSatisfaction(diaryBook.getSatisfaction());
				long customerIds = diary.getCustomerId();
				List<DiaryAttachment> attachmentList = diaryAttachmentDao.findDairyImgAllById(id);
				List<Object> list = new ArrayList<Object>();
				if(attachmentList.size() > 0){
					DiaryAttachment diaryAttachment = attachmentList.get(0);
					String img = diaryAttachment.getAddress();
					list.add(img);
					String[] imgAddress = new String[list.size()];
					for (int i = 0; i < list.size(); i++) {
						imgAddress[0] = (String) list.get(0);
					}
					diary.setAttachmentAddress(imgAddress);
				}
				//设置用户信息
				Customer dbCustomer = customerDao.getCustomerById(Long.valueOf(customerIds));
				if(!CommUtils.isNull(dbCustomer)){
					diary.setCustomerName(dbCustomer.getNickName());
					diary.setCustomerlogoimg(dbCustomer.getImage());
				/*if(dbCustomer.getAddressId() != null){
					Long aid = dbCustomer.getAddressId();
					CustomerAddress customerAddress = customerAddressDao.findByColumnValue(aid);
						diary.setCustomerAddress(customerAddress.getAddress());
					}*/
				}
			}
			return diaryList;
		}
	}

	@Override
	public DicServiceType getDicServiceTypeById(Long productTypeId) {
		return dicServiceTypeDao.getServiceTypeInfo(productTypeId);
	}

	@Override
	public Result saveDiaryFavourCount(Long diaryId, Long customerId) {
		Result result = new Result();
		if(diaryId == null){
			result.setCode(1);
			return result;
		}
		if(customerId == null){
			result.setCode(1);
			return result;
		}
		result = rpcDiaryService.saveDiaryFavourCount(diaryId, customerId);
		return result;
	}

	@Override
	public Result saveDiaryBrowNum(Long id) {
		return rpcDiaryService.saveDiaryBrowNum(id);
	} 
}
