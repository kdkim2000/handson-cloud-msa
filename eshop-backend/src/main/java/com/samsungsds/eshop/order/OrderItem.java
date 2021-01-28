package com.samsungsds.eshop.order;

import com.samsungsds.eshop.payment.CreditCardInfo;
import com.samsungsds.eshop.shipping.Address;

import javax.persistence.*;

import static org.aspectj.bridge.Version.getText;

@Entity
public class OrderItem {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String emailAddress;
    private String address;
    private String creditCardInfo;

    public OrderItem(String emailAddress, String address, String creditCardInfo) {
        this.emailAddress = emailAddress;
        this.address = address;
        this.creditCardInfo = creditCardInfo;
    }

    public OrderItem() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(String creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            "}";
    }
}