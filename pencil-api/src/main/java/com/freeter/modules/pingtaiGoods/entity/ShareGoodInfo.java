package com.freeter.modules.pingtaiGoods.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName 分享商品类
 * @Author WangKui
 * @Date 2020/5/7 18:07
 * @Version 1.0
 **/
public class ShareGoodInfo {
    private String goodsName;//商品名称
    private String goodsDesc;//商品描述
    private String goodsThumbnailUrl;//商品缩略图
    private String goodsImageUrl;//商品主图
    private List<String> goodsGalleryUrls;//商品轮播图
    private Long minGroupPrice;//最小拼团价（单位为分）
    private Long couponDiscount;//优惠券面额，单位为分
    private Long juanHouPrice;//卷后价，单位为分
    private Long promotionRate;//佣金比例，千分比
    private BigDecimal promotionAmount;//佣金金额
    private String inviteCode;//邀请码
    private String schemaUrl;//schema的链接
    private String mobileShortUrl;//唤醒拼多多app的推广短链接
    private String shortUrl;//推广短链接
    private Integer goodsShareLinkId;//分享者

    public Integer getGoodsShareLinkId() {
        return goodsShareLinkId;
    }

    public void setGoodsShareLinkId(Integer goodsShareLinkId) {
        this.goodsShareLinkId = goodsShareLinkId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getSchemaUrl() {
        return schemaUrl;
    }

    public void setSchemaUrl(String schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    public String getMobileShortUrl() {
        return mobileShortUrl;
    }

    public void setMobileShortUrl(String mobileShortUrl) {
        this.mobileShortUrl = mobileShortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
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

    public Long getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Long couponDiscount) {
        this.couponDiscount = couponDiscount;
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
}
