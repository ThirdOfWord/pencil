package com.freeter.modules.user.api.controller;


import com.freeter.modules.user.service.WeixinMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * 微信用户
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
@RestController
@RequestMapping("api/weixinmember")
@SuppressWarnings({"unchecked","rawtypes"})
public class WeixinMemberController {
    @Autowired
    private WeixinMemberService weixinMemberService;

}
