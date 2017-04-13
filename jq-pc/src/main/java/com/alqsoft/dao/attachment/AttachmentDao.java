package com.alqsoft.dao.attachment;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.attachment.Attachment;

/**
 * 
    * @ClassName: AttachmentDao  
    * @Description: 图片表dao
    * @author 腾卉  
    * @date 2017年3月22日  
    *
 */
@MyBatisRepository
public interface AttachmentDao{

	/**
	 * 
	* @Title: getOneById  
	* @Description: 根据id查询实体
	* @author   腾卉 
	* @return Attachment    返回类型
	 */
	public Attachment getOneById(Long id);
}
