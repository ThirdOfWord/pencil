package com.freeter.modules.good.controller;
import com.freeter.common.utils.R;
import com.freeter.modules.good.service.GoodsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * 供应商商品
 * 后端接口
 * @author wangkui
 * @email 2589842358@qq.com
 * @date 2020-02-27 16:20:41
 */
@RestController
@RequestMapping("api/goods")
@SuppressWarnings({"unchecked","rawtypes"})
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 搜索商品
     * @param currentPage
     * @param title
     * @return
     */
    @RequestMapping("search")
    public R getSearch(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage,
                       @RequestParam(value = "title",required = false)String title){
        if (StringUtils.isEmpty(title)){
            return R.error(499, "请输入内容");
        }
        currentPage=(currentPage-1)*8;
        return goodsService.getSearch(title,currentPage);
    }
    /**
     * 精选商品
     * @return
     */
    @RequestMapping("choice")
    public R getChoice(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        return goodsService.getChoice(currentPage);
    }

    /**
     * 猜你喜欢
     * @param currentPage
     * @return
     */
    @RequestMapping("like")
    public R getLike(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        return goodsService.getLike(currentPage);
    }
    /**
     * 为您推荐
     * @param currentPage
     * @return
     */
    @RequestMapping("recommend")
    public R getRecommend(@RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        return goodsService.getRecommend(currentPage);
    }

    /**
     * 商品列表（综合查询）
     * @param pId
     * @param goodsClassifyId
     * @param currentPage
     * @return
     */
    @RequestMapping("complex")
    public R getComplex(@RequestParam(value = "pId",defaultValue = "1")int pId,
                        @RequestParam(value = "goodsClassifyId",defaultValue = "0")int goodsClassifyId,
                        @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (goodsClassifyId==0){
            return R.error(499,"参数错误");
        }
        return goodsService.getComplex(pId,goodsClassifyId,currentPage);
    }

    /**
     *商品列表（卷后价查询）
     * @param pId 判断是一级分类（=0）还是二级分类（=1）
     * @param goodsClassifyId 分类编号
     * @param status 0:升序 1:倒序
     * @param currentPage
     * @return
     */
    @RequestMapping("price")
    public R getPrice(@RequestParam(value = "pId",defaultValue = "1")int pId,
                        @RequestParam(value = "goodsClassifyId",defaultValue = "0")int goodsClassifyId,
                        @RequestParam(value = "status",defaultValue = "0")int status,
                        @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (goodsClassifyId==0){
            return R.error(499,"参数错误");
        }
        return goodsService.getPrice(pId,goodsClassifyId,currentPage,status);
    }
    /**
     *商品列表（销量查询）
     * @param pId 判断是一级分类（=0）还是二级分类（=1）
     * @param goodsClassifyId 分类编号
     * @param status 0:升序 1:倒序
     * @param currentPage
     * @return
     */
    @RequestMapping("sales")
    public R getSales(@RequestParam(value = "pId",defaultValue = "1")int pId,
                      @RequestParam(value = "goodsClassifyId",defaultValue = "0")int goodsClassifyId,
                      @RequestParam(value = "status",defaultValue = "0")int status,
                      @RequestParam(value = "currentPage",defaultValue = "1")int currentPage){
        currentPage=(currentPage-1)*8;
        if (goodsClassifyId==0){
            return R.error(499,"参数错误");
        }
        return goodsService.getSales(pId,goodsClassifyId,currentPage,status);
    }

}
