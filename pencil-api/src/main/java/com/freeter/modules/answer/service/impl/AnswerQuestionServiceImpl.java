package com.freeter.modules.answer.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.JedisUtils;
import com.freeter.modules.answer.dao.AnswerQuestionDao;
import com.freeter.modules.answer.entity.AnswerQuestionEntity;
import com.freeter.modules.answer.service.AnswerQuestionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("answerQuestionService")
public class AnswerQuestionServiceImpl extends ServiceImpl<AnswerQuestionDao, AnswerQuestionEntity> implements AnswerQuestionService {
    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
    @Override
    public AnswerQuestionEntity selectInfo(String room) {
        String checkCate=null;
        String cate=jedis.get("cate:"+room);
        if (StringUtils.isNotEmpty(cate)) {//不为null
            checkCate = "(" + cate + ")";
            AnswerQuestionEntity answerQuestionEntity = baseMapper.selectInfo(checkCate);//随机抽题
            if (null == answerQuestionEntity) {
                AnswerQuestionEntity answerQuestion = baseMapper.getInfo();//随机抽题
                String aaa = answerQuestion.getAnswerCateId().toString();
                jedis.set("cate:" + room, aaa);
                jedis.Expire("cate:" + room,60*30);
                return answerQuestion;
            } else {
                cate = cate + "," + answerQuestionEntity.getAnswerCateId().toString();
                jedis.set("cate:" + room, cate);
                jedis.Expire("cate:" + room,60*30);
                return answerQuestionEntity;
            }
        }else {
            AnswerQuestionEntity answerQuestionEntity=baseMapper.getInfo();//随机抽题
            String aaa=answerQuestionEntity.getAnswerCateId().toString();
            jedis.set("cate:"+room, aaa);
            jedis.Expire("cate:" + room,60*30);
            return answerQuestionEntity;
        }
    }

    @Override
    public AnswerQuestionEntity getInfo() {
        AnswerQuestionEntity answerQuestion=baseMapper.getInfo();//随机抽题
        return answerQuestion;
    }

    @Override
    public AnswerQuestionEntity aaa() {
        String room="123456asdsadsafasff";
        String checkCate=null;
        String cate=jedis.get("cate:"+room);
        if (StringUtils.isNotEmpty(cate)) {//不为null
            checkCate = "(" + cate + ")";
            AnswerQuestionEntity answerQuestionEntity = baseMapper.selectInfo(checkCate);//随机抽题
            if (null == answerQuestionEntity) {
                AnswerQuestionEntity answerQuestion = baseMapper.getInfo();//随机抽题
                String aaa = answerQuestion.getAnswerCateId().toString();
                jedis.set("cate:" + room, aaa);
                jedis.Expire("cate:" + room,60*30);
                return answerQuestion;
            } else {
                cate = cate + "," + answerQuestionEntity.getAnswerCateId().toString();
                jedis.set("cate:" + room, cate);
                jedis.Expire("cate:" + room,60*30);
                return answerQuestionEntity;
            }
        }else {
            AnswerQuestionEntity answerQuestionEntity=baseMapper.getInfo();//随机抽题
            String aaa=answerQuestionEntity.getAnswerCateId().toString();
            jedis.set("cate:"+room, aaa);
            jedis.Expire("cate:" + room,60*30);
            return answerQuestionEntity;
        }
    }
}
