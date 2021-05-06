package com.freeter.modules.packageCard.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.packageCard.service.PackageCardService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 卡包卡片
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@RestController
@RequestMapping("api/packagecard")
@SuppressWarnings({"unchecked","rawtypes"})
public class PackageCardController {
    @Autowired
    private PackageCardService packageCardService;
    @Autowired
    private TokenService tokenService;
    /**
    * @Description:  获取签到页面
    * @Author: Mr.Wang 
    * @Date: 2020/5/3 
    */
    @RequestMapping("signInfor")
    public R signInfor() {
     return packageCardService.signInfor();
    }
    /**
    * @Description: 用户签到领取答题卡
    * @Author: Mr.Wang 
    * @Date: 2020/5/3 
    */
    @RequestMapping("signAdd")
    public R signAdd(HttpServletRequest request) {
        int memberId=token(request);
     return packageCardService.signAdd(memberId);
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
