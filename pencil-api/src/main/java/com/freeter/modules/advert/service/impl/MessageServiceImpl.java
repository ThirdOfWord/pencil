package com.freeter.modules.advert.service.impl;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.advert.dao.MessageDao;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.advert.service.MessageService;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageDao, MessageEntity> implements MessageService {

	@Override
	public void insertByMemberId(int memberId,int status) {
		String message=null;
		if (status==1){
			message="您发起的助力已完成，平台赠送您“10张答题卡”已存入卡包";
		}if (status==0){
			message="您参与的助力已完成，平台赠送您“10张答题卡”已存入卡包";
		}
		MessageEntity messageEntity=new MessageEntity();
		messageEntity.setCreateTime((int) DateUtil.getUnixStamp());
		messageEntity.setIsRead(0);
		messageEntity.setMemberId(memberId);
		messageEntity.setMessage(message);
		messageEntity.setType(0);
		messageEntity.setTypeId(0);
		baseMapper.insert(messageEntity);
	}
}
