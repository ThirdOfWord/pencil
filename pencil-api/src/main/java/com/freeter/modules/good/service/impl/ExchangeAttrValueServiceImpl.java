package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.good.dao.ExchangeAttrValueDao;
import com.freeter.modules.good.entity.ExchangeAttrValueEntity;
import com.freeter.modules.good.entity.vo.ExchangeAttrValueVO;
import com.freeter.modules.good.service.ExchangeAttrValueService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("exchangeAttrValueService")
public class ExchangeAttrValueServiceImpl extends ServiceImpl<ExchangeAttrValueDao, ExchangeAttrValueEntity> implements ExchangeAttrValueService {

    @Override
    public List<ExchangeAttrValueVO> getListByAttrId(int attr) {
        List<ExchangeAttrValueVO> attrValueList=baseMapper.getListByAttrId(attr);//获取规格集合
        return attrValueList;
    }
}
