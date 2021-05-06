package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.MemberCollectGoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;

import java.util.List;
import java.util.Map;

/**
 * 收藏商品表
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-12 23:09:17
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MemberCollectGoodsDao extends BaseMapper<MemberCollectGoodsEntity> {
 List<GoodsVO> getList(Map<String,Object> map);//显示当前用户收藏的商品
}
