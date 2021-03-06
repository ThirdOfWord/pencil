package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.entity.query.AnswerClassifyQuery;
import com.freeter.modules.answer.service.AnswerClassifyService;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.good.dao.ExchangeGoodsDao;
import com.freeter.modules.good.entity.ExchangeAttrEntity;
import com.freeter.modules.good.entity.ExchangeGoodsAttrsEntity;
import com.freeter.modules.good.entity.ExchangeGoodsEntity;
import com.freeter.modules.good.entity.vo.ExchangeAttrValueVO;
import com.freeter.modules.good.entity.vo.ExchangeGoodsVO;
import com.freeter.modules.good.service.ExchangeAttrService;
import com.freeter.modules.good.service.ExchangeAttrValueService;
import com.freeter.modules.good.service.ExchangeGoodsAttrsService;
import com.freeter.modules.good.service.ExchangeGoodsService;
import com.freeter.modules.money.entity.ExchangeBonusEntity;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.entity.MoneySetRecordEntity;
import com.freeter.modules.money.service.ExchangeBonusService;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.entity.ExchangeOrderGoodsEntity;
import com.freeter.modules.order.service.ExchangeOrderGoodsService;
import com.freeter.modules.order.service.ExchangeOrderService;
import com.freeter.modules.user.entity.MemberAddressEntity;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberAddressService;
import com.freeter.modules.user.service.MemberService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("exchangeGoodsService")
public class ExchangeGoodsServiceImpl extends ServiceImpl<ExchangeGoodsDao, ExchangeGoodsEntity> implements ExchangeGoodsService {
    @Autowired
    private AnswerClassifyService answerClassifyService;
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private ExchangeAttrService exchangeAttrService;
    @Autowired
    private ExchangeAttrValueService exchangeAttrValueService;
    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private ExchangeOrderGoodsService exchangeOrderGoodsService;
    @Autowired
    private MemberAddressService memberAddressService;
    @Autowired
    private ExchangeGoodsAttrsService exchangeGoodsAttrsService;
    @Autowired
    private ExchangeBonusService exchangeBonusService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MoneySetService moneySetService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;
    /**
     * ????????????????????????
     * @param memberId
     * @return
     */
    @Override
    public List getList(int memberId) {
        //1????????????????????????2??????????????????????????????????????????3???????????????????????????????????????
        List<AnswerClassifyQuery> list=answerClassifyService.getList();
        if (list.isEmpty()){
            return null;
        }

        for (AnswerClassifyQuery answerClassifyQuery:list){
            int answerClassifyId=answerClassifyQuery.getAnswerClassifyId();
            //2????????????????????????????????????
           EntityWrapper<AnswerMemberEntity> ew=new EntityWrapper<>();
           ew.eq("member_id", memberId);
           ew.eq("answer_classify_id",answerClassifyId);
            AnswerMemberEntity answerMemberEntity= answerMemberService.selectOne(ew);
            if (null == answerMemberEntity){
                answerClassifyQuery.setIsDuiHuanStatus(0);
            }else {
                answerClassifyQuery.setIsDuiHuanStatus(1);
            }
            //3???????????????????????????????????????
            List<ExchangeGoodsVO> goodsVOList=baseMapper.getByanswerClassifyIdList(answerClassifyId);
            if (goodsVOList.isEmpty()){
                answerClassifyQuery.setAnswerClassifyId(-1);
            }else {
                answerClassifyQuery.setGoodsList(goodsVOList);
            }
        }
        list.removeIf(an -> an.getAnswerClassifyId()== -1);
        return list;
    }
    /**
     * ???????????????????????????
     * @param memberId
     * @return
     */
    @Override
    public Map getOneInfo(int memberId,int exchangeGoodsId) {
        Map<String,Object> map=new HashMap<>();
        ExchangeGoodsEntity goodsEntity=baseMapper.selectById(exchangeGoodsId);
        if (null == goodsEntity){
            return map;
        }
        map.put("exchangeGoodsId", goodsEntity.getExchangeGoodsId());
        map.put("title", goodsEntity.getTitle());
        map.put("thumb", goodsEntity.getThumb());
        map.put("stock", goodsEntity.getStock());
        map.put("isAttrStatus", goodsEntity.getIsAttrStatus());
        map.put("attrIdStr", goodsEntity.getAttrIdStr());
        map.put("answerClassifyId",goodsEntity.getAnswerClassifyId());
        if (goodsEntity.getIsAttrStatus()==1){//???????????????
            List list=new ArrayList();
            String attrIdStr=goodsEntity.getAttrIdStr();
            if (StringUtils.isNotEmpty(attrIdStr)){
                String[] attrId=attrIdStr.split("-");
                if (attrId != null && attrId.length != 0) {
                    for (int i = 0; i < attrId.length; i++) {
                        Map<String,Object> mapAttr=new HashMap<>();
                        System.out.println("======"+attrId[i]);
                        ExchangeAttrEntity attrEntity=exchangeAttrService.selectById(attrId[i]);
                        if (null !=attrEntity){
                            mapAttr.put("title", attrEntity.getTitle());
                        }else {
                            mapAttr.put("title", null);
                        }
                        int attr = Integer.parseInt(attrId[i]);//????????????
                        List<ExchangeAttrValueVO> attrValueList=exchangeAttrValueService.getListByAttrId(attr);//??????????????????
                        mapAttr.put("attrValueList", attrValueList);
                        list.add(mapAttr);
                    }
                }
            }
            map.put("attr", list);
        }
        return map;
    }

