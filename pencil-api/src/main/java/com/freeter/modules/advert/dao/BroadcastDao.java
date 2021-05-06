package com.freeter.modules.advert.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.advert.entity.BroadcastEntity;

import java.util.List;

/**
 * 广播
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 16:49:35
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface BroadcastDao extends BaseMapper<BroadcastEntity> {
 List<BroadcastEntity> getList();//获取广播前十条数据
}
