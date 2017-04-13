package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.UserComment;



public interface UserCommentService {

	AppResult getCommentList(UserComment userComment);
	AppResult updateAgree(UserComment userComment);
	AppResult addComment(UserComment userComment);
	/**
	 * 按照评论对象和评论id查询列表
	 * @return
	 */
	List<UserComment> getCommentByParame(UserComment userComment);
	AppResult updateInfo(UserComment userComment);
}
