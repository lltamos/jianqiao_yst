package com.yst.web.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yst.web.model.AppResult;

import javassist.Modifier;

public class BeanUtils {
	// 合并目标到原数据类
	private static Log logger = LogFactory.getLog(BeanUtils.class);

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
				Object value = null;
				Object sValue = null;
				try {
					value = m.invoke(target);
					if (value != null && !value.equals("")) {
						if (m.getName().startsWith("getImage")) {// 删除已存在图片
							sValue = m.invoke(source);
							if (sValue != null && !sValue.equals("")) {
								File f = new File(ServerParam.ROOT_PATH
										+ sValue.toString());
								boolean isDelete = f.delete();
								logger.debug("______________image_delete:"
										+ isDelete);
							}
						}
						if (m.getName().startsWith("getDetail_url")) {// 删除已存在静态页面
							sValue = m.invoke(source);
							if (sValue != null && !sValue.equals("")) {
								File f = new File(ServerParam.ROOT_PATH
										+ sValue.toString());
								boolean isDelete = f.delete();
								logger.debug("______________html_delete:"
										+ isDelete);
							}
						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				} finally {
					try {
						if (value != null && !value.equals("")) {
							Method setterMethod = sourceClass.getMethod(
									"set"
											+ propertyName.substring(0, 1)
													.toUpperCase()
											+ propertyName.substring(1), m
											.getReturnType());
							setterMethod.invoke(source, value);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error(e.getMessage());
					}
				}
			}

		}
		return (T) source;
	}

	// 得到对象中所有image属性放入map
	public static Map<String, String> getImageMap(Object source) {
		Map<String, String> imageMap = new HashMap<String, String>();// 初始化imageMap
		Class sourceClass = source.getClass();
		for (Method m : sourceClass.getDeclaredMethods()) {
			if (Modifier.toString(m.getModifiers()).indexOf("public") != -1
					&& m.getName().startsWith("getImage")) {// 是否为image属性
				Object value = null;
				String propertyName = m.getName().substring(3, 4).toLowerCase()
						+ m.getName().substring(4);
				try {
					value = m.invoke(source);
					if (value != null && !value.equals("")) {
						imageMap.put(propertyName, value.toString());
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
		logger.debug("getImageMap方法:imageMap.size:" + imageMap.size());
		return imageMap;
	}

	// 把map中所有url给对象相应属性
	public static <T> T setMaptoBean(Map<String, String> targetMap,
			Object source) {
		Class sourceClass = source.getClass();
		Set<Entry<String, String>> set = targetMap.entrySet();
		for (Entry<String, String> entry : set) {
			String name = entry.getKey();
			String url = entry.getValue();
			try {
				Method setter = sourceClass.getMethod(
						"set" + name.substring(0, 1).toUpperCase()
								+ name.substring(1), String.class);
				setter.invoke(source, url);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error(e.getMessage());
			}
		}
		return (T) source;
	}

	// 得到对象中所有image属性转成base64字符串
	public static Object getBase64Image(Object source) {
		Class sourceClass = source.getClass();
		for (Method m : sourceClass.getDeclaredMethods()) {
			if (Modifier.toString(m.getModifiers()).indexOf("public") != -1
					&& m.getName().startsWith("getImage")) {// 是否为image属性
				Object value = null;
				String propertyName = m.getName().substring(3, 4).toLowerCase()
						+ m.getName().substring(4);
				try {
					value = m.invoke(source);
					if (value != null && !value.equals("")) {
						Method setterMethod = sourceClass.getMethod("set"
								+ propertyName.substring(0, 1).toUpperCase()
								+ propertyName.substring(1), m.getReturnType());
						setterMethod.invoke(source,
								UpLoadUtils.getImageBase64(value.toString()));
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error(e.getMessage());
				}
			}
		}
		return source;
	}

	public static AppResult uploadImage(Object o, String module) {
		AppResult appResult = new AppResult();
		appResult.setVersion(AppResult.VERSION);
		appResult.setResult(AppResult.FAILED);
		Map<String, String> imageMap = BeanUtils.getImageMap(o);
		if (imageMap.size() != 0) {
			appResult = UpLoadUtils.saveFile(imageMap, module);
			if (appResult.getResult().equals(AppResult.SUCCESS)) {
				BeanUtils.setMaptoBean(appResult.getImg_urls(), o);
			}
		} else {
			appResult.setResult(AppResult.NO_IMAGE);
		}
		return appResult;
	}

	public static String[] getStringArray(Class<?> oClass) {
		Field[] fields = oClass.getDeclaredFields();
		List<String> list = new ArrayList<String>();
		for (Field f : fields) {
			if (f.isAnnotationPresent(com.yst.web.annotations.Field.class)) {
				if(f.getAnnotation(com.yst.web.annotations.Field.class).name().equals("")){
					list.add(f.getName());
				}else{
					list.add(f.getAnnotation(com.yst.web.annotations.Field.class).name());
				}
			}
//			else if (f.isAnnotationPresent(IndexedEmbedded.class)) {
//				if (f.getName().equals("merchant")) {
//					logger.debug(f.getName());
//					list.add(f.getName() + ".name");
//				}
//			}
		}
		String[] keywords = new String[list.size()];
		return list.toArray(keywords);
	}
	/**
	 * map转实体类
	 * 
	 * @param oClass
	 * @param map
	 * @return
	 */
	public static <T> T convertMap(Class<?> oClass, Map map) {
		Object obj = null;
		if (map == null) {
			return null;
		}
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(oClass); // 获取类属性
			obj = oClass.newInstance();
			// 给 JavaBean 对象的属性赋值
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (map.containsKey(propertyName)) {
					// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
					try {
						Object value = map.get(propertyName);
						Object[] args = new Object[1];
						args[0] = value;
						descriptor.getWriteMethod().invoke(obj, args);
					} catch (Exception e) {
						logger.info(e.getMessage());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T) obj;
	}
}
