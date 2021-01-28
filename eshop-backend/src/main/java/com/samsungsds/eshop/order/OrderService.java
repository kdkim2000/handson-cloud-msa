package com.samsungsds.eshop.order;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.product.Product;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    Money calculateItemPrice(CartItem[] cartItems, Product[] products) {
        return Stream.of(cartItems).map(
                cartItem -> Stream.of(products)
                        .filter(product -> product.getId().equals(cartItem.getId()))
                        .map(Product::getPriceUsd).findFirst().orElse(new Money())
                        .multiply(cartItem.getQuantity())
        ).reduce(new Money(), Money::plus);
    }

    public String createOrderId() {
        // 원래는 뭔가 많이 해야 하지만 예제이므로 그냥 UUID
        return "ORDER-" + UUID.randomUUID().toString();
    }

    public OrderItem createOrder(OrderItem orderItem){
        return orderRepository.save(orderItem);
    }


    public List<OrderItem> getOrderItems() {
        return (List<OrderItem>) orderRepository.findAll();
    }

    public void deleteOrder(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
