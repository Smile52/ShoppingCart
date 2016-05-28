package com.smile.bean;

/**
 * Created by smile on 16/5/27.
 */
public class StoreInfo {
    private String storeName;
    private int shopId;
    private boolean storeCheckStatus;

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return storeName;
    }

    public void setShopName(String shopName) {
        this.storeName = shopName;
    }

    public boolean isStoreCheckStatus() {
        return storeCheckStatus;
    }

    public void setStoreCheckStatus(boolean storeCheckStatus) {
        this.storeCheckStatus = storeCheckStatus;
    }

}
