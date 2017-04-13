package com.alqsoft.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.alqframework.bean.MyBeanUtils;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.UserTable;
import com.alqsoft.entity.role.Role;
import com.alqsoft.entity.user.User;
import com.alqsoft.model.Permission;
import com.alqsoft.service.role.RoleService;
import com.alqsoft.service.user.UserService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 用户控制器
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-7-26 下午8:01:14
 * 
 */
@RequestMapping("user")
@Controller
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private StandardPasswordEncoder passwordEncoder;
	
	
	@RequestMapping("user-password")
	public String userPassword() {
		return "user/user-password";
	}
	/**
	 * 注销
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("user-logout")
	public String userLogout(Model model,
			HttpServletRequest request,
			HttpSession session) {
		session.removeAttribute((String) session.getAttribute(SystemRole.SESSIONROLE.getName()));
		/*session.removeAttribute((String) session.getAttribute(SystemRole.DOCTOR.getName()));
		session.removeAttribute((String) session.getAttribute(SystemRole.HOSPITAL.getName()));
		session.removeAttribute((String) session.getAttribute(SystemRole.RECOMMENDER.getName()));
		session.removeAttribute((String) session.getAttribute(SystemRole.ADMIN.getName()));*/
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute("dbUser");
		session.removeAttribute(SystemRole.SESSIONROLE.getName());
		return "user/user-login-page";
	}

	@RequestMapping("user-info")
	public String userInfo(){
		return "user/user-info";
	}
	
	
	/**
	 * 用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping("user-list")
	@Permission(SystemRole.ADMIN)
	public String userList(Model model) {
		return "user/user-list";
	}

	/**
	 * 新增或者修改页面的视图
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("user-input")
	public String userInput(Model model, @RequestParam(value = "id", required = false) Long id,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User systemUser = (User) session.getAttribute("user");
		User user = null;
		if (id != null) {
			user = userService.get(id);
			if (user != null)
				model.addAttribute(user);
		}
		List<Role> roles = roleService.getRoleAll();
		if (systemUser.getRole().getId() != 1) {
			CollectionUtils.filter(roles, new Predicate() {

				@Override
				public boolean evaluate(Object object) {
					Role role = (Role) object;
					return role.getId() != 1;
				}
			});
		}
		model.addAttribute("roles", roles);
		return "user/user-input";
	}

	/**
	 * 新增和修改用户信息
	 * 
	 * @param user
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("user-add")
	@ResponseBody
	public Result userAdd(@Valid User user, BindingResult bindingResult) {
		// 判断角色
		if (user.getRole().getId() == null) {
			return SpringMVCUtils.returnError("请选择角色");
		}
		// 验证判断是否有错
		if (!bindingResult.hasErrors()) {
			User user2 = userService.getUserByName(user.getUserName());
			if (user.getId() == null) {
				if (user2 != null) {
					return SpringMVCUtils.returnError("您新增的用户名已经重复，请重新输入");
				} else {
					// 设置角色
					user.setRole(roleService.get(user.getRole().getId()));
					user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
					// 保存用户
					if (userService.saveAndModify(user)!=null) {
						return SpringMVCUtils.returnSuccess("用户保存成功");
					} else {
						return SpringMVCUtils.returnError("系统错误，请联系管理员");
					}
				}
			} else {
				user.setTimes(user2.getTimes());
				MyBeanUtils.propertyUtils(user2, user);
				// 设置角色
				user2.setRole(roleService.get(user.getRole().getId()));
				user2.setUserPassword(passwordEncoder.encode(user2.getUserPassword()));
				// 保存用户
				if (userService.saveAndModify(user2)!=null) {
					return SpringMVCUtils.returnSuccess("用户保存成功");
				} else {
					return SpringMVCUtils.returnError("系统错误，请联系管理员");
				}
			}

		} else {
			return SpringMVCUtils.returnError(bindingResult);
		}
	}

	/**
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("user-delete")
	@ResponseBody
	public Result userDelete(@RequestParam("id") String id) {
		String[] ids = StringUtils.split(id, ",");
		try {
			for (String string : ids) {
				userService.delete(Long.parseLong(string));
			}
			return SpringMVCUtils.returnSuccess("删除用户成功");
		} catch (NumberFormatException e) {
			return SpringMVCUtils.returnError("删除用户失败");
		}
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("user-code")
	public void userCode(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		this.userService.getVerifyCode(request,response,session);
	}
	/**
	 * 管理员登录
	 * @param model
	 * @param user
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("user-login")
	@ResponseBody
	public Result login(Model model,User user,@RequestParam("imageCode")String imageCode,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute(SystemRole.SESSIONROLE.getName());
		session.removeAttribute(SystemRole.HOSPITAL.getName());
		session.removeAttribute(SystemRole.RECOMMENDER.getName());
		session.removeAttribute(SystemRole.DOCTOR.getName());
		session.removeAttribute("nameAuthentication");
		return this.userService.login(user,imageCode,session,request);
	}
	
	/**
	 * 后台首页
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("user-index")
	public String userIndex(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(session.getAttribute(SystemRole.SESSIONROLE.getName())==null){
			return "user/user-login-page";
		}
		return "user/user-index";
	}
	/**
	 * 跳转登录页面---在跳转登陆页面的时候将session中存在的信息全部销毁包括pc的session
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("user-login-page")
	public String userLoginPage(Model model,
			HttpServletRequest request,
			HttpSession session) {
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute(SystemRole.SESSIONROLE.getName());
		session.removeAttribute(SystemRole.HOSPITAL.getName());
		session.removeAttribute(SystemRole.RECOMMENDER.getName());
		session.removeAttribute(SystemRole.DOCTOR.getName());
		session.removeAttribute("nameAuthentication");
		return "user/user-login-page";
	}
	
	/**
	 * 管理员列表分页
	 * @param model
	 * @param user
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("user-list-data")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<UserTable>> userListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		Integer page = start / length;
		return this.userService.getUserList(page,length,request);
	}
}
