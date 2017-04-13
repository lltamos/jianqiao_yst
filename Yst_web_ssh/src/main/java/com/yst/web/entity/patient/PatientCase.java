package com.yst.web.entity.patient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 患者病例
*   
* 项目名称：jianqiao  
* 类名称：PatientCase  
* 类描述：  
* 创建人：whl  
* 创建时间：2016年2月22日 下午4:06:44  
* 修改人：whl  
* 修改时间：2016年2月22日 下午4:06:44  
* 修改备注：  
* @version   
*
 */
@Entity
@Table(name = "patient_case")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class PatientCase extends IdEntity{
	private String content;
	private Patient patient;
	
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name="patient_id")
	@ForeignKey(name="")
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Type(type="text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
