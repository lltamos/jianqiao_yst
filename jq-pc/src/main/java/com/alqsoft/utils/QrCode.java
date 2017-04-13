package com.alqsoft.utils;

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class QrCode
{
    /**
     * 生成二维码
     * @param content 内容
     * @return
     * @throws WriterException
     */
    public static InputStream qrCodeUtil(String content) throws WriterException
    {
        try
        {
            QrCode zp = new QrCode();
            BufferedImage bim = zp.getQR_CODEBufferedImage(content, BarcodeFormat.QR_CODE, 330, 330, zp.getDecodeHintType());
            //BufferedImage resultBim= zp.addLogo_QRCode(bim, new File(logo), new LogoConfig());
            if(bim==null){
                return null;
            }
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(bim, "gif", os);
            InputStream is = new ByteArrayInputStream(os.toByteArray());
            return is;
        }
        catch (Exception e)
        {
           return null;
        }
    }

    /**
     * 给二维码图片添加Logo
     *
     * @param qrPic
     * @param logoPic
     */
    public BufferedImage addLogo_QRCode(BufferedImage qrPic, File logoPic, LogoConfig logoConfig)
    {
        try
        {
            /**
             * 读取二维码图片，并构建绘图对象
             */
            Graphics2D g = qrPic.createGraphics();
            /**
             * 读取Logo图片
             */
            BufferedImage logo = ImageIO.read(logoPic);
            int widthLogo = logo.getWidth(), heightLogo = logo.getHeight();
            // 计算图片放置位置
            int x = (qrPic.getWidth() - widthLogo) / 2;
            int y = (qrPic.getHeight() - logo.getHeight()) / 2;
            //开始绘制图片
            g.drawImage(logo, x, y, widthLogo, heightLogo, null);
            g.drawRoundRect(x, y, widthLogo, heightLogo, 20, 20);
            g.setStroke(new BasicStroke(logoConfig.getBorder()));
            g.setColor(logoConfig.getBorderColor());
            g.drawRect(x, y, widthLogo, heightLogo);
            g.dispose();
            /*ImageIO.write(qrPic, "jpeg", new File("D:/newPic.jpg"));*/
            return qrPic;
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * 生成二维码bufferedImage图片
     *
     * @param content
     *            编码内容
     * @param barcodeFormat
     *            编码类型
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     * @param hints
     *            设置参数
     * @return
     */
    public BufferedImage getQR_CODEBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints)
    {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try
        {
            multiFormatWriter = new MultiFormatWriter();

            // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);

            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            // 开始利用二维码数据创建Bitmap图片，分别设为黑（0xFFFFFFFF）白（0xFF000000）两色
            for (int x = 0; x < w; x++)
            {
                for (int y = 0; y < h; y++)
                {
                    image.setRGB(x, y, bm.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码的格式参数
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType()
    {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);

        return hints;
    }
}

class LogoConfig
{
    // logo默认边框颜色
    public static final Color DEFAULT_BORDERCOLOR = Color.WHITE;
    // logo默认边框宽度
    public static final int DEFAULT_BORDER = 2;
    // logo大小默认为照片的1/5
    public static final int DEFAULT_LOGOPART = 5;

    private final int border = DEFAULT_BORDER;
    private final Color borderColor;
    private final int logoPart;

    public LogoConfig()
    {
        this(DEFAULT_BORDERCOLOR, DEFAULT_LOGOPART);
    }

    public LogoConfig(Color borderColor, int logoPart)
    {
        this.borderColor = borderColor;
        this.logoPart = logoPart;
    }

    public Color getBorderColor()
    {
        return borderColor;
    }

    public int getBorder()
    {
        return border;
    }

    public int getLogoPart()
    {
        return logoPart;
    }
}