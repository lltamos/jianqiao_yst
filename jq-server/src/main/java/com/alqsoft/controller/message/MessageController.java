package com.alqsoft.controller.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.message.Message;
import com.alqsoft.model.Permission;
import com.alqsoft.service.message.MessageService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 
    * @ClassName: MessageController  
    * @Description: 健桥资讯（后台添加）  
    * @author 腾卉  
    * @date 2017年3月22日  
    *
 */
@RequestMapping("message")
@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	
	/**
	 * 
	* @Title: tomessage  
	* @Description: 跳转健桥资讯页面
	* @author   腾卉 
	* @return String    返回类型
	 */
	@RequestMapping("to-message")
	@Permission(SystemRole.ADMIN)
	public String tomessage() {
		return "message/message-list";
	}
	
	/**
	 * 
	* @Title: messgaeListData  
	* @Description: 健桥资讯列表分页
	* @author   腾卉 
	* @return BootStrapResult<List<Message>>    返回类型
	 */
	@RequestMapping("getmessage")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Message>> messgaeListData(Model model,
			@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request,
			HttpSession session) {
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return null;
		}
		Integer page = start / length;
		return this.messageService.getMessageList(page, length, request);
	}
	
	/**
	 * 
	* @Title: messageAddPage  
	* @Description: 健桥资讯添加跳转视图
	* @author   腾卉 
	* @return String    返回类型
	 */
	@RequestMapping("message-input")
	@Permission(SystemRole.ADMIN)
	public String messageAddPage(Model model,
			Message message,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(message.getId()!=null){
			model.addAttribute("message", this.messageService.getOneById(message.getId()));
		}
		return "message/message-input";
	}
	/**
	 * 
	 * @Title: messageSave  
	 * @Description: 健桥资讯新增、修改
	 * @author   腾卉 
	 * @return Result    返回类型
	 */
	@RequestMapping("message-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result messageSave(
			@RequestParam(value="aids") String aids,
			@RequestParam(value="id") String id,
			@RequestParam(value="status") String status,
			@RequestParam(value="title") String title,
			@RequestParam(value="des") String des,
			@RequestParam(value="address") String address,
			Message message,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session ) {
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return SpringMVCUtils.returnError("登录超时，请重新登录！");
		}
		if( id!=""&& status!="" ){
			message.setId(Long.valueOf(id));
			message.setStatus(Integer.valueOf(status));
		}
		message.setTitle(title);
		message.setDes(des);
		message.setAddress(address);
		return this.messageService.save( aids,message );
	}
	/**
	 * 
	* @Title: messageDeleted  
	* @Description: 删除资讯信息
	* @author   腾卉 
	* @return Result    返回类型
	 */
	@RequestMapping("message-deleted")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result messageDeleted(Model model,
			Message message,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return SpringMVCUtils.returnError("登录超时，请重新登录！");
		}
		return this.messageService.delete(message);
	}
	/**
	 * 
	* @Title: messageInfo 
	* @Description: 查看咨询信息
	* @return String    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("message-info")
	@Permission(SystemRole.ADMIN)
	public String messageInfo(Model model,
			@RequestParam(required=true,value="id")Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		model.addAttribute("message", this.messageService.get(id));
		return "message/message-info";
	}
	
	
}
