package com.samsungsds.eshop.shipping;

import java.io.Serializable;

enum ShippingStatus implements Serializable {
    READY("ready"), SHIPPING("shipping"), COMPLETED("completed"), FAILED("failed");

    public String value;

    ShippingStatus(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
