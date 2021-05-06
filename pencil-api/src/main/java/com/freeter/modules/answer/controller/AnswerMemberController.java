package com.freeter.modules.answer.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.exception.RRException;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.answer.entity.AnswerClassifyEntity;
import com.freeter.modules.answer.entity.vo.AnswerMemberVO;
import com.freeter.modules.answer.service.AnswerClassifyService;
import com.freeter.modules.answer.service.AnswerMemberService;
import com.freeter.modules.good.entity.ExchangeGoodsEntity;
import com.freeter.modules.good.service.ExchangeGoodsService;
import com.freeter.modules.user.entity.MemberAddressEntity;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户拥有的答题卡数
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 15:14:57
 */
@RestController
@RequestMapping("api/answermember")
@SuppressWarnings({"unchecked","rawtypes"})
public class AnswerMemberController {
    @Autowired
    private AnswerMemberService answerMemberService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ExchangeGoodsService exchangeGoodsService;
    @Autowired
    private AnswerClassifyService answerClassifyService;
    /* *
     *我的卡包
     * @author wangkui 
     * @date 2020/1/14 14:18
     * @param [request]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("list")
    public R getList( HttpServletRequest request){
        int number=0;
        Map<String,Object> map=new HashMap<>();
        synchronized (this){
            int memberId=token(request);
            List<AnswerMemberVO> list=answerMemberService.getList(memberId);
            if (!list.isEmpty()){
                for (AnswerMemberVO answerMemberVO:list){
                    int answerClassifyId=answerMemberVO.getAnswerClassifyId();
                    int total=answerMemberVO.getTotal().intValue();
                    EntityWrapper<ExchangeGoodsEntity> ew=new EntityWrapper();
                    ew.eq("answer_classify_id",answerClassifyId);
                    ew.eq("status",1);
                    ExchangeGoodsEntity goodsEntity =exchangeGoodsService.selectOne(ew);
                    if (null != goodsEntity){
                        number=number+total;
                    }
                }
                map.put("answermember", list);
                map.put("number", number);
            }
        }
        return R.ok(map);
    }
    /**
    * @Description: 去答题显示我的卡包（禁止显示最高级卡）
    * @Author: Mr.Wang 
    * @Date: 2020/5/26 
    */
    @RequestMapping("getList")
    public R List( HttpServletRequest request){
        synchronized (this){
            int memberId=token(request);
            List<AnswerMemberVO> list=answerMemberService.getList(memberId);
            if (!list.isEmpty()){
                int number=list.size();
                int sortsid = list.get(number-1).getSortsid();
                EntityWrapper<AnswerClassifyEntity> entityWrapper=new EntityWrapper();
                entityWrapper.eq("status", 1);
                entityWrapper.eq("sortsid", sortsid+1);
                AnswerClassifyEntity classifyEntity =answerClassifyService.selectOne(entityWrapper);
                if (null == classifyEntity){
                    list.remove(number-1);
                }
            }
            return R.ok().put("answermember", list);
        }
    }
    /*@PostMapping("list")
    public R getList(@RequestParam(value = "status",defaultValue = "0")Integer status, HttpServletRequest request){
        int number=0;
        int memberId=token(request);
        List<AnswerMemberVO> list=answerMemberService.getList(memberId);
        if (status==1){
            if (!list.isEmpty()){
                list=list.stream().filter(s-> s.getMergeId()>0).collect(Collectors.toList());
            }
            return R.ok().put("answermember", list);
        }else {
            Map<String,Object> map=new HashMap<>();
            if (!list.isEmpty()){
                for (AnswerMemberVO answerMemberVO:list){
                    int answerClassifyId=answerMemberVO.getAnswerClassifyId();
                    int total=answerMemberVO.getTotal().intValue();
                    EntityWrapper<ExchangeGoodsEntity> ew=new EntityWrapper();
                    ew.eq("answer_classify_id",answerClassifyId);
                    ew.eq("status",1);
                    ExchangeGoodsEntity goodsEntity =exchangeGoodsService.selectOne(ew);
                    if (null != goodsEntity){
                        number=number+total;
                    }
                }
            }
            map.put("answermember", list);
            map.put("number", number);
            return R.ok(map);
        }
    }*/

    /**
     * 显示可兑换的卡包
     * @param request
     * @return
     */
    @RequestMapping("duihuan")
    public R getDuihuan(HttpServletRequest request){
        int memberId=token(request);
        List<AnswerMemberVO> list=answerMemberService.getDuihuan(memberId);
        return R.ok().put("duihuan", list);
    }
    /**
     * 查看当前卡是否可以合并或拆分
     * @param answerClassifyId
     * @param status 0:合并当前卡，1：拆分当前卡
     * @param request
     * @return
     */
    @RequestMapping("check")
    public R check(@RequestParam(value = "answerClassifyId",defaultValue = "0")int answerClassifyId,
                   @RequestParam(value = "status",defaultValue = "0")int status,
                   HttpServletRequest request){
        if (answerClassifyId==0){
            return R.error(499,"参数错误");
        }
        int memberId=token(request);
        return answerMemberService.check(answerClassifyId,memberId,status);
    }
    /* *
     *获取合并升级当前卡需要的卡
     * @author wangkui
     * @date 2020/1/14 15:05
     * @param [answerClassifyId, request]
     * @return com.freeter.common.utils.R
     */
    @RequestMapping("checkUpgrade")
    public R checkUpgrade(@RequestParam(value = "answerClassifyId",defaultValue = "0")int answerClassifyId, HttpServletRequest request){
        if (answerClassifyId==0){
            return R.error(499,"参数错误");
        }
        int memberId=token(request);
        return answerMemberService.checkUpgrade(answerClassifyId,memberId);
    }
    /* *
     *合并升级当前卡
     * @author wangkui
     * @date 2020/1/14 16:06
     * @param [answerClassifyId, request]
     * @return com.freeter.common.utils.R
     */
    @PostMapping("upgrade")
    public R upgrade(@RequestParam(value = "answerClassifyId",defaultValue = "0")int answerClassifyId, HttpServletRequest request){
        if (answerClassifyId==0){
            return R.error(499,"参数错误");
        }
        int memberId=token(request);
        return answerMemberService.upgrade(answerClassifyId,memberId);
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
