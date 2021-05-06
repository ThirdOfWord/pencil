package com.freeter.modules.taskOrder.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.service.GoodsOrderService;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.kplunion.OrderService.request.query.OrderRowReq;
import com.jd.open.api.sdk.domain.kplunion.OrderService.response.query.OrderRowResp;
import com.jd.open.api.sdk.request.kplunion.UnionOpenOrderRowQueryRequest;
import com.jd.open.api.sdk.response.kplunion.UnionOpenOrderRowQueryResponse;
import org.apache.commons.lang.StringUtils;
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
public class JdOrder {
    @Autowired
    private GoodsOrderService goodsOrderService;

    private final static String SERVER_URL = "https://api.jd.com/routerjson";
    private static  String accessToken = "cd2c4e6f02e6436bb4d0e51b60c2c42erlnm";
    private final static  String appKey = "97d9a36db2d6f14fd3a5030e5231b221";
    private final static  String appSecret = "9cc6f49ae6cd4715b030bd28fc2e12c9";
    private final static  String APPID = "4000176845";
    private final static JdClient client=new DefaultJdClient(SERVER_URL,accessToken,appKey,appSecret);
    private final static UnionOpenOrderRowQueryRequest request=new UnionOpenOrderRowQueryRequest();
    @Async
    //@Scheduled(cron = "0 0/7 * * * ?")
    public void testScheduled() throws Exception{
        int page=1;
        OrderRowReq orderReq=new OrderRowReq();
        orderReq.setPageSize(20);
        orderReq.setType(3);
        long stamp=DateUtil.getUnixStamp();
        long start=stamp-(60*8);
        String startTime= DateUtil.timeStampToStr(start);
        String endTime= DateUtil.timeStampToStr(stamp);
        System.out.println("京东：startTime："+startTime);
        System.out.println("京东：endTime："+endTime);
        orderReq.setStartTime(startTime);
        orderReq.setEndTime(endTime);
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        getPageOrder(page,liRun,orderReq);
        System.out.println("JdOrder run:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    private void getPageOrder(int page,BigDecimal liRun,OrderRowReq orderReq) throws Exception{
        orderReq.setPageIndex(page);
        request.setOrderReq(orderReq);
        UnionOpenOrderRowQueryResponse response=client.execute(request);
        int code=response.getQueryResult().getCode();//查看返回code是否为200
        if (code == 200 ){
            OrderRowResp[] orderRowResps= response.getQueryResult().getData();
            int length=orderRowResps.length;
            if(null != orderRowResps || length > 0){
              for (OrderRowResp  order:orderRowResps){
                  Long orderId=order.getOrderId();
                  int validCode=order.getValidCode();//订单状态 15.待付款,16.已付款,17.已完成
                  BigDecimal price=new BigDecimal(order.getPrice());//单价
                  int skuNum = order.getSkuNum();//商品数量
                  double finalRate = order.getFinalRate();//最终佣金比例
                  double actualFee = order.getActualFee();//实际计算的佣金金额
                  String orderTime = order.getOrderTime();//创建时间
                  String ext1 = order.getExt1();//自定义参数
                  int ordTime=0;
                  if (StringUtils.isNotEmpty(orderTime)){
                      ordTime=DateUtil.StringToTimestamp(orderTime);
                  }
                  String finishTime = order.getFinishTime();//完成时间
                  int finTime=0;
                  if (StringUtils.isNotEmpty(finishTime)){
                      finTime=DateUtil.StringToTimestamp(finishTime);
                  }
                  EntityWrapper<GoodsOrderEntity> orderWrapper=new EntityWrapper<>();
                  orderWrapper.eq("orderid", orderId);
                  orderWrapper.eq("supplier_id", 1);
                  GoodsOrderEntity orderEntity=goodsOrderService.selectOne(orderWrapper);
                  if (null == orderEntity){//新增
                      BigDecimal amount=new BigDecimal(actualFee);
                      BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                      if (validCode == 15){
                          orderEntity.setOrderStatus(0);
                      }else if (validCode == 16){ //已付款
                          orderEntity.setOrderStatus(1);
                          orderEntity.setIsSettle(0);
                          orderEntity.setIsRrive(1);
                          orderEntity.setFeeMember(freeMember);
                      }else if (validCode == 17){//已完成
                          orderEntity.setOrderStatus(4);
                          orderEntity.setIsSettle(1);
                          orderEntity.setIsRrive(0);
                          orderEntity.setFeeMember(freeMember);
                      }else {
                          orderEntity.setOrderStatus(0);
                      }
                      orderEntity.setSkunum(skuNum);
                      orderEntity.setSkuname(order.getSkuName());
                      orderEntity.setSkuid(order.getSkuId());
                      orderEntity.setOrdertime(ordTime);
                      orderEntity.setFinishtime(finTime);
                      orderEntity.setPrice(price);
                      orderEntity.setSupplierId(1);
                      orderEntity.setOrderid(String.valueOf(orderId));
                      orderEntity.setOrderStatusOld(validCode);
                      if (StringUtils.isNotEmpty(ext1)){
                          orderEntity.setMemberId(Integer.parseInt(ext1));
                      }
                      orderEntity.setPositionid(order.getPid());
                      orderEntity.setReceiveTime(finTime);
                      orderEntity.setActualfee(new Double(actualFee).longValue());
                      orderEntity.setActualcosprice(new BigDecimal(actualFee));
                      orderEntity.setCommissionrate(new Double(finalRate).longValue());
                      orderEntity.setPaymoney(price.multiply(new BigDecimal(skuNum)));
                      orderEntity.setShareMemberId(0);
                      goodsOrderService.insert(orderEntity);
                  }else {//更新
                      BigDecimal amount=new BigDecimal(actualFee);
                      BigDecimal freeMember=amount.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);
                      int status=orderEntity.getOrderStatus();
                      BigDecimal actualcosprice=orderEntity.getActualcosprice();
                      BigDecimal actualfee=new BigDecimal(actualFee);
                      if (status == 4 && actualcosprice.compareTo(actualfee) !=0){
                          orderEntity.setOrderStatus(3);
                      }else {
                          if (validCode == 16){ //已付款
                              orderEntity.setOrderStatus(1);
                              orderEntity.setIsSettle(0);
                              orderEntity.setIsRrive(1);
                              orderEntity.setFeeMember(freeMember);
                          }else if (validCode == 17){//已完成
                              orderEntity.setOrderStatus(4);
                              orderEntity.setIsSettle(1);
                              orderEntity.setIsRrive(0);
                              orderEntity.setFeeMember(freeMember);
                          }
                      }

                      orderEntity.setSkunum(skuNum);
                      orderEntity.setSkuname(order.getSkuName());
                      orderEntity.setSkuid(order.getSkuId());
                      orderEntity.setOrdertime(ordTime);
                      orderEntity.setFinishtime(finTime);
                      orderEntity.setPrice(price);
                      orderEntity.setSupplierId(1);
                      orderEntity.setOrderid(String.valueOf(orderId));
                      orderEntity.setOrderStatusOld(validCode);
                      if (StringUtils.isNotEmpty(ext1)){
                          orderEntity.setMemberId(Integer.parseInt(ext1));
                      }
                      orderEntity.setPositionid(order.getPid());
                      orderEntity.setReceiveTime(finTime);
                      orderEntity.setActualfee(new Double(actualFee).longValue());
                      orderEntity.setActualcosprice(new BigDecimal(actualFee));
                      orderEntity.setCommissionrate(new Double(finalRate).longValue());
                      orderEntity.setPaymoney(price.multiply(new BigDecimal(skuNum)));
                      orderEntity.setShareMemberId(0);
                      goodsOrderService.updateAllColumnById(orderEntity);
                  }
              }
              if (length == 20){
                  page=page+1;
                  getPageOrder(page,liRun,orderReq);
              }
            }
        }
    }
}