package com.freeter.modules.community.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface CommunityArticleDao extends BaseMapper<CommunityArticleEntity> {
 List<CommunityArticleVO> getAllList(int currentPage);//全部分类
 List<CommunityArticleVO> getByGoodsClassList(int goodsClassifyId,int currentPage);//根据商品分类获取社区集合
}
