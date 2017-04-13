package com.yst.web.action;

import java.io.IOException;
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

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.ACL;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Menu;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.model.vo.TreeVO;
import com.yst.web.service.ResourceService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("resourceAction")
@Scope("prototype")
public class ResourceAction extends BaseAction {
	private static Log logger = LogFactory.getLog(ResourceAction.class);
	@Resource(name = "resourceService")
	private ResourceService resourceService;
	private Menu menu = new Menu();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String add() {
		this.resourceService.add(menu);
		return SUCCESS;
	}

	public String delete() {
		this.resourceService.deleteById(menu.getMenu_id());
		return SUCCESS;
	}

	public String updatePage() {
		menu = this.resourceService.findById(menu.getMenu_id());
		List<Menu> list = this.resourceService.selectAll();
		ActionContext.getContext().put("menus", list);
		return "update_page";
	}

	public String addPage() {
		List<Menu> list = this.resourceService.selectAll();
		ActionContext.getContext().put("menus", list);
		return "add_page";
	}

	public String update() {
		this.resourceService.update(menu);
		return SUCCESS;
	}

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}

	@Override
	public Menu getModel() {
		return menu;
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

	public void listAjax() {
		List<Menu> menus = this.resourceService.selectAllByPage();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(menus);
		JSONUtils.sendJSON(appResult);
	}

	/**
	 * 显示树
	 * 
	 * @param menu
	 *            资源对象
	 */
	public void showTree() {
		User user = (User) session.get(ServerParam.USER_SESSION);
		if (user != null) {
			logger.debug(user.getLogin_name());
			Map<String, Object> map = getResourceMap(user);
			if(map==null){
				try {
					response.getWriter().print("<script type='text/javascript'>alert('登录超时，请重新登录');</script>");
					response.sendRedirect("user!loginPage");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			logger.debug(new Gson().toJson(map));
			TreeVO vo = this.resourceService.findRoot(map);
			JSONUtils.sendJSON(vo);
		}
	}

	private Map<String, Object> getResourceMap(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		Set<UserRole> roleUsers = user.getRoleUsers();
		if (roleUsers == null) {
			return map;
		}
		for (UserRole userRole : roleUsers) {
			Set<ACL> roleMenus = userRole.getRole().getRoleMenus();
			if (roleMenus == null) {
				break;
			}
			for (ACL acl : roleMenus) {
				Menu menu = acl.getMenu();
				map.put(menu.getMenu_id().toString(), menu);
			}
		}
		return map;
	}

	/**
	 * 修改菜单信息
	 * 
	 * @param menu
	 *            资源对象
	 */
	public void updateAjax() {
		if (menu.getParent().getMenu_id() == 0)
			menu.setParent(null);
		appResult = this.resourceService.update(menu);
		JSONUtils.sendJSON(appResult);
	}

	/**
	 * 添加菜单信息
	 * 
	 * @param menu
	 *            资源对象
	 */
	public void addAjax() {
		appResult = this.resourceService.add(menu);
		JSONUtils.sendJSON(appResult);
	}

}
