package 海报拼接二维码;

import com.freeter.common.utils.R;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName test
 * @Author WangKui
 * @Date 2020/5/26 23:28
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/a")
@SuppressWarnings({"unchecked","rawtypes"})
public class test {

    @RequestMapping("b")
    public void pay(HttpServletRequest request, HttpServletResponse response){
        String bkgUrl = "http://source.wenxiaoyou.com/test/WechatIMG43.jpeg";
        List<HashMap<String, Object>> elements = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        // 添加二维码
        map.put(ImagePainter.KEY_TYPE, ImagePainter.ElementType.IMAGE);
        ImagePainter.ImageElement image = new ImagePainter.ImageElement();
        image.x = 495;
        image.y = 980;
        image.width = 170;
        image.height = 170;
        image.imageUrl = "http://source.wenxiaoyou.com/image/1568947454861.jpg";
        map.put(ImagePainter.KEY_ELEMENT, image);
        elements.add(map);
        // 添加头像
        HashMap<String, Object> avatarMap = new HashMap<>();
        avatarMap.put(ImagePainter.KEY_TYPE, ImagePainter.ElementType.IMAGE);
        ImagePainter.ImageElement avatar = new ImagePainter.ImageElement();
        avatar.x = 30;
        avatar.y = 30;
        avatar.width = 128;
        avatar.height = 128;
        avatar.borderWidth = 2;
        avatar.round = true;
        avatar.imageUrl = "http://source.wenxiaoyou.com/test/1568886588882.jpg";
        avatarMap.put(ImagePainter.KEY_ELEMENT, avatar);
        elements.add(avatarMap);

        // 添加文字
        HashMap<String, Object> map2 = new HashMap<>();
        map2.put(ImagePainter.KEY_TYPE, ImagePainter.ElementType.FONT);
        ImagePainter.FontElement font = new ImagePainter.FontElement();
        font.content = "扫一扫，领取奖品";
        font.x = 510;
        font.y = 1170;
        font.fontColor = new Color(0, 0, 0);
        map2.put(ImagePainter.KEY_ELEMENT, font);
        elements.add(map2);
        ImagePainter.getPaintedStream(request, response, bkgUrl, elements);
    }
}
