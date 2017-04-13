package com.alqsoft.controller.pay.wechat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alqsoft.utils.QrCode;

import io.netty.handler.codec.http.HttpResponse;

/**
 * 二维码生成
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月17日 上午9:39:44
 * 
 */
@Controller
@RequestMapping("wechat/pay")
public class QRCodeController {
	@RequestMapping("QRCode")
	public void QRCode(String url,HttpServletResponse response ){

		 try {
			 ServletOutputStream outputStream = response.getOutputStream();
			
			InputStream qrCodeUtil = QrCode.qrCodeUtil(url);
			System.out.println("1111");
			//out = response.getOutputStream();  
			int b = 0;  
			byte[] buffer = new byte[1024];  
			while ((b = qrCodeUtil.read(buffer)) != -1) {  
			    // 4.写到输出流(out)中  
				outputStream.write(buffer, 0, b);  
			}  
			qrCodeUtil.close();  
			outputStream.flush();  
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
	}
	
}
