package com.freeter.modules.money.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;

import java.util.List;

/**
 * 平台钱包
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 22:14:20
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MoneySetService extends IService<MoneySetEntity> {
 int checkZhuLiMoney();//判断需要分配答题卡的金额是否小于助力库的金额
 List<PackageCardVO> getFaKaKuMoney(int status);//判断需要分配答题卡的金额是否小于发卡库的金额
}

