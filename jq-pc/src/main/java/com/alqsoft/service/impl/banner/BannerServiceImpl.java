package com.alqsoft.service.impl.banner;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.alqframework.result.ResultUtils;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.banner.BannerDao;
import com.alqsoft.service.banner.BannerService;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月29日 下午4:59:08 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@Service
public class BannerServiceImpl implements BannerService {
	@Resource
	private BannerDao bannerdao;

	@Override
	public List<Map<String, Object>> fetchBanner(Integer len) {
		List<Map<String, Object>> list = null;

		if (len != null) {
			list = bannerdao.fetchBanner(len);
		}
	
		return list;
	}

}
