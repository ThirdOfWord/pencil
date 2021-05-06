package com.freeter.modules.other.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.other.service.BonusRecordService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 红包领取记录
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-03-17 15:47:20
 */
@RestController
@RequestMapping("api/bonusrecord")
@SuppressWarnings({"unchecked","rawtypes"})
public class BonusRecordController {
    @Autowired
    private BonusRecordService bonusRecordService;
    @Autowired
    private TokenService tokenService;
    /**
     * 领取红包
     * @author wangkui
     * @date 2020/3/17 16:14
     * @param[request]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("insert")
    public R insert(@RequestParam(value = "coding",required = false) String coding,HttpServletRequest request){
        int memberId=token(request);
        if (StringUtils.isEmpty(coding)){
            return R.error(499, "请输入红包兑换码");
        }
        return bonusRecordService.insert(memberId,coding);
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
