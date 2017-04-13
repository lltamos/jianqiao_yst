package com.alqsoft.controller.chat;

import java.util.Date;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.UniqueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.msgpush.PushContent;

/**
 * @Title: PushMsgController.java
 * @Description: 消息推送 接口控制器
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月19日 下午3:19:45
 * Copyright © 2013 厦门卓讯信息技术有限公司 All rights reserved.
 * 
 */
@RequestMapping("view/pushmsg")
@Controller
public class PushMsgController {
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;
	
	/**
	 * 
	 * @Title: sendChatMsg
	 * @Description: 把推送的消息放入缓冲区，等待发送
	 * @param @param content 发送消息内容
	 * @param @return 设定文件
	 * @return Result 返回类型
	 * @throws
	 */
	@RequestMapping("addPushMsg")
	@ResponseBody
	public Result sendChatMsg(@ModelAttribute("pushContent") PushContent pushContent) {
		if (pushContent!=null) {
//			PushContent pushContent = JSON.parseObject(content, PushContent.class);
			pushContent.setUniqueKey(UniqueUtils.getUUID());
			pushContent.setPushSendTime(new Date());// 保存发送时间
			// 消息内容 （以队列的形式放入一级缓冲区里：左进右出）,用于ChatTask作业中消息缓冲区的保存
			redisTemplate.opsForList().leftPush("push-public", pushContent);
			return ResultUtils.returnSuccess("消息放入redis缓冲区成功");// 把发送消息放入缓冲区
		} else {
			return ResultUtils.returnError("推送消息不能为空", -1);
		}
	}

}
