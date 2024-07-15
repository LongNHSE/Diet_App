package com.example.diet.product.dto;

public class Product {
    private String _id;
    private String productName;
    private String productTypeId;
    private int price;
    private int quantity;
    private String brand;
    private String origin;
    private double volume;
    private String effect;
    private double rate;
    private int purchase;
    private String[] image;


    public String getProductId() {
        return _id;
    }


    public void setProductId(String productId) {
        this._id = productId;
    }


    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getType() {
        return productTypeId;
    }


    public void setType(String productTypeId) {
        this.productTypeId = productTypeId;
    }


    public int getPrice() {
        return price;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getBrand() {
        return brand;
    }


    public void setBrand(String brand) {
        this.brand = brand;
    }


    public String getOrigin() {
        return origin;
    }


    public void setOrigin(String origin) {
        this.origin = origin;
    }


    public double getVolume() {
        return volume;
    }


    public void setVolume(double volume) {
        this.volume = volume;
    }


    public String getEffect() {
        return effect;
    }


    public void setEffect(String effect) {
        this.effect = effect;
    }


    public double getRate() {
        return rate;
    }


    public void setRate(double rate) {
        this.rate = rate;
    }


    public int getPurchase() {
        return purchase;
    }


    public void setPurchase(int purchase) {
        this.purchase = purchase;
    }


    public String[] getImages() {
        return image;
    }


    public void setImages(String[] images) {
        this.image = images;
    }
}
