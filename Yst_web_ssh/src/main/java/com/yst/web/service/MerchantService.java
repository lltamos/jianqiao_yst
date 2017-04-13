package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yst.web.model.AppResult;
import com.yst.web.model.Merchant;
import com.yst.web.utils.ResultInfo;

public interface MerchantService {
	public Merchant findById(int id);
	public List<Merchant> selectAll();
	public AppResult add(Merchant merchant);
	public void deleteById(int id);
	public void update(Merchant merchant);
	public AppResult reg(Merchant merchant);
	public List<Merchant> selectByName(Merchant merchant);
	public AppResult getVerify(Merchant merchant);
	public AppResult getInfo(Merchant merchant);
	public AppResult updateInfo(Merchant merchant);
	public List<Merchant> selectAllByPage(Merchant merchant, Map session);
	public void deleteById(Integer merchant_id, Integer deleted);
	public AppResult getAllMerchant();
}
