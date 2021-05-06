package com.freeter.modules.user.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.GameImageUtils;
import com.freeter.common.utils.R;
import com.freeter.modules.user.dao.MemberDao;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.WeixinMemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WeixinMemberService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkScPublisherInfoSaveRequest;
import com.taobao.api.response.TbkScPublisherInfoSaveResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {
    @Autowired
    private WeixinMemberService weixinMemberService;

    private final static String url="http://gw.api.taobao.com/router/rest";
    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    /**
     * 修改头像
     * @param memberId
     * @param file
     * @return
     */
    @Override
    public R updateHeadimgurl(int memberId, MultipartFile file) {
        String uploadLocation = "qianbitou/public/uploads/api/headimg";
        String dbUrl="/uploads/api/headimg/";//存入数据库的路径
        String tuPian=null;
        try {
            tuPian= GameImageUtils.GameImage(file, uploadLocation,dbUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MemberEntity memberEntity= baseMapper.selectById(memberId);
        memberEntity.setHeadimgurl(tuPian);
        memberEntity.setUpdateTime(DateUtil.getUnixStamp());
        baseMapper.updateById(memberEntity);
        EntityWrapper<WeixinMemberEntity> ew=new EntityWrapper();
        ew.eq("member_id", memberId);
        WeixinMemberEntity weixinMemberEntity=weixinMemberService.selectOne(ew);
        if (null !=weixinMemberEntity){
            weixinMemberEntity.setHeadimgurl(tuPian);
            weixinMemberEntity.setUpdateTime(DateUtil.getUnixStamp());
            weixinMemberService.updateById(weixinMemberEntity);
        }
        return R.ok();
    }

    /**
     * 修改用户信息   status  1:昵称、2:性别、3:支付宝账号、4:手机号
     * @param status
     * @param message
     * @return
     */
    @Override
    public R updateInfo(int status, String message,int memberId) {
        MemberEntity memberEntity=baseMapper.selectById(memberId);
        if (null == memberEntity){
            return R.error();
        }
        WeixinMemberEntity weixinMemberEntity=null;
        if (status==1 || status==2){
            EntityWrapper<WeixinMemberEntity> ew=new EntityWrapper();
            ew.eq("member_id", memberId);
            weixinMemberEntity=weixinMemberService.selectOne(ew);
        }
        switch (status){
            case 1: memberEntity.setNickname(message);
                    weixinMemberEntity.setNickname(message);
                    weixinMemberService.updateById(weixinMemberEntity);
                  break;
            case 2:
                if (StringUtils.equals(message, "男")){
                    memberEntity.setSix(1);
                    weixinMemberEntity.setSex(1);
                    weixinMemberService.updateById(weixinMemberEntity);
                }if (StringUtils.equals(message, "女")){
                    memberEntity.setSix(2);
                    weixinMemberEntity.setSex(2);
                    weixinMemberService.updateById(weixinMemberEntity);
                }
                break;
            case 3:memberEntity.setAccountAlipay(message);
                break;
            case 4:memberEntity.setMobile(message);
                break;
        }
        baseMapper.updateById(memberEntity);
        return R.ok();
    }
    //获取渠道id
    @Override
    @Transactional
    public TbkScPublisherInfoSaveResponse getRelationId(String accessToken) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkScPublisherInfoSaveRequest req = new TbkScPublisherInfoSaveRequest();
        req.setRelationFrom("1");
        req.setOfflineScene("1");
        req.setOnlineScene("1");
        req.setInviterCode("NLH34W");
        req.setInfoType(1L);
        TbkScPublisherInfoSaveResponse rsp = client.execute(req, accessToken);
        return rsp;
    }
}
