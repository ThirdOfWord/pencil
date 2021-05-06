package com.freeter.modules.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.user.dao.MemberAddressDao;
import com.freeter.modules.user.entity.MemberAddressEntity;
import com.freeter.modules.user.service.MemberAddressService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("memberAddressService")
public class MemberAddressServiceImpl extends ServiceImpl<MemberAddressDao, MemberAddressEntity> implements MemberAddressService {


}
