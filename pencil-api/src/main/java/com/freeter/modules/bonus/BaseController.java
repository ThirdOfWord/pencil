package com.freeter.modules.bonus;

import com.freeter.common.exception.RRException;
import com.freeter.common.util.Constant;
import com.freeter.entity.TokenEntity;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : RXK
 * Date : 2021/5/7 15:40
 * Code Less Think More
 * Desc:
 */
public abstract class BaseController {

    @Autowired
    private TokenService tokenService;

    /**
     * 从请求头或者请求参数中获取用户id
     * @return ：用户id
     * @exception RRException: 请求异常
     */
    protected Long token(HttpServletRequest request){
        String token = request.getHeader(Constant.USER_TOKEN);
        if(StringUtils.isBlank(token)){
            token = request.getParameter(Constant.USER_TOKEN);
        }

        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空");
        }

        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getIsExpire()==1){
            throw new RRException("token失效，请重新登录");
        }

        return tokenEntity.getMemberId();
    }
}
