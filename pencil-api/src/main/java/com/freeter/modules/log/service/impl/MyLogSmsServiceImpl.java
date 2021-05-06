package com.freeter.modules.log.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.log.dao.MyLogSmsDao;
import com.freeter.modules.log.entity.MyLogSmsEntity;
import com.freeter.modules.log.service.MyLogSmsService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("myLogSmsService")
public class MyLogSmsServiceImpl extends ServiceImpl<MyLogSmsDao, MyLogSmsEntity> implements MyLogSmsService {

}
