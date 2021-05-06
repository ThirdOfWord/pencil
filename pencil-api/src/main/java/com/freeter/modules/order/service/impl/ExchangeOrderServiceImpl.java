package com.freeter.modules.order.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.order.dao.ExchangeOrderDao;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.entity.vo.Order_GoodsVO;
import com.freeter.modules.order.service.ExchangeOrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("exchangeOrderService")
public class ExchangeOrderServiceImpl extends ServiceImpl<ExchangeOrderDao, ExchangeOrderEntity> implements ExchangeOrderService {

    @Override
    public List<Order_GoodsVO> getList(int memberId, int currentPage,int status) {
        Map<String,Object> map =new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage", currentPage);
        List<Order_GoodsVO> list;
        if (status==0){
            list=baseMapper.getListWei(map);
        }else {
            list=baseMapper.getList(map);
        }
        return list;
    }

    /**
     * 筛选兑换订单
     * @param memberId
     * @param currentPage
     * @return
     */
    @Override
    public List<Order_GoodsVO> getListAll(int memberId, int currentPage) {
        Map<String,Object> map =new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage", currentPage);
        List<Order_GoodsVO> list=baseMapper.getListAll(map);
        return list;
    }
}
