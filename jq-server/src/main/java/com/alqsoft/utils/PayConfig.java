package com.alqsoft.utils;
/**
 * 支付工程 重要参数配置文件
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年1月22日 下午4:00:50
 * 
 */
public class PayConfig {
	
	public static final String JDpay="京东";
	public static final String YLpay="易联";
	public static final String GZYLpay="贵州银联";
	
	
	/**
	 * 京东代付 参数
	 */
	public static final String tradeRequestSSL="https://mapi.jdpay.com/npp10/defray_pay";//会员号
	public static final String customer_no="360080004000861867";//会员号
	public static final String pub="npp_11_API2_pro.cer";//代付的网银对外证书 京东统一对外的证书
	public static final String pri="cert.pfx";//代付的商户证书 平台申请 秘钥文件名（该文件包含公钥和私钥）
	public static final String singKey="360080004000861867abc";//代付使用的签名秘钥sign_key和代付商户证书密码password
	public static final String password="881017";
	public static final String rsaPath="c:\\rsa\\";
	public static final String notify_url="http://";//异步回调地址 供应商提现
	public static final String notify_url2="http://";//异步回调地址 供应商提现
	public static final String notify_url3="http://main.sht315.com/ydg/ydmvc/customerCashForJD/customerCashResultCall.do";
	public static final String payee_account_type="P";//对私户=P；对公户=C
	public static final String trade_currency="CNY";
	public static final String pay_tool="TRAN";//支付工具
	public static final String sign_type="SHA";//签名方式
	public static final String payee_card_type="DE";//卡类型  借记卡=DE；信用卡=CR
	public static final String payee_mobile="13333333333";//持卡人手机
	public static final String Single_day_limit="30000";//单日最高限额 商城自定 单位分
	public static final String weekend="false";//节假日提现开关 true为开 其他为关
	
	/**
	 * 京东支付 参数
	 */
	public static final String key="pBqdzOqCob1GqcrfRliK1FuT9ngKTuC7";//密钥
	public static final String v_mid="110201467001";//商户号
	public static final String v_moneytype="CNY";//货币种类
	public static final String v_url="http://";//支付返回地址 pay工程
	public static final String v_url_member="http://";
	public static final String remark2="[url:=http://123.207.167.176:6555/pay/pc/view/jdpayDirect/returnMsg.do]";//异步回调
	
	/**
	 * 贵州银联代付 参数
	 */
	public static final String ph_custId = "CB0000030105"; //客户号 云平台提供，云平台唯一编号，在云平台注册成功后，登陆云平台即可查询到。 
	
	public static final String custId = "CB0000030109"; //易商通客户号 云平台提供，云平台唯一编号，在云平台注册成功后，登陆云平台即可查询到。 
	
	public static final String test_custId = "CP0000000098"; //客户号 云平台提供，云平台唯一编号，在云平台注册成功后，登陆云平台即可查询到。 
    
	public static final String ph_partnerId = "00000117"; // 合作渠道ID
	
	public static final String partnerId = "00000119"; // 易商通合作渠道ID
	
	public static final String test_partnerId = "00000078"; // 合作渠道ID
   
	public static final String payeeAcctType = "PERSONAL"; //PERSONAL 对私 ， BUSINESS 对公
    
	public static final String ph_notifyUrl = "http://123.207.167.176:10003/pc/view/gzylcash/GZYLCashPay.do"; // 异步通知地址
    
	public static final String bdbd_notifyUrl = "http://124.205.162.42:12302/pc/view/gzylCashDirect/GZYLCashPay.do";
	
	public static final String test_notifyUrl = "http://106.37.208.102:8097/JSB"; // 异步通知地址
    
	public static final String guiyangJSBUrl = "https://yun.unionpay.com:18080/openplatform-webapp/payOrderJSB.do"; //请求地址
    
	public static final String test_guiyangJSBUrl = "http://58.42.236.252:9920/openplatform-webapp/payOrderJSB.do"; //请求地址
    
