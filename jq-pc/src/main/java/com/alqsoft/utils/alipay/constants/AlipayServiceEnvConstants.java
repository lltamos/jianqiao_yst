

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.alqsoft.utils.alipay.constants;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOzLsxeI5NtuIb7SiD0BlwcnYs"
			+ "4qEE/EEoqieuOKWC2w3T1QRn0hmVBYVSiNGKZjR08JbRQoHqJ4hZcApqa+XM89e6"
			+ "NVAmpNHwPjHftTKlrvdV99dvB0mp2kPXKUx5BrY9mcNqS/cn9IGp7w/46bTVmIS9"
			+ "lUIVBW67CnITO4BL/QIDAQAB";
    												
    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "UTF-8";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "UTF-8";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";
    
    
    public static final String PARTNER           = "2088421425680042";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
    public static final String APP_ID            = "2016071501622721";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = 
    		"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM7MuzF4jk224hvt"
			+ "KIPQGXBydizioQT8QSiqJ644pYLbDdPVBGfSGZUFhVKI0YpmNHTwltFCgeoniFlw"
			+ "Cmpr5czz17o1UCak0fA+Md+1MqWu91X3128HSanaQ9cpTHkGtj2Zw2pL9yf0ganv"
			+ "D/jptNWYhL2VQhUFbrsKchM7gEv9AgMBAAECgYEAivq0Y07t8/SQiMwdilh0gEms"
			+ "egXkyQDumXGhMUkIkqS86jwitdNRNgF71Df2+tgtMNnvwbq8rKzcyZ6uoU7aJGkK"
			+ "uVITsCykP7EMqM2KdXsedBXGIXdwW5D9I+MT9mh17oSX4pAaff6BQIGiXPFUglEp"
			+ "1d+lKxOYJNzuYBgU1oECQQD5Pb+ZZ3b16EIslyFi/tIFO2mZgZFH0hgNtgl0ADKr"
			+ "y9P/DaMDeZzzLfA/e2fRUnx61H/DEmrvio0n19Io4/Y5AkEA1GhZz5wiyhy73xTQ"
			+ "0J047bdHLfyjnXc6AE2bTVPApRpP9/8K3pU41fKqHXy4mQ/EnKE0ZXopxGgMML+c"
			+ "MkVj5QJBAMhIzoa/leDV2xdp+vJKQwrhS2KHs+867QlXlxEYd5+GE72Jf81zFKR1"
			+ "7fGVgT+8QTuTN3fSfQwxEm1KCR/C0EECQEco/o2zPWSA4+AE1UBOq/fX4xzg+t+S"
			+ "Rfg8B7+MQ4oMmqX25+lSDMyAk8V1N1LwGWpwB2HH355RTWQ3jVi6LpECQAZWNLYW"
			+ "DpH7snw2Da3nfqbx1kAOwSMWV8/EqGXdhoJAzKfjP8cPjGAxfXu0TAjIG03dvBal"
			+ "Tisn8pXanLb3oyI=";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/lrgK0+fjFLNTuK2V8TZ/536kRKzfnRe5ZUtBWBZazyuxQfJyqKslsaMzG9Cng3OQ+l/UPFhMWtA0/6uDYXdred0okFaEoSf3wyYKQpzNAbPjRq6KumK0m/4YfD83q155we/7PDC0cRbNP8MOYu+GPlLQCV6AsJ9+c3E6E7THsQIDAQAB";

    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "http://openapi.alipaydev.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}