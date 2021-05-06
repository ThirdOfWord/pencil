package com.freeter.modules.taskOrder.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.taskOrder.entity.GoodsOrderEntity;
import com.freeter.modules.taskOrder.entity.vo.GoodsOrderVO;
import com.freeter.modules.taskOrder.service.GoodsOrderService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 推广商城订单
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-19 22:37:31
 */
@RestController
@RequestMapping("api/goodsorder")
@SuppressWarnings({"unchecked","rawtypes"})
public class GoodsOrderController {
    @Autowired
    private GoodsOrderService goodsOrderService;
    @Autowired
    private TokenService tokenService;

    /**
    * @Description: 获取订单（全部：0、待结算：1、已结算：2、失效：3） 
    * @Author: Mr.Wang 
    * @Date: 2020/5/7 
    */
    @RequestMapping("AllList")
    public R getAllList(@RequestParam(value = "orderStatus",defaultValue = "0")int orderStatus,
                     @RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        int memberId=token(request);
        currentPage=(currentPage-1)*8;
        List<GoodsOrderVO> list=null;
        if (orderStatus == 0){
            list=goodsOrderService.getAllList(memberId,currentPage);
        }else if (orderStatus == 1){
            list=goodsOrderService.getAllListByStatus(memberId,currentPage,orderStatus);
        }else if (orderStatus == 2){
            list=goodsOrderService.getAllListByStatus(memberId,currentPage,orderStatus);
        }else if (orderStatus == 3){
            orderStatus=0;
            list=goodsOrderService.getAllListByStatus(memberId,currentPage,orderStatus);
        }
        return R.ok().put("goodsorder", list);
    }

    /**
     * 订单筛选---根据平台编号
     * @author wangkui
     * @date 2020/3/19 13:17
     * @param[currentPage, request]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("list")
    public R getlist(@RequestParam(value = "supplierId",defaultValue = "0")int supplierId,
            @RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        if (supplierId==0){
            return R.error(499, "参数异常");
        }
        int memberId=token(request);
        currentPage=(currentPage-1)*8;
        return goodsOrderService.getlist(memberId,supplierId,currentPage);
    }
    /**
    * @Description: 测试订单
    * @Author: Mr.Wang 
    * @Date: 2020/5/3 
    */
    @RequestMapping("testAdd")
    public R testAdd(GoodsOrderEntity goodsOrderEntity){
        boolean res=goodsOrderService.insert(goodsOrderEntity);
        if (res)
            return R.ok();
        return R.error("添加失败");
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
