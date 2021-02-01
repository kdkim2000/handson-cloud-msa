package com.samsungsds.eshop.inventory;

import javax.persistence.*;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String productId;

    private String productName;

    private Integer quantity;

    private String location;

    public Inventory() {
    }

    public Inventory(Integer id, String productId, String productName, Integer quantity, String location) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
