package com.yst.web.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.ResourceDao;
import com.yst.web.model.ACL;
import com.yst.web.model.AppResult;
import com.yst.web.model.Menu;
import com.yst.web.model.Role;
import com.yst.web.model.vo.TreeVO;
import com.yst.web.service.ResourceService;
import com.yst.web.utils.PageModelContext;

@Service("resourceService")
@Transactional
public class ResourceServiceImpl implements ResourceService {
	private static Log logger = LogFactory.getLog(ResourceServiceImpl.class);
	@Resource(name="resourceDao")
	private ResourceDao resourceDao;
	@Override
	public Menu findById(int id) {
		return this.resourceDao.get(Menu.class, id);
	}
	@Override
	public List<Menu> selectAll() {
		return this.resourceDao.query(Menu.class);
	}
	@Override
	public void deleteById(int id) {
		//先删除所有ACL中等于menu_id的记录
		this.resourceDao.deleteByColumnValue(ACL.class, "menu.menu_id", id);//删除所有ACL中等于role_id记录
		this.resourceDao.delete(Menu.class, id);
	}

	@Override
	public AppResult update(Menu menu) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		this.resourceDao.update(menu);
		appResult.setResult(AppResult.SUCCESS);// 成功
		return appResult;
	}

	@Override
	public AppResult add(Menu menu) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		this.resourceDao.save(menu);
		appResult.setResult(AppResult.SUCCESS);// 成功
		return appResult;
	}
	@Override
	public TreeVO findRoot(Map<String,Object> map) {
		Menu menu = this.resourceDao.getRoot();
		TreeVO tree = new TreeVO(menu,map);
		return tree;
	}
	
	@Override
	public TreeVO findRoot(Map<String,Object> map,String act) {
		Menu menu = this.resourceDao.getRoot();
		TreeVO tree = new TreeVO(menu,map,act);
		return tree;
	}
	@Override
	public List<Menu> selectAllByPage() {
		String hql ="from Menu as o order by o.order_index asc";
		return this.resourceDao.query(hql, PageModelContext.getPageModel(), null);
	}
	
}