	public static final String ph_privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL+TwAR9lV3YNtrWpP4L5RY/BuSGlpKC7Yh1U9ELFg55h1i3ZW8OV0mqiIHN07eey7j1pAp2xiAVQZtQZto2KIhvMiSymLa4XJkYWLVcxDboiwlozZ832nML8al3vS/JiZaeL3g7lVA9JhhEFOPS0pkIA1G7zpquYuhPQquXKDHFAgMBAAECgYAm1aeWAXMW2+56vAokKXsd4FbgWlwJhRrrj4UXGh01f/Msy7h3I7wUBcq4LWYekcUQUFMZf+w3srdi6ZB/6XybmbsuCd3NWVDJZh16FH3WPL6eESIp5JMJ4X3/yDAQI/Slu4ZgT603E8GkCKcd1aTYUWNzTcX4fFvDoV96IbbTnQJBAOaU/19Ub1rJ6eNlFEcH/dNpCZTVYoP6k4pggnBTbXA0wNj99EUm2SZac3OtQMSHR/aIZLA5bvo1yrE4pfqQficCQQDUsgnsMgG9w/aB1z2xDUb3BE7gs93/ERlJQ0cXI+s9PXKL5uzPEoOiX1ns/5bwkSa/R4oITTFc4QTuFYQsInAzAkAdmZDyzucAk5z3uPkSaT83TWuSdJYR9S/Nergj7UHGPq9m66rY2bTUjJX3io0e7XLafgXW7XiGnDMp7ui3sqbPAkEAhp7JOP3NrFZDx5p8KXvF1sKbSg2ODMq9vmkerb7GPVSPE41thQKq3jKLsD11Q34bkCWvb+GrxKWHgUmNBoIlkQJBAIVolzTCwGlYNx5vgEdS2OCWKUqBmo1B03HuV1aThMpFXMQ3DWGD3ifUDfr9iZaR1NFF1JQ0x8OdujVDSvmmHak=" ; // 私钥
	
	public static final String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMkt6Oai+Tca1pOhUWaAcp8TY8wMqPj6GxFzI7Xd0jWwx/pXbDpoOk1Pdp/aJ0FOcQfycrAwwrQ4U3vxFx02TsrkR/klSjDGRHEkZcwst2pqBpW2z0TdcKCeY/BFwPm17v5NKSHwEAkcuM3NQFMFyjcZBJp5uLAp40l4bMBo5zqJAgMBAAECgYAEddUdD+3wj9LEESaJ3D0uU7K5umogowLonyHijVl0wUedwERxAxCP2DwsDR0jsIaHA5QRXfNjJYCMVesKRcL3aIVbpkYIAIzNP+144GyJz1hidMDQiwonMCuR1+80l4/3Gb/slaOoI0QDYlifnd2vmh5xrrjzJAlbgyo4IaORAQJBAPExak9XQrCpx9GRDt7psCOhyMVi2VFA+wVRALDepCXcO5Jsdt1Ez0lK3+xk9rOem4+H0qARoVz4kVEJDYqmQSECQQDVh6Pd7wFc0X2tQ5h/xIZR/OKW/eYF/Cj6BQPBGTY+DE0WLHz9Qam36uhabLi7pzViA2w0GfS6JzslJXPRcgRpAkAbyxX1Dpkgwm/ENnMAPO2PLZV1KW7hbht/AVaTCxTfSAGVXepUlRlN2NL06q2DBUPnxj7/MwjlLb+RFvn4gDABAkEAg4+mBCitQz4D3Awo2pgAVmWkSnm3Dvr1WE5cqM3a4NH9cOsKO5QIeLvwWz5fd6cnOfN36IYRhdyEEXgI6wP/+QJBAOwiaOwCoeJgmhI4dqnOqJIeypVpJC877t6kS77ObH15A+KqF4Ym+KQvaJBVGlt5UHJMvne4ldAjFa31UWiV+oM=" ; // 本地报单私钥
    
	public static final String test_privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAOg+PNOota7ppeHwDBB7zttL/OpUmaOCbei/J2t/FLNjumsMjJVGLfdxKlrqCQXVAzAZDIakQcbfRvvlhZJCEBAnL2tru8Qp6hs9dokI2zOXjf9wyk4hPQT5/noXEnlnXO1lr4MZeXhgLKCwZOQfTeE9WSWuTmdA5Dzgu0d/TvuJAgMBAAECgYBBEqdCeyQlFWyYaQVIXRhx09HS6s99xB79twnZker/9LKYKhT+AoMAsSG4BZlvm+bfxDUBSObxTUB7di099OrAw0J1F0QpCXL5Jrxc2NdW8/j1hXb77UbdgsUZg4hM5JkJ2QRxiwT0JyWUAIikSx0W+jUzTFkz1UFaMiZOwEX7rQJBAPcd1+dvclPiR77McvxEpje04dddIiGIQxCw7oZmARMOK33Jrd3+6nTv8xlhFSWM9/xgJzQq8n+aZ4X9+ZXEp9sCQQDwl4RolGsvtp/8jyFBRNGOTQ6CWM/77lK47swzeu50GCFNyf+tLNu8kOhyk+8LIftKFm44m8PZrsZDYZLNDGlrAkA6n0bHrWWGxshUV/XzKGnyDyQATiS5pbSbMg3zriEVHyhsF7r6Te3avc2CuMgmd1Gg+kJymrmaUcu7OqvJvrQ/AkBFQylwPgIZi1bFi6MEOj6l29MofU7q9TJFYSHSVDqfm27DCTsc7MQZphH1Ild3+gFw08JJc7ZPTbxwG3/6ne8fAkBDiUWE/r5GAVrLDFXIqglkG+25B0LPGT2ttTNL2Id/4QAbUXLxGuwmBY52B8m2Y2U8agJDL6YdsE+gvykcH9oB" ; // 私钥
    
