package com.alqsoft.dao.merchant;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.Merchant;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月16日 下午12:07:23
 * 
 */
@MyBatisRepository
public interface MerchantDao {

	List<Map<String, Object>> findMerchantAll();

	Merchant getMerchantById(Long id);

}
