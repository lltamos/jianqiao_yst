package com.alqsoft.dao.manage;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.manager.Manager;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年10月13日 下午5:37:06
 * 
 */
public interface ManageDao extends BaseDao<Manager>{

	@Query("FROM Manager m WHERE m.account=:account")
	public Manager getManageByAccount(@Param("account")String account);

}
