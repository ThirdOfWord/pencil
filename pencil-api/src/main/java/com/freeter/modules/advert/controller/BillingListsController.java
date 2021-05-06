package com.freeter.modules.advert.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.advert.entity.BillingListsEntity;
import com.freeter.modules.advert.service.BillingListsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广告列表
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-09 15:45:09
 */
@RestController
@RequestMapping("api/billinglists")
@SuppressWarnings({"unchecked","rawtypes"})
public class BillingListsController {
    @Autowired
    private BillingListsService billingListsService;

    /**
     * 首页广告
     * @param billingSiteId
     * @return
     */
    @RequestMapping("advert")
    public R getAdvert(@RequestParam(value = "billingSiteId",defaultValue = "0") Integer billingSiteId){
        EntityWrapper<BillingListsEntity> billingListsWrapper=new EntityWrapper<>();
        billingListsWrapper.eq("status", 1);
        if (billingSiteId==1){
            billingListsWrapper.eq("billing_site_id", 1);
        }if (billingSiteId==2){
            billingListsWrapper.eq("billing_site_id", 2);
        }if (billingSiteId==3){
            billingListsWrapper.eq("billing_site_id", 3);
        }if (billingSiteId==4){
            billingListsWrapper.eq("billing_site_id", 6);
        }else {
            billingListsWrapper.eq("billing_site_id", billingSiteId);
        }
        List<BillingListsEntity> list=billingListsService.selectList(billingListsWrapper);
        return R.ok().put("advertList", list);
    }

    /**
     * 首页淘宝广告6图
     * @return
     */
    @RequestMapping("tianMaoAdvert")
    public R getTianMaoAdvert(){
        EntityWrapper<BillingListsEntity> billingListsWrapper=new EntityWrapper<>();
        billingListsWrapper.eq("status", 1);
        billingListsWrapper.eq("billing_site_id", 4);
        billingListsWrapper.or();
        billingListsWrapper.eq("billing_site_id", 5);
        billingListsWrapper.orderBy("billing_site_id");
        List<BillingListsEntity> list=billingListsService.selectList(billingListsWrapper);
        return R.ok().put("advertList", list);
    }
}
