package com.freeter.modules.pingtaiGoods.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName PopInfo
 * @Author WangKui
 * @Date 2020/5/7 18:07
 * @Version 1.0
 **/
public class GoodInfo {

    private Integer supplierId;//所属供应商 1:京东 ,2:拼多多，3：淘宝，4：饿了么，5：天猫
    private List<Integer> activityTags;//商品活动标记数组
    private Integer activityType;//活动
    private String categoryName;//	商品类目名
    private Long cltCpnDiscount;//店铺收藏券面额,单位为分
    private Long goodsId;//商品id
    private String goodsName;//商品名称
    private String goodsDesc;//商品描述
    private String goodsThumbnailUrl;//商品缩略图
    private String goodsImageUrl;//商品主图
    private List<String> goodsGalleryUrls;//商品轮播图
    private Boolean hasCoupon;//商品是否有优惠券 true-有，false-没有
    private String lgstTxt;//	物流分
    private String mallName;//店铺名字
    private Integer merchantType;//店铺类型
    private String optName;//	商品标签名
    private String salesTip;//已售卖件数
    private List<Long> serviceTags;//服务标签
    private String servTxt;//	服务分
    private String descTxt;//	描述分
    private Long minGroupPrice;//最小拼团价（单位为分）
    private Long couponDiscount;//优惠券面额，单位为分
    private Long juanHouPrice;//卷后价，单位为分
    private Long promotionRate;//佣金比例，千分比
    private BigDecimal promotionAmount;//佣金金额

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Long getMinGroupPrice() {
        return minGroupPrice;
    }

    public void setMinGroupPrice(Long minGroupPrice) {
        this.minGroupPrice = minGroupPrice;
    }

    public Long getJuanHouPrice() {
        return juanHouPrice;
    }

    public void setJuanHouPrice(Long juanHouPrice) {
        this.juanHouPrice = juanHouPrice;
    }

    public Long getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(Long promotionRate) {
        this.promotionRate = promotionRate;
    }

    public BigDecimal getPromotionAmount() {
        return promotionAmount;
    }

    public void setPromotionAmount(BigDecimal promotionAmount) {
        this.promotionAmount = promotionAmount;
    }

    public List<Integer> getActivityTags() {
        return activityTags;
    }

    public void setActivityTags(List<Integer> activityTags) {
        this.activityTags = activityTags;
    }

    public Integer getActivityType() {
        return activityType;
    }

    public void setActivityType(Integer activityType) {
        this.activityType = activityType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCltCpnDiscount() {
        return cltCpnDiscount;
    }

    public void setCltCpnDiscount(Long cltCpnDiscount) {
        this.cltCpnDiscount = cltCpnDiscount;
    }

    public Long getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Long couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getGoodsThumbnailUrl() {
        return goodsThumbnailUrl;
    }

    public void setGoodsThumbnailUrl(String goodsThumbnailUrl) {
        this.goodsThumbnailUrl = goodsThumbnailUrl;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public List<String> getGoodsGalleryUrls() {
        return goodsGalleryUrls;
    }

    public void setGoodsGalleryUrls(List<String> goodsGalleryUrls) {
        this.goodsGalleryUrls = goodsGalleryUrls;
    }

    public Boolean getHasCoupon() {
        return hasCoupon;
    }

    public void setHasCoupon(Boolean hasCoupon) {
        this.hasCoupon = hasCoupon;
    }

    public String getLgstTxt() {
        return lgstTxt;
    }

    public void setLgstTxt(String lgstTxt) {
        this.lgstTxt = lgstTxt;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public String getOptName() {
        return optName;
    }

    public void setOptName(String optName) {
        this.optName = optName;
    }

    public String getSalesTip() {
        return salesTip;
    }

    public void setSalesTip(String salesTip) {
        this.salesTip = salesTip;
    }

    public List<Long> getServiceTags() {
        return serviceTags;
    }

    public void setServiceTags(List<Long> serviceTags) {
        this.serviceTags = serviceTags;
    }

    public String getServTxt() {
        return servTxt;
    }

    public void setServTxt(String servTxt) {
        this.servTxt = servTxt;
    }

    public String getDescTxt() {
        return descTxt;
    }

    public void setDescTxt(String descTxt) {
        this.descTxt = descTxt;
    }
}
