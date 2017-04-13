package com.yst.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.ActivityDao;
import com.yst.web.model.Activity;
import com.yst.web.model.ActivityMember;
import com.yst.web.model.AppResult;
import com.yst.web.model.Merchant;
import com.yst.web.model.MerchantActivity;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.service.ActivityService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.FreemarkerUtil;
import com.yst.web.utils.PageModelContext;

@Service("activityService")
@Transactional
public class ActivityServiceImpl implements ActivityService {
	private static Log logger = LogFactory.getLog(ActivityServiceImpl.class);
	@Resource(name = "activityDao")
	private ActivityDao activityDao;

	@Override
	public AppResult getMerchantActivityList(MerchantActivity merchantActivity) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		PageModel pm = PageModelContext.getPageModel();
		String hql = "from MerchantActivity as o";
		List<MerchantActivity> merchantActivityList = this.activityDao.query(
				hql, pm, null);
		if (merchantActivityList.size() == 0) {
			appResult.setError_info("没有添加主题活动数据");
			return appResult;
		}
		appResult.setPage_model(pm);
		appResult.setData(merchantActivityList);
		appResult.setError_info("获取列表成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult getActivityInfo(MerchantActivity merchantActivity) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_activity_id = merchantActivity
				.getMerchant_activity_id();
		if (merchant_activity_id == null || merchant_activity_id.equals("")) {
			appResult.setError_info("活动id不得为空");
			return appResult;
		}
		MerchantActivity dbMerchantActivity = this.activityDao.get(
				MerchantActivity.class, merchant_activity_id);
		if (dbMerchantActivity == null) {
			appResult.setError_info("主题活动id不存在或错误");
			return appResult;
		}
		String hql = "from ActivityMember as o where o.activity.merchant_activity_id=?";
		List<ActivityMember> activityMemberList = this.activityDao.query(hql,
				null, merchant_activity_id);
		Integer join_merber = activityMemberList.size();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activity", dbMerchantActivity);
		map.put("join_member", join_merber);
		appResult.setData(map);
		appResult.setError_info("获取成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public List<MerchantActivity> selectAllByPage(Integer merchant_id,
			Map session) {
		String order_str = "";
		if (merchant_id != null && !merchant_id.equals("")) {
			order_str = " where o.merchant.merchant_id=" + merchant_id;
		}
		String hql = "from MerchantActivity as o" + order_str;
		return this.activityDao.query(hql, PageModelContext.getPageModel(),
				Merchant.class, null);
	}

	@Override
	public AppResult updateInfo(MerchantActivity merchantActivity) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_activity_id = merchantActivity.getMerchant_activity_id();
		if (merchant_activity_id == null || merchant_activity_id.equals("")) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		MerchantActivity dbMerchantActivity =this.activityDao.get(MerchantActivity.class, merchant_activity_id);
		Integer activity_type =dbMerchantActivity.getActivity_type();
		Integer prod_acti_id =dbMerchantActivity.getProd_acti_id();
		if (dbMerchantActivity != null) {
			appResult = BeanUtils.uploadImage(merchantActivity, "merchant_activity");
			if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
				if (appResult.getResult().equals(AppResult.FAILED)) {
					return appResult;
				}
			}
			if(activity_type==2){
				Activity dbActivity =this.activityDao.get(Activity.class, prod_acti_id);
				dbActivity.setContent_desc(merchantActivity.getContent_desc());
				this.activityDao.saveOrUpdate(dbActivity);
				dbMerchantActivity.setContent_desc(dbActivity.getContent_desc());
			}
			BeanUtils.copy(merchantActivity, dbMerchantActivity);
			this.activityDao.update(dbMerchantActivity);
			appResult.setData(dbMerchantActivity);
			appResult.setResult(AppResult.SUCCESS);
			appResult.setError_info("修改成功");
		} else {
			appResult.setError_info("活动id错误或不存在");
		}
		return appResult;
	}

	@Override
	public AppResult add(MerchantActivity merchantActivity, Integer merchant_id) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Activity activity = new Activity();
		Merchant dbMerchant = this.activityDao.get(Merchant.class, merchant_id);
		if (dbMerchant == null) {
			appResult.setError_info("商户id不存在或错误");
			return appResult;
		}
		appResult = BeanUtils.uploadImage(merchantActivity, "merchant_activity");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		Integer activity_type =merchantActivity.getActivity_type();
		if(activity_type==2){
			activity.setContent_desc(merchantActivity.getContent_desc());
			activity.setMerchant(dbMerchant);
			this.activityDao.saveOrUpdate(activity);
			merchantActivity.setProd_acti_id(activity.getActivity_id());
		}
		merchantActivity.setMerchant(dbMerchant);
		this.activityDao.saveOrUpdate(merchantActivity);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public List<Product> getProductList(Integer merchant_id) {
		String hql = "from Product as o where o.merchant.merchant_id=?";
		return this.activityDao.query(hql, null, merchant_id);
	}

	@Override
	public void deleteById(Integer merchant_activity_id) {
		MerchantActivity dbMerchantActivity =this.activityDao.get(MerchantActivity.class, merchant_activity_id);
		Integer activity_type =dbMerchantActivity.getActivity_type();
		Integer prod_acti_id =dbMerchantActivity.getProd_acti_id();
		if(activity_type==2){
			this.activityDao.delete(Activity.class, prod_acti_id);
		}
		this.activityDao.delete(dbMerchantActivity);
	}

	@Override
	public MerchantActivity findById(Integer merchant_activity_id) {
		MerchantActivity dbMerchantActivity =this.activityDao.get(MerchantActivity.class,merchant_activity_id);
		Integer activity_type =dbMerchantActivity.getActivity_type();
		Integer prod_acti_id =dbMerchantActivity.getProd_acti_id();
		if(activity_type==2){
			dbMerchantActivity.setContent_desc(this.activityDao.get(Activity.class, prod_acti_id).getContent_desc());
		}
		return dbMerchantActivity;
	}
}
