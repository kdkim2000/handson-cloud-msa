package com.samsungsds.eshop.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
public class OrderProduct {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer orderId;
    private String productId;
    private String productName;
    private Integer quantity;

    public OrderProduct() { }

    public OrderProduct(Integer id, Integer orderId, String productId, String productName, Integer quantity) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}