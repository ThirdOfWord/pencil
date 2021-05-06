package com.freeter.modules.pingtaiGoods;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.pingtaiGoods.entity.GoodsGoldRushEntity;
import com.freeter.modules.pingtaiGoods.entity.TaoBaoInfo;
import com.freeter.modules.pingtaiGoods.entity.TaoLiJin;
import com.freeter.modules.pingtaiGoods.service.GoodsGoldRushService;
import com.freeter.token.service.TokenService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.NTbkShop;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 淘宝
 */
@RestController
@RequestMapping("api/taobao")
public class TaobaoClientController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private CollectGoodsService collectGoodsService;
    @Autowired
    private GoodsGoldRushService goodsGoldRushService;

    private final static String url="http://gw.api.taobao.com/router/rest";
    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    private final static Long adzoneId=110507450350L;

    /**
     * @Description: 获取淘宝商品列表（物料id）
     * @Author: Mr.Wang 
     * @Date: 2020/5/27 
     */
    @RequestMapping("getTaoBaoList")
    public R getTaoBaoList(@RequestParam(value ="currentPage",defaultValue="1")Long currentPage,
                           @RequestParam(value ="materialId",defaultValue="32366")Long materialId) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(20L);
        req.setAdzoneId(adzoneId);
        req.setPageNo(currentPage);
        req.setMaterialId(materialId);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        try {
          rsp.getResultList().isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(499,"无商品数据");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<TaoBaoInfo> taoBaoInfoList=new ArrayList<>();
        try {
            for (TbkDgOptimusMaterialResponse.MapData list:rsp.getResultList()){
                TaoBaoInfo taoBaoInfo=new TaoBaoInfo();
                taoBaoInfo.setSupplierId(3);
                taoBaoInfo.setGoodsId(list.getItemId());
                taoBaoInfo.setGoodsName(list.getTitle());
                taoBaoInfo.setGoodsDesc(list.getItemDescription());
                taoBaoInfo.setGoodsThumbnailUrl(list.getWhiteImage());
                taoBaoInfo.setGoodsImageUrl(list.getPictUrl());
                taoBaoInfo.setGoodsGalleryUrls(list.getSmallImages());
                taoBaoInfo.setCategoryName(list.getCategoryName());
                taoBaoInfo.setMallName(list.getShopTitle());
                taoBaoInfo.setSalesTip(String.valueOf(list.getVolume()));
                taoBaoInfo.setMerchantType(list.getUserType().intValue());
                String couponEndTime=list.getCouponEndTime();
                if (org.apache.commons.lang.StringUtils.isNotEmpty(couponEndTime)){
                    long endTime=Long.parseLong(couponEndTime);
                    Long today=System.currentTimeMillis();
                    if (endTime >= today){
                        taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                    }else {
                        taoBaoInfo.setCouponShareUrl(list.getClickUrl());
                    }
                }else {
                    taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                }
                taoBaoInfo.setClickUrl(list.getClickUrl());

                BigDecimal minGroupPrice = new BigDecimal(0);//单价 元
                BigDecimal couponDiscount = new BigDecimal(0);//优惠券面额，单位为元
                BigDecimal promotionRate = new BigDecimal(0);//佣金比例，百分比
                try {
                    String zkFinalPrice= list.getZkFinalPrice() == null? "0":list.getZkFinalPrice();
                    long couponAmount= list.getCouponAmount() == null? 0:list.getCouponAmount();
                    String commissionRate= list.getCommissionRate() == null? "0":list.getCommissionRate();
                    minGroupPrice = new BigDecimal(zkFinalPrice);//单价 元
                    promotionRate = new BigDecimal(commissionRate);//佣金比例，百分比
                    couponDiscount = new BigDecimal(couponAmount);//优惠券面额，单位为元
                }catch (Exception e){
                   e.printStackTrace();
                }
                BigDecimal juanHouPrice = minGroupPrice.subtract(couponDiscount);//卷后价，单位为元

                BigDecimal bb=promotionRate.divide(new BigDecimal(100));
                BigDecimal cc=juanHouPrice.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                taoBaoInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为元）
                taoBaoInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为元
                taoBaoInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为元
                taoBaoInfo.setPromotionRate(promotionRate);//佣金比例，百分比
                taoBaoInfo.setPromotionAmount(cc);//佣金金额
                taoBaoInfoList.add(taoBaoInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok().put("goodList",taoBaoInfoList);
    }
    /**
    * @Description: 获取淘宝相似推荐商品列表（物料id）
    * @Author: Mr.Wang 
    * @Date: 2020/6/19 
    */
    @RequestMapping("getTaoBaoRecommendList")
    public R getTaoBaoRecommendList(@RequestParam(value ="currentPage",defaultValue="1")Long currentPage,
                           @RequestParam(value ="goodsId",defaultValue="0")Long goodsId) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(20L);
        req.setAdzoneId(adzoneId);
        req.setPageNo(currentPage);
        req.setMaterialId(13256L);
        req.setItemId(goodsId);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);

        try {
            rsp.getResultList().isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(499,"无商品数据");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<TaoBaoInfo> taoBaoInfoList=new ArrayList<>();
        try {
            for (TbkDgOptimusMaterialResponse.MapData list:rsp.getResultList()){
                TaoBaoInfo taoBaoInfo=new TaoBaoInfo();
                taoBaoInfo.setSupplierId(3);
                taoBaoInfo.setGoodsId(list.getItemId());
                taoBaoInfo.setGoodsName(list.getTitle());
                taoBaoInfo.setGoodsDesc(list.getItemDescription());
                taoBaoInfo.setGoodsThumbnailUrl(list.getWhiteImage());
                taoBaoInfo.setGoodsImageUrl(list.getPictUrl());
                taoBaoInfo.setGoodsGalleryUrls(list.getSmallImages());
                taoBaoInfo.setCategoryName(list.getCategoryName());
                taoBaoInfo.setMallName(list.getShopTitle());
                taoBaoInfo.setSalesTip(String.valueOf(list.getVolume()));
                taoBaoInfo.setMerchantType(list.getUserType().intValue());
                String couponEndTime=list.getCouponEndTime();
                if (org.apache.commons.lang.StringUtils.isNotEmpty(couponEndTime)){
                    long endTime=Long.parseLong(couponEndTime);
                    Long today=System.currentTimeMillis();
                    if (endTime >= today){
                        taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                    }else {
                        taoBaoInfo.setCouponShareUrl(list.getClickUrl());
                    }
                }else {
                    taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                }
                taoBaoInfo.setClickUrl(list.getClickUrl());

                BigDecimal minGroupPrice = new BigDecimal(0);//单价 元
                BigDecimal couponDiscount = new BigDecimal(0);//优惠券面额，单位为元
                BigDecimal promotionRate = new BigDecimal(0);//佣金比例，百分比
                try {
                    String zkFinalPrice= list.getZkFinalPrice() == null? "0":list.getZkFinalPrice();
                    long couponAmount= list.getCouponAmount() == null? 0:list.getCouponAmount();
                    String commissionRate= list.getCommissionRate() == null? "0":list.getCommissionRate();
                    minGroupPrice = new BigDecimal(zkFinalPrice);//单价 元
                    promotionRate = new BigDecimal(commissionRate);//佣金比例，百分比
                    couponDiscount = new BigDecimal(couponAmount);//优惠券面额，单位为元
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigDecimal juanHouPrice = minGroupPrice.subtract(couponDiscount);//卷后价，单位为元

                BigDecimal bb=promotionRate.divide(new BigDecimal(100));
                BigDecimal cc=juanHouPrice.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                taoBaoInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为元）
                taoBaoInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为元
                taoBaoInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为元
                taoBaoInfo.setPromotionRate(promotionRate);//佣金比例，百分比
                taoBaoInfo.setPromotionAmount(cc);//佣金金额
                taoBaoInfoList.add(taoBaoInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok().put("goodList",taoBaoInfoList);
    }

    /**
    * @Description: 猜你喜欢（物料id）
    * @Author: Mr.Wang 
    * @Date: 2020/9/18 
    */
    @RequestMapping("getLikeTaoBaoList")
    public R getLikeTaoBaoList(@RequestParam(value ="currentPage",defaultValue="1")Long currentPage,
                               @RequestParam(value ="deviceType",required = false)String deviceType,
                               @RequestParam(value ="deviceValue",required = false)String deviceValue,
                               @RequestParam(value ="materialId",defaultValue="6708")Long materialId) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        req.setPageSize(20L);
        req.setAdzoneId(adzoneId);
        req.setPageNo(currentPage);
        req.setMaterialId(materialId);
        req.setDeviceType(deviceType);
        req.setDeviceValue(deviceValue);
        TbkDgOptimusMaterialResponse rsp = client.execute(req);
        try {
            rsp.getResultList().isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(499,"无商品数据");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<TaoBaoInfo> taoBaoInfoList=new ArrayList<>();
        try {
            for (TbkDgOptimusMaterialResponse.MapData list:rsp.getResultList()){
                TaoBaoInfo taoBaoInfo=new TaoBaoInfo();
                taoBaoInfo.setSupplierId(3);
                taoBaoInfo.setGoodsId(list.getItemId());
                taoBaoInfo.setGoodsName(list.getTitle());
                taoBaoInfo.setGoodsDesc(list.getItemDescription());
                taoBaoInfo.setGoodsThumbnailUrl(list.getWhiteImage());
                taoBaoInfo.setGoodsImageUrl(list.getPictUrl());
                taoBaoInfo.setGoodsGalleryUrls(list.getSmallImages());
                taoBaoInfo.setCategoryName(list.getCategoryName());
                taoBaoInfo.setMallName(list.getShopTitle());
                taoBaoInfo.setSalesTip(String.valueOf(list.getVolume()));
                taoBaoInfo.setMerchantType(list.getUserType().intValue());
                String couponEndTime=list.getCouponEndTime();
                if (org.apache.commons.lang.StringUtils.isNotEmpty(couponEndTime)){
                    long endTime=Long.parseLong(couponEndTime);
                    Long today=System.currentTimeMillis();
                    if (endTime >= today){
                        taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                    }else {
                        taoBaoInfo.setCouponShareUrl(list.getClickUrl());
                    }
                }else {
                    taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                }
                taoBaoInfo.setClickUrl(list.getClickUrl());

                BigDecimal minGroupPrice = new BigDecimal(0);//单价 元
                BigDecimal couponDiscount = new BigDecimal(0);//优惠券面额，单位为元
                BigDecimal promotionRate = new BigDecimal(0);//佣金比例，百分比
                try {
                    String zkFinalPrice= list.getZkFinalPrice() == null? "0":list.getZkFinalPrice();
                    long couponAmount= list.getCouponAmount() == null? 0:list.getCouponAmount();
                    String commissionRate= list.getCommissionRate() == null? "0":list.getCommissionRate();
                    minGroupPrice = new BigDecimal(zkFinalPrice);//单价 元
                    promotionRate = new BigDecimal(commissionRate);//佣金比例，百分比
                    couponDiscount = new BigDecimal(couponAmount);//优惠券面额，单位为元
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigDecimal juanHouPrice = minGroupPrice.subtract(couponDiscount);//卷后价，单位为元

                BigDecimal bb=promotionRate.divide(new BigDecimal(100));
                BigDecimal cc=juanHouPrice.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                taoBaoInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为元）
                taoBaoInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为元
                taoBaoInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为元
                taoBaoInfo.setPromotionRate(promotionRate);//佣金比例，百分比
                taoBaoInfo.setPromotionAmount(cc);//佣金金额
                taoBaoInfoList.add(taoBaoInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok().put("goodList",taoBaoInfoList);
    }

    /**
    * @Description: 获取淘宝商品列表（分类id）
    * @Author: Mr.Wang 
    * @Date: 2020/6/15 
    */
    @RequestMapping("getTaoBaoListClasslfy")
    public R getTaoBaoListClasslfy(@RequestParam(value ="currentPage",defaultValue="1")Long currentPage,
                                   @RequestParam(value = "cat",required = false)String cat,
                                   @RequestParam(value = "sort",required = false)String sort) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(20L);
        req.setPageNo(currentPage);
        req.setPlatform(2L);
        req.setHasCoupon(false);
        req.setAdzoneId(adzoneId);
        req.setMaterialId(17004L);
        if (org.apache.commons.lang.StringUtils.isNotEmpty(cat)){
            req.setCat(cat);
        }
        if (org.apache.commons.lang.StringUtils.isNotEmpty(sort)){
            req.setSort(sort);
        }
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        try {
            rsp.getResultList().isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(499,"无商品数据");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<TaoBaoInfo> taoBaoInfoList=new ArrayList<>();
        try {
            for (TbkDgMaterialOptionalResponse.MapData list:rsp.getResultList()){
                TaoBaoInfo taoBaoInfo=new TaoBaoInfo();
                taoBaoInfo.setSupplierId(3);
                taoBaoInfo.setGoodsId(list.getItemId());
                taoBaoInfo.setGoodsName(list.getTitle());
                taoBaoInfo.setGoodsDesc(list.getItemDescription());
                taoBaoInfo.setGoodsThumbnailUrl(list.getWhiteImage());
                taoBaoInfo.setGoodsImageUrl(list.getPictUrl());
                taoBaoInfo.setGoodsGalleryUrls(list.getSmallImages());
                taoBaoInfo.setCategoryName(list.getCategoryName());
                taoBaoInfo.setMallName(list.getShopTitle());
                taoBaoInfo.setSalesTip(String.valueOf(list.getVolume()));
                taoBaoInfo.setMerchantType(list.getUserType().intValue());
                String couponEndTime=list.getCouponEndTime();
                if (org.apache.commons.lang.StringUtils.isNotEmpty(couponEndTime)){
                    boolean time = DateUtil.belongCalendarBefore(couponEndTime);
                    if (time){
                        taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                    }else {
                        taoBaoInfo.setCouponShareUrl(list.getUrl());
                    }
                }else {
                    taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                }
                taoBaoInfo.setClickUrl(list.getUrl());

                BigDecimal minGroupPrice = new BigDecimal(0);//单价 元
                BigDecimal couponDiscount = new BigDecimal(0);//优惠券面额，单位为元
                BigDecimal promotionRate = new BigDecimal(0);//佣金比例，百分比
                try {
                    String zkFinalPrice= list.getZkFinalPrice() == null? "0":list.getZkFinalPrice();
                    String couponAmount= list.getCouponAmount() == null? "0":list.getCouponAmount();
                    String commissionRate= list.getCommissionRate() == null? "0":list.getCommissionRate();
                    minGroupPrice = new BigDecimal(zkFinalPrice);//单价 元
                    promotionRate = new BigDecimal(commissionRate).divide(new BigDecimal(100));//佣金比例，百分比
                    couponDiscount = new BigDecimal(couponAmount);//优惠券面额，单位为元
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigDecimal juanHouPrice = minGroupPrice.subtract(couponDiscount);//卷后价，单位为元
                BigDecimal bb=promotionRate.divide(new BigDecimal(100));
                BigDecimal cc=juanHouPrice.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                taoBaoInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为元）
                taoBaoInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为元
                taoBaoInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为元
                taoBaoInfo.setPromotionRate(promotionRate);//佣金比例，百分比
                taoBaoInfo.setPromotionAmount(cc);//佣金金额
                taoBaoInfoList.add(taoBaoInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok().put("goodList",taoBaoInfoList);
    }
    /**
    * @Description: 搜索商品
    * @Author: Mr.Wang 
    * @Date: 2020/5/27 
    */
    @RequestMapping("getTaoBaoSearch")
    public R getTaoBaoSearch(@RequestParam(value ="currentPage",defaultValue="1")Long currentPage,
                             @RequestParam(value = "title",required = false)String title) throws Exception {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(20L);
        req.setPageNo(currentPage);
        req.setPlatform(2L);
        req.setHasCoupon(false);
        req.setAdzoneId(adzoneId);
        req.setMaterialId(17004L);
        req.setQ(title);
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        try {
            rsp.getResultList().isEmpty();
        }catch (Exception e){
            e.printStackTrace();
            return R.error(499,"无商品数据");
        }
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        List<TaoBaoInfo> taoBaoInfoList=new ArrayList<>();
        try {
            for (TbkDgMaterialOptionalResponse.MapData list:rsp.getResultList()){
                TaoBaoInfo taoBaoInfo=new TaoBaoInfo();
                taoBaoInfo.setSupplierId(3);
                taoBaoInfo.setGoodsId(list.getItemId());
                taoBaoInfo.setGoodsName(list.getTitle());
                taoBaoInfo.setGoodsDesc(list.getItemDescription());
                taoBaoInfo.setGoodsThumbnailUrl(list.getWhiteImage());
                taoBaoInfo.setGoodsImageUrl(list.getPictUrl());
                taoBaoInfo.setGoodsGalleryUrls(list.getSmallImages());
                taoBaoInfo.setCategoryName(list.getCategoryName());
                taoBaoInfo.setMallName(list.getShopTitle());
                taoBaoInfo.setSalesTip(String.valueOf(list.getVolume()));
                taoBaoInfo.setMerchantType(list.getUserType().intValue());

                String couponEndTime=list.getCouponEndTime();
                if (org.apache.commons.lang.StringUtils.isNotEmpty(couponEndTime)){
                    boolean time = DateUtil.belongCalendarBefore(couponEndTime);
                    if (time){
                        taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                    }else {
                        taoBaoInfo.setCouponShareUrl(list.getUrl());
                    }
                }else {
                    taoBaoInfo.setCouponShareUrl(list.getCouponShareUrl());
                }
                taoBaoInfo.setClickUrl(list.getUrl());

                BigDecimal minGroupPrice = new BigDecimal(0);//单价 元
                BigDecimal couponDiscount = new BigDecimal(0);//优惠券面额，单位为元
                BigDecimal promotionRate = new BigDecimal(0);//佣金比例，百分比
                try {
                    String zkFinalPrice= list.getZkFinalPrice() == null? "0":list.getZkFinalPrice();
                    String couponAmount= list.getCouponAmount() == null? "0":list.getCouponAmount();
                    String commissionRate= list.getCommissionRate() == null? "0":list.getCommissionRate();
                    minGroupPrice = new BigDecimal(zkFinalPrice);//单价 元
                    promotionRate = new BigDecimal(commissionRate).divide(new BigDecimal(100));//佣金比例，百分比
                    couponDiscount = new BigDecimal(couponAmount);//优惠券面额，单位为元
                }catch (Exception e){
                    e.printStackTrace();
                }
                BigDecimal juanHouPrice = minGroupPrice.subtract(couponDiscount);//卷后价，单位为元
                BigDecimal bb=promotionRate.divide(new BigDecimal(100));
                BigDecimal cc=juanHouPrice.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                taoBaoInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为元）
                taoBaoInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为元
                taoBaoInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为元
                taoBaoInfo.setPromotionRate(promotionRate);//佣金比例，百分比
                taoBaoInfo.setPromotionAmount(cc);//佣金金额
                taoBaoInfoList.add(taoBaoInfo);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.ok().put("goodList",taoBaoInfoList);
    }
    /**
     *  淘宝客-公用-淘宝客商品详情查询(淘宝)
     * @author wangkui
     * @date 2020/3/11 14:28
     * @param[supplierGoodsId]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("getInfo")
    public R getInfo(@RequestParam(value ="supplierGoodsId",defaultValue="0")String supplierGoodsId,
                     @RequestParam(value ="shareUrl",required = false)String shareUrl,HttpServletRequest request) throws Exception{
        String ipAddress=getIpAddress(request);
        Map<String,Object> map=new HashMap<>();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids(supplierGoodsId);
        req.setPlatform(2L);
        req.setIp(ipAddress);
        TbkItemInfoGetResponse rsp = client.execute(req);
        try {
            String nick=rsp.getResults().get(0).getNick();
            Long sellerId=rsp.getResults().get(0).getSellerId();
            String shopUrl = getShop(nick,sellerId);
            map.put("shopUrl", shopUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            String text=rsp.getResults().get(0).getTitle();
            String model=getTaoKouLing(text,shareUrl);
            map.put("model", model);
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("taobao", JSONObject.parseObject( rsp.getBody()));
        //判断当前商品用户是否收藏过
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(org.apache.commons.lang.StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        //token为空
        if(org.apache.commons.lang.StringUtils.isBlank(token)){
            map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
        }else {
            //查询token信息
            TokenEntity tokenEntity = tokenService.queryByToken(token);
            if (tokenEntity == null || tokenEntity.getIsExpire() == 1) {
                map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
            } else {
                //去收藏表查询
                EntityWrapper<CollectGoodsEntity> entityWrapper = new EntityWrapper();
                entityWrapper.eq("member_id", tokenEntity.getMemberId().intValue());
                entityWrapper.eq("goods_id", Long.parseLong(supplierGoodsId));
                entityWrapper.eq("supplier_id", 3);
                CollectGoodsEntity collectGoodsEntity = collectGoodsService.selectOne(entityWrapper);
                if (null == collectGoodsEntity) {
                    map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
                } else {
                    map.put("collectStatus", 1);//收藏当前商品状态--0：未收藏，1：已收藏
                }
            }
        }
        return R.ok(map);
    }
    //获取店铺
    public String getShop(String nick,Long sellerId) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkShopGetRequest req = new TbkShopGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
        req.setQ(nick);
        req.setPageNo(1L);
        req.setPageSize(20L);
        TbkShopGetResponse rsp = client.execute(req);
        if (!rsp.getResults().isEmpty()){
            List<NTbkShop> list =rsp.getResults();
            for (NTbkShop shop:list){
                if (shop.getUserId().equals(sellerId)){
                    return shop.getPictUrl();
                }

            }
        }
        return null;
    }
    //获取淘口令
    public String getTaoKouLing(String text,String shareUrl) throws Exception{
        if (StringUtils.isEmpty(shareUrl)){
            return null;
        }
        shareUrl="https:"+shareUrl;
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText(text);
        req.setUrl(shareUrl);
        TbkTpwdCreateResponse rsp = client.execute(req);
        return rsp.getData().getModel();
    }
    /**
     * 淘宝客-公用-淘宝客商品详情查询(天猫)
     * @param supplierGoodsId
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("getTianMaoInfo")
    public R getTianMaoInfo(@RequestParam(value ="supplierGoodsId",defaultValue="0")String supplierGoodsId,
                            @RequestParam(value ="shareUrl",required = false)String shareUrl,HttpServletRequest request) throws Exception{
        String ipAddress=getIpAddress(request);
        Map<String,Object> map=new HashMap<>();
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids(supplierGoodsId);
        req.setPlatform(2L);
        req.setIp(ipAddress);
        TbkItemInfoGetResponse rsp = client.execute(req);
        try {
            String nick=rsp.getResults().get(0).getNick();
            Long sellerId=rsp.getResults().get(0).getSellerId();
            String shopUrl = getShop(nick,sellerId);
            map.put("shopUrl", shopUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            String text=rsp.getResults().get(0).getTitle();
            String model=getTaoKouLing(text,shareUrl);
            map.put("model", model);
        }catch (Exception e){
            e.printStackTrace();
        }
        map.put("taobao", JSONObject.parseObject( rsp.getBody()));
        //判断当前商品用户是否收藏过
        //从header中获取token
        String token = request.getHeader("token");
        //如果header中不存在token，则从参数中获取token
        if(org.apache.commons.lang.StringUtils.isBlank(token)){
            token = request.getParameter("token");
        }
        //token为空
        if(org.apache.commons.lang.StringUtils.isBlank(token)){
            map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
        }else {
            //查询token信息
            TokenEntity tokenEntity = tokenService.queryByToken(token);
            if (tokenEntity == null || tokenEntity.getIsExpire() == 1) {
                map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
            } else {
                //去收藏表查询
                EntityWrapper<CollectGoodsEntity> entityWrapper = new EntityWrapper();
                entityWrapper.eq("member_id", tokenEntity.getMemberId().intValue());
                entityWrapper.eq("goods_id", Long.parseLong(supplierGoodsId));
                CollectGoodsEntity collectGoodsEntity = collectGoodsService.selectOne(entityWrapper);
                if (null == collectGoodsEntity) {
                    map.put("collectStatus", 0);//收藏当前商品状态--0：未收藏，1：已收藏
                } else {
                    map.put("collectStatus", 1);//收藏当前商品状态--0：未收藏，1：已收藏
                }
            }
        }
        return R.ok(map);
    }

    /**
    * @Description: 创建淘礼金 
    * @Param:  
    * @return:  
    * @Author: Mr.Wang 
    * @Date: 2020/8/3 
    */
    @RequestMapping("addGoldRush")
    public R addGoldRush(TaoLiJin taoLiJin) throws Exception{
        synchronized (this){
            String postId = taoLiJin.getPostId();
            String postCode = taoLiJin.getPostCode();
            if (org.apache.commons.lang3.StringUtils.isEmpty(postId) || org.apache.commons.lang3.StringUtils.isEmpty(postCode)){
                return R.error("postId为空或者postCode为空");
            }
            EntityWrapper<GoodsGoldRushEntity> goodsGoldRushWrapper = new EntityWrapper<>();
            goodsGoldRushWrapper.eq("goods_gold_rush_id", postId);
            goodsGoldRushWrapper.eq("post_code", postCode);
            GoodsGoldRushEntity goodsGoldRushEntity= goodsGoldRushService.selectOne(goodsGoldRushWrapper);
            if (null == goodsGoldRushEntity){
                return R.error("请求验证失败");
            }

            String text = taoLiJin.getText();
            if (org.apache.commons.lang.StringUtils.isEmpty(text) || text.length() <6){
                return R.error("口令弹框内容长度要大于5个字符");
            }
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            TbkDgVegasTljCreateRequest req = new TbkDgVegasTljCreateRequest();
            req.setAdzoneId(taoLiJin.getAdzoneId());
            req.setItemId(taoLiJin.getItemId());
            req.setTotalNum(taoLiJin.getTotalNum());
            req.setName(taoLiJin.getName());
            req.setUserTotalWinNumLimit(taoLiJin.getUserTotalWinNumLimit());
            req.setSecuritySwitch(true);
            req.setPerFace(taoLiJin.getPerFace());
            req.setSendStartTime(StringUtils.parseDateTime(taoLiJin.getSendStartTime()));
            if (org.apache.commons.lang3.StringUtils.isNotEmpty(taoLiJin.getSendEndTime())){
                req.setSendEndTime(StringUtils.parseDateTime(taoLiJin.getSendEndTime()));
            }
            req.setUseEndTime(taoLiJin.getUseEndTime());
            req.setUseEndTimeMode(taoLiJin.getUseEndTimeMode());
            TbkDgVegasTljCreateResponse rsp = client.execute(req);
            Map<String,Object> map = new HashMap<>();
            //生成淘口令
            try {
                String sendUrl = rsp.getResult().getModel().getSendUrl();
                String model = taoKouLing(text,sendUrl);
                map.put("model",model);
                map.put("list",rsp.getResult().getModel());
            }catch (Exception e){
                e.printStackTrace();
                return R.error(rsp.getBody());
            }
            goodsGoldRushEntity.setPostCode(0);
            goodsGoldRushService.updateAllColumnById(goodsGoldRushEntity);
            return R.ok(map);
        }
    }



    public String taoKouLing(String text,String sendUrl){
        try {
            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
            req.setText(text);
            req.setUrl(sendUrl);
            //req.setLogo(sendUrl);
            TbkTpwdCreateResponse rsp = client.execute(req);
            return rsp.getData().getModel();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
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
