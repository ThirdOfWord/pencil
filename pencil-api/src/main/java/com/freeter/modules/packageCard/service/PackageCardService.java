package com.freeter.modules.packageCard.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.packageCard.entity.PackageCardEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;

import java.util.List;

/**
 * 卡包卡片
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface PackageCardService extends IService<PackageCardEntity> {
 R signInfor();//获取签到页面
 R signAdd(int memberId);//用户签到领取答题卡
 List<PackageCardVO> getPackageMoney();//获取助力库对应卡的金额
 List<PackageCardVO> getPackageFaKaKuMoney(int status);//获取发卡库对应卡的金额
}

