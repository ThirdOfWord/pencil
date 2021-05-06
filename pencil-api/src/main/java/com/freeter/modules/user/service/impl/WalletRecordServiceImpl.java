package com.freeter.modules.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.user.dao.WalletRecordDao;
import com.freeter.modules.user.entity.WalletRecordEntity;
import com.freeter.modules.user.service.WalletRecordService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("walletRecordService")
public class WalletRecordServiceImpl extends ServiceImpl<WalletRecordDao, WalletRecordEntity> implements WalletRecordService {

}
