package com.freeter.modules.good.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.good.entity.GoodsClassifyEntity;
import com.freeter.modules.good.service.GoodsClassifyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 商品分类
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-09 10:55:26
 */
@RestController
@RequestMapping("api/goodsclassify")
@SuppressWarnings({"unchecked","rawtypes"})
public class GoodsClassifyController {
    @Autowired
    private GoodsClassifyService goodsClassifyService;

    @PostMapping("getOne")
    @ApiOperation("商品一级分类")
    public R getOne(){
        EntityWrapper<GoodsClassifyEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("status", 1);
        entityWrapper.eq("pid", 0);
        List<GoodsClassifyEntity> list=goodsClassifyService.selectList(entityWrapper);
        return R.ok().put("getOne", list);
    }

    @PostMapping("getTwo")
    @ApiOperation("商品二级分类")
    public R getTwo(@RequestParam(value = "goodsClassifyId",defaultValue = "-1") Long goodsClassifyId){
        EntityWrapper<GoodsClassifyEntity> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("status", 1);
        entityWrapper.eq("pid", goodsClassifyId);
        List<GoodsClassifyEntity> list=goodsClassifyService.selectList(entityWrapper);
        return R.ok().put("getTwo", list);
    }
}
