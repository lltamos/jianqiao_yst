package com.yst.web.service;

import java.util.List;

import com.yst.web.model.Advertise;
import com.yst.web.model.AppResult;

public interface AdvertiseService {
	public Advertise findById(int id);
	public List<Advertise> selectAll();
	public AppResult add(Advertise advertise);
	public void deleteById(int id);
	public void update(Advertise advertise);
	public AppResult getAdvList(Advertise advertise);
	public AppResult getInfo(Advertise advertise);
	public List<Advertise> selectAllByPage();
	public AppResult updateInfo(Advertise advertise);
}
