package com.alqsoft.service.order;

import com.alqsoft.entity.DoctorServiceOrder;

/**
 * 在线咨询逻辑接口
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 上午9:43:53
 * 
 */
public interface ZXZXOrderService {

	public DoctorServiceOrder saveAndModify(DoctorServiceOrder arg0);
	public DoctorServiceOrder getDoctorServiceOrderByorderNo(String order_id);
}
