package com.yst.web.utils;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yst.web.model.Customer;

public class CommUtils {
	private static Log logger = LogFactory.getLog(CommUtils.class);
	public static void printRequest(HttpServletRequest request){
		System.out.println("--------------------encoding:"+request.getCharacterEncoding());
		System.out.println("--------------------uri:"+request.getRequestURI());
		System.out.println("--------------------url:"+request.getRequestURL());
		System.out.println("--------------------params:"+request.getQueryString());
	}
	
	// 获取ip
		public static String getIpAddr(HttpServletRequest request) {
			if (null == request) {
				return null;
			}

			String proxs[] = { "X-Forwarded-For", "Proxy-Client-IP",
					"WL-Proxy-Client-IP", "HTTP_CLIENT_IP", "HTTP_X_FORWARDED_FOR" };

			String ip = null;

			for (String prox : proxs) {
				ip = request.getHeader(prox);
				if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
					continue;
				} else {
					break;
				}
			}

			if (StringUtils.isBlank(ip)) {
				return request.getRemoteAddr();
			}

			return ip;
		}
		
		/**
		 * 发送短信 http Get请求 请求Url:"http://sms.1xinxi.cn/asmx/smsservice.aspx?” 参数说明
		 * name = "wbhsqfwzx"; pwd = "9FA6EB4D075A98F84F896CF0D451"; type = "pt";
		 * 以上三个参数是固定的 Content：发送内容+ "【社区服务中心】” 注意必须加上【社区服务中心】 否则是发送不了的 Mobile：手机号
		 * 例子：http://sms.1xinxi.cn/asmx/smsservice.aspx? name = wbhsqfwzx& pwd =
		 * 9FA6EB4D075A98F84F896CF0D451& type =
		 * pt&Mobile=12345678909&Content=验证码XXX【社区服务中心】
		 * 
		 * 返回结果是json对象，解析后 Code 此参数=0代表发送成功 Msg 失败后的消息 其他几个参数不是很重要
		 * 
		 * @return
		 */
		public static String sendMessage(String content, String phone) {
			logger.debug("开始发送短信。。。。。。");
			String mobile = phone;
			String url = "http://sms.1xinxi.cn/asmx/smsservice.aspx?";
			String param = "";
//			String name = "wbhsqfwzx";
//			String pwd = "9FA6EB4D075A98F84F896CF0D451";
			String name="yishangtong";
			String pwd ="9FA6EB4D075A98F84F896CF0D451";
			String type = "pt";
			String cont = content + "【社区服务中心】";
			param = "name=" + name + "&pwd=" + pwd + "&type=" + type + "&Mobile="
					+ mobile + "&Content=" + cont;
			logger.debug(url + param);
			HttpClientObject hco = new HttpClientObject();
			hco.setGet(url + param);
			long start = System.currentTimeMillis();
			hco.submit();
			long end = System.currentTimeMillis();
			logger.debug("____________用时：" + (end - start));
			String str = hco.getResponseString();
			logger.debug(str + "   结束发送短信。。。。。。");
			return str;

		}
		
		public int getRandom() {
			int num = (int) (Math.random() * 9000 + 1000);
			return num;
		}
		

		public static Integer getMerchant_id(Map session){
			Integer merchant_id=null;
			Customer dbCustomer = (Customer) session
					.get(ServerParam.CUSTOMER_SESSION);
			if (dbCustomer != null) {
				if (dbCustomer.getMerchant() != null) {
					merchant_id = dbCustomer.getMerchant().getMerchant_id();
				}
			}
			return merchant_id;
		}
		public static boolean isNull(Object o){
			if(o==null || o.equals("")){
				return true;
			}
			return false;
		}
}
