package com.freeter.modules.good.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.CollectGoodsEntity;

/**
 * 商品收藏

 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-06-06 10:46:53
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface CollectGoodsService extends IService<CollectGoodsEntity> {
 R insertOrdelete(CollectGoodsEntity goodsEntity);//当前用户收藏商品，再次点击取消收藏
 R getList(int memberId, int currentPage);//显示当前用户收藏的商品
}

