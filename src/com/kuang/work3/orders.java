package com.kuang.work3;

import java.io.Serializable;
import java.sql.Date;

public class orders implements Serializable {
    private int orderId;
    private Date orderTime;
    private int productId;

    public orders() {
    }

    public orders(int orderId, Date orderTime, int productId) {
        this.orderId = orderId;
        this.orderTime = orderTime;
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "orders{" +
                "orderId=" + orderId +
                ", orderTime=" + orderTime +
                ", productId=" + productId +
                '}';
    }
}
