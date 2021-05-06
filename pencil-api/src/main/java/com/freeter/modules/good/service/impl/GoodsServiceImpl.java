package com.freeter.modules.good.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.freeter.common.util.JedisUtils;
import com.freeter.common.utils.R;
import com.freeter.modules.good.dao.GoodsDao;
import com.freeter.modules.good.entity.GoodsClassifyEntity;
import com.freeter.modules.good.entity.GoodsEntity;
import com.freeter.modules.good.entity.vo.GoodsVO;
import com.freeter.modules.good.service.GoodsClassifyService;
import com.freeter.modules.good.service.GoodsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"unchecked","rawtypes"})
@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {
    private static final JedisUtils jedis=new JedisUtils("116.62.204.50" ,6379,"awH3mHmkNJB87Rxm" );
    @Autowired
    private GoodsClassifyService goodsClassifyService;

    /**
     * 搜索商品
     * @param title
     * @param currentPage
     * @return
     */
    @Override
    public R getSearch(String title, int currentPage) {
        Map<String,Object> map =new HashMap<>();
        map.put("title", title);
        map.put("currentPage",currentPage );
        List<GoodsVO> list=baseMapper.getSearch(map);
        return R.ok().put("goodList",list);
    }

    /**
     * 精选商品
     * @param currentPage
     * @return
     */
    @Override
    public R getChoice(int currentPage) {
        List<GoodsVO> list=baseMapper.getChoice(currentPage);
        return R.ok().put("goodList",list);
    }

    /**
     * 猜你喜欢
     * @param currentPage
     * @return
     */
    @Override
    public R getLike(int currentPage) {
        List<GoodsVO> list=baseMapper.getLike(currentPage);
        return R.ok().put("goodList",list);
    }

    /**
     * 为您推荐
     * @param currentPage
     * @return
     */
    @Override
    public R getRecommend(int currentPage) {
        List<GoodsVO> list=baseMapper.getRecommend(currentPage);
        return R.ok().put("goodList",list);
    }

    /**
     * 商品列表（综合查询）
     * @param pId
     * @param goodsClassifyId
     * @param currentPage
     * @return
     */
    @Override
    public R getComplex(int pId, int goodsClassifyId, int currentPage) {
        Map<String,Object> map =new HashMap<>();
        if (pId==0){//说明是一级分类需要查出二级分类
            String goodsClass=jedis.get(goodsClassifyId+"_goodsClass");
            if (StringUtils.isEmpty(goodsClass)){
                EntityWrapper<GoodsClassifyEntity> ew=new EntityWrapper();
                ew.eq("pid", goodsClassifyId);
                ew.eq("status", 1);
                List<GoodsClassifyEntity> classifyList=goodsClassifyService.selectList(ew);
                String classifyId=new String();
                if (classifyList.isEmpty()){
                    return R.ok().put("goodList",null);
                }else {
                    for (GoodsClassifyEntity list:classifyList){
                        classifyId=classifyId+list.getGoodsClassifyId().toString()+",";
                    }
                    if (StringUtils.isNotEmpty(classifyId)){
                        classifyId=classifyId.substring(0,classifyId.length()-1);
                        classifyId="("+classifyId+")";
                        jedis.setex(goodsClassifyId+"_goodsClass", classifyId,60*60*6);
                    }
                    map.put("classifyId", classifyId);
                    map.put("currentPage", currentPage);
                    List<GoodsVO> goodsVOList=baseMapper.getOneClassify(map);
                    map.clear();
                    return R.ok().put("goodList",goodsVOList);
                }
            }else {
                map.put("classifyId", goodsClass);
                map.put("currentPage", currentPage);
                List<GoodsVO> goodsVOList=baseMapper.getOneClassify(map);
                map.clear();
                return R.ok().put("goodList",goodsVOList);
            }
        }else {//二级分类
            map.put("goodsClassifyId", goodsClassifyId);
            map.put("currentPage", currentPage);
            List<GoodsVO> goodsVOList=baseMapper.getTwoClassify(map);
            map.clear();
            return R.ok().put("goodList",goodsVOList);
        }
    }

    /**
     *商品列表（卷后价查询）
     * @param pId 判断是一级分类（=0）还是二级分类（=1）
     * @param goodsClassifyId
     * @param currentPage
     * @param status 0:升序 1:倒序
     * @return
     */
    @Override
    public R getPrice(int pId, int goodsClassifyId, int currentPage, int status) {
        Map<String,Object> map =new HashMap<>();
        String sort=null;
        if (status==0){
            sort="ASC";
        }else {
            sort="DESC";
        }
        if (pId==0){//说明是一级分类需要查出二级分类
            String goodsClass=jedis.get(goodsClassifyId+"_goodsClass");
            if (StringUtils.isEmpty(goodsClass)){
                EntityWrapper<GoodsClassifyEntity> ew=new EntityWrapper();
                ew.eq("pid", goodsClassifyId);
                ew.eq("status", 1);
                List<GoodsClassifyEntity> classifyList=goodsClassifyService.selectList(ew);
                String classifyId=new String();
                if (classifyList.isEmpty()){
                    return R.ok().put("goodList",null);
                }else {
                    for (GoodsClassifyEntity list:classifyList){
                        classifyId=classifyId+list.getGoodsClassifyId().toString()+",";
                    }
                    if (StringUtils.isNotEmpty(classifyId)){
                        classifyId=classifyId.substring(0,classifyId.length()-1);
                        classifyId="("+classifyId+")";
                        jedis.setex(goodsClassifyId+"_goodsClass", classifyId,60*60*6);
                    }
                    map.put("sort", sort);
                    map.put("classifyId", classifyId);
                    map.put("currentPage", currentPage);
                    List<GoodsVO> goodsVOList=baseMapper.getPriceOneClassify(map);
                    map.clear();
                    return R.ok().put("goodList",goodsVOList);
                }
            }else {
                map.put("sort", sort);
                map.put("classifyId", goodsClass);
                map.put("currentPage", currentPage);
                List<GoodsVO> goodsVOList=baseMapper.getPriceOneClassify(map);
                map.clear();
                return R.ok().put("goodList",goodsVOList);
            }
        }else {//二级分类
            map.put("sort", sort);
            map.put("goodsClassifyId", goodsClassifyId);
            map.put("currentPage", currentPage);
            List<GoodsVO> goodsVOList=baseMapper.getPriceTwoClassify(map);
            map.clear();
            return R.ok().put("goodList",goodsVOList);
        }
    }

    /**
     * 商品列表（销量查询）
     * @param pId
     * @param goodsClassifyId
     * @param currentPage
     * @param status
     * @return
     */
    @Override
    public R getSales(int pId, int goodsClassifyId, int currentPage, int status) {
        Map<String,Object> map =new HashMap<>();
        String sort=null;
        if (status==0){
            sort="ASC";
        }else {
            sort="DESC";
        }
        if (pId==0){//说明是一级分类需要查出二级分类
            String goodsClass=jedis.get(goodsClassifyId+"_goodsClass");
            if (StringUtils.isEmpty(goodsClass)){
                EntityWrapper<GoodsClassifyEntity> ew=new EntityWrapper();
                ew.eq("pid", goodsClassifyId);
                ew.eq("status", 1);
                List<GoodsClassifyEntity> classifyList=goodsClassifyService.selectList(ew);
                String classifyId=new String();
                if (classifyList.isEmpty()){
                    return R.ok().put("goodList",null);
                }else {
                    for (GoodsClassifyEntity list:classifyList){
                        classifyId=classifyId+list.getGoodsClassifyId().toString()+",";
                    }
                    if (StringUtils.isNotEmpty(classifyId)){
                        classifyId=classifyId.substring(0,classifyId.length()-1);
                        classifyId="("+classifyId+")";
                        jedis.setex(goodsClassifyId+"_goodsClass", classifyId,60*60*6);
                    }
                    map.put("sort", sort);
                    map.put("classifyId", classifyId);
                    map.put("currentPage", currentPage);
                    List<GoodsVO> goodsVOList=baseMapper.getSalesOneClassify(map);
                    map.clear();
                    return R.ok().put("goodList",goodsVOList);
                }
            }else {
                map.put("sort", sort);
                map.put("classifyId", goodsClass);
                map.put("currentPage", currentPage);
                List<GoodsVO> goodsVOList=baseMapper.getSalesOneClassify(map);
                map.clear();
                return R.ok().put("goodList",goodsVOList);
            }
        }else {//二级分类
            map.put("sort", sort);
            map.put("goodsClassifyId", goodsClassifyId);
            map.put("currentPage", currentPage);
            List<GoodsVO> goodsVOList=baseMapper.getSalesTwoClassify(map);
            map.clear();
            return R.ok().put("goodList",goodsVOList);
        }
    }
}
