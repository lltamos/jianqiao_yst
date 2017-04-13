package com.alqsoft.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.doctorserviceorder.DoctorServiceOrderDao;
import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.service.order.ZXZXOrderService;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 上午9:44:55
 * 
 */
@Service
@Transactional(readOnly=true)
public class ZXZXOrderImpl implements ZXZXOrderService{

	    @Autowired
		private DoctorServiceOrderDao doctorServiceOrderDao;
	    
	    @Transactional
		public DoctorServiceOrder saveAndModify(DoctorServiceOrder arg0) {
			return doctorServiceOrderDao.save(arg0);
		}

	    public DoctorServiceOrder getDoctorServiceOrderByorderNo(String order_id){
	    	return doctorServiceOrderDao.getDoctorServiceOrderByorderNo(order_id);
	    }

	
}
