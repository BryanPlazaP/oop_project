package com.ups.oop.dto;

public class StoreDTO {
    private int storeId;
    private String storeName;
    private String storeBranchName;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreBranchName() {
        return storeBranchName;
    }

    public void setStoreBranchName(String storeBranchName) {
        this.storeBranchName = storeBranchName;
    }
}

