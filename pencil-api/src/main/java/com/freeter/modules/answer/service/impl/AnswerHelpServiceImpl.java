package com.freeter.modules.answer.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.JedisUtils;
import com.freeter.common.utils.R;
import com.freeter.modules.advert.service.MessageService;
import com.freeter.modules.answer.entity.AnswerStartendEntity;
import com.freeter.modules.answer.service.AnswerHelpService;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.answer.service.AnswerStartendService;
import com.freeter.modules.money.entity.MoneySetEntity;
import com.freeter.modules.money.service.MoneySetService;
import com.freeter.modules.packageCard.entity.PackageEntity;
import com.freeter.modules.packageCard.service.PackageService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.model.MemberModel;
import com.freeter.modules.user.service.MemberService;
import lombok.Synchronized;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.endpoint.UpfrontConduitSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Auther: hao
 * @Date: 2020/1/15 15:33
 * @Description:
 */
@SuppressWarnings({"unchecked","rawtypes"})
@Service("answerHelpService")
@Transactional
public class AnswerHelpServiceImpl implements AnswerHelpService{
    @Autowired
    private MemberService memberService;
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AnswerStartendService answerStartendService;
    @Autowired
    private MoneySetService moneySetService;
    @Autowired
    private PackageService packageService;

    private final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
   // private final JedisUtils jedis=new JedisUtils("127.0.0.1" ,6379,"" );
    /**
    * @Description: ??????????????????????????????????????
    * @Author: Mr.Wang??
    * @Date: 2020/5/2??
    */
    @Override
    public synchronized R isStart(int memberId) {
        EntityWrapper<AnswerStartendEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        //ew.eq("answer_startend_id", 1);
        AnswerStartendEntity answerStartendEntity=answerStartendService.selectOne(ew);
        //1?????????????????????
        if (null == answerStartendEntity){
            return R.error(499, "?????????????????????");
        }
        //2?????????????????????
        long date=DateUtil.getUnixStamp();//????????????
        int startTime=answerStartendEntity.getTimeStart();//????????????
        int endTime=answerStartendEntity.getEndTime();//????????????
        if (date<startTime || date>endTime){
            return R.error(499, "???????????????????????????");
        }
        return R.ok();
    }

