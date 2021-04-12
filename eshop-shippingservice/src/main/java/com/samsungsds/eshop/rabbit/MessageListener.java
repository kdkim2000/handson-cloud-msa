package com.samsungsds.eshop.rabbit;

import com.samsungsds.eshop.shipping.ShippingItem;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @RabbitListener(queues = "shipping.queue")
    public void receiveShippingChange( String item) {
        System.out.println("## Receive Shipping Item Change ##");
        System.out.println(item.toString());
    }
}
