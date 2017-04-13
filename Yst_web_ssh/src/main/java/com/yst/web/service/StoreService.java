package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.Merchant;
import com.yst.web.model.Store;

public interface StoreService {
	public Store findById(int id);
	public List<Store> selectAll();
	public void add(Store store);
	public void deleteById(int id);
	public void update(Store store);
	public AppResult reg(Store store);
	public AppResult getStoreList(Store store);
	public AppResult getInfo(Store store);
	public List<Store> selectAllByPage();
	public AppResult updateInfo(Store store);
}
