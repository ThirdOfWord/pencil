package com.freeter.modules.packageCard.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.packageCard.dao.PackageCardDao;
import com.freeter.modules.packageCard.entity.PackageCardEntity;
import com.freeter.modules.packageCard.entity.PackageEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.packageCard.service.PackageCardService;
import com.freeter.modules.packageCard.service.PackageService;
import com.freeter.token.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("packageCardService")
@Transactional
public class PackageCardServiceImpl extends ServiceImpl<PackageCardDao, PackageCardEntity> implements PackageCardService {
    @Autowired
    private PackageService packageService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private MoneySetService moneySetService;
    /**
    * @Description:  获取签到页面 
    * @Author: Mr.Wang 
    * @Date: 2020/5/3 
    */
    @Override
    public R signInfor() {
        EntityWrapper<PackageEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("package_id", 2);
        entityWrapper.eq("status", 1);
        PackageEntity packageEntity=packageService.selectOne(entityWrapper);
        if (null == packageEntity){
            return R.ok().put("signInfor", null);
        }
        PackageCardVO packageCardVO=baseMapper.signInfor();
        return R.ok().put("signInfor", packageCardVO);
    }
    /**
    * @Description:  用户签到领取答题卡
    * @Author: Mr.Wang 
    * @Date: 2020/5/3 
    */
    @Override
    public synchronized R signAdd(int memberId) {
        //查看签到是否开放
        EntityWrapper<PackageEntity> entityWrapper=new EntityWrapper();
        entityWrapper.eq("package_id", 2);
        entityWrapper.eq("status", 1);
        PackageEntity packageEntity=packageService.selectOne(entityWrapper);
        if (null == packageEntity){
            return R.error(499, "签到接口已关闭");
        }
        //查询当前用户是否已签到
        EntityWrapper<MoneySetRecordEntity> moneySetRecordWrapper=new EntityWrapper();
        moneySetRecordWrapper.eq("member_id", memberId);
        moneySetRecordWrapper.eq("source_type", 3);
        moneySetRecordWrapper.eq("source_id", 2);
        moneySetRecordWrapper.orderBy("create_time",false);
        MoneySetRecordEntity recordEntity=moneySetRecordService.selectOne(moneySetRecordWrapper);
        if (null != recordEntity){
            //判断时间是否是今天的
            int createTime=recordEntity.getCreateTime();
            boolean istoday =DateUtil.isToday(DateUtil.TimestampToDate(createTime));
            if (istoday){
              return R.error(499, "今天已签到过");
            }
        }
        //判断发卡库金额和签到答题卡金额
        MoneySetEntity moneySetEntity=moneySetService.selectById(5);
        PackageCardVO packageCardVO=baseMapper.signInfor();
        BigDecimal faKaKuMoney =moneySetEntity.getMoney();//发卡库金额
        BigDecimal money=packageCardVO.getAwardMoney().multiply(new BigDecimal(packageCardVO.getNum()));//签到金额
        if (faKaKuMoney.compareTo(money) == -1){
            return R.error(499, "发卡库金额不足");
        }
        packageEntity.setSales(packageEntity.getSales().intValue()+1);
        packageService.updateAllColumnById(packageEntity);
        //发卡库减少 记录产生  奖品库增加  记录产生  用户答题卡新增
        //用户答题卡新增
        int answerClassifyId=packageCardVO.getAnswerClassifyId();
        int num=packageCardVO.getNum();
        EntityWrapper<AnswerMemberEntity> memberEntityEntityWrapper=new EntityWrapper<>();
        memberEntityEntityWrapper.eq("member_id", memberId);
        memberEntityEntityWrapper.eq("answer_classify_id",answerClassifyId);
        AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(memberEntityEntityWrapper);
        if (null == answerMemberEntity){
            answerMemberEntity=new AnswerMemberEntity();
            answerMemberEntity.setMemberId(memberId);
            answerMemberEntity.setCreateTime((int)DateUtil.getUnixStamp());
            answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            answerMemberEntity.setTotal(num);
            answerMemberEntity.setAnswerClassifyId(answerClassifyId);
            answerMemberService.insert(answerMemberEntity);
        }else {
            answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()+num);
            answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            answerMemberService.updateAllColumnById(answerMemberEntity);
        }
        //发卡库减少 记录产生
        MoneySetRecordEntity faKaRecordEntity=new MoneySetRecordEntity();
        faKaRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        faKaRecordEntity.setAddCut(1);//减|1|,加|0|
        faKaRecordEntity.setMoney(money);
        faKaRecordEntity.setMemberId(memberId);
        faKaRecordEntity.setMoneySetId(5);
        faKaRecordEntity.setAfter(faKaKuMoney.subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        faKaRecordEntity.setOld(faKaKuMoney);
        faKaRecordEntity.setTitle(moneySetEntity.getTitle());
        faKaRecordEntity.setSourceType(3);
        faKaRecordEntity.setSourceId(2);
        moneySetRecordService.insert(faKaRecordEntity);
        moneySetEntity.setMoney(faKaKuMoney.subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(moneySetEntity);
        //奖品库增加  记录产生
        MoneySetEntity setEntity=moneySetService.selectById(2);
        BigDecimal jiangPinKuMoney=setEntity.getMoney();//奖品库金额
        MoneySetRecordEntity jangPinKuRecordEntity=new MoneySetRecordEntity();
        jangPinKuRecordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        jangPinKuRecordEntity.setAddCut(0);//减|1|,加|0|
        jangPinKuRecordEntity.setMoney(money);
        jangPinKuRecordEntity.setMemberId(memberId);
        jangPinKuRecordEntity.setMoneySetId(2);
        jangPinKuRecordEntity.setAfter(jiangPinKuMoney.add(money).setScale(2,BigDecimal.ROUND_DOWN));
        jangPinKuRecordEntity.setOld(jiangPinKuMoney);
        jangPinKuRecordEntity.setTitle(setEntity.getTitle());
        jangPinKuRecordEntity.setSourceType(3);
        jangPinKuRecordEntity.setSourceId(2);
        moneySetRecordService.insert(jangPinKuRecordEntity);
        setEntity.setMoney(jiangPinKuMoney.add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(setEntity);
        return R.ok();
    }

    /**
    * @Description:  获取助力库对应卡的金额
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public List<PackageCardVO> getPackageMoney() {
        List<PackageCardVO> list =baseMapper.getPackageMoney();
        return list;
    }
    /**
    * @Description:  注册时获取发卡库对应卡的金额
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public List<PackageCardVO> getPackageFaKaKuMoney(int status) {
        List<PackageCardVO> list =baseMapper.getPackageFaKaKuMoney(status);
        return list;
    }
}
