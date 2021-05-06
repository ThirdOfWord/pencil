package com.freeter.modules.money.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;

import java.util.List;

/**
 * 平台钱包记录
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 22:14:20
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MoneySetRecordService extends IService<MoneySetRecordEntity> {
 List<PackageCardVO> addDaTiKa(int memberId); //用户注册成功获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录

 boolean addYaoQingRenDaTiKa(int pid); //用户注册成功邀请人获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
}

