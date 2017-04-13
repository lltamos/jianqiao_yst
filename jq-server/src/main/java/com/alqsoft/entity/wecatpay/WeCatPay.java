package com.alqsoft.entity.wecatpay;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午4:46:59
 * 
 */
@Entity
@Table(name = "wechat_pay_info")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class WeCatPay extends IdEntity implements Serializable {

		private static final long serialVersionUID = 1L;
		
		//发送参数
		private String appid;			//应用ID
		private String mch_id;			//商户号
		private String nonce_str;		//随机字符串
		private String sign;			//签名
		private String body;			//商品描述
		private String detail;			//商品详情
		private String attach;			//附加数据
		private String out_trade_no;	//商户订单号
		private String fee_type;		//货币类型
		private String total_fee;		//总金额
		private String spbill_create_ip;//终端IP
		private String time_start;		//交易起始时间
		private String time_expire;		//交易结束时间
		private int goods_tag;		//商品标记  1 在线咨询  2 服务包定金
		private String notify_url;		//通知地址
		private String trade_type;		//交易类型
		private String limit_pay;		//指定支付方式
		private String product_id;		//trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
		
		//返回参数  包括回调
		private String result_code;//业务结果  SUCCESS/FAIL 
		private String err_code;//错误代码
		private String err_code_des;//错误返回的信息描述
		private String prepay_id;//微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
		private String code_url;//trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付
		private String return_code;//返回状态码SUCCESS/FAIL此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断
		private String return_msg;//返回信息
		
		//回调的一些参数
		private String openid;		//用户在商户appid下的唯一标识
		private String bank_type;	//银行类型，采用字符串类型的银行标识，银行类型见银行列表
		private String cash_fee;	//现金支付金额订单现金支付金额，详见支付金额
		private String transaction_id;	//微信支付订单号
		private String time_end;		//支付完成时间
		
		

		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getMch_id() {
			return mch_id;
		}
		public void setMch_id(String mch_id) {
			this.mch_id = mch_id;
		}
		public String getNonce_str() {
			return nonce_str;
		}
		public void setNonce_str(String nonce_str) {
			this.nonce_str = nonce_str;
		}
		public String getSign() {
			return sign;
		}
		public void setSign(String sign) {
			this.sign = sign;
		}
		public String getBody() {
			return body;
		}
		public void setBody(String body) {
			this.body = body;
		}
		public String getDetail() {
			return detail;
		}
		public void setDetail(String detail) {
			this.detail = detail;
		}
		public String getAttach() {
			return attach;
		}
		public void setAttach(String attach) {
			this.attach = attach;
		}
		public String getOut_trade_no() {
			return out_trade_no;
		}
		public void setOut_trade_no(String out_trade_no) {
			this.out_trade_no = out_trade_no;
		}
		public String getFee_type() {
			return fee_type;
		}
		public void setFee_type(String fee_type) {
			this.fee_type = fee_type;
		}
		public String getTotal_fee() {
			return total_fee;
		}
		public void setTotal_fee(String total_fee) {
			this.total_fee = total_fee;
		}
		public String getSpbill_create_ip() {
			return spbill_create_ip;
		}
		public void setSpbill_create_ip(String spbill_create_ip) {
			this.spbill_create_ip = spbill_create_ip;
		}
		public String getTime_start() {
			return time_start;
		}
		public void setTime_start(String time_start) {
			this.time_start = time_start;
		}
		public String getTime_expire() {
			return time_expire;
		}
		public void setTime_expire(String time_expire) {
			this.time_expire = time_expire;
		}
		public int getGoods_tag() {
			return goods_tag;
		}
		public void setGoods_tag(int goods_tag) {
			this.goods_tag = goods_tag;
		}
		public String getNotify_url() {
			return notify_url;
		}
		public void setNotify_url(String notify_url) {
			this.notify_url = notify_url;
		}
		public String getTrade_type() {
			return trade_type;
		}
		public void setTrade_type(String trade_type) {
			this.trade_type = trade_type;
		}
		public String getLimit_pay() {
			return limit_pay;
		}
		public void setLimit_pay(String limit_pay) {
			this.limit_pay = limit_pay;
		}
		public String getResult_code() {
			return result_code;
		}
		public void setResult_code(String result_code) {
			this.result_code = result_code;
		}
		public String getErr_code() {
			return err_code;
		}
		public void setErr_code(String err_code) {
			this.err_code = err_code;
		}
		public String getErr_code_des() {
			return err_code_des;
		}
		public void setErr_code_des(String err_code_des) {
			this.err_code_des = err_code_des;
		}
		public String getPrepay_id() {
			return prepay_id;
		}
		public void setPrepay_id(String prepay_id) {
			this.prepay_id = prepay_id;
		}
		public String getCode_url() {
			return code_url;
		}
		public void setCode_url(String code_url) {
			this.code_url = code_url;
		}
		public String getReturn_code() {
			return return_code;
		}
		public void setReturn_code(String return_code) {
			this.return_code = return_code;
		}
		public String getReturn_msg() {
			return return_msg;
		}
		public void setReturn_msg(String return_msg) {
			this.return_msg = return_msg;
		}
		public static long getSerialversionuid() {
			return serialVersionUID;
		}
		public String getOpenid() {
			return openid;
		}
		public void setOpenid(String openid) {
			this.openid = openid;
		}
		public String getBank_type() {
			return bank_type;
		}
		public void setBank_type(String bank_type) {
			this.bank_type = bank_type;
		}
		public String getCash_fee() {
			return cash_fee;
		}
		public void setCash_fee(String cash_fee) {
			this.cash_fee = cash_fee;
		}
		public String getTransaction_id() {
			return transaction_id;
		}
		public void setTransaction_id(String transaction_id) {
			this.transaction_id = transaction_id;
		}
		public String getTime_end() {
			return time_end;
		}
		public void setTime_end(String time_end) {
			this.time_end = time_end;
		}
		public String getProduct_id() {
			return product_id;
		}
		public void setProduct_id(String product_id) {
			this.product_id = product_id;
		}
		
	}

