package com.freeter.modules.advert.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.advert.entity.MessageEntity;

/**
 * 消息表
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-16 16:18:35
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MessageService extends IService<MessageEntity> {
void insertByMemberId(int memberId,int status);//消息通知
}

