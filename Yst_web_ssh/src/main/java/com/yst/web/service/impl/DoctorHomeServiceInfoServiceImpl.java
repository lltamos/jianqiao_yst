package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorHomeServiceInfoDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorHomeServiceInfo;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.model.PageModel;
import com.yst.web.service.DoctorHomeServiceInfoService;
import com.yst.web.utils.PageModelContext;
@Service("doctorHomeServiceInfoService")
@Transactional
public class DoctorHomeServiceInfoServiceImpl implements DoctorHomeServiceInfoService{
	private static Log logger = LogFactory.getLog(DoctorHomeServiceInfoServiceImpl.class);
	@Resource(name = "doctorHomeServiceInfoDao")
	private DoctorHomeServiceInfoDao doctorHomeServiceInfoDao;
	
	@Override
	public AppResult addDoctorHomeServiceInfo(
			DoctorHomeServiceInfo doctorHomeServiceInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String pay_relative_id = doctorHomeServiceInfo.getPay_relative_id();
		if(doctorHomeServiceInfo!=null){
			if(pay_relative_id!=null){
				DoctorServiceOrder doctorServiceOrder =this.doctorHomeServiceInfoDao.findByColumnValue(DoctorServiceOrder.class, "pay_relative_id", pay_relative_id);
				if(doctorServiceOrder!=null){
					doctorHomeServiceInfo.setDoctorServiceOrder(doctorServiceOrder);
				}else{
					appResult.setError_info("所属订单号的订单为空");
					appResult.setData("");
				}
			}else{
				appResult.setError_info("订单号不能为空");
				appResult.setData("");
			}
			this.doctorHomeServiceInfoDao.save(doctorHomeServiceInfo);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("到家服务信息不能为空");
		}
		return appResult;
	}
	
	
	@Override
	public List<DoctorHomeServiceInfo> queryList() {
		String hql = "from DoctorHomeServiceInfo as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DoctorHomeServiceInfo> doctorHomeServiceInfoList = this.doctorHomeServiceInfoDao.query(hql, pm, null);
		return doctorHomeServiceInfoList;
	}
	@Override
	public DoctorHomeServiceInfo findDoctorHomeServiceInfoInfo(Integer order_id) {
		DoctorHomeServiceInfo doctorHomeServiceInfo = this.doctorHomeServiceInfoDao.findByColumnValue(DoctorHomeServiceInfo.class, "order_id", order_id);
		return doctorHomeServiceInfo;
	}
	@Override
	public AppResult updateDoctorHomeServiceInfo(DoctorHomeServiceInfo doctorHomeServiceInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.doctorHomeServiceInfoDao.update(doctorHomeServiceInfo);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDoctorHomeServiceInfo(Integer order_id) {
		this.doctorHomeServiceInfoDao.delete(DoctorHomeServiceInfo.class, order_id);
	}
	

}
