package com.freeter.modules.answer.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.entity.AnswerOptionsEntity;
import com.freeter.modules.answer.entity.AnswerQuestionEntity;
import com.freeter.modules.answer.service.AnswerOptionsService;
import com.freeter.modules.answer.service.AnswerQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 题库问题
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
@RestController
@RequestMapping("api/answerquestion")
@SuppressWarnings({"unchecked","rawtypes"})
public class AnswerQuestionController {
    @Autowired
    private AnswerQuestionService answerQuestionService;
    @Autowired
    private AnswerOptionsService answerOptionsService;
    /* *
     *随机抽题
     * @author wangkui
     * @date 2020/1/10 14:10
     * @param [answerClassifyId]
     * @return com.freeter.common.utils.R
     */
    @CrossOrigin
    @RequestMapping("info")
    public R getInfo(@RequestParam(value = "answerClassifyId",defaultValue = "0")Integer answerClassifyId){
        Map<String,Object> map=new HashMap<>();
        AnswerQuestionEntity answerQuestionEntity=answerQuestionService.getInfo();//随机抽题
        if (null != answerQuestionEntity){
            map.put("answerQuestionInfo", answerQuestionEntity);
            EntityWrapper<AnswerOptionsEntity> optionsWrapper=new EntityWrapper<>();
            optionsWrapper.eq("answer_question_id", answerQuestionEntity.getAnswerQuestionId());
            List<AnswerOptionsEntity> list=answerOptionsService.selectList(optionsWrapper);
            map.put("answerOptionsList", list);
        }
        return R.ok(map);
    }
    @RequestMapping("aaaa")
    public R aaaa(){
        JSONArray jsonArray=new JSONArray();
       for (int i=0;i<200;i++){
           Map<String,Object> map=new HashMap<>();
           AnswerQuestionEntity answerQuestionEntity=answerQuestionService.aaa();
           EntityWrapper<AnswerOptionsEntity> optionsWrapper=new EntityWrapper<>();
           optionsWrapper.eq("answer_question_id", answerQuestionEntity.getAnswerQuestionId());
           List<AnswerOptionsEntity> list=answerOptionsService.selectList(optionsWrapper);
           map.put("title",answerQuestionEntity.getTitle());
           map.put("list",list);
           jsonArray.add(map);
       }
        return R.ok().put("list", jsonArray);
    }
}
