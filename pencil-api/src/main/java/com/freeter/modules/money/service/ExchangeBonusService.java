package com.freeter.modules.money.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.money.entity.ExchangeBonusEntity;

/**
 * 兑换订单红包
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-05-03 23:41:35
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ExchangeBonusService extends IService<ExchangeBonusEntity> {
R getRedPack(int memberId,String coding);//领取红包 
R addShare(int memberId,int exchangeBonusId);//分享成功后分享次数减一为0时分享结束
}

