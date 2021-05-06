package com.freeter.modules.money.controller;

import com.freeter.modules.money.service.MoneySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 平台钱包
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 22:14:20
 */
@RestController
@RequestMapping("message/moneyset")
@SuppressWarnings({"unchecked","rawtypes"})
public class MoneySetController {
    @Autowired
    private MoneySetService moneySetService;


}
