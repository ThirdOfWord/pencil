package com.freeter.modules.other.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.other.dao.BonusSetDao;
import com.freeter.modules.other.entity.BonusSetEntity;
import com.freeter.modules.other.service.BonusSetService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("bonusSetService")
public class BonusSetServiceImpl extends ServiceImpl<BonusSetDao, BonusSetEntity> implements BonusSetService {

}
