package com.freeter.login.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.QRCode.QRCodeUtil;
import com.freeter.common.util.Constant;
import com.freeter.common.util.DateUtil;
import com.freeter.common.util.JedisUtils;
import com.freeter.common.utils.R;
import com.freeter.common.utils.RedisUtils;
import com.freeter.entity.TokenEntity;
import com.freeter.login.form.WeixinMemberForm;
import com.freeter.login.service.ApiLoginService;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.advert.service.MessageService;
import com.freeter.modules.money.service.MoneySetRecordService;
import com.freeter.modules.packageCard.entity.vo.PackageCardVO;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.entity.WeixinMemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.modules.user.service.WeixinMemberService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("apiLoginService")
public class ApiLoginServiceImpl implements ApiLoginService{
    @Autowired
    private WeixinMemberService weixinMemberService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private MoneySetRecordService moneySetRecordService;

    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
    /**
     *微信授权登录
     * @author wangkui
     * @date 2020/1/14 11:23
     * @return com.freeter.common.utils.R
     */
    @Override
    public R weChatLogin(WeixinMemberForm form) {
            //根据openid查询用户信息
            EntityWrapper<WeixinMemberEntity> ew=new EntityWrapper<>();
            ew.eq("openid", form.getOpenid());
            WeixinMemberEntity weixinMemberEntity = weixinMemberService.selectOne(ew);
            if (Objects.isNull(weixinMemberEntity)) {
                //没有绑定用户请前往绑定
                WeixinMemberEntity weixinEntity =new WeixinMemberEntity();
                weixinEntity.setSex(form.getSex());
                weixinEntity.setRefreshToken(form.getRefreshToken());
                weixinEntity.setProvince(form.getProvince());
                weixinEntity.setCity(form.getCity());
                weixinEntity.setCountry(form.getCountry());
                weixinEntity.setExpiresIn(form.getExpiresIn());
                weixinEntity.setNickname(form.getNickname());
                weixinEntity.setHeadimgurl(form.getHeadimgurl());
                weixinEntity.setAccessToken(form.getAccessToken());
                weixinEntity.setUnionid(form.getUnionid());
                weixinEntity.setOpenid(form.getOpenid());
                weixinEntity.setCreateTime(DateUtil.getUnixStamp());
                weixinMemberService.insert(weixinEntity);
                return R.error(1,form.getOpenid());
            } else {
                if (weixinMemberEntity.getMemberId().intValue()==0){
                    return R.error(1,form.getOpenid());
                }
                //已经绑定用户信息更新信息直接登录
                weixinMemberEntity.setAccessToken(form.getAccessToken());
                weixinMemberEntity.setHeadimgurl(form.getHeadimgurl());
                weixinMemberEntity.setNickname(form.getNickname());
                weixinMemberEntity.setCountry(form.getCountry());
                weixinMemberEntity.setCity(form.getCity());
                weixinMemberEntity.setExpiresIn(form.getExpiresIn());
                weixinMemberEntity.setProvince(form.getProvince());
                weixinMemberEntity.setUpdateTime(DateUtil.getUnixStamp());
                weixinMemberEntity.setRefreshToken(form.getRefreshToken());
                weixinMemberEntity.setSex(form.getSex());
                weixinMemberService.updateById(weixinMemberEntity);
                int memberId=weixinMemberEntity.getMemberId();
                TokenEntity tokenEntity = tokenService.createToken(memberId);
                MemberEntity memberEntity =memberService.selectById(memberId);
                HashMap<String,Object> map = new HashMap();
                if (Objects.nonNull(memberEntity)){
                     map.put("wallet", memberEntity.getWallet());
                     map.put("inviteCode", memberEntity.getInviteCode());
                }else {
                    map.put("wallet", 0);
                    map.put("inviteCode", "");
                }
                map.put("token", tokenEntity.getToken());
                String headimgurl= form.getHeadimgurl();
                if (StringUtils.isNotEmpty(headimgurl)){
                    String url=headimgurl.substring(0, 1);
                    if (StringUtils.equals(url, "/")){
                        headimgurl="http://xxqbt.xxqbt.com"+headimgurl;
                    }
                }
                map.put("headimgurl", headimgurl);
                map.put("nickname", form.getNickname());
                map.put("memberId", memberId);
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
                return R.ok(map);
            }
    }

