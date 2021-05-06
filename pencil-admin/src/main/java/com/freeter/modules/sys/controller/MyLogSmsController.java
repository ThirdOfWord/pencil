package com.freeter.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.freeter.common.utils.PageUtils;
import com.freeter.common.utils.R;
import com.freeter.modules.sys.entity.MyLogSmsEntity;
import com.freeter.modules.sys.service.MyLogSmsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 
 * 后端接口
 * @author xuchen
 * @email 171998110@qq.com
 * @date 2019-05-31 17:14:44
 */
@RestController
@RequestMapping("sys/myLogSms")
public class MyLogSmsController {
    @Autowired
    private MyLogSmsService myLogSmsService;

    /* *
     *短信日志
     * @author wangkui
     * @date 2019/5/31 17:18
     * @param [params]
     * @return com.freeter.common.utils.R
     */
    @ResponseBody
    @RequestMapping("/list")
    @RequiresPermissions("sys/myLogSms:list")
    public R list(@RequestParam Map<String, Object> params,@RequestParam(value = "key",required = false)String key){
        EntityWrapper<MyLogSmsEntity> wrapper = new EntityWrapper<MyLogSmsEntity>();
        wrapper.like("smsPhoneNumber",key);
        PageUtils page = new PageUtils(myLogSmsService.queryPage(params,wrapper));
        return R.ok().put("page", page);
    }
}
