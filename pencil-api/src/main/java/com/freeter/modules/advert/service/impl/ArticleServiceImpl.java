package com.freeter.modules.advert.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.advert.dao.ArticleDao;
import com.freeter.modules.advert.entity.ArticleEntity;
import com.freeter.modules.advert.service.ArticleService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleDao, ArticleEntity> implements ArticleService {


}
