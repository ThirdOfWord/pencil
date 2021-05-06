package com.freeter.modules.packageCard.controller;


import com.freeter.modules.packageCard.service.PackageRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卡包领取记录
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-04-27 18:29:40
 */
@RestController
@RequestMapping("message/packagerecord")
@SuppressWarnings({"unchecked","rawtypes"})
public class PackageRecordController {
    @Autowired
    private PackageRecordService packageRecordService;

}
