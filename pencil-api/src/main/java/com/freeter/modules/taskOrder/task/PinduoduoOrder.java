package com.freeter.modules.taskOrder.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.service.GoodsOrderService;
import com.pdd.pop.sdk.http.PopClient;
import com.pdd.pop.sdk.http.PopHttpClient;
import com.pdd.pop.sdk.http.api.pop.request.PddDdkOrderListIncrementGetRequest;
import com.pdd.pop.sdk.http.api.pop.response.PddDdkOrderListIncrementGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Component
public class PinduoduoOrder {
    @Autowired
    private GoodsOrderService goodsOrderService;

    private final static String clientId = "05f9fbbd87cb4c37947463f75da309bc";
    private final static  String clientSecret = "7f466088dc0ac82891ba306e317c9826aac5ca38";
    private final static PopClient client = new PopHttpClient(clientId, clientSecret);
    private final static PddDdkOrderListIncrementGetRequest request = new PddDdkOrderListIncrementGetRequest();
    @Async
    @Scheduled(cron = "0 0/2 * * * ?")
    public void testScheduled() throws Exception{
        int page=1;
        long endTime= DateUtil.getUnixStamp();
        request.setStartUpdateTime(endTime-130);
        request.setEndUpdateTime(endTime);
        request.setPageSize(20);
        request.setReturnCount(true);
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        getPageOrder(page,liRun);
        System.out.println("pinduoduoOrder run:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    private void getPageOrder(int page,BigDecimal liRun) throws Exception{
        request.setPage(page);
        PddDdkOrderListIncrementGetResponse response = client.syncInvoke(request);
        int size=response.getOrderListGetResponse().getOrderList().size();
        if (size>0){
            for (int i=0;i<size;i++){
                List<PddDdkOrderListIncrementGetResponse.OrderListGetResponseOrderListItem> list=response.getOrderListGetResponse().getOrderList();
                BigDecimal goodsPrice=new BigDecimal(list.get(i).getGoodsPrice()).divide(new BigDecimal(100));
                BigDecimal orderAmount=new BigDecimal(list.get(i).getOrderAmount()).divide(new BigDecimal(100));
                //BigDecimal promotionAmount=new BigDecimal(list.get(i).getPromotionAmount()).divide(new BigDecimal(100));
                Long promotionAmount=list.get(i).getPromotionAmount();//???????????????????????????
                String orderSn=list.get(i).getOrderSn();//?????????
                int orderStatus=list.get(i).getOrderStatus();//????????????
                EntityWrapper<GoodsOrderEntity> orderWrapper=new EntityWrapper<>();
                orderWrapper.eq("orderid", orderSn);
                GoodsOrderEntity orderEntity=goodsOrderService.selectOne(orderWrapper);

                if (null == orderEntity){//????????????
                    orderEntity=new GoodsOrderEntity();
                    //???????????????????????????
                    String customParameters = list.get(i).getCustomParameters();
                    if(customParameters.indexOf(",") >= 0){
                        //???????????????????????????
                        String[] strArray = customParameters.split(",");
                        for (int x = 0; x < strArray.length; x++) {
                            if (x == 0){
                                orderEntity.setShareMemberId(Integer.parseInt(strArray[x]));
                            }else if (x == 1){
                                orderEntity.setMemberId(Integer.parseInt(strArray[x]));
                            }
                        }
                    }else {
                        orderEntity.setMemberId(Integer.parseInt(customParameters));
                    }
                    //??????????????????????????????db??????  -1 ?????????; 0-????????????1-????????????2-???????????????3-???????????????4-?????????????????????????????????5-???????????????8-??????????????????????????????????????????
                    if (orderStatus == -1){
                        orderEntity.setOrderStatus(0);
                        orderEntity.setIsSettle(1);
                        orderEntity.setIsRrive(0);
                    }
                    if (orderStatus == 0 || orderStatus == 1){
                        BigDecimal amount=new BigDecimal(promotionAmount).divide(new BigDecimal(100));
                        BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                        orderEntity.setOrderStatus(1);
                        orderEntity.setIsSettle(0);
                        orderEntity.setIsRrive(1);
                        orderEntity.setFeeMember(freeMember);
                    }
                    if (orderStatus == 2){
                        BigDecimal amount=new BigDecimal(promotionAmount).divide(new BigDecimal(100));
                        BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                        orderEntity.setOrderStatus(2);
                        orderEntity.setIsSettle(0);
                        orderEntity.setIsRrive(1);
                        orderEntity.setFeeMember(freeMember);
                    }
                    if (orderStatus == 3 || orderStatus == 5 || orderStatus == 8){
                        BigDecimal amount=new BigDecimal(promotionAmount).divide(new BigDecimal(100));
                        BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                        orderEntity.setOrderStatus(4);
                        orderEntity.setIsSettle(1);
                        orderEntity.setIsRrive(0);
                        orderEntity.setFeeMember(freeMember);
                    }
                    if (orderStatus == 4){//??????
                        BigDecimal amount=new BigDecimal(promotionAmount).divide(new BigDecimal(100));
                        BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                        orderEntity.setOrderStatus(3);
                        orderEntity.setIsSettle(0);
                        orderEntity.setIsRrive(0);
                        orderEntity.setFeeMember(freeMember);
                    }
                    if (orderStatus != -1){
                        orderEntity.setOrderStatusOld(orderStatus);
                        orderEntity.setSupplierId(2);
                        orderEntity.setActualfee(promotionAmount);
                        orderEntity.setCommissionrate(list.get(i).getPromotionRate());
                        orderEntity.setOrderid(orderSn);
                        orderEntity.setOrdertime(list.get(i).getOrderCreateTime().intValue());
                        orderEntity.setPaymoney(orderAmount);
                        orderEntity.setPayTime(list.get(i).getOrderPayTime()==null ? 0 : list.get(i).getOrderPayTime().intValue());
                        orderEntity.setPositionid(list.get(i).getPId());
                        orderEntity.setPrice(goodsPrice);
                        orderEntity.setReceiveTime(list.get(i).getOrderReceiveTime()==null ? 0 : list.get(i).getOrderReceiveTime().intValue());
                        orderEntity.setSkuid(list.get(i).getGoodsId());
                        orderEntity.setSkuname(list.get(i).getGoodsName());
                        orderEntity.setSkunum(list.get(i).getGoodsQuantity().intValue());
                        orderEntity.setThumbnailUrl(list.get(i).getGoodsThumbnailUrl());
                        goodsOrderService.insert(orderEntity);
                    }
                }else {//????????????
                    int orderStatusOld=orderEntity.getOrderStatusOld();//??????????????????????????????
                    if (orderStatusOld == 0 || orderStatusOld == 1){
                       if (orderStatus == 2){
                           orderEntity.setOrderStatus(2);
                       }else if (orderStatus == 3 || orderStatus == 5 || orderStatus == 8){
                            orderEntity.setOrderStatus(4);
                            orderEntity.setIsSettle(1);
                            orderEntity.setIsRrive(0);
                        }else if(orderStatus ==4){
                           orderEntity.setOrderStatus(3);
                           orderEntity.setIsSettle(0);
                           orderEntity.setIsRrive(0);
                       }

                    }
                    if (orderStatusOld == 2){
                        if (orderStatus == 3 || orderStatus == 5 || orderStatus == 8){
                            orderEntity.setOrderStatus(4);
                            orderEntity.setIsSettle(1);
                            orderEntity.setIsRrive(0);
                        }else if (orderStatus == 0 || orderStatus == 1){
                            orderEntity.setOrderStatus(1);
                        }else if (orderStatus ==4){
                            orderEntity.setOrderStatus(3);
                            orderEntity.setIsSettle(0);
                            orderEntity.setIsRrive(0);
                        }
                    }
                    if (orderStatusOld == 3 || orderStatusOld == 5 || orderStatusOld == 8){
                        if (orderStatus == 2){
                            orderEntity.setOrderStatus(2);
                            orderEntity.setIsSettle(0);
                            orderEntity.setIsRrive(1);
                        }
                    }
                    //???????????????????????????
                    String customParameters = list.get(i).getCustomParameters();
                    if(customParameters.indexOf(",") >= 0){
                        //???????????????????????????
                        String[] strArray = customParameters.split(",");
                        for (int x = 0; x < strArray.length; x++) {
                            if (x == 0){
                                orderEntity.setShareMemberId(Integer.parseInt(strArray[x]));
                            }else if (x == 1){
                                orderEntity.setMemberId(Integer.parseInt(strArray[x]));
                            }
                        }
                    }else {
                        orderEntity.setMemberId(Integer.parseInt(customParameters));
                    }
                    orderEntity.setOrderStatusOld(orderStatus);
                    orderEntity.setSupplierId(2);
                    orderEntity.setActualfee(promotionAmount);
                    orderEntity.setCommissionrate(list.get(i).getPromotionRate());
                    orderEntity.setOrderid(orderSn);
                    orderEntity.setOrdertime(list.get(i).getOrderCreateTime().intValue());
                    orderEntity.setPaymoney(orderAmount);
                    orderEntity.setPayTime(list.get(i).getOrderPayTime()==null ? 0 : list.get(i).getOrderPayTime().intValue());
                    orderEntity.setPositionid(list.get(i).getPId());
                    orderEntity.setPrice(goodsPrice);
                    orderEntity.setReceiveTime(list.get(i).getOrderReceiveTime()==null ? 0 : list.get(i).getOrderReceiveTime().intValue());
                    orderEntity.setSkuid(list.get(i).getGoodsId());
                    orderEntity.setSkuname(list.get(i).getGoodsName());
                    orderEntity.setSkunum(list.get(i).getGoodsQuantity().intValue());
                    orderEntity.setThumbnailUrl(list.get(i).getGoodsThumbnailUrl());
                    goodsOrderService.updateAllColumnById(orderEntity);
                }
            }
            if (size==20){
                page=page+1;
                getPageOrder(page,liRun);
            }
        }
    }
}