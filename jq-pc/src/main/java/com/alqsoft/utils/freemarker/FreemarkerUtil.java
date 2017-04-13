package com.alqsoft.utils.freemarker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alqsoft.utils.alipay.util.UtilDate;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	private static Log logger = LogFactory.getLog(FreemarkerUtil.class);

	/**
	 * 
	 * @param templateName
	 *            模板文件名称
	 * @param templateEncoding
	 *            模板文件的编码方式
	 * @param root
	 *            数据模型根对象
	 */
	public static void analysisTemplate(String templateName, String templateEncoding, Map<?, ?> root,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String BASE_PATH = request.getRealPath("/");
			/**
			 * 创建Configuration对象
			 */
			Configuration config = new Configuration();

			/**
			 * 指定模板路径
			 */
			File file = new File(BASE_PATH + "/ftl/");
			/**
			 * 设置要解析的模板所在的目录，并加载模板文件
			 */
			config.setDirectoryForTemplateLoading(file);
			/**
			 * 设置包装器，并将对象包装为数据模型
			 */
			config.setObjectWrapper(new DefaultObjectWrapper());

			/**
			 * 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			 */
			Template template = config.getTemplate(templateName, templateEncoding);
			/**
			 * 合并数据模型与模板
			 */
			// Writer out = new OutputStreamWriter(new FileOutputStream(
			// "Test.html"), "GBK"); // 输出流
			PrintWriter out = response.getWriter();
			template.process(root, out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

	}

	public static String make(String templateName, String templateEncoding, Map<?, ?> root,
			HttpServletRequest request) {
		Configuration cfg = new Configuration();
		// 项目真实路径
		String BASE_PATH = request.getRealPath("/");
		// 新闻模板路径
		String templatePath = BASE_PATH + "ftl/";
		logger.info(templatePath);
		// 存放在news目录下的以yyyymmdd表示的子目录中,即按天数来存放
		String cDateStr = "html/";

		// 文件后缀
		String filePostfix = ".html";

		// 静态新闻生成文件存放路径
		String path = BASE_PATH + cDateStr;

		String fileTimeName = UtilDate.getOrderNum();
		// 写到数据库地路径
		String returnFileName = cDateStr + fileTimeName + filePostfix;
		String fileName = "";
		File newsDir = new File(path);

		fileName = path + fileTimeName + filePostfix;
		if (!newsDir.exists()) {
			newsDir.mkdirs();
			fileName = path + fileTimeName + filePostfix;
		}
		try {
			logger.info(new File(templatePath).getPath());
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template newsTemplate = cfg.getTemplate(templateName, templateEncoding);
			Writer out = new OutputStreamWriter(new FileOutputStream(fileName), templateEncoding);
			try {
				newsTemplate.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnFileName;
	}

	/**
	 * 重新生成html文件,使用原来的文件名
	 *
	 * @param news
	 * @param templateParam
	 */
	public static void update(String templateName, String fileName, String templateEncoding, Map<?, ?> root,
			HttpServletRequest request) {
		Configuration cfg = new Configuration();
		// 项目真实路径
		String BASE_PATH = request.getRealPath("/");
		// 新闻模板路径
		String templatePath = BASE_PATH + "ftl/";
		// 原来的路径,当删除原来的目录时,重建目录
		String path = BASE_PATH + fileName.substring(0, fileName.lastIndexOf("/"));
		fileName = BASE_PATH + fileName;
		File oldDir = new File(path);
		if (!oldDir.exists()) {
			oldDir.mkdirs();
		}
		// 注意下面是用freemarker来处理的了.
		try {
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template newsTemplate = cfg.getTemplate(templateName, templateEncoding);
			Writer out = new OutputStreamWriter(new FileOutputStream(fileName), templateEncoding);
			try {
				newsTemplate.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}