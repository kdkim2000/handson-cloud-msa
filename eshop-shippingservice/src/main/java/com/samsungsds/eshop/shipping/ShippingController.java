package com.samsungsds.eshop.shipping;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;


@RestController
@RequestMapping(value = "/shippings")
public class ShippingController {
    private final Logger logger = LoggerFactory.getLogger(ShippingController.class);
    private final ShippingService shippingService;
    private final RabbitTemplate rabbitTemplate;

    public ShippingController(ShippingService shippingService,
                              RabbitTemplate rabbitTemplate) {

        this.shippingService = shippingService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping(value = "/cost")
    public ResponseEntity<Money> calculateShippingCost(@RequestBody List<ShippingItem> shippingList) {
        logger.info("calculateShippingCost");
        int itemCount = shippingList.stream()
                .map(ShippingItem::getQuantity)
                .reduce(0, Integer::sum);
        Money shippingCost = shippingService.calculateShippingCostFromCount(itemCount);
        logger.info("shippingCost : " + shippingCost);
        return ResponseEntity.ok(shippingCost);
    }


    @PostMapping
    public ResponseEntity<ShippingResult> saveShipping(@RequestBody ShippingResult shippingResult){
            return ResponseEntity.ok(shippingService.saveShipping(shippingResult));
    }

    @PostMapping("/cancel/{shippingId}")
    public ResponseEntity<Boolean> cancelShipping(@PathVariable Integer shippingId) throws JsonProcessingException {
        //Order 에 대한 취소 작업을 진행할수 있도록  Message Queue를 이용해 전달한다.
        //TODO 1: 내부 배송 상태값 변경
        ShippingResult updatedResult = shippingService.updateShippingStatus(shippingId, ShippingStatus.FAILED);

        //TODO 2: Backend Service에 큐를 통해서 Transaction 상태 변화 전파
        ShippingTransaction transaction = ShippingTransaction.builder()
                .orderId(updatedResult.getOrderId())
                .beforeStatus(ShippingStatus.READY)
                .changeStatus(ShippingStatus.FAILED)
                .build();

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(transaction);

        Message shippingMessage = MessageBuilder.withBody(jsonString.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        String EXCHANGE_NAME="shipping.topic";

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "order.shipping.first", shippingMessage);

        logger.debug("#### CANCEL SHIPPING : ");


        return ResponseEntity.ok(true);
    }




    @GetMapping
    public ResponseEntity<ShippingResult> getShipping(@RequestParam Integer orderId) {
        ShippingResult shippingResult = shippingService.getShippingResultByOrderId(orderId);
        if (shippingResult == null || orderId == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(shippingResult);
    }

    @PostMapping(value = "/calculate")
    public ResponseEntity<Money> calculateShippingCost(@RequestBody CartItem[] cartItems){
        return ResponseEntity.ok(shippingService.calculateShippingCostFromCartItems(cartItems));
    }

    @PostMapping(value = "/order")
    public ResponseEntity<ShippingResult> processShipOrder(@RequestBody ShippingRequest shippingRequest){
        return ResponseEntity.ok(shippingService.shipOrder(shippingRequest));
    }

}

