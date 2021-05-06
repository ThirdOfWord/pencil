package com.freeter.modules.pingtaiGoods.entity;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * @ClassName PopInfo
 * @Author WangKui
 * @Date 2020/5/7 18:07
 * @Version 1.0
 **/
public class TaoLiJinInfo {
    private Integer goodsGoldRushId;

    private Long goodsId;//商品id

    private String goodsName;//商品名称

    private String goodsThumbnailUrl;//商品缩略图

    private BigDecimal juanHouPrice;//卷后价

    private BigDecimal subsidyPrice;//补贴价

    @ApiModelProperty(value = "领取链接")
    private String urlLink;

    public Integer getGoodsGoldRushId() {
        return goodsGoldRushId;
    }

    public void setGoodsGoldRushId(Integer goodsGoldRushId) {
        this.goodsGoldRushId = goodsGoldRushId;
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

    public String getGoodsThumbnailUrl() {
        return goodsThumbnailUrl;
    }

    public void setGoodsThumbnailUrl(String goodsThumbnailUrl) {
        this.goodsThumbnailUrl = goodsThumbnailUrl;
    }

    public BigDecimal getJuanHouPrice() {
        return juanHouPrice;
    }

    public void setJuanHouPrice(BigDecimal juanHouPrice) {
        this.juanHouPrice = juanHouPrice;
    }

    public BigDecimal getSubsidyPrice() {
        return subsidyPrice;
    }

    public void setSubsidyPrice(BigDecimal subsidyPrice) {
        this.subsidyPrice = subsidyPrice;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }
}
