package com.freeter.modules.money.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.money.dao.MoneySetRecordDao;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.packageCard.entity.PackageEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.packageCard.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("moneySetRecordService")
@Transactional
public class MoneySetRecordServiceImpl extends ServiceImpl<MoneySetRecordDao, MoneySetRecordEntity> implements MoneySetRecordService {
    @Autowired
    private MoneySetService moneySetService;
    @Autowired
    private PackageService packageService;
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;
    /**
    * @Description:  用户注册成功获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public List<PackageCardVO> addDaTiKa(int memberId) {
        //判断注册送答题卡是否开启
        EntityWrapper<PackageEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("package_id", 1);
        entityWrapper.eq("status", 1);
        PackageEntity packageEntity=packageService.selectOne(entityWrapper);
        if (null == packageEntity){
            return null;
        }
        //判断需要分配答题卡的金额是否小于发卡库的金额
        List<PackageCardVO> list=moneySetService.getFaKaKuMoney(1);
        if (null == list || list.isEmpty()){
            return null;
        }
        packageEntity.setSales(packageEntity.getSales().intValue()+1);
        packageService.updateAllColumnById(packageEntity);
        //进行发卡
        BigDecimal money=new BigDecimal(0);
        for (PackageCardVO cardEntity:list){
            money=money.add(cardEntity.getAwardMoney().multiply(new BigDecimal(cardEntity.getNum())));
            EntityWrapper<AnswerMemberEntity> memberEntityEntityWrapper=new EntityWrapper<>();
            memberEntityEntityWrapper.eq("member_id", memberId);
            memberEntityEntityWrapper.eq("answer_classify_id",cardEntity.getAnswerClassifyId());
            AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(memberEntityEntityWrapper);
            if (null == answerMemberEntity){
                answerMemberEntity=new AnswerMemberEntity();
                answerMemberEntity.setMemberId(memberId);
                answerMemberEntity.setCreateTime((int) DateUtil.getUnixStamp());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                answerMemberEntity.setTotal(cardEntity.getNum());
                answerMemberEntity.setAnswerClassifyId(cardEntity.getAnswerClassifyId());
                answerMemberService.insert(answerMemberEntity);
            }else {
                answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()+cardEntity.getNum());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                answerMemberService.updateAllColumnById(answerMemberEntity);
            }
        }
        //2、修改平台钱包和钱包记录
        MoneySetEntity moneySetEntity=moneySetService.selectById(5);
        //发卡库减少记录
        MoneySetRecordEntity recordEntity=new MoneySetRecordEntity();
        recordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        recordEntity.setAddCut(1);//减|1|,加|0|
        recordEntity.setMoney(money);
        recordEntity.setMemberId(memberId);
        recordEntity.setMoneySetId(5);
        recordEntity.setAfter(moneySetEntity.getMoney().subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        recordEntity.setOld(moneySetEntity.getMoney());
        recordEntity.setTitle(moneySetEntity.getTitle());
        recordEntity.setSourceType(3);
        recordEntity.setSourceId(1);
        moneySetRecordService.insert(recordEntity);
        moneySetEntity.setMoney(moneySetEntity.getMoney().subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(moneySetEntity);
        //奖品库增加记录
        MoneySetEntity setEntity=moneySetService.selectById(2);
        MoneySetRecordEntity moneySetRecordEntity=new MoneySetRecordEntity();
        moneySetRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        moneySetRecordEntity.setAddCut(0);//减|1|,加|0|
        moneySetRecordEntity.setMoney(money);
        moneySetRecordEntity.setMemberId(memberId);
        moneySetRecordEntity.setMoneySetId(2);
        moneySetRecordEntity.setAfter(setEntity.getMoney().add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetRecordEntity.setOld(setEntity.getMoney());
        moneySetRecordEntity.setTitle(setEntity.getTitle());
        moneySetRecordEntity.setSourceType(3);
        moneySetRecordEntity.setSourceId(1);
        moneySetRecordService.insert(moneySetRecordEntity);
        setEntity.setMoney(setEntity.getMoney().add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(setEntity);
        return list;
    }

    /**
    * @Description:  用户注册成功邀请人获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
    * @Author: Mr.Wang 
    * @Date: 2020/9/9 
    */
    @Override
    public boolean addYaoQingRenDaTiKa(int pid) {
        //判断注册送答题卡是否开启
        EntityWrapper<PackageEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("package_id", 4);
        entityWrapper.eq("status", 1);
        PackageEntity packageEntity=packageService.selectOne(entityWrapper);
        if (null == packageEntity){
            return false;
        }
        //判断需要分配答题卡的金额是否小于发卡库的金额
        List<PackageCardVO> list=moneySetService.getFaKaKuMoney(4);
        if (null == list || list.isEmpty()){
            return false;
        }
        packageEntity.setSales(packageEntity.getSales().intValue()+1);
        packageService.updateAllColumnById(packageEntity);
        //进行发卡
        BigDecimal money=new BigDecimal(0);
        for (PackageCardVO cardEntity:list){
            money=money.add(cardEntity.getAwardMoney().multiply(new BigDecimal(cardEntity.getNum())));
            EntityWrapper<AnswerMemberEntity> memberEntityEntityWrapper=new EntityWrapper<>();
            memberEntityEntityWrapper.eq("member_id", pid);
            memberEntityEntityWrapper.eq("answer_classify_id",cardEntity.getAnswerClassifyId());
            AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(memberEntityEntityWrapper);
            if (null == answerMemberEntity){
                answerMemberEntity=new AnswerMemberEntity();
                answerMemberEntity.setMemberId(pid);
                answerMemberEntity.setCreateTime((int) DateUtil.getUnixStamp());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                answerMemberEntity.setTotal(cardEntity.getNum());
                answerMemberEntity.setAnswerClassifyId(cardEntity.getAnswerClassifyId());
                answerMemberService.insert(answerMemberEntity);
            }else {
                answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()+cardEntity.getNum());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                answerMemberService.updateAllColumnById(answerMemberEntity);
            }
        }
        //2、修改平台钱包和钱包记录
        MoneySetEntity moneySetEntity=moneySetService.selectById(5);
        //发卡库减少记录
        MoneySetRecordEntity recordEntity=new MoneySetRecordEntity();
        recordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        recordEntity.setAddCut(1);//减|1|,加|0|
        recordEntity.setMoney(money);
        recordEntity.setMemberId(pid);
        recordEntity.setMoneySetId(5);
        recordEntity.setAfter(moneySetEntity.getMoney().subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        recordEntity.setOld(moneySetEntity.getMoney());
        recordEntity.setTitle(moneySetEntity.getTitle());
        recordEntity.setSourceType(3);
        recordEntity.setSourceId(1);
        moneySetRecordService.insert(recordEntity);
        moneySetEntity.setMoney(moneySetEntity.getMoney().subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(moneySetEntity);
        //奖品库增加记录
        MoneySetEntity setEntity=moneySetService.selectById(2);
        MoneySetRecordEntity moneySetRecordEntity=new MoneySetRecordEntity();
        moneySetRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        moneySetRecordEntity.setAddCut(0);//减|1|,加|0|
        moneySetRecordEntity.setMoney(money);
        moneySetRecordEntity.setMemberId(pid);
        moneySetRecordEntity.setMoneySetId(2);
        moneySetRecordEntity.setAfter(setEntity.getMoney().add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetRecordEntity.setOld(setEntity.getMoney());
        moneySetRecordEntity.setTitle(setEntity.getTitle());
        moneySetRecordEntity.setSourceType(3);
        moneySetRecordEntity.setSourceId(1);
        moneySetRecordService.insert(moneySetRecordEntity);
        setEntity.setMoney(setEntity.getMoney().add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(setEntity);
        return true;
    }
}
