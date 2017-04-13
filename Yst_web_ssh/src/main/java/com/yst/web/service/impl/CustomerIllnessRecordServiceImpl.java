package com.yst.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerIllnessRecordDao;
import com.yst.web.dao.CustomerRelativeDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerIllnessRecord;
import com.yst.web.model.CustomerRelative;
import com.yst.web.model.PageModel;
import com.yst.web.service.CustomerIllnessRecordService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.PageModelContext;
@Service("customerIllnessRecordService")
@Transactional
public class CustomerIllnessRecordServiceImpl implements CustomerIllnessRecordService{
	private static Log logger = LogFactory.getLog(CustomerIllnessRecordServiceImpl.class);
	@Resource(name = "customerIllnessRecordDao")
	private CustomerIllnessRecordDao customerIllnessRecordDao;
	@Resource(name = "customerRelativeDao")
	CustomerRelativeDao customerRelativeDao;
	
	
	@Override
	public AppResult findDetails(int id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(id>0){
			CustomerIllnessRecord customerIllnessRecord = this.customerIllnessRecordDao.findByColumnValue(CustomerIllnessRecord.class, "record_id", id);
			if(customerIllnessRecord!=null){
				appResult.setResult(AppResult.SUCCESS);
				appResult.setData(customerIllnessRecord);
			}else{
				appResult.setError_info("所属id无数据");
			}
		}else{
			appResult.setError_info("记录id不正确");
		}
		return appResult;
	}

	@Override
	public List<CustomerIllnessRecord> selectAll() {
		return this.customerIllnessRecordDao.query(CustomerIllnessRecord.class);
	}

	@Override
	public AppResult queryRecordList(CustomerIllnessRecord customerIllnessRecord) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);//默认失败
		appResult.setVersion(1);//默认1
		Integer relative_id = customerIllnessRecord.getRelative_id();
		String hqll = "from CustomerRelative as c where patient_id=?";
		CustomerRelative customerRelative = (CustomerRelative) this.customerRelativeDao.queryForObject(hqll, relative_id);
		if(CommUtils.isNull(customerRelative)){
			appResult.setError_info("相关人员id不能为空");
			appResult.setData("");
		}
		relative_id = customerRelative.getRelative_id();
		if(relative_id==null || "".equals(relative_id)){
			appResult.setError_info("相关人员id不能为空");
			appResult.setData("");
		}else{
			String hql = "from CustomerIllnessRecord as c where customerRelative.relative_id=?";
			PageModel pm = PageModelContext.getPageModel();
			List<CustomerIllnessRecord> list = this.customerIllnessRecordDao.query(hql, pm, relative_id);
			int length = list.size();
			if(length>0){
				for (int i = 0; i < length; i++) {
					CustomerIllnessRecord cr = list.get(i);
					//设置年龄
					Date date = new Date();
					Date birthDate = cr.getCustomerRelative().getBirth_date();
					int da =date.getYear()-birthDate.getYear();
					cr.setAge(da);
					//日期格式化
					Date cr_date = cr.getEntering_time();
					SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
					String d = sdf.format(cr_date);
					cr.setStr_entering_time(d);
				}
				appResult.setCustomer_id(relative_id);
				appResult.setPage_model(pm);
				appResult.setData(list);
				appResult.setError_info("获取列表成功");
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setData("");
				appResult.setError_info("所属用户没有病例档案");
			}
		}
		return appResult;
	}
	
	@Override
	public AppResult addIllnessRecord( CustomerIllnessRecord customerIllnessRecord) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer relative_id = customerIllnessRecord.getRelative_id();
		if(relative_id !=null || !"".equals(relative_id)){
			String hqll = "from CustomerRelative as c where patient_id=?";
			CustomerRelative cr = (CustomerRelative) this.customerRelativeDao.queryForObject(hqll, relative_id);
			if(cr==null){
				appResult.setError_info("没有相关人员信息");
			}else{
				appResult = BeanUtils.uploadImage(customerIllnessRecord, "customerIllnessRecord");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				Date date = new Date(System.currentTimeMillis());
				SimpleDateFormat fromt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String datetime = fromt.format(date);
				try {
					Date dt = fromt.parse(datetime);
					customerIllnessRecord.setEntering_time(dt);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				customerIllnessRecord.setCustomerRelative(cr);
				this.customerIllnessRecordDao.save(customerIllnessRecord);
				appResult.setResult(AppResult.SUCCESS);
			}
		}else{
			appResult.setError_info("相关人员信息id为空");
		}
		return appResult;
	}

	@Override
	public AppResult deleteIllnessRecord(Integer record_id) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(record_id>0){
			CustomerIllnessRecord  customerIllnessRecord= this.customerIllnessRecordDao.get(CustomerIllnessRecord.class, record_id);
			if(customerIllnessRecord!=null){
				this.customerIllnessRecordDao.delete(CustomerIllnessRecord.class, record_id);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("删除失败:病例档案id不存在");
			}
		}else{
			appResult.setError_info("病例档案id错误");
		}
		return appResult;
	}

	@Override
	public void deleteByParame(Integer relative_id) {
		this.customerIllnessRecordDao.deleteByParame(relative_id);
	}

	@Override
	public List<CustomerIllnessRecord> findCustomerIllnessRecordList() {
		String hql = "from CustomerIllnessRecord as c ";
		List<CustomerIllnessRecord> list = this.customerIllnessRecordDao.query(hql, PageModelContext.getPageModel(), null);
		int length = list.size();
		if(length>0){
			for (int i = 0; i < length; i++) {
				CustomerIllnessRecord customerIllnessRecord = list.get(i);
				customerIllnessRecord.setStr_relative(customerIllnessRecord.getCustomerRelative().getName());
			}
		}
		return list;
	}

	@Override
	public CustomerIllnessRecord findCustomerIllnessRecordInfo( Integer record_id) {
		CustomerIllnessRecord customerIllnessRecord  = this.customerIllnessRecordDao.findByColumnValue(CustomerIllnessRecord.class, "record_id", record_id);
		return customerIllnessRecord;
	}

	
	@Override
	public AppResult updateCustomerIllnessRecord(CustomerIllnessRecord customerIllnessRecord) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		Integer record_id = customerIllnessRecord.getRecord_id();
		if(record_id!=null&&!"".equals(record_id)){
			CustomerIllnessRecord cr = this.customerIllnessRecordDao.findByColumnValue(CustomerIllnessRecord.class, "record_id", record_id);
			if(cr!=null){
				Integer relative_id = customerIllnessRecord.getRelative_id();
				if(relative_id!=null){
					CustomerRelative customerRelative = new CustomerRelative();
					customerRelative.setRelative_id(relative_id);
					cr.setCustomerRelative(customerRelative);
				}else{
					appResult.setError_info("相关人员id为空");
					appResult.setData("");
				}
				appResult = BeanUtils.uploadImage(customerIllnessRecord, "customerIllnessRecord");
				if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
					if (appResult.getResult().equals(AppResult.FAILED)) {
						return appResult;
					}
				}
				BeanUtils.copy(customerIllnessRecord, cr);
				this.customerIllnessRecordDao.update(cr);
				appResult.setResult(AppResult.SUCCESS);
			}else{
				appResult.setError_info("病例记录为空");
				appResult.setData("");
			}
		}else{
			appResult.setError_info("病例记录id不能为空");
			appResult.setData("");
		}
		return appResult;
	}

}
