package com.freeter.modules.order.controller;


import com.freeter.modules.order.service.ExchangeOrderGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 兑换订单商品
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:27
 */
@RestController
@RequestMapping("api/exchangeordergoods")
@SuppressWarnings({"unchecked","rawtypes"})
public class ExchangeOrderGoodsController {
    @Autowired
    private ExchangeOrderGoodsService exchangeOrderGoodsService;

}
