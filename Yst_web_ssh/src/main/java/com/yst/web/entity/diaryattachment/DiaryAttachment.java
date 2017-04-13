package com.yst.web.entity.diaryattachment;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yst.web.entity.diary.Diary;

/**
 * 日记附件
 * 
 * @author 张灿
 * @e-mail 1023059764@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-10-19 上午12:27:45
 * 
 */
@Entity
@Table(name = "alq_attachment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DiaryAttachment extends IdEntity{
	
	@NotBlank(message = "附件名称不能为空")
	@Length(min = 2, max = 50, message = "附件名称必须在2到50之间，请重新输入")
	private String name;//附件名称
	@NotBlank(message = "附件地址不能为空")
	@Length(min = 2, max = 200, message = "附件地址必须在2到200之间，请重新输入")
	private String address;//附件地址
	
	private Double memory;//记录文件大小；
	
	private Integer field;//记录文件上传的唯一标识
	
	private Diary diary;//日记id
	
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "diary_id")
	@ForeignKey(name="")
	public Diary getDiary() {
		return diary;
	}
	public void setDiary(Diary diary) {
		this.diary = diary;
	}
	public Integer getField() {
		return field;
	}
	public void setField(Integer field) {
		this.field = field;
	}
	public Double getMemory() {
		return memory;
	}
	public void setMemory(Double memory) {
		this.memory = memory;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
