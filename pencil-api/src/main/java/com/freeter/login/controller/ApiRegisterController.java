package com.freeter.login.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.QRCode.QRCodeUtil;
import com.freeter.common.util.Constant;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.JedisUtils;
import com.freeter.common.utils.R;
import com.freeter.common.utils.RedisUtils;
import com.freeter.common.validator.ValidatorUtils;
import com.freeter.entity.TokenEntity;
import com.freeter.login.form.RegisterForm;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.advert.service.MessageService;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WeixinMemberService;
import com.freeter.token.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * 注册接口
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-26 17:27
 */
@RestController
@RequestMapping("api/")
@Api(tags="注册接口")
public class ApiRegisterController {
    @Autowired
    private MemberService memberService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;

    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
    @CrossOrigin
    @RequestMapping("verifyCode")
    @ApiOperation("验证邀请码")
    public R verifyCode(@RequestParam(value = "inviteCode",required = false) String inviteCode){
        if (StringUtils.isNotEmpty(inviteCode)){
            int length=inviteCode.length();
            EntityWrapper<MemberEntity> memberWrapper=new EntityWrapper<>();
            memberWrapper.eq("status", 1);
            memberWrapper.eq("is_del", 0);
            if (length==11){//手机号，反之邀请码
                memberWrapper.eq("mobile",inviteCode);
            }else {
                memberWrapper.eq("invite_code",inviteCode);
            }
            MemberEntity memberEntity= memberService.selectOne(memberWrapper);
            if (null != memberEntity){
                return R.ok();
            }
        }
        return R.error(499,"无用户");
    }
    @CrossOrigin
    @RequestMapping("register")
    @ApiOperation("手机号注册")
    public R register(RegisterForm form){
        ValidatorUtils.validateEntity(form);
        String logCode = jedis.get(Constant.SMS_CODE_KEY+form.getPhone());
        if(logCode == null) {
            return R.error("验证码已经失效，请重新获取");
        }
        if(!form.getCode().equals(logCode)) {
            return R.error("输入验证码有误，请重新填写");
        }
        EntityWrapper<MemberEntity> memberWrapper=new EntityWrapper<>();
        memberWrapper.eq("mobile",form.getPhone());
        MemberEntity member= memberService.selectOne(memberWrapper);
        if (null != member){
            return R.error(499,"手机号已存在");
        }
        //查看填写的邀请码是否存在
        int pid=0;
        if (StringUtils.isNotEmpty(form.getInviteCode()) && !StringUtils.equals(form.getInviteCode(), "null")){
            EntityWrapper<MemberEntity> ew=new EntityWrapper<>();
            ew.eq("invite_code",form.getInviteCode());
            MemberEntity entity= memberService.selectOne(ew);
            if (null == entity){
                return R.error(499,"邀请码有误");
            }
            pid=entity.getMemberId();
            //用户注册成功邀请人获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
           boolean result = moneySetRecordService.addYaoQingRenDaTiKa(pid);
        }
        MemberEntity memberEntity=new MemberEntity();
        String code=getCode();
        memberEntity.setCreateTime(DateUtil.getUnixStamp());
        memberEntity.setMobile(form.getPhone());
        memberEntity.setInviteCode(code);
        memberEntity.setStatus(1);
        if (pid!=0){
            memberEntity.setPid(pid);
        }
        // 生成的二维码的路径及名称
        String destPath = "qianbitou/public/uploads/api/code/"+code+".jpg";
        String dbUrl="/uploads/api/code/"+code+".jpg";//存入数据库的路径
        try {
            //生成二维码
            QRCodeUtil.encode(code,"", destPath, true);
        }catch (Exception e){
            System.out.println("QRCodeUtil--Exception");
        }
        memberEntity.setInviteQrcode(dbUrl);
        if (memberService.insert(memberEntity)){
            int memberId=memberEntity.getMemberId();
            //用户注册成功获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
            List<PackageCardVO> list=moneySetRecordService.addDaTiKa(memberId);
            Map<String,Object> map=new HashMap<>();
            map.put("packageCar", list);
            TokenEntity tokenEntity = tokenService.createToken(memberId);
            map.put("inviteCode", memberEntity.getInviteCode());
            map.put("nickname", memberEntity.getNickname());
            map.put("wallet", memberEntity.getWallet());
            map.put("token", tokenEntity.getToken());
            map.put("deviceType", null);
            map.put("deviceValue", null);
            map.put("headimgurl", "");
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
            map.put("memberId", memberId);
            return R.ok(map);
        }
        return R.error(499,"注册失败");
    }
    /* *
     *创建邀请码
     * @author wangkui
     * @date 2020/1/8 15:10
     * @param []
     * @return java.lang.String
     */
    public String getCode(){
        char[] charArray=new char[]{
                '0' ,'1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n',
                'o','p','q','r','s','t','u','v','w','x','y','z'
        };
        Random random=new Random();
        int[] indexArray=new int[]{
                random.nextInt(36),random.nextInt(36),
                random.nextInt(36),random.nextInt(36),
                random.nextInt(36),random.nextInt(36),
                random.nextInt(36),random.nextInt(36)
        };
        char[] generateArray=new char[8];
        for(int i=0;i<indexArray.length;i++){
            generateArray[i]=charArray[indexArray[i]];
        }
        return new String(generateArray);
    }

}
