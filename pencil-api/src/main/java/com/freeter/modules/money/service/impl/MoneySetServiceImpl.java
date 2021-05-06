package com.freeter.modules.money.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.money.dao.MoneySetDao;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.packageCard.entity.PackageCardEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.packageCard.service.PackageCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("moneySetService")
@Transactional
public class MoneySetServiceImpl extends ServiceImpl<MoneySetDao, MoneySetEntity> implements MoneySetService {
    @Autowired
    private PackageCardService packageCardService;
    /**
    * @Description:  判断需要分配答题卡的金额是否小于发卡库的金额
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public List<PackageCardVO> getFaKaKuMoney(int status) {
        List<PackageCardVO> list =packageCardService.getPackageFaKaKuMoney(status);//获取助力库对应卡的金额
        BigDecimal money=new BigDecimal(0);
        for (PackageCardVO cardVO:list){
            money=money.add(cardVO.getAwardMoney().multiply(new BigDecimal(cardVO.getNum())));
        }
        MoneySetEntity moneySetEntity=baseMapper.selectById(5);
        if (null == moneySetEntity){
            return null;
        }
        if (moneySetEntity.getMoney().compareTo(money) > -1){
            return list;//可以分配答题卡
        }else {
            return null;
        }
    }

    /**
    * @Description: 判断需要分配答题卡的金额是否小于助力库的金额
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public int checkZhuLiMoney() {
        List<PackageCardVO> list =packageCardService.getPackageMoney();//获取助力库对应卡的金额
        BigDecimal money=new BigDecimal(0);
        for (PackageCardVO cardVO:list){
            money=money.add(cardVO.getAwardMoney().multiply(new BigDecimal(cardVO.getNum())));
        }
        MoneySetEntity moneySetEntity=baseMapper.selectById(3);
        if (null == moneySetEntity){
            return 0;
        }
        money=money.multiply(new BigDecimal(3));
        if (moneySetEntity.getMoney().compareTo(money) > -1){
            return 1;//可以分配答题卡
        }else {
            return 0;
        }
    }
}

