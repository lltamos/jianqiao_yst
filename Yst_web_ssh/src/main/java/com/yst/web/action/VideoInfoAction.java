package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgTag;
import com.yst.web.model.UserComment;
import com.yst.web.model.VideoInfo;
import com.yst.web.service.KnowlgTagService;
import com.yst.web.service.UserCommentService;
import com.yst.web.service.VideoInfoService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("videoInfoAction")
@Scope("prototype")
public class VideoInfoAction extends BaseAction{

	private static final long serialVersionUID = 4603152356567544433L;
	
	@Resource(name="videoInfoService")
	private VideoInfoService videoInfoService;
	@Resource(name="knowlgTagService")
	private KnowlgTagService knowlgTagService;
	@Resource(name = "userCommentService")
	private UserCommentService userCommentService;
	private VideoInfo videoInfo = new VideoInfo();
	private UserComment userComment = new UserComment();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		appResult = this.videoInfoService.getAllList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		JSONUtils.sendJSON(appResult);
	}
	
	public String addPage(){
		List<KnowlgTag> knowlgTagList = this.knowlgTagService.queryList();
		ActionContext.getContext().put("knowlgTagList", knowlgTagList);
		return "add_page";
	}
	
	public void addAjax(){
		BeanUtils.getBase64Image(videoInfo);// 转换image为base64字符串
		appResult = this.videoInfoService.addVideoInfo(videoInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	public String deleteAjax(){
		this.videoInfoService.deleteVideoInfo(videoInfo.getId());
		return SUCCESS;
	}
	
	public String updatePage(){
		videoInfo = this.videoInfoService.findVideoInfo(videoInfo.getId());
		List<KnowlgTag> knowlgTagList = this.knowlgTagService.queryList();
		ActionContext.getContext().put("knowlgTagList", knowlgTagList);
		return "update_page";
	}
	
	public void updateAjax(){
		BeanUtils.getBase64Image(videoInfo);// 转换image为base64字符串
		appResult = this.videoInfoService.updateVideoInfo(videoInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	public String getUserCommetList(){
		List<UserComment> userCommentList = this.userCommentService.getCommentByParame(userComment);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setData(userCommentList);
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		JSONUtils.sendJSON(appResult);
		return "comment_list";
	} 
	
	//以下为业务接口
	/**
	 * 获取视频的列表接口
	 */
	public void getVideoAllList(){
		appResult = this.videoInfoService.getAllList();
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 视频点击量统计接口
	 */
	public void hitsVideoCount(){
		appResult = this.videoInfoService.updateVideoHitsCount(videoInfo.getId());
		JSONUtils.sendJSON(appResult);
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public VideoInfo getModel() {
		return videoInfo;
	}

	public UserComment getUserComment() {
		return userComment;
	}

	public void setUserComment(UserComment userComment) {
		this.userComment = userComment;
	}
	
	
}
