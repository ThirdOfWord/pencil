package com.freeter.modules.pingtaiGoods.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.modules.pingtaiGoods.dao.GoodsGoldRushDao;
import com.freeter.modules.pingtaiGoods.entity.GoodReceiveEntity;
import com.freeter.modules.pingtaiGoods.entity.GoodsGoldRushEntity;
import com.freeter.modules.pingtaiGoods.entity.TaoLiJinInfo;
import com.freeter.modules.pingtaiGoods.service.GoodReceiveService;
import com.freeter.modules.pingtaiGoods.service.GoodsGoldRushService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.response.TbkItemInfoGetResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("goodsGoldRushService")
public class GoodsGoldRushServiceImpl extends ServiceImpl<GoodsGoldRushDao, GoodsGoldRushEntity> implements GoodsGoldRushService {
    @Autowired
    private GoodReceiveService goodReceiveService;
    @Autowired
    private MemberService memberService;

    private final static String url="http://gw.api.taobao.com/router/rest";
    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    private final static Long adzoneId=110507450350L;
    /**
    * @Description: 淘礼金列表
    * @Author: Mr.Wang 
    * @Date: 2020/8/17 
    */
    @Override
    @Transactional
    public List<TaoLiJinInfo> getListAll(int currentPage, HttpServletRequest request) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        String ipAddress=getIpAddress(request);

        EntityWrapper<GoodsGoldRushEntity> entityEntityWrapper =new EntityWrapper<>();
        entityEntityWrapper.eq("status", 1);
        entityEntityWrapper.eq("post_code", 0);
        entityEntityWrapper.orderBy("sortid", true);
        entityEntityWrapper.last("limit "+currentPage+",10");
        List<GoodsGoldRushEntity> goldRushEntityList = baseMapper.selectList(entityEntityWrapper);
        List<TaoLiJinInfo> list = new ArrayList<>();
        if (!goldRushEntityList.isEmpty()){
            for (GoodsGoldRushEntity goldRushEntity:goldRushEntityList) {
                TaoLiJinInfo taoLiJinInfo = new TaoLiJinInfo();
                taoLiJinInfo.setSubsidyPrice(goldRushEntity.getPerFace());
                taoLiJinInfo.setUrlLink(goldRushEntity.getUrlLink());
                taoLiJinInfo.setGoodsGoldRushId(goldRushEntity.getGoodsGoldRushId());
                try {
                    //获取商品信息
                    TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
                    req.setNumIids(goldRushEntity.getItemId());
                    req.setPlatform(2L);
                    req.setIp(ipAddress);
                    TbkItemInfoGetResponse rsp = client.execute(req);
                    String zkFinalPrice = rsp.getResults().get(0).getZkFinalPrice();
                    String reservePrice = rsp.getResults().get(0).getReservePrice();
                    /*if (StringUtils.isEmpty(zkFinalPrice)){
                        taoLiJinInfo.setJuanHouPrice(new BigDecimal(zkFinalPrice).subtract(goldRushEntity.getPerFace()));
                    }else {
                        taoLiJinInfo.setJuanHouPrice(new BigDecimal(reservePrice).subtract(goldRushEntity.getPerFace()));
                    }*/
                    taoLiJinInfo.setJuanHouPrice(new BigDecimal(0));
                    taoLiJinInfo.setGoodsId(rsp.getResults().get(0).getNumIid());
                    taoLiJinInfo.setGoodsName(rsp.getResults().get(0).getTitle());
                    taoLiJinInfo.setGoodsThumbnailUrl(rsp.getResults().get(0).getPictUrl());
                }catch (Exception e){
                    e.printStackTrace();
                }
                list.add(taoLiJinInfo);
            }
        }
        return list;
    }
    /**
    * @Description: 用户领取淘礼金前判断下 
    * @Author: Mr.Wang 
    * @Date: 2020/8/17 
    */
    @Override
    @Transactional
    public R receive(int memberId, int goodsGoldRushId) {
        // 1、新用户7天内可以领取1次,而且7天内只能领取1次
        //2、超过7天的用户属于老用户,不能领取
        MemberEntity memberEntity = memberService.selectById(memberId);
        if (null == memberEntity){
            return R.error(499,"当前用户不存在");
        }
        String openId = memberEntity.getOpenId();
        if (StringUtils.isEmpty(openId)){
            return R.error(1,"需要授权淘宝");
        }
        long createTime = memberEntity.getCreateTime();
        long nowDate = DateUtil.getUnixStamp();
        long time = 604800;
        if ((nowDate-createTime)< time){
            //判断当前账号
            EntityWrapper<GoodReceiveEntity> goodReceiveEntityEntityWrapper = new EntityWrapper<>();
            goodReceiveEntityEntityWrapper.eq("member_id", memberId);
            GoodReceiveEntity receiveEntity= goodReceiveService.selectOne(goodReceiveEntityEntityWrapper);
            if (null != receiveEntity){
                return R.error(499,"当前用户只可以领取1次");
            }
            //判断当前账号的手机号
            EntityWrapper<GoodReceiveEntity> goodReceiveEntityWrapper = new EntityWrapper<>();
            goodReceiveEntityWrapper.eq("mobile", memberEntity.getMobile());
            receiveEntity= goodReceiveService.selectOne(goodReceiveEntityWrapper);
            if (null != receiveEntity){
                return R.error(499,"当前用户只可以领取1次");
            }
            //判断当前账号的授权的淘宝号
            EntityWrapper<GoodReceiveEntity> goodReceiveWrapper = new EntityWrapper<>();
            goodReceiveWrapper.eq("open_id", openId);
            receiveEntity= goodReceiveService.selectOne(goodReceiveWrapper);
            if (null != receiveEntity){
                return R.error(499,"当前用户只可以领取1次");
            }
            //记录领取记录
            receiveEntity = new GoodReceiveEntity();
            receiveEntity.setCreateTime((int)nowDate);
            receiveEntity.setGoodsGoldRushId(goodsGoldRushId);
            receiveEntity.setMemberId(memberId);
            receiveEntity.setMobile(memberEntity.getMobile());
            receiveEntity.setOpenId(openId);
            goodReceiveService.insert(receiveEntity);
            return R.ok();
        }else {
            return R.error(499,"用户不符合领取规则");
        }
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 参考文章： http://developer.51cto.com/art/201111/305181.htm
     *
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     *
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     *
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
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
