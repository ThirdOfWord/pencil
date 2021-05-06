package pingtaiGoods;

import com.freeter.common.util.MD5Util;
import com.freeter.modules.pingtaiGoods.entity.GoodInfo;

import com.freeter.modules.pingtaiGoods.entity.ShareGoodInfo;
import com.jd.open.api.sdk.internal.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.jd.open.api.sdk.internal.util.StringUtil.areNotEmpty;

/**京东
 * @Auther: hao
 * @Date: 2020/3/20 11:13
 * @Description:
 */
public class JingdongDemo {
     /*public static void main(String[] args) throws Exception{
        //设置参数
        Map<String, String> map = new TreeMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间格式
        String SERVER_URL = "https://api.jd.com/routerjson";
        String appKey = "97d9a36db2d6f14fd3a5030e5231b221";
        String accessToken = "cd2c4e6f02e6436bb4d0e51b60c2c42erlnm";
        String appSecret = "9cc6f49ae6cd4715b030bd28fc2e12c9";
        String materialId = "https://item.jd.com/67602758108.html";
        Long pid =3002489551L;
        String memberId = "2";
        map.put("timestamp", sdf.format(new Date()));
        map.put("v", "1.0");
        map.put("sign_method", "md5");
        map.put("format", "json");
        map.put("method", "jd.union.open.promotion.common.get");
        //String entity="{\"promotionCodeReq\":{\"materialId\":\"https://item.jd.com/67602758108.html\",\"siteId\":\"4000176845\",\"ext1\":\"2\",\"couponUrl\":\"http://coupon.m.jd.com/coupons/show.action?key=845684b4374544bb8938529758eb3c9b&roleId=32453480&to=mall.jd.com/index-10192028.html\"}}";
        String couponUrl="http://coupon.m.jd.com/coupons/show.action?key=845684b4374544bb8938529758eb3c9b&roleId=32453480&to=mall.jd.com/index-10192028.html";
         //couponUrl = URLEncoder.encode(couponUrl, "UTF-8");
         String entity="{\"promotionCodeReq\":{\"materialId\":\""+materialId+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"couponUrl\":\""+couponUrl+"\",\"positionId\":"+pid+"}}";
        // entity=URLEncoder.encode(entity, "utf-8");
        //param_json为空的时候需要写成 "{}"
        //map.put("360buy_param_json", "{\"promotionCodeReq\":{\"materialId\":\"https://item.jd.com/68498414276.html\",\"siteId\":\"4000176845\",\"ext1\":\"2\"}}");
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
        String encode = encryptWithMD5(sb.toString(),"utf-8");
         encode=encode.toUpperCase();
        couponUrl = URLEncoder.encode(couponUrl, "UTF-8");
       // entity="{\"promotionCodeReq\":{\"materialId\":\""+materialId+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"couponUrl\":\""+couponUrl+"\"}}";
        entity="{\"promotionCodeReq\":{\"materialId\":\""+materialId+"\",\"siteId\":\"4000176845\",\"ext1\":\""+memberId+"\",\"couponUrl\":\""+couponUrl+"\",\"positionId\":"+pid+"}}";
        *//*https://api.jd.com/routerjson?v=1.0
        &method=jd.union.open.promotion.common.get
        &app_key=97d9a36db2d6f14fd3a5030e5231b221
        &access_token=cd2c4e6f02e6436bb4d0e51b60c2c42erlnm
        &360buy_param_json={"promotionCodeReq":{materialId:"https://item.jd.com/23484023378.html",siteId:"4000176845"}}
        &timestamp=2020-06-06 15:25:00.364+0800&sign=5D5FAECDBB4B91028BF83E4CE41EF5BB*//*
        //拼接请求参数
        // String prarm ="v=1.0&method=jd.union.open.promotion.common.get&app_key=97d9a36db2d6f14fd3a5030e5231b221&access_token=cd2c4e6f02e6436bb4d0e51b60c2c42erlnm&360buy_param_json={\"promotionCodeReq\":{materialId:\"https://item.jd.com/64720169032.html\",siteId:\"4000176845\",ext1:\"2\"}}&timestamp="+sdf.format(new Date())+"&sign=92213a7a7714c754e4548973bfc7c8df";
       *//* String prarm = "sign="+sign+"&timestamp="+sdf.format(new Date())+"&v=1.0"+
                "&sign_method=md5&format=json&method="+method+
                "&360buy_param_json={\"promotionCodeReq\":{\"materialId\":"+materialId+",\"siteId\":4000176845,\"ext1\":"+memberId+"}}"+
                "&access_token="+accessToken+"&app_key="+appKey;*//*
        *//* String prarm = "sign="+encode+"&timestamp="+map.get("timestamp")+"&v=1.0"+
                 "&sign_method=md5&format=json&method=jd.union.open.promotion.common.get"+
                 "&360buy_param_json={\"promotionCodeReq\":{\"materialId\":\"https://item.jd.com/68498414276.html\",\"siteId\":\"4000176845\",\"ext1\":\"2\"}}"+
                 "&access_token="+accessToken+"&app_key="+appKey;*//*
         String prarm = "sign="+encode+"&timestamp="+map.get("timestamp")+"&v=1.0"+
                 "&sign_method=md5&format=json&method=jd.union.open.promotion.common.get"+
                 "&360buy_param_json="+entity+
                 "&access_token="+accessToken+"&app_key="+appKey;

         String s = sendPost(SERVER_URL, prarm);//发送post请求
         System.out.println("-----------:"+s);
    }*/
    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     *            发送请求的 URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    public static String encryptWithMD5(String target,String charset) {
        String md5Str = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.reset();
            byte[] bytes = md5.digest(charset==null?target.getBytes():target.getBytes(charset));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : bytes) {
                int bt = b & 0xff;
                if (bt < 16) {
                    stringBuffer.append(0);
                }
                stringBuffer.append(Integer.toHexString(bt));
            }
            md5Str = stringBuffer.toString();
        } catch (Exception ex) {

        }
        return md5Str;
    }
    //订单行查询接口
    /*public static void main(String[] args) throws Exception{
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        UnionOpenOrderRowQueryRequest request=new UnionOpenOrderRowQueryRequest();
        OrderRowReq orderReq=new OrderRowReq();
        request.setOrderReq(orderReq);
        UnionOpenOrderRowQueryResponse response=client.execute(request);
    }*/

    //获取通用推广链接
    /*public static void main(String[] args) throws Exception{
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        UnionOpenPromotionCommonGetRequest request=new UnionOpenPromotionCommonGetRequest();
        PromotionCodeReq promotionCodeReq=new PromotionCodeReq();
        request.setPromotionCodeReq(promotionCodeReq);
        UnionOpenPromotionCommonGetResponse response=client.execute(request);
    }*/
    //订单查询接口
    /*public static void main(String[] args) throws Exception{
        JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
        UnionOpenOrderQueryRequest request=new UnionOpenOrderQueryRequest();
        OrderReq orderReq=new OrderReq();
        request.setOrderReq(orderReq);
        UnionOpenOrderQueryResponse response=client.execute(request);
    }*/
   /* public static void main(String[] args) {
        List linkedList=new LinkedList();//插入和删除速度快
        List<Integer> arrayList=new ArrayList();//查询的时候速度快
        Iterator<Integer> it=arrayList.iterator();
        while (it.hasNext()){
            if (it.next()==1){
                it.remove();
            }
        }
    }*/
   /* public static void main(String[] args) {
       String response="{\"code\":200,\"data\":{\"list\":[{\"brandCode\":\"192987\",\"brandName\":\"严济堂\",\"categoryInfo\":{\"cid1\":9192,\"cid1Name\":\"医疗保健\",\"cid2\":9193,\"cid2Name\":\"营养健康\",\"cid3\":9202,\"cid3Name\":\"骨骼健康\"},\"comments\":46258,\"commissionInfo\":{\"commission\":23.7,\"commissionShare\":30,\"couponCommission\":8.7,\"plusCommissionShare\":30},\"couponInfo\":{\"couponList\":[{\"bindType\":2,\"discount\":5,\"getEndTime\":1592271480000,\"getStartTime\":1584581880000,\"isBest\":0,\"link\":\"https:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=ad776183e7684a6684238d7904556509&roleId=28748505&to=yanjitang.jd.com\",\"platformType\":0,\"quota\":60,\"useEndTime\":1592323199000,\"useStartTime\":1584547200000},{\"bindType\":3,\"discount\":50,\"getEndTime\":1591718339000,\"getStartTime\":1591080733000,\"isBest\":1,\"link\":\"http:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=3157423429d04b789c90394625ee4c6b&roleId=32209355\",\"platformType\":0,\"quota\":79,\"useEndTime\":1591718399000,\"useStartTime\":1591027200000}]},\"goodCommentsShare\":99,\"imageInfo\":{\"imageList\":[{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/39786\\/16\\/433\\/132203\\/5cb97e58E54170a4d\\/953bc66e6a99d7e1.png\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/32933\\/5\\/13120\\/129326\\/5cb97e58E1f8e87f0\\/3aaad00ce94b921e.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/9393\\/6\\/8516\\/291472\\/5c0dc63bE4c10a25b\\/0531c082ab0eccaa.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/39278\\/26\\/442\\/120192\\/5cb97e58Ef04282b3\\/3e6624dc160b3417.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/37450\\/6\\/3166\\/101105\\/5cb97e59E713dc258\\/7b4077d1dba1f603.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/7896\\/35\\/934\\/166796\\/5bcd244dE3d77ae3f\\/218b4335147a3824.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/8591\\/3\\/1139\\/399409\\/5bcd244dEb26288a2\\/bbfc5e4e77de6f64.jpg\"}]},\"inOrderComm30Days\":16298.89,\"inOrderCount30Days\":1486,\"isHot\":1,\"isJdSale\":0,\"materialUrl\":\"item.jd.com\\/18314689040.html\",\"owner\":\"p\",\"pinGouInfo\":[],\"pingGouInfo\":[],\"priceInfo\":{\"lowestCouponPrice\":29,\"lowestPrice\":79,\"lowestPriceType\":1,\"price\":79},\"shopInfo\":{\"shopId\":597989,\"shopLevel\":4.5,\"shopName\":\"严济堂官方旗舰店\"},\"skuId\":18314689040,\"skuName\":\"【买2送1】严济堂 氨糖软骨素钙片 成人中老年补钙 补软骨 护关节 增加骨密度 850mg*60片\",\"spuid\":18314689040,\"videoInfo\":[]},{\"brandCode\":\"12380\",\"brandName\":\"美的（Midea）\",\"categoryInfo\":{\"cid1\":6196,\"cid1Name\":\"厨具\",\"cid2\":6197,\"cid2Name\":\"烹饪锅具\",\"cid3\":6199,\"cid3Name\":\"炒锅\"},\"comments\":272637,\"commissionInfo\":{\"commission\":11.85,\"commissionShare\":15,\"couponCommission\":7.35,\"plusCommissionShare\":15},\"couponInfo\":{\"couponList\":[{\"bindType\":3,\"discount\":30,\"getEndTime\":1591459197000,\"getStartTime\":1591235743000,\"isBest\":1,\"link\":\"http:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=b524be3a161c4e11a1f4f742100cef72&roleId=32320023&to=item.jd.com\\/36622120065.html\",\"platformType\":0,\"quota\":69,\"useEndTime\":1591459199000,\"useStartTime\":1591200000000}]},\"goodCommentsShare\":96,\"imageInfo\":{\"imageList\":[{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/119940\\/12\\/3883\\/167766\\/5ed77228E24be3b30\\/f991a7f7362870ba.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t25693\\/83\\/2697950461\\/92164\\/8d9ca55\\/5bebe0e0Neb20a972.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/109510\\/28\\/14315\\/137553\\/5ea639eaEff7f5451\\/12d2469a7de37a14.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/114857\\/30\\/3127\\/132983\\/5ea63950E6fe2ba2b\\/3f2128d60b17b08f.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/33140\\/20\\/8280\\/116501\\/5cc599f5E2f477458\\/6a1f3229339517b3.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/110386\\/24\\/14351\\/164338\\/5ea63967E23ea1882\\/f36b1e38ffa548bb.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/111364\\/25\\/3126\\/223108\\/5ea6396fEae8f1948\\/dae30317e99f4859.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/115399\\/31\\/3087\\/151105\\/5ea639c6E4ad267b6\\/684a5b1792392e80.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t1\\/52382\\/36\\/3562\\/129631\\/5d1acd68E2a5f377a\\/030a36eab2c47223.jpg\"}]},\"inOrderComm30Days\":65700.4,\"inOrderCount30Days\":14963,\"isHot\":1,\"isJdSale\":0,\"materialUrl\":\"item.jd.com\\/36622120065.html\",\"owner\":\"p\",\"pinGouInfo\":[],\"pingGouInfo\":[],\"priceInfo\":{\"lowestCouponPrice\":49,\"lowestPrice\":79,\"lowestPriceType\":1,\"price\":79},\"shopInfo\":{\"shopId\":899397,\"shopLevel\":4.9,\"shopName\":\"美的厨具官方旗舰店\"},\"skuId\":36622120065,\"skuName\":\"美的不粘锅麦饭石色炒锅煎锅电磁炉燃气煤气灶适用家用炒菜锅专用平底锅炊具+竹铲 酒红色\",\"spuid\":36622120065,\"videoInfo\":[]}],\"totalCount\":156155},\"msg\":\"success\"}";
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
             System.out.println("11111111111111"+popInfoList.toString());
        }
    }*/
    public static void main(String[] args) {
        String response="{\"code\":200,\"data\":{\"ItemInfo\":[{\"unitPrice\":36.9,\"materialUrl\":\"http:\\/\\/item.jd.com\\/29161713525.html\",\"endDate\":32472115200000,\"isFreeFreightRisk\":1,\"isFreeShipping\":0,\"commisionRatioWl\":20,\"commisionRatioPc\":20,\"imgUrl\":\"http:\\/\\/img14.360buyimg.com\\/n1\\/jfs\\/t25525\\/12\\/1363641978\\/290106\\/bd6afa1d\\/5baef0a9Nf4802a8f.jpg\",\"vid\":102883,\"cidName\":\"生鲜\",\"wlUnitPrice\":36.9,\"cid2Name\":\"禽肉蛋品\",\"isSeckill\":0,\"cid2\":13586,\"cid3Name\":\"蛋类\",\"inOrderCount\":6340,\"cid3\":12250,\"shopId\":102883,\"isJdSale\":0,\"goodsName\":\"【湖北产地直发】神丹 松花鸭皮蛋 溏心砂芯变蛋无铅工艺 16枚\",\"skuId\":29161713525,\"startDate\":1590595200000,\"cid\":12218}],\"descPic\":[\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/21916\\/17\\/12025\\/134802\\/5c947c25E07f7bc41\\/a8292235366806e6.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/21079\\/3\\/11958\\/116371\\/5c947c26E394030ea\\/8a94a53e2fededf8.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/30967\\/39\\/7239\\/125882\\/5c947c26Ea07a5eab\\/813716f2d39e1007.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/21111\\/18\\/12142\\/201356\\/5c947c26Eb431b253\\/62832190d113cd58.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/17161\\/35\\/9549\\/174358\\/5c947c27E45357160\\/80d676585fdea772.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/20140\\/10\\/12094\\/147553\\/5c947c27E1654db00\\/552472bc2ec6560c.jpg\",\"\\/\\/img10.360buyimg.com\\/imgzone\\/jfs\\/t1\\/33202\\/8\\/14773\\/152720\\/5d2008e8Eacf259c1\\/de4261566acfd2fa.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/11888\\/25\\/12812\\/113485\\/5c947c27E8677e4a8\\/43ef128468dbcd43.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/32246\\/14\\/7116\\/174016\\/5c947c28E97f4c6de\\/ba90860ed6cfd588.jpg\",\"\\/\\/img30.360buyimg.com\\/popWaterMark\\/jfs\\/t1\\/11216\\/28\\/12932\\/170638\\/5c947c28E5ab74daf\\/c2494bdf1947f218.jpg\"],\"itemCouponInfo\":{\"brandCode\":\"25952\",\"brandName\":\"神丹\",\"categoryInfo\":{\"cid1\":12218,\"cid1Name\":\"生鲜\",\"cid2\":13586,\"cid2Name\":\"禽肉蛋品\",\"cid3\":12250,\"cid3Name\":\"蛋类\"},\"comments\":52993,\"commissionInfo\":{\"commission\":7.38,\"commissionShare\":20,\"couponCommission\":5.58,\"plusCommissionShare\":20},\"couponInfo\":{\"couponList\":[{\"bindType\":3,\"discount\":9,\"getEndTime\":1591545598000,\"getStartTime\":1590661473000,\"isBest\":1,\"link\":\"https:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=08468111a2bd4fc8ad01441ddfd42d04&roleId=31884493&to=mall.jd.com\",\"platformType\":0,\"quota\":35,\"useEndTime\":1591545599000,\"useStartTime\":1590595200000},{\"bindType\":3,\"discount\":9,\"getEndTime\":1591631998000,\"getStartTime\":1590940800000,\"isBest\":0,\"link\":\"http:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=7c67c4e03e8446a8921026b32034ac49&roleId=31916555&to=mall.jd.com\\/index-101019.html\",\"platformType\":0,\"quota\":35,\"useEndTime\":1591631999000,\"useStartTime\":1590940800000},{\"bindType\":3,\"discount\":9,\"getEndTime\":1591545598000,\"getStartTime\":1590659965000,\"isBest\":0,\"link\":\"https:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=445819cbc1f345c6b01f8b7d00debaed&roleId=31881107&to=mall.jd.com\\/index-101019.html\",\"platformType\":0,\"quota\":35,\"useEndTime\":1591545599000,\"useStartTime\":1590595200000},{\"bindType\":3,\"discount\":9,\"getEndTime\":1591977598000,\"getStartTime\":1591261476000,\"isBest\":0,\"link\":\"http:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=9163a11019784349a6b0e23115124127&roleId=32362807&to=mall.jd.com\\/index-101019.html\",\"platformType\":0,\"quota\":35,\"useEndTime\":1591977599000,\"useStartTime\":1591200000000},{\"bindType\":3,\"discount\":9,\"getEndTime\":1592063998000,\"getStartTime\":1591454952000,\"isBest\":0,\"link\":\"http:\\/\\/coupon.m.jd.com\\/coupons\\/show.action?key=bc383f3252ba492c95a382ccf00c579e&roleId=32489216&to=mall.jd.com\\/index-101019.html\",\"platformType\":0,\"quota\":35,\"useEndTime\":1592063999000,\"useStartTime\":1591372800000}]},\"goodCommentsShare\":99,\"imageInfo\":{\"imageList\":[{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t25525\\/12\\/1363641978\\/290106\\/bd6afa1d\\/5baef0a9Nf4802a8f.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t18637\\/22\\/1882011686\\/644240\\/9766d1a0\\/5ada9e33N65e10393.png\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t18697\\/162\\/130638059\\/210174\\/c200b56d\\/5a5f2e32N66af90f4.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t17281\\/144\\/152152870\\/288356\\/6dfa1287\\/5a5f14b9Nfc6e63e2.jpg\"},{\"url\":\"http:\\/\\/img14.360buyimg.com\\/ads\\/jfs\\/t16762\\/124\\/135287961\\/170990\\/380aff45\\/5a5f34f8Nf9eb4316.jpg\"}]},\"inOrderComm30Days\":36509.79,\"inOrderCount30Days\":6340,\"isHot\":1,\"isJdSale\":0,\"materialUrl\":\"item.jd.com\\/29161713525.html\",\"owner\":\"p\",\"pinGouInfo\":[],\"pingGouInfo\":[],\"priceInfo\":{\"lowestCouponPrice\":27.9,\"lowestPrice\":36.9,\"lowestPriceType\":1,\"price\":36.9},\"shopInfo\":{\"shopId\":101019,\"shopLevel\":4.5,\"shopName\":\"神丹官方旗舰店\"},\"skuId\":29161713525,\"skuName\":\"【湖北产地直发】神丹 松花鸭皮蛋 溏心砂芯变蛋无铅工艺 16枚\",\"spuid\":29161713525,\"videoInfo\":[]}},\"msg\":\"success\"}";
        JSONObject json = JSONObject.fromObject(response);
        String code=json.getString("code");
        String materialUrl =null;
        if (StringUtils.equals(code, "200")){
            Double min=0.47956456;
            Double max=0.47999999;
            BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
            BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );

            List<GoodInfo> popInfoList=new ArrayList<>();
            JSONObject data = JSONObject.fromObject(json.getString("data"));

            if(null !=data){
                    JSONObject good = (JSONObject)data.get("itemCouponInfo");
                    ShareGoodInfo goodInfo=new ShareGoodInfo();
                    JSONObject imageInfo = JSONObject.fromObject(good.getString("imageInfo"));//图片信息
                    JSONArray imageList = JSONArray.fromObject(imageInfo.getString("imageList"));//图片信息
                    List<String> goodsGalleryUrls=new ArrayList<>();
                    if (!imageList.isEmpty()){
                        for (int j=0;j<imageList.size();j++){
                            JSONObject image = imageList.getJSONObject(j);//图片列表
                            goodsGalleryUrls.add(image.getString("url"));//图片url
                        }
                    }
                    goodInfo.setGoodsGalleryUrls(goodsGalleryUrls);
                    goodInfo.setGoodsName(good.getString("skuName"));
                    materialUrl = good.getString("materialUrl");
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

            }
            System.out.println("11111111111111"+popInfoList.toString());
        }
    }
}
