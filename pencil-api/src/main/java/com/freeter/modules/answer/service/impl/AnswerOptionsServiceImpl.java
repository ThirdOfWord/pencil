package com.freeter.modules.answer.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.answer.dao.AnswerOptionsDao;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.entity.AnswerOptionsEntity;
import com.freeter.modules.answer.service.AnswerClassifyService;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.answer.service.AnswerOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("answerOptionsService")
public class AnswerOptionsServiceImpl extends ServiceImpl<AnswerOptionsDao, AnswerOptionsEntity> implements AnswerOptionsService {
    @Autowired
    private AnswerClassifyService answerClassifyService;
    @Autowired
    private AnswerMemberService answerMemberService;

    /**
     * 当前用户卡升级加一
     * @param memberId
     * @param anClassId
     */
    @Override
    @Transactional
    public void addByKa(String memberId, int anClassId) {
        EntityWrapper<AnswerClassifyEntity> ew=new EntityWrapper<>();
        ew.eq("merge_id", anClassId);
        AnswerClassifyEntity classifyEntity =answerClassifyService.selectOne(ew);
        int classifyId=classifyEntity.getAnswerClassifyId();//当前答题卡的上级
        //答对用户当前卡减一
        EntityWrapper<AnswerMemberEntity> ewTwo=new EntityWrapper<>();
        ewTwo.eq("member_id", memberId);
        ewTwo.eq("answer_classify_id", anClassId);
        AnswerMemberEntity answerMemberEntityTwo=answerMemberService.selectOne(ewTwo);
        if (null != answerMemberEntityTwo){
            if (answerMemberEntityTwo.getTotal().intValue()==1){
                answerMemberService.deleteById(answerMemberEntityTwo.getAnswerMemberId());
            }else {
                if (answerMemberEntityTwo.getTotal().intValue()>1){
                    answerMemberEntityTwo.setTotal(answerMemberEntityTwo.getTotal().intValue()-1);
                    answerMemberEntityTwo.setUpdateTime((int)DateUtil.getUnixStamp());
                    answerMemberService.updateAllColumnById(answerMemberEntityTwo);
                }
            }
        }
        //答对用户升级卡
        EntityWrapper<AnswerMemberEntity> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("member_id", memberId);
        entityWrapper.eq("answer_classify_id", classifyId);
        AnswerMemberEntity memberEntity=answerMemberService.selectOne(entityWrapper);
        if (null==memberEntity){
            memberEntity=new AnswerMemberEntity();
            memberEntity.setTotal(1);
            memberEntity.setCreateTime((int)DateUtil.getUnixStamp());
            memberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            memberEntity.setAnswerClassifyId(classifyId);
            memberEntity.setMemberId(Integer.parseInt(memberId));
            answerMemberService.insert(memberEntity);
        }else {
            memberEntity.setTotal(memberEntity.getTotal().intValue()+1);
            memberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            answerMemberService.updateAllColumnById(memberEntity);
        }
    }

    /**
     * 当前用户退出卡扣除
     * @param memberId
     * @param anClassId
     */
    @Override
    @Transactional
    public void deleteByKa(String memberId, int anClassId) {
        EntityWrapper<AnswerMemberEntity> wrapper=new EntityWrapper<>();
        wrapper.eq("member_id", memberId);
        wrapper.eq("answer_classify_id", anClassId);
        AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(wrapper);
        if (null != answerMemberEntity){
            if (answerMemberEntity.getTotal().intValue()==1){
                answerMemberService.deleteById(answerMemberEntity.getAnswerMemberId());
            }else {
                if (answerMemberEntity.getTotal().intValue()>1){
                    answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()-1);
                    answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                    answerMemberService.updateAllColumnById(answerMemberEntity);
                }
            }
        }
    }

    /**
     *
     * @author wangkui
     * @date 2020/1/21 14:40
     * @param  memberId, twoMemberId, anClassId 参数 1：答对用户，2：答错用户，3：答题卡类别
     * @return java.lang.String
     */
    @Override
    @Transactional
    public String updatePkById(String memberId, String twoMemberId, int anClassId) {
        EntityWrapper<AnswerClassifyEntity> ew=new EntityWrapper<>();
        ew.eq("merge_id", anClassId);
        AnswerClassifyEntity classifyEntity =answerClassifyService.selectOne(ew);
        int classifyId=classifyEntity.getAnswerClassifyId();//当前答题卡的上级
        String title=classifyEntity.getTitle();
        //答对用户升级卡
        EntityWrapper<AnswerMemberEntity> entityWrapper=new EntityWrapper<>();
        entityWrapper.eq("member_id", memberId);
        entityWrapper.eq("answer_classify_id", classifyId);
        AnswerMemberEntity memberEntity=answerMemberService.selectOne(entityWrapper);
        if (null==memberEntity){
            memberEntity=new AnswerMemberEntity();
            memberEntity.setTotal(1);
            memberEntity.setCreateTime((int)DateUtil.getUnixStamp());
            memberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            memberEntity.setAnswerClassifyId(classifyId);
            memberEntity.setMemberId(Integer.parseInt(memberId));
            answerMemberService.insert(memberEntity);
        }else {
            memberEntity.setTotal(memberEntity.getTotal().intValue()+1);
            memberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
            answerMemberService.updateAllColumnById(memberEntity);
        }
        //答对用户当前卡减一
        EntityWrapper<AnswerMemberEntity> ewTwo=new EntityWrapper<>();
        ewTwo.eq("member_id", memberId);
        ewTwo.eq("answer_classify_id", anClassId);
        AnswerMemberEntity answerMemberEntityTwo=answerMemberService.selectOne(ewTwo);
        if (null != answerMemberEntityTwo){
            if (answerMemberEntityTwo.getTotal().intValue()==1){
                answerMemberService.deleteById(answerMemberEntityTwo.getAnswerMemberId());
            }else {
                if (answerMemberEntityTwo.getTotal().intValue()>1){
                    answerMemberEntityTwo.setTotal(answerMemberEntityTwo.getTotal().intValue()-1);
                    answerMemberEntityTwo.setUpdateTime((int)DateUtil.getUnixStamp());
                    answerMemberService.updateAllColumnById(answerMemberEntityTwo);
                }
            }
        }
        //答错用户
        EntityWrapper<AnswerMemberEntity> wrapper=new EntityWrapper<>();
        wrapper.eq("member_id", twoMemberId);
        wrapper.eq("answer_classify_id", anClassId);
        AnswerMemberEntity answerMemberEntity=answerMemberService.selectOne(wrapper);
        if (null != answerMemberEntity){
            if (answerMemberEntity.getTotal().intValue()==1){
                answerMemberService.deleteById(answerMemberEntity.getAnswerMemberId());
            }else {
                if (answerMemberEntity.getTotal().intValue()>1){
                    answerMemberEntity.setTotal(answerMemberEntity.getTotal().intValue()-1);
                    answerMemberEntity.setUpdateTime((int)DateUtil.getUnixStamp());
                    answerMemberService.updateAllColumnById(answerMemberEntity);
                }
            }
        }
        return title;
    }
}
