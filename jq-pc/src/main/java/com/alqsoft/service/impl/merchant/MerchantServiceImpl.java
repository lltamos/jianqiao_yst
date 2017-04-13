package com.alqsoft.service.impl.merchant;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.service.merchant.MerchantService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午5:35:30
 * 
 */
@Service
public class MerchantServiceImpl implements MerchantService{

	@Autowired
	private MerchantDao merchantDao;
	
	@Override
	public List<Map<String, Object>> findMerchantAll() {
		
		return merchantDao.findMerchantAll();
	}

}
