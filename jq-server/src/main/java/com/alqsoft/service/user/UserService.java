package com.alqsoft.service.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.easyui.EasyuiResult;
import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.UserTable;
import com.alqsoft.entity.user.User;
import com.alqsoft.utils.BootStrapResult;

/**
 * 用户管理业务
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-5 下午8:36:42
 * 
 */
public interface UserService extends BaseService<User> {
	/**
	 * 根据用户名查询用户
	 * 
	 * @param userName
	 * @return
	 */
	public User getUserByName(String userName); 
	
	public User getUserByNameByUnLocal(String userName);
	
	public User getUserByNameIsLocked(String userName);

	/**
	 * 分页查询数据
	 * 
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public EasyuiResult<List<User>> getUserPage(Map<String, Object> map, Integer page, Integer rows);

	/**
	 * 根据角色id查找用户
	 * 
	 * @param roleId
	 * @return
	 */
	public List<User> getUsersByRoleId(Long roleId);
	/**
	 * 获取验证码
	 * @param request
	 * @param response
	 * @param session
	 */
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session);
	/**
	 * 用户登录
	 * @param user
	 * @param imageCode 
	 * @param session
	 * @param request
	 * @return
	 */
	public Result login(User user, String imageCode, HttpSession session, HttpServletRequest request);
	/**
	 * 获取用户列表
	 * @param page
	 * @param length
	 * @param request
	 * @return
	 */
	public BootStrapResult<List<UserTable>> getUserList(Integer page, Integer length, HttpServletRequest request);
}
