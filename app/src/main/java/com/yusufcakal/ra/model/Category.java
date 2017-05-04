package com.yusufcakal.ra.model;

/**
 * Created by Yusuf on 3.05.2017.
 */

public class Category {

    private String catId, name, image;

    public Category() {

    }

    public Category(String catId, String name, String image) {
        this.catId = catId;
        this.name = name;
        this.image = image;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
