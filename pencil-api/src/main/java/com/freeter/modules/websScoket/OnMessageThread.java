package com.freeter.modules.websScoket;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.JedisUtils;
import com.freeter.modules.answer.entity.AnswerOptionsEntity;
import com.freeter.modules.answer.entity.AnswerQuestionEntity;
import com.freeter.modules.answer.service.AnswerOptionsService;
import com.freeter.modules.answer.service.AnswerQuestionService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.model.MemberModel;
import com.freeter.modules.user.service.MemberService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
/**
 * @Auther: hao
 * @Date: 2020/1/19 17:28
 * @Description:
 */
public class OnMessageThread implements Runnable{

    private MemberService memberService;
    private AnswerQuestionService answerQuestionService;
    private AnswerOptionsService answerOptionsService;

    static Log log= LogFactory.get(WebSocketServer.class);
    private ConcurrentHashMap<String,WebSocketServer> webSocketMap;
    private String message;
    private String memberId;
    private final JedisUtils jedis;

    public OnMessageThread(ConcurrentHashMap<String, WebSocketServer> webSocketMap, String message, String memberId,JedisUtils jedis) {
        this.webSocketMap = webSocketMap;
        this.message = message;
        this.memberId = memberId;
        this.jedis=jedis;
        this.memberService= BeanContext.getApplicationContext().getBean(MemberService.class);
        this.answerQuestionService= BeanContext.getApplicationContext().getBean(AnswerQuestionService.class);
        this.answerOptionsService= BeanContext.getApplicationContext().getBean(AnswerOptionsService.class);
    }

