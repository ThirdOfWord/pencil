package com.freeter.modules.good.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.good.entity.ExchangeAttrValueEntity;
import com.freeter.modules.good.entity.vo.ExchangeAttrValueVO;

import java.util.List;

/**
 * 规格管理
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-05 16:23:57
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeAttrValueDao extends BaseMapper<ExchangeAttrValueEntity> {
 List<ExchangeAttrValueVO> getListByAttrId(int attr);//获取规格集合
}
