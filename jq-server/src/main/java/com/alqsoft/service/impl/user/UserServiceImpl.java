package com.alqsoft.service.impl.user;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.easyui.EasyUtils;
import org.alqframework.easyui.EasyuiResult;
import org.alqframework.encrypt.MD5;
import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.user.UserDao;
import com.alqsoft.dao.usertable.UserTableDao;
import com.alqsoft.entity.UserTable;
import com.alqsoft.entity.user.User;
import com.alqsoft.service.user.UserService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;
import com.alqsoft.utils.SystemRole;

/**
 * 用户管理实体
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-6 下午10:53:50
 * 
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private UserTableDao userTableDao;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try{
			userDao.delete(arg0);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public User get(Long arg0) {
		return userDao.findOne(arg0);
	}

	@Override
	@Transactional
	public User saveAndModify(User arg0) {
		return userDao.save(arg0);
	}

	@Override
	public User getUserByName(String userName) {
		return userDao.getUserByName(userName);
	}

	@Override
	public EasyuiResult<List<User>> getUserPage(Map<String, Object> map, Integer page, Integer rows) {
		Map<String, SearchFilter> filter = SearchFilter.parse(map);
		Specification<User> specification = DynamicSpecifications.bySearchFilter(filter.values(),
				User.class);
		Page<User> userPage = userDao.findAll(specification, new PageRequest(page - 1, rows,
				new Sort(Direction.DESC, new String[] { "updateTime" })));
		return EasyUtils.returnPage(User.class, userPage);
	}

	@Override
	public List<User> getUsersByRoleId(Long roleId) {
		return userDao.getUsersByRoleId(roleId);
	}

	@Override
	public User getUserByNameIsLocked(String userName) {
		return userDao.getUserByNameIsLocked(userName);
	}

	@Override
	public User getUserByNameByUnLocal(String userName) {
		return userDao.getUserByNameByUnLocal(userName);
	}

	@Override
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		int width = 100, height = 25;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 22 * i + 6, 22);
		}
		// 将认证码存入SESSION
		session.setAttribute("sRand", sRand);
		g.dispose();
		try {
			ImageIO.write(image, "JPEG", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 生成随机的颜色
	 * 
	 * @return
	 */
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	@Override
	@Transactional
	public Result login(User user,String imageCode, HttpSession session, HttpServletRequest request) {
		String userName = user.getUserName();
		String password = user.getUserPassword();
		if(StringUtils.isBlank(userName)){

			return SpringMVCUtils.returnError("用户名不能为空!");
		}
		if(StringUtils.isBlank(password)){
			return SpringMVCUtils.returnError("密码不能为空!");
		}
		if(StringUtils.isBlank(imageCode)){
			return SpringMVCUtils.returnError("验证码不能为空!");
		}
		Object sRand=session.getAttribute("sRand");
		if(sRand==null){
			return SpringMVCUtils.returnError("验证码异常!");
		}
		if(!imageCode.equals(sRand)){
			return SpringMVCUtils.returnError("验证码错误！");
		}
		UserTable dbUser=userTableDao.getUserByName(userName);
		if(dbUser==null){
			return SpringMVCUtils.returnError("登录用户不存在！");
		}
		Integer isLocked=dbUser.getDeleted();
		if(isLocked==null || isLocked==1){
			return SpringMVCUtils.returnError("用户已禁用！");
		}
		String dbPassword = dbUser.getPassword();
		String passwordMD5 = MD5.getMD5ofStr(password);
		if (!dbPassword.equals(passwordMD5)) {
			return SpringMVCUtils.returnError("密码错误！");
		}
		
		
		dbUser.setImageCode(imageCode);
		userTableDao.save(dbUser);
		session.setAttribute(SystemRole.ADMIN.getName(), dbUser);
		session.setAttribute(SystemRole.SESSIONROLE.getName(), "admin");
		session.setAttribute("dbUser", dbUser);
		return SpringMVCUtils.returnSuccess("登录成功！");
	}

	@Override
	public BootStrapResult<List<UserTable>> getUserList(Integer page, Integer length, HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		Map<String, SearchFilter> filter = SearchFilter.parse(searchParams);
		Specification<UserTable> specification = DynamicSpecifications.bySearchFilter(filter.values(),UserTable.class);
		Page<UserTable> accountPage = userTableDao.findAll(specification, new PageRequest(page, length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(UserTable.class, accountPage);
	}
}
