package com.freeter.modules.community.controller;


import com.freeter.modules.community.service.StoreUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区商家
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@RestController
@RequestMapping("message/storeuser")
@SuppressWarnings({"unchecked","rawtypes"})
public class StoreUserController {
    @Autowired
    private StoreUserService storeUserService;

}
