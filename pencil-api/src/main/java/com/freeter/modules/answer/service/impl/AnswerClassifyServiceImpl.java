package com.freeter.modules.answer.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.modules.answer.dao.AnswerClassifyDao;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.query.AnswerClassifyQuery;
import com.freeter.modules.answer.service.AnswerClassifyService;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("answerClassifyService")
public class AnswerClassifyServiceImpl extends ServiceImpl<AnswerClassifyDao, AnswerClassifyEntity> implements AnswerClassifyService {
    @Override
    public List<AnswerClassifyQuery> getList() {
        List<AnswerClassifyQuery> list=baseMapper.getList();
        return list;
    }
}
