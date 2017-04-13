package com.alqsoft.service.order;

import com.alqsoft.entity.ProductOrder;

/**
 * 服务包逻辑接口
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 上午9:48:08
 * 
 */
public interface FWBOderService {

	public ProductOrder saveAndModify(ProductOrder arg0);
	public ProductOrder getDoctorServiceOrderByorderNo(String order_id);
}