    /* *
     *微信登录绑定手机号
     * @author wangkui
     * @date 2020/1/14 11:23
     * @param [phone, code, openid]
     * @return com.freeter.common.utils.R
     */
    @Override
    public R bangding(String phone, String code, String openid) {
        String codeRedis = jedis.get(Constant.SMS_CODE_KEY+phone);
        if(StringUtils.isEmpty(codeRedis)) {
            return R.error(499,"验证码已经失效，请重新获取");
        }
        if(!code.equals(codeRedis)) {
            return R.error(499,"输入验证码有误，请重新填写");
        }
        //根据openid查询用户信息
        EntityWrapper<WeixinMemberEntity> ew=new EntityWrapper<>();
        ew.eq("openid", openid);
        WeixinMemberEntity weixinMemberEntity = weixinMemberService.selectOne(ew);
        if (Objects.isNull(weixinMemberEntity)){
            return R.error(499,"找不到对象");
        }
        Map<String,Object> map=new HashMap<>();
        //根据phone查询用户信息
        EntityWrapper<MemberEntity> memberWrapper=new EntityWrapper<>();
        memberWrapper.eq("mobile", phone);
        MemberEntity memberEntity =memberService.selectOne(memberWrapper);
        if (null==memberEntity){
            memberEntity=new MemberEntity();
            String inviteCode=getCode();
            memberEntity.setMobile(phone);
            memberEntity.setHeadimgurl(weixinMemberEntity.getHeadimgurl());
            memberEntity.setCreateTime(DateUtil.getUnixStamp());
            memberEntity.setStatus(1);
            memberEntity.setInviteCode(inviteCode);
            memberEntity.setSix(weixinMemberEntity.getSex());
            memberEntity.setNickname(weixinMemberEntity.getNickname());
            // 生成的二维码的路径及名称
            String destPath = "qianbitou/public/uploads/api/code/"+inviteCode+".jpg";
            String dbUrl="/uploads/api/code/"+inviteCode+".jpg";//存入数据库的路径
            try {
                //生成二维码
                QRCodeUtil.encode(inviteCode,"", destPath, true);
            }catch (Exception e){
                System.out.println("QRCodeUtil--Exception");
            }
            memberEntity.setInviteQrcode(dbUrl);
            memberService.insert(memberEntity);
            //用户注册成功获取对应的答题卡 发卡库减少 奖品库增加 增加或减少记录
            List<PackageCardVO> list=moneySetRecordService.addDaTiKa(memberEntity.getMemberId());
            map.put("packageCar", list);
        }else {
            if (memberEntity.getStatus()==0){
                return R.error(499,"当前账号已禁用");
            }
            if (StringUtils.isEmpty(memberEntity.getHeadimgurl())){
                memberEntity.setHeadimgurl(weixinMemberEntity.getHeadimgurl());
                memberService.updateById(memberEntity);
            }

        }
        weixinMemberEntity.setMemberId(memberEntity.getMemberId());
        if (weixinMemberService.updateById(weixinMemberEntity)){
            TokenEntity tokenEntity = tokenService.createToken(memberEntity.getMemberId());
            map.put("inviteCode", memberEntity.getInviteCode());
            String headimgurl= weixinMemberEntity.getHeadimgurl();
            if (StringUtils.isNotEmpty(headimgurl)){
                String url=headimgurl.substring(0, 1);
                if (StringUtils.equals(url, "/")){
                    headimgurl="http://xxqbt.xxqbt.com"+headimgurl;
                }
            }
            map.put("headimgurl", headimgurl);
            map.put("nickname", weixinMemberEntity.getNickname());
            map.put("wallet", memberEntity.getWallet());
            map.put("token", tokenEntity.getToken());
            //获取是否有未读消息
            EntityWrapper<MessageEntity> messageWrapper=new EntityWrapper<>();
            messageWrapper.eq("member_id", memberEntity.getMemberId());
            messageWrapper.eq("is_read", 0);
            MessageEntity messageEntity=messageService.selectOne(messageWrapper);
            if (null == messageEntity){
                map.put("is_message",0);//0:未有消息；1：未读消息
            }else {
                map.put("is_message",1);
            }
            return R.ok(map);
        }
        return R.error(499,"绑定失败");
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
