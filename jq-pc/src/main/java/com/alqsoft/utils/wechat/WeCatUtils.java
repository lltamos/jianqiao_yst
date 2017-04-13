package com.alqsoft.utils.wechat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.alqframework.pay.weixin.util.MD5Util;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


/**
 * 微信工具了类
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 下午2:35:53
 * 
 */
public class WeCatUtils {

	/**
	 * 取出一个指定长度大小的随机正整数.  length小于11
	 * @author Yaowei
	 * @param  
	 * @return int
	 * @Time 2017年3月10日
	 */
    public static int buildRandom(int length) {  
        int num = 1;  
        double random = Math.random();  
        if (random < 0.1) {  
            random = random + 0.1;  
        }  
        for (int i = 0; i < length; i++) {  
            num = num * 10;  
        }  
        return (int) ((random * num));  
    }  
    /**
     * 将请求参数转换为xml格式的string  
     * @author Yaowei
     * @param  
     * @return String
     * @Time 2017年3月10日
     */
    public static String getRequestXml(SortedMap<Object, Object> parameters) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }
    
   /**
    * 签名生成  
    * @author Yaowei
    * @param  1  packageParams 参数   2  characterEncoding 编码格式
    * @return String
    * @Time 2017年3月10日
    */
    public static String createSign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = (String) entry.getValue();  
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toUpperCase();  
        return sign;  
    }  
    /**
     * 是否签名正确,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。 
     * @author Yaowei
     * @param  
     * @return boolean
     * @Time 2017年3月10日
     */
    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if(!"sign".equals(k) && null != v && !"".equals(v)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
          
        sb.append("key=" + API_KEY);  
          
        //算出摘要  
        String mysign = MD5Util.MD5Encode(sb.toString(), characterEncoding).toLowerCase();  
        String tenpaySign = ((String)packageParams.get("sign")).toLowerCase();  
          
        //System.out.println(tenpaySign + "    " + mysign);  
        return tenpaySign.equals(mysign);  
    }  
    
    
    private static String[] IGNORE = new String[]{
            "sign_type", "sign_data", "encrypt_type", "encrypt_data","salt","signature"
    };
    /**
     * 讲指定map类型的参数用"=" "&"拼接成一个string串
     * @author Yaowei
     * @param  
     * @return String
     * @Time 2017年3月10日
     */
    public static String mapToLinkString(Map<String, Object> map) {
        ArrayList<String> mapKeys = new ArrayList<String>(map.keySet());
        Collections.sort(mapKeys);
        StringBuilder link = new StringBuilder();
        boolean first = true;
        for_map_keys:
        for(String key: mapKeys) {
            Object obj= map.get(key);
            String value ="";
            if(obj!=null) value=((String[])obj)[0];
            if ( "".equals(value.trim())) continue;
            for(String i: IGNORE) {
                if (i.equalsIgnoreCase(key)) continue for_map_keys;
            }
            if (!first) link.append("&");
            link.append(key).append("=").append(value);
            if (first) first = false;
        }
        return link.toString();
    }
    /** 
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。 
     * @param strxml 
     * @return 
     * @throws JDOMException 
     * @throws IOException 
     */  
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {  
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");  
  
        if(null == strxml || "".equals(strxml)) {  
            return null;  
        }  
          
        Map m = new HashMap();  
          
        InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));  
        SAXBuilder builder = new SAXBuilder();  
        Document doc = builder.build(in);  
        Element root = doc.getRootElement();  
        List list = root.getChildren();  
        Iterator it = list.iterator();  
        while(it.hasNext()) {  
            Element e = (Element) it.next();  
            String k = e.getName();  
            String v = "";  
            List children = e.getChildren();  
            if(children.isEmpty()) {  
                v = e.getTextNormalize();  
            } else {  
                v = WeCatUtils.getChildrenText(children);  
            }  
              
            m.put(k, v);  
        }  
          
        //关闭流  
        in.close();  
          
        return m;  
    }  
    
    /** 
     * 获取子结点的xml 
     * @param children 
     * @return String 
     */  
    public static String getChildrenText(List children) {  
        StringBuffer sb = new StringBuffer();  
        if(!children.isEmpty()) {  
            Iterator it = children.iterator();  
            while(it.hasNext()) {  
                Element e = (Element) it.next();  
                String name = e.getName();  
                String value = e.getTextNormalize();  
                List list = e.getChildren();  
                sb.append("<" + name + ">");  
                if(!list.isEmpty()) {  
                    sb.append(WeCatUtils.getChildrenText(list));  
                }  
                sb.append(value);  
                sb.append("</" + name + ">");  
            }  
        }  
          
        return sb.toString();  
    } 
    /**
     * 判断是否为空
     * @author Yaowei
     * @param  
     * @return boolean
     * @Time 2017年3月10日
     */
    public static boolean isNull(Object o){
		if(o==null || o.equals("")){
			return true;
		}
		return false;
	}
    /**
     * 把原有对象的参数复制到当前对象中
     * @author Yaowei
     * @param  
     * @return T
     * @Time 2017年3月12日
     */
	public static <T> T copy(Object target, Object source) {
		Class sourceClass = source.getClass();
		Class targerClass = target.getClass();

		for (Method m : targerClass.getDeclaredMethods()) {
			if (Modifier.toString(m.getModifiers()).indexOf("public") != -1
					&& m.getName().startsWith("get")
					|| m.getName().startsWith("is")) {
				String propertyName = m.getName().startsWith("get") ? m
						.getName().substring(3, 4).toLowerCase()
						+ m.getName().substring(4) : m.getName()
						.substring(2, 3).toLowerCase()
						+ m.getName().substring(3);
			}
		}
		return (T) source;
	}
}
