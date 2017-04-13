package com.alqsoft.service.impl.customer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.rpc.RpcUpdateCustormerService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.utils.CommUtils;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.weixin.wxpay.MD5Util;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午3:53:33
 * 
 */
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private RpcUpdateCustormerService rpcUpdateCustormerService;
	@Autowired
	private MobileCheck mobileCheck;

	@Override
	public Customer getCustomerById(long cid) {

		return customerDao.getCustomerById(cid);
	}

	@Override
	public Result userLogin(String phone, String password, HttpServletRequest request, String imageCode,
			HttpSession session) {
		if (CommUtils.isNull(phone)) {
			return ResultUtils.returnError("手机号不能为空!");
		}
		// "^1[34578]\\d{9}$"; //^[0-9]*$
		String regExp = "^1[34578]\\d{9}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(phone);
		if (m.find() == false) {
			return ResultUtils.returnError("请输入正确的手机号!");
		}
		if (CommUtils.isNull(password)) {
			return ResultUtils.returnError("密码不能为空!");
		}
		Object sRand = session.getAttribute("sRand");
		if (sRand == null) {
			return ResultUtils.returnError("验证码异常!");
		}
		if (!imageCode.equals(sRand)) {
			return ResultUtils.returnError("验证码错误！");
		}
		Customer customer = customerDao.getCustomerByPhone(phone);
		if (CommUtils.isNull(customer)) {
			return ResultUtils.returnError("账号不存在!");
		}
		if (customer.getDeleted() == null) {
			customer.setDeleted(0);
		}
		if (customer.getDeleted().intValue() == 1) {
			return ResultUtils.returnError("账号已禁用!");
		}
		String md5Encode = MD5Util.MD5Encode(password, "utf-8");
		if (!md5Encode.equals(customer.getPassword())) {
			return ResultUtils.returnError("密码错误!");
		}
		request.getSession().setAttribute("customer", customer);
		Date date = new Date();
		customer.setLastLoginTime(date);
		customer = rpcUpdateCustormerService.updateLoginTime(customer);
		if (customer == null) {
			return ResultUtils.returnError("登陆失败");
		}
		return ResultUtils.returnSuccess("登录成功!");
	}

	@Override
	public Result userChangePassWd(Long id, String oldPassWd, String newstr1, String newstr2, HttpSession session) {

		boolean c = StringUtils.isAnyBlank(newstr1, newstr2, oldPassWd);

		if (c) {

			return ResultUtils.returnError("修改密码失败,请正确");

		}

		boolean b = StringUtils.equals(newstr1, newstr2);

		if (b) {

			Customer customer = customerDao.getCustomerById(id);

			String password = customer.getPassword();

			String md5passwd = MD5Util.MD5Encode(oldPassWd, "utf-8");

			if (StringUtils.equals(md5passwd, password)) {

				int updatePasswd = rpcUpdateCustormerService.updatePasswd(id, MD5Util.MD5Encode(newstr1, "utf-8"));

				if (updatePasswd > 0) {
					customer.setPassword(MD5Util.MD5Encode(newstr1, "utf-8"));
					session.setAttribute("customer", customer);
					return ResultUtils.returnSuccess("修改密码成功!");
				} else {

					return ResultUtils.returnError("修改密码失败，请稍候再试!");
				}

			} else {

				return ResultUtils.returnError("修改密码失败，旧密码输入错误!");
			}

		} else {

			return ResultUtils.returnError("两次输入不一致!");
		}

	}

	@Override
	public Result userChangePhone(Long id, String phone, String code, HttpSession session) {

		boolean c = StringUtils.isAnyBlank(phone, code);

		if (c) {

			return ResultUtils.returnError("修改手机号失败,请正确输入！");
		}
		Customer customerByPhone = customerDao.getCustomerByPhone(phone);

		if (customerByPhone != null) {
			return ResultUtils.returnError("该手机号已注册！");
		}

		Customer customer = customerDao.getCustomerById(id);

		if (customer == null) {
			return ResultUtils.returnError("用户不存在！");
		}
		// 验证手机验证码

		Result checkMsg = mobileCheck.checkMsg(phone, code, "JQ2017031606");

		if (checkMsg.getCode() == 1) {

			int m = rpcUpdateCustormerService.updatePhone(id, phone);
			if (m > 0) {
				customer.setPhone(phone);
				session.setAttribute("customer", customer);
				return ResultUtils.returnSuccess("修改手机号成功！");
			}

		} else {
			return ResultUtils.returnError("验证码验证失败！");
		}

		return ResultUtils.returnError("修改手机号失败,请正确输入！");

	}

	@Override
	public void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		int width = 100, height = 25;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
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
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
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
	public Customer getCustomerByPhone(String phone,HttpServletRequest request,
			HttpSession session) {
		Customer customer = customerDao.getCustomerByPhone(phone);
		request.getSession().setAttribute("customer", customer);
		Date date = new Date();
		customer.setLastLoginTime(date);
		customer = rpcUpdateCustormerService.updateLoginTime(customer);
		return customer;
	}

	@Override
	public Customer getCustomer(String phone) {
		return customerDao.getCustomerByPhone(phone);
	}

}
