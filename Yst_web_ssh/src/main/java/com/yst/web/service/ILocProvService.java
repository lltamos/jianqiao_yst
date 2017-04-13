package com.yst.web.service;

import java.util.List;

import com.yst.web.model.LocProv;

public interface ILocProvService {
	public static final String DINAME = "ILocProvService";
	public List<LocProv> selectAll();
}
