package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.GoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;

import java.util.List;
import java.util.Map;

/**
 * 供应商商品
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:41
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface GoodsDao extends BaseMapper<GoodsEntity> {
 List<GoodsVO> getSearch(Map<String,Object> map);//搜索
 List<GoodsVO> getChoice(int currentPage);//精选
 List<GoodsVO> getLike(int currentPage);//猜你喜欢
 List<GoodsVO> getRecommend(int currentPage);//为您推荐
 List<GoodsVO> getOneClassify(Map<String,Object> map);//综合
 List<GoodsVO> getTwoClassify(Map<String,Object> map);//综合
 List<GoodsVO> getPriceOneClassify(Map<String,Object> map);//卷后价
 List<GoodsVO> getPriceTwoClassify(Map<String,Object> map);//卷后价
 List<GoodsVO> getSalesOneClassify(Map<String,Object> map);//销量
 List<GoodsVO> getSalesTwoClassify(Map<String,Object> map);//销量
}
