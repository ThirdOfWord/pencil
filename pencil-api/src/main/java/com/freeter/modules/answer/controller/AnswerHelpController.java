package com.freeter.modules.answer.controller;

import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.answer.service.AnswerHelpService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 发起助力
 * @Auther: hao
 * @Date: 2020/1/15 15:32
 * @Description:
 */
@RestController
@RequestMapping("api/answerHelp")
public class AnswerHelpController {
    @Autowired
    private AnswerHelpService helpService;
    @Autowired
    private TokenService tokenService;
    /**
    * @Description: 查看当前用户是否可以答题
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @RequestMapping("isStart")
    public R isStart(HttpServletRequest request){
        int memberId=token(request);
        return helpService.isStart(memberId);
    }
    /**
     * 发起助力或查看助力情况
     * @paramrequest
     * @return
     */
    @CrossOrigin
    @RequestMapping("add")
    public R add(@RequestParam(value = "memberId",defaultValue = "0") int memberId){
        if (memberId == 0){
            return R.error(499, "参数错误");
        }
        return helpService.add(memberId);
    }
    /*@RequestMapping("add")
    public R add(HttpServletRequest request){
        int memberId=token(request);
        return helpService.add(memberId);
    }*/
    /**
     *  帮助助力
     * @author wangkui
     * @date 2020/1/16 14:04
     * @param [ firstMemberId, request]
     * @return com.freeter.common.utils.R
     */
    @CrossOrigin
    @RequestMapping("doInsert")
    public R doInsert(@RequestParam(value = "firstMemberId",defaultValue = "0") int firstMemberId,HttpServletRequest request){
        if (firstMemberId==0){
            return R.error(499, "参数错误");
        }
        int memberId=token(request);
        if (firstMemberId==memberId){
            return R.error(499, "无法给自己助力");
        }
        return helpService.doInsert(firstMemberId,memberId);
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
