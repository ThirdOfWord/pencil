package com.freeter.modules.good.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.MemberCollectGoodsEntity;

/**
 * 收藏商品表
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-12 23:09:17
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MemberCollectGoodsService extends IService<MemberCollectGoodsEntity> {
R insertOrdelete(Long supplierGoodsId,int memberId);//当前用户收藏商品，再次点击取消收藏
R getList(int memberId,int currentPage);//显示当前用户收藏的商品
}

