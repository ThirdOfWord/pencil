package com.freeter.modules.answer.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.service.AnswerClassifyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 答题分类
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
@RestController
@RequestMapping("api/answerclassify")
@SuppressWarnings({"unchecked","rawtypes"})
public class AnswerClassifyController {
    @Autowired
    private AnswerClassifyService answerClassifyService;
    /* *
     *答题卡分类
     * @author wangkui
     * @date 2020/1/10 13:49
     * @param []
     * @return com.freeter.common.utils.R
     */
    @CrossOrigin
    @RequestMapping("list")
    public R getList(){
        EntityWrapper<AnswerClassifyEntity> classifyWrapper=new EntityWrapper<>();
        classifyWrapper.eq("status", 1);
        classifyWrapper.orderBy("sortsid");
        List<AnswerClassifyEntity> list=answerClassifyService.selectList(classifyWrapper);
        return R.ok().put("answerclassify", list);
    }
}
