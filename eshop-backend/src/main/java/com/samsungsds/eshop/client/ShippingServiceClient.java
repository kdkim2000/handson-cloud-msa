package com.samsungsds.eshop.client;

import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.shipping.ShippingRequest;
import com.samsungsds.eshop.shipping.ShippingResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="SHIPPING")
public interface ShippingServiceClient {

    @GetMapping("/shippings")
    ShippingResult getShippings(@RequestParam Integer orderId);

    @PostMapping("/shippings/calculate")
    Money calculateShippingCost(CartItem[] cartItems);

    @PostMapping(value = "/shippings/order")
    ShippingResult processShipOrder(ShippingRequest shippingRequest);

    @PostMapping(value = "/shippings")
    ShippingResult saveShipping(@RequestBody ShippingResult shippingResult);

    }
