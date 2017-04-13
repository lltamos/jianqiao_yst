package com.yst.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.ACL;
import com.yst.web.model.AppResult;
import com.yst.web.model.Menu;
import com.yst.web.model.Role;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.model.vo.TreeVO;
import com.yst.web.service.ResourceService;
import com.yst.web.service.RoleService;
import com.yst.web.service.impl.RoleServiceImpl;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction {
	private static Log logger = LogFactory.getLog(RoleAction.class);
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "resourceService")
	private ResourceService resourceService;
	private Role role = new Role();
	AppResult appResult = new AppResult();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;

	public void listAjax() {
		List<Role> list = this.roleService.queryList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(list);
		JSONUtils.sendJSON(appResult);
	}

	public String addPage() {
		return "add_page";
	}

	public void addAjax() {
		appResult = this.roleService.addRole(role);
		JSONUtils.sendJSON(appResult);
	}

	public String updatePage() {
		role = this.roleService.findRoleInfo(role.getRole_id());
		return "update_page";
	}

	public void updateAjax() {
		appResult = this.roleService.updateRole(role);
		JSONUtils.sendJSON(appResult);
	}

	public String add() {
		this.roleService.add(role);
		return SUCCESS;
	}

	public String delete() {
		this.roleService.deleteById(role.getRole_id());
		return SUCCESS;
	}

	public String update() {
		this.roleService.update(role);
		return SUCCESS;
	}

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		List<Role> roles = this.roleService.selectAll();
		ActionContext.getContext().put("roles", roles);
		return LIST;
	}

	public String resourcePage() {
		role = this.roleService.findRoleInfo(role.getRole_id());
		return "resource_page";
	}

	/**
	 * 显示树
	 * 
	 * @param menu
	 *            资源对象
	 */
	public void showRoleTree() {
		role = this.roleService.findRoleInfo(role.getRole_id());
		Map<String, Object> map = getResourceMap(role);
		TreeVO vo = this.resourceService.findRoot(map,"role");
		JSONUtils.sendJSON(vo);
	}

	private Map<String, Object> getResourceMap(Role role2) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<ACL> roleMenus = role2.getRoleMenus();
		if (roleMenus == null) {
			return map;
		}
		for (ACL acl : roleMenus) {
			Menu menu = acl.getMenu();
			map.put(menu.getMenu_id().toString(), menu);
		}
		return map;
	}
	public void resourceAjax(){
		appResult = this.roleService.updateResource(role);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public Role getModel() {
		// TODO Auto-generated method stub
		return role;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

}
