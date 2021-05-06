package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;

import java.util.List;
import java.util.Map;

/**
 * 商品收藏

 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-06-06 10:46:53
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface CollectGoodsDao extends BaseMapper<CollectGoodsEntity> {
 List<CollectGoodsEntity> getList(Map<String,Object> map);//显示当前用户收藏的商品
}
