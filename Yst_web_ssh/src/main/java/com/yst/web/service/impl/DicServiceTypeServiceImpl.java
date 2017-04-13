package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicServiceTypeDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicServiceType;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicServiceTypeService;
import com.yst.web.utils.PageModelContext;
@Service("dicServiceTypeService")
@Transactional
public class DicServiceTypeServiceImpl implements DicServiceTypeService{
	private static Log logger = LogFactory.getLog(DicServiceTypeServiceImpl.class);
	@Resource(name="dicServiceTypeDao")
	private DicServiceTypeDao dicServiceTypeDao;
	
	@Override
	public List<DicServiceType> selectAll() {
		List<DicServiceType> dicServiceTypesList = this.dicServiceTypeDao.query(DicServiceType.class);
		int length = dicServiceTypesList.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				DicServiceType dicServiceType = dicServiceTypesList.get(i);
				Integer service_type_id = dicServiceType.getService_type_id();
				//0是小时 1是按年
				if(0==service_type_id){
					dicServiceType.setStr_service_type_name("在线咨询/小时");
				}
				if(1==service_type_id){
					dicServiceType.setStr_service_type_name("在线咨询/按年");
				}
				if(2==service_type_id){
					dicServiceType.setStr_service_type_name("到家服务/按年");
				}
				if(3==service_type_id){
					dicServiceType.setStr_service_type_name("到家服务/小时");
				}
				if(4==service_type_id){
					dicServiceType.setStr_service_type_name("会议服务");
				}
			}
		}
		return dicServiceTypesList;
	}
	
	
	@Override
	public List<DicServiceType> queryList() {
		String hql = "from DicServiceType as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicServiceType> dicServiceTypeList = this.dicServiceTypeDao.query(hql, pm, null);
		return dicServiceTypeList;
	}
	@Override
	public DicServiceType findDicServiceTypeInfo(Integer service_type_id) {
		DicServiceType dicServiceType = this.dicServiceTypeDao.findByColumnValue(DicServiceType.class, "service_type_id", service_type_id);
		return dicServiceType;
	}
	@Override
	public AppResult updateDicServiceType(DicServiceType dicServiceType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicServiceTypeDao.update(dicServiceType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDicServiceType(Integer service_type_id) {
		this.dicServiceTypeDao.delete(DicServiceType.class, service_type_id);
	}
	
	@Override
	public AppResult addDicServiceType(DicServiceType dicServiceType) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicServiceTypeDao.save(dicServiceType);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
