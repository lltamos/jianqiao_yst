package com.yst.web.dao;

import java.util.Map;

import com.yst.web.utils.BaseDao;


public interface PatientOrderDao extends BaseDao {
	Map<String,String> get(Long id);
}
