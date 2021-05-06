package com.freeter.modules.answer.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.answer.entity.AnswerMemberEntity;
import com.freeter.modules.answer.entity.vo.AnswerMemberVO;

import java.util.List;
import java.util.Map;

/**
 * 用户拥有的答题卡数
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 15:14:57
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface AnswerMemberService extends IService<AnswerMemberEntity> {
 List<AnswerMemberVO> getList(int memberId);
 R check(int answerClassifyId,int memberId,int status);//查看当前卡是否可以合并或拆分
 R checkUpgrade(int answerClassifyId, int memberId);//升级当前卡需要哪些卡
 R upgrade(int answerClassifyId, int memberId);//合并升级当前卡
 void insertByMemberId(int memberId);//助力成功后，三人各得10张答题卡
 List<AnswerMemberVO> getDuihuan(int memberId);//显示可兑换的卡包
}

