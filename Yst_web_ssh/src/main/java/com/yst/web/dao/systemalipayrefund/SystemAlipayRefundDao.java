package com.yst.web.dao.systemalipayrefund;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yst.web.entity.systemalipayrefund.SystemAlipayRefund;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午5:42:22
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface SystemAlipayRefundDao extends BaseDao<SystemAlipayRefund>{
	@Query("from SystemAlipayRefund as o where o.batchNo=:batchNo")
	public SystemAlipayRefund getRefundByBatchNo(@Param("batchNo")String batchNo);
}
