package com.freeter.modules.money.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.money.entity.ExchangeBonusEntity;
import com.freeter.modules.money.service.ExchangeBonusService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 兑换订单红包
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-05-03 23:41:35
 */
@RestController
@RequestMapping("api/exchangebonus")
@SuppressWarnings({"unchecked","rawtypes"})
public class ExchangeBonusController {
    @Autowired
    private ExchangeBonusService exchangeBonusService;
    @Autowired
    private TokenService tokenService;
    /**
    * @Description: 领取红包 
    * @Author: Mr.Wang 
    * @Date: 2020/5/4 
    */
    @RequestMapping("redPack")
    public R getRedPack(@RequestParam(value = "coding",required = false) String coding, HttpServletRequest request){
        synchronized (this){
            int memberId=token(request);
            if (coding.length() != 8){
                return R.error(499, "领取码无效");
            }
            return exchangeBonusService.getRedPack(memberId,coding);
        }
    }
    /**
    * @Description:通过订单编号获取分享红包信息
    * @Author: Mr.Wang 
    * @Date: 2020/6/8 
    */
    @RequestMapping("getInfo")
    public R getInfo(@RequestParam(value = "exchangeOrderId",defaultValue = "0") Integer exchangeOrderId, HttpServletRequest request){
        int memberId=token(request);
        if (exchangeOrderId == 0){
            return R.error(499, "参数有误");
        }
        EntityWrapper<ExchangeBonusEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("exchange_order_id", exchangeOrderId);
        entityWrapper.eq("member_id", memberId);
        ExchangeBonusEntity bonusEntity= exchangeBonusService.selectOne(entityWrapper);
        if (null == bonusEntity){
            return R.ok("数据有误");
        }
        return R.ok().put("bonusEntity", bonusEntity);
    }
    /**
    * @Description: 分享成功后分享次数减一为0时分享结束
    * @Author: Mr.Wang 
    * @Date: 2020/5/6 
    */
    @RequestMapping("addShare")
    public R addShare(@RequestParam(value = "exchangeBonusId",defaultValue = "0") Integer exchangeBonusId, HttpServletRequest request){
        int memberId=token(request);
        if (exchangeBonusId == 0){
            return R.error(499, "参数有误");
        }
        return exchangeBonusService.addShare(memberId,exchangeBonusId);
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
