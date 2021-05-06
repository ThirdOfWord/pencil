package com.freeter.modules.taskOrder.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.utils.R;
import com.freeter.modules.taskOrder.dao.GoodsOrderDao;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.entity.vo.GoodsOrderVO;
import com.freeter.modules.taskOrder.service.GoodsOrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("goodsOrderService")
public class GoodsOrderServiceImpl extends ServiceImpl<GoodsOrderDao, GoodsOrderEntity> implements GoodsOrderService {

    /**
    * @Description: 获取全部订单
    * @Author: Mr.Wang 
    * @Date: 2020/5/8 
    */
    @Override
    public List<GoodsOrderVO> getAllList(int memberId, int currentPage) {
        Map<String,Object> map =new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage",currentPage);
        List<GoodsOrderVO> list=baseMapper.getAll(map);
        return list;
    }
    /**
    * @Description: 根据status获取对应的订单
    * @Author: Mr.Wang 
    * @Date: 2020/5/8 
    */
    @Override
    public List<GoodsOrderVO> getAllListByStatus(int memberId, int currentPage, int orderStatus) {
        Map<String,Object> map =new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage",currentPage);
        map.put("orderStatus",orderStatus);
        List<GoodsOrderVO> list=baseMapper.getAllListByStatus(map);
        return list;
    }

    /**
     * 订单筛选---根据平台编号
     * @param memberId
     * @param supplierId
     * @param currentPage
     * @return
     */
    @Override
    public R getlist(int memberId, int supplierId, int currentPage) {
        Map<String,Object> map=new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage", currentPage);
        map.put("supplierId", supplierId);
        List<GoodsOrderVO> list=baseMapper.getlist(map);
        return R.ok().put("list", list);
    }
}
