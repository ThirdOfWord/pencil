package com.freeter.modules.packageCard.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface PackageCardDao extends BaseMapper<PackageCardEntity> {
 PackageCardVO signInfor();//获取签到页面
 List<PackageCardVO> getPackageMoney();//获取助力库对应卡的金额
 List<PackageCardVO> getPackageFaKaKuMoney(int status);//获取助力库对应卡的金额
}
