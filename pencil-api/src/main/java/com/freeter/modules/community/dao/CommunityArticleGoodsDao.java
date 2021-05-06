package com.freeter.modules.community.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.community.entity.CommunityArticleGoodsEntity;
import com.freeter.modules.community.entity.vo.CommunityArticleGoodsVO;

import java.util.List;

/**
 * 社区推广商品
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface CommunityArticleGoodsDao extends BaseMapper<CommunityArticleGoodsEntity> {
 List<CommunityArticleGoodsVO> getByCommunityArticleIdList(int communityArticleId);//获取社区推广信息中的所有图片
}
