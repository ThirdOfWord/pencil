package com.freeter.modules.community.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.community.dao.CommunityArticleDao;
import com.freeter.modules.community.entity.CommunityArticleEntity;
import com.freeter.modules.community.entity.vo.CommunityArticleGoodsVO;
import com.freeter.modules.community.entity.vo.CommunityArticleVO;
import com.freeter.modules.community.service.CommunityArticleGoodsService;
import com.freeter.modules.community.service.CommunityArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("communityArticleService")
public class CommunityArticleServiceImpl extends ServiceImpl<CommunityArticleDao, CommunityArticleEntity> implements CommunityArticleService {
    @Autowired
    private CommunityArticleGoodsService communityArticleGoodsService;
    @Override
    public List<CommunityArticleVO> getAllList(int currentPage) {
        List<CommunityArticleVO> list =baseMapper.getAllList(currentPage);//全部分类
        if (!list.isEmpty()){
            for (CommunityArticleVO communityArticle:list){
                int communityArticleId=communityArticle.getCommunityArticleId().intValue();
                List<CommunityArticleGoodsVO> listGoods=communityArticleGoodsService.getByCommunityArticleIdList(communityArticleId);//获取社区推广信息中的所有图片
                if (!listGoods.isEmpty()){
                    communityArticle.setGoodsVOS(listGoods);
                }
            }
        }
        return list;
    }

    @Override
    public List<CommunityArticleVO> getByGoodsClassList(int goodsClassifyId, int currentPage) {
        List<CommunityArticleVO> list =baseMapper.getByGoodsClassList(goodsClassifyId,currentPage);//根据商品分类获取社区集合
        if (!list.isEmpty()){
            for (CommunityArticleVO communityArticle:list){
                int communityArticleId=communityArticle.getCommunityArticleId().intValue();
                List<CommunityArticleGoodsVO> listGoods=communityArticleGoodsService.getByCommunityArticleIdList(communityArticleId);//获取社区推广信息中的所有图片
                if (!listGoods.isEmpty()){
                    communityArticle.setGoodsVOS(listGoods);
                }
            }
        }
        return list;
    }
}
