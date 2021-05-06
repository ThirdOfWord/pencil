package com.freeter.modules.order.controller;


import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.order.entity.ExchangeOrderEntity;
import com.freeter.modules.order.entity.vo.Order_GoodsVO;
import com.freeter.modules.order.service.ExchangeOrderService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 兑换订单
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-20 16:06:26
 */
@RestController
@RequestMapping("api/exchangeorder")
@SuppressWarnings({"unchecked","rawtypes"})
public class ExchangeOrderController {
    @Autowired
    private ExchangeOrderService exchangeOrderService;
    @Autowired
    private TokenService tokenService;

    /**
     * 显示兑换列表兑换商品信息
     * @param currentPage
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("list")
    public R list(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                  @RequestParam(value = "status",defaultValue = "0")int status,HttpServletRequest request){
        int memberId=token(request);
        currentPage=(currentPage-1)*10;
        List<Order_GoodsVO> list=exchangeOrderService.getList(memberId,currentPage,status);
        return R.ok().put("list", list);
    }

    /**
     * 兑换订单确认收货
     * @param exchangeOrderId
     * @param request
     * @return
     */
    @RequestMapping("updateStatus")
    public R updateStatus(@RequestParam(value = "exchangeOrderId",defaultValue = "0")int exchangeOrderId,HttpServletRequest request){
        if(exchangeOrderId==0){
            return R.error(499, "参数错误");
        }
        int memberId=token(request);
        ExchangeOrderEntity orderEntity=exchangeOrderService.selectById(exchangeOrderId);
        if (null !=orderEntity){
            if (orderEntity.getStatus()==3 && orderEntity.getStatus()!=4){
                orderEntity.setStatus(4);
                exchangeOrderService.updateById(orderEntity);
                return R.ok();
            }
        }
        return R.error(499,"已确认收货");
    }
    /**
     * 显示兑换列表兑换商品信息
     * @param currentPage
     * @param status
     * @param request
     * @return
     */
    @RequestMapping("listAll")
    public R listAll(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,HttpServletRequest request){
        int memberId=token(request);
        currentPage=(currentPage-1)*10;
        List<Order_GoodsVO> list=exchangeOrderService.getListAll(memberId,currentPage);
        return R.ok().put("list", list);
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
