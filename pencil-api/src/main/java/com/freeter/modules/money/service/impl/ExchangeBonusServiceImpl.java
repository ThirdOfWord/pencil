package com.freeter.modules.money.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.money.dao.ExchangeBonusDao;
import com.freeter.modules.money.entity.ExchangeBonusEntity;
import com.freeter.modules.money.entity.ExchangeBonusRecordEntity;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.money.service.ExchangeBonusRecordService;
import com.freeter.modules.money.service.ExchangeBonusService;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.service.ExchangeOrderService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.WalletRecordEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WalletRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("exchangeBonusService")
@Transactional
public class ExchangeBonusServiceImpl extends ServiceImpl<ExchangeBonusDao, ExchangeBonusEntity> implements ExchangeBonusService {
    @Autowired
    private ExchangeBonusService exchangeBonusService;
    @Autowired
    private ExchangeBonusRecordService exchangeBonusRecordService;
    @Autowired
    private WalletRecordService walletRecordService;
    @Autowired
    private MoneySetService moneySetService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;
    /**
    * @Description: ??????????????????
    * @Author: Mr.Wang??
    * @Date: 2020/5/4??
    */
    @Override
    public R getRedPack(int memberId, String coding) {
        //1????????????????????????????????????
        EntityWrapper<ExchangeBonusEntity> bonusEntityWrapper=new EntityWrapper<>();
        bonusEntityWrapper.eq("coding", coding);
        bonusEntityWrapper.eq("status", 0);
        ExchangeBonusEntity bonusEntity=exchangeBonusService.selectOne(bonusEntityWrapper);
        if (null == bonusEntity){
            return R.error(499, "???????????????");
        }
        if (memberId == bonusEntity.getMemberId().intValue()){
            return R.error(499, "??????????????????");
        }
        int num = bonusEntity.getNum();//??????
        int sales = bonusEntity.getSales();//?????????
        BigDecimal price=bonusEntity.getPrice();//????????????
        if (sales >= num || num==0){
            return R.error(499, "??????????????????");
        }
        int bonusId=bonusEntity.getExchangeBonusId();
        //???????????????????????????????????????
        EntityWrapper<ExchangeBonusRecordEntity> bonusRecordEntityWrapper=new EntityWrapper<>();
        bonusRecordEntityWrapper.eq("exchange_bonus_id", bonusId);
        bonusRecordEntityWrapper.eq("member_id", memberId);
        ExchangeBonusRecordEntity exchangeBonusRecordEntity=exchangeBonusRecordService.selectOne(bonusRecordEntityWrapper);
        if (null != exchangeBonusRecordEntity){
            return R.error(499, "??????????????????");
        }
        bonusEntity.setSales(bonusEntity.getSales().intValue()+1);
        bonusEntity.setUpdateTime((int)DateUtil.getUnixStamp());
        baseMapper.updateAllColumnById(bonusEntity);
        //???????????????????????????????????????????????????
        sales=sales+1;
        if (sales == num){
            ExchangeOrderEntity orderEntity =exchangeOrderService.selectById(bonusEntity.getExchangeOrderId());
            if (null != orderEntity){
                orderEntity.setStatus(3);
                orderEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                exchangeOrderService.updateAllColumnById(orderEntity);
            }
        }
        //???????????????????????????????????????
        MemberEntity memberEntity=memberService.selectById(memberId);
        BigDecimal wallet=memberEntity.getWallet();//?????????????????????
        memberEntity.setWallet(wallet.add(price));
        memberEntity.setUpdateTime(DateUtil.getUnixStamp());
        memberEntity.setWalletToday(memberEntity.getWalletToday().add(price));
        memberEntity.setWalletEstimate(memberEntity.getWalletEstimate().add(price));

        WalletRecordEntity walletRecordEntity=new WalletRecordEntity();
        walletRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        walletRecordEntity.setMemberId(memberId);
        walletRecordEntity.setAddCut(0);//??????1 ??????0
        walletRecordEntity.setMoney(price);
        walletRecordEntity.setAfter(wallet.add(price));
        walletRecordEntity.setOld(wallet);
        walletRecordEntity.setSourceType(4);//??????|4|?????????|3|????????????|2|???????????????|1|???????????????|0|
        walletRecordEntity.setSourceId(bonusId);
        walletRecordService.insert(walletRecordEntity);

        memberService.updateAllColumnById(memberEntity);
        //??????????????????????????????
        MoneySetEntity setEntity=moneySetService.selectById(4);

        MoneySetRecordEntity moneySetRecordEntity=new MoneySetRecordEntity();
        moneySetRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        moneySetRecordEntity.setAddCut(0);//???|1|,???|0|
        moneySetRecordEntity.setMoney(price);
        moneySetRecordEntity.setMemberId(memberId);
        moneySetRecordEntity.setMoneySetId(4);
        moneySetRecordEntity.setAfter(setEntity.getMoney().add(price).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetRecordEntity.setOld(setEntity.getMoney());
        moneySetRecordEntity.setTitle(setEntity.getTitle());
        moneySetRecordEntity.setSourceType(4);
        moneySetRecordEntity.setSourceId(bonusId);
        moneySetRecordEntity.setMobile(memberEntity.getMobile());
        moneySetRecordEntity.setNickname(memberEntity.getNickname());
        moneySetRecordService.insert(moneySetRecordEntity);
        setEntity.setMoney(setEntity.getMoney().add(price).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(setEntity);
        //??????????????????
        ExchangeBonusRecordEntity bonusRecordEntity=new ExchangeBonusRecordEntity();
        bonusRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        bonusRecordEntity.setUpdateTime((int)DateUtil.getUnixStamp());
        bonusRecordEntity.setExchangeBonusId(bonusId);
        bonusRecordEntity.setMemberId(memberId);
        bonusRecordEntity.setMobile(memberEntity.getMobile());
        bonusRecordEntity.setNickname(memberEntity.getNickname());
        bonusRecordEntity.setPrice(price);
        exchangeBonusRecordService.insert(bonusRecordEntity);
        return R.ok().put("money", price);
    }
    /**
    * @Description: ????????????????????????????????????0???????????????
    * @Author: Mr.Wang??
    * @Date: 2020/5/6??
    */
    @Override
    public R addShare(int memberId, int exchangeBonusId) {
        ExchangeBonusEntity bonusEntity=baseMapper.selectById(exchangeBonusId);
        if (null == bonusEntity){
            return R.error(499, "????????????");
        }
        int shareNum=bonusEntity.getShareNum().intValue();//????????????????????????
        if (shareNum == 0){
            return R.ok().put("shareNum", shareNum);
        }
        bonusEntity.setShareNum(shareNum-1);
        bonusEntity.setUpdateTime((int)DateUtil.getUnixStamp());
        baseMapper.updateAllColumnById(bonusEntity);
        if (bonusEntity.getShareNum().intValue() == 0){
            //??????????????????
            ExchangeOrderEntity orderEntity =exchangeOrderService.selectById(bonusEntity.getExchangeOrderId());
            if (null != orderEntity){
                orderEntity.setStatus(3);
                orderEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                exchangeOrderService.updateAllColumnById(orderEntity);
            }
        }
        return R.ok().put("shareNum", bonusEntity.getShareNum());
    }
}
