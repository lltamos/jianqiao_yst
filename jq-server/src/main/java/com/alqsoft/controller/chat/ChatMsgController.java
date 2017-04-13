package com.alqsoft.controller.chat;

import java.util.List;

import org.alqframework.encode.EncodeUtils;
import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.sms.ClientResponse;
import com.alqsoft.entity.sms.SmsContent;
import com.alqsoft.utils.chat.ChatCommonUtils;
import com.google.common.collect.Lists;

/**
 * @Title: ChatMsgController.java
 * @Description: web 信息聊天请求 接口控制器
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月18日 下午5:47:12 Copyright © 2013 厦门卓讯信息技术有限公司 All rights
 *              reserved.
 * 
 */
@RequestMapping("view/webchat")
@Controller
public class ChatMsgController {

	@Autowired     
	private ChatCommonUtils chatCommonUtils;

	/**
	 * 
	 * @Title: sendChatMsg
	 * @Description: 消息发送
	 * @param @param content 发送消息内容aaa
	 * @param @return 设定文件
	 * @return Result 返回类型
	 * @throws
	 */
	@RequestMapping("sendChatMsg")
	@ResponseBody
	public Result sendChatMsg(@ModelAttribute("content") SmsContent content) {
		if (content!=null) {
			return chatCommonUtils.addChatMsgToRedis(content);// 把发送消息放入缓冲区
		} else {
			return ResultUtils.returnError("发送消息内容不能为空", -1);
		}
	}

	/**
	 * 
	 * @Title: getChatMsg
	 * @Description:接收消息
	 * @param @param userId 用户id
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("getChatMsg")
	@ResponseBody
	public Object getChatMsg(@RequestParam("userId") Long userId) {
		if (userId!=null) {
			List<Object> lists = Lists.newArrayList();
			for (int i = 0; i < 10; i++) {
				// 获取作业中接收者缓冲区的消息发送内容
				SmsContent smsContent = chatCommonUtils.getRedisSmsContent(userId.toString());
				if (smsContent != null) {
					lists.add(chatCommonUtils.getChatMsg(userId.toString(),smsContent));
				}else{
					break;
				}
			}
			if (lists.isEmpty()) {
				return ResultUtils.returnError("消息不存在", -1); 
			}
			return lists;
		} else {
			return ResultUtils.returnError("用户不存在", -1);
		}
	}

	/**
	 * 
	 * @Title: chatMsgResponse
	 * @Description: web端收到消息 进行响应 [单发]
	 * @param @param content 响应内容
	 * @param @return 设定文件
	 * @return Result 返回类型
	 * @throws
	 */
	@RequestMapping("sendChatWebResponse")
	@ResponseBody
	public Result chatMsgResponse(@ModelAttribute("content") ClientResponse content) {
		ClientResponse clientResponse = chatCommonUtils.getChatMsgClientResponse(content);
		return ResultUtils.returnSuccess("消息单发响应成功", clientResponse);
	}

	/**
	 * 
	 * @Title: chatMsgResponse
	 * @Description: web端收到消息 进行响应 【群发】
	 * @param @param content 响应内容
	 * * @param @param accountId 用户id
	 * @param @return 设定文件
	 * @return Result 返回类型
	 * @throws
	 */
	@RequestMapping("sendChatGroupWebResponse")
	@ResponseBody
	public Result chatGroupClientResponse(@ModelAttribute("content") ClientResponse content,@RequestParam("accountId") Long accountId) {
		ClientResponse clientResponse = chatCommonUtils.getChatGroupMsgClientResponse(content,accountId.toString());
		return ResultUtils.returnSuccess("消息群发响应成功", clientResponse);
	}
}
