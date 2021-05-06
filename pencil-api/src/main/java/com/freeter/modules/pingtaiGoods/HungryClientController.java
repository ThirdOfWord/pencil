package com.freeter.modules.pingtaiGoods;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.pingtaiGoods.entity.TaoBaoInfo;
import com.freeter.modules.pingtaiGoods.service.GoodsGoldRushService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
/**
 * 淘宝
 */
@RestController
@RequestMapping("api/hungry")
public class HungryClientController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;

    private final static String url="http://gw.api.taobao.com/router/rest";
    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    private final static Long adzoneId=110507450350L;

    /**
    * @Description: 淘宝客-推广者-官方活动转链 饿了么
    * @Author: Mr.Wang 
    * @Date: 2020/9/10 
    */
    @RequestMapping("getElm")
    public R getElm(HttpServletRequest request) throws Exception {
        int memberId=token(request);
        MemberEntity memberEntity = memberService.selectById(memberId);
        if (null == memberEntity){
            return R.error();
        }
        String relationid=memberEntity.getRelationId();
        if (StringUtils.isEmpty(relationid)){
            return R.error(1, "未授权淘宝");
        }
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(adzoneId);
        req.setRelationId(Long.parseLong(relationid));
        req.setActivityMaterialId("1571715733668");
        //req.setUnionId("demo");
        TbkActivityInfoGetResponse rsp = client.execute(req);
        String shortClickUrl = rsp.getData().getShortClickUrl();
        if (StringUtils.isEmpty(shortClickUrl)){
            shortClickUrl = rsp.getData().getClickUrl();
        }
        String taoKouLing = getTaoKouLing("快来领取饿了么",shortClickUrl);
        Map<String,Object> map = new HashMap<>();
        map.put("elmUrl", shortClickUrl);
        map.put("taoKouLing",taoKouLing);
        return R.ok(map);
    }
    //获取淘口令
    public String getTaoKouLing(String text,String shareUrl) throws Exception{
        if (StringUtils.isEmpty(shareUrl)){
            return null;
        }
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText(text);
        req.setUrl(shareUrl);
        TbkTpwdCreateResponse rsp = client.execute(req);
        String bbb = rsp.getData().getModel();
        String str1=bbb.substring(0, bbb.indexOf("￥"));
        String str2=bbb.substring(str1.length()+1, bbb.length());
        String str3=str2.substring(0, str2.indexOf("￥"));
        return "￥"+str3+"￥";
    }

    private int token(HttpServletRequest request){
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空");
        }
        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getIsExpire()==1){
            throw new RRException("token失效，请重新登录");
        }
        return tokenEntity.getMemberId().intValue();
    }

}
