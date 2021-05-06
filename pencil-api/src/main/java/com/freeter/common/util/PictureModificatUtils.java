package com.freeter.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.io.*;
import java.net.URL;
/**
 * @ClassName PictureModificatUtils图片修改操作类的工具类
 * @Author WangKui
 * @Date 2020/5/25 22:21
 * @Version 1.0
 **/

public class PictureModificatUtils {
    private static Log logger = LogFactory.get(PictureModificatUtils.class);

    /**
     * @return void
     * @Description:在图片的固定位置写入文字
     * @创建人 pf
     */
    public static void modifyImage(BufferedImage img, String content, int x, int y, Color color, Font font) {
        try {
            //获取原始图片的宽和高
            Graphics2D gNew = img.createGraphics();
            gNew.setColor(color);
            // 设置字体、字型、字号
            gNew.setFont(font);
            // 写入文字
            //字体失真处理
            gNew.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            gNew.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
            gNew.drawString(content, x, y);
        } catch (Exception e) {
            logger.error("modifyImage error:{}", e);
        }
        return;
    }

    public static BufferedImage mergePic(BufferedImage big, BufferedImage small, int x, int y) {
        //2.大图合并小图
        Graphics2D g = big.createGraphics();
        g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
        g.dispose();
        return big;
    }

    /**
     * 传入的图像必须是正方形的 才会 圆形 如果是长方形的比例则会变成椭圆的
     *
     * @param bi1 用户头像地址
     * @return
     * @throws IOException
     */
    public static BufferedImage convertCircular(BufferedImage bi1, float strokeWidth) {
        // 透明底的图片
        BufferedImage bi = new BufferedImage(bi1.getWidth(), bi1.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = bi.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
        int border = 1;
        Ellipse2D.Double shape = new Ellipse2D.Double(border, border, bi1.getWidth() - border * 2, bi1.getHeight() - border * 2);
        graphics.setClip(shape);
        // 使用 setRenderingHint 设置抗锯齿
        graphics.drawImage(bi1, border, border, bi1.getWidth() - border * 2, bi1.getHeight() - border * 2, null);
        graphics.dispose();

        //在圆图外面再画一个圆
        //新创建一个graphics，这样画的圆不会有锯齿
        graphics = bi.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
        //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
        Stroke s = new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        graphics.setStroke(s);
        graphics.setColor(Color.WHITE);
        graphics.drawOval(border, border, bi1.getWidth() - border * 2, bi1.getHeight() - border * 2);
        graphics.dispose();
        return bi;
    }

    /**
     * 缩小Image，此方法返回源图像按给定宽度、高度限制下缩放后的图像
     *
     * @param inputImage
     * @param newWidth   ：压缩后宽度
     * @param newHeight  ：压缩后高度
     * @throws java.io.IOException return
     */
    public static BufferedImage scaleByPercentage(BufferedImage inputImage, int newWidth, int newHeight) {
        // 获取原始图像透明度类型
        int type = inputImage.getColorModel().getTransparency();
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        // 开启抗锯齿
        RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // 使用高质量压缩
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        BufferedImage img = new BufferedImage(newWidth, newHeight, type);
        Graphics2D graphics2d = img.createGraphics();
        graphics2d.setRenderingHints(renderingHints);
        graphics2d.drawImage(inputImage, 0, 0, newWidth, newHeight, 0, 0, width, height, null);
        graphics2d.dispose();
        return img;
    }

    /**
     * 无二维码的海报的图
     */
    private static final String POSTER_URL = "";

    /*public static void main(String[] args) throws IOException {
        String name = "哈哈你好啊";
        String ticket = "gQFj8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyUjRUQU1zLXdkSmsxb0phMU50YzkAAgTVh0FdAwRYAgAA";
        //无二维码的基础图片
        BufferedImage initImage = ImageIO.read(new URL(POSTER_URL));
        //头像
        BufferedImage headImage = ImageIO.read(new URL("https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epGVJJ7HuTOuOia3qhjzNjTwc30Ls3yjhVRaWPIptSZwX74Iu00uhcoFGJp2UWKglqibJ3IicpeI7PQQ/132"));

        BufferedImage codeImage = ImageIO.read(new URL(POSTER_URL));

        headImage = scaleByPercentage(headImage, 58, 58);
        headImage = convertCircular(headImage, 0.5f);
        initImage = mergePic(initImage, headImage, 27, 19);

        Color color = new Color(86, 130, 255);
        Font font = new Font("宋体", Font.BOLD, 25);
        FontMetrics fm = initImage.createGraphics().getFontMetrics(font);

        String temp = getNameString(name, fm);
        String suffix = "...";
        boolean isSuffix = false;
        if (temp.endsWith(suffix)) {
            isSuffix = true;
            temp = temp.substring(0, temp.length() - 3);
        }
        modifyImage(initImage, temp, 95, 58, color, font);
        if (isSuffix) {
            int width = fm.stringWidth(temp);
            modifyImage(initImage, suffix, 95 + width, 58, color, font);
        }


        //拼接二维码
        byte[] bytes = WxAccountManagerUtils.getQrCode(ticket);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        BufferedImage qrCodeImage = ImageIO.read(inputStream);
        qrCodeImage = PictureModificatUtils.scaleByPercentage(qrCodeImage, 190, 190);
        PictureModificatUtils.mergePic(initImage, qrCodeImage, 280, 1075);

        File file = new File("a.png");
        ImageIO.write(initImage, "PNG", file);
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        ImageIO.write(initImage, "PNG", out);
//        byte[] byteArray = out.toByteArray();
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
//        ossService.uploadFile("tax/wx/share/activity", byteArrayInputStream, byteArray.length, ticket);
//        out.close();
        String accessToken = "24_mdXs9NRpuYh2QZXFMSbPiLyy-aIJ5lBD_WcC6XSJniW6BfzK_8w89rCcD80yAcC973O0XV67w_3T6FRo2KnTK1glz0wtTCcTQAtNA6EvwxyPMmCXa083cGn10ycICMbADAXRB";
        String mediaId = WxMaterialManagerUtils.uploadTempMaterial(accessToken, WxMaterialManagerUtils.IMAGE, file);
        System.out.println(mediaId);
//        OutputStream os = new FileOutputStream("/Users/pf/Desktop/logger/test2.jpg");
//        ImageIO.write(initImage, "PNG", os);
//        os.close();
    }*/

    private static String getNameString(String string, FontMetrics fm) {
        //计算字体宽度
        int nickWidth = fm.stringWidth(string);
        if (nickWidth > 102) {
            String temp = string.substring(0, string.length() - 1);
            nickWidth = fm.stringWidth(temp);
            while (nickWidth > 84) {
                temp = temp.substring(0, temp.length() - 1);
                nickWidth = fm.stringWidth(temp);
            }
            return temp + "...";
        }
        return string;
    }

    /*public static java.awt.Font getSelfDefinedFont(String fileName, int style, float size) {
        java.awt.Font font = null;
        try {
            //从jar包中获取文件
            InputStream input = ModificationPicUtils.class.getResourceAsStream(fileName);
            font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, input);
            font = font.deriveFont(style, size);
        } catch (FontFormatException e) {
            logger.error("getSelfDefinedFont error:{}", e);
            return null;
        } catch (FileNotFoundException e) {
            logger.error("getSelfDefinedFont error:{}", e);
            return null;
        } catch (IOException e) {
            logger.error("getSelfDefinedFont error:{}", e);
            return null;
        }
        return font;
    }*/

}
