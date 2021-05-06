package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.good.dao.MemberCollectGoodsDao;
import com.freeter.modules.good.entity.MemberCollectGoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;
import com.freeter.modules.good.service.MemberCollectGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("memberCollectGoodsService")
public class MemberCollectGoodsServiceImpl extends ServiceImpl<MemberCollectGoodsDao, MemberCollectGoodsEntity> implements MemberCollectGoodsService {
    @Autowired
    private MemberCollectGoodsService memberCollectGoodsService;
    /**
     * 当前用户收藏商品，再次点击取消收藏
     * @param supplierGoodsId
     * @param memberId
     * @return
     */
    @Override
    public R insertOrdelete(Long supplierGoodsId, int memberId) {
        EntityWrapper<MemberCollectGoodsEntity> entityEntityWrapper=new EntityWrapper<>();
        entityEntityWrapper.eq("member_id", memberId);
        entityEntityWrapper.eq("supplier_goods_id",supplierGoodsId);
        MemberCollectGoodsEntity memberCollectGoodsEntity=memberCollectGoodsService.selectOne(entityEntityWrapper);
        if (null != memberCollectGoodsEntity){
            memberCollectGoodsService.delete(entityEntityWrapper);
            return R.ok("取消收藏");
        }else {
            memberCollectGoodsEntity=new MemberCollectGoodsEntity();
            memberCollectGoodsEntity.setMemberId(memberId);
            memberCollectGoodsEntity.setSupplierGoodsId(supplierGoodsId);
            memberCollectGoodsEntity.setCreateTime((int)DateUtil.getUnixStamp());
            baseMapper.insert(memberCollectGoodsEntity);
            return R.ok("收藏成功");
        }
    }

    /**
     * 显示当前用户收藏的商品
     * @param memberId
     * @param currentPage
     * @return
     */
    @Override
    public R getList(int memberId, int currentPage) {
        Map<String,Object> map =new HashMap<>();
        map.put("memberId", memberId);
        map.put("currentPage", currentPage);
        List<GoodsVO> goodsVOList=baseMapper.getList(map);
        return R.ok().put("goodList", goodsVOList);
    }
}
