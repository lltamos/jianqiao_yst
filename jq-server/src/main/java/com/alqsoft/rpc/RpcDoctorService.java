package com.alqsoft.rpc;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.doctor.Doctors;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午7:20:54
 * 
 */
public interface RpcDoctorService {
	
	public Result applyDoctor(Doctors doctor, Customer customer);
	
	public Doctors getDoctorById(Long id);

}
