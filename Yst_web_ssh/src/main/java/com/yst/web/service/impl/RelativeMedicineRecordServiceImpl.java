package com.yst.web.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerRelativeDao;
import com.yst.web.dao.RelativeMedicineRecordDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerRelative;
import com.yst.web.model.PageModel;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.service.RelativeMedicineRecordService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
@Service("relativeMedicineRecordService")
@Transactional
@SuppressWarnings("unused")
public class RelativeMedicineRecordServiceImpl implements RelativeMedicineRecordService{
	private static Log logger = LogFactory.getLog(RelativeMedicineRecordServiceImpl.class);
	@Resource(name = "relativeMedicineRecordDao")
	private RelativeMedicineRecordDao relativeMedicineRecordDao;
	@Resource(name = "customerRelativeDao")
	CustomerRelativeDao customerRelativeDao;
	
	@Override
	public AppResult addRelativeMedicineRecord(RelativeMedicineRecord relativeMedicineRecord) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(relativeMedicineRecord.getStart_date()==null){
			appResult.setError_info("用药日期不能为空");
			return appResult;
		}
		if(relativeMedicineRecord!=null){
			Integer relative_Id = relativeMedicineRecord.getRelative_id();
			String hqll = "from CustomerRelative as c where patient_id=?";
			CustomerRelative crt = (CustomerRelative) this.customerRelativeDao.queryForObject(hqll, relative_Id);
			if(crt != null){
			relative_Id = crt.getRelative_id();
				if(relative_Id!=null || !"".equals(relative_Id)){
					CustomerRelative customerRelative = this.relativeMedicineRecordDao.findByColumnValue(CustomerRelative.class, "relative_id", relative_Id);
					if(customerRelative!=null){
						appResult = BeanUtils.uploadImage(relativeMedicineRecord, "relativeMedicineRecord");
						if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
							if (appResult.getResult().equals(AppResult.FAILED)) {
								return appResult;
							}
						}
						CustomerRelative cr =new CustomerRelative();
						cr.setRelative_id(relative_Id);
						relativeMedicineRecord.setCustomerRelative(cr);
						this.relativeMedicineRecordDao.save(relativeMedicineRecord);
						Integer record_id = relativeMedicineRecord.getRecord_id();
						appResult.setData(record_id);
						appResult.setResult(AppResult.SUCCESS);
					}else{
						appResult.setError_info("不存在所属相关人员信息");
					}
			
				}
			}else{
				appResult.setError_info("相关人员id不能为空");
			}
		}else{
			appResult.setError_info("相关人员id不能为空");
		}
		return appResult;
	}


	@Override
	public AppResult getRelativeMedicineRecord(int relative_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String hqll = "from CustomerRelative as c where patient_id=?";
		CustomerRelative cr = (CustomerRelative) this.customerRelativeDao.queryForObject(hqll, relative_id);
		if(cr != null){
			relative_id = cr.getRelative_id();
			String hql = "from RelativeMedicineRecord as r where relative_id=?";
			PageModel pm =  PageModelContext.getPageModel();
			List<RelativeMedicineRecord> list = this.relativeMedicineRecordDao.query(hql, pm, relative_id);
			if(list.size()>0){
				int length = list.size();
				for (int i = 0; i < length; i++) {
					RelativeMedicineRecord relativeMedicineRecord = list.get(i);
					Date datetime = relativeMedicineRecord.getStart_date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					String strdatetime = sdf.format(datetime);
					relativeMedicineRecord.setStr_start_date(strdatetime);
				}
				appResult.setData(list);
				appResult.setPage_model(pm);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setData("");
				appResult.setError_info("所属相关人员无数据");
			}
		}else{
			appResult.setData("");
			appResult.setError_info("相关人员id为空");
		}
		return appResult;
	}


	@Override
	public AppResult deleteRelativeMedicineRecord(int record_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(record_id>0){
			RelativeMedicineRecord relativeMedicineRecord = this.relativeMedicineRecordDao.findByColumnValue(RelativeMedicineRecord.class, "record_id", record_id);
			if(relativeMedicineRecord!=null){
				this.relativeMedicineRecordDao.deleteRelativeMedicne(relativeMedicineRecord);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("数据不存在");
			}
		}else{
			appResult.setError_info("用药记录id不能为空");
		}
		return appResult;
	}


	@Override
	public List<RelativeMedicineRecord> findRelativeMedicineRecordList() {
		String hql = "from RelativeMedicineRecord as o ";
		List<RelativeMedicineRecord> list = this.relativeMedicineRecordDao.query(hql, PageModelContext.getPageModel(),RelativeMedicineRecord.class, null);
		int length = list.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				RelativeMedicineRecord relativeMedicineRecord = list.get(i);
				Date start_date = relativeMedicineRecord.getStart_date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String strStartDate = sdf.format(start_date);
				relativeMedicineRecord.setStr_start_date(strStartDate);
				relativeMedicineRecord.setStr_relative(relativeMedicineRecord.getCustomerRelative().getName());
			}
		}
		return list;
	}


	@Override
	public RelativeMedicineRecord findRelativeMedicineRecordInfo(
			Integer record_id) {
		return this.relativeMedicineRecordDao.findByColumnValue(RelativeMedicineRecord.class, "record_id", record_id);
	}


	@Override
	public AppResult updateRelativeMedicineRecord(RelativeMedicineRecord relativeMedicineRecord) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer record_id = relativeMedicineRecord.getRecord_id();
		if(record_id!=null){
			appResult = BeanUtils.uploadImage(relativeMedicineRecord, "relativeMedicineRecord");
			if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
				if (appResult.getResult().equals(AppResult.FAILED)) {
					return appResult;
				}
			}
			CustomerRelative customerRelative = new CustomerRelative();
			customerRelative.setRelative_id(relativeMedicineRecord.getRelative_id());
			relativeMedicineRecord.setCustomerRelative(customerRelative);
			this.relativeMedicineRecordDao.update(relativeMedicineRecord);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("用药记录id不能为空");
			appResult.setData("");
		}
		return appResult;
	}


}
