package com.yst.web.service;

import java.util.List;

import org.alqframework.result.Result;

import com.yst.web.entity.patient.Patient;
import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerRelative;

public interface CustomerRelativeService {
	public List<CustomerRelative> selectAll();
	public AppResult selectByParame(CustomerRelative customerRelative);
	public AppResult addCustomerRelative(CustomerRelative custoerRelative);
	public AppResult deleteRelative(int customer_id,int relative_id);
	public Result savePatientRelative(Patient patient2, Integer customerId);
}
