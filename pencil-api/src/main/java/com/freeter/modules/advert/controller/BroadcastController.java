package com.freeter.modules.advert.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.advert.entity.ArticleEntity;
import com.freeter.modules.advert.entity.BroadcastEntity;
import com.freeter.modules.advert.service.BroadcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 广播
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 16:49:35
 */
@RestController
@RequestMapping("api/broadcast")
@SuppressWarnings({"unchecked","rawtypes"})
public class BroadcastController {
    @Autowired
    private BroadcastService broadcastService;

    /**
     * 获取广播前十条信息
     * @return
     */
    @RequestMapping("list")
    public R list(){
        List<BroadcastEntity> list=broadcastService.getList();
        return R.ok().put("broadcastList",list);
    }
}
