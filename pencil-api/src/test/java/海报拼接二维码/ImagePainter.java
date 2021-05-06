package 海报拼接二维码;

import org.apache.commons.lang3.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by michaeltang on 2019/9/19.
 *
 * 生成合成图片，以流方式返回给前端显示，用于配置生成海报场景
 * 可以绘制字体和图片，如：二维码，头像，注意元素的添加顺序，后加入的在上面
 */

public class ImagePainter {
    /**
     * 绘制类型：
     */
    public static final String KEY_TYPE = "key_type";

    /**
     * 元素类型：FONT、IMAGE、SQUIRE
     */
    public static final String KEY_ELEMENT = "key_content_element";

    /**
     * 字体封装类
     */
    public static class FontElement {
        public int x;
        public int y;
        public int fontSize = 16;
        public Color fontColor;
        public boolean bold;
        public int fontWeight;
        public String content;

        public FontElement() {
        }

        public FontElement(int x, int y, int fontSize, Color fontColor, boolean bold, int fontWeight, String content) {
            this.x = x;
            this.y = y;
            this.fontSize = fontSize;
            this.fontColor = fontColor;
            this.bold = bold;
            this.fontWeight = fontWeight;
            this.content = content;
        }
    }

    /**
     * 图像封装样式
     */
    public static class ImageElement {
        public int x;
        public int y;
        public int width;
        public int height;
        public boolean round = false;
        public int borderWidth = 1;
        public Color borderColor;
        public Color bkgColor;
        public String imageUrl;

        public ImageElement() {
        }

        public ImageElement(int x, int y, int width, int height, boolean round, int borderWidth, Color borderColor, Color bkgColor, String imgUrl) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.round = round;
            this.borderColor = borderColor;
            this.borderWidth = borderWidth;
            this.bkgColor = bkgColor;
            this.imageUrl = imgUrl;
        }
    }

    /**
     * 元素类型
     **/
    public static enum ElementType {
        FONT("FONT", "文字"),
        IMAGE("IMAGE", "图像");

        private String value;
        private String displayName;

        static Map<String, ElementType> enumMap = new HashMap<String, ElementType>();

        static {
            for (ElementType type : ElementType.values()) {
                enumMap.put(type.getValue(), type);
            }
        }

        private ElementType(String value, String displayName) {
            this.value = value;
            this.displayName = displayName;
        }


        public String getValue() {
            return this.value;
        }


        public String getDisplayName() {
            return this.displayName;
        }

        public static ElementType getEnum(String value) {
            return enumMap.get(value);
        }

    }

    /**
     * 生成合成图片，以流方式返回给前端显示，用于配置生成海报场景
     *
     * @param request
     * @param response
     * @param backgroundUrl 大背景图Url
     * @param elements      元素数组，可以是字体，可以是图片，如二维码，头像，注意元素的添加顺序，后加入的在上面
     */
    public static void getPaintedStream(HttpServletRequest request, HttpServletResponse response,
                                        String backgroundUrl, List<HashMap<String, Object>> elements) {
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpeg");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");

        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(paint(backgroundUrl, elements), "JPEG", response.getOutputStream());
        } catch (Exception e) {
        }
    }

    public static BufferedImage paint(String backgroundUrl, List<HashMap<String, Object>> components) {
        if (StringUtils.isEmpty(backgroundUrl)) {
            //throw new OpenmoreException("没有指定背景图像");
        }
        if (components == null || components.size() == 0) {
            //throw new OpenmoreException("至少在图片指定一个绘制的组件");
        }
        BufferedImage bkgImage = readUrlImage(backgroundUrl);
        for (HashMap map : components) {
            Object obj = map.get(KEY_TYPE);
            if (obj == null) {
                continue;
            }
            ElementType type = (ElementType) map.get(KEY_TYPE);
            if (type == ElementType.FONT) {
                FontElement font = (FontElement) map.get(KEY_ELEMENT);
                drawFont(bkgImage, font);
            } else if (type == ElementType.IMAGE) {
                ImageElement image = (ImageElement) map.get(KEY_ELEMENT);
                drawImage(bkgImage, image);
            }

        }
        return bkgImage;
    }

    private static void drawFont(BufferedImage bkgImage, FontElement font) {
        Graphics2D g = bkgImage.createGraphics();
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        if (font.bold) {
            g.setFont(new Font("Microsoft YaHei", Font.BOLD, font.fontSize));
        } else {
            g.setFont(new Font("Microsoft YaHei", Font.PLAIN, font.fontSize));
        }
        g.setColor(font.fontColor);
        g.drawString(font.content, font.x, font.y);
        g.dispose();
    }

    private static void drawImage(BufferedImage bkgImage, ImageElement img) {
        Graphics2D g = bkgImage.createGraphics();
        g.setColor(Color.WHITE);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage image = readUrlImage(img.imageUrl);
        if (img.round) {
            // 图片切成是一个圆型
            int width = img.width - img.borderWidth * 2;
            Ellipse2D.Double shape = new Ellipse2D.Double(img.x + img.borderWidth, img.y + img.borderWidth, width, width);
            g.setClip(shape);
            g.drawImage(image.getScaledInstance(img.width, img.width, Image.SCALE_SMOOTH), img.x, img.y, img.width, img.width, null);
            g.dispose();
            // 画白边
            Graphics2D g2 = bkgImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Stroke s = new BasicStroke(img.borderWidth * 3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
            g2.setStroke(s);
            g2.setColor(Color.WHITE);
            g2.drawOval(img.x, img.y, img.width, img.width);
            g2.dispose();
        } else {
            g.drawImage(image.getScaledInstance(img.width, img.height, Image.SCALE_SMOOTH), img.x, img.y, img.width, img.height, null);
            g.dispose();
        }
    }

    private static BufferedImage readUrlImage(String urlString) {
        try {
            URL url = new URL(urlString);
            DataInputStream dataInputStream = new DataInputStream(url.openStream());
            // 读取背景图
            return ImageIO.read(dataInputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
