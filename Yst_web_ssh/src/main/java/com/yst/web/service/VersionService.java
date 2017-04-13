package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.Version;

public interface VersionService {
	public Version findById(int id);
	public List<Version> selectAll();
	public void add(Version download);
	public void deleteById(int id);
	public void update(Version download);
	public List<Version> selectAllByPage();
	public AppResult updateInfo(Version version);
	public AppResult getNewApp(Version version);
}
