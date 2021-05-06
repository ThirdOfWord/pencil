package com.freeter.modules.answer.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.freeter.modules.answer.entity.AnswerQuestionEntity;

/**
 * 题库问题
 * 
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerQuestionDao extends BaseMapper<AnswerQuestionEntity> {
 AnswerQuestionEntity selectInfo(String checkCate);//随机抽题
 AnswerQuestionEntity getInfo();//随机抽题
}
