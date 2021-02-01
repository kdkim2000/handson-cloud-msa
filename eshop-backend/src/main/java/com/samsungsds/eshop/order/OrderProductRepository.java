package com.samsungsds.eshop.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends CrudRepository<OrderProduct, Integer> {
    List<OrderProduct> findAllByOrderId(Integer orderId);
}
