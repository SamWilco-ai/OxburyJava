package com.wilcock.samuel.oxbury.model;

import java.sql.Date;

public class dataModel {

    String manufacturer;
    String retailer;
    String productCode;
    String transactionID;

    Date transactionDate;
    float quantity;
    float value;

    public dataModel(String manufacturer, String retailer, String productCode, String transactionID, Date transactionDate, float quantity, float value) {
        this.manufacturer = manufacturer;
        this.retailer = retailer;
        this.productCode = productCode;
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.quantity = quantity;
        this.value = value;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "dataModel{" +
                "manufacturer='" + manufacturer + '\'' +
                ", retailer='" + retailer + '\'' +
                ", productCode='" + productCode + '\'' +
                ", transactionID='" + transactionID + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", quantity=" + quantity +
                ", value=" + value +
                '}';
    }
}
