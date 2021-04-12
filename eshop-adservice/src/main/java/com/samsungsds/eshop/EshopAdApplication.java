package com.samsungsds.eshop;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;


@SpringBootApplication
@EnableEurekaClient
public class EshopAdApplication {
    public static void main(String[] args) {
        SpringApplication.run(EshopAdApplication.class, args);
    }

    @RabbitListener(queues = "ad.queue")
    public void receiveMessage(final String message) {
        System.out.println(message);
    }


}
