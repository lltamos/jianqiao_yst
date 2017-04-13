package com.yst.web.service;

import java.util.List;
import java.util.Map;

import com.yst.web.model.AppResult;
import com.yst.web.model.MerchantActivity;
import com.yst.web.model.Product;


public interface ActivityService {

	AppResult getMerchantActivityList(MerchantActivity merchantActivity);
	AppResult getActivityInfo(MerchantActivity merchantActivity);
	List<MerchantActivity> selectAllByPage(Integer merchant_id, Map session);
	AppResult updateInfo(MerchantActivity merchantActivity);
	AppResult add(MerchantActivity merchantActivity, Integer merchant_id);
	List<Product> getProductList(Integer merchant_id);
	void deleteById(Integer merchant_activity_id);
	MerchantActivity findById(Integer merchant_activity_id);
}
