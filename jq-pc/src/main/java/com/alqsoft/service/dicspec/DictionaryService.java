package com.alqsoft.service.dicspec;

import java.util.List;
import java.util.Map;

public interface DictionaryService {

	/**
	 * 查询所有专长字典
	 * @return
	 */
	public List<Map<String,Object>> findDicSpecAll();
	
	/**
	 * 查询所有科室
	 * @return
	 */
	public List<Map<String,Object>> finddicOfficeAll();
	
	/**
	 * 查询所有职称
	 * @return
	 */
	public List<Map<String,Object>> findDicTitleAll();
}
