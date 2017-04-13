package com.alqsoft.dao.banner;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

/**
 * 
 * @Description: TODO
 * @author llsmp
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月29日 下午4:45:57 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */
@MyBatisRepository
public interface BannerDao {

	List<Map<String, Object>> fetchBanner(Integer len);

}
