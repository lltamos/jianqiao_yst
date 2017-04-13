package com.yst.web.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorCallInfoDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorCallInfo;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.service.DoctorCallInfoService;

@Service("doctorCallInfoService")
@Transactional
public class DoctorCallInfoServiceImpl implements DoctorCallInfoService {
	private static Log logger = LogFactory.getLog(DoctorCallInfoServiceImpl.class);
	@Resource(name = "doctorCallInfoDao")
	private DoctorCallInfoDao doctorCallInfoDao;

	@Override
	public AppResult saveOrUpdateRemianCallTime(DoctorCallInfo doctorCallInfo) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String order_id = doctorCallInfo.getOrder_id();
		Integer during_time = doctorCallInfo.getDuring_time();
		if (order_id == null || order_id.equals("")) {
			appResult.setError_info("订单id不能为空");
			return appResult;
		}
		DoctorServiceOrder dbDso = this.doctorCallInfoDao.findByColumnValue(
				DoctorServiceOrder.class, "pay_relative_id", order_id);
		if (dbDso == null) {
			appResult.setError_info("订单号错误或不存在");
			return appResult;
		}
		Integer service_type_id = dbDso.getDicServiceType()
				.getService_type_id();
		Integer pay_status = dbDso.getPay_status();
		if(pay_status!=1){
			appResult.setError_info("订单未付款");
			return appResult;
		}
		if (service_type_id != 1) {
			appResult.setError_info("不是在线通话类订单");
			return appResult;
		}
		Integer buy_time = dbDso.getBuy_time();
		Integer remain_time=null;
		logger.debug(remain_time);
		String hql ="from DoctorCallInfo as o where order_id=? order by o.update_date desc";
		DoctorCallInfo dbDoctorCallInfo = (DoctorCallInfo) this.doctorCallInfoDao.queryForObject(hql, order_id);
		if (dbDoctorCallInfo == null) {
			if (during_time == null || during_time.equals("") || during_time == 0) {// 无时间查询剩余时间
				doctorCallInfo.setDuring_time(0);
				doctorCallInfo.setRemain_time(buy_time);
			} else {// 有时间
				doctorCallInfo.setDuring_time(during_time);
				remain_time=buy_time-during_time;
				if(remain_time<=0){
					remain_time=0;
				}
				doctorCallInfo.setRemain_time(remain_time);
			}
			doctorCallInfo.setBuy_time(buy_time);
			doctorCallInfo.setUpdate_date(new Date());
			dbDoctorCallInfo = doctorCallInfo;
		}else{
			if (during_time == null || during_time.equals("") || during_time == 0) {// 无时间
			} else {// 有时间
				remain_time = dbDoctorCallInfo.getRemain_time();
				dbDoctorCallInfo.setDuring_time(during_time);
				remain_time=remain_time-during_time;
				if(remain_time<=0){
					remain_time=0;
				}
				dbDoctorCallInfo.setRemain_time(remain_time);
				dbDoctorCallInfo.setUpdate_date(new Date());
			}
		}
		this.doctorCallInfoDao.saveOrUpdate(dbDoctorCallInfo);
		if(remain_time!=null && remain_time==0){//通话时长用完 修改订单状态
			dbDso.setPay_status(4);
			this.doctorCallInfoDao.saveOrUpdate(dbDso);
		}
		appResult.setData(dbDoctorCallInfo);
		appResult.setError_info("获取成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
