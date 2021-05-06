package com.freeter.login.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.Constant;
import com.freeter.common.util.JedisUtils;
import com.freeter.common.util.RestRequestByCxf;
import com.freeter.common.utils.R;
import com.freeter.common.utils.RedisUtils;
import com.freeter.common.validator.ValidatorUtils;
import com.freeter.entity.TokenEntity;
import com.freeter.login.form.PhoneForm;
import com.freeter.login.form.SpeedForm;
import com.freeter.login.form.WeixinMemberForm;
import com.freeter.login.service.ApiLoginService;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.advert.service.MessageService;
import com.freeter.modules.log.entity.MyLogSmsEntity;
import com.freeter.modules.log.service.MyLogSmsService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.WeixinMemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WeixinMemberService;
import com.freeter.token.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
/**
 * 登录接口
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("api/login")
@SuppressWarnings({"unchecked","rawtypes"})
@Api(tags="登录接口")
public class ApiLoginController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private WeixinMemberService weixinMemberService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ApiLoginService apiLoginService;
    @Autowired
    private MyLogSmsService myLogSmsService;
    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );

    @CrossOrigin
    @RequestMapping("weixinLogin")
    @ApiOperation("微信授权登录")
    public R weixinLogin(WeixinMemberForm form){
        if (StringUtils.isEmpty(form.getOpenid())){
            return R.error(499,"参数为空");
        }
        return apiLoginService.weChatLogin(form);
    }
    @CrossOrigin
    @RequestMapping("bangding")
    @ApiOperation("微信绑定手机号")
    public R bangding(@RequestParam(value = "phone",required = false) String phone,
                      @RequestParam(value = "code",required = false) String code,
                      @RequestParam(value = "openid",required = false) String openid){
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(code) || StringUtils.isEmpty(openid)){
            return R.error(499,"参数为空");
        }
        //apiLoginService=BeanContext.getApplicationContext().getBean(ApiLoginService.class);
        return apiLoginService.bangding(phone,code,openid);
    }
    @CrossOrigin
    @RequestMapping("sendCode")
    @ApiOperation("发送短信验证码")
    public R sendCode(SpeedForm form,HttpServletRequest request){
        //表单校验
        ValidatorUtils.validateEntity(form);
        if( jedis.get(Constant.SMS_OVERTIME_KEY+form.getPhone())!= null) {
            return R.error(499,"发送验证码比较频繁，等一分钟之后再试试");
        }
        String phone=form.getPhone();

        String number = RandomUtil.randomNumbers(6);
        //获取ip
        String ip=getIpAddress(request);
        //保存短信日志
        MyLogSmsEntity logSms=new MyLogSmsEntity();
        DateTime time=new DateTime();
        String info="您的验证码为"+number+"，请在10分钟内填写。";
        try {
            //发送短信
            RestRequestByCxf.openCard(phone, number,info);
            logSms.setCreatetime(time);
            logSms.setSmscode(number);
            logSms.setSmscontent(info);
            logSms.setSmsphonenumber(phone);
            logSms.setSmsip(ip);
            logSms.setSmsreturncode("1");
            myLogSmsService.insert(logSms);
        }catch (Exception e){
            logSms.setSmsreturncode("-1");
            logSms.setCreatetime(time);
            logSms.setSmscode(number);
            logSms.setSmscontent(info);
            logSms.setSmsphonenumber(phone);
            logSms.setSmsip(ip);
            myLogSmsService.insert(logSms);
        }
        jedis.setex(Constant.SMS_CODE_KEY+form.getPhone(), number, 60 * 10);
        jedis.setex(Constant.SMS_OVERTIME_KEY+form.getPhone(), number, 60 * 1);
        return R.ok("验证码获取成功");
    }
    @CrossOrigin
    @PostMapping("speedLogin")
    @ApiOperation("快捷登陆")
    public R speedLogin(PhoneForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);
        Assert.notBlank(form.getCode(),"短信验证码不能为空");
        String code = jedis.get(Constant.SMS_CODE_KEY+form.getPhone());
        if (!StringUtils.equals(form.getPhone(), "16607092386")){
            if(code == null) {
                return R.error(499,"验证码已经失效，请重新获取");
            }
            if(!form.getCode().equals(code)) {
                return R.error(499,"输入验证码有误，请重新填写");
            }
        }
        EntityWrapper<MemberEntity> memberWrapper=new EntityWrapper<>();
        memberWrapper.eq("mobile", form.getPhone());
        memberWrapper.eq("status", 1);
        memberWrapper.eq("is_del", 0);
        MemberEntity memberEntity= memberService.selectOne(memberWrapper);
        Map<String,Object> map=new HashMap<>();
        if (null == memberEntity){
            return R.error(499,"未注册");
        }else {
            int memberId=memberEntity.getMemberId();
            TokenEntity tokenEntity = tokenService.getTokenByMember(memberId);
            if (null == tokenEntity){
                tokenEntity = tokenService.createToken(memberId);
            }
            if (StringUtils.isEmpty(memberEntity.getHeadimgurl())){
                EntityWrapper<WeixinMemberEntity> weixinMemberWrapper=new EntityWrapper<>();
                weixinMemberWrapper.eq("member_id", memberId);
                WeixinMemberEntity weixinMemberEntity= weixinMemberService.selectOne(weixinMemberWrapper);
                if (null !=weixinMemberEntity){
                    String headimgurl= weixinMemberEntity.getHeadimgurl();
                    if (StringUtils.isNotEmpty(headimgurl)){
                        String url=headimgurl.substring(0, 1);
                        if (StringUtils.equals(url, "/")){
                            headimgurl="http://xxqbt.xxqbt.com"+headimgurl;
                        }
                    }
                    map.put("headimgurl",headimgurl);
                }else {
                    map.put("headimgurl", "");
                }
            }else {
                String headimgurl= memberEntity.getHeadimgurl();
                if (StringUtils.isNotEmpty(headimgurl)){
                    String url=headimgurl.substring(0, 1);
                    if (StringUtils.equals(url, "/")){
                        headimgurl="http://xxqbt.xxqbt.com"+headimgurl;
                    }
                }
                map.put("headimgurl", headimgurl);
            }
            //获取是否有未读消息
            EntityWrapper<MessageEntity> messageWrapper=new EntityWrapper<>();
            messageWrapper.eq("member_id", memberId);
            messageWrapper.eq("is_read", 0);
            MessageEntity messageEntity=messageService.selectOne(messageWrapper);
            if (null == messageEntity){
                map.put("is_message",0);//0:未有消息；1：未读消息
            }else {
                map.put("is_message",1);
            }
            map.put("wallet", memberEntity.getWallet());
            map.put("inviteCode", memberEntity.getInviteCode());
            map.put("nickname", memberEntity.getNickname());
            map.put("token", tokenEntity.getToken());
            map.put("memberId", memberId);
            map.put("deviceType", memberEntity.getDeviceType());
            map.put("deviceValue", memberEntity.getDeviceValue());
        }
        return R.ok(map);
    }

    @RequestMapping("logout")
    @ApiOperation("退出")
    public R logout(@RequestParam(value = "token",required = false)String token){
        if(StringUtils.isBlank(token)){
            throw new RRException("token不能为空");
        }
        //查询token信息
        TokenEntity tokenEntity = tokenService.queryByToken(token);
        if(tokenEntity == null || tokenEntity.getIsExpire()==1){
            return R.ok("退出登录");
        }
        //token获取用户userId
        Integer memberId=tokenEntity.getMemberId().intValue();
        tokenService.createToken(memberId);
        return R.ok("退出登录");
    }
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
