package com.smile.bean;

/**
 * Created by smile on 16/5/27.
 */
public class ShoppingCartBean {
    private int id;//ID
    private int storeID;
    private String storeName;//店铺名
    private String goodsName;//商品名
    private boolean goodsCheckStatus;//商品选中状态
    private String goodsImgUrl;//商品图片URL
    private String someInfo;//商品一些信息，例如颜色等
    private double goodsPrice;//商品价格
    private double huiBi;//慧币
    private int amount;//数量
    private boolean storeCheckStatus;//店铺选中状态
    private boolean goodsEditShow;//编辑显示状态

    public ShoppingCartBean() {
        super();
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public boolean isGoodsCheckStatus() {
        return goodsCheckStatus;
    }

    public void setGoodsCheckStatus(boolean goodsCheckStatus) {
        this.goodsCheckStatus = goodsCheckStatus;
    }

    public String getGoodsImgUrl() {
        return goodsImgUrl;
    }

    public void setGoodsImgUrl(String goodsImgUrl) {
        this.goodsImgUrl = goodsImgUrl;
    }

    public String getSomeInfo() {
        return someInfo;
    }

    public void setSomeInfo(String someInfo) {
        this.someInfo = someInfo;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getHuiBi() {
        return huiBi;
    }

    public void setHuiBi(double huiBi) {
        this.huiBi = huiBi;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isStoreCheckStatus() {
        return storeCheckStatus;
    }

    public void setStoreCheckStatus(boolean storeCheckStatus) {
        this.storeCheckStatus = storeCheckStatus;
    }

    public boolean isGoodsEditShow() {
        return goodsEditShow;
    }

    public void setGoodsEditShow(boolean goodsEditShow) {
        this.goodsEditShow = goodsEditShow;
    }
}

