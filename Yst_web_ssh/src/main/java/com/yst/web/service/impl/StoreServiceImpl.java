package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.MerchantDao;
import com.yst.web.dao.StoreDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Merchant;
import com.yst.web.model.PageModel;
import com.yst.web.model.ProductOrder;
import com.yst.web.model.Store;
import com.yst.web.service.StoreService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;

@Service("storeService")
@Transactional
public class StoreServiceImpl implements StoreService {
	private static Log logger = LogFactory.getLog(StoreServiceImpl.class);
	@Resource(name = "storeDao")
	private StoreDao storeDao;

	@Resource(name = "merchantDao")
	private MerchantDao merchantDao;

	@Override
	public Store findById(int id) {
		return this.storeDao.get(Store.class, id);
	}

	@Override
	public List<Store> selectAll() {
		return this.storeDao.query(Store.class);
	}

	@Override
	public void deleteById(int id) {
		this.storeDao.delete(Store.class, id);
	}

	@Override
	public void update(Store store) {
		this.storeDao.update(store);
	}

	@Override
	public void add(Store store) {
		this.storeDao.save(store);
	}

	@Override
	public AppResult reg(Store store) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = store.getMerchant_id();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属商家id不能为空");
		} else {
			Merchant dbMerchant = this.merchantDao.get(Merchant.class,
					merchant_id);
			if (dbMerchant == null) {
				appResult.setError_info("所属商家id错误或未注册");
			} else {
				store.setMerchant(dbMerchant);
				this.storeDao.save(store);
				appResult.setError_info("申请成功");
				appResult.setResult(AppResult.SUCCESS);
			}
		}
		return appResult;
	}

	@Override
	public AppResult getStoreList(Store store) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer merchant_id = store.getMerchant_id();
		if (merchant_id == null || merchant_id.equals("")) {
			appResult.setError_info("所属商家id不能为空");
		} else {
			Merchant dbMerchant = this.merchantDao.get(Merchant.class,
					merchant_id);
			if (dbMerchant == null) {
				appResult.setError_info("所属商家id错误或未注册");
			} else {
				String hql = "from Store as o where o.merchant.merchant_id=?";
				PageModel pm = PageModelContext.getPageModel();
				List<ProductOrder> list = this.storeDao.query(hql, pm,
						merchant_id);
				if (list.size() == 0) {
					appResult.setError_info("所属商家没有添加店铺");
				} else {
					appResult.setMerchant_id(merchant_id);
					appResult.setPage_model(pm);
					appResult.setData(list);
					appResult.setError_info("获取列表成功");
					appResult.setResult(AppResult.SUCCESS);
				}
			}
		}
		return appResult;
	}

	@Override
	public AppResult getInfo(Store store) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer store_id = store.getStore_id();
		if (store_id == null || store_id.equals("")) {
			appResult.setError_info("店铺id不能为空");
		} else {
			Store dbStore = this.storeDao.get(Store.class, store_id);
			if (dbStore == null) {
				appResult.setError_info("店铺id错误或未注册");
			} else {
				appResult.setData(dbStore);
				appResult.setError_info("获取成功");
				appResult.setResult(AppResult.SUCCESS);
			}
		}
		return appResult;
	}

	@Override
	public AppResult updateInfo(Store store) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer store_id = store.getStore_id();
		if (store_id == null || store_id.equals("")) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		Store dbStore = this.storeDao.get(Store.class, store_id);
		if (dbStore != null) {
			BeanUtils.copy(store, dbStore);
			this.storeDao.update(dbStore);
			appResult.setData(dbStore);
			appResult.setResult(AppResult.SUCCESS);
			appResult.setError_info("修改成功");
		} else {
			appResult.setError_info("商店id错误或不存在");
		}
		return appResult;
	}

	@Override
	public List<Store> selectAllByPage() {
		String hql = "from Store as o";
		return this.storeDao.query(hql, PageModelContext.getPageModel(),
				Store.class, null);
	}

}
