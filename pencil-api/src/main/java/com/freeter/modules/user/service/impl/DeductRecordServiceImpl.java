package com.freeter.modules.user.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.user.dao.DeductRecordDao;
import com.freeter.modules.user.entity.DeductRecordEntity;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.DeductRecordService;
import com.freeter.modules.user.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("deductRecordService")
public class DeductRecordServiceImpl extends ServiceImpl<DeductRecordDao, DeductRecordEntity> implements DeductRecordService {
    @Autowired
    private MemberService memberService;

    @Override
    @Transactional
    public R pay(int memberId, BigDecimal money) {
        MemberEntity memberEntity = memberService.selectById(memberId);
        BigDecimal wallet=memberEntity.getWallet();//当前余额
        if (money.compareTo(wallet) == 1){
            return R.error(499, "余额不足");
        }
        BigDecimal balance=wallet.subtract(money);//剩余总额
        DeductRecordEntity deductRecordEntity=new DeductRecordEntity();
        deductRecordEntity.setCreateTime((int) DateUtil.getUnixStamp());
        deductRecordEntity.setUpdateTime((int) DateUtil.getUnixStamp());
        deductRecordEntity.setAccount(memberEntity.getAccountAlipay());
        deductRecordEntity.setBefore(wallet);
        deductRecordEntity.setAfter(balance);
        deductRecordEntity.setMoney(money);
        deductRecordEntity.setMemberId(memberId);
        deductRecordEntity.setStatus(0);
        deductRecordEntity.setName(memberEntity.getName());
        baseMapper.insert(deductRecordEntity);
        memberEntity.setWallet(balance);
        memberService.updateById(memberEntity);
        return R.ok();
    }
}
