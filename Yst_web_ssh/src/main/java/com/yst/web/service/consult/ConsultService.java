package com.yst.web.service.consult;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.consult.Consult;

public interface ConsultService extends BaseService<Consult>{

	/**
	 * 首页健桥资讯
	 * @return
	 */
	public Result findConsultList();

}
