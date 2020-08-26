package com.example.petadmin.model.shoppingcart;

import com.example.petadmin.model.Product;

public class CartLineInfo {

    private Product Product;
    private int quantity;

    public CartLineInfo() {
        this.quantity = 0;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product Product) {
        this.Product = Product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return this.Product.getPrice() * this.quantity;
    }

}
