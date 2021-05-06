package com.freeter.modules.good.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.ExchangeGoodsAttrsEntity;
import com.freeter.modules.good.service.ExchangeGoodsAttrsService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:59
 */
@RestController
@RequestMapping("api/exchangegoodsattrs")
@SuppressWarnings({"unchecked","rawtypes"})
public class ExchangeGoodsAttrsController {
    @Autowired
    private ExchangeGoodsAttrsService exchangeGoodsAttrsService;
    @Autowired
    private TokenService tokenService;

    /**
     * 兑换商品规格查看对应图片以及库存
     * @param exchangeGoodsId
     * @param attrValueId
     * @return
     */
    @RequestMapping("checkAttr")
    public R checkAttr(@RequestParam(value = "exchangeGoodsId",defaultValue = "0") int exchangeGoodsId,
                       @RequestParam(value = "attrValueId",required = false) String attrValueId){
        if (exchangeGoodsId==0){
            return R.error(499, "参数错误");
        }
        EntityWrapper<ExchangeGoodsAttrsEntity> ew=new EntityWrapper();
        ew.eq("exchange_goods_id", exchangeGoodsId);
        ew.eq("exchange_attr_value_id_str", attrValueId);
        ExchangeGoodsAttrsEntity goodsAttrsEntity=exchangeGoodsAttrsService.selectOne(ew);
        return R.ok().put("exchangegoodsInfo", goodsAttrsEntity);
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
