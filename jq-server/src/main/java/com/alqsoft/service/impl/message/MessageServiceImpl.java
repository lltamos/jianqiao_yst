package com.alqsoft.service.impl.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.alqframework.webmvc.springmvc.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.message.MessageDao;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.message.Message;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.message.MessageService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageDao messageDao;
	@Autowired
	private AttachmentService attachmentService;
	
	@Override
	public boolean delete(Long arg0) {
		try{
			messageDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Message get(Long arg0) {
		return messageDao.findOne(arg0);
	}

	@Override
	public Message saveAndModify(Message arg0) {
		return messageDao.save(arg0);
	}

	@Override
	public BootStrapResult<List<Message>> getMessageList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<Message> specification = DynamicSpecifications.bySearchFilter(filter.values(), Message.class);
		Page<Message> accountPage = messageDao.findAll(specification,
				new PageRequest(page, length, new Sort(Direction.ASC, new String[] { "id" })));
		return BootStrapResultUtils.returnPage(Message.class, accountPage);
	}

	@Override
	public Message getOneById(Long id) {
		return messageDao.findOne(id);
	}

	@Override
	@Transactional
	public Result delete(Message message) {
		Result result = new Result();
		Long id = message.getId();
		Message modle = messageDao.findOne(id);
		modle.setStatus(message.getStatus());
		messageDao.save(modle);
		result.setCode(1);
		result.setMsg("操作成功！");
		return result;
	}

	@Override
	public Result save( String aids, Message message) {
		Result result = new Result();
		Attachment attachment = new Attachment();
		if( StringUtils.isNotBlank(aids) ){
			attachment = attachmentService.getOneById(Long.valueOf(aids));
		}
		Long id = message.getId();
		String title = message.getTitle();
		String des = message.getDes();
		String address = message.getAddress();
		Integer status = message.getStatus();
		if( id==null ){
			message.setStatus(0);
			message.setImage(attachment.getAddress());
			messageDao.save(message);
			result.setCode(1);
			result.setMsg("保存成功！");
			return result;
		}else{
			Message one = messageDao.findOne(id);
			one.setTitle(title);
			one.setDes(des);
			if( StringUtils.isNotBlank(aids) ){
				one.setImage(attachment.getAddress());
			}
			one.setAddress(address);
			one.setStatus(status);
			messageDao.save(one);
			result.setCode(1);
			result.setMsg("修改成功！");
			return result;
		}
	}

}
