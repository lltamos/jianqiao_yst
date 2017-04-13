package com.yst.web.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.Advertise;
import com.yst.web.model.AppResult;
import com.yst.web.model.Product;
import com.yst.web.model.ProductType;
import com.yst.web.model.Role;
import com.yst.web.model.User;
import com.yst.web.model.UserRole;
import com.yst.web.service.AdvertiseService;
import com.yst.web.service.ProductService;
import com.yst.web.service.ProductTypeService;
import com.yst.web.service.RoleService;
import com.yst.web.service.UserService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.MD5;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ResultInfo;
import com.yst.web.utils.ServerParam;
import com.yst.web.utils.SubmitResultInfo;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends BaseAction {
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name = "productTypeService")
	private ProductTypeService productTypeService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "advertiseService")
	private AdvertiseService advertiseService;
	private User user = new User();
	// 注入session，request，response
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;

	AppResult appResult = new AppResult();
	public String logout(){
		session.remove(ServerParam.USER_SESSION);
		session.remove(ServerParam.CUSTOMER_SESSION);
		return "login_page";
	}
	/**
	 * 用户登陆
	 * 
	 * @param imageCode
	 *            验证码信息
	 * @return
	 */
	public void login() {
		// 获取Session
		// 得到用户名和密码
		String username = user.getLogin_name();
		String password = user.getPassword();
		String imageCode = user.getImageCode();
		SubmitResultInfo resultInfo =this.userService.login(username, password,imageCode,session);
		JSONUtils.sendJSON(resultInfo);
	}
	
	public String index() {
		return "index";
	}
	public String customerServicePage() {
		return "customer_service_page";
	}
	public String loginPage() {
		return "login_page";
	}
	public String showPage() {
		List<ProductType> productTypeList =this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypeList", productTypeList);
		List<Product> productList =this.productService.selectAllByPage();
		ActionContext.getContext().put("productList", productList);
		List<Advertise> advertList =this.advertiseService.selectAll();
		ActionContext.getContext().put("advertList", advertList);
		return "show_page";
	}
	public String addPage() {
		return "add_page";
	}

	public String add() {
		this.userService.add(user);
		return "index";
	}

	public String delete() {
		this.userService.deleteById(user.getUser_id(), user.getDeleted());
		return SUCCESS;
	}

	public String updatePage() {
		List<Role> roles = this.roleService.selectAll();
		ActionContext.getContext().put("rolelist", roles);
		user = this.userService.findById(user.getUser_id());
		setRoles(user);
		return "update_page";
	}
	public String updateRolePage() {
		List<Role> roles = this.roleService.selectAll();
		ActionContext.getContext().put("rolelist", roles);
		user = this.userService.findById(user.getUser_id());
		setRoles(user);
		return "update_role_page";
	}
	public String update() {
		this.userService.updateInfo(user);
		return SUCCESS;
	}

	public String search() {
		List<User> users = this.userService.selectByName(user.getLogin_name());
		ActionContext.getContext().put("users", users);
		return LIST;
	}

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}

	public void getVerifyCode() throws Exception {
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
		this.session.put("sRand", sRand);
		g.dispose();
		ImageIO.write(image, "JPEG", response.getOutputStream());
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

	public void listAjax() {
		List<User> users = this.userService.selectAll();
		setRoles(users);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(users);
		JSONUtils.sendJSON(appResult);
	}

	public void updateAjax() {
		appResult=this.userService.updateInfo(user);
		JSONUtils.sendJSON(appResult);
	}

	public void addAjax() {
		ResultInfo ri = new ResultInfo();
		ri.setType(ResultInfo.TYPE_RESULT_FAIL);
		SubmitResultInfo resultInfo = new SubmitResultInfo(ri);
		// 获取Session

		response.setContentType("text/html;charset=utf-8");
		// 得到用户名和密码
		String username = user.getLogin_name();
		String password = user.getPassword();
		if (StringUtils.isEmptyOrWhitespaceOnly(username)
				|| StringUtils.isEmptyOrWhitespaceOnly(password)) {

		} else {
			// 根据用户名查询用户信息
			User dbUser = userService.selectByUserName(username);
			if (dbUser != null) {
				ri.setMessage("用户名已存在");
			} else {
				// 验证密码
				String passwordMD5 = MD5.getMD5ofStr(password);
				this.userService.add(user);
				session.put(ServerParam.USER_SESSION, user);
				ri.setType(ResultInfo.TYPE_RESULT_SUCCESS);
				ri.setMessage("注册成功");
				resultInfo.setUrl("user.action");
			}
		}
		JSONUtils.sendJSON(resultInfo);
	}
	public void updateRole(){
		appResult =this.userService.updateRole(user);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public User getModel() {
		return user;
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
	public List<User> setRoles(List<User> users) {
		for (User user : users) {// 手动添加角色列表...
			List<Map<String,Object>> roles = new ArrayList<Map<String,Object>>();
			for (UserRole userRole : user.getRoleUsers()) {
				Map<String,Object> map =  new HashMap<String,Object>();
				map.put("id", userRole.getRole().getRole_id());
				map.put("role_name", userRole.getRole().getRole_name());
				map.put("role_des", userRole.getRole().getDes());
				roles.add(map);
			}
			user.setRoles(roles);
		}
		return users;
	}

	public User setRoles(User user) {
		// 手动添加角色列表...
		List<Map<String,Object>> roles = new ArrayList<Map<String,Object>>();
		for (UserRole userRole : user.getRoleUsers()) {
			Map<String,Object> map =  new HashMap<String,Object>();
			map.put("id", userRole.getRole().getRole_id());
			map.put("role_name", userRole.getRole().getRole_name());
			map.put("role_des", userRole.getRole().getDes());
			roles.add(map);
		}
		user.setRoles(roles);
		return user;
	}
	

	
	/**
	 * 获取登录状态
	 */
	public void getSession(){
		boolean flg =true;
		session.remove(ServerParam.BACK_URL);
		User dbUser=(User) session.get(ServerParam.USER_SESSION);
		if(dbUser==null){
			String uri=request.getRequestURI();
			uri=uri.replace("user!getSession", "");
			String url=user.getUrl();
			url=url.replace(uri, "");
			url=url.replaceAll("&", "%26");
			session.put(ServerParam.BACK_URL, url);
			flg= false;
		}
		JSONUtils.sendJSON(flg);
	}
}
