package pingtaiGoods;
import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;

import java.util.Date;

public class TaobaoClientDemo {
    private final static String url="http://gw.api.taobao.com/router/rest";
    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    private final static Long adzoneId=110507450350L;
    /*public static void main(String[] args) throws Exception{
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="30117515";
        String secret="9d3d998debbfc5e9a63fb11b6812cd47";
        String goodsUrl="https://uland.taobao.com/coupon/edetail?e=oxBYyIzH%2F7sNfLV8niU3R5TgU2jJNKOfNNtsjZw%2F%2FoIMRAyZUlGQNxloaEtW8593nzbvrSnN1cKd6PEyZ%2Bzx%2BMuRTiT9oEhVZV8pr6FWc0PF0qY5aHxvFPpMYwyv%2FC4mmMHpNfYdHdCg3Mo%2Fx42IGyZhwga6L1ca5FOypmeO160okVXeX9KRtG9hqyGSndke7ryGf3jJX%2FFVbrKqp4Yn8g%3D%3D&&app_pvid=59590_11.23.85.106_39287_1592113780546&ptl=floorId:3765;app_pvid:59590_11.23.85.106_39287_1592113780546;tpp_pvid:45378131-0d8e-4285-8cee-0975a849d9fc&union_lens=lensId%3AMAPI%401592113780%400b17556a_0ec6_172b1605790_38f2%4001";
        String logo="https://img.alicdn.com/bao/uploaded/TB1YlMzh9zqK1RjSZFHwu23CpXa.png";
        *//*TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setText("铅笔头测试商品淘口令");
        req.setUrl(goodsUrl);
        TbkTpwdCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());*//*
        *//*TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
        req.setUserId(2347683237L);
        req.setCount(1L);
        TbkShopRecommendGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());*//*
       *//* TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkShopGetRequest req = new TbkShopGetRequest();
        req.setFields("user_id,shop_title,shop_type,seller_nick,pict_url,shop_url");
        req.setQ("优能家居旗舰店");
        req.setPageNo(1L);
        req.setPageSize(20L);
        TbkShopGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());*//*
    }*/
    //淘宝客-推广者-官方活动转链
    public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(adzoneId);
        req.setRelationId(2519600334L);
        req.setActivityMaterialId("1571715733668");
        req.setUnionId("demo");
        TbkActivityInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }
    //淘宝客-推广者-官方活动转链
    /*public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivitylinkGetRequest req = new TbkActivitylinkGetRequest();
        req.setPlatform(2L);
        //req.setUnionId("demo");
        req.setAdzoneId(adzoneId);
        req.setPromotionSceneId(1571715733668L);
        //req.setSubPid("mm_123_123_123");
        req.setRelationId("2525003682");
        TbkActivitylinkGetResponse rsp = client.execute(req);
        System.out.println(rsp.getData());
    }*/
    //商品分类
  /*  public static void main(String[] args) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        ItemcatsGetRequest req = new ItemcatsGetRequest();
        req.setCids("18957,19562");
        req.setDatetime(StringUtils.parseDateTime("2000-01-01 00:00:00"));
        req.setFields("cid,parent_cid,name,is_parent");
        req.setParentCid(50011999L);
        ItemcatsGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
     //淘礼金
 /*   public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkDgVegasTljCreateRequest req = new TbkDgVegasTljCreateRequest();
        req.setAdzoneId(adzoneId);
        req.setItemId(590084304040L);
        req.setTotalNum(10L);
        req.setName("淘礼金来啦");
        req.setUserTotalWinNumLimit(1L);
        req.setSecuritySwitch(true);
        req.setPerFace("10");
        req.setSendStartTime(new Date());
        TbkDgVegasTljCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/

    /*public static void main(String[] args) throws ApiException {
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="28079708";
        String secret="fdf04c46c5380d6d978f639fc8ad911f";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        req.setNumIids("587143572046");
        req.setPlatform(1L);
        req.setIp("11.22.33.43");
        TbkItemInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
        System.out.println(JSONObject.parseObject( rsp.getBody()));
    }*/
   /* public static void main(String[] args) throws ApiException {
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="28079708";
        String secret="fdf04c46c5380d6d978f639fc8ad911f";
        TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
        TbkItemConvertRequest req = new TbkItemConvertRequest();
        req.setFields("num_iid,click_url");
        req.setNumIids("123,456");
        req.setAdzoneId(123L);
        req.setPlatform(123L);
        req.setUnid("demo");
        req.setDx("1");
        TbkItemConvertResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/

   //淘宝客-公用-淘口令生成
    /*public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        String aaa = "https://s.click.ele.me/t?&e=-s026nS6cj9Ww1fnuCoovMHuDvNwrpfu6g9fk3kfrmR7r9OznmOkMR4T44lKCEua0g5COINift0Lo7SOHU6sNJqIRPiQp3WRnH44X9T6WN183GhtHBbRzZ2hAGHNucSjhMymJafMaX2PNnQpts2KzhQId4sEdw6W9ZUdhO3sCcC9fLc6vlh3bqy4HDf3OFiAg58xSM2XtCsv3raSpDLRiNLmWA36Lpj6aMUo4ojQEtMy3feGgmK4zUwGfwcI8FKGq6FE39Ey4HsVqs8Sisi3agmsDO4qOoju10fEHQsKnkWegWVHZorRNTWKmH02JgR0JzvfYN753xhlF2w1aZx8CW41ChInNUZklFKTq0xQqXCiIKiKCz1u325Jt8L3uP2rXry2eAS7mwPyOkbGwHBiBd1Yxk9pdnWNqpVcf23q47CrJZBIkkrHHr9iY2TeZprZkNTGWuZZbOHf8vsEBau1Yl4s34xzvMEM9bogwtne&&union_lens=lensId:0b1833f0_0d60_17477f26d5a_8c63";
        req.setUserId("123");
        req.setText("长度大于5个字符");
        req.setUrl(aaa);
        req.setLogo("https://uland.taobao.com/");
        req.setExt("{}");
        TbkTpwdCreateResponse rsp = client.execute(req);
        String bbb = rsp.getData().getModel();

        String str1=bbb.substring(0, bbb.indexOf("￥"));
        String str2=bbb.substring(str1.length()+1, bbb.length());
        String str3=str2.substring(0, str2.indexOf("￥"));

        System.out.println("￥"+str3+"￥");
        System.out.println(rsp.getData().getModel());
    }*/
   //淘宝客-推广者-所有订单查询
   /* public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();
        req.setPageSize(20L);
        req.setQueryType(1L);
        //req.setStartTime("2020-08-04 18:44:00");
        //req.setEndTime("2020-08-04 19:59:56");
        //req.setStartTime("2020-09-13 17:32:01");
        //req.setEndTime("2020-09-13 17:35:01");
        req.setStartTime("2020-09-14 11:23:01");
        req.setEndTime("2020-09-14 11:24:01");
        req.setPageNo(1L);
        req.setOrderScene(1L);
        TbkOrderDetailsGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
   // 淘宝客-公用-私域用户备案
    /*public static void main(String[] args) throws Exception{
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkScPublisherInfoSaveRequest req = new TbkScPublisherInfoSaveRequest();
        String sessionKey = "61023134003be1446dc8312836a9e2a8f125a3876d51c962056066357";
        req.setRelationFrom("1");
        req.setOfflineScene("1");
        req.setOnlineScene("1");
        req.setInviterCode("NLH34W");
        req.setInfoType(1L);
        //req.setNote("小蜜蜂");
        //req.setRegisterInfo("{\"phoneNumber\":\"18801088599\",\"city\":\"江苏省\",\"province\":\"南京市\",\"location\":\"玄武区花园小区\",\"detailAddress\":\"5号楼3单元101室\",\"shopType\":\"社区店\",\"shopName\":\"全家便利店\",\"shopCertifyType\":\"营业执照\",\"certifyNumber\":\"111100299001\"}");
        TbkScPublisherInfoSaveResponse rsp = client.execute(req, sessionKey);
        System.out.println(rsp.getBody());
    }*/
   //淘宝客-推广者-官方活动信息获取
   /* public static void main(String[] args) throws Exception{
        String url="http://gw.api.tbsandbox.com/router/rest";
        String appkey="30117515";
        String secret="9d3d998debbfc5e9a63fb11b6812cd47";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivityInfoGetRequest req = new TbkActivityInfoGetRequest();
        req.setAdzoneId(110507450350L);
        req.setActivityMaterialId("1585018034441L");
        req.setUnionId("member:4");
        TbkActivityInfoGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
   //淘宝客-推广者-官方活动转链 )
    /*public static void main(String[] args) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkActivitylinkGetRequest req = new TbkActivitylinkGetRequest();
        req.setPlatform(1L);
        req.setUnionId("demo");
        req.setAdzoneId(123L);
        req.setPromotionSceneId(12345678L);
        req.setSubPid("mm_123_123_123");
        req.setRelationId("23");
        TbkActivitylinkGetResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
    //淘宝客-公用-淘口令生成
    /*public static void main(String[] args) throws Exception{
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="28079708";
        String secret="fdf04c46c5380d6d978f639fc8ad911f";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        req.setUserId("123");
        req.setText("长度大于5个字符");
        req.setUrl("https://uland.taobao.com/");
        req.setLogo("https://uland.taobao.com/");
        req.setExt("{memeberId:1}");
        TbkTpwdCreateResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
   // 淘宝客-推广者-商品链接转换
   /* public static void main(String[] args) throws Exception{
        String url="http://gw.api.taobao.com/router/rest";
        String appkey="28079708";
        String secret="fdf04c46c5380d6d978f639fc8ad911f";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        TbkItemConvertRequest req = new TbkItemConvertRequest();
        req.setFields("num_iid,click_url");
        req.setNumIids("123,456");
        req.setAdzoneId(123L);
        req.setPlatform(123L);
        req.setUnid("demo");
        req.setDx("1");
        TbkItemConvertResponse rsp = client.execute(req);
        System.out.println(rsp.getBody());
    }*/
   //淘宝客-推广者-官方活动转链
   /*public static void main(String[] args) throws Exception{
       String url="http://gw.api.taobao.com/router/rest";
       String appkey="28079708";
       String secret="fdf04c46c5380d6d978f639fc8ad911f";
       TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
       TbkActivitylinkGetRequest req = new TbkActivitylinkGetRequest();
       req.setPlatform(1L);
       req.setUnionId("demo");
       req.setAdzoneId(110330250233L);
       req.setPromotionSceneId(12345678L);
       req.setSubPid("mm_123_123_123");
       req.setRelationId("23");
       TbkActivitylinkGetResponse rsp = client.execute(req);
       System.out.println(rsp.getBody());
   }
     /*public static void main(String[] args) {
        BigDecimal aa=new BigDecimal(100);
        BigDecimal bb=new BigDecimal(0.95);
        BigDecimal cc=aa.multiply(bb).setScale(2,BigDecimal.ROUND_HALF_UP);
        System.out.println("-------------"+cc);
        System.out.println("------2*0.75 = 1-------"+2*0.75);
        System.out.println("============"+new BigDecimal(100).multiply(new BigDecimal(0.95)));
        String content = "I am noob " + "from runoob.com.";
        String pattern = ".*runoob.*";
        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("字符串中是否包含了 'runoob' 子字符串? " + isMatch);
    }*/


}
