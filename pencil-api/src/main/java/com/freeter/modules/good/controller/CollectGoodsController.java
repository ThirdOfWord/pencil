package com.freeter.modules.good.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品收藏

 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-06-06 10:46:53
 */
@RestController
@RequestMapping("api/collectgoods")
@SuppressWarnings({"unchecked","rawtypes"})
public class CollectGoodsController {
    @Autowired
    private CollectGoodsService collectGoodsService;
    @Autowired
    private TokenService tokenService;

    /**
     * 当前用户收藏商品，再次点击取消收藏
     * @param goodsEntity
     * @param request
     * @return
     */
    @RequestMapping("insertOrdelete")
    public synchronized R insertOrdelete(CollectGoodsEntity goodsEntity, HttpServletRequest request){
        int memberId=token(request);
        if (goodsEntity.getGoodsId().intValue() == 0){
            return R.error(499, "商品id为空");
        }
        goodsEntity.setMemberId(memberId);
        return collectGoodsService.insertOrdelete(goodsEntity);
    }
    /**
    * @Description: 显示用户收藏的商品
    * @Author: Mr.Wang 
    * @Date: 2020/6/7 
    */
    @RequestMapping("list")
    public R getList(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        currentPage=(currentPage-1)*12;
        int memberId=token(request);
        return collectGoodsService.getList(memberId,currentPage);
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
