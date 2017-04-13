package com.alqsoft.controller.pc.view.attachment.message;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.service.message.MessageService;

/**
 * 
* @ClassName: MessageController 
* @Description: pc健桥资讯控制层 
* @author 腾卉 
* @date 2017年3月27日 下午4:25:03 
*
 */
@Controller
@RequestMapping("pc/view/message")
public class MessageController {

	@Autowired
	public MessageService messageService;
	/**
	 * 
	* @Title: getAll 
	* @Description: 查询所有资讯
	* @return String    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("getAll")
	@ResponseBody
	public Result getAll(
			HttpServletRequest request,
			HttpServletResponse response
			){
		Result result = new Result();
		List<Map<String, Object>> list = messageService.getAll();
		if( list != null ){
			result.setContent(list);
			return result;
		}
		return null;
	}
	
}
