package com.freeter.modules.good.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.SupplierEntity;
import com.freeter.modules.good.entity.vo.SupplierVO;

import java.util.List;

/**
 * 
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:42
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface SupplierService extends IService<SupplierEntity> {
 List<SupplierVO> getClassList();//显示分类
R getComplex(int supplierId,int currentPage);//指定平台商品列表（综合查询）
R getPrice(int supplierId,int currentPage,int status);//指定平台商品列表（卷后价查询）
R getSales(int supplierId,int currentPage,int status);//指定平台商品列表（销量查询）
}

