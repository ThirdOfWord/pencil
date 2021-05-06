package com.freeter.modules.pingtaiGoods.controller;

import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.order.entity.vo.Order_GoodsVO;
import com.freeter.modules.pingtaiGoods.entity.GoodsGoldRushEntity;
import com.freeter.modules.pingtaiGoods.entity.TaoLiJinInfo;
import com.freeter.modules.pingtaiGoods.service.GoodsGoldRushService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @ClassName GoodsGoldRushController 淘礼金
 * @Author WangKui
 * @Date 2020/8/17 21:51
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/goldRush")
public class GoodsGoldRushController {
    @Autowired
    private GoodsGoldRushService goodsGoldRushService;
    @Autowired
    private TokenService tokenService;

    /**
    * @Description: 淘礼金列表
    * @Author: Mr.Wang 
    * @Date: 2020/8/17 
    */
    @RequestMapping("list")
    public R list(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        currentPage=(currentPage-1)*10;
        List<TaoLiJinInfo> list=goodsGoldRushService.getListAll(currentPage,request);
        return R.ok().put("list", list);
    }
    /**
    * @Description: 用户领取淘礼金前判断下
    * @Author: Mr.Wang 
    * @Date: 2020/8/17 
    */
    @RequestMapping("receive")
    public R receive(@RequestParam(value = "goodsGoldRushId",defaultValue = "0")int goodsGoldRushId, HttpServletRequest request){
        synchronized (this) {
            if (goodsGoldRushId == 0) {
                return R.error("参数有误");
            }
            int memberId = token(request);
            return goodsGoldRushService.receive(memberId,goodsGoldRushId);
        }
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
