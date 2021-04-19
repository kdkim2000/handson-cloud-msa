package com.samsungsds.eshop.listener;

import com.samsungsds.eshop.order.OrderService;
import com.samsungsds.eshop.shipping.ShippingTransaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShippingChangeListener {

    final OrderService orderService;

    public ShippingChangeListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = "shipping.queue")
    public void receiveShippingChange(ShippingTransaction transaction) {
        //SpringTransaction
        //DATA EXAMPLE : ShippingTransaction(orderId=1, beforeStatus=READY, changeStatus=FAILED)

        log.debug("# Receive Change Shipping Data  :  " + transaction.toString());
        orderService.cancelOrder(transaction.getOrderId());
    }

}
