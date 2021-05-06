package com.freeter.modules.answer.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.answer.entity.AnswerQuestionEntity;
import io.swagger.models.auth.In;

/**
 * 题库问题
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerQuestionService extends IService<AnswerQuestionEntity> {

 AnswerQuestionEntity selectInfo(String room);//随机抽题
 AnswerQuestionEntity getInfo();//随机抽题
 AnswerQuestionEntity aaa();//随机抽题
}

