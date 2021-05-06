package com.freeter.modules.answer.service;

import com.freeter.common.utils.R;


/**
 * @Auther: hao
 * @Date: 2020/1/15 15:32
 * @Description:
 */
@SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerHelpService {
    R add(int memberId);
    R isStart(int memberId);//查看当前用户是否可以发起助力或参加助力
    R doInsert(int firstMemberId,int memberId);
}
