package com.alqsoft.model;

import java.io.Serializable;

/**
 * 
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年7月8日 下午5:11:45
 * 
 */
public class JdpayModel implements Serializable{
		
	private String v_mid;//商户编号

	private String v_oid;//订单编号	

	private String v_amount;//	订单总金额

	private String v_moneytype;//	币种	

	private String v_url;//	URL地址	

	private String v_md5info;//	MD5校验码	

	private String remark1;//	备注1 值自定义。

	private String remark2;//	备注2	 结果通知页面，如果有值，则按照所写地址进行通知；如果无值，就不进行通知，不通知可能会导致掉单。

	private String v_rcvname;//	收货人姓名	

	private String v_rcvaddr;//	收货人地址	

	private String v_rcvtel;//	收货人电话	

	private String v_rcvpost;//	收货人邮编	
	
	private String v_rcvemail;//	收货人Email
	
	private String v_rcvmobile;//	收货人手机号

	private String v_ordername;//	订货人姓名	

	private String v_orderaddr;//	订货人地址	

	private String v_ordertel;//	订货人电话	

	private String v_orderpost;//	订货人邮编	

	private String v_orderemail;//	订货人Email	

	private String v_ordermobile;//	订货人手机号	
	
	private String v_pstatus; //支付状态
	
	private String v_pmode; // 支付方式中文说明，如"中行长城信用卡"
	
	private String v_pstring; // 对支付结果的说明，成功时（v_pstatus=20）为"支付成功"，支付失败时（v_pstatus=30）为"支付失败"
	
	private String pmode_id; // 网银在线定义的银行编码
	
	private Object object; //携带参数
	
	private String phone; //会员手机号
	
	private Double availableIntegral;//会员可用总积分
	
	private Long credit;//总的现金金额
	
	private Long leftWithdrawalAmount;//商户 可提现金额 单位分
	
	private Integer  craeteOrderPayType;//生成订单的支付类型
	
	private String memberNum; //商户代理充值的用户
	
	public String getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}

	public Double getAvailableIntegral() {
		return availableIntegral;
	}

	public void setAvailableIntegral(Double availableIntegral) {
		this.availableIntegral = availableIntegral;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Long getCredit() {
		return credit;
	}

	public void setCredit(Long credit) {
		this.credit = credit;
	}

	public Long getLeftWithdrawalAmount() {
		return leftWithdrawalAmount;
	}

	public void setLeftWithdrawalAmount(Long leftWithdrawalAmount) {
		this.leftWithdrawalAmount = leftWithdrawalAmount;
	}

	public Integer getCraeteOrderPayType() {
		return craeteOrderPayType;
	}

	public void setCraeteOrderPayType(Integer craeteOrderPayType) {
		this.craeteOrderPayType = craeteOrderPayType;
	}

	public String getPmode_id() {
		return pmode_id;
	}

	public void setPmode_id(String pmode_id) {
		this.pmode_id = pmode_id;
	}

	public String getV_pstring() {
		return v_pstring;
	}

	public void setV_pstring(String v_pstring) {
		this.v_pstring = v_pstring;
	}

	public String getV_pmode() {
		return v_pmode;
	}

	public void setV_pmode(String v_pmode) {
		this.v_pmode = v_pmode;
	}

	public String getV_pstatus() {
		return v_pstatus;
	}

	public void setV_pstatus(String v_pstatus) {
		this.v_pstatus = v_pstatus;
	}

	public String getV_mid() {
		return v_mid;
	}

	public void setV_mid(String v_mid) {
		this.v_mid = v_mid;
	}

	public String getV_oid() {
		return v_oid;
	}

	public void setV_oid(String v_oid) {
		this.v_oid = v_oid;
	}

	public String getV_amount() {
		return v_amount;
	}

	public void setV_amount(String v_amount) {
		this.v_amount = v_amount;
	}

	public String getV_moneytype() {
		return v_moneytype;
	}

	public void setV_moneytype(String v_moneytype) {
		this.v_moneytype = v_moneytype;
	}

	public String getV_url() {
		return v_url;
	}

	public void setV_url(String v_url) {
		this.v_url = v_url;
	}

	public String getV_md5info() {
		return v_md5info;
	}

	public void setV_md5info(String v_md5info) {
		this.v_md5info = v_md5info;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getV_rcvname() {
		return v_rcvname;
	}

	public void setV_rcvname(String v_rcvname) {
		this.v_rcvname = v_rcvname;
	}

	public String getV_rcvaddr() {
		return v_rcvaddr;
	}

	public void setV_rcvaddr(String v_rcvaddr) {
		this.v_rcvaddr = v_rcvaddr;
	}

	public String getV_rcvtel() {
		return v_rcvtel;
	}

	public void setV_rcvtel(String v_rcvtel) {
		this.v_rcvtel = v_rcvtel;
	}

	public String getV_rcvpost() {
		return v_rcvpost;
	}

	public void setV_rcvpost(String v_rcvpost) {
		this.v_rcvpost = v_rcvpost;
	}

	public String getV_rcvemail() {
		return v_rcvemail;
	}

	public void setV_rcvemail(String v_rcvemail) {
		this.v_rcvemail = v_rcvemail;
	}

	public String getV_rcvmobile() {
		return v_rcvmobile;
	}

	public void setV_rcvmobile(String v_rcvmobile) {
		this.v_rcvmobile = v_rcvmobile;
	}

	public String getV_ordername() {
		return v_ordername;
	}

	public void setV_ordername(String v_ordername) {
		this.v_ordername = v_ordername;
	}

	public String getV_orderaddr() {
		return v_orderaddr;
	}

	public void setV_orderaddr(String v_orderaddr) {
		this.v_orderaddr = v_orderaddr;
	}

	public String getV_ordertel() {
		return v_ordertel;
	}

	public void setV_ordertel(String v_ordertel) {
		this.v_ordertel = v_ordertel;
	}

	public String getV_orderpost() {
		return v_orderpost;
	}

	public void setV_orderpost(String v_orderpost) {
		this.v_orderpost = v_orderpost;
	}

	public String getV_orderemail() {
		return v_orderemail;
	}

	public void setV_orderemail(String v_orderemail) {
		this.v_orderemail = v_orderemail;
	}

	public String getV_ordermobile() {
		return v_ordermobile;
	}

	public void setV_ordermobile(String v_ordermobile) {
		this.v_ordermobile = v_ordermobile;
	}
}
