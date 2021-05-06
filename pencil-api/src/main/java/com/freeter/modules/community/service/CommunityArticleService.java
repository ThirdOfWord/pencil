package com.freeter.modules.community.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.community.entity.CommunityArticleEntity;
import com.freeter.modules.community.entity.vo.CommunityArticleVO;

import java.util.List;

/**
 * 社区推广
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface CommunityArticleService extends IService<CommunityArticleEntity> {
 List<CommunityArticleVO> getAllList(int currentPage);//全部分类
 List<CommunityArticleVO> getByGoodsClassList(int goodsClassifyId,int currentPage);//根据商品分类获取社区集合
}