    @Override
    public void run() {
                //解析发送的报文
                JSONObject jsonObject = JSON.parseObject(message);
                //追加发送人(防止串改)
                //jsonObject.put("fromMemberId",this.memberId);
                //String toMemberId=jsonObject.getString("memberId");
                int anClassId=jsonObject.getInteger("anClassId");//答题卡编号
                int status=jsonObject.getInteger("status");//状态 0:开始匹配,1:停止匹配，2：开始挑战，3：提交答案
                //判断状态  0:开始匹配,1:停止匹配，2：开始挑战，3：提交答案
                if (status == 0){
                     Long number=jedis.llen("anClassId:"+anClassId);
                     if (number == 0){//说明队列无值返回前端等待
                         jsonObject.clear();
                         jsonObject.put("status", 0);//等待中
                         try {
                             webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                         } catch (Exception e) {
                             if (webSocketMap.containsKey(memberId)) {
                                 webSocketMap.remove(memberId);
                                 log.error("开始匹配后:当前用户："+memberId+"断线");
                             }
                         }
                         jedis.rpush("anClassId:"+anClassId, memberId);
                     }else {
                         String randomMemberId =jedis.lpop("anClassId:"+anClassId);//移除并返回列表 key 的尾元素
                         if (StringUtils.isNotEmpty(randomMemberId) && !StringUtils.equals(memberId,randomMemberId)){//说明队列中不为null值
                             if (webSocketMap.containsKey(randomMemberId)){
                                 String uuid=UUID.randomUUID().toString();
                                 log.info("开始匹配中:当前用户："+memberId+",对方用户："+randomMemberId+",报文:"+message+",room:"+uuid);
                                 jedis.sadd("A"+uuid,memberId,randomMemberId);
                                 jedis.Expire("A"+uuid,60*40);
                                 int oneMember=Integer.parseInt(memberId);//当前用户
                                 int twoMember=Integer.parseInt(randomMemberId);//对方用户
                                 MemberEntity oneMemberEntity=memberService.selectById(oneMember);
                                 MemberEntity twoMemberEntity=memberService.selectById(twoMember);
                                 if (null != oneMemberEntity && null != twoMemberEntity) {
                                     jsonObject.clear();
                                     Map<String, String> map = new HashMap<>();
                                     String nickname = oneMemberEntity.getNickname();
                                     if (StringUtils.isEmpty(nickname)) {
                                         String mobile = oneMemberEntity.getMobile();
                                         nickname = mobile.substring(0, 3) + "*****" + mobile.substring(8, 11);
                                     }
                                     String headimgurl = oneMemberEntity.getHeadimgurl();
                                     if (StringUtils.isEmpty(headimgurl)) {
                                         headimgurl = "null";
                                     } else {
                                         String url = headimgurl.substring(0, 1);
                                         if (StringUtils.equals(url, "/")) {
                                             headimgurl = "http://xxqbt.xxqbt.com" + headimgurl;
                                         }
                                     }
                                     jsonObject.put("nickname", nickname);
                                     jsonObject.put("headimgurl", headimgurl);
                                     jsonObject.put("status", 1);
                                     jsonObject.put("room",uuid);
                                     map.put("nickname", nickname);
                                     map.put("headimgurl", headimgurl);
                                     jedis.hmset("pInfo:" + memberId, map);
                                     jedis.Expire("pInfo:" + memberId,60*60);
                                     try {
                                         webSocketMap.get(randomMemberId).sendMessage(jsonObject.toJSONString());
                                     } catch (Exception e) {
                                         if (webSocketMap.containsKey(randomMemberId)) {
                                             webSocketMap.remove(randomMemberId);
                                             jedis.del("A"+uuid);
                                             log.error("开始匹配后:对方用户："+randomMemberId+"断线");
                                         }
                                     }
                                     jsonObject.clear();
                                     String twoNickname = twoMemberEntity.getNickname();
                                     String twoHeadimgurl = twoMemberEntity.getHeadimgurl();
                                     if (StringUtils.isEmpty(twoNickname)) {
                                         String twoMoblie = twoMemberEntity.getMobile();
                                         twoNickname = twoMoblie.substring(0, 3) + "*****" + twoMoblie.substring(8, 11);
                                     }
                                     if (StringUtils.isEmpty(twoHeadimgurl)) {
                                         twoHeadimgurl = "null";
                                     } else {
                                         String url = twoHeadimgurl.substring(0, 1);
                                         if (StringUtils.equals(url, "/")) {
                                             twoHeadimgurl = "http://xxqbt.xxqbt.com" + twoHeadimgurl;
                                         }
                                     }
                                     jsonObject.put("nickname", twoNickname);
                                     jsonObject.put("headimgurl", twoHeadimgurl);
                                     jsonObject.put("status", 1);
                                     jsonObject.put("room",uuid);
                                     map.clear();
                                     map.put("nickname", twoNickname);
                                     map.put("headimgurl", twoHeadimgurl);
                                     jedis.hmset("pInfo:" + randomMemberId, map);
                                     jedis.Expire("pInfo:" + randomMemberId,60*60);
                                     try {
                                         webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                                     } catch (Exception e) {
                                         if (webSocketMap.containsKey(memberId)) {
                                             webSocketMap.remove(memberId);
                                             jedis.del("A"+uuid);
                                             log.error("开始匹配后:当前用户："+memberId+"断线");
                                         }
                                     }
                                 }
                             }else {
                                 //当前用户存入队列
                                 jsonObject.clear();
                                 jsonObject.put("status", 0);//等待中
                                 jedis.lpush("anClassId:"+anClassId, memberId);
                                 try {
                                     webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                                     } catch (Exception e) {
                                         if (webSocketMap.containsKey(memberId)) {
                                             webSocketMap.remove(memberId);
                                             jedis.lrem("anClassId:"+anClassId, 0, memberId);//删除队列中自己的id
                                             log.error("开始匹配中:当前用户已离线："+memberId+"没找到对方,报文:"+message);
                                         }
                                     }
                                 }
                         }else {
                             log.info("开始匹配前:当前用户："+memberId+",未查到对方用户于是保存到队列中："+",报文:"+message);
                             //当前用户存入队列，
                             jedis.lpush("anClassId:"+anClassId, memberId);
                             jsonObject.clear();
                             jsonObject.put("status", 0);//等待中
                             try {
                                 webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                             } catch (Exception e) {
                                 if (webSocketMap.containsKey(memberId)) {
                                     webSocketMap.remove(memberId);
                                     jedis.lrem("anClassId:"+anClassId, 0, memberId);//删除队列中自己的id
                                     log.error("开始匹配前:当前用户已离线："+memberId+",报文:"+message);
                                 }
                             }
                         }
                     }
                }
                if (status == 1){ //停止匹配
                    log.info("停止匹配:当前用户："+memberId+",报文:"+message);
                    jedis.lrem("anClassId:"+anClassId, 0, memberId);//删除队列中自己的id
                    jsonObject.clear();
                    jsonObject.put("status", 2);//2:停止匹配
                    try {
                        webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                    }catch (Exception e){
                        if(webSocketMap.containsKey(memberId)){
                            webSocketMap.remove(memberId);
                            log.error("停止匹配memberId断网:"+memberId+"已结束");
                        }
                    }
                }
                if (status == 2){//开始挑战
                    String room=jsonObject.getString("room");//房间号
                    /*
                     * redis锁 失效
                     */
                    Long res=jedis.setnx("S2"+room, room);
                    jedis.Expire("S2"+room,2);
                    if (res == 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String duiShou=jedis.get("B"+room);
                    if (StringUtils.isEmpty(duiShou)) {//说明第一用户刚进来
                        jedis.setex("B"+room,memberId,12);//30S后自动删除
                        jsonObject.clear();
                        jsonObject.put("status", 0);//等待中
                        try {
                            webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                        } catch (Exception e) {
                            if (webSocketMap.containsKey(memberId)) {
                                webSocketMap.remove(memberId);
                                jedis.del("B"+room);
                                log.error("开始挑战:当前用户已离线："+memberId+",报文:"+message);
                            }
                        }
                        jedis.del("S2"+room);
                    }else {//说明第二个用户进来
                        duiShou=jedis.get("B"+room);
                        AnswerQuestionEntity answerQuestionEntity=answerQuestionService.selectInfo(room);//随机抽题
                        log.info("开始挑战中返回题:当前用户："+memberId+",对方用户："+duiShou+",报文:"+message+"答题名称："+answerQuestionEntity.getTitle());
                        if (null !=answerQuestionEntity){
                            EntityWrapper<AnswerOptionsEntity> optionsWrapper=new EntityWrapper<>();
                            optionsWrapper.eq("answer_question_id", answerQuestionEntity.getAnswerQuestionId());
                            List<AnswerOptionsEntity> list=answerOptionsService.selectList(optionsWrapper);
                            String nickname=jedis.hget("pInfo:"+duiShou,"nickname");
                            String headimgurl=jedis.hget("pInfo:"+duiShou,"headimgurl");
                            jsonObject.clear();
                            jsonObject.put("title", answerQuestionEntity.getTitle());
                            jsonObject.put("answerOptionsList", list);
                            jsonObject.put("nickname",nickname);
                            jsonObject.put("headimgurl",headimgurl);
                            jsonObject.put("room",room);
                            jsonObject.put("status", 3);//显示题
                            log.info("测试题目为null:当前用户："+memberId+",对方用户："+duiShou+",报文:"+message+"答题名称："+answerQuestionEntity.getTitle());
                            try {
                                webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());//当前用户
                            }catch (Exception e){
                                if (webSocketMap.containsKey(memberId)) {
                                    webSocketMap.remove(memberId);
                                    log.error("开始挑战中有题的情况下:当前用户已离线："+memberId+",报文:"+message);
                                }
                            }
                            if (webSocketMap.containsKey(duiShou)){
                                jsonObject.put("nickname",jedis.hget("pInfo:"+memberId,"nickname"));
                                jsonObject.put("headimgurl",jedis.hget("pInfo:"+memberId,"headimgurl"));
                                try {
                                    webSocketMap.get(duiShou).sendMessage(jsonObject.toJSONString());//对应的用户
                                }catch (Exception e){
                                    if (webSocketMap.containsKey(duiShou)) {
                                        webSocketMap.remove(duiShou);
                                        log.error("开始挑战中有题的情况下:对方用户已离线："+duiShou+",报文:"+message);
                                    }
                                }
                            }
                            jedis.del("B"+room);
                        }
                        jedis.del("S2"+room);
                    }

                }
                if (status == 3){//3：提交答案
                    String room=jsonObject.getString("room");//房间号
                    /*
                     * redis锁 失效
                     */
                    Long res=jedis.setnx("S3"+room, room);
                    jedis.Expire("S3"+room,2);
                    if (res == 0){
                        try {
                            Thread.sleep(1100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    String anOptionId=jsonObject.getString("anOptionId");//答案编号
                    String duiShou=jedis.get("C"+room);
                    if (StringUtils.isEmpty(duiShou)) {//说明第一用户刚进来
                        jedis.setex("daAn:"+memberId,anOptionId,12);//30S后自动删除
                        jedis.setex("C"+room,memberId,12);//30S后自动删除
                        jsonObject.clear();
                        jsonObject.put("status", 0);//等待中
                        try {
                            webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());
                        } catch (Exception e) {
                            if (webSocketMap.containsKey(memberId)) {
                                webSocketMap.remove(memberId);
                                log.error("开始挑战:当前用户已离线："+memberId+",报文:"+message);
                            }
                        }
                    }else {//说明第二个用户进来了
                        duiShou=jedis.get("C"+room);//对方用户编号
                        String enAnOptionId=jedis.get("daAn:"+duiShou);//对方答案编号
                        if (StringUtils.equals(anOptionId, enAnOptionId)){ //答案相等
                            AnswerQuestionEntity answerQuestionEntity=answerQuestionService.selectInfo(room);//随机抽题
                            log.info("提交答案:答案相等返回题:当前用户："+memberId+",对方用户："+duiShou+",报文:"+message+"答题名称："+answerQuestionEntity.getTitle());
                            if (null !=answerQuestionEntity){
                                EntityWrapper<AnswerOptionsEntity> optionsWrapper=new EntityWrapper<>();
                                optionsWrapper.eq("answer_question_id", answerQuestionEntity.getAnswerQuestionId());
                                List<AnswerOptionsEntity> list=answerOptionsService.selectList(optionsWrapper);
                                jsonObject.clear();
                                jsonObject.put("title", answerQuestionEntity.getTitle());
                                jsonObject.put("answerOptionsList", list);
                                jsonObject.put("status", 3);//显示题
                                jsonObject.put("room",room);
                                try {
                                    webSocketMap.get(duiShou).sendMessage(jsonObject.toJSONString());//对应的用户
                                } catch (Exception e) {//对应的用户离线
                                    if (webSocketMap.containsKey(duiShou)) {
                                        webSocketMap.remove(duiShou);
                                        log.error("提交答案:答案相等:对应的用户"+duiShou+"离线,当前用户"+memberId+"已结束");
                                    }
                                }
                                try {
                                    log.info("提交答案:答案相等:当前用户"+memberId+"正常显示答题内容,已结束");
                                    webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());//当前用户
                                } catch (Exception e) {//当前用户离线
                                    if (webSocketMap.containsKey(memberId)) {
                                        webSocketMap.remove(memberId);
                                        log.error("提交答案:答案相等:当前用户"+memberId+"离线,已结束");
                                    }
                                }
                            }
                            jedis.del("C"+room);
                        }else {//两用户答案不一致
                            AnswerOptionsEntity optionsEntity=answerOptionsService.selectById(anOptionId);//根据当前用户查询答案是否正确
                            if (null != optionsEntity){
                                if (optionsEntity.getYes() == 1){//当前用户答对 自己当前答题卡升一级，对方失去当前答题卡
                                    log.info("提交答案:答案不相等:当前用户："+memberId+"答对，答题卡升一级,对方用户："+duiShou+"对方失去当前答题卡,报文:"+message);
                                    String title=answerOptionsService.updatePkById(memberId,duiShou,anClassId);//参数 1：答对用户，2：答错用户，3：答题卡类别
                                    jsonObject.clear();
                                    jsonObject.put("status", 5);//显示输赢
                                    jsonObject.put("result", 0);//结果 0：输，1：赢
                                    try {
                                        webSocketMap.get(duiShou).sendMessage(jsonObject.toJSONString());//对应的用户
                                    } catch (Exception e) {//对应的用户离线
                                        if (webSocketMap.containsKey(duiShou)) {
                                            webSocketMap.remove(duiShou);
                                        }
                                    }
                                    jsonObject.clear();
                                    jsonObject.put("status", 5);//显示输赢
                                    jsonObject.put("result", 1);//结果 0：输，1：赢
                                    jsonObject.put("title", title);
                                    try {
                                        webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());//当前用户
                                    } catch (Exception e) {//当前用户离线
                                        if (webSocketMap.containsKey(memberId)) {
                                            webSocketMap.remove(memberId);
                                        }
                                    }
                                    jedis.del("C"+room);
                                }else {//对方用户答对  自己当前答题卡失去，对方当前答题卡升一级
                                    log.info("提交答案:答案不相等:当前用户："+memberId+"答错,对方用户："+duiShou+"答对对方答题卡升级,报文:"+message);
                                    String title=answerOptionsService.updatePkById(duiShou,memberId,anClassId);//参数 1：答对用户，2：答错用户，3：答题卡类别
                                    jsonObject.clear();
                                    jsonObject.put("status", 5);//显示输赢
                                    jsonObject.put("result", 0);//结果 0：输，1：赢
                                    try {
                                        webSocketMap.get(memberId).sendMessage(jsonObject.toJSONString());//当前用户
                                    } catch (Exception e) {//当前用户离线
                                        if (webSocketMap.containsKey(memberId)) {
                                            webSocketMap.remove(memberId);
                                        }
                                    }
                                    jsonObject.clear();
                                    jsonObject.put("result", 1);//结果 0：输，1：赢
                                    jsonObject.put("title", title);
                                    jsonObject.put("status", 5);//显示输赢
                                    try {
                                        webSocketMap.get(duiShou).sendMessage(jsonObject.toJSONString());//对应的用户
                                    } catch (Exception e) {//对应的用户离线
                                        if (webSocketMap.containsKey(duiShou)) {
                                            webSocketMap.remove(duiShou);
                                        }
                                    }
                                    jedis.del("C"+room);
                                }
                            }
                        }
                    }
                    jedis.del("S3"+room);
                }
                if (status == 4){//当前用户退出答题
                    String room=jsonObject.getString("room");//房间号
                    /*
                     * redis锁 失效
                     */
                    Long res=jedis.setnx("S4"+room, room);
                    jedis.Expire("S4"+room,2);
                    if (res == 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Set<String> set=jedis.smembers("A"+room);
                    if (!set.isEmpty()){
                        for (String duiShou : set) {
                            if (StringUtils.isNotEmpty(duiShou) && !StringUtils.equals(memberId, duiShou)){
                                try {
                                    jedis.del("B"+room,"C"+room,"A"+room);
                                    jsonObject.clear();
                                    jsonObject.put("status", -1);
                                    webSocketMap.get(duiShou).sendMessage(jsonObject.toJSONString());
                                } catch (Exception e) {
                                    if (webSocketMap.containsKey(duiShou)) {
                                        webSocketMap.remove(duiShou);
                                        log.error("用户退出答题:对方用户已离线："+duiShou+",报文:"+message);
                                    }
                                }
                            }
                        }
                    }
                    //当前用户卡扣除
                    answerOptionsService.deleteByKa(memberId,anClassId);//参数 1退出用户，2：答题卡类别
                    webSocketMap.remove(memberId);
                    jedis.del("S4"+room,"A"+room);
                }
                if (status == 5){//对方用户退出答题
                    String room=jsonObject.getString("room");//房间号
                    /*
                     * redis锁 失效
                     */
                    Long res=jedis.setnx("S5"+room, room);
                    jedis.Expire("S5"+room,2);
                    if (res == 0){
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Set<String> set=jedis.smembers("A"+room);
                    if(!set.isEmpty()){
                        for (String duiShou : set) {
                            if (StringUtils.isNotEmpty(duiShou) && !StringUtils.equals(memberId, duiShou)){
                                /*
                                  对方用户卡扣除
                                  参数 1退出用户，2：答题卡类别
                                 */
                                answerOptionsService.deleteByKa(duiShou,anClassId);
                            }
                        }
                        jedis.del("A"+room);
                    }
                    /*
                       当前用户卡升级加一
                       参数 1当前用户，2：答题卡类别
                     */
                    answerOptionsService.addByKa(memberId,anClassId);
                    jedis.del("S5"+room);
                }
                log.error("请求的userId:"+memberId+"已结束");
    }
}
