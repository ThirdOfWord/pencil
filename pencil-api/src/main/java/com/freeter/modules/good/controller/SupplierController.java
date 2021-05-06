package com.freeter.modules.good.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.SupplierEntity;
import com.freeter.modules.good.entity.vo.SupplierVO;
import com.freeter.modules.good.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 
 * 例如淘宝、拼多多后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:42
 */
@RestController
@RequestMapping("api/supplier")
@SuppressWarnings({"unchecked","rawtypes"})
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    /**
     * 显示分类
     * @return
     */
    @RequestMapping("classify")
    public R getClassify(){
        List<SupplierVO> list=supplierService.getClassList();
        return R.ok().put("supplierClassify", list);
    }

    /**
     * 指定平台商品列表（综合查询）
     * @param supplierId
     * @param currentPage
     * @return
     */
    @RequestMapping("complex")
    public R getComplex(@RequestParam(value = "supplierId",defaultValue = "0")int supplierId,
                        @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (supplierId==0){
            return R.error(499,"参数错误");
        }
        return supplierService.getComplex(supplierId,currentPage);
    }

    /**
     * 指定平台商品列表（卷后价查询）
     * @param supplierId
     * @param status
     * @param currentPage
     * @return
     */
    @RequestMapping("price")
    public R getPrice(@RequestParam(value = "supplierId",defaultValue = "0")int supplierId,
                        @RequestParam(value = "status",defaultValue = "0")int status,
                        @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (supplierId==0){
            return R.error(499,"参数错误");
        }
        return supplierService.getPrice(supplierId,currentPage,status);
    }
    /**
     * 指定平台商品列表（销量查询）
     * @param supplierId
     * @param status
     * @param currentPage
     * @return
     */
    @RequestMapping("sales")
    public R getSales(@RequestParam(value = "supplierId",defaultValue = "0")int supplierId,
                      @RequestParam(value = "status",defaultValue = "0")int status,
                      @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (supplierId==0){
            return R.error(499,"参数错误");
        }
        return supplierService.getSales(supplierId,currentPage,status);
    }
}
