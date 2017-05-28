package com.yusufcakal.ra.model;

/**
 * Created by Yusuf on 28.05.2017.
 */

public class OrderHistory {

    private int orderId, status;
    private String date;
    private double priceTotal;

    public OrderHistory(int orderId, int status, String date, double priceTotal) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
        this.priceTotal = priceTotal;
    }

    public OrderHistory() {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }
}
