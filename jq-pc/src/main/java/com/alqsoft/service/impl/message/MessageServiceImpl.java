package com.alqsoft.service.impl.message;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.message.MessageDao;
import com.alqsoft.service.message.MessageService;

@Service
@Transactional(readOnly=true)
public class MessageServiceImpl implements MessageService {

	@Autowired
	public MessageDao messageDao;
	
	@Override
	public List<Map<String, Object>> getAll(){
		return messageDao.getAll();
	}

}
