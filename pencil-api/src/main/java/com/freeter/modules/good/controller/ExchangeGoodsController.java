package com.freeter.modules.good.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.ExchangeGoodsAttrsEntity;
import com.freeter.modules.good.service.ExchangeGoodsService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 兑换商品
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:56
 */
@RestController
@RequestMapping("api/exchangegoods")
@SuppressWarnings({"unchecked","rawtypes"})
public class ExchangeGoodsController {
    @Autowired
    private ExchangeGoodsService exchangeGoodsService;
    @Autowired
    private TokenService tokenService;


    /**
     * 显示兑奖商品列表
     * @param request
     * @return
     */
    @RequestMapping("list")
    public R list(HttpServletRequest request){
        int memberId=token(request);
        List list=exchangeGoodsService.getList(memberId);
        return R.ok().put("exchangegoodsList", list);
    }
    /**
     * 显示兑换商品详情页
     * @param request
     * @return
     */
    @RequestMapping("oneInfo")
    public R oneInfo(@RequestParam(value = "exchangeGoodsId",defaultValue = "0") int exchangeGoodsId,HttpServletRequest request){
        if (exchangeGoodsId==0){
            return R.error(499, "参数错误");
        }
        int memberId=token(request);
        Map map=exchangeGoodsService.getOneInfo(memberId,exchangeGoodsId);
        return R.ok().put("exchangegoodsInfo", map);
    }
    /**
     * 去兑换
     * @param exchangeGoodsId
     * @param attrValueId
     * @return
     */
    @RequestMapping("goOrder")
    public synchronized R goOrder(@RequestParam(value = "exchangeGoodsId",defaultValue = "0") int exchangeGoodsId,
                     @RequestParam(value = "memberAddressId",defaultValue = "0") int memberAddressId,
                     @RequestParam(value = "isAttrStatus",defaultValue = "0") int isAttrStatus,
                       @RequestParam(value = "attrValueId",required = false) String attrValueId,HttpServletRequest request){
        if (exchangeGoodsId==0 || memberAddressId==0){
            return R.error(499, "参数错误");
        }
        int memberId=token(request);
        R r=null;
        synchronized (this){
            r =exchangeGoodsService.insertOrder(memberId,exchangeGoodsId,memberAddressId,isAttrStatus,attrValueId);
        }
        return r;
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
