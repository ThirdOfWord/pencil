package com.freeter.modules.advert.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.advert.dao.BroadcastDao;
import com.freeter.modules.advert.entity.BroadcastEntity;
import com.freeter.modules.advert.service.BroadcastService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("broadcastService")
public class BroadcastServiceImpl extends ServiceImpl<BroadcastDao, BroadcastEntity> implements BroadcastService {
    /**
     * 获取广播前十条数据
     * @return
     */
    @Override
    public List<BroadcastEntity> getList() {
        List<BroadcastEntity> list=baseMapper.getList();
        return list;
    }
}
