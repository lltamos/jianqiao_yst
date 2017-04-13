
package com.yst.web.entity.consult;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Date:     2016年11月22日 下午4:52:45 <br/>
 * @author   zhangcan
 * @version  健桥咨询表
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "consult")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Consult extends IdEntity{
	
	private String title;//标题名称
	
	private String textdesc;//文字描述
	
	private String image;//图片
	
	private String toUrl;//跳转链接

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTextdesc() {
		return textdesc;
	}

	public void setTextdesc(String textdesc) {
		this.textdesc = textdesc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getToUrl() {
		return toUrl;
	}

	public void setToUrl(String toUrl) {
		this.toUrl = toUrl;
	}
}

