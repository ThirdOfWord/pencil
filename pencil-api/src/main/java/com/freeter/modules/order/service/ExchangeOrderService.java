package com.freeter.modules.order.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.entity.vo.Order_GoodsVO;

import java.util.List;

/**
 * 兑换订单
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeOrderService extends IService<ExchangeOrderEntity> {
 List<Order_GoodsVO> getList(int memberId,int currentPage,int status);
 List<Order_GoodsVO> getListAll(int memberId,int currentPage);
}

