package com.freeter.modules.community.controller;


import com.freeter.modules.community.service.CommunityArticleGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 社区推广商品
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@RestController
@RequestMapping("message/communityarticlegoods")
@SuppressWarnings({"unchecked","rawtypes"})
public class CommunityArticleGoodsController {
    @Autowired
    private CommunityArticleGoodsService communityArticleGoodsService;


}
