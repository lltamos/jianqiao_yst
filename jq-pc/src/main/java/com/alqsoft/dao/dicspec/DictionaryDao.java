package com.alqsoft.dao.dicspec;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 医生专长
 * @author zj
 *
 */
@MyBatisRepository
public interface DictionaryDao {

	public List<Map<String,Object>> findDicSpecAll();
	public List<Map<String,Object>> finddicOfficeAll();
	public List<Map<String,Object>> findDicTitleAll();
	
}
