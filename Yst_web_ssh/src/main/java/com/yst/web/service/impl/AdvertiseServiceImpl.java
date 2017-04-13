package com.yst.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.AdvertiseDao;
import com.yst.web.model.Advertise;
import com.yst.web.model.AppResult;
import com.yst.web.model.Product;
import com.yst.web.model.ProductOrder;
import com.yst.web.model.Store;
import com.yst.web.service.AdvertiseService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.FreemarkerUtil;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Service("advertiseService")
@Transactional
public class AdvertiseServiceImpl implements AdvertiseService {
	private static Log logger = LogFactory.getLog(AdvertiseServiceImpl.class);
	@Resource(name = "advertiseDao")
	private AdvertiseDao advertiseDao;

	@Override
	public Advertise findById(int id) {
		return this.advertiseDao.get(Advertise.class, id);
	}

	@Override
	public List<Advertise> selectAll() {
		return this.advertiseDao.query(Advertise.class);
	}

	@Override
	public void deleteById(int id) {
		this.advertiseDao.delete(Advertise.class, id);
	}

	@Override
	public void update(Advertise advertise) {
		this.advertiseDao.update(advertise);
	}

	@Override
	public AppResult add(Advertise advertise) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		appResult = BeanUtils.uploadImage(advertise, "advertise");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		String detail_html = advertise.getDetail_html();
		String url = "";
		if (detail_html != null && !detail_html.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("advertise", advertise);
			url = FreemarkerUtil.make("html3.ftl", "UTF-8", map);
			url = ServerParam.DOMAIN+ServerParam.PROJECT_NAME+url;
			advertise.setLink_content(ServerParam.DOMAIN+ServerParam.PROJECT_NAME+url);
			logger.debug(url);
		}
		this.advertiseDao.save(advertise);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult getAdvList(Advertise advertise) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String hql = "from Advertise as o order by o.order_index asc";
		List<ProductOrder> list = this.advertiseDao.query(hql, null, null);
		if (list.size() == 0) {
			appResult.setError_info("没有添加广告");
		} else {
			appResult.setData(list);
			appResult.setError_info("获取列表成功");
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public AppResult getInfo(Advertise advertise) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer adv_id = advertise.getAdv_id();
		if (adv_id == null || adv_id.equals("")) {
			appResult.setError_info("广告id不能为空");
		} else {
			Advertise dbAdvertise = this.advertiseDao.get(Advertise.class,
					adv_id);
			if (dbAdvertise == null) {
				appResult.setError_info("广告id错误或未注册");
			} else {
				appResult.setData(dbAdvertise.getLink_content());
				appResult.setError_info("获取成功");
				appResult.setResult(AppResult.SUCCESS);
			}
		}
		return appResult;
	}

	@Override
	public List<Advertise> selectAllByPage() {
		String hql = "from Advertise as o";
		return this.advertiseDao.query(hql, PageModelContext.getPageModel(),
				null);
	}

	@Override
	public AppResult updateInfo(Advertise advertise) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer adv_id = advertise.getAdv_id();
		if (adv_id == null || adv_id.equals("")) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		Advertise dbAdvertise = this.advertiseDao.get(Advertise.class, adv_id);
		if (dbAdvertise != null) {
			appResult = BeanUtils.uploadImage(advertise, "advertise");
			if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
				if (appResult.getResult().equals(AppResult.FAILED)) {
					return appResult;
				}
			}
			BeanUtils.copy(advertise, dbAdvertise);
			String detail_html = advertise.getDetail_html();
			if (detail_html != null && !detail_html.equals("")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("advertise", dbAdvertise);
				String url = FreemarkerUtil.make("html3.ftl", "UTF-8", map);
				Advertise newAdvertise = new Advertise();
				url = ServerParam.DOMAIN+ServerParam.PROJECT_NAME+url;
				newAdvertise.setLink_content(url);
				logger.debug(url);
				BeanUtils.copy(newAdvertise, dbAdvertise);
			}
			this.advertiseDao.update(dbAdvertise);
			appResult.setData(dbAdvertise);
			appResult.setResult(AppResult.SUCCESS);
			appResult.setError_info("修改成功");
		} else {
			appResult.setError_info("id错误或不存在");
		}
		return appResult;
	}
}
