package com.example.diet.cart.dto;

import com.example.diet.product.dto.Product;

public class Cart {
    private String productId;
    private int unitPrice;
    private int quantity;
    private String billId;

    private int total;

    public Product getProduct() {
        return products;
    }

    public void setProduct(Product products) {
        this.products = products;
    }

    private Product products;

    public Cart(String productId, int unitPrice, int quantity, String billId, int total) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.billId = billId;
        this.total = total;
    }

    public Cart(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}