	public static final String ph_publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2/H/PnFlbJRxExmmVxIbIU+xvnwRNoN/GRlfMRlesDAqQU2BPcj7iXMfX4NCK632B+Spe+oI6xAo0rJr2qyjCiUgvLl6hvd/okR+Ee9SuDMRXFhqxR0kulDhE0Dfj0dVd6SokUEOBcM16bXH0oCu0DgbiUcRDMfS5p1VxjhrxLAOJ0N4y3dek3t2Lh1fL9ok3aVXpwRzvYjS4SxBt6A03mDoHpksmSYjEA+o1NNR0h0SD0H7f7C7J1reoRqsfjcjjy3pbeCauTkCNB3TJvBVYN4LLRUHkvWeA7GwANvmpDdPYpSWCa9G3akEA7Za+y39rMqU8zCT6FtfsHH9q2OHvQIDAQAB"; // 公钥
    
	public static final String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2/H/PnFlbJRxExmmVxIbIU+xvnwRNoN/GRlfMRlesDAqQU2BPcj7iXMfX4NCK632B+Spe+oI6xAo0rJr2qyjCiUgvLl6hvd/okR+Ee9SuDMRXFhqxR0kulDhE0Dfj0dVd6SokUEOBcM16bXH0oCu0DgbiUcRDMfS5p1VxjhrxLAOJ0N4y3dek3t2Lh1fL9ok3aVXpwRzvYjS4SxBt6A03mDoHpksmSYjEA+o1NNR0h0SD0H7f7C7J1reoRqsfjcjjy3pbeCauTkCNB3TJvBVYN4LLRUHkvWeA7GwANvmpDdPYpSWCa9G3akEA7Za+y39rMqU8zCT6FtfsHH9q2OHvQIDAQAB"; // 本地报单公钥
	
	public static final String test_publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+IMBbuSZVmiilWuGhGm4cgTmw7YBXykebkQkIDJEifj+SZxjMJBsjZ5JqjAFSlPNW+gv9T3UXe5gBQPM8YqB+kwAWtHjzRDlU/kaAq2A+MVCqR44KDNaVK+raiBme1wJ3w0bxDPwxjMPkg2psc0jGuP+lovS3fJwNbkEHRne68wIDAQAB"; // 公钥
	
	
	/**
	 * 易联支付
	 */
	//版本号 | 签名公钥 | 报文密钥密文 | 报文原文密文 | 报文原文签名
	public static final String version = "2.0.0";
	public static final String proccode = "0200";//
	public static final String processcode = "190011";
	public static final String Currency = "CNY";
	public static final String merchantPwd = "123456";
	public static final String MerchantNo = "302020000114";
	public static final String IDCardType = "01";
	public static final String yilianPAYUrl = "https://test.payeco.com:9443/DnaOnlineTest/servlet/DnaPayB2C";
	
	//异步回调
	public static final String bdbd2_asynaddress = "http://123.207.147.211:12311/pc/viwe/ylpay/returnMsg.do?m=font";
	public static final String bdbd1_asynaddress = "http://123.207.147.211:12311/pc/viwe/ylpay/returnMsg.do?m=font";
	public static final String yd_synaddress = "";
	public static final String bd_synaddress = "";
	public static final String synaddress = "";
	
	//同步回调
	public static final String bdbd2_synaddress = "http://123.207.147.211:12311/pay/after/lcoal/online.do?m=back";
	public static final String bdbd1_synaddress = "http://123.207.147.211:12301/order/after/toRechargeRecord/toRechargeRecordList.do?m=back";
	public static final String yd_asynaddress = "";
	public static final String bd_asynaddress = "";
	public static final String asynaddress = "";

}
