package com.alqsoft.rpc.impl;

import java.util.Date;
import java.util.Map;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.entity.ImLog;
import com.alqsoft.rpc.RpcImDoctorServices;
import com.alqsoft.service.imlog.ImLogService;

@Service
public class RpcImDoctorServicesImpl implements RpcImDoctorServices{
	
	
	@Autowired
	private ImLogService imLogService;
	
	@Override
	public Result saveImLog(Map<String, Object> param) {
		try {
			ImLog il= new ImLog();
			il.setCreatedTime(new Date());
			il.setDoctorPhone(param.get("doctorName")+"");
			il.setCustomerPhone(param.get("customerName")+"");
			il.setCustomerId(Long.parseLong(param.get("customerId")+""));
			il.setDoctorId(Long.parseLong(param.get("doctorId")+""));
			imLogService.saveAndModify(il);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultUtils.returnError("保存用户记录异常");
		}
		return ResultUtils.returnSuccess("保存用户聊天记录成功");
	}

	
	

}
