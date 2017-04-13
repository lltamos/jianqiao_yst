package com.yst.web.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.UserCommentDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.PageModel;
import com.yst.web.model.Product;
import com.yst.web.model.UserComment;
import com.yst.web.service.UserCommentService;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.PageModelContext;

@Service("userCommentService")
@Transactional
public class UserCommentServiceImpl implements UserCommentService {
	private static Log logger = LogFactory.getLog(UserCommentServiceImpl.class);
	@Resource(name = "userCommentDao")
	private UserCommentDao userCommentDao;

	@Override
	public AppResult getCommentList(UserComment userComment) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer comment_for_type = userComment.getComment_for_type();
		if (comment_for_type == null || comment_for_type.equals("")) {
			appResult.setError_info("评论对象不得为空");
			return appResult;
		}
		Integer comment_for_id = userComment.getComment_for_id();
		if (comment_for_id == null || comment_for_id.equals("")) {
			appResult.setError_info("评论对象id不得为空");
			return appResult;
		}
		Integer order_by = userComment.getOrder_by();
		if (order_by == null || order_by.equals("")) {
			order_by = 0;
			appResult.setError_info("排序规则不得为空");
			return appResult;
		}
		String object ="";
		String id="";
		//1 活动 2 知识库 3.视频,4.服务包
		if(comment_for_type==1){
			object="MerchantActivity";
			id="merchant_activity_id";
		}else if(comment_for_type==2){
			object = "KnowlgInfo";
			id="id";
		}else if(comment_for_type==3){
			object = "VideoInfo";
			id="id";
		}else if(comment_for_type==4){
			object="Product";
			id="product_id";
		}
		String hql="from "+object+" as o where o."+id+"=?";
		Object o =this.userCommentDao.queryForObject(hql, comment_for_id);
		if(o==null){
			appResult.setError_info("评论对象id不存在或错误");
			return appResult;
		}
		String order_by_str = "";
		if (order_by == 1) {
			order_by_str = " order by o.comment_date desc";
		} else if (order_by == 2) {
			order_by_str = " order by o.hot desc";
		}
		PageModel pm = PageModelContext.getPageModel();
		hql = "from UserComment as o where o.deleted<>1 and o.comment_for_type=? and o.comment_for_id=?"
				+ order_by_str;
		List<UserComment> userCommentList = this.userCommentDao.query(hql, pm,
				comment_for_type, comment_for_id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("object", o);
		map.put("list", userCommentList);
		map.put("date", new Date());
		appResult.setPage_model(pm);
		appResult.setData(map);
		appResult.setError_info("获取列表成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult updateAgree(UserComment userComment) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer user_comment_id = userComment.getUser_comment_id();
		Integer agree = userComment.getAgree();
		if (user_comment_id == null || user_comment_id.equals("")) {
			appResult.setError_info("评论id不得为空");
			return appResult;
		}
		if (agree == null || agree.equals("")) {
			appResult.setError_info("是否赞成不得为空");
			return appResult;
		}
		UserComment dbUserComment = this.userCommentDao.get(UserComment.class,
				user_comment_id);
		if (dbUserComment == null) {
			appResult.setError_info("评论id存在或错误");
			return appResult;
		}
		if (agree == 1) {
			dbUserComment.setAgree(dbUserComment.getAgree() + 1);//赞成+1
		} else {
			dbUserComment.setDisagree(dbUserComment.getDisagree() + 1);//不赞成+1
		}
		this.userCommentDao.saveOrUpdate(dbUserComment);
		appResult.setError_info("成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public AppResult addComment(UserComment userComment) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer customer_id = userComment.getCustomer_id();
		if (customer_id == null || customer_id.equals("")) {
			appResult.setError_info("用户id不得为空");
			return appResult;
		}
		Integer comment_for_type = userComment.getComment_for_type();
		if (comment_for_type == null || comment_for_type.equals("")) {
			appResult.setError_info("评论对象不得为空");
			return appResult;
		}
		Integer comment_for_id = userComment.getComment_for_id();
		if (comment_for_id == null || comment_for_id.equals("")) {
			appResult.setError_info("评论对象id不得为空");
			return appResult;
		}
		Customer dbCustomer = this.userCommentDao.get(Customer.class,
				customer_id);
		if (dbCustomer == null) {
			appResult.setError_info("用户id不存在或错误");
			return appResult;
		}
		userComment.setComment_date(new Date());
		this.userCommentDao.saveOrUpdate(userComment);
		appResult.setError_info("添加成功");
		appResult.setResult(AppResult.SUCCESS);
		return appResult;
	}

	@Override
	public List<UserComment> getCommentByParame(UserComment userComment) {
		Integer comment_for_type = userComment.getComment_for_type();
		Integer comment_for_id = userComment.getComment_for_id();
		if (comment_for_type != null && comment_for_id != null
				&& !comment_for_type.equals("") && !comment_for_id.equals("")) {
			String hql = "from UserComment as o where o.comment_for_type=? and o.comment_for_id=?";
			List<UserComment> userCommentsList = this.userCommentDao.query(hql,
					PageModelContext.getPageModel(), UserComment.class,
					comment_for_type, comment_for_id);
			return userCommentsList;
		} else {
			String hql = "from UserComment as o";
			List<UserComment> userCommentsList = this.userCommentDao.query(hql,
					PageModelContext.getPageModel(), UserComment.class, null);
			return userCommentsList;
		}
	}

	@Override
	public AppResult updateInfo(UserComment userComment) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);// 默认1
		appResult.setResult(AppResult.FAILED);// 默认失败
		Integer user_comment_id = userComment.getUser_comment_id();
		if (user_comment_id == null || user_comment_id.equals("")) {
			appResult.setError_info("评论id不能为空");
			return appResult;
		}
		UserComment dbUserComment = this.userCommentDao.get(UserComment.class,
				user_comment_id);
		if(dbUserComment!=null){
			BeanUtils.copy(userComment, dbUserComment);
			this.userCommentDao.saveOrUpdate(dbUserComment);
			appResult.setData(dbUserComment);
			appResult.setResult(AppResult.SUCCESS);
			appResult.setError_info("修改成功");
		} else {
			appResult.setError_info("评论id错误或不存在");
		}
		return appResult;
	}
}
