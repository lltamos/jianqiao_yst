package com.alqsoft.service.message;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.webmvc.springmvc.Result;

import com.alqsoft.entity.message.Message;
import com.alqsoft.utils.BootStrapResult;

/**
 * 
    * @ClassName: MessageService  
    * @Description: 健桥资讯service  
    * @author 腾卉  
    * @date 2017年3月22日  
    *
 */
public interface MessageService extends BaseService<Message>{

	/**
	 * 
	* @Title: getMerchantList  
	* @Description: 健桥资讯分页列表
	* @author   腾卉 
	* @return BootStrapResult<List<Message>>    返回类型
	 */
	BootStrapResult<List<Message>> getMessageList(Integer page, Integer length, HttpServletRequest request);
	
	/**
	 * 
	* @Title: getOneById  
	* @Description: 根据id返回实体
	* @author   腾卉 
	* @return Message    返回类型
	 */
	Message getOneById(Long id);
	/**
	 * 
	* @Title: save  
	* @Description: 保存资讯信息
	* @author   腾卉 
	* @return Result    返回类型
	 */
	Result save( String aids,Message message );
	
	/**
	 * 
	* @Title: delete  
	* @Description: 删除资讯信息
	* @author   腾卉 
	* @return Result    返回类型
	 */
	Result delete(Message message);
	
}
