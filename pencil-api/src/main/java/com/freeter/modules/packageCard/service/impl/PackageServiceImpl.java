package com.freeter.modules.packageCard.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.packageCard.dao.PackageDao;
import com.freeter.modules.packageCard.entity.PackageEntity;
import com.freeter.modules.packageCard.service.PackageService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("packageService")
public class PackageServiceImpl extends ServiceImpl<PackageDao, PackageEntity> implements PackageService {

}
