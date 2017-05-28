package com.yusufcakal.ra.model;

import java.util.List;

/**
 * Created by Yusuf on 5.05.2017.
 */

public class Product {

    private int productId, star, categoryId, piece, basketID;
    private String name, description, image;
    private double price;
    private List<String> imageList;

    public Product() {
    }

    public Product(int productId, int star, int categoryId, String name, String description, double price, List<String> imageList) {
        this.productId = productId;
        this.star = star;
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageList = imageList;
    }

    public Product(int piece, String name, String image, double price, int basketID) {
        this.piece = piece;
        this.name = name;
        this.image = image;
        this.price = price;
        this.basketID = basketID;
    }

    public int getBasketID() {
        return basketID;
    }

    public void setBasketID(int basketID) {
        this.basketID = basketID;
    }

    public int getPiece() {
        return piece;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPiece(int piece) {
        this.piece = piece;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
