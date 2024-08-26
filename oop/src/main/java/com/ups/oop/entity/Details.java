package com.ups.oop.entity;
public class Details {
    private int detailsId;
    private int receiptId;
    private String serial;
    private int productId;
    private int prodAmount;
    private int tax;
    private double subtotal;
    private int storeId;

    public int getDetailsId() {
        return detailsId;
    }

    public void setDetailsId(int detailsId) {
        this.detailsId = detailsId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getProdAmount() {
        return prodAmount;
    }

    public void setProdAmount(int prodAmount) {
        this.prodAmount = prodAmount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = (int) tax;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
}
