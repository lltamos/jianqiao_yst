package com.alqsoft.controller.manage;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.alqframework.easyui.EasyuiResult;
import org.alqframework.pay.weixin.util.MD5Util;
import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.manager.Manager;
import com.alqsoft.service.manage.ManageService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年10月13日 下午4:46:49
 * 
 */
@RequestMapping("manage")
@Controller
public class ManageContriller {

	@Autowired
	private ManageService manageService;
	
	/**
	 * 跳转试图展示页面
	 * @return
	 */
	@RequestMapping("manage-list")
	public String manageList(){
		return "manage/manage-list";
	}
	
	/**
	 * 跳转试图添加和修改页面
	 * @return
	 */
	@RequestMapping("manage-input")
	public String manageInput(Model model, @RequestParam(value = "id", required = false) Long id){
		Manager manage = null;
		if(id != null){
			manage = manageService.get(id);
			if(manage != null){
				model.addAttribute("manage", manage);
			}
		}
		return "manage/manage-input";
	}
	
	/**
	 * 查询普惠管理员信息
	 * @param page
	 * @param rows
	 * @param request
	 * @return
	 */
	@RequestMapping("manage-list-data")
	@ResponseBody
	public EasyuiResult<List<Manager>> manageListData(@RequestParam("page") Integer page,
			@RequestParam("rows") Integer rows, HttpServletRequest request){
		return manageService.getManageListAll(page, rows);
	}
	
	/**
	 * 添加和修改普惠管理员信息
	 * @param manager
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("manage-add")
	@ResponseBody
	public Result manageAdd(@Valid Manager manager,BindingResult bindingResult) {
		if (!bindingResult.hasErrors()) {
			Manager manager2 = manageService.getManageByAccount(manager.getAccount());
			if (manager.getId() == null) {
				if(manager2 != null) {
					return SpringMVCUtils.returnError("该用户名已存在，请重新输入");
				} else {
					String pwd = manager.getPassword();
					String password1 = MD5Util.MD5Encode(pwd, "utf-8");
					manager.setPassword(password1);
					if (manageService.saveAndModify(manager) != null) {
						return SpringMVCUtils.returnSuccess("管理员添加成功");
					} else {
						return SpringMVCUtils.returnError("系统错误，请联系管理员");
					}
				}
			} else {
				manager2 = manageService.get(manager.getId());
				String pwd = manager.getPassword();
				String password2 = MD5Util.MD5Encode(pwd, "utf-8");
				manager2.setPassword(password2);
				//MyBeanUtils.propertyUtils(manager2 , manager);
				if (manageService.saveAndModify(manager2) != null) {
					return SpringMVCUtils.returnSuccess("管理员修改成功");
				} else {
					return SpringMVCUtils.returnError("系统错误，请联系管理员");
				}
			}
		} else {
			return SpringMVCUtils.returnError(bindingResult);
		}
	}
	
	@RequestMapping("manage-delete")
	@ResponseBody
	public Result manageDelete(@RequestParam("id") String id){
		String[] ids = StringUtils.split(id, ",");
		try{
			Arrays.stream(ids).forEach(s->{
				manageService.delete(Long.parseLong(s));
			});
			return SpringMVCUtils.returnSuccess("管理员信息删除成功");
		} catch (NumberFormatException e) {
			return SpringMVCUtils.returnError("管理员信息删除失败");
		}
	}
	
}