    /**
     *?????????????????????????????????
     * @author wangkui
     * @date 2020/1/15 16:17
     * @param  memberId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @Override
    public synchronized R add(int memberId) {
        //????????????????????????
        PackageEntity packageEntity=packageService.selectById(3);
        if (null == packageEntity || packageEntity.getStatus() == 0 || packageEntity.getNum() == 0){
            return R.error(499, "?????????????????????");
        }

        Map<String,Object> map=new HashMap<>();
        List<MemberModel> list=new ArrayList();
        long startTime=0;
        list=selectSet(memberId,list);//????????????????????????
        if (!list.isEmpty()){
            String time=jedis.get("HStart:"+memberId);
            if (StringUtils.isNotEmpty(time)){
                startTime=Integer.parseInt(time);
            }
            map.put("helpInfo", list);
            map.put("startTime",startTime);
            map.put("firstMemberId",memberId);
            return R.ok(map);
        }
        //1???????????????ID??????????????????????????????????????????????????????????????????ID???????????????value?????????value??????????????????????????????????????????ID????????????
         //????????????????????????????????????
        String user=jedis.get("HUser:"+memberId);
         if (StringUtils.isNotEmpty(user)){
             int oneMember=Integer.parseInt(user);
             list=selectSet(oneMember,list);//????????????????????????
             if (!list.isEmpty()){
                 String time=jedis.get("HStart:"+oneMember);
                 if (StringUtils.isNotEmpty(time)){
                     startTime=Integer.parseInt(time);
                 }
                 map.put("helpInfo", list);
                 map.put("firstMemberId",oneMember);
                 map.put("startTime",startTime);
                 return R.ok(map);
             }
         }
         //null ????????????
        //4???????????????????????????????????????????????????????????????????????????
        String ZLStart=jedis.get("ZLStart:"+memberId);//???????????????????????????
        if (StringUtils.isNotEmpty(ZLStart)){ //?????????
           return R.error(499, "???????????????????????????????????????");
        }
        AnswerStartendEntity answerStartendEntity=answerStartendService.selectById(1);
        if (null == answerStartendEntity){
            return R.error(499, "??????????????????????????????");
        }
        jedis.setex("ZLStart:"+memberId,"1", 60*answerStartendEntity.getGap().intValue());

        jedis.sadd("HTeam:"+memberId,""+memberId);
        jedis.Expire("HTeam:"+memberId,60*60);
        startTime=DateUtil.getUnixStamp();
        jedis.setex("HStart:"+memberId,""+startTime, 60*60+1);
        MemberEntity memberEntity =memberService.selectById(memberId);
        if (Objects.nonNull(memberEntity)){
            list.add(new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 1));
        }
        map.put("helpInfo", list);
        map.put("startTime",startTime);
        map.put("firstMemberId",memberId);
        return R.ok(map);
    }
    private  List<MemberModel> selectSet(int memberId,List<MemberModel> list){
        Set<String> team=jedis.smembers("HTeam:"+memberId);
        if (null != team){ //???????????????
            for (String s: team){
                int teamMember=Integer.parseInt(s);
                MemberEntity memberEntity =memberService.selectById(teamMember);
                if (Objects.nonNull(memberEntity)){
                    String nickname = memberEntity.getNickname();
                    if (StringUtils.isEmpty(nickname)){
                        String mobile = memberEntity.getMobile();
                        nickname=mobile.substring(0, 3)+"*****"+mobile.substring(7, 11);
                    }
                    if (teamMember==memberId){
                        MemberModel memberModel=new MemberModel(nickname, memberEntity.getHeadimgurl(), 1);
                        list.add(memberModel);
                    }else {
                        MemberModel memberModel=new MemberModel(nickname, memberEntity.getHeadimgurl(), 0);
                        list.add(memberModel);
                    }
                }
            }
        }
        return list;
    }
    /**
     *  ??????????????????
     * @author wangkui
     * @date 2020/1/16 14:08
     * @param[firstMemberId, memberId]
     * @return com.freeter.common.utils.R
     */
    @Override
    public synchronized R doInsert(int firstMemberId, int memberId) {
        //????????????????????????
        PackageEntity packageEntity=packageService.selectById(3);
        if (null == packageEntity || packageEntity.getStatus() == 0 || packageEntity.getNum() == 0){
            return R.error(499, "?????????????????????");
        }
        //?????????????????????????????????
        String Boost=jedis.get("Boost:"+memberId);
        if (StringUtils.isNotEmpty(Boost)) {
            int oneMember = Integer.parseInt(Boost);
            if (oneMember >= packageEntity.getNum().intValue()){
                return R.error(499, "??????????????????????????????");
            }else {
                int num = oneMember+1;
                String BoostTime=jedis.get("BoostTime:"+memberId);
                int time = Integer.parseInt(BoostTime);//???????????????redis?????????
                int nowTime = (int)DateUtil.getUnixStamp();//????????????
                int second = 60*60*24 -(nowTime-time);
                if (second < 0){
                    second = 1;
                }
                jedis.setex("Boost:"+memberId,String.valueOf(num), second);
            }
        }else {
            long time = DateUtil.getUnixStamp();
            jedis.setex("BoostTime:"+memberId,String.valueOf(time), 60*60*24);
            jedis.setex("Boost:"+memberId,"1", 60*60*24);
        }
        Map<String,Object> map=new HashMap<>();
        List<MemberModel> list=new ArrayList();
        long startTime=0;
        long teamNumber=jedis.scard("HTeam:"+firstMemberId);
        if (teamNumber==0){
            return R.error(499, "???????????????");
        }if (teamNumber>=3){ //????????????
            list=doSet(firstMemberId,list);
            map.put("helpInfo", list);
            map.put("startTime",0);
            map.put("firstMemberId",firstMemberId);
            return R.ok(map);
        }
        //?????????
        long number=jedis.sadd("HTeam:"+firstMemberId,""+memberId);
        if (number==0){
            return R.error(499, "?????????");
        }
        teamNumber=jedis.scard("HTeam:"+firstMemberId);
        if (teamNumber>=3){ //????????????
            Set<String> team=jedis.smembers("HTeam:"+firstMemberId);
            if (!team.isEmpty()){ //???????????????
                //??????????????????????????????????????????????????????????????????
                int isPackage=moneySetService.checkZhuLiMoney();
                map.put("isPackage",isPackage);
                for (String s: team){
                    int teamMember=Integer.parseInt(s);
                    //??????????????????????????????10???????????????????????????
                    if (isPackage==1){
                        answerMemberService.insertByMemberId(teamMember);
                    }
                    MemberEntity memberEntity =memberService.selectById(teamMember);
                    if (Objects.nonNull(memberEntity)){
                        if (teamMember==firstMemberId){
                            messageService.insertByMemberId(teamMember,1);//????????????
                            MemberModel memberModel=new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 1);
                            list.add(memberModel);
                        }else {
                            messageService.insertByMemberId(teamMember,0);//????????????
                            MemberModel memberModel=new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 0);
                            list.add(memberModel);
                        }
                    }
                }
            }
            jedis.del("HStart:"+firstMemberId);
            map.put("helpInfo", list);
            map.put("startTime",0);
            map.put("firstMemberId",firstMemberId);
            return R.ok(map);
        }else {
            jedis.setex("HUser:"+memberId,""+firstMemberId,60*60);
            list=doSet(firstMemberId,list);
            String time=jedis.get("HStart:"+firstMemberId);
            if (StringUtils.isNotEmpty(time)){
                startTime=Integer.parseInt(time);
            }
            map.put("helpInfo", list);
            map.put("startTime",startTime);
            map.put("firstMemberId",firstMemberId);
            return R.ok(map);
        }
    }
    private List<MemberModel> doSet(int firstMemberId,List<MemberModel> list){
        Set<String> team=jedis.smembers("HTeam:"+firstMemberId);
        if (!team.isEmpty()){ //???????????????
            for (String s: team){
                int teamMember=Integer.parseInt(s);
                MemberEntity memberEntity =memberService.selectById(teamMember);
                if (Objects.nonNull(memberEntity)){
                    if (teamMember==firstMemberId){
                        MemberModel memberModel=new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 1);
                        list.add(memberModel);
                    }else {
                        MemberModel memberModel=new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 0);
                        list.add(memberModel);
                    }
                }
            }
        }
        return list;
    }

}
