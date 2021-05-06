package com.freeter.modules.advert.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.advert.service.MessageService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 消息表
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-16 16:18:35
 */
@RestController
@RequestMapping("api/message")
@SuppressWarnings({"unchecked","rawtypes"})
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private TokenService tokenService;

    /**
     * 用户消息通知
     * @author wangkui
     * @date 2020/1/17 9:44
     * @param  currentPage, request
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("list")
    public R list(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        int memberId=token(request);
        currentPage=(currentPage-1)*10;
        EntityWrapper<MessageEntity> ew =new EntityWrapper<>();
        ew.eq("member_id", memberId);
        ew.orderBy("create_time", false);
        ew.last("limit "+currentPage+",10");
        List<MessageEntity> list=messageService.selectList(ew);
        return R.ok().put("messageList", list);
    }
    /**
     * 修改消息状态
     * @author wangkui
     * @date 2020/1/17 10:33
     * @param [messageId, request]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("select")
    public R select(@RequestParam(value = "messageId",defaultValue = "0")int messageId, HttpServletRequest request){
        if (messageId==0){
            return R.error(499, "参数错误");
        }
        token(request);
        MessageEntity messageEntity=messageService.selectById(messageId);
        if (null != messageEntity && messageEntity.getIsRead()==0){
            messageEntity.setIsRead(1);
            messageService.updateById(messageEntity);
        }
        return R.ok();
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
