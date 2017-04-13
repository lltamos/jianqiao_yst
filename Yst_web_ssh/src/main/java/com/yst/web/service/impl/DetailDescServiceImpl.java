package com.yst.web.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DetailDescDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DetailDesc;
import com.yst.web.model.Doctor;
import com.yst.web.model.Product;
import com.yst.web.service.DetailDescService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.FreemarkerUtil;
import com.yst.web.utils.ServerParam;

@Service("detailDescService")
@Transactional
public class DetailDescServiceImpl implements DetailDescService {
	private static Log logger = LogFactory.getLog(DetailDescServiceImpl.class);
	@Resource(name = "detailDescDao")
	private DetailDescDao detailDescDao;

	@Override
	public AppResult getObjectInfo(DetailDesc detailDesc) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer desc_type = detailDesc.getDesc_type();
		Integer main_id = detailDesc.getMain_id();
		if (main_id == null || main_id.equals("")) {
			appResult.setError_info("主体id不能为空");
			return appResult;
		}
		if (desc_type == null || desc_type.equals("")
				|| (desc_type != 1 && desc_type != 2)) {
			appResult.setError_info("信息类型为空或错误");
			return appResult;
		}
		String hql = "from DetailDesc as o where o.main_id=? and o.desc_type=? order by o.desc_index asc";
		List<DetailDesc> dbDetailDescList = this.detailDescDao.query(hql, null,
				main_id, desc_type);
		if (dbDetailDescList.size() == 0) {
			appResult.setError_info("详情不存在或id错误");
			return appResult;
		} else {
			appResult.setData(dbDetailDescList);
			appResult.setError_info("获取成功");
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}

	@Override
	public AppResult saveOrUpdateInfo(DetailDesc detailDesc) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer desc_id = detailDesc.getDesc_id();
		DetailDesc dbDetailDesc = null;
		appResult = BeanUtils.uploadImage(detailDesc, "detalDesc");
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		if (desc_id != null && !desc_id.equals("")) {
			dbDetailDesc = this.detailDescDao.get(DetailDesc.class, desc_id);
			if (dbDetailDesc == null) {
				appResult.setError_info("desc_id错误");
				return appResult;
			}
			BeanUtils.copy(detailDesc, dbDetailDesc);
		} else {
			dbDetailDesc = detailDesc;
		}
		this.detailDescDao.saveOrUpdate(dbDetailDesc);
		appResult.setError_info("保存成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult deleteInfo(DetailDesc detailDesc) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer desc_id = detailDesc.getDesc_id();
		if (desc_id == null || desc_id.equals("")) {
			appResult.setError_info("id不能为空");
			return appResult;
		}
		DetailDesc dbDetailDesc = this.detailDescDao.get(DetailDesc.class,
				desc_id);
		if (dbDetailDesc == null) {
			appResult.setError_info("id不存在或错误");
			return appResult;
		}
		this.detailDescDao.delete(dbDetailDesc);
		appResult.setError_info("删除成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult updateOrder(DetailDesc detailDesc) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String order = detailDesc.getOrder();
		if (order == null || order.equals("")) {
			appResult.setError_info("排序字符串不能为空");
			return appResult;
		}
		String[] orders = order.split(",");
		for (String str : orders) {
			String[] id_indexs = str.split(":");
			if (id_indexs.length != 2) {
				appResult.setError_info("格式错误:无法获取id,index");
				return appResult;
			}
			Integer id = Integer.parseInt(id_indexs[0]);
			Integer index = Integer.parseInt(id_indexs[1]);
			DetailDesc dbDetailDesc = this.detailDescDao.get(DetailDesc.class,
					id);
			if (dbDetailDesc == null) {
				appResult.setError_info("id不存在或错误");
				return appResult;
			}
			dbDetailDesc.setDesc_index(index);
			this.detailDescDao.update(dbDetailDesc);
		}
		appResult.setError_info("更新成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AppResult addShowPage(DetailDesc detailDesc) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer desc_type = detailDesc.getDesc_type();
		Integer main_id = detailDesc.getMain_id();
		if (main_id == null || main_id.equals("")) {
			appResult.setError_info("主体id不能为空");
			return appResult;
		}
		if (desc_type == null || desc_type.equals("")
				|| (desc_type != 1 && desc_type != 2)) {
			appResult.setError_info("信息类型为空或错误");
			return appResult;
		}
		Product dbProduct =null;
		Doctor dbDoctor =null;
		if(desc_type==1){
			dbDoctor =this.detailDescDao.get(Doctor.class, main_id);
			if(dbDoctor==null){
				appResult.setError_info("医生不存在或id错误");
				return appResult;
			}
		}else if(desc_type==2){
			dbProduct =this.detailDescDao.get(Product.class, main_id);
			if(dbProduct==null){
				appResult.setError_info("服务包不存在或id错误");
				return appResult;
			}
		}else{
			appResult.setError_info("信息类型无效");
			return appResult;
		}
		String hql = "from DetailDesc as o where o.main_id=? and o.desc_type=? order by o.desc_index asc";
		List<DetailDesc> dbDetailDescList = this.detailDescDao.query(hql, null,
				main_id, desc_type);
		if (dbDetailDescList.size() == 0) {
			appResult.setError_info("详情不存在或id错误");
			return appResult;
		} else {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("dbDescList", dbDetailDescList);
			String url=FreemarkerUtil.make("html2.ftl", "UTF-8", map);
			if(desc_type==2){
				if(dbProduct.getDetail_url()!=null && !dbProduct.getDetail_url().equals("") ){//删除已存在页面
					File f =new File(ServerParam.ROOT_PATH+dbProduct.getDetail_url());
					boolean isDelete =f.delete();
					logger.debug("______________html_delete:"+isDelete);
				}
				dbProduct.setDetail_url(url);
				this.detailDescDao.saveOrUpdate(dbProduct);
			}
			logger.debug(url);
			appResult.setData(url);
			appResult.setError_info("获取成功");
			appResult.setResult(AppResult.SUCCESS);
		}
		return appResult;
	}
}
