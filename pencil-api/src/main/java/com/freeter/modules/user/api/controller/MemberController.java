package com.freeter.modules.user.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.GameImageUtils;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.WeixinMemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WeixinMemberService;
import com.freeter.token.service.TokenService;
import com.taobao.api.response.TbkScPublisherInfoSaveResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户表
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-07 16:14:11
 */
@RestController
@RequestMapping("api/member")
@SuppressWarnings({"unchecked","rawtypes"})
public class MemberController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private WeixinMemberService weixinMemberService;

    /**
    * @Description: 判断用户是否已授权淘宝 
    * @Author: Mr.Wang 
    * @Date: 2020/8/24 
    */
    @RequestMapping("check")
    public R check(HttpServletRequest request){
        synchronized (this){
            int memberId=token(request);
            MemberEntity memberEntity = memberService.selectById(memberId);
            if (null != memberEntity){
                if (StringUtils.isNotEmpty(memberEntity.getRelationId())){
                    long deviceTime = memberEntity.getDeviceTime();//淘宝授权的时间
                    long time = DateUtil.getUnixStamp();
                    deviceTime = deviceTime + 60*60*24*30;
                    if (deviceTime < time){
                        return R.error(1, "先授权淘宝");
                    }
                    return R.ok();
                }
            }else {
                return R.error("系统异常");
            }
            return R.error(1, "先授权淘宝");
        }
    }
    /**
    * @Description: 淘宝授权登录获取信息
    * @Author: Mr.Wang 
    * @Date: 2020/8/23 
    */
    @RequestMapping("warrant")
    public R warrant(@RequestParam(value = "openId",required = false) String openId,
                     @RequestParam(value = "accessToken",required = false) String accessToken,
                     @RequestParam(value = "deviceType",required = false) String deviceType,
                     @RequestParam(value = "deviceValue",required = false) String deviceValue,
                     HttpServletRequest request){
        synchronized (this){
            int memberId=token(request);
            if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(accessToken)){
                return R.error(499, "参数不能为空");
            }
            MemberEntity memberEntity = memberService.selectById(memberId);
            String relationId = memberEntity.getRelationId();
            if (StringUtils.isEmpty(relationId)){
                //获取渠道id
                try {
                    TbkScPublisherInfoSaveResponse req = memberService.getRelationId(accessToken);
                    relationId = String.valueOf(req.getData().getRelationId());
                    //判断当前淘宝账户是不是已授权，如果已授权的话把之前的置为空
                    EntityWrapper<MemberEntity> memberEntityWrapper=new EntityWrapper<>();
                    memberEntityWrapper.eq("relation_id", relationId);
                    MemberEntity memberEntityTwo=memberService.selectOne(memberEntityWrapper);
                    if (null != memberEntityTwo){
                        memberEntityTwo.setSpecialId(null);
                        memberEntityTwo.setRelationId(null);
                        memberEntityTwo.setOpenId(null);
                        memberEntityTwo.setAccessToken(null);
                        memberService.updateAllColumnById(memberEntityTwo);
                    }
                    memberEntity.setRelationId(relationId);
                    memberEntity.setSpecialId(String.valueOf(req.getData().getSpecialId()));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            memberEntity.setDeviceType(deviceType);
            memberEntity.setDeviceValue(deviceValue);
            memberEntity.setOpenId(openId);
            memberEntity.setAccessToken(accessToken);
            memberEntity.setDeviceTime(DateUtil.getUnixStamp());
            memberService.updateAllColumnById(memberEntity);
            return R.ok();
        }
    }

    /**
     * 去提现时显示账号密码
     * @param request
     * @return
     */
    @RequestMapping("selectPay")
    public R selectPay(HttpServletRequest request){
        int memberId=token(request);
        MemberEntity memberEntity = memberService.selectById(memberId);
        Map<String,Object> map=new HashMap<>();
        map.put("name", memberEntity.getName());
        map.put("accountAlipay", memberEntity.getAccountAlipay());
        return R.ok().put("payInfo", map);
    }

    /**
     * 去提现--绑定支付宝账号
     * @param name
     * @param accountAlipay
     * @param request
     * @return
     */
    @RequestMapping("bindingPay")
    public R bindingPay(@RequestParam(value = "name",required = false) String name,
                        @RequestParam(value = "accountAlipay",required = false) String accountAlipay,
                        HttpServletRequest request){
        if (StringUtils.isEmpty(name) || StringUtils.isEmpty(accountAlipay)){
            return R.error(499, "参数不能为空");
        }
        int memberId=token(request);
        MemberEntity memberEntity = memberService.selectById(memberId);
        memberEntity.setAccountAlipay(accountAlipay);
        memberEntity.setName(name);
        if (memberService.updateById(memberEntity)){
            return R.ok();
        }
        return R.error();
    }

    /**
     * 用户修改头像
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("updateHeadimgurl")
    public R updateHeadimgurl(MultipartFile file, HttpServletRequest request){
        int memberId=token(request);
        if (file.isEmpty()) {
            return R.error("上传文件不能为空");
        }
        return memberService.updateHeadimgurl(memberId,file);
    }

    /**
     * 显示用户信息
     * @param request
     * @return
     */
    @RequestMapping("memberInfo")
    public R memberInfo(HttpServletRequest request){
        int memberId=token(request);
        MemberEntity memberEntity=memberService.selectById(memberId);
        if (null == memberEntity){
            return R.error();
        }
        Map<String,Object> map=new HashMap<>();
        map.put("mobile", memberEntity.getMobile());
        map.put("nickname", memberEntity.getNickname());
        map.put("six", memberEntity.getSix());
        map.put("wallet", memberEntity.getWallet());
        map.put("walletEstimate", memberEntity.getWalletEstimate());
        map.put("walletToday", memberEntity.getWalletToday());
        String headimgurl= memberEntity.getHeadimgurl();
        if (StringUtils.isNotEmpty(headimgurl)){
            String url=headimgurl.substring(0, 1);
            if (StringUtils.equals(url, "/")){
                headimgurl="http://xxqbt.xxqbt.com"+headimgurl;
            }
        }
        map.put("headimgurl",headimgurl);
        map.put("accountAlipay", memberEntity.getAccountAlipay());
        map.put("inviteCode", memberEntity.getInviteCode());
        EntityWrapper<WeixinMemberEntity> ew=new EntityWrapper();
        ew.eq("member_id", memberId);
        WeixinMemberEntity weixinMemberEntity=weixinMemberService.selectOne(ew);
        if (null ==weixinMemberEntity){
            map.put("isWeiXin", 0);//未绑定
        }else {
            map.put("isWeiXin", 1);//绑定
        }
        return R.ok(map);
    }
    /**
    * @Description: 修改昵称
    * @Author: Mr.Wang 
    * @Date: 2020/6/11 
    */
    @RequestMapping("updateNiCheng")
    public R updateNiCheng(@RequestParam(value = "message",required = false) String message,HttpServletRequest request){
        if (StringUtils.isEmpty(message)){
            return R.error(499,"内容不能为空");
        }
        int memberId=token(request);
        MemberEntity memberEntity=memberService.selectById(memberId);
        memberEntity.setNickname(message);
        memberService.updateAllColumnById(memberEntity);
        return R.ok();
    }



    /**
     * 修改用户信息   status  1:昵称、2:性别、3:支付宝账号、4:手机号
     * @param message
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("updateInfo")
    public R updateInfo(@RequestParam(value = "status",defaultValue = "0") Integer status,
                        @RequestParam(value = "message",required = false) String message,HttpServletRequest request){
        int memberId=token(request);
        if (status==0){
            return R.error(499,"参数错误");
        }
        if (StringUtils.isEmpty(message)){
            return R.error(499,"内容不能为空");
        }
        return memberService.updateInfo(status,message,memberId);
    }
    /**
    * @Description: 我的粉丝
    * @Author: Mr.Wang 
    * @Date: 2020/5/28 
    */
    @RequestMapping("myFan")
    public synchronized R myFan(HttpServletRequest request){
        int memberId=token(request);
        List list=new ArrayList<>();
        EntityWrapper<MemberEntity> entityEntityWrapper=new EntityWrapper<>();
        entityEntityWrapper.eq("pid", memberId);
        entityEntityWrapper.orderBy("create_time",false);
        List<MemberEntity> memberEntityList=memberService.selectList(entityEntityWrapper);
        if(!memberEntityList.isEmpty()){
          for (MemberEntity memberEntity:memberEntityList){
              Map<String,Object> map=new HashMap<>();
              String mobile=memberEntity.getMobile();
              mobile =mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
              map.put("mobile", mobile);
              map.put("nickname", memberEntity.getNickname());
              map.put("createTime", memberEntity.getCreateTime());
              map.put("headimgurl", memberEntity.getHeadimgurl());
              list.add(map);
          }
        }
        return R.ok().put("myFan",list);
    }
    /**
     * @Description: 我的邀请码
     * @Author: Mr.Wang 
     * @Date: 2020/5/28 
     */
    @RequestMapping("myCode")
    public synchronized R myCode(HttpServletRequest request){
        int memberId=token(request);
        MemberEntity memberEntity=memberService.selectById(memberId);
        Map<String,Object> map=new HashMap<>();
        map.put("inviteCode", memberEntity.getInviteCode());
        map.put("inviteQrcode", "http://xxqbt.xxqbt.com"+memberEntity.getInviteQrcode());
        return R.ok(map);
    }
    private int token(HttpServletRequest request){
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        //token为空
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空");
        }
        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getIsExpire()==1){
            throw new RRException("token失效，请重新登录");
        }
        return tokenEntity.getMemberId().intValue();
    }
}
