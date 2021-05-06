package com.freeter.modules.user.api.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.annotation.Login;
import com.freeter.common.exception.RRException;
import com.freeter.common.util.DateUtil;
import com.freeter.common.utils.R;
import com.freeter.entity.TokenEntity;
import com.freeter.modules.user.entity.MemberAddressEntity;
import com.freeter.modules.user.service.MemberAddressService;
import com.freeter.token.service.TokenService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户地址
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-01-10 15:14:57
 */
@RestController
@RequestMapping("api/memberaddress")
@SuppressWarnings({"unchecked","rawtypes"})
public class MemberAddressController {
    @Autowired
    private MemberAddressService memberAddressService;
    @Autowired
    private TokenService tokenService;

    /**
     * 用户单个收货地址
     * @param request
     * @return
     */
    @RequestMapping("one")
    public R getOne(HttpServletRequest request){
        int memberId=token(request);
        EntityWrapper<MemberAddressEntity> addressWrapper=new EntityWrapper<>();
        addressWrapper.eq("member_id", memberId);
        addressWrapper.eq("is_def", 1);
        MemberAddressEntity memberAddressEntity =memberAddressService.selectOne(addressWrapper);
        if (null == memberAddressEntity){
            EntityWrapper<MemberAddressEntity> ew=new EntityWrapper<>();
            ew.eq("member_id", memberId);
            memberAddressEntity=memberAddressService.selectOne(ew);
        }
        return R.ok().put("addressOne", memberAddressEntity);
    }
    /* *
     *获取用户地址集合
     * @author wangkui
     * @date 2020/1/10 15:47
     * @param [inviteCode]
     * @return com.freeter.common.utils.R
     */
    @PostMapping("list")
    public R getList(HttpServletRequest request){
        int memberId=token(request);
        EntityWrapper<MemberAddressEntity> addressWrapper=new EntityWrapper<>();
        addressWrapper.eq("member_id", memberId);
        List<MemberAddressEntity> list =memberAddressService.selectList(addressWrapper);
        return R.ok().put("addressList", list);
    }
    /* *
     *新增或修改地址
     * @author wangkui
     * @date 2020/1/10 16:16
     * @param [request]
     * @return com.freeter.common.utils.R
     */
    @PostMapping("add")
    public R add(@RequestParam(value = "memberAddressId",defaultValue = "0") Integer memberAddressId,
                 @RequestParam(value = "name",required = false) String name,
                 @RequestParam(value = "mobile",required = false) String mobile,
                 @RequestParam(value = "province",required = false) String province,
                 @RequestParam(value = "city",required = false) String city,
                 @RequestParam(value = "district",required = false) String district,
                 @RequestParam(value = "content",required = false) String content,
                 @RequestParam(value = "isDef",defaultValue = "0") Integer isDef,
                 HttpServletRequest request){
        int memberId=token(request);
        if (memberAddressId==0){ //新增
            MemberAddressEntity addressEntity=new MemberAddressEntity();
            addressEntity.setMemberId(memberId);
            addressEntity.setProvince(province);
            addressEntity.setCity(city);
            addressEntity.setContent(content);
            addressEntity.setDistrict(district);
            if (isDef ==1){
                EntityWrapper<MemberAddressEntity> ew=new EntityWrapper<>();
                ew.eq("member_id", memberId);
                ew.eq("is_def", 1);
                MemberAddressEntity address=memberAddressService.selectOne(ew);
                if (null !=address){
                    address.setIsDef(0);
                    if (!memberAddressService.updateAllColumnById(address)){
                        return R.error(499,"新增收货地址失败");
                    }
                }

            }
            addressEntity.setIsDef(isDef);
            addressEntity.setMobile(mobile);
            addressEntity.setName(name);
            addressEntity.setCreateTime(DateUtil.getUnixStamp());
            addressEntity.setUpdateTime(DateUtil.getUnixStamp());
            if (memberAddressService.insert(addressEntity)){
                return R.ok();
            }else {
                return R.error(499,"新增收货地址失败");
            }
        }else {//更新地址
            MemberAddressEntity addressEntity=memberAddressService.selectById(memberAddressId);
            addressEntity.setProvince(province);
            addressEntity.setCity(city);
            addressEntity.setContent(content);
            addressEntity.setDistrict(district);
            addressEntity.setIsDef(isDef);
            addressEntity.setMobile(mobile);
            addressEntity.setName(name);
            addressEntity.setUpdateTime(DateUtil.getUnixStamp());
            if (isDef ==1){
                EntityWrapper<MemberAddressEntity> ew=new EntityWrapper<>();
                ew.eq("member_id", memberId);
                ew.eq("is_def", 1);
                MemberAddressEntity address=memberAddressService.selectOne(ew);
                if (null !=address && address.getMemberAddressId().intValue()!=memberAddressId.intValue()){
                    address.setIsDef(0);
                    if (!memberAddressService.updateAllColumnById(address)){
                        return R.error(499,"编辑收货地址失败");
                    }
                }
            }
            if (memberAddressService.updateAllColumnById(addressEntity)){
                return R.ok();
            }else {
                return R.error(499,"编辑收货地址失败");
            }
        }
    }
    /* *
     *删除地址
     * @author wangkui
     * @date 2020/1/10 17:05
     * @param [memberAddressId, request]
     * @return com.freeter.common.utils.R
     */
    @PostMapping("delete")
    public R delete(@RequestParam(value = "memberAddressId",defaultValue = "0") Integer memberAddressId,HttpServletRequest request){
        int memberId=token(request);
        EntityWrapper<MemberAddressEntity> ew=new EntityWrapper<>();
        ew.eq("member_id", memberId);
        ew.eq("member_address_id", memberAddressId);
        if (memberAddressService.delete(ew)){
            return R.ok();
        }
        return R.error(499,"删除失败");
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
