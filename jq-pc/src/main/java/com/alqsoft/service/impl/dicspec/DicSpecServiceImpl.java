package com.alqsoft.service.impl.dicspec;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.dicspec.DictionaryDao;
import com.alqsoft.service.dicspec.DictionaryService;
@Service
public class DicSpecServiceImpl implements DictionaryService{

	@Autowired
	private DictionaryDao dictionaryDao;
	
	@Override
	public List<Map<String, Object>> findDicSpecAll() {
		return dictionaryDao.findDicSpecAll();
	}

	@Override
	public List<Map<String, Object>> finddicOfficeAll() {
		return dictionaryDao.finddicOfficeAll();
	}

	@Override
	public List<Map<String, Object>> findDicTitleAll() {
		return dictionaryDao.findDicTitleAll();
	}

	
	
}
