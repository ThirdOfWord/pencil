package com.freeter.modules.user.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.advert.entity.MessageEntity;
import com.freeter.modules.user.entity.DeductRecordEntity;
import com.freeter.modules.user.entity.MemberEntity;
import com.freeter.modules.user.service.DeductRecordService;
import com.freeter.modules.user.service.MemberService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * 提现记录表
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-22 16:14:16
 */
@RestController
@RequestMapping("api/deductrecord")
@SuppressWarnings({"unchecked","rawtypes"})
public class DeductRecordController {
    @Autowired
    private DeductRecordService deductRecordService;
    @Autowired
    private TokenService tokenService;

    /**
     * 去提现
     * @param money
     * @param request
     * @return
     */
    @RequestMapping("pay")
    public R pay(@RequestParam(value = "money",required = false) BigDecimal money,
                        HttpServletRequest request){
        BigDecimal b=new BigDecimal(1);
        if (money.compareTo(b) == -1){
            return R.error(499, "最低提现1元");
        }
        int memberId=token(request);
        return deductRecordService.pay(memberId,money);
    }

    /**
     * 收益列表
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping("list")
    public R list(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage, HttpServletRequest request){
        int memberId=token(request);
        currentPage=(currentPage-1)*10;
        EntityWrapper<DeductRecordEntity> ew =new EntityWrapper<>();
        ew.eq("member_id", memberId);
        ew.orderBy("create_time", false);
        ew.last("limit "+currentPage+",10");
        List<DeductRecordEntity> list=deductRecordService.selectList(ew);
        return R.ok().put("payList", list);
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
