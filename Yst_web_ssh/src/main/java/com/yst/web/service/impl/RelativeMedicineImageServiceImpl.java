package com.yst.web.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.RelativeMedicineImageDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.RelativeMedicineImage;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.service.RelativeMedicineImageService;
import com.yst.web.utils.BeanUtils;
@Service("relativeMedicineImageService")
@Transactional
public class RelativeMedicineImageServiceImpl implements RelativeMedicineImageService{
	private static Log logger = LogFactory.getLog(RelativeMedicineImageServiceImpl.class);
	@Resource(name ="relativeMedicineImageDao")
	private RelativeMedicineImageDao relativeMedicineImageDao;
	
	@Override
	public AppResult addRelativeMedicineImage(RelativeMedicineImage relativeMedicineImage) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(relativeMedicineImage!=null){
			Integer recordId =  relativeMedicineImage.getRecord_id();
			if(recordId!=null || !"".equals(recordId)){
				RelativeMedicineRecord relativeMedicineRecord = this.relativeMedicineImageDao.findByColumnValue(RelativeMedicineRecord.class, "record_id", recordId);
				if(relativeMedicineRecord!=null){
					appResult = BeanUtils.uploadImage(relativeMedicineImage, "relativeMedicineImage");
					if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
						if (appResult.getResult().equals(AppResult.FAILED)) {
							return appResult;
						}
					}
					//relativeMedicineImage.setRelativeMedicineRecord(relativeMedicineRecord);
					this.relativeMedicineImageDao.save(relativeMedicineImage);
					appResult.setResult(AppResult.SUCCESS);
				}else{
					appResult.setError_info("所属用药记录不存在");
					appResult.setData("");
				}
			}else{
				appResult.setError_info("用药记录id不能为空");
			}
		}else{
			appResult.setError_info("用药记录图片信息不能为空");
		}
		return appResult;
	}

}
