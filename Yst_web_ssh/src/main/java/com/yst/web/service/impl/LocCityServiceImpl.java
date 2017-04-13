package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.model.LocCity;
import com.yst.web.model.PageModel;
import com.yst.web.service.ILocCityService;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.PageModelContext;
@Service(ILocCityService.DINAME)
@Transactional
public class LocCityServiceImpl implements ILocCityService{

	private static Log logger = LogFactory.getLog(LocCityServiceImpl.class);
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	
	@Override
	public List<LocCity> selectAll() {
		return this.customerDao.query(LocCity.class);
	}
	@Override
	public List<LocCity> selectByProvID(Integer prov_id) {
		String where_sql="";
		if(!CommUtils.isNull(prov_id)){
			where_sql=" where o.locProv.prov_id="+prov_id;
		}
		String hql  ="from LocCity as o "+where_sql;
		PageModel pm = PageModelContext.getPageModel();
		List<LocCity> locCityList = this.customerDao.query(hql, null, null);
		return locCityList;
	}
}
