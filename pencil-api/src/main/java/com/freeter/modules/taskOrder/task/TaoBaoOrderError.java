package com.freeter.modules.taskOrder.task;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.util.DateUtil;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.entity.GoodsOrderErrorLogEntity;
import com.freeter.modules.taskOrder.service.GoodsOrderErrorLogService;
import com.freeter.modules.taskOrder.service.GoodsOrderService;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.MemberService;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkOrderDetailsGetRequest;
import com.taobao.api.response.TbkOrderDetailsGetResponse;
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

/**
 * @ClassName TaoBaoOrderError
 * @Author WangKui
 * @Date 2020/8/3 22:48
 * @Version 1.0
 **/
@Component
public class TaoBaoOrderError {
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private GoodsOrderErrorLogService goodsOrderErrorLogService;

    private final static String appkey="30117515";
    private final static String secret="9d3d998debbfc5e9a63fb11b6812cd47";
    private final static String url="http://gw.api.taobao.com/router/rest";
    private TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);

    /**
    * @Description: 1：按照订单淘客创建时间查询
    * @Author: Mr.Wang 
    * @Date: 2020/9/13 
    */
    @Async
    @Scheduled(cron = "0 0/60 * * * ?")
    public void getListOrder(){
        EntityWrapper<GoodsOrderErrorLogEntity> errorLogEntityWrapper = new EntityWrapper<>();
        List<GoodsOrderErrorLogEntity> orderErrorLogList = goodsOrderErrorLogService.selectList(errorLogEntityWrapper);
        getTime(orderErrorLogList);
    }
    public void getTime(List<GoodsOrderErrorLogEntity> goodsOrderList){
        if (!goodsOrderList.isEmpty()) {
            int size = goodsOrderList.size();
            //根据筛选获取开始时间和结束时间
            for (GoodsOrderErrorLogEntity errorLogEntity : goodsOrderList) {
                try {
                    testScheduled(errorLogEntity.getStartTime(),errorLogEntity.getEndTime(),errorLogEntity.getId());
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }

        }
    }

    public void testScheduled(String startTime,String endTime,long id) throws Exception{
        TbkOrderDetailsGetRequest req = new TbkOrderDetailsGetRequest();
        long pageNo=1;
        req.setPageSize(20L);
        req.setStartTime(startTime);
        req.setEndTime(endTime);
        req.setOrderScene(2L);
        req.setQueryType(1L);
        Double min=0.47956456;
        Double max=0.47999999;
        BigDecimal b = new BigDecimal(min + ((max - min) * new Random().nextDouble()));
        BigDecimal liRun = b.setScale(8, BigDecimal.ROUND_DOWN );
        getPageOrder(pageNo,liRun,req,id);
        System.out.println("TaoBaoOrderError run:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }
    private void getPageOrder(long pageNo,BigDecimal liRun,TbkOrderDetailsGetRequest req,long id) throws Exception{
        req.setPageNo(pageNo);
        Thread.sleep(2500);
        boolean isDelete = true;
        try {
            TbkOrderDetailsGetResponse rsp = client.execute(req);
            System.out.println(rsp.getBody());
            boolean hasNext = rsp.getData().getHasNext();
            List<TbkOrderDetailsGetResponse.PublisherOrderDto> results = rsp.getData().getResults();
            if (null != results){
                int size= results.size();
                for (int i = 0; i < size; i++){
                    TbkOrderDetailsGetResponse.PublisherOrderDto orderDto = results.get(i);
                    if (null != orderDto){
                        String orderSn = orderDto.getTradeId();  //订单编号
                        long orderStatus = orderDto.getTkStatus();  //订单状态
                        String totalCommissionFee = orderDto.getTotalCommissionFee();  //佣金金额=结算金额*佣金比率
                        String commissionrate = orderDto.getTotalCommissionRate();  //佣金比率 百分比
                        String paymoney = orderDto.getAlipayTotalPrice();  //买家拍下付款的金额（不包含运费金额）
                        if (StringUtils.isEmpty(paymoney)){
                            paymoney = "0";
                        }
                        String price = orderDto.getItemPrice();  //单价
                        BigDecimal commissionrateBiLi = new BigDecimal(commissionrate).multiply(new BigDecimal(10));//佣金比率 千分比
                        //如果佣金金额等于0， 根据付款金额*佣金比率
                        BigDecimal totalCommissionFeeMoney = new BigDecimal(totalCommissionFee);
                        //平台类型
                        String orderType = orderDto.getOrderType();
                        EntityWrapper<GoodsOrderEntity> orderWrapper=new EntityWrapper<>();
                        orderWrapper.eq("orderid", orderSn);
                        GoodsOrderEntity orderEntity=goodsOrderService.selectOne(orderWrapper);
                        String ralaid = String.valueOf(orderDto.getRelationId());
                        if (null == orderEntity){
                            int memberId = 0;
                            if(StringUtils.isNotEmpty(ralaid)){
                                EntityWrapper<MemberEntity> memberEntityWrapper=new EntityWrapper<>();
                                memberEntityWrapper.eq("relation_id", ralaid);
                                MemberEntity memberEntity=memberService.selectOne(memberEntityWrapper);
                                if (null != memberEntity){
                                    memberId = memberEntity.getMemberId();
                                }
                            }
                            //新增
                            orderEntity = new GoodsOrderEntity();
                            //3：订单结算，12：订单付款， 13：订单失效，14：订单成功
                            if (orderStatus == 13){
                                orderEntity.setOrderStatus(3);
                                orderEntity.setIsSettle(0);
                                orderEntity.setIsRrive(0);
                            }else if (orderStatus == 14){
                                orderEntity.setOrderStatus(4);
                            }else if (orderStatus == 12){
                                orderEntity.setOrderStatus(1);
                                orderEntity.setIsSettle(0);
                                orderEntity.setIsRrive(1);
                            }else if (orderStatus == 3){
                                orderEntity.setOrderStatus(2);
                                orderEntity.setIsSettle(0);
                            }
                            //饿了么
                            if (StringUtils.equals(orderType, "饿了么")){
                                if (orderStatus != 13){
                                    String incomeRate = orderDto.getIncomeRate();//饿了么比率
                                    long actualfee = 0L;
                                    BigDecimal freeMember = new BigDecimal(0);
                                    if (totalCommissionFeeMoney.compareTo(new BigDecimal(0)) == 0){
                                        // 根据付款金额*佣金比率
                                        totalCommissionFeeMoney = new BigDecimal(incomeRate).divide(new BigDecimal(100)).multiply(new BigDecimal(paymoney));
                                        actualfee =totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                        freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                    }else {
                                        //根据佣金金额计算
                                        actualfee =totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                        freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                    }
                                    orderEntity.setFeeMember(freeMember);
                                    orderEntity.setActualfee(actualfee);
                                    orderEntity.setCommissionrate(new BigDecimal(incomeRate).multiply(new BigDecimal(10)).longValue());
                                    orderEntity.setPrice(new BigDecimal(0));
                                }
                                orderEntity.setSupplierId(4);
                            }else {
                                //淘宝或天猫
                                if (orderStatus != 13){
                                    long actualfee = 0L;
                                    BigDecimal freeMember = new BigDecimal(0);
                                    if (totalCommissionFeeMoney.compareTo(new BigDecimal(0)) == 0){
                                        // 根据付款金额*佣金比率
                                        totalCommissionFeeMoney = new BigDecimal(commissionrate).divide(new BigDecimal(100)).multiply(new BigDecimal(paymoney));
                                        actualfee =totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                        freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                    }else {
                                        //根据佣金金额计算
                                        actualfee =totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                        freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                    }
                                    orderEntity.setFeeMember(freeMember);
                                    orderEntity.setActualfee(actualfee);
                                    orderEntity.setCommissionrate(commissionrateBiLi.longValue());
                                    if (StringUtils.isNotEmpty(price)){
                                        orderEntity.setPrice(new BigDecimal(price));
                                    }
                                }
                                orderEntity.setSupplierId(3);
                            }
                            orderEntity.setPaymoney(new BigDecimal(paymoney));
                            orderEntity.setOrdertime(DateUtil.StringToTimestamp(orderDto.getTkCreateTime()));
                            if(StringUtils.isNotEmpty(orderDto.getTbPaidTime())){
                                orderEntity.setPayTime(DateUtil.StringToTimestamp(orderDto.getTbPaidTime()));
                            }
                            if(StringUtils.isNotEmpty(orderDto.getTkEarningTime())){
                                orderEntity.setReceiveTime(DateUtil.StringToTimestamp(orderDto.getTkEarningTime()));
                            }
                            orderEntity.setPositionid(String.valueOf(orderDto.getAdzoneId()));
                            orderEntity.setOrderid(orderSn);
                            orderEntity.setOrderStatusOld((int)orderStatus);
                            orderEntity.setSkuid(orderDto.getItemId());
                            orderEntity.setSkuname(orderDto.getItemTitle());
                            orderEntity.setSkunum(orderDto.getItemNum().intValue());
                            orderEntity.setThumbnailUrl(orderDto.getItemImg());
                            orderEntity.setRelationId(ralaid);
                            orderEntity.setMemberId(memberId);
                            goodsOrderService.insert(orderEntity);
                        }else {
                            //修改
                            int orderStatusOld=orderEntity.getOrderStatusOld();//原先的订单状态
                            if (orderStatus != orderStatusOld) {
                                //3：订单结算，12：订单付款， 13：订单失效，14：订单成功
                                if (orderStatus == 13) {
                                    orderEntity.setOrderStatus(3);
                                    orderEntity.setIsSettle(0);
                                    orderEntity.setIsRrive(0);
                                } else if (orderStatus == 14) {
                                    orderEntity.setOrderStatus(4);
                                } else if (orderStatus == 12) {
                                    orderEntity.setOrderStatus(1);
                                    orderEntity.setIsSettle(0);
                                    orderEntity.setIsRrive(1);
                                } else if (orderStatus == 3) {
                                    orderEntity.setOrderStatus(2);
                                    orderEntity.setIsSettle(0);
                                }
                                //饿了么
                                if (StringUtils.equals(orderType, "饿了么")) {
                                    if (orderStatus != 13) {
                                        String incomeRate = orderDto.getIncomeRate();//饿了么比率
                                        long actualfee = 0L;
                                        BigDecimal freeMember = new BigDecimal(0);
                                        if (totalCommissionFeeMoney.compareTo(new BigDecimal(0)) == 0) {
                                            // 根据付款金额*佣金比率
                                            totalCommissionFeeMoney = new BigDecimal(incomeRate).divide(new BigDecimal(100)).multiply(new BigDecimal(paymoney));
                                            actualfee = totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                            freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                        } else {
                                            //根据佣金金额计算
                                            actualfee = totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                            freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                        }
                                        orderEntity.setFeeMember(freeMember);
                                        orderEntity.setActualfee(actualfee);
                                        orderEntity.setCommissionrate(new BigDecimal(incomeRate).multiply(new BigDecimal(10)).longValue());
                                        orderEntity.setPrice(new BigDecimal(0));
                                        orderEntity.setPaymoney(new BigDecimal(paymoney));
                                    }
                                } else {
                                    //淘宝和天猫
                                    if (orderStatus != 13) {
                                        long actualfee = 0L;
                                        BigDecimal freeMember = new BigDecimal(0);
                                        if (totalCommissionFeeMoney.compareTo(new BigDecimal(0)) == 0) {
                                            // 根据付款金额*佣金比率
                                            totalCommissionFeeMoney = new BigDecimal(commissionrate).divide(new BigDecimal(100)).multiply(new BigDecimal(paymoney));
                                            actualfee = totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                            freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                        } else {
                                            //根据佣金金额计算
                                            actualfee = totalCommissionFeeMoney.multiply(new BigDecimal(100)).longValue();//后台计算 单位“分”
                                            freeMember = totalCommissionFeeMoney.multiply(liRun).setScale(2, BigDecimal.ROUND_DOWN);//app显示的收益 单位“元”
                                        }
                                        orderEntity.setFeeMember(freeMember);
                                        orderEntity.setActualfee(actualfee);
                                        orderEntity.setCommissionrate(commissionrateBiLi.longValue());
                                        orderEntity.setPrice(new BigDecimal(price));
                                        orderEntity.setPaymoney(new BigDecimal(paymoney));
                                    }
                                }

                                orderEntity.setOrdertime(DateUtil.StringToTimestamp(orderDto.getTkCreateTime()));
                                orderEntity.setOrderStatusOld((int) orderStatus);
                                if (StringUtils.isNotEmpty(orderDto.getTbPaidTime())) {
                                    orderEntity.setPayTime(DateUtil.StringToTimestamp(orderDto.getTbPaidTime()));
                                }
                                orderEntity.setPositionid(String.valueOf(orderDto.getAdzoneId()));
                                if (StringUtils.isNotEmpty(orderDto.getTkEarningTime())) {
                                    orderEntity.setReceiveTime(DateUtil.StringToTimestamp(orderDto.getTkEarningTime()));
                                }

                                orderEntity.setSkuid(orderDto.getItemId());
                                orderEntity.setSkuname(orderDto.getItemTitle());
                                orderEntity.setSkunum(orderDto.getItemNum().intValue());
                                orderEntity.setThumbnailUrl(orderDto.getItemImg());
                                orderEntity.setRelationId(ralaid);
                                goodsOrderService.updateAllColumnById(orderEntity);
                            }
                        }
                    }
                }
            }
            if (hasNext){
                pageNo=pageNo+1;
                getPageOrder(pageNo,liRun,req,id);
            }
        }catch (Exception e){
            isDelete = false;
            e.printStackTrace();
        }finally {
            if (isDelete){
                goodsOrderErrorLogService.deleteById(id);
            }
        }
    }
}
