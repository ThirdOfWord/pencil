package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.ExchangeGoodsEntity;
import com.freeter.modules.good.entity.vo.ExchangeGoodsVO;

import java.util.List;

/**
 * 兑换商品
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:56
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeGoodsDao extends BaseMapper<ExchangeGoodsEntity> {

 List<ExchangeGoodsVO> getByanswerClassifyIdList(int answerClassifyId);//3、显示每个卡对应的商品集合

}
