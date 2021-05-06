package com.freeter.modules.answer.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.DateUtils;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.dao.AnswerMemberDao;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.entity.vo.AnswerMemberVO;
import com.freeter.modules.answer.service.AnswerClassifyService;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.packageCard.entity.PackageCardEntity;
import com.freeter.modules.packageCard.entity.PackageEntity;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.packageCard.service.PackageCardService;
import com.freeter.modules.packageCard.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("answerMemberService")
public class AnswerMemberServiceImpl extends ServiceImpl<AnswerMemberDao, AnswerMemberEntity> implements AnswerMemberService {
    @Autowired
    private AnswerClassifyService answerClassifyService;
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private PackageCardService packageCardService;
    @Autowired
    private MoneySetService moneySetService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;
    @Autowired
    private PackageService packageService;

    /**
     * 显示可兑换的卡包
     * @param memberId
     * @return
     */
    @Override
    public List<AnswerMemberVO> getDuihuan(int memberId) {
        List<AnswerMemberVO> list=baseMapper.getDuihuan(memberId);
        return list;
    }

    @Override
    public List<AnswerMemberVO> getList(int memberId) {
        List<AnswerMemberVO> list=baseMapper.getList(memberId);
        return list;
    }
    /**
     * 查看当前卡是否可以合并或拆分
     * @param answerClassifyId
     * @param status 0:合并当前卡，1：拆分当前卡
     * @return
     */
    @Override
    public R check(int answerClassifyId, int memberId, int status) {
        AnswerClassifyEntity classifyEntity= answerClassifyService.selectById(answerClassifyId);
        if (status==0){
           if (null !=classifyEntity){
               if (classifyEntity.getIsMerge()==1){
                   return R.ok();
               }
           }
            return R.error(499, "此卡不支持被合成");
        }else {
            if (null !=classifyEntity){
                if (classifyEntity.getIsSplit()==1){
                    EntityWrapper<AnswerMemberEntity> ew=new EntityWrapper();
                    ew.eq("member_id", memberId);
                    ew.eq("answer_classify_id", answerClassifyId);
                    AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(ew);
                    if (null != answerMemberEntity){
                        return R.ok();
                    }
                }
            }
            return R.error(499, "此卡不支持被拆分");
        }
    }

