package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.utils.R;
import com.freeter.modules.good.dao.SupplierDao;
import com.freeter.modules.good.entity.SupplierEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;
import com.freeter.modules.good.entity.vo.SupplierVO;
import com.freeter.modules.good.service.SupplierService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("supplierService")
public class SupplierServiceImpl extends ServiceImpl<SupplierDao, SupplierEntity> implements SupplierService {
    /**
     * 指定平台商品列表（综合查询）
     * @param supplierId
     * @param currentPage
     * @return
     */
    @Override
    public R getComplex(int supplierId, int currentPage) {
        Map<String,Object> map=new HashMap<>();
        map.put("supplierId", supplierId);
        map.put("currentPage", currentPage);
        List<GoodsVO> goodsVOList=baseMapper.getComplex(map);
        map.clear();
        return R.ok().put("goodList", goodsVOList);
    }

    /**
     * 指定平台商品列表（卷后价查询）
     * @param supplierId
     * @param currentPage
     * @param status
     * @return
     */
    @Override
    public R getPrice(int supplierId, int currentPage, int status) {
        Map<String,Object> map=new HashMap<>();
        String sort=null;
        if (status==0){
            sort="ASC";
        }else {
            sort="DESC";
        }
        map.put("sort", sort);
        map.put("supplierId", supplierId);
        map.put("currentPage", currentPage);
        List<GoodsVO> goodsVOList=baseMapper.getPrice(map);
        map.clear();
        return R.ok().put("goodList", goodsVOList);
    }
    /**
     * 指定平台商品列表（销量查询）
     * @param supplierId
     * @param currentPage
     * @param status
     * @return
     */
    @Override
    public R getSales(int supplierId, int currentPage, int status) {
        Map<String,Object> map=new HashMap<>();
        String sort=null;
        if (status==0){
            sort="ASC";
        }else {
            sort="DESC";
        }
        map.put("sort", sort);
        map.put("supplierId", supplierId);
        map.put("currentPage", currentPage);
        List<GoodsVO> goodsVOList=baseMapper.getSales(map);
        map.clear();
        return R.ok().put("goodList", goodsVOList);
    }
    @Override
    public List<SupplierVO> getClassList() {
        List<SupplierVO> list=baseMapper.getClassList();
        return list;
    }
}
