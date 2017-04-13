package com.yst.web.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.VersionDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Version;
import com.yst.web.service.VersionService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Service("versionService")
@Transactional
public class VersionServiceImpl implements VersionService {
	private static Log logger = LogFactory.getLog(VersionServiceImpl.class);
	@Resource(name = "versionDao")
	private VersionDao versionDao;

	@Override
	public Version findById(int id) {
		return this.versionDao.get(Version.class, id);
	}

	@Override
	public List<Version> selectAll() {
		return this.versionDao.query(Version.class);
	}

	@Override
	public void deleteById(int id) {
		Version version =this.versionDao.get(Version.class, id);
		String sValue =version.getUrl();
		if (sValue != null && !sValue.equals("")) {
			File f =new File(ServerParam.ROOT_PATH+sValue.toString());
			boolean isDelete =f.delete();
			logger.debug("______________app_delete:"+isDelete);
		}
		this.versionDao.delete(version);
	}

	@Override
	public void update(Version download) {
		this.versionDao.update(download);
	}

	@Override
	public void add(Version download) {
		this.versionDao.save(download);
	}

	@Override
	public List<Version> selectAllByPage() {
		String hql = "from Version as o order by o.update_time desc";
		return this.versionDao
				.query(hql, PageModelContext.getPageModel(), null);
	}

	@Override
	public AppResult updateInfo(Version version) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer version_id = version.getVersion_id();
		String platform = version.getPlatform();
		appResult = BeanUtils.uploadImage(version, platform);
		if (!appResult.getResult().equals(AppResult.NO_IMAGE)) {
			if (appResult.getResult().equals(AppResult.FAILED)) {
				return appResult;
			}
		}
		version.setUrl(version.getImage());
		version.setUpdate_time(new Date());
		Version dbVersion = null;
		if (version_id != null && !version_id.equals("")) {
			dbVersion = this.versionDao.get(Version.class, version_id);
			if (dbVersion == null) {
				appResult.setError_info("id不存在或错误");
				return appResult;
			}
			BeanUtils.copy(version, dbVersion);
		} else {
			dbVersion = version;
		}
		this.versionDao.saveOrUpdate(dbVersion);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("成功");
		return appResult;
	}

	@Override
	public AppResult getNewApp(Version version) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		String platform = version.getPlatform();
		String app_name = version.getApp_name();
		if (platform == null || platform.equals("")) {
			appResult.setError_info("应用平台不能为空");
			return appResult;
		}
		if (app_name == null || app_name.equals("")) {
			appResult.setError_info("应用名称不能为空");
			return appResult;
		}
		String hql="from Version as o where o.platform=? and o.app_name=? order by o.update_time desc";
		Version dbVersion =(Version) this.versionDao.queryForObject(hql, platform,app_name);
		if(dbVersion==null){
			appResult.setError_info("无记录或参数错误");
			return appResult;
		}
		appResult.setData(dbVersion);
		appResult.setResult(AppResult.SUCCESS);
		appResult.setError_info("成功");
		return appResult;
	}
}
