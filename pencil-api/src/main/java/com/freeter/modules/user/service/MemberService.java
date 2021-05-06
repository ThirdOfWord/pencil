package com.freeter.modules.user.service;


import com.baomidou.mybatisplus.service.IService;
import com.freeter.common.utils.R;
import com.freeter.modules.user.entity.MemberEntity;
import com.taobao.api.response.TbkScPublisherInfoSaveResponse;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

/**
 * 用户表
 *
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
 @SuppressWarnings({"unchecked","rawtypes"})
public interface MemberService extends IService<MemberEntity> {

  R updateHeadimgurl(int memberId, MultipartFile file);
  R updateInfo(int status,String message,int memberId);
  TbkScPublisherInfoSaveResponse getRelationId(String accessToken) throws Exception;
}

