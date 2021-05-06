package com.freeter.modules.taskOrder.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.entity.vo.GoodsOrderVO;

import java.util.List;

/**
 * 推广商城订单
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-19 22:37:31
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface GoodsOrderService extends IService<GoodsOrderEntity> {
R getlist(int memberId, int supplierId,int currentPage);//订单筛选---根据平台编号
 List<GoodsOrderVO> getAllList(int memberId,int currentPage);//全部
 List<GoodsOrderVO> getAllListByStatus(int memberId,int currentPage,int orderStatus);//状态区分
}

