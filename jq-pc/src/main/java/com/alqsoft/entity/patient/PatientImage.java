package com.alqsoft.entity.patient;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;


/**
 * 患者影像资料
*   
* 项目名称：jianqiao  
* 类名称：PatientImage  
* 类描述：  
* 创建人：whl  
* 创建时间：2016年2月22日 下午4:07:51  
* 修改人：whl  
* 修改时间：2016年2月22日 下午4:07:51  
* 修改备注：  
* @version   
*
 */
public class PatientImage extends IdEntity{
	
	private Long patient;//患者id
	
	private Long patientDisease;//求医信息id
	
	@NotBlank(message = "附件名称不能为空")
	@Length(min = 2, max = 50, message = "附件名称必须在2到50之间，请重新输入")
	private String name;//附件名称
	
	@NotBlank(message = "附件地址不能为空")
	@Length(min = 2, max = 200, message = "附件地址必须在2到200之间，请重新输入")
	private String address;//附件地址
	
	private Double memory;//记录文件大小；
	
	private Integer field;//记录文件上传的唯一标识

	
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
	public Long getPatient() {
		return patient;
	}
	public void setPatient(Long patient) {
		this.patient = patient;
	}
	public Long getPatientDisease() {
		return patientDisease;
	}
	public void setPatientDisease(Long patientDisease) {
		this.patientDisease = patientDisease;
	}
	
}
