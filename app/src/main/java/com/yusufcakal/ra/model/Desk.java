package com.yusufcakal.ra.model;

/**
 * Created by Yusuf on 11.05.2017.
 */

public class Desk {

    private String name, status, tempId;

    public Desk(String name, String status, String tempId) {
        this.name = name;
        this.status = status;
        this.tempId = tempId;
    }

    public Desk() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderId() {
        return tempId;
    }

    public void setOrderId(String orderId) {
        this.tempId = orderId;
    }
}
