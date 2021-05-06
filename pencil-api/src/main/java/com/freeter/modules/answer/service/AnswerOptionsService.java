package com.freeter.modules.answer.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.answer.entity.AnswerOptionsEntity;

/**
 * 题库问题选项
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerOptionsService extends IService<AnswerOptionsEntity> {
 String updatePkById(String memberId,String twoMemberId,int anClassId);//参数 1：答对用户，2：答错用户，3：答题卡类别
 void deleteByKa(String memberId,int anClassId);//参数 1退出用户，2：答题卡类别
 void addByKa(String memberId,int anClassId);//参数 1当前用户，2：答题卡类别
}

