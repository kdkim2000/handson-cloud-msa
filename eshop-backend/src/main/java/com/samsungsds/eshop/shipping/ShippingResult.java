package com.samsungsds.eshop.shipping;

import com.samsungsds.eshop.payment.Money;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;

import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ShippingResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer orderId;
    private String shippingTrackingId;
    private Money shippingCost;
    private ShippingStatus status = ShippingStatus.READY;

    public ShippingResult(String shippingTrackingId, Money shippingCost) {
        this.shippingTrackingId = shippingTrackingId;
        this.shippingCost = shippingCost;
    }

    public String getShippingTrackingId() {
        return shippingTrackingId;
    }

    public void setShippingTrackingId(String shippingTrackingId) {
        this.shippingTrackingId = shippingTrackingId;
    }

    public Money getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Money shippingCost) {
        this.shippingCost = shippingCost;
    }

    @Override
    public String toString() {
        return "ShippingResult{" +
                "shippingTrackingId='" + shippingTrackingId + '\'' +
                ", shippingCost=" + shippingCost +
                '}';
    }

    public String getStatus() {
        return status.value;
    }

    public void setStatus(ShippingStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        if ("ready".equals(status)) {
            this.status = ShippingStatus.READY;
        }else if ("shipping".equals(status)) {
            this.status = ShippingStatus.SHIPPING;
        }else if ("completed".equals(status)) {
            this.status = ShippingStatus.COMPLETED;
        } else if ("failed".equals(status)) {
            this.status = ShippingStatus.FAILED;
        }
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
}
