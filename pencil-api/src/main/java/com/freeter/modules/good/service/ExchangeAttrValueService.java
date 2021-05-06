package com.freeter.modules.good.service;


import com.baomidou.mybatisplus.service.IService;
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
public interface ExchangeAttrValueService extends IService<ExchangeAttrValueEntity> {
 List<ExchangeAttrValueVO> getListByAttrId(int attr);//获取规格集合

}

