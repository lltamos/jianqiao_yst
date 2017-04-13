package com.yst.web.model;

import java.io.Serializable;

import com.yst.web.annotations.Field;
/**
 *知识库信息
 */
public class KnowlgInfo implements Serializable{

	private static final long serialVersionUID = 7672635323266398887L;

	@Field
	private Integer id;
	@Field
	private String name;//名称
	@Field
	private String short_desc;//简单的描述信息
	private Integer tag_id;//知识库的标签
	private String image_header;//头部显示的图片
	@Field
	private String content_desc;//图文详情
	private Integer comment_count;//评论数量，有评论的时候更新一次
	private Integer view_count;//用户点击的数量，每次浏览一次，数量加一
	private Integer deleted;//是否删除1:删除 0:正常
	private KnowlgTag knowlgTag;
	
	
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public KnowlgTag getKnowlgTag() {
		return knowlgTag;
	}
	public void setKnowlgTag(KnowlgTag knowlgTag) {
		this.knowlgTag = knowlgTag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public Integer getTag_id() {
		return tag_id;
	}
	public void setTag_id(Integer tag_id) {
		this.tag_id = tag_id;
	}
	public String getImage_header() {
		return image_header;
	}
	public void setImage_header(String image_header) {
		this.image_header = image_header;
	}
	public String getContent_desc() {
		return content_desc;
	}
	public void setContent_desc(String content_desc) {
		this.content_desc = content_desc;
	}
	public Integer getComment_count() {
		return comment_count;
	}
	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	
}
