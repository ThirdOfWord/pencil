package com.freeter.modules.other.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.other.dao.BonusRecordDao;
import com.freeter.modules.other.entity.BonusRecordEntity;
import com.freeter.modules.other.entity.BonusSetEntity;
import com.freeter.modules.other.service.BonusRecordService;
import com.freeter.modules.other.service.BonusSetService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("bonusRecordService")
public class BonusRecordServiceImpl extends ServiceImpl<BonusRecordDao, BonusRecordEntity> implements BonusRecordService {
    @Autowired
    private BonusSetService bonusSetService;
    @Autowired
    private BonusRecordService bonusRecordService;
    @Autowired
    private MemberService memberService;
    /**
     * 领取红包
     * @param memberId
     * @param coding
     * @return
     */
    @Override
    @Transactional
    public synchronized R insert(int memberId, String coding) {
        //1、判断当前兑换码是否有效
        EntityWrapper<BonusSetEntity> bonusSetWrapper=new EntityWrapper<>();
        bonusSetWrapper.eq("coding", coding);
        bonusSetWrapper.gt("num", 0);
        bonusSetWrapper.eq("status", 1);
        BonusSetEntity bonusSetEntity=bonusSetService.selectOne(bonusSetWrapper);
        if (null == bonusSetEntity){
            return R.error(499, "兑换码无效");
        }
        int bonusSetId=bonusSetEntity.getBonusSetId();
        //2、判断当前用户是否已领取红包
        EntityWrapper<BonusRecordEntity> recordWrapper=new EntityWrapper<>();
        recordWrapper.eq("bonus_set_id", bonusSetId);
        recordWrapper.eq("member_id", memberId);
        BonusRecordEntity bonusRecordEntity=bonusRecordService.selectOne(recordWrapper);
        if (null != bonusRecordEntity){
            return R.error(499, "已重复领取");
        }
        //3、获取当前用户 可以领取红包
        MemberEntity memberEntity=memberService.selectById(memberId);
        if (null == memberEntity){
            return R.error(499, "网络开小差");
        }
        BigDecimal money=bonusSetEntity.getMoney();
        BonusRecordEntity recordEntity=new BonusRecordEntity();
        recordEntity.setBonusSetId(bonusSetId);
        recordEntity.setCoding(coding);
        recordEntity.setMemberId(memberId);
        recordEntity.setMoney(money);
        recordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        baseMapper.insert(recordEntity);//添加领红包纪录
        bonusSetEntity.setSales(bonusSetEntity.getSales().intValue()+1);
        bonusSetEntity.setNum(bonusSetEntity.getNum().intValue()-1);
        bonusSetService.updateById(bonusSetEntity);//修改销量和数量
        memberEntity.setWallet(memberEntity.getWallet().add(money));
        memberService.updateById(memberEntity);
        return R.ok().put("money",money);
    }
}
