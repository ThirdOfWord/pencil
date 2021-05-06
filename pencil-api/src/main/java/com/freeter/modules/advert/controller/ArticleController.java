package com.freeter.modules.advert.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.advert.entity.ArticleEntity;
import com.freeter.modules.advert.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 文章列表
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-24 22:06:57
 */
@RestController
@RequestMapping("api/article")
@SuppressWarnings({"unchecked","rawtypes"})
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired

    /**
     * 使用协议
     * @return
     */
    @RequestMapping("xieyi")
    public R xieyi(){
        EntityWrapper<ArticleEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        ew.eq("article_category_id", 1);
        ArticleEntity articleEntity=articleService.selectOne(ew);
        return R.ok().put("xieyi",articleEntity);
    }
    /**
    * @Description:  隐私协议
    * @Author: Mr.Wang 
    * @Date: 2020/5/26 
    */
    @RequestMapping("yinsi")
    public R yinsi(){
        EntityWrapper<ArticleEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        ew.eq("article_category_id", 4);
        ArticleEntity articleEntity=articleService.selectOne(ew);
        return R.ok().put("yinsi",articleEntity);
    }
    /**
     * 新手指引
     * @return
     */
    @RequestMapping("zhiyin")
    public R zhiyin(){
        EntityWrapper<ArticleEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        ew.eq("article_category_id", 2);
        List<ArticleEntity> list=articleService.selectList(ew);
        return R.ok().put("zhiyin",list);
    }
    /**
     * 常见问题
     * @return
     */
    @RequestMapping("wenti")
    public R wenti(){
        EntityWrapper<ArticleEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        ew.eq("article_category_id", 3);
        List<ArticleEntity> list=articleService.selectList(ew);
        return R.ok().put("wenti",list);
    }
}
