package com.freeter.modules.sys.service.impl;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.utils.Query;
import com.freeter.modules.sys.dao.MyLogSmsDao;
import com.freeter.modules.sys.entity.MyLogSmsEntity;
import com.freeter.modules.sys.service.MyLogSmsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("myLogSmsService")
public class MyLogSmsServiceImpl extends ServiceImpl<MyLogSmsDao, MyLogSmsEntity> implements MyLogSmsService {
    @Override
    public Page<MyLogSmsEntity> queryPage(Map<String, Object> params, Wrapper<MyLogSmsEntity> wrapper) {
        Page<MyLogSmsEntity> page =new Query<MyLogSmsEntity>(params).getPage();
        List<MyLogSmsEntity> list=baseMapper.selectLogSms(page,wrapper);
        return page.setRecords(list);
    }

}
