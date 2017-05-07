package com.yusufcakal.ra.model;

import java.util.List;

/**
 * Created by Yusuf on 7.05.2017.
 */

public class ProductBasketDetail {

    private double price;
    private int piece;
    private String name;
    private List<String> imageList;

    public ProductBasketDetail(double price, int piece, String name, List<String> imageList) {
        this.price = price;
        this.piece = piece;
        this.name = name;
        this.imageList = imageList;
    }

    public ProductBasketDetail() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPiece() {
        return piece;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
