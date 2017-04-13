package com.alqsoft.service.impl.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.productorder.ProductOrderDao;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.service.order.FWBOderService;

/**
 * 服务包订单业务层
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 上午9:47:37
 * 
 */
@Service
@Transactional(readOnly=true)
public class FWBOrderServiceImpl implements FWBOderService{
    @Autowired
	private ProductOrderDao productOrderDao;
    
    @Transactional
	public ProductOrder saveAndModify(ProductOrder arg0) {
		return productOrderDao.save(arg0);
	}

    public ProductOrder getDoctorServiceOrderByorderNo(String order_id){
    	return productOrderDao.getDoctorServiceOrderByorderNo(order_id);
    }


}
