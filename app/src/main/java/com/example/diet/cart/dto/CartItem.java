package com.example.diet.cart.dto;

public class CartItem {
    private String itemName;
    private int itemPrice; // Price in integer (cents or smallest currency unit)
    private int quantity;

    public CartItem(String itemName, int itemPrice, int quantity) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getQuantity() {
        return quantity;
    }
}
