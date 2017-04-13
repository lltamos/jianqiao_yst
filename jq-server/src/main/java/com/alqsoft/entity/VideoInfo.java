package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 视频信息表
 *
 */
@Entity
@Table(name = "video_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class VideoInfo  extends IdEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;//名称
	
	private String short_desc;//简单的描述信息
	private Integer tag_id;//所属的标签，暂时为空
	private String image_header;//头部显示的图片
	private String image_video;//视频地址
	
	private Integer comment_count;//评论数量
	private Integer view_count;//用户点击的数量
	
	private Integer deleted;//是否删除1:删除 0:正常
	private KnowlgTag knowlgTag;
	
	private String str_tag_name;
	
	
	
	public String getStr_tag_name() {
		return str_tag_name;
	}
	public void setStr_tag_name(String str_tag_name) {
		this.str_tag_name = str_tag_name;
	}
	public KnowlgTag getKnowlgTag() {
		return knowlgTag;
	}
	public void setKnowlgTag(KnowlgTag knowlgTag) {
		this.knowlgTag = knowlgTag;
	}
	public String getImage_video() {
		return image_video;
	}
	public void setImage_video(String image_video) {
		this.image_video = image_video;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
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
