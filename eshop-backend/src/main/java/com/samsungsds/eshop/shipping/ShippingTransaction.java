package com.samsungsds.eshop.shipping;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ShippingTransaction implements Serializable {

    Integer orderId;
    ShippingStatus beforeStatus;
    ShippingStatus changeStatus;

}
