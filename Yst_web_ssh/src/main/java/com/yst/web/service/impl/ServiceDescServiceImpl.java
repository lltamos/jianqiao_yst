package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.ServiceDescDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Doctor;
import com.yst.web.model.PageModel;
import com.yst.web.model.ServiceDesc;
import com.yst.web.service.ServiceDescService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
@Service("serviceDescService")
@Transactional
public class ServiceDescServiceImpl implements ServiceDescService{

	@Resource(name="serviceDescDao")
	private ServiceDescDao serviceDescDao;
	
	@Override
	public AppResult findServiceDescByType(Integer type) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(type!=null){
			ServiceDesc serviceDesc = this.serviceDescDao.findByColumnValue(ServiceDesc.class, "type", type);
			List<Doctor> list = this.serviceDescDao.selectByColumnValue(Doctor.class, "verify", 1);
			int num = list.size();
			if(num>0){
				serviceDesc.setDoctor_count(num);
			}else{
				serviceDesc.setDoctor_count(0);
			}
			if(serviceDesc!=null){
				appResult.setData(serviceDesc);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属服务类型无数据");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("服务类型不能为空");
			appResult.setData("");
		}
		return appResult;
	}
	
	
	@Override
	public List<ServiceDesc> queryList() {
		String hql = "from ServiceDesc as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<ServiceDesc> serviceDescList = this.serviceDescDao.query(hql, pm, null);
		return serviceDescList;
	}
	@Override
	public ServiceDesc findServiceDescInfo(Integer id) {
		ServiceDesc serviceDesc = this.serviceDescDao.findByColumnValue(ServiceDesc.class, "id", id);
		return serviceDesc;
	}
	@Override
	public AppResult updateServiceDesc(ServiceDesc serviceDesc) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer id = serviceDesc.getId();
		if(id!=null){
			ServiceDesc sd = this.serviceDescDao.findByColumnValue(ServiceDesc.class, "id", id);
			if(sd!=null){
				appResult = BeanUtils.uploadImage(serviceDesc, "serviceDesc");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				BeanUtils.copy(serviceDesc, sd);
				this.serviceDescDao.update(sd);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("所属服务描述不存在");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("服务描述id不能为空");
			appResult.setData("");
		}
		return appResult;
	} 
	@Override
	public void deleteServiceDesc(Integer id) {
		this.serviceDescDao.delete(ServiceDesc.class, id);
	}
	
	@Override
	public AppResult addServiceDesc(ServiceDesc serviceDesc) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		appResult = BeanUtils.uploadImage(serviceDesc, "serviceDesc");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		this.serviceDescDao.save(serviceDesc);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public List<ServiceDesc> selectAll() {
		return this.serviceDescDao.query(ServiceDesc.class);
	}
}