    /**
     *获取合并升级当前卡需要的卡
     * @author wangkui
     * @date 2020/1/14 15:13
     * @param[answerClassifyId, memberId]
     * @return com.freeter.common.utils.R
     */
    @Override
    public R checkUpgrade(int answerClassifyId, int memberId) {
        AnswerClassifyEntity classifyEntity= answerClassifyService.selectById(answerClassifyId);
        if (null != classifyEntity){
            int mergeId= classifyEntity.getMergeId();
            Map<String,Object> map =new HashMap<>();
            map.put("memberId", memberId);
            map.put("mergeId", mergeId);
            AnswerMemberVO answerMember=baseMapper.checkUpgrade(map);
            map.clear();
            return R.ok().put("answerMemberInfo", answerMember);
        }
        return R.error("未找到卡分类对象");
    }
    /**
     *合并升级当前卡
     * @author wangkui
     * @date 2020/1/14 16:07
     * @param answerClassifyId, memberId]
     * @return com.freeter.common.utils.R
     */
    @Override
    @Transactional
    public R upgrade(int answerClassifyId, int memberId) {
        AnswerClassifyEntity classifyEntity= answerClassifyService.selectById(answerClassifyId);
        if (null != classifyEntity){
            int mergeId= classifyEntity.getMergeId();
            EntityWrapper<AnswerMemberEntity> ew=new EntityWrapper<>();
            ew.eq("member_id", memberId);
            ew.eq("answer_classify_id",mergeId);
            AnswerMemberEntity answerMember=answerMemberService.selectOne(ew);
            if (null == answerMember || answerMember.getTotal().intValue()<2){
                return R.error(499,"未找到要合并的卡或数量不够");
            }else {
                int number=answerMember.getTotal().intValue();
              if (number>2){
                  answerMember.setTotal(number-2);
                  answerMember.setUpdateTime((int)DateUtil.getUnixStamp());
                  answerMemberService.updateById(answerMember);
              }if (number==2){
                  answerMemberService.deleteById(answerMember.getAnswerMemberId());
                }
                //新增升级一张卡
                EntityWrapper<AnswerMemberEntity> entityWrapper=new EntityWrapper<>();
                entityWrapper.eq("member_id", memberId);
                entityWrapper.eq("answer_classify_id",answerClassifyId);
                AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(entityWrapper);
                if (null !=answerMemberEntity){
                    answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()+1);
                    answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                    baseMapper.updateById(answerMemberEntity);
                }else {
                    answerMemberEntity=new AnswerMemberEntity();
                    answerMemberEntity.setTotal(1);
                    answerMemberEntity.setAnswerClassifyId(answerClassifyId);
                    answerMemberEntity.setMemberId(memberId);
                    answerMemberEntity.setCreateTime((int)DateUtil.getUnixStamp());
                    answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                    baseMapper.insert(answerMemberEntity);
                }
                return R.ok("合并升级成功");
            }

        }
        return R.error("未找到卡分类对象");
    }
     /**
      *1、助力成功后，三人各得10张答题卡（通过后台查询）2、修改平台钱包和钱包记录
      * @author wangkui
      * @date 2020/1/16 15:48
      * @param[memberId]
      * @return void
      */
    @Override
    @Transactional
    public void insertByMemberId(int memberId) {
        List<PackageCardVO> list =packageCardService.getPackageMoney();//获取助力库对应卡的金额
        BigDecimal money=new BigDecimal(0);
        //1、三人各得10张答题卡（通过后台查询）
        for (PackageCardVO cardEntity:list){
            money=money.add(cardEntity.getAwardMoney().multiply(new BigDecimal(cardEntity.getNum())));
            EntityWrapper<AnswerMemberEntity> memberEntityEntityWrapper=new EntityWrapper<>();
            memberEntityEntityWrapper.eq("member_id", memberId);
            memberEntityEntityWrapper.eq("answer_classify_id",cardEntity.getAnswerClassifyId());
            AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(memberEntityEntityWrapper);
            if (null == answerMemberEntity){
                answerMemberEntity=new AnswerMemberEntity();
                answerMemberEntity.setMemberId(memberId);
                answerMemberEntity.setCreateTime((int)DateUtil.getUnixStamp());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                answerMemberEntity.setTotal(cardEntity.getNum());
                answerMemberEntity.setAnswerClassifyId(cardEntity.getAnswerClassifyId());
                baseMapper.insert(answerMemberEntity);
            }else {
                answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()+cardEntity.getNum());
                answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                baseMapper.updateAllColumnById(answerMemberEntity);
            }
        }
        //2、修改平台钱包和钱包记录
        MoneySetEntity moneySetEntity=moneySetService.selectById(3);
        //助力库减少记录
        MoneySetRecordEntity recordEntity=new MoneySetRecordEntity();
        recordEntity.setCreateTime((int)DateUtil.getUnixStamp());
        recordEntity.setAddCut(1);//减|1|,加|0|
        recordEntity.setMoney(money);
        recordEntity.setMemberId(memberId);
        recordEntity.setMoneySetId(3);
        recordEntity.setAfter(moneySetEntity.getMoney().subtract(money).setScale(2,BigDecimal.ROUND_DOWN));
        recordEntity.setOld(moneySetEntity.getMoney());
        recordEntity.setTitle(moneySetEntity.getTitle());
        recordEntity.setSourceId(3);
        recordEntity.setSourceType(3);
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
        moneySetRecordEntity.setSourceId(3);
        moneySetRecordEntity.setSourceType(3);
        moneySetRecordService.insert(moneySetRecordEntity);
        setEntity.setMoney(setEntity.getMoney().add(money).setScale(2,BigDecimal.ROUND_DOWN));
        moneySetService.updateAllColumnById(setEntity);
        PackageEntity packageEntity= packageService.selectById(3);
        if (null != packageEntity){
            packageEntity.setSales(packageEntity.getSales().intValue()+1);
            packageService.updateAllColumnById(packageEntity);
        }
    }
}
