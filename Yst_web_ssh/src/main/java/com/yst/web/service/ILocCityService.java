package com.yst.web.service;

import java.util.List;

import com.yst.web.model.LocCity;

public interface ILocCityService {

	public static final String DINAME = "ILocCityService";
	public List<LocCity> selectByProvID(Integer prov_id);
	public List<LocCity> selectAll();
}
