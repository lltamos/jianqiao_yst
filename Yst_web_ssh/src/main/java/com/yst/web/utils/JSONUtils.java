package com.yst.web.utils;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

public class JSONUtils {
	private static Log logger = LogFactory.getLog(JSONUtils.class);
	public static void sendToJSON(Object o) {
		try {
			ServletActionContext.getResponse().setContentType("text/html");// 不加这句页面无法解析
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder()
					.excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
			String s = gson.toJson(o).toString();
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().print(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void sendJSON(Object o) {
		try {
			ServletActionContext.getResponse().setContentType("text/html");// 不加这句页面无法解析
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			String s = gson.toJson(o).toString();
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().print(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void springSendJSON(Object o,HttpServletResponse response) {
		try {
			response.setContentType("text/html");// 不加这句页面无法解析
			response.setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			String s = gson.toJson(o).toString();
			System.out.println(s);
			response.getWriter().print(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String getJSON(Object o) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
		String s = gson.toJson(o).toString();
		System.out.println(s);
		return s;
	}
	public static void sendSdiconsJSON(Object o) {
		try {
			ServletActionContext.getResponse().setContentType("text/html");//不加这句页面无法解析
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			String s = JSONMapper.toJSON(o).render(false);
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().print(s);
		} catch (MapperException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T> void sendJSONByType(Object o, Class<T> clazz) {
		Type type = new TypeToken<T>(){}.getType();
		try {
			ServletActionContext.getResponse().setContentType("text/html");// 不加这句页面无法解析
			ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();
			String s = gson.toJson(o,type).toString();
			System.out.println(s);
			ServletActionContext.getResponse().getWriter().print(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
