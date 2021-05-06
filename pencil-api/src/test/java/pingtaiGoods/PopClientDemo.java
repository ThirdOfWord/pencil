package pingtaiGoods;


import com.freeter.common.utils.R;
import com.pdd.pop.sdk.common.util.JsonUtil;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkGoodsDetailRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkWeappQrcodeUrlGenRequest;
import com.pdd.pop.sdk.http.api.pop.request.PddGoodsCatsGetRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkGoodsDetailResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkWeappQrcodeUrlGenResponse;
import com.pdd.pop.sdk.http.api.pop.response.PddGoodsCatsGetResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//拼多多
@RestController
@RequestMapping("api/pop")
public class PopClientDemo {
    private final static String clientId = "05f9fbbd87cb4c37947463f75da309bc";
    private final static  String clientSecret = "7f466088dc0ac82891ba306e317c9826aac5ca38";
    @RequestMapping("list")
    public R listAll() throws Exception{
        PopClient client = new PopHttpClient(clientId, clientSecret);
        PddDdkGoodsDetailRequest request = new PddDdkGoodsDetailRequest();
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(3180973827L);
        request.setGoodsIdList(goodsIdList);
        //*request.setPid("str");
        request.setCustomParameters("str");
        request.setZsDuoId(0L);
        request.setPlanType(0);
        request.setSearchId("str");
        PddDdkGoodsDetailResponse response = client.syncInvoke(request);
        return R.ok().put("list", response);
    }
    //商品标准类目接口
    public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddGoodsCatsGetRequest request = new PddGoodsCatsGetRequest();
        request.setParentCatId(16794L);
        PddGoodsCatsGetResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }
    //查询已经生成的推广位信息
    /*public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddDdkGoodsPidQueryRequest request = new PddDdkGoodsPidQueryRequest();
        *//*request.setPage(0);
        request.setPageSize(10);
        List<String> pidList = new ArrayList<String>();
        pidList.add("");//9393981_129055352
        request.setPidList(pidList);*//*
        PddDdkGoodsPidQueryResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response.getPIdQueryResponse().getPIdList()));
    }*/
    //多多进宝推广链接生成
    /*public static void main(String[] args) throws Exception{
        PopClient client = new PopHttpClient(clientId, clientSecret);
        PddDdkGoodsPromotionUrlGenerateRequest request = new PddDdkGoodsPromotionUrlGenerateRequest();
        request.setPId("9393981_129055352");
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(89817131112L);
        request.setGoodsIdList(goodsIdList);
        request.setGenerateShortUrl(false);
        request.setMultiGroup(true);
        request.setCustomParameters("2");
        request.setGenerateWeappWebview(false);
        request.setGenerateWeApp(false);
        request.setGenerateWeiboappWebview(false);
        request.setGenerateMallCollectCoupon(false);
        request.setGenerateSchemaUrl(false);
        request.setGenerateQqApp(false);
        PddDdkGoodsPromotionUrlGenerateResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }*/
    //多多客生成单品推广小程序二维码url
    /*public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddDdkWeappQrcodeUrlGenRequest request = new PddDdkWeappQrcodeUrlGenRequest();
        request.setCustomParameters("1");
        request.setGenerateMallCollectCoupon(false);
        List<Long> goodsIdList = new ArrayList<>();
        goodsIdList.add(89817131112L);
        request.setGoodsIdList(goodsIdList);
        request.setPId("9393981_129055352");
        //request.setZsDuoId(0L);
        PddDdkWeappQrcodeUrlGenResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }*/
    //最后更新时间段增量同步推广订单信息
  /*  public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);
        PddDdkOrderListIncrementGetRequest request = new PddDdkOrderListIncrementGetRequest();
        request.setStartUpdateTime(1584278151-86400L);
        request.setEndUpdateTime(1584278151L);
        request.setPageSize(2);
        request.setPage(1);
        request.setReturnCount(true);
        PddDdkOrderListIncrementGetResponse response = client.syncInvoke(request);
        int size=response.getOrderListGetResponse().getOrderList().size();
        for (int i=0;i<size;i++){
            List<PddDdkOrderListIncrementGetResponse.OrderListGetResponseOrderListItem> list=response.getOrderListGetResponse().getOrderList();
            BigDecimal goodsPrice=new BigDecimal(list.get(i).getGoodsPrice()).divide(new BigDecimal(100));
            BigDecimal orderAmount=new BigDecimal(list.get(i).getOrderAmount()).divide(new BigDecimal(100));
            BigDecimal promotionAmount=new BigDecimal(list.get(i).getPromotionAmount()).divide(new BigDecimal(100));
            GoodsOrderPddEntity pddEntity=new GoodsOrderPddEntity();//创建订单
            pddEntity.setOrderCreateTime(list.get(i).getOrderCreateTime().intValue());
            int memberId=list.get(i).getCustomParameters().equals("memberId1") ? 1 : Integer.parseInt(list.get(i).getCustomParameters());
            pddEntity.setMemberId(memberId);
            //pddEntity.setMemberId(Integer.parseInt(list.get(i).getCustomParameters()));
            pddEntity.setOrderSn(list.get(i).getOrderSn());
            pddEntity.setGoodsId(list.get(i).getGoodsId());
            pddEntity.setGoodsName(list.get(i).getGoodsName());
            pddEntity.setGoodsThumbnailUrl(list.get(i).getGoodsThumbnailUrl());
            pddEntity.setGoodsQuantity(list.get(i).getGoodsQuantity().intValue());
            pddEntity.setGoodsPrice(goodsPrice);
            pddEntity.setOrderAmount(orderAmount);
            pddEntity.setPromotionRate(list.get(i).getPromotionRate().intValue());
            pddEntity.setPromotionAmount(promotionAmount);
            pddEntity.setBatchNo(list.get(i).getBatchNo());
            pddEntity.setOrderStatus(list.get(i).getOrderStatus());
            pddEntity.setOrderPayTime(list.get(i).getOrderPayTime()==null ? 0 : list.get(i).getOrderPayTime().intValue());
            pddEntity.setOrderGroupSuccessTime(list.get(i).getOrderGroupSuccessTime()==null ? 0 : list.get(i).getOrderGroupSuccessTime().intValue());
            pddEntity.setOrderReceiveTime(list.get(i).getOrderReceiveTime()==null ? 0 : list.get(i).getOrderReceiveTime().intValue());
            pddEntity.setOrderVerifyTime(list.get(i).getOrderVerifyTime()==null ? 0 : list.get(i).getOrderVerifyTime().intValue());
            pddEntity.setOrderSettleTime(list.get(i).getOrderSettleTime()==null ? 0 : list.get(i).getOrderSettleTime().intValue());
            pddEntity.setOrderModifyAt(list.get(i).getOrderModifyAt().intValue());
            pddEntity.setType(list.get(i).getType());
            pddEntity.setGroupId(list.get(i).getGroupId());
            pddEntity.setAuthDuoId(list.get(i).getAuthDuoId());
            pddEntity.setZsDuoId(list.get(i).getZsDuoId());
            pddEntity.setPid(list.get(i).getPId());
            pddEntity.setCpaNew(list.get(i).getCpaNew());
        }
        System.out.println(JsonUtil.transferToJson(response));
    }*/
    //用时间段查询推广订单接口
    /*public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);
        PddDdkOrderListRangeGetRequest request = new PddDdkOrderListRangeGetRequest();
        //request.setEndTime(DateUtil.printDate());
        request.setEndTime("2020-03-14 01:57:50");
        //request.setLastOrderId("str");
        String aaa=DateUtil.timeStampToStr(1584035870L);
        System.out.println("aaaaaaaaa---:"+aaa);
        request.setPageSize(300);
        request.setStartTime("2020-03-13 01:57:50");
        PddDdkOrderListRangeGetResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response.getOrderListGetResponse().getOrderList()));
    }*/
    //查询订单详情
    /*public static void main(String[] args) throws Exception {
        PopClient client = new PopHttpClient(clientId, clientSecret);
        PddDdkOrderDetailGetRequest request = new PddDdkOrderDetailGetRequest();
        request.setOrderSn("200313-163066020672389");
        PddDdkOrderDetailGetResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }*/
    /*//获取商品基本信息
    public static void main(String[] args) throws Exception {

        String clientId = "05f9fbbd87cb4c37947463f75da309bc";
        String clientSecret = "7f466088dc0ac82891ba306e317c9826aac5ca38";
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddDdkGoodsBasicInfoGetRequest request = new PddDdkGoodsBasicInfoGetRequest();
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(3180973827L);
        request.setGoodsIdList(goodsIdList);
        PddDdkGoodsBasicInfoGetResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }*/
    /*public static void main(String[] args) throws Exception {

        String clientId = "05f9fbbd87cb4c37947463f75da309bc";
        String clientSecret = "7f466088dc0ac82891ba306e317c9826aac5ca38";
        PopClient client = new PopHttpClient(clientId, clientSecret);

        PddDdkGoodsDetailRequest request = new PddDdkGoodsDetailRequest();
        List<Long> goodsIdList = new ArrayList<Long>();
        goodsIdList.add(3180973827L);
        request.setGoodsIdList(goodsIdList);
        request.setPid("str");
        request.setCustomParameters("str");
        request.setZsDuoId(0L);
        request.setPlanType(0);
        request.setSearchId("str");
        PddDdkGoodsDetailResponse response = client.syncInvoke(request);
        System.out.println(JsonUtil.transferToJson(response));
    }*/
}
