package com.freeter.modules.community.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.community.dao.StoreUserDao;
import com.freeter.modules.community.entity.StoreUserEntity;
import com.freeter.modules.community.service.StoreUserService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("storeUserService")
public class StoreUserServiceImpl extends ServiceImpl<StoreUserDao, StoreUserEntity> implements StoreUserService {


}
