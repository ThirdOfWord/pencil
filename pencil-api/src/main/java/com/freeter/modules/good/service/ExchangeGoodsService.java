package com.freeter.modules.good.service;

import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.ExchangeGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 兑换商品
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:56
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeGoodsService extends IService<ExchangeGoodsEntity> {
 List getList(int memberId);//显示兑奖商品列表
 Map getOneInfo(int memberId,int exchangeGoodsId);//显示兑换商品详情页
 R insertOrder(int memberId,int exchangeGoodsId,int memberAddressId,int isAttrStatus,String attrValueId);
}

