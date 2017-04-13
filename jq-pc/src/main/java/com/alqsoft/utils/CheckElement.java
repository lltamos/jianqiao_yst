package com.alqsoft.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class CheckElement {

	 public static String send(String url, Map<String,String> map,String encoding) throws Exception{  
	        String body = "";  
	  
	        //创建httpclient对象  
	        CloseableHttpClient client = HttpClients.createDefault();  
	        //创建post方式请求对象  
	        HttpPost httpPost = new HttpPost(url);  
	          
	        //装填参数  
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
	        if(map!=null){  
	            for (Entry<String, String> entry : map.entrySet()) {  
	                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
	            }  
	        }  
	        //设置参数到请求对象中  
	        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));  
	  
	        System.out.println("请求地址："+url);  
	        System.out.println("请求参数："+nvps.toString());  
	          
	        //设置header信息  
	        //指定报文头【Content-type】、【User-Agent】  
	        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");  
	        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");  
	          
	        //执行请求操作，并拿到结果（同步阻塞）  
	        CloseableHttpResponse response = client.execute(httpPost);  
	        //获取结果实体  
	        HttpEntity entity = response.getEntity();  
	        if (entity != null) {  
	            //按指定编码转换结果实体为String类型  
	            body = EntityUtils.toString(entity, encoding);  
	        }  
	        EntityUtils.consume(entity);  
	        //释放链接  
	        response.close();  
	        return body;  
	    }  
	
	public Result checkTwoElement(String name,String idCard) throws Exception{
		String url= "http://123.207.175.228:5200/mobile/view/checkInfoCard/checkUserInfo";
		
		Map<String,String> checkMap =new HashMap<String,String>();
		checkMap.put("name",name);
		checkMap.put("idCard",idCard);
		String send = send(url, checkMap, "utf-8");
		 Boolean check = (Boolean)JSONObject.parse(send);
		 if(!check){
			 return ResultUtils.returnError("您输入的身份证和姓名不一致，请重新填写");
		 }else{
			 return ResultUtils.returnSuccess("姓名身份证一致");
		 }
	}
	
	
	
	
	public  Result checkThreeElement(String name,String idCard,String bankNo) throws Exception{
		String threeUrl= "http://123.207.175.228:5200/mobile/view/jdCheck/checkJDCard";
		List<NameValuePair> list=new ArrayList();
		list.add(new BasicNameValuePair("name",name));
		list.add(new BasicNameValuePair("cardNum",idCard));
		list.add(new BasicNameValuePair("bankCardNo",bankNo));
		
Map<String, Object> jdMap = HttpRequestUtils.httpPost(threeUrl, list);
if(jdMap==null){
	 return ResultUtils.returnError("验证身份信息通讯出现错误，请联系客服");
}
Integer  jdType= Integer.parseInt(jdMap.get("code")+"");

if(!jdType.equals(1) && !jdType.equals(3)){
	 return ResultUtils.returnError("您绑定的身份证号和银行卡号不一致，请核对后再试");  
   }


if(!jdType.equals(1) && !jdType.equals(3)){
	 return ResultUtils.returnError("您绑定的身份证号和银行卡号不一致，请核对后再试");  
  }else{
	 return ResultUtils.returnSuccess("姓名、银行卡、身份证验证一致");
  }
	}
	
	@Test	
	public void test() throws Exception{
//Result checkThreeElement = checkTwoElement("祖铭阳","230202199301190336");
		Result checkThreeElement = checkThreeElement("祖铭阳","230202199301190336","6214830153814073");
		System.out.println(checkThreeElement.getMsg()+""+checkThreeElement.getCode());
	}
	
	   public static Map<String,Object> httpPost(String url,List<NameValuePair> nvps){
	       return httpPost(url,nvps, false);
	   }
	
	   public static Map<String,Object> httpPost(String url,List<NameValuePair> nvp, boolean noNeedResponse){
		   //post请求返回结果
	       DefaultHttpClient httpClient = new DefaultHttpClient();
	       JSONObject jsonResult = null;
	       HttpPost method = new HttpPost(url);
	       try {
	           if (null != nvp&&nvp.size()>0) {
	               //解决中文乱码问题
	               method.setEntity(new UrlEncodedFormEntity(nvp));
	           }
	           HttpResponse result = httpClient.execute(method);
	           url = URLDecoder.decode(url, "UTF-8");
	           /**请求发送成功，并得到响应**/
	          
	           if (result.getStatusLine().getStatusCode() == 200) {
	               String str = "";
	               try {
	                   /**读取服务器返回过来的json字符串数据**/
	                   str = EntityUtils.toString(result.getEntity());
	                   if (noNeedResponse) {
	                       return null;
	                   }
	                   /**把json字符串转换成json对象**/
	                   jsonResult = JSONObject.parseObject(str);
	               } catch (Exception e) {
	               }
	           }
	       } catch (IOException e) {
	    	   e.printStackTrace();
	       }
	       return jsonResult;
	   }
}
