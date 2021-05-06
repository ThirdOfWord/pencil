package com.freeter.modules.answer.controller;


import com.freeter.modules.answer.service.AnswerOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




/**
 * 题库问题选项
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
@RestController
@RequestMapping("api/answeroptions")
@SuppressWarnings({"unchecked","rawtypes"})
public class AnswerOptionsController {
    @Autowired
    private AnswerOptionsService answerOptionsService;


}
