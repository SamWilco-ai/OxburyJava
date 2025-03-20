package com.wilcock.samuel.oxbury.model;

public class totalQuantModel {

    String product_code;
    float total_quantity;

    public totalQuantModel(String product_code, int total_quantity) {
        this.product_code = product_code;
        this.total_quantity = total_quantity;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public float getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(float total_quantity) {
        this.total_quantity = total_quantity;
    }

    @Override
    public String toString() {
        return "totalQuantModel{" +
                "product_code='" + product_code + '\'' +
                ", total_quantity=" + total_quantity +
                '}';
    }
}
