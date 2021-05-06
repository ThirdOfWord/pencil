package com.freeter.modules.pingtaiGoods;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;

import com.jd.open.api.sdk.domain.kplunion.PromotionService.request.get.PromotionCodeReq;
import com.jd.open.api.sdk.request.kplunion.UnionOpenPromotionCommonGetRequest;
import com.jd.open.api.sdk.response.kplunion.UnionOpenPromotionCommonGetResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;

/**
 * 京东
 */
@RestController
@RequestMapping("api/jd")
public class JdController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CollectGoodsService collectGoodsService;

    static Log log= LogFactory.get(JdController.class);
    private final static String SERVER_URL = "https://api.jd.com/routerjson";
    private static  String accessToken = "cd2c4e6f02e6436bb4d0e51b60c2c42erlnm";
    private final static  String appKey = "97d9a36db2d6f14fd3a5030e5231b221";
    private final static  String appSecret = "9cc6f49ae6cd4715b030bd28fc2e12c9";
    private final static  String APPID = "4000176845";
    //private final static  String code = "zn8664";



    /**
    * @Description: 推广链接接口
     * @Author: Mr.Wang 
    * @Date: 2020/6/2  //测试：
    */
    @RequestMapping("url")
    public R getUrl(@RequestParam(value ="materialUrl",required=false)String materialUrl,
                    @RequestParam(value ="couponUrl",required=false)String couponUrl,HttpServletRequest req) throws Exception{
        if (StringUtils.isEmpty(materialUrl)){
            return R.error(499, "参数错误");
        }
        int memberId=token(req);
        JdClient client = new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        UnionOpenPromotionCommonGetRequest request=new UnionOpenPromotionCommonGetRequest();
        PromotionCodeReq promotionCodeReq=new PromotionCodeReq();
        promotionCodeReq.setMaterialId(materialUrl);
        promotionCodeReq.setPid(APPID);
        if (StringUtils.isNotEmpty(couponUrl)){
            promotionCodeReq.setCouponUrl(couponUrl);
        }
        //promotionCodeReq.setSiteId(APPID);
        //promotionCodeReq.setExt1(String.valueOf(memberId));
        request.setPromotionCodeReq(promotionCodeReq);
        UnionOpenPromotionCommonGetResponse response=client.execute(request);
        return R.ok().put("url", response);
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
