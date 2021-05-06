package com.freeter.modules.answer.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
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
public interface AnswerMemberDao extends BaseMapper<AnswerMemberEntity> {
 List<AnswerMemberVO> getList(int memberId);
 AnswerMemberVO checkUpgrade(Map<String,Object>  map);//检查升级需要的卡
 List<AnswerMemberVO> getDuihuan(int memberId);//显示可兑换的卡包

}
