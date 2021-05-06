package com.freeter.modules.community.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.community.dao.CommunityArticleGoodsDao;
import com.freeter.modules.community.entity.CommunityArticleGoodsEntity;
import com.freeter.modules.community.entity.vo.CommunityArticleGoodsVO;
import com.freeter.modules.community.service.CommunityArticleGoodsService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("communityArticleGoodsService")
public class CommunityArticleGoodsServiceImpl extends ServiceImpl<CommunityArticleGoodsDao, CommunityArticleGoodsEntity> implements CommunityArticleGoodsService {

    @Override
    public List<CommunityArticleGoodsVO> getByCommunityArticleIdList(int communityArticleId) {
        List<CommunityArticleGoodsVO> listGoods=baseMapper.getByCommunityArticleIdList(communityArticleId);//获取社区推广信息中的所有图片
        return listGoods;
    }
}
