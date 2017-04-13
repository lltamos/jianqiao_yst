package com.yst.web.service.impl;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.yst.web.dao.UserDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Role;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.service.UserService;
import com.yst.web.utils.MD5;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ResultInfo;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.SubmitResultInfo;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	private static Log logger = LogFactory.getLog(UserServiceImpl.class);
	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	public User findById(int id) {
		return this.userDao.get(User.class, id);
	}

	@Override
	public List<User> selectAll() {
		String hql = "from User as o";
		return this.userDao.query(hql, PageModelContext.getPageModel(),
				User.class, null);
	}

	@Override
	public void deleteById(int id, int deleted) {
		User user = this.userDao.load(User.class, id);
		user.setDeleted(deleted);
		this.userDao.update(user);
	}

	@Override
	public AppResult updateInfo(User user) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		User dbUser = this.userDao.get(User.class, user.getUser_id());
		dbUser.setPassword(MD5.getMD5ofStr(user.getPassword()));
		dbUser.setUpdate_time(new Date(System.currentTimeMillis()));
		this.userDao.update(dbUser);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public void add(User user) {
		String password = user.getPassword();
		String md5Password = MD5.getMD5ofStr(password);
		user.setPassword(md5Password);
		user.setCreate_time(new Date(System.currentTimeMillis()));
		this.userDao.save(user);
	}

	@Override
	public SubmitResultInfo login(String username, String password, String imageCode,Map session) {

		ResultInfo ri = new ResultInfo();
		ri.setType(ResultInfo.TYPE_RESULT_FAIL);
		SubmitResultInfo resultInfo = new SubmitResultInfo(ri);
		// 检查输入是否正确
		if (StringUtils.isEmptyOrWhitespaceOnly(username)
				|| StringUtils.isEmptyOrWhitespaceOnly(password)) {
			System.out.println(username + "_____________" + password);
			ri.setMessage("用户名或密码错误！");
		} else if (StringUtils.isEmptyOrWhitespaceOnly(imageCode)) {
			ri.setMessage("验证码为空！");
		} else if (!imageCode.equals(session.get("sRand"))) {
			ri.setMessage("验证码错误！");
		} else {
			// 根据用户名查询用户信息
			User dbUser = this.selectByUserName(username);
			if (dbUser == null) {
				ri.setMessage("用户名或密码错误");
			} else {
				// 验证密码
				String dbPassword = dbUser.getPassword();
				String passwordMD5 = MD5.getMD5ofStr(password);
				if (dbPassword.equals(passwordMD5)) {
					Integer status = dbUser.getDeleted();
					if (AppResult.STATUS_ENABLED.equals(status)) {
						session.put(ServerParam.USER_SESSION, dbUser);
						Customer dbCustomer =this.userDao.findByColumnValue(Customer.class, "phone", username);
						if(dbCustomer!=null){
							session.put(ServerParam.CUSTOMER_SESSION, dbCustomer);
						}
						ri.setType(ResultInfo.TYPE_RESULT_SUCCESS);
						ri.setMessage("登陆成功");
						resultInfo.setUrl("user!index");
					} else {
						ri.setMessage("账号已被禁用");
					}
				} else {  
					ri.setMessage("用户名或密码错误");
				}
			}
		}
		return resultInfo;
	}

	@Override
	public List<User> selectByName(String username) {
		return this.userDao.selectByColumnLike(User.class, "login_name",
				username);
	}

	@Override
	public User selectByUserName(String username) {
		return this.userDao.findByColumnValue(User.class, "login_name",
				username);
	}

	@Override
	public int count() {
		return this.userDao.count(User.class);
	}

	@Override
	public AppResult updateRole(User user) {
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		String[] checkeds = user.getCheckeds().split(",");
		Integer user_id = user.getUser_id();
		this.userDao.deleteByColumnValue(UserRole.class, "user.user_id",
				user_id);// 删除所有UserRole中等于user_id记录
		User dbUser = this.userDao.get(User.class, user_id);
		Set<UserRole> roleUsers = new HashSet<UserRole>();
		for (String id : checkeds) {
			UserRole userRole = new UserRole();
			userRole.setUser(dbUser);
			userRole.setRole(this.userDao.get(Role.class, Integer.parseInt(id)));
			roleUsers.add(userRole);
		}
		dbUser.setRoleUsers(roleUsers);
		this.userDao.saveOrUpdate(dbUser);
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}
}
