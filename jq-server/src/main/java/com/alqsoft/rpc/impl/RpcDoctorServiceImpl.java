package com.alqsoft.rpc.impl;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.dic.DicOfficeDao;
import com.alqsoft.dao.dic.DicTitleDao;
import com.alqsoft.dao.doctor.DoctorDao;
import com.alqsoft.dao.merchant.MerchantDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicOffice;
import com.alqsoft.entity.DicTitle;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.rpc.RpcDoctorService;
import com.alqsoft.service.doctor.DoctorService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午7:21:20
 * 
 */
@Service
public class RpcDoctorServiceImpl implements RpcDoctorService {

	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private DicTitleDao dicTitleDao;
	
	@Autowired
	private DicOfficeDao dicOfficeDao;
	
	@Autowired
	private MerchantDao merchantDao;
	
	@Autowired
	private DoctorDao doctorDao;
	
	
	@Override
	public Result applyDoctor(Doctors doctor, Customer customer) {
		Result result = new Result();
		if (customer == null) {
			result.setCode(1);
			result.setMsg("用户不存在");
			return result;
		}
		doctor.setCustomerId(customer.getId());
		doctor.setCustomerPhone(customer.getPhone());
		Long merchantId = doctor.getMerchantId();
		Merchant merchant = merchantDao.findOne(merchantId);
		if (merchant == null) {
			result.setCode(1);
			result.setMsg("工作单位不存在");
			return result;
		}
		doctor.setMerchantName(merchant.getName());
		Long titleId = doctor.getTitleId();
		DicTitle dicTitle = dicTitleDao.findOne(titleId);
		if(dicTitle == null){
			result.setCode(1);
			result.setMsg("工作职称不存在");
			return result;
		}
		doctor.setTitleName(dicTitle.getName());
		Long officeId = doctor.getOfficeId();
		DicOffice dicOffice = dicOfficeDao.findOne(officeId);
		if(dicOffice == null){
			result.setCode(1);
			result.setMsg("工作科室不存在");
			return result;
		}
		doctor.setOfficeName(dicOffice.getName());
		return doctorService.saveApplyDoctor(doctor);
	}

	@Override
	public Doctors getDoctorById(Long id) {
		// TODO Auto-generated method stub
		Doctors doctors = doctorDao.findOne(id);
		return doctors;
	}

}
