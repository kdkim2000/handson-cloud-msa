package com.samsungsds.eshop.order;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.inventory.InventoryService;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.product.Product;

import com.samsungsds.eshop.product.ProductService;
import net.bytebuddy.utility.visitor.LineNumberPrependingMethodVisitor;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    final OrderRepository orderRepository;
    final OrderProductRepository orderProductRepository;
    final InventoryService inventoryService;
    final ProductService productService;

    public OrderService(OrderRepository orderRepository,
                        OrderProductRepository orderProductRepository,
                        InventoryService inventoryService,
                        ProductService productService
    ) {
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.inventoryService = inventoryService;
        this.productService = productService;
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

    public OrderItem createOrder(OrderItem orderItem) {
        OrderItem createdOrderItem = orderRepository.save(orderItem);

        // Insert Order Product
        CartItem[] cartItems = orderItem.getCartItems();
        Arrays.stream(cartItems).forEach(cartItem -> {
            // Minus Inventory
            inventoryService.minusInventory(cartItem.getId(), cartItem.getQuantity());

            Product product = productService.fetchProductById(cartItem.getId());
            //Insert Order Product history
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrderId(createdOrderItem.getId());
            orderProduct.setProductId(cartItem.getId());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProduct.setProductName(orderProduct.getProductName());
            orderProductRepository.save(orderProduct);
        });

        return createdOrderItem;
    }


    public List<OrderItem> getOrderItems() {
        return (List<OrderItem>) orderRepository.findAll();
    }

    public void deleteOrder(Integer orderId) {
        Optional<OrderItem> orderItem = orderRepository.findById(orderId);
        //Revert Inventory Item count
        orderItem.ifPresent(od -> {
            List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(od.getId());
            orderProducts.stream().forEach(orderProduct -> {
                inventoryService.plusInventory(orderProduct.getProductId(), orderProduct.getQuantity());
            });
        });

        orderRepository.deleteById(orderId);
    }

    public List<OrderProduct> getOrderProducts(Integer orderId) {
        List<OrderProduct> orderProducts = orderProductRepository.findAllByOrderId(orderId);
        return orderProducts.stream().peek(orderProduct -> {
            Product product = productService.fetchProductById(orderProduct.getProductId());
            orderProduct.setProductName(product.getName());
        }).collect(Collectors.toList());
    }
}
