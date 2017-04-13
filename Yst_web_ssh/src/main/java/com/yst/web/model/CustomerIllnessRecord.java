package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * CustomerIllnessRecord.java
 * @Description: 健桥- 病例
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月21日 下午5:39:20
 *
 */
public class CustomerIllnessRecord implements Serializable{

	private static final long serialVersionUID = 4120521998043780969L;

	private Integer record_id;//病例id
	private Integer relative_id;//相关人员id
	private String symptom;//症状
	private String diagnose;//诊断
	private String name;//名称
	private Date entering_time;
	private String proce;//发病过程
	private String image1;
	private String image2;
	private String image3;
	private String image4;
	private String image5;
	private String image6;
	private String image7;
	private String image8;
	private String image9;
	private String image10;
	private String image11;
	private String image12;
	private String image13;
	private String image14;
	private String image15;
	private String image16;
	private String image17;
	private String image18;
	private String image19;
	private String image20;
	private CustomerRelative customerRelative;

	
	private String str_entering_time;
	private String str_relative;
	private Integer age;
	
	
	
	public String getStr_relative() {
		return str_relative;
	}
	public void setStr_relative(String str_relative) {
		this.str_relative = str_relative;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public CustomerRelative getCustomerRelative() {
		return customerRelative;
	}
	public void setCustomerRelative(CustomerRelative customerRelative) {
		this.customerRelative = customerRelative;
	}
	public String getStr_entering_time() {
		return str_entering_time;
	}
	public void setStr_entering_time(String str_entering_time) {
		this.str_entering_time = str_entering_time;
	}
	public Integer getRecord_id() {
		return record_id;
	}
	public void setRecord_id(Integer record_id) {
		this.record_id = record_id;
	}
	public Integer getRelative_id() {
		return relative_id;
	}
	public void setRelative_id(Integer relative_id) {
		this.relative_id = relative_id;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public String getDiagnose() {
		return diagnose;
	}
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	
	public String getProce() {
		return proce;
	}
	public void setProce(String proce) {
		this.proce = proce;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEntering_time() {
		return entering_time;
	}
	public void setEntering_time(Date entering_time) {
		this.entering_time = entering_time;
	}
	public String getImage1() {
		return image1;
	}
	public void setImage1(String image1) {
		this.image1 = image1;
	}
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	}
	public String getImage3() {
		return image3;
	}
	public void setImage3(String image3) {
		this.image3 = image3;
	}
	public String getImage4() {
		return image4;
	}
	public void setImage4(String image4) {
		this.image4 = image4;
	}
	public String getImage5() {
		return image5;
	}
	public void setImage5(String image5) {
		this.image5 = image5;
	}
	public String getImage6() {
		return image6;
	}
	public void setImage6(String image6) {
		this.image6 = image6;
	}
	public String getImage7() {
		return image7;
	}
	public void setImage7(String image7) {
		this.image7 = image7;
	}
	public String getImage8() {
		return image8;
	}
	public void setImage8(String image8) {
		this.image8 = image8;
	}
	public String getImage9() {
		return image9;
	}
	public void setImage9(String image9) {
		this.image9 = image9;
	}
	public String getImage10() {
		return image10;
	}
	public void setImage10(String image10) {
		this.image10 = image10;
	}
	public String getImage11() {
		return image11;
	}
	public void setImage11(String image11) {
		this.image11 = image11;
	}
	public String getImage12() {
		return image12;
	}
	public void setImage12(String image12) {
		this.image12 = image12;
	}
	public String getImage13() {
		return image13;
	}
	public void setImage13(String image13) {
		this.image13 = image13;
	}
	public String getImage14() {
		return image14;
	}
	public void setImage14(String image14) {
		this.image14 = image14;
	}
	public String getImage15() {
		return image15;
	}
	public void setImage15(String image15) {
		this.image15 = image15;
	}
	public String getImage16() {
		return image16;
	}
	public void setImage16(String image16) {
		this.image16 = image16;
	}
	public String getImage17() {
		return image17;
	}
	public void setImage17(String image17) {
		this.image17 = image17;
	}
	public String getImage18() {
		return image18;
	}
	public void setImage18(String image18) {
		this.image18 = image18;
	}
	public String getImage19() {
		return image19;
	}
	public void setImage19(String image19) {
		this.image19 = image19;
	}
	public String getImage20() {
		return image20;
	}
	public void setImage20(String image20) {
		this.image20 = image20;
	}
	
	
}
