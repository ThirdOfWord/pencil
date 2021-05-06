package com.freeter.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.freeter.modules.sys.entity.MyLogSmsEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 
 * 
 * @author xuchen
 * @email 171998110@qq.com
 * @date 2019-05-26 13:45:01
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MyLogSmsDao extends BaseMapper<MyLogSmsEntity> {
 List<MyLogSmsEntity> selectLogSms(Page page, @Param("ew") Wrapper<MyLogSmsEntity> wrapper);

}
