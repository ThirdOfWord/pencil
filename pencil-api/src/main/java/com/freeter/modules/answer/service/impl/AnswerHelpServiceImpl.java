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
    * @Description:  查看当前用户是否可以答题
    * @Author: Mr.Wang 
    * @Date: 2020/5/2 
    */
    @Override
    public synchronized R isStart(int memberId) {
        EntityWrapper<AnswerStartendEntity> ew=new EntityWrapper();
        ew.eq("status", 1);
        //ew.eq("answer_startend_id", 1);
        AnswerStartendEntity answerStartendEntity=answerStartendService.selectOne(ew);
        //1、判断是否开启
        if (null == answerStartendEntity){
            return R.error(499, "当前未开启答题");
        }
        //2、判断起始时间
        long date=DateUtil.getUnixStamp();//当前时间
        int startTime=answerStartendEntity.getTimeStart();//开始时间
        int endTime=answerStartendEntity.getEndTime();//结束时间
        if (date<startTime || date>endTime){
            return R.error(499, "当前时间未开启答题");
        }
        return R.ok();
    }

    /**
     *发起助力或查看助力情况
     * @author wangkui
     * @date 2020/1/15 16:17
     * @param  memberId
     * @return java.util.Map<java.lang.String,java.lang.Object>
     */
    @Override
    public synchronized R add(int memberId) {
        //判断是否开启助力
        PackageEntity packageEntity=packageService.selectById(3);
        if (null == packageEntity || packageEntity.getStatus() == 0 || packageEntity.getNum() == 0){
            return R.error(499, "当前助力未开启");
        }

        Map<String,Object> map=new HashMap<>();
        List<MemberModel> list=new ArrayList();
        long startTime=0;
        list=selectSet(memberId,list);//检查集合是否有值
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
        //1、根据用户ID查看集合、无集合可能是帮助别人助力，根据用户ID找到对应的value、根据value查看集合、无集合根据当前用户ID创建集合
         //无集合可能是帮助别人助力
        String user=jedis.get("HUser:"+memberId);
         if (StringUtils.isNotEmpty(user)){
             int oneMember=Integer.parseInt(user);
             list=selectSet(oneMember,list);//检查集合是否有值
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
         //null 创建集合
        //4、判断当前用户助力频率（有限制多少分钟内不能助力）
        String ZLStart=jedis.get("ZLStart:"+memberId);//限制发起助力的频率
        if (StringUtils.isNotEmpty(ZLStart)){ //有限制
           return R.error(499, "当前助力太频繁，请稍后再试");
        }
        AnswerStartendEntity answerStartendEntity=answerStartendService.selectById(1);
        if (null == answerStartendEntity){
            return R.error(499, "获取不到助力频率时间");
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
        if (null != team){ //集合不为空
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
     *  帮助好友助力
     * @author wangkui
     * @date 2020/1/16 14:08
     * @param[firstMemberId, memberId]
     * @return com.freeter.common.utils.R
     */
    @Override
    public synchronized R doInsert(int firstMemberId, int memberId) {
        //判断是否开启助力
        PackageEntity packageEntity=packageService.selectById(3);
        if (null == packageEntity || packageEntity.getStatus() == 0 || packageEntity.getNum() == 0){
            return R.error(499, "当前助力未开启");
        }
        //判断助力次数的限制一天
        String Boost=jedis.get("Boost:"+memberId);
        if (StringUtils.isNotEmpty(Boost)) {
            int oneMember = Integer.parseInt(Boost);
            if (oneMember >= packageEntity.getNum().intValue()){
                return R.error(499, "今天助力次数已到上线");
            }else {
                int num = oneMember+1;
                String BoostTime=jedis.get("BoostTime:"+memberId);
                int time = Integer.parseInt(BoostTime);//第一次存入redis的时间
                int nowTime = (int)DateUtil.getUnixStamp();//现在时间
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
            return R.error(499, "链接已失效");
        }if (teamNumber>=3){ //助力完成
            list=doSet(firstMemberId,list);
            map.put("helpInfo", list);
            map.put("startTime",0);
            map.put("firstMemberId",firstMemberId);
            return R.ok(map);
        }
        //去助力
        long number=jedis.sadd("HTeam:"+firstMemberId,""+memberId);
        if (number==0){
            return R.error(499, "已助力");
        }
        teamNumber=jedis.scard("HTeam:"+firstMemberId);
        if (teamNumber>=3){ //助力完成
            Set<String> team=jedis.smembers("HTeam:"+firstMemberId);
            if (!team.isEmpty()){ //集合不为空
                //判断需要分配答题卡的金额是否小于助力库的金额
                int isPackage=moneySetService.checkZhuLiMoney();
                map.put("isPackage",isPackage);
                for (String s: team){
                    int teamMember=Integer.parseInt(s);
                    //助力成功后，三人各得10张答题卡、消息通知
                    if (isPackage==1){
                        answerMemberService.insertByMemberId(teamMember);
                    }
                    MemberEntity memberEntity =memberService.selectById(teamMember);
                    if (Objects.nonNull(memberEntity)){
                        if (teamMember==firstMemberId){
                            messageService.insertByMemberId(teamMember,1);//消息通知
                            MemberModel memberModel=new MemberModel(memberEntity.getNickname(), memberEntity.getHeadimgurl(), 1);
                            list.add(memberModel);
                        }else {
                            messageService.insertByMemberId(teamMember,0);//消息通知
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
        if (!team.isEmpty()){ //集合不为空
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
