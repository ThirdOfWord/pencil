package com.freeter.modules.pingtaiGoods;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.entity.GoodsShareLinkEntity;
import com.freeter.modules.good.entity.MemberCollectGoodsEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.good.service.GoodsShareLinkService;
import com.freeter.modules.good.service.MemberCollectGoodsService;
import com.freeter.modules.pingtaiGoods.entity.GoodInfo;
import com.freeter.modules.pingtaiGoods.entity.PopInfo;
import com.freeter.modules.pingtaiGoods.entity.ShareGoodInfo;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.request.*;
import com.pdd.pop.sdk.http.api.pop.response.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 拼多多
 */
@RestController
@RequestMapping("api/duoduobao")
public class PopClientController{
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CollectGoodsService collectGoodsService;
    @Autowired
    private GoodsShareLinkService goodsShareLinkService;

    static Log log= LogFactory.get(PopClientController.class);
    private final static String clientId = "05f9fbbd87cb4c37947463f75da309bc";
    private final static  String clientSecret = "7f466088dc0ac82891ba306e317c9826aac5ca38";
    private final static  PopClient client = new PopHttpClient(clientId, clientSecret);
    private final static String pid="9393981_129055352";
    /**
    * @Description: 获取拼多多商品列表
    * @Author: Mr.Wang 
    * @Date: 2020/5/5 
    */
    @RequestMapping("list")
    public R getList(@RequestParam(value ="currentPage",defaultValue="1")int currentPage,
                     @RequestParam(value ="catId",defaultValue="0")Long catId,
                     @RequestParam(value ="sortType",defaultValue="0")Integer sortType) throws Exception {
        PddDdkGoodsSearchRequest request = new PddDdkGoodsSearchRequest();
        request.setPage(currentPage);
        request.setPageSize(20);
        request.setPid(pid);
        request.setSortType(0);
        request.setCatId(catId);
        request.setWithCoupon(false);
        request.setSortType(sortType);
        PddDdkGoodsSearchResponse response = client.syncInvoke(request);
        List<PddDdkGoodsSearchResponse.GoodsSearchResponseGoodsListItem> list=response.getGoodsSearchResponse().getGoodsList();
        if (list.isEmpty()){
            return R.ok().put("duoduobaoList",null);
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<PopInfo> popInfoList=new ArrayList<>();
        for (PddDdkGoodsSearchResponse.GoodsSearchResponseGoodsListItem  goodsListItem:list){
            PopInfo popInfo=new PopInfo();
            popInfo.setActivityTags(goodsListItem.getActivityTags());
            popInfo.setActivityType(goodsListItem.getActivityType());
            popInfo.setCategoryName(goodsListItem.getCategoryName());
            popInfo.setCltCpnDiscount(goodsListItem.getCltCpnDiscount());
            popInfo.setGoodsId(goodsListItem.getGoodsId());
            popInfo.setGoodsImageUrl(goodsListItem.getGoodsImageUrl());
            popInfo.setGoodsName(goodsListItem.getGoodsName());
            popInfo.setGoodsThumbnailUrl(goodsListItem.getGoodsThumbnailUrl());
            popInfo.setGoodsDesc(goodsListItem.getGoodsDesc());
            popInfo.setHasCoupon(goodsListItem.getHasCoupon());
            popInfo.setLgstTxt(goodsListItem.getLgstTxt());
            popInfo.setMallName(goodsListItem.getMallName());
            popInfo.setMerchantType(goodsListItem.getMerchantType());
            popInfo.setOptName(goodsListItem.getOptName());
            popInfo.setSalesTip(goodsListItem.getSalesTip());
            popInfo.setServiceTags(goodsListItem.getServiceTags());
            popInfo.setServTxt(goodsListItem.getServTxt());
            popInfo.setDescTxt(goodsListItem.getDescTxt());
            popInfo.setSupplierId(2);
            Long minGroupPrice=0L;//最小拼团价（单位为分）
            Long couponDiscount=0L;//优惠券面额，单位为分
            Long juanHouPrice=0L;//卷后价，单位为分
            Long promotionRate=0L;//佣金比例，千分比
            try {
                minGroupPrice=goodsListItem.getMinGroupPrice();//最小拼团价（单位为分）
                couponDiscount=goodsListItem.getCouponDiscount();//优惠券面额，单位为分
                juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
                promotionRate=goodsListItem.getPromotionRate();//佣金比例，千分比
            }catch (Exception e){
                log.error("pingduoduoList异常");
            }
            BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
            BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
            BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
            popInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为分）
            popInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为分
            popInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为分
            popInfo.setPromotionRate(promotionRate);//佣金比例，千分比
            popInfo.setPromotionAmount(cc);//佣金金额
            popInfoList.add(popInfo);
        }
        log.info("获取拼多多商品列表");
        return R.ok().put("duoduobaoList", popInfoList);
    }
    /**
    * @Description: 拼多多搜索商品
    * @Author: Mr.Wang 
    * @Date: 2020/6/3 
    */
    @RequestMapping("search")
    public R search(@RequestParam(value ="currentPage",defaultValue="1")int currentPage,
                    @RequestParam(value = "title",required = false)String title,HttpServletRequest req) throws Exception {
        if (StringUtils.isEmpty(title)){
            return R.error(499, "搜索内容为空");
        }
        int memberId= token(req);
        PddDdkGoodsSearchRequest request = new PddDdkGoodsSearchRequest();
        request.setCustomParameters(String.valueOf(memberId));
        request.setPage(currentPage);
        request.setPageSize(20);
        request.setPid(pid);
        request.setSortType(0);
        request.setKeyword(title);
        request.setWithCoupon(false);
        PddDdkGoodsSearchResponse response = client.syncInvoke(request);
        List<PddDdkGoodsSearchResponse.GoodsSearchResponseGoodsListItem> list=response.getGoodsSearchResponse().getGoodsList();
        if (list.isEmpty()){
            return R.ok().put("goodsList",null);
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<GoodInfo> popInfoList=new ArrayList<>();
        for (PddDdkGoodsSearchResponse.GoodsSearchResponseGoodsListItem  goodsListItem:list){
            GoodInfo popInfo=new GoodInfo();
            popInfo.setActivityTags(goodsListItem.getActivityTags());
            popInfo.setActivityType(goodsListItem.getActivityType());
            popInfo.setCategoryName(goodsListItem.getCategoryName());
            popInfo.setCltCpnDiscount(goodsListItem.getCltCpnDiscount());
            popInfo.setGoodsId(goodsListItem.getGoodsId());
            popInfo.setGoodsImageUrl(goodsListItem.getGoodsImageUrl());
            popInfo.setGoodsName(goodsListItem.getGoodsName());
            popInfo.setGoodsThumbnailUrl(goodsListItem.getGoodsThumbnailUrl());
            popInfo.setGoodsDesc(goodsListItem.getGoodsDesc());
            popInfo.setHasCoupon(goodsListItem.getHasCoupon());
            popInfo.setLgstTxt(goodsListItem.getLgstTxt());
            popInfo.setMallName(goodsListItem.getMallName());
            popInfo.setMerchantType(goodsListItem.getMerchantType());
            popInfo.setOptName(goodsListItem.getOptName());
            popInfo.setSalesTip(goodsListItem.getSalesTip());
            popInfo.setServiceTags(goodsListItem.getServiceTags());
            popInfo.setServTxt(goodsListItem.getServTxt());
            popInfo.setDescTxt(goodsListItem.getDescTxt());
            popInfo.setSupplierId(2);

            Long minGroupPrice=0L;
            Long couponDiscount=0L;
            Long juanHouPrice=0L;
            Long promotionRate=0L;
            try {
                minGroupPrice=goodsListItem.getMinGroupPrice();//最小拼团价（单位为分）
                couponDiscount=goodsListItem.getCouponDiscount();//优惠券面额，单位为分
                juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
                promotionRate=goodsListItem.getPromotionRate();//佣金比例，千分比
            }catch (Exception e){
                log.error("pingduoduoList异常");
            }

            BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
            BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
            BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
            popInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为分）
            popInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为分
            popInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为分
            popInfo.setPromotionRate(promotionRate);//佣金比例，千分比
            popInfo.setPromotionAmount(cc);//佣金金额
            popInfoList.add(popInfo);
        }
        return R.ok().put("goodsList", popInfoList);
    }
    /**
     * 多多进宝商品详情查询
     * @author wangkui
     * @date 2020/3/11 11:35
     * @param[supplierGoodsId]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("getInfo")
    public R getInfo(@RequestParam(value ="supplierGoodsId",defaultValue="0")String supplierGoodsId,HttpServletRequest req) throws Exception{
        Long goodsId=Long.parseLong(supplierGoodsId);
        if (goodsId==0){
            return R.error(499,"参数错误");
        }
        Map<String,Object> map=new HashMap<>();
        PddDdkGoodsDetailRequest request = new PddDdkGoodsDetailRequest();
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(goodsId);
        request.setGoodsIdList(goodsIdList);
        request.setPid(pid);
        request.setSearchId("pdd.ddk.goods.search");
        PddDdkGoodsDetailResponse response = client.syncInvoke(request);
        Long mallId=0L;
        try {
            mallId=response.getGoodsDetailResponse().getGoodsDetails().get(0).getMallId();
            String imgUrl=getShop(mallId);
            map.put("shopUrl", imgUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("duoduobao", response);
        //判断当前商品用户是否收藏过
        //从header中获取token
        String token = req.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = req.getParameter("token");
        }
        //token为空
        if(StringUtils.isBlank(token)){
            map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
        }else {
            //查询token信息
            TokenEntity tokenEntity = tokenService.queryByToken(token);
            if(tokenEntity == null || tokenEntity.getIsExpire()==1){
                map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
            }else {
             //去收藏表查询
                EntityWrapper<CollectGoodsEntity> entityWrapper=new EntityWrapper();
                entityWrapper.eq("member_id", tokenEntity.getMemberId().intValue());
                entityWrapper.eq("goods_id", goodsId);
                entityWrapper.eq("supplier_id", 2);
                CollectGoodsEntity collectGoodsEntity=collectGoodsService.selectOne(entityWrapper);
                if (null == collectGoodsEntity){
                    map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
                }else {
                    map.put("collectStatus", 1);//收藏当前商品状态--0：未收藏，1：已收藏
                }
            }
        }
        return R.ok(map);
    }
    //获取店铺
    public String getShop(Long mallId) throws Exception{
        PddDdkMerchantListGetRequest request = new PddDdkMerchantListGetRequest();
        List<Long> mallIdList = new ArrayList<Long>();
        mallIdList.add(mallId);
        request.setMallIdList(mallIdList);

        request.setPageNumber(1);
        request.setPageSize(1);
        PddDdkMerchantListGetResponse response = client.syncInvoke(request);
        String imgUrl=null;
        try {
            imgUrl=response.getMerchantListResponse().getMallSearchInfoVoList().get(0).getImgUrl();
        }catch (Exception e){
            e.printStackTrace();
        }
       return imgUrl;
    }
    /**
     * 多多进宝推广链接生成
     * @param supplierGoodsId
     * @param requ
     * @return
     * @throws Exception
     */
    @RequestMapping("url")
    public R getUrl(@RequestParam(value ="supplierGoodsId",defaultValue="0")String supplierGoodsId,HttpServletRequest requ) throws Exception{
        Long goodsId=Long.parseLong(supplierGoodsId);
        if (goodsId==0){
            return R.error(499,"参数错误");
        }
        int memberId=token(requ);
        PddDdkGoodsPromotionUrlGenerateRequest request = new PddDdkGoodsPromotionUrlGenerateRequest();
        request.setPId(pid);
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(goodsId);
        request.setGoodsIdList(goodsIdList);
        request.setGenerateShortUrl(true);
        request.setMultiGroup(false);//true:双人团
        request.setCustomParameters(String.valueOf(memberId));
        request.setGenerateWeappWebview(false);
        request.setGenerateWeApp(false);
        request.setGenerateWeiboappWebview(false);
        request.setGenerateMallCollectCoupon(false);
        request.setGenerateSchemaUrl(true);
        request.setGenerateQqApp(false);
        PddDdkGoodsPromotionUrlGenerateResponse response = client.syncInvoke(request);
        return R.ok().put("url", response);
    }

    /**
    * @Description: 分享商品购买者转链接
    * @Author: Mr.Wang 
    * @Date: 2020/6/29 
    */
    @CrossOrigin
    @RequestMapping("shareUrl")
    public R shareUrl(@RequestParam(value ="goodsShareLinkId",defaultValue="0")Integer goodsShareLinkId,HttpServletRequest requ) throws Exception{
        if (goodsShareLinkId == 0){
            return R.error(499,"参数错误");
        }
        GoodsShareLinkEntity shareLinkEntity=goodsShareLinkService.selectById(goodsShareLinkId);
        if (null == shareLinkEntity){
            return R.error(499,"参数错误");
        }
        Long goodsId=Long.parseLong(shareLinkEntity.getGoodsId());
        if (goodsId==0){
            return R.error(499,"参数错误");
        }
        int memberId=token(requ);
        if (null == shareLinkEntity.getMemberId()){
            return R.error(499,"参数错误");
        }
        shareLinkEntity.setClickMemberId(memberId);
        shareLinkEntity.setUpdateTime((int)DateUtil.getUnixStamp());
        goodsShareLinkService.updateAllColumnById(shareLinkEntity);
        String customParameters = String.valueOf(shareLinkEntity.getMemberId())+","+String.valueOf(memberId);
        PddDdkGoodsPromotionUrlGenerateRequest request = new PddDdkGoodsPromotionUrlGenerateRequest();
        request.setPId(pid);
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(goodsId);
        request.setGoodsIdList(goodsIdList);
        request.setGenerateShortUrl(true);
        request.setMultiGroup(false);//true:双人团
        request.setCustomParameters(customParameters);
        request.setGenerateWeappWebview(false);
        request.setGenerateWeApp(false);
        request.setGenerateWeiboappWebview(false);
        request.setGenerateMallCollectCoupon(false);
        request.setGenerateSchemaUrl(true);
        request.setGenerateQqApp(false);
        PddDdkGoodsPromotionUrlGenerateResponse response = client.syncInvoke(request);
        return R.ok().put("url", response);
    }

    /**
    * @Description: 拼多多商品分享
    * @Author: Mr.Wang 
    * @Date: 2020/6/1 
    */
    @RequestMapping("popShareGood")
    public R popShareGood(@RequestParam(value ="supplierGoodsId",required = false)Long supplierGoodsId,HttpServletRequest req) throws Exception{
        if (supplierGoodsId==0){
            return R.error(499,"参数错误");
        }
        int memberId= token(req);
        MemberEntity memberEntity =memberService.selectById(memberId);
        if (null == memberEntity){
            return R.error(499, "用户信息不存在");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        //商品详情
        PddDdkGoodsDetailRequest request = new PddDdkGoodsDetailRequest();
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(supplierGoodsId);
        request.setGoodsIdList(goodsIdList);
        PddDdkGoodsDetailResponse response = client.syncInvoke(request);
        PddDdkGoodsDetailResponse.GoodsDetailResponseGoodsDetailsItem goodsDetailsItem =response.getGoodsDetailResponse().getGoodsDetails().get(0);
       //商品推广链接
        PddDdkGoodsPromotionUrlGenerateRequest requestUrl = new PddDdkGoodsPromotionUrlGenerateRequest();
        requestUrl.setPId(pid);
        List<Long> goodList = new ArrayList<Long>();
        goodList.add(supplierGoodsId);
        requestUrl.setGoodsIdList(goodsIdList);
        requestUrl.setMultiGroup(false);//true:双人团
        requestUrl.setCustomParameters(String.valueOf(memberId));
        requestUrl.setGenerateSchemaUrl(true);
        requestUrl.setGenerateShortUrl(true);
        PddDdkGoodsPromotionUrlGenerateResponse responseUrl = client.syncInvoke(requestUrl);
        PddDdkGoodsPromotionUrlGenerateResponse.GoodsPromotionUrlGenerateResponseGoodsPromotionUrlListItem url= responseUrl.getGoodsPromotionUrlGenerateResponse().getGoodsPromotionUrlList().get(0);
       //用作分享者和购买者使用
        GoodsShareLinkEntity shareLinkEntity = new GoodsShareLinkEntity();
        shareLinkEntity.setCreateTime((int) DateUtil.getUnixStamp());
        shareLinkEntity.setGoodsId(supplierGoodsId.toString());
        shareLinkEntity.setMemberId(memberId);
        shareLinkEntity.setUpdateTime((int)DateUtil.getUnixStamp());
        shareLinkEntity.setSupplierId(2);
        goodsShareLinkService.insert(shareLinkEntity);
        ShareGoodInfo shareGoodInfo=new ShareGoodInfo();
        shareGoodInfo.setGoodsShareLinkId(shareLinkEntity.getGoodsShareLinkId());
        shareGoodInfo.setGoodsDesc(goodsDetailsItem.getGoodsDesc());
        shareGoodInfo.setGoodsName(goodsDetailsItem.getGoodsName());
        shareGoodInfo.setGoodsThumbnailUrl(goodsDetailsItem.getGoodsThumbnailUrl());
        shareGoodInfo.setGoodsImageUrl(goodsDetailsItem.getGoodsImageUrl());
        shareGoodInfo.setGoodsGalleryUrls(goodsDetailsItem.getGoodsGalleryUrls());
        shareGoodInfo.setInviteCode(memberEntity.getInviteCode());
        shareGoodInfo.setSchemaUrl(url.getSchemaUrl());
        shareGoodInfo.setMobileShortUrl(url.getMobileShortUrl());
        shareGoodInfo.setShortUrl(url.getShortUrl());

        Long minGroupPrice=goodsDetailsItem.getMinGroupPrice();//最小拼团价（单位为分）
        Long couponDiscount=goodsDetailsItem.getCouponDiscount();//优惠券面额，单位为分
        Long juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
        Long promotionRate=goodsDetailsItem.getPromotionRate();//佣金比例，千分比
        BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
        BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
        BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
        shareGoodInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为分）
        shareGoodInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为分
        shareGoodInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为分
        shareGoodInfo.setPromotionRate(promotionRate);//佣金比例，千分比
        shareGoodInfo.setPromotionAmount(cc);//佣金金额
        return R.ok().put("shareGoodInfo",shareGoodInfo);
    }

   /**
   * @Description: 检查当前用户是否备案
   * @Author: Mr.Wang 
   * @Date: 2020/11/8 
   */
    @RequestMapping("checkBeiAn")
    public R checkBeiAn(HttpServletRequest req) throws Exception{
        int memberId= token(req);
        PddDdkMemberAuthorityQueryRequest request = new PddDdkMemberAuthorityQueryRequest();
        request.setPid(pid);
        request.setCustomParameters(String.valueOf(memberId));
        PddDdkMemberAuthorityQueryResponse response = client.syncInvoke(request);
        return R.ok().put("checkBeiAn",response.getAuthorityQueryResponse());
    }

    /**
     * @Description: 当前用户去备案
     * @Author: Mr.Wang 
     * @Date: 2020/11/8 
     */
    @RequestMapping("beiAn")
    public R beiAn(HttpServletRequest req) throws Exception{
        int memberId= token(req);
        PddDdkRpPromUrlGenerateRequest request = new PddDdkRpPromUrlGenerateRequest();
        request.setChannelType(10);
        request.setCustomParameters(String.valueOf(memberId));
        List<String> pIdList = new ArrayList<String>();
        pIdList.add(pid);
        request.setPIdList(pIdList);
        PddDdkRpPromUrlGenerateResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
        return R.ok().put("checkBeiAn",response.getRpPromotionUrlGenerateResponse());
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
