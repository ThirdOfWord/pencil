package com.freeter.modules.pingtaiGoods;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.JingDongUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.good.entity.CollectGoodsEntity;
import com.freeter.modules.good.entity.MemberCollectGoodsEntity;
import com.freeter.modules.good.service.CollectGoodsService;
import com.freeter.modules.pingtaiGoods.entity.GoodInfo;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import com.jd.open.api.sdk.internal.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName HttpUrlJingDongController 喵有劵京东商品
 * @Author WangKui
 * @Date 2020/6/3 19:25
 * @Version 1.0
 **/
@RestController
@RequestMapping("api/jdGoods")
public class HttpUrlJingDongController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private CollectGoodsService collectGoodsService;

    private final String apkey ="98be1408-a6d2-9406-f4e8-143f89074539";
    private final String keyId ="J4697115035309567";

    private final static String SERVER_URL = "https://api.jd.com/routerjson";
    private static  String accessToken = "cd2c4e6f02e6436bb4d0e51b60c2c42erlnm";
    private final static  String appKey = "97d9a36db2d6f14fd3a5030e5231b221";
    private final static  String appSecret = "9cc6f49ae6cd4715b030bd28fc2e12c9";
    private final static  String APPID = "4000176845";
    private final static  Long pid =3002489551L;
    //private final static  String code = "zn8664";
    /**
    * @Description: 喵有劵京东商品列表
    * @Author: Mr.Wang 
    * @Date: 2020/6/3 
    */
    @RequestMapping("list")
    private R getList(@RequestParam(value ="currentPage",defaultValue="1")int currentPage) throws Exception {
        String url = "http://api.web.21ds.cn/jingdong/getJdUnionItems?apkey="+apkey+"&pageIndex="+currentPage+"&pageSize=20&cid1=0";
        String response= JingDongUtil.sendGet(url);
        //打印结果
        JSONObject json = JSONObject.fromObject(response);
        String code=json.getString("code");
        if (StringUtils.equals(code, "200")){
            Double min=0.47956456;
            Double max=0.47999999;
            BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
            BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );

            List<GoodInfo> popInfoList=new ArrayList<>();
            JSONObject data = JSONObject.fromObject(json.getString("data"));
            JSONArray list = JSONArray.fromObject(data.getString("list"));
            if(list.size()>0){
                for(int i=0;i<list.size();i++){
                    GoodInfo goodInfo=new GoodInfo();
                    JSONObject good = list.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    goodInfo.setGoodsId(good.getLong("skuId"));
                    goodInfo.setSupplierId(1);
                    goodInfo.setGoodsImageUrl(good.getString("materialUrl"));//商品链接
                    JSONObject categoryInfo = JSONObject.fromObject(good.getString("categoryInfo"));//类目信息
                    goodInfo.setCategoryName(categoryInfo.getString("cid1Name"));
                    JSONObject imageInfo = JSONObject.fromObject(good.getString("imageInfo"));//图片信息
                    JSONArray imageList = JSONArray.fromObject(imageInfo.getString("imageList"));//图片信息
                    List<String> goodsGalleryUrls=new ArrayList<>();
                    if (!imageList.isEmpty()){
                        for (int j=0;j<imageList.size();j++){
                            JSONObject image = imageList.getJSONObject(j);//图片列表
                            goodsGalleryUrls.add(image.getString("url"));//图片url
                        }
                    }
                    goodInfo.setSalesTip(good.getString("inOrderCount30Days"));
                    goodInfo.setGoodsGalleryUrls(goodsGalleryUrls);
                    JSONObject shopInfo = JSONObject.fromObject(good.getString("shopInfo"));//店铺信息
                    goodInfo.setMallName(shopInfo.getString("shopName"));
                    goodInfo.setGoodsName(good.getString("skuName"));
                    JSONObject priceInfo = JSONObject.fromObject(good.getString("priceInfo"));//价格信息
                    Long lowestPrice = priceInfo.getLong("lowestPrice");//	最低价格
                    JSONObject commissionInfo = JSONObject.fromObject(good.getString("commissionInfo"));//佣金信息
                    Long commissionShare = commissionInfo.getLong("commissionShare");//佣金比例 百分比
                    JSONObject couponInfo = JSONObject.fromObject(good.getString("couponInfo"));//优惠券信息，返回内容为空说明该SKU无可用优惠券
                    JSONArray couponList = JSONArray.fromObject(couponInfo.getString("couponList"));//优惠券明细
                    Long discount=0L;
                    if (!couponList.isEmpty()){
                        JSONObject coupon =  couponList.getJSONObject(0);
                        discount = coupon.getLong("discount");//	券面额
                    }
                    Long minGroupPrice=lowestPrice*100;//最小拼团价（单位为分）
                    Long couponDiscount=discount*100;//优惠券面额，单位为分
                    Long juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
                    Long promotionRate=commissionShare*10;//佣金比例，千分比
                    BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
                    BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
                    BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//计算过得佣金

                    goodInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为分）
                    goodInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为分
                    goodInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为分
                    goodInfo.setPromotionRate(promotionRate);//佣金比例，千分比
                    goodInfo.setPromotionAmount(cc);//佣金金额
                    popInfoList.add(goodInfo);
                }
            }
            return R.ok().put("JDlist",popInfoList);
        }
        return R.error(499, "获取数据异常");
    }
    /**
    * @Description: 喵有劵京东搜索商品列表 
    * @Author: Mr.Wang 
    * @Date: 2020/6/4 
    */
    @RequestMapping("search")
    private R getsearch(@RequestParam(value ="currentPage",defaultValue="1")int currentPage,
                        @RequestParam(value = "title",required = false)String title) throws Exception {
        if (StringUtils.isEmpty(title)){
            return R.error(499, "搜索内容为空");
        }
        if (title.length()>100){
            return R.error(499, "搜索内容过长");
        }
        String url = "http://api.web.21ds.cn/jingdong/getJdUnionItems?apkey="+apkey+"&pageIndex="+currentPage+"&pageSize=20&keyword="+ URLEncoder.encode(title,"utf-8");
        String response= JingDongUtil.sendGet(url);
        //打印结果
        JSONObject json = JSONObject.fromObject(response);
        String code=json.getString("code");
        if (StringUtils.equals(code, "200")){
            Double min=0.47956456;
            Double max=0.47999999;
            BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
            BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );

            List<GoodInfo> popInfoList=new ArrayList<>();
            JSONObject data = JSONObject.fromObject(json.getString("data"));
            JSONArray list = JSONArray.fromObject(data.getString("list"));
            if(list.size()>0){
                for(int i=0;i<list.size();i++){
                    GoodInfo goodInfo=new GoodInfo();
                    JSONObject good = list.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    goodInfo.setGoodsId(good.getLong("skuId"));
                    goodInfo.setSupplierId(1);
                    goodInfo.setGoodsImageUrl(good.getString("materialUrl"));//商品链接
                    JSONObject categoryInfo = JSONObject.fromObject(good.getString("categoryInfo"));//类目信息
                    goodInfo.setCategoryName(categoryInfo.getString("cid1Name"));
                    JSONObject imageInfo = JSONObject.fromObject(good.getString("imageInfo"));//图片信息
                    JSONArray imageList = JSONArray.fromObject(imageInfo.getString("imageList"));//图片信息
                    List<String> goodsGalleryUrls=new ArrayList<>();
                    if (!imageList.isEmpty()){
                        for (int j=0;j<imageList.size();j++){
                            JSONObject image = imageList.getJSONObject(j);//图片列表
                            goodsGalleryUrls.add(image.getString("url"));//图片url
                        }
                    }
                    goodInfo.setSalesTip(good.getString("inOrderCount30Days"));
                    goodInfo.setGoodsGalleryUrls(goodsGalleryUrls);
                    JSONObject shopInfo = JSONObject.fromObject(good.getString("shopInfo"));//店铺信息
                    goodInfo.setMallName(shopInfo.getString("shopName"));
                    goodInfo.setGoodsName(good.getString("skuName"));
                    JSONObject priceInfo = JSONObject.fromObject(good.getString("priceInfo"));//价格信息
                    Long lowestPrice = priceInfo.getLong("lowestPrice");//	最低价格
                    JSONObject commissionInfo = JSONObject.fromObject(good.getString("commissionInfo"));//佣金信息
                    Long commissionShare = commissionInfo.getLong("commissionShare");//佣金比例 百分比
                    JSONObject couponInfo = JSONObject.fromObject(good.getString("couponInfo"));//优惠券信息，返回内容为空说明该SKU无可用优惠券
                    JSONArray couponList = JSONArray.fromObject(couponInfo.getString("couponList"));//优惠券明细
                    Long discount=0L;
                    if (!couponList.isEmpty()){
                        JSONObject coupon =  couponList.getJSONObject(0);
                        discount = coupon.getLong("discount");//	券面额
                    }
                    Long minGroupPrice=lowestPrice*100;//最小拼团价（单位为分）
                    Long couponDiscount=discount*100;//优惠券面额，单位为分
                    Long juanHouPrice=minGroupPrice-couponDiscount;//卷后价，单位为分
                    Long promotionRate=commissionShare*10;//佣金比例，千分比
                    BigDecimal aa=new BigDecimal(juanHouPrice).divide(new BigDecimal(100));
                    BigDecimal bb=new BigDecimal(promotionRate).divide(new BigDecimal(1000));
                    BigDecimal cc=aa.multiply(bb).multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//计算过得佣金

                    goodInfo.setMinGroupPrice(minGroupPrice);//最小拼团价（单位为分）
                    goodInfo.setCouponDiscount(couponDiscount);//优惠券面额，单位为分
                    goodInfo.setJuanHouPrice(juanHouPrice);//卷后价，单位为分
                    goodInfo.setPromotionRate(promotionRate);//佣金比例，千分比
                    goodInfo.setPromotionAmount(cc);//佣金金额
                    popInfoList.add(goodInfo);
                }
            }
            return R.ok().put("goodsList",popInfoList);
        }
        return R.error(499, "无数据");
    }

    /**
    * @Description: 喵有劵京东商品详情页
    * @Author: Mr.Wang 
    * @Date: 2020/6/4 
    */
    @RequestMapping("getInfo")
    public R getInfo(@RequestParam(value ="supplierGoodsId",defaultValue="0")Long supplierGoodsId, HttpServletRequest req) throws Exception{
        if (supplierGoodsId==0){
            return R.error(499,"参数错误");
        }
        String url = " http://api.web.21ds.cn/jingdong/getItemInfo?apkey="+apkey+"&skuid="+supplierGoodsId;
        String response= JingDongUtil.sendGet(url);
        //打印结果
        //JSONObject json = JSONObject.fromObject(response);
        Map<String,Object> map=new HashMap<>();
        map.put("jdInfo", response);
        //判断当前商品用户是否收藏过
        String token = req.getHeader("token");
        if(StringUtils.isBlank(token)){
            token = req.getParameter("token");
        }
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
                entityWrapper.eq("goods_id", supplierGoodsId);
                entityWrapper.eq("supplier_id", 1);
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
    /**
    * @Description: 京东联盟 优惠卷和转链接二合一
    * @Author: Mr.Wang 
    * @Date: 2020/6/7 
    */
    @RequestMapping("url")
    public R getUrl(@RequestParam(value ="materialUrl",required = false)String materialUrl,
                    @RequestParam(value ="couponUrl",required = false)String couponUrl, HttpServletRequest req) throws Exception{
     if (StringUtils.isEmpty(materialUrl)){
         return R.error(499, "参数错误");
     }
     int memberId=token(req);
     String entity=null;
     if (StringUtils.isEmpty(couponUrl)){
         entity="{\"promotionCodeReq\":{\"materialId\":\""+materialUrl+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"positionId\":"+pid+"}}";
     }else {
         String coupon = URLDecoder.decode(couponUrl, "UTF-8");
         entity="{\"promotionCodeReq\":{\"materialId\":\""+materialUrl+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"couponUrl\":\""+coupon+"\",\"positionId\":"+pid+"}}";
     }
        //设置参数
        Map<String, String> map = new TreeMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
        map.put("timestamp", sdf.format(new Date()));
        map.put("v", "1.0");
        map.put("sign_method", "md5");
        map.put("format", "json");
        map.put("method", "jd.union.open.promotion.common.get");
        map.put("360buy_param_json", entity);
        map.put("access_token", accessToken);
        map.put("app_key", appKey);
        StringBuilder sb = new StringBuilder(appSecret);//应规则首尾加上appSecret
        for (Map.Entry entry : map.entrySet()) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();
            //检测参数是否为空
            //检测参数是否为空
            if (StringUtil.areNotEmpty(new String[]{name, value})) {
                sb.append(name).append(value);
            }
        }
        sb.append(appSecret);//应规则首尾加上appSecret
        //生成签名
        String encode =JingDongUtil.encryptWithMD5(sb.toString(),"utf-8");
        encode=encode.toUpperCase();//字母转大写
        if (StringUtils.isNotEmpty(couponUrl)){
            //couponUrl = URLEncoder.encode(couponUrl, "UTF-8");
            entity="{\"promotionCodeReq\":{\"materialId\":\""+materialUrl+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"couponUrl\":\""+couponUrl+"\",\"positionId\":"+pid+"}}";
        }
        String prarm = "sign="+encode+"&timestamp="+map.get("timestamp")+"&v=1.0"+
                "&sign_method=md5&format=json&method=jd.union.open.promotion.common.get"+
                "&360buy_param_json="+entity+
                "&access_token="+accessToken+"&app_key="+appKey;
        String result =JingDongUtil.sendPost(SERVER_URL, prarm);
        try {
            JSONObject response =JSONObject.fromObject(result);
            JSONObject jd_union_open_promotion_common_get_responce=(JSONObject)response.get("jd_union_open_promotion_common_get_responce");
            JSONObject getResult=(JSONObject)jd_union_open_promotion_common_get_responce.get("getResult");
            JSONObject data=(JSONObject)getResult.get("data");
            return R.ok(data);
        }catch (Exception e){
            return R.error(499, "获取数据异常");
        }
    }

    /**
    * @Description: 喵有劵京东获取商品推广链接
    * @Author: Mr.Wang 
    * @Date: 2020/7/2 
    */
    @RequestMapping("httpUrl")
    public R httpUrl(@RequestParam(value ="materialUrl",required = false)String materialUrl,
                    @RequestParam(value ="couponUrl",required = false)String couponUrl, HttpServletRequest req) throws Exception{
        if (StringUtils.isEmpty(materialUrl)){
            return R.error(499, "参数错误");
        }
        String url="";
        if (StringUtils.isEmpty(couponUrl)){
            url = "http://api.web.21ds.cn/jingdong/doItemCpsUrl?apkey="+apkey+"&materialId="+materialUrl+"&key_id="+keyId+"&autoSearch=1";
        }else {
            url = "http://api.web.21ds.cn/jingdong/doItemCpsUrl?apkey="+apkey+"&materialId="+materialUrl+"&key_id="+keyId+"&couponUrl="+couponUrl;
        }
        String response= JingDongUtil.sendGet(url);
        return R.ok().put("jdUrl",response);
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
