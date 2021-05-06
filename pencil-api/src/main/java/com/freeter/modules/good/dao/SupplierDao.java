package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.SupplierEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;
import com.freeter.modules.good.entity.vo.SupplierVO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:42
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface SupplierDao extends BaseMapper<SupplierEntity> {
 List<SupplierVO> getClassList();//显示分类
 List<GoodsVO> getComplex(Map<String,Object> map);//指定平台商品列表（综合查询）
 List<GoodsVO> getPrice(Map<String,Object> map);//指定平台商品列表（卷后价查询）
 List<GoodsVO> getSales(Map<String,Object> map);//指定平台商品列表（销量查询）
}
