package com.freeter.modules.pingtaiGoods.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.pingtaiGoods.entity.GoodsGoldRushEntity;
import com.freeter.modules.pingtaiGoods.entity.TaoLiJinInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 淘礼金
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-08-08 11:19:04
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface GoodsGoldRushService extends IService<GoodsGoldRushEntity> {
 List<TaoLiJinInfo> getListAll(int currentPage, HttpServletRequest request);
 R receive(int memberId,int goodsGoldRushId);
}

