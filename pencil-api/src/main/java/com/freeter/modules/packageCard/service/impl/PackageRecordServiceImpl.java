package com.freeter.modules.packageCard.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.packageCard.dao.PackageRecordDao;
import com.freeter.modules.packageCard.entity.PackageRecordEntity;
import com.freeter.modules.packageCard.service.PackageRecordService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("packageRecordService")
public class PackageRecordServiceImpl extends ServiceImpl<PackageRecordDao, PackageRecordEntity> implements PackageRecordService {

}
