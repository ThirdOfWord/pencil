package com.freeter.modules.taskOrder.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.entity.vo.GoodsOrderVO;

import java.util.List;
import java.util.Map;

/**
 * 推广商城订单
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-19 22:37:31
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface GoodsOrderDao extends BaseMapper<GoodsOrderEntity> {
 List<GoodsOrderVO> getlist(Map<String,Object> map);//订单筛选---根据平台编号
 List<GoodsOrderVO> getAll(Map<String,Object> map);//获取全部订单
 List<GoodsOrderVO> getAllListByStatus(Map<String,Object> map);//根据status获取对应的订单

}
