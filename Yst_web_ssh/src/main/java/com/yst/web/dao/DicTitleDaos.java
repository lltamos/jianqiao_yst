package com.yst.web.dao;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yst.web.model.DicTitle;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月25日 下午4:08:56
 * 
 */
public interface DicTitleDaos extends BaseDao<DicTitle>{

	@Query("from DicTitle d where d.title_id=:title_id")
	DicTitle getTitleNameById(@Param("title_id")Integer title_id);
}
