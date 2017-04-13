
package com.alqsoft.model;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Date:     2016年10月12日 上午10:57:58 <br/>
 * @author   zhangcan
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class PayModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private Long memberId;//会员
	@NotBlank
	private String orderNum;//订单号
	@NotNull
	private Integer payType;//支付方式 1 现金支付 2充值金额支付 3可用积分支付 4商户余额代付
	
	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
}

