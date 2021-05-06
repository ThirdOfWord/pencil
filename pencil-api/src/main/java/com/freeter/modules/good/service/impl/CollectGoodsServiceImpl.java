package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.good.dao.CollectGoodsDao;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;
import com.freeter.modules.good.service.CollectGoodsService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("collectGoodsService")
public class CollectGoodsServiceImpl extends ServiceImpl<CollectGoodsDao, CollectGoodsEntity> implements CollectGoodsService {
    /**
    * @Description: 当前用户收藏商品，再次点击取消收藏
    * @Author: Mr.Wang 
    * @Date: 2020/6/7 
    */
    @Override
    public R insertOrdelete(CollectGoodsEntity goodsEntity) {
        EntityWrapper<CollectGoodsEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("member_id", goodsEntity.getMemberId());
        entityWrapper.eq("supplier_id", goodsEntity.getSupplierId());
        entityWrapper.eq("goods_id", goodsEntity.getGoodsId());
        CollectGoodsEntity collectGoodsEntity=this.selectOne(entityWrapper);
        if (null == collectGoodsEntity){
            goodsEntity.setCreateTime((int)DateUtil.getUnixStamp());
            baseMapper.insert(goodsEntity);
            return R.ok("已添加收藏");
        }else {
            baseMapper.delete(entityWrapper);
            return R.ok("已取消收藏");
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
        List<CollectGoodsEntity> goodsVOList=baseMapper.getList(map);
        return R.ok().put("goodList", goodsVOList);
    }
}
