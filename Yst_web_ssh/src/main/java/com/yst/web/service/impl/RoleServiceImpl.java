package com.yst.web.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.RoleDao;
import com.yst.web.model.ACL;
import com.yst.web.model.AppResult;
import com.yst.web.model.Menu;
import com.yst.web.model.PageModel;
import com.yst.web.model.Role;
import com.yst.web.model.UserRole;
import com.yst.web.service.RoleService;
import com.yst.web.utils.PageModelContext;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {
	private static Log logger = LogFactory.getLog(RoleServiceImpl.class);
	@Resource(name = "roleDao")
	private RoleDao roleDao;

	@Override
	public Role findById(int id) {
		return this.roleDao.get(Role.class, id);
	}

	@Override
	public List<Role> selectAll() {
		return this.roleDao.query(Role.class);
	}

	@Override
	public void deleteById(int id) {
		this.roleDao.deleteByColumnValue(ACL.class, "role.role_id", id);//删除所有ACL中等于role_id记录
		this.roleDao.delete(Role.class, id);
	}

	@Override
	public void update(Role role) {
		this.roleDao.update(role);
	}

	@Override
	public void add(Role role) {
		this.roleDao.save(role);
	}

	@Override
	public List<Role> queryList() {
		String hql = "from Role as ds ";
		PageModel pm = PageModelContext.getPageModel();
		List<Role> roleList = this.roleDao.query(hql, pm, null);
		return roleList;
	}

	@Override
	public Role findRoleInfo(Integer role_id) {
		return this.roleDao.findByColumnValue(Role.class, "role_id", role_id);
	}

	@Override
	public AppResult updateRole(Role role) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.roleDao.update(role);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult addRole(Role role) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		this.roleDao.save(role);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult updateResource(Role role) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String[] checkeds = role.getCheckeds().split(",");
		Integer role_id = role.getRole_id();
		this.roleDao.deleteByColumnValue(ACL.class, "role.role_id", role_id);//删除所有ACL中等于role_id记录
		Role dbRole = this.roleDao.get(Role.class, role_id);
		Set<ACL> roleMenus = new HashSet<ACL>();
		for (String id : checkeds) {
			if(id.equals("1")){//根节点不需添加
				continue;
			}
			ACL acl = new ACL();
			acl.setRole(dbRole);
			acl.setMenu(this.roleDao.get(Menu.class, Integer.parseInt(id)));
			roleMenus.add(acl);
		}
		dbRole.setRoleMenus(roleMenus);
		this.roleDao.saveOrUpdate(dbRole);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

}
