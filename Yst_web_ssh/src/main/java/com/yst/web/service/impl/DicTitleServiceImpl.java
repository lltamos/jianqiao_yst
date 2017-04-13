package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.DicTitleDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicTitle;
import com.yst.web.model.PageModel;
import com.yst.web.service.DicTitleService;
import com.yst.web.utils.PageModelContext;
@Service("dicTitleService")
@Transactional
public class DicTitleServiceImpl implements DicTitleService{
	private static Log logger = LogFactory.getLog(DicTitleServiceImpl.class);
	@Resource(name = "dicTitleDao")
	private DicTitleDao dicTitleDao;
	
	@Override
	public List<DicTitle> selectAll() {
		return this.dicTitleDao.query(DicTitle.class);
	}

	
	@Override
	public List<DicTitle> queryList() {
		String hql = "from DicTitle as d ";
		PageModel pm = PageModelContext.getPageModel();
		List<DicTitle> dicTitleList = this.dicTitleDao.query(hql, pm, null);
		return dicTitleList;
	}
	@Override
	public DicTitle findDicTitleInfo(Integer title_id) {
		DicTitle dicTitle = this.dicTitleDao.findByColumnValue(DicTitle.class, "title_id", title_id);
		return dicTitle;
	}
	@Override
	public AppResult updateDicTitle(DicTitle dicTitle) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicTitleDao.update(dicTitle);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
	@Override
	public void deleteDicTitle(Integer title_id) {
		this.dicTitleDao.delete(DicTitle.class, title_id);
	}
	
	@Override
	public AppResult addDicTitle(DicTitle dicTitle) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.dicTitleDao.save(dicTitle);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
}
