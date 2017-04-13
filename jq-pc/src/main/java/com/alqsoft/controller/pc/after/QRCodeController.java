package com.alqsoft.controller.pc.after;

import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.utils.QrCode;

import sun.misc.BASE64Decoder;


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
	@ResponseBody
	public void QRCode(String url,HttpServletResponse response ){

		 try {
			 ServletOutputStream outputStream = response.getOutputStream();
			 BASE64Decoder decoder = new BASE64Decoder(); 
			InputStream qrCodeUtil = QrCode.qrCodeUtil(url);
			byte[] b1 = decoder.decodeBuffer(qrCodeUtil); 
			//BufferedImage Image = new BufferedImage(150, 150,BufferedImage.TYPE_INT_RGB );
			BufferedImage Image = new BufferedImage(150, 150,BufferedImage.TYPE_INT_RGB );

			ImageIO.read(qrCodeUtil);
			//out = response.getOutputStream();  
			
			int b = 0;  
			ImageIO.write(Image, "JIF", response.getOutputStream());
//
//			while ((b = qrCodeUtil.read(b1)) != -1) {  
//			    // 4.写到输出流(out)中  
//				outputStream.write(b1, 0, b); 
			/*byte[] buffer = new byte[1024]; */ 
//			}  
			qrCodeUtil.close();  
			outputStream.flush();  
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}  
		
	}
	
	
	
	
	
	
}
