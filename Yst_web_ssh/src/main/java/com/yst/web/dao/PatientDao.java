package com.yst.web.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.yst.web.entity.patient.Patient;
import com.yst.web.utils.BaseDao;


public interface PatientDao extends BaseDao {
	Map<String,String> get(Long id);

}
