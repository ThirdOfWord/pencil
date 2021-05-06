package com.freeter.modules.good.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.GoodsEntity;

/**
 * 供应商商品
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:41
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface GoodsService extends IService<GoodsEntity> {
R getSearch(String title,int currentPage);//搜索商品
R getChoice(int currentPage);//精选
R getLike(int currentPage);//猜你喜欢
R getRecommend(int currentPage);//为您推荐
R getComplex(int pId,int goodsClassifyId,int currentPage);
R getPrice(int pId,int goodsClassifyId,int currentPage,int status);
R getSales(int pId,int goodsClassifyId,int currentPage,int status);
}

