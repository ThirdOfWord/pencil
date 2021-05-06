package com.freeter.modules.user.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.user.entity.DeductRecordEntity;

import java.math.BigDecimal;

/**
 * 提现记录表
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-22 16:14:16
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface DeductRecordService extends IService<DeductRecordEntity> {
 R pay(int memberId, BigDecimal money);
}

