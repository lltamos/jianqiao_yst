package com.alqsoft.service.impl.wechatpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.rpc.pay.impl.RpcWeChatPayServiceImpl;
import com.alqsoft.service.wechatpay.WeChatPayReturnService;
import com.alqsoft.service.wechatpay.WeChatPayService;

/**
 * 微信支付业务
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月16日 下午4:56:46
 * 
 */
@Service
@Transactional
public class WeChatPayServiceImpl implements WeChatPayService{

	private static Log logger = LogFactory.getLog(RpcWeChatPayServiceImpl.class);
	@Autowired
	private WeChatPayReturnService weChatPayReturnService; 
	@Autowired
	private RpcPayService rpcPayService; 
	
	@Override
	public String sendCodeUnifiedOrder(String orderId, String describe, String totalFee,
			String tradeType, String ip,int formOrder) {
		    String qRfromGoogleUrl="";
			Result result=null;
			try {
				result = rpcPayService.sendCodeUnifiedOrder(orderId, describe, totalFee, tradeType, ip,formOrder);
				logger.info(orderId+"++++"+describe+"++++"+totalFee+"++++++++"+"tradeType"+tradeType+"++++++++++++++++++"+ip+"++++++++++++++++++++"+formOrder);
				logger.info(result);
				logger.info(result.getMsg() + "============="+ result.getCode() +"=============="+result.getContent());
				logger.info("微信支付内部接口正常");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("微信支付内部接口异常");
				e.printStackTrace();
			}
			try {
			if(result.getCode()==1){
				@SuppressWarnings("unchecked")
				Map<String, String> content = (Map)result.getContent();
				qRfromGoogleUrl = content.get("codeurl");
				// qRfromGoogleUrl = QRfromGoogle(prpayUrl);
			}
			//String postData = HttpUtil.postData(qRfromGoogleUrl, null);
			//InputStream qrCodeUtil = QrCode.qrCodeUtil(qRfromGoogleUrl);
			logger.info("返回ur生成支付二维码成功");
			return qRfromGoogleUrl;
		} catch (Exception e) {
			logger.error("返回ur生成支付二维码异常");
			e.printStackTrace();
		}
		return null;
	}
    /**
     * 微信支付回调
     */
	public void weChatCodePayNotity(HttpServletRequest request, HttpServletResponse response,
			Map<String, String> parms) {
		try {
			weChatPayReturnService.weChatCodePayNotity(request, response);
			logger.info("支付回调当中....");
		} catch (Exception e) {
			logger.error("支付回调本地接口出错.....");
			e.printStackTrace();
		}
	}
	
    /**
     * 生成二维码接口
     * @author Yaowei
     * @param  
     * @return String
     * @Time 2017年3月13日
     */
	private  String QRfromGoogle(String chl) throws Exception {  
	    int widhtHeight = 300;  
	    String EC_level = "L";  
	    int margin = 0;  
	    chl = UrlEncode(chl);  
	    String QRfromGoogle = "http://chart.apis.google.com/chart?chs=" + widhtHeight + "x" + widhtHeight  
	            + "&cht=qr&chld=" + EC_level + "|" + margin + "&chl=" + chl;  
	  
	    return QRfromGoogle;  
	}  
	
	/**
	 * 特殊字符处理 
	 * @author Yaowei
	 * @param  
	 * @return String
	 * @Time 2017年3月13日
	 */
	private String UrlEncode(String src)  throws UnsupportedEncodingException {  
	    return URLEncoder.encode(src, "UTF-8").replace("+", "%20");  
	}  
	
}
