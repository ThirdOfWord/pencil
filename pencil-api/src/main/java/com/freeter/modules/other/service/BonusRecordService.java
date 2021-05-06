package com.freeter.modules.other.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.other.entity.BonusRecordEntity;

/**
 * 红包领取记录
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-03-17 15:47:20
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface BonusRecordService extends IService<BonusRecordEntity> {
  R insert(int memberId, String coding);
}

