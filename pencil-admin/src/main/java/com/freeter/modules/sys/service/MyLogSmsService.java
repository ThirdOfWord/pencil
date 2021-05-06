package com.freeter.modules.sys.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.sys.entity.MyLogSmsEntity;

import java.util.Map;


/**
 * 
 *
 * @author xuchen
 * @email 171998110@qq.com
 * @date 2019-05-26 13:45:01
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MyLogSmsService extends IService<MyLogSmsEntity> {
 Page<MyLogSmsEntity> queryPage(Map<String, Object> params, Wrapper<MyLogSmsEntity> wrapper);
}

