package com.freeter.modules.pingtaiGoods;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.JingDongUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.pingtaiGoods.entity.GoodInfo;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import com.jd.open.api.sdk.internal.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName HttpUrlJingDongController 喵有劵淘宝商品
 * @Author WangKui
 * @Date 2020/6/3 19:25
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/taobaoUrl")
public class HttpUrlTaoBaoController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;

    private final String apkey ="98be1408-a6d2-9406-f4e8-143f89074539";

    private final static String pid ="mm_535270004_1780000352_110507450350";

    private final static String invitercode = "NLH34W";

    /**
    * @Description: 喵有劵淘宝商品转链接
    * @Author: Mr.Wang 
    * @Date: 2020/6/3 
    */
    @RequestMapping("url")
    private R getUrl(@RequestParam(value ="goodsId",defaultValue="0")Long goodsId,HttpServletRequest request) throws Exception {
        int memberId=token(request);
        MemberEntity memberEntity =memberService.selectById(memberId);
        String relationid=memberEntity.getRelationId();
        if (StringUtils.isEmpty(relationid)){
            return R.error(-1, "未授权淘宝");
        }
        if (goodsId == 0){
            return R.error(499, "商品编号有误");
        }
        String tbname ="小小铅笔头";
        tbname=URLEncoder.encode(tbname, "utf-8");
        String url = "http://api.web.21ds.cn/taoke/doItemHighCommissionPromotionLink?apkey="+apkey+"&itemid="+goodsId+"&pid="+pid
                +"&tbname="+tbname+"&relationid="+relationid+"&shorturl=1&tpwd=1&hasiteminfo=0";
        String response= JingDongUtil.sendGet(url);
        //打印结果
        JSONObject json = JSONObject.fromObject(response);
        return R.ok().put("url", json);
    }
    /**
    * @Description: 喵有劵渠道商一键备案API 
    * @Author: Mr.Wang 
    * @Date: 2020/6/12 
    */
    @RequestMapping("channel")
    private R channel(@RequestParam(value ="relation_id",required=false)String relation_id,
                      @RequestParam(value ="custompar",required=false)String custompar,
                      @RequestParam(value ="account_name",required=false)String account_name,
                      @RequestParam(value ="desc",required=false)String desc){
        if (StringUtils.isEmpty(custompar) || StringUtils.isEmpty(relation_id)){
            return R.error(499, "授权失败");
        }
        MemberEntity memberEntity= memberService.selectById(custompar);
        if (null == memberEntity){
            return R.error(499, "获取用户信息失败");
        }
        memberEntity.setRelationId(relation_id);
        memberService.updateAllColumnById(memberEntity);
        return R.ok();
    }

    /**
    * @Description: 喵有劵获取订单信息
    * @Author: Mr.Wang 
    * @Date: 2020/6/12 
    */
    @RequestMapping("orderList")
    private R getOrderList()throws Exception{
        Long unixStamp=DateUtil.getUnixStamp();
        String endTime=DateUtil.timeStampToStr(unixStamp);
        String starTime=DateUtil.timeStampToStr(unixStamp-60*19);
        starTime=URLEncoder.encode(starTime, "UTF-8");
        endTime=URLEncoder.encode(endTime, "UTF-8");
        String tbname ="小小铅笔头";
        tbname=URLEncoder.encode(tbname, "UTF-8");
        String url = "http://api.web.21ds.cn/taoke/tbkOrderDetailsGet?apkey="+apkey+"&tbname="+tbname+"&order_scene=2&start_time="+starTime+"&end_time="+endTime;
        String response= JingDongUtil.sendGet(url);
        //打印结果
        JSONObject json = JSONObject.fromObject(response);
        return R.ok().put("order", json);
    }
    /**
    * @Description: 喵有劵 获取淘宝分类
    * @Author: Mr.Wang 
    * @Date: 2020/6/15 
    */
    @RequestMapping("classfly")
    private R classfly(@RequestParam(value ="cid",defaultValue="0")Integer cid)throws Exception{
        String url = "http://api.web.21ds.cn/platform/getTbCategory?apkey="+apkey+"&cid="+cid;
        String response= JingDongUtil.sendGet(url);
        //打印结果
        JSONObject json = JSONObject.fromObject(response);
        return R.ok().put("classfly", json);
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
