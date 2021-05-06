package com.freeter.modules.good.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.service.MemberCollectGoodsService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 收藏商品表
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-12 23:09:17
 */
@RestController
@RequestMapping("api/membercollectgoods")
@SuppressWarnings({"unchecked","rawtypes"})
public class MemberCollectGoodsController {
    @Autowired
    private MemberCollectGoodsService memberCollectGoodsService;
    @Autowired
    private TokenService tokenService;

    /**
     * 当前用户收藏商品，再次点击取消收藏
     * @param supplierGoodsId
     * @param request
     * @return
     */
    @RequestMapping("insertOrdelete")
    public R insertOrdelete(@RequestParam(value = "supplierGoodsId",defaultValue = "0")Long supplierGoodsId, HttpServletRequest request){
        int memberId=token(request);
        if (supplierGoodsId==0){
            return R.error(499, "商品id为空");
        }
        return memberCollectGoodsService.insertOrdelete(supplierGoodsId,memberId);
    }

    /**
     * 显示当前用户收藏的商品
     * @param currentPage
     * @return
     */
    @RequestMapping("list")
    public R getList(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,HttpServletRequest request){
        currentPage=(currentPage-1)*8;
        int memberId=token(request);
        return memberCollectGoodsService.getList(memberId,currentPage);
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
