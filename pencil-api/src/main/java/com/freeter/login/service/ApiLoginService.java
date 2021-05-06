package com.freeter.login.service;


import com.freeter.common.utils.R;
import com.freeter.login.form.WeixinMemberForm;

/**
 * 答题分类
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 13:31:38
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface ApiLoginService{
R weChatLogin(WeixinMemberForm form);
R bangding(String phone,String code,String openid);
}

