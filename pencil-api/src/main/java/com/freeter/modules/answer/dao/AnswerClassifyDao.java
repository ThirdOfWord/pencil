package com.freeter.modules.answer.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface AnswerClassifyDao extends BaseMapper<AnswerClassifyEntity> {
 List<AnswerClassifyQuery> getList();
}
