package com.freeter.modules.user.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.user.dao.WeixinMemberDao;
import com.freeter.modules.user.entity.WeixinMemberEntity;
import com.freeter.modules.user.service.WeixinMemberService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("weixinMemberService")
public class WeixinMemberServiceImpl extends ServiceImpl<WeixinMemberDao, WeixinMemberEntity> implements WeixinMemberService {

}