    /**
     * ????????????  -?????????????????????????????????  ??????????????????????????????????????????
     * @param exchangeGoodsId ??????id
     * @param memberAddressId  ??????id
     * @param isAttrStatus
     * @param attrValueId
     * @return
     */
    @Override
    @Transactional
    public R insertOrder(int memberId,int exchangeGoodsId, int memberAddressId, int isAttrStatus, String attrValueId) {
            ExchangeGoodsEntity goodsEntity=baseMapper.selectById(exchangeGoodsId);
            if (goodsEntity.getStock().intValue()>0){//????????????????????????
                int answerClassifyId=goodsEntity.getAnswerClassifyId();//?????????????????????
                AnswerClassifyEntity answerClassifyEntity=answerClassifyService.selectById(answerClassifyId);//???????????????
                EntityWrapper<AnswerMemberEntity> answerMemberWrapper=new EntityWrapper();
                answerMemberWrapper.eq("member_id", memberId);
                answerMemberWrapper.eq("answer_classify_id", answerClassifyId);
                AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(answerMemberWrapper);
                if (null == answerMemberEntity || answerMemberEntity.getTotal()<=0){
                    return R.error(499, "???????????????");
                }
                MemberAddressEntity addressEntity=memberAddressService.selectById(memberAddressId);
                if (null == addressEntity){
                    return R.error(499, "??????????????????");
                }
                String thumb="";
                String title="";
                if (isAttrStatus==1){//?????????
                    EntityWrapper<ExchangeGoodsAttrsEntity> ew=new EntityWrapper();
                    ew.eq("exchange_goods_id", exchangeGoodsId);
                    ew.eq("exchange_attr_value_id_str", attrValueId);
                    ExchangeGoodsAttrsEntity goodsAttrsEntity=exchangeGoodsAttrsService.selectOne(ew);
                    if (null == goodsAttrsEntity || goodsAttrsEntity.getStock()<=0){
                        return R.error(499, "?????????????????????");
                    }
                    goodsAttrsEntity.setStock(goodsAttrsEntity.getStock().intValue()-1);
                    exchangeGoodsAttrsService.updateById(goodsAttrsEntity);
                    thumb=goodsAttrsEntity.getThumb();
                    title=goodsAttrsEntity.getTitle();
                }else {
                    thumb=goodsEntity.getThumb();
                    title=goodsEntity.getTitle();
                }
                //???????????????????????????
                ExchangeOrderEntity orderEntity=new ExchangeOrderEntity();
                orderEntity.setMemberAddressId(memberAddressId);
                orderEntity.setProvince(addressEntity.getProvince());
                orderEntity.setCity(addressEntity.getCity());
                orderEntity.setDistrict(addressEntity.getDistrict());
                orderEntity.setAddress(addressEntity.getContent());
                orderEntity.setMobile(addressEntity.getMobile());
                orderEntity.setNickname(addressEntity.getName());
                orderEntity.setIsShipping(0);
                orderEntity.setMemberId(memberId);
                orderEntity.setAnswerClassifyId(answerClassifyId);
                orderEntity.setAnswerClassifyTitle(answerClassifyEntity.getTitle());
                orderEntity.setStatus(2);
                orderEntity.setCreateTime((int)DateUtil.getUnixStamp());
                orderEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                exchangeOrderService.insert(orderEntity);

                ExchangeOrderGoodsEntity orderGoodsEntity=new ExchangeOrderGoodsEntity();
                orderGoodsEntity.setExchangeGoodsId(exchangeGoodsId);
                orderGoodsEntity.setCreateTime((int)DateUtil.getUnixStamp());
                orderGoodsEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                orderGoodsEntity.setExchangeOrderId(orderEntity.getExchangeOrderId());
                orderGoodsEntity.setAttrTitle(attrValueId);
                orderGoodsEntity.setNum(1);
                orderGoodsEntity.setThumb(thumb);
                orderGoodsEntity.setTitle(title);
                exchangeOrderGoodsService.insert(orderGoodsEntity);

                goodsEntity.setSales(goodsEntity.getSales().intValue()+1);
                goodsEntity.setStock(goodsEntity.getStock().intValue()-1);
                baseMapper.updateById(goodsEntity);
                if (answerMemberEntity.getTotal()==1){
                    answerMemberService.deleteById(answerMemberEntity.getAnswerMemberId());
                }else {
                    answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()-1);
                    answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                    answerMemberService.updateById(answerMemberEntity);
                }
                BigDecimal kaMoney=answerClassifyEntity.getAwardMoney();//???????????????
                MemberEntity memberEntity =memberService.selectById(memberId);
                //????????????????????? ????????????
                MoneySetEntity moneySetEntity=moneySetService.selectById(2);
                //?????????????????????
                MoneySetRecordEntity recordEntity=new MoneySetRecordEntity();
                recordEntity.setCreateTime((int)DateUtil.getUnixStamp());
                recordEntity.setAddCut(1);//???|1|,???|0|
                recordEntity.setMoney(kaMoney);
                recordEntity.setMemberId(memberId);
                recordEntity.setMoneySetId(2);
                recordEntity.setAfter(moneySetEntity.getMoney().subtract(kaMoney).setScale(2,BigDecimal.ROUND_DOWN));
                recordEntity.setOld(moneySetEntity.getMoney());
                recordEntity.setTitle(moneySetEntity.getTitle());
                recordEntity.setSourceType(1);
                recordEntity.setSourceId(orderEntity.getExchangeOrderId());
                moneySetRecordService.insert(recordEntity);
                moneySetEntity.setMoney(moneySetEntity.getMoney().subtract(kaMoney).setScale(2,BigDecimal.ROUND_DOWN));
                moneySetService.updateAllColumnById(moneySetEntity);
                //???????????????
                BigDecimal money =answerClassifyEntity.getBonusMoney();
                int num=answerClassifyEntity.getBonusNum();
                String code=getRandomString(8);
                ExchangeBonusEntity bonusEntity=new ExchangeBonusEntity();
                bonusEntity.setAnswerClassifyId(answerClassifyId);
                bonusEntity.setCoding(code);
                bonusEntity.setCreateTime((int)DateUtil.getUnixStamp());
                bonusEntity.setExchangeOrderId(orderEntity.getExchangeOrderId());
                bonusEntity.setMemberId(memberId);
                bonusEntity.setMobile(memberEntity.getMobile());
                bonusEntity.setNickname(memberEntity.getNickname());
                bonusEntity.setMoneyTotal(money.multiply(new BigDecimal(num)));
                bonusEntity.setNum(num);
                bonusEntity.setPrice(money);
                bonusEntity.setSales(0);
                bonusEntity.setShareDes("???????????????"+code);
                bonusEntity.setShareThumb("/uploads/api/exchange_bonus.jpg");
                bonusEntity.setShareTitle(memberEntity.getNickname()+"??????????????????");
                bonusEntity.setShareNum(num);
                bonusEntity.setStatus(0);
                bonusEntity.setCardTitle(answerClassifyEntity.getTitle());
                bonusEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                exchangeBonusService.insert(bonusEntity);
                return R.ok().put("bonusInfo", bonusEntity);
            }
            return R.error(499, "????????????");
    }
    //length????????????????????????????????????
    public static String getRandomString(int length){
        String str="abcdefghijkmnpqrstuvwxyzABCDEFGHIJKLMNPQRSTUVWXYZ23456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(57);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
