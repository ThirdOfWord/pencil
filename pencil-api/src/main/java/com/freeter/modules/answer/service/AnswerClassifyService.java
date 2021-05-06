package com.freeter.modules.answer.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.query.AnswerClassifyQuery;

import java.util.List;

/**
 * 答题分类
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerClassifyService extends IService<AnswerClassifyEntity> {
 List<AnswerClassifyQuery> getList();

}

