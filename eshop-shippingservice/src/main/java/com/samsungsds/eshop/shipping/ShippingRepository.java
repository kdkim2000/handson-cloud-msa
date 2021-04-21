package com.samsungsds.eshop.shipping;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingResult, Integer> {

    ShippingResult findShippingResultByOrderId(Integer orderId);
}