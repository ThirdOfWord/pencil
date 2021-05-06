package com.freeter.modules.order.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.entity.vo.Order_GoodsVO;

import java.util.List;
import java.util.Map;

/**
 * 兑换订单
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeOrderDao extends BaseMapper<ExchangeOrderEntity> {

 List<Order_GoodsVO> getList(Map<String,Object> map);
 List<Order_GoodsVO> getListWei(Map<String,Object> map);
 List<Order_GoodsVO> getListAll(Map<String,Object> map);

}
