package com.alqsoft.service.impl.productdetails;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.productdetails.ProductDetailsDao;
import com.alqsoft.service.productdetails.ProductDetailsService;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
	
	@Autowired
	private ProductDetailsDao productDetailsDao;
	
	@Override
	public Map<String, Object> getProductDetailsById(Long id) {
		// TODO Auto-generated method stub
		return productDetailsDao.getProductDetailsById(id);
	}

	@Override
	public List<Map<String, Object>> getDoctorByProductTypeId(Long productTypeId) {
		// TODO Auto-generated method stub
		return productDetailsDao.getDoctorByProductTypeId(productTypeId);
	}

	@Override
	public List<Map<String, Object>> getDiaryByProductTypeId(Map<String,Object> param) {
		List<Map<String, Object>> diary = productDetailsDao.getDiaryByProductTypeId(param);
		int count = 1;
		for(Map<String, Object> map : diary){
			
			if(null != map.get("id")){
				String id = map.get("id").toString();
				Long likeNum = productDetailsDao.getLikeNum(Long.parseLong(id));
				if(null != likeNum){
					map.put("like", likeNum);
				}
				List<String> imageList = productDetailsDao.getImageByDiaryId(Long.parseLong(id));
				if(null != likeNum){
					map.put("imageList", imageList);
				}
				map.put("count",count++);
			}
			
		}
		return diary;
	}

	@Override
	public Map<String, Object> getNewDiary() {
		// TODO Auto-generated method stub
		return productDetailsDao.getNewDiary();
	}

	@Override
	public Map<String, Object> getDepositePrice(Long id) {
		// TODO Auto-generated method stub
		return productDetailsDao.getDepositePrice(id);
	}

	@Override
	public Map<String, Object> getServiceNameByServiceTypeId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return productDetailsDao.getServiceNameByServiceTypeId(map).get(0);
	}

}
