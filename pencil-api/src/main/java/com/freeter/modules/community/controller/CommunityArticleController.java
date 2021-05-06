package com.freeter.modules.community.controller;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.community.entity.CommunityArticleEntity;
import com.freeter.modules.community.entity.vo.CommunityArticleVO;
import com.freeter.modules.community.service.CommunityArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 社区推广
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-14 17:14:38
 */
@RestController
@RequestMapping("api/communityarticle")
@SuppressWarnings({"unchecked","rawtypes"})
public class CommunityArticleController {
    @Autowired
    private CommunityArticleService communityArticleService;

    /**
     * 社区信息
     * @param goodsClassifyId
     * @param currentPage
     * @return
     */
    @RequestMapping("list")
    public R list(@RequestParam(value = "goodsClassifyId",defaultValue = "0")int goodsClassifyId,
                     @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*6;
        if(goodsClassifyId==0){
            List<CommunityArticleVO> list =communityArticleService.getAllList(currentPage);//全部分类
            return R.ok().put("communityList", list);
        }else {
            List<CommunityArticleVO> list =communityArticleService.getByGoodsClassList(goodsClassifyId,currentPage);//根据商品分类获取社区集合
            return R.ok().put("communityList", list);
        }
    }

    /**
     * 分享统计接口
     * @param communityArticleId
     * @return
     */
    @RequestMapping("addPartake")
    public R addPartake(@RequestParam(value = "communityArticleId",defaultValue = "0")int communityArticleId){
        if (communityArticleId ==0){
            return R.error(499, "传值异常");
        }
        CommunityArticleEntity communityArticleEntity=communityArticleService.selectById(communityArticleId);
        communityArticleEntity.setShares(communityArticleEntity.getShares().intValue()+1);
        communityArticleService.updateById(communityArticleEntity);
        return R.ok();
    }
}
