package com.alqsoft.rpc.impl;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.alqsoft.dao.productorder.ProductOrderDao;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.rpc.RpcProductOrderService;

@Service
@Transactional
public class RpcProductOrderServiceImpl implements RpcProductOrderService {

	@Autowired
	private ProductOrderDao productOrderDao;

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		productOrderDao.delete(arg0);
		return true;
	}

	@Override
	public ProductOrder get(Long arg0) {
		// TODO Auto-generated method stub
		ProductOrder productOrder = productOrderDao.findOne(arg0);
		return productOrder;
	}

	@Override
	public ProductOrder saveAndModify(ProductOrder arg0) {
		// TODO Auto-generated method stub
		return productOrderDao.save(arg0);
	}

	@Override
	public ProductOrder addProductOrder(ProductOrder p) {
		// TODO Auto-generated method stub
		ProductOrder save = productOrderDao.save(p);
		return save;
	}

	@Override
	public boolean lazyCensorOrder(Long id) {

		
		
		Timer t = new Timer();

		t.schedule(new TimerTask() {

			@Override
			public void run() {
				ProductOrder p = productOrderDao.findOne(id);
				

				if (p!=null&&p.getPayStatus() == 0) {					
					productOrderDao.delete(id);					
				}

			}
		}, 900000);

		return true;
	}

}
