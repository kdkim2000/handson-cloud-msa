package com.samsungsds.eshop.order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Iterables;
import com.samsungsds.eshop.cart.CartItem;
import com.samsungsds.eshop.cart.CartService;
import com.samsungsds.eshop.client.ShippingServiceClient;
import com.samsungsds.eshop.payment.Money;
import com.samsungsds.eshop.payment.PaymentRequest;
import com.samsungsds.eshop.payment.PaymentService;
import com.samsungsds.eshop.product.Product;
import com.samsungsds.eshop.product.ProductService;
import com.samsungsds.eshop.shipping.ShippingItem;
import com.samsungsds.eshop.shipping.ShippingRequest;
import com.samsungsds.eshop.shipping.ShippingResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/checkouts")
public class OrderController {
    private final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;
    private final CartService cartService;
    private final PaymentService paymentService;
    private final ProductService productService;
    private final RabbitTemplate rabbitTemplate;
    private final ShippingServiceClient shippingServiceClient;

    public OrderController(final OrderService orderService,
                           final PaymentService paymentService,
                           final CartService cartService,
                           final ProductService productService,
                           final ShippingServiceClient shippingServiceClient,
                           final RabbitTemplate rabbitTemplate) {
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.cartService = cartService;
        this.productService = productService;
        this.rabbitTemplate = rabbitTemplate;
        this.shippingServiceClient = shippingServiceClient;
    }


    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderItemDTO>> getOrderItems(){
        List<OrderItem> orderItemList = orderService.getOrderItems();
        List<OrderItemDTO> result = orderItemList.stream()
                .map(OrderItemDTO::new)
                .peek(dto -> {
                    List<OrderProduct> orderProducts = orderService.getOrderProducts(dto.getId());
                    dto.setOrderProducts(orderProducts);
                    ShippingResult shippingInfo = shippingServiceClient.getShippings(dto.getId());
                    //shippingServiceClient
                    dto.setShippingStatus(shippingInfo.getStatus());
                }).collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }



    @GetMapping(value = "/tests")
    public ResponseEntity<String> test(){
        /* 타서비스 호출을 위한 테스트 부분 추가*/
        //List<Object> ads = adServiceClient.getAds();
        //ads.forEach(System.out::println);

        // Shipping Service로 메세지 전송 하는 부분 추가
        String jsonString = "{\"quantity\":\"30\", \"productId\":\"PRODUCT_01\"}";
        ShippingItem shippingItem = new ShippingItem();
        shippingItem.setQuantity(30);
        shippingItem.setProductId("PRODUCT_NO_1");
        Message shippingMessage = MessageBuilder.withBody(jsonString.getBytes()).setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        String EXCHANGE_NAME="shipping.topic";

        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "order.shipping.first", shippingMessage);

        return ResponseEntity.ok("abcd");
    }

    @DeleteMapping(value = "/orders/{orderId}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Integer orderId) {
        try {
            orderService.removeOrder(orderId);
        } catch (Exception e){
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping(value = "/orders")
    public ResponseEntity<OrderResult> placeOrder(@RequestBody OrderRequest orderRequest) {
        logger.info("placeOrder : " + orderRequest);

        // cart 조회
        CartItem[] cartItems = Iterables.toArray(cartService.getCartItems(), CartItem.class);

        // cart 상품 조회
        Product[] products = getProducts(cartItems);

        // 상품 가격 합계 계산
        Money itemPrice = orderService.calculateItemPrice(cartItems, products);
        logger.info("total item price : " + itemPrice);

        // 예상 배송비 계산
        Money shippingCost = shippingServiceClient.calculateShippingCost(cartItems);

        // 결제 요청
        PaymentRequest request = new PaymentRequest(orderRequest.getCreditCardInfo(), itemPrice.plus(shippingCost));
        paymentService.requestPayment(request);

        // 주문ID 생성
        String orderId = orderService.createOrderId(); // VISUAL ID


        // 배송 요청
        ShippingRequest shippingRequest = new ShippingRequest(cartItems, orderRequest.getAddress());

        //Shipping 정보를 임시로 받아온다
        ShippingResult shippingResult = shippingServiceClient.processShipOrder(shippingRequest);
        logger.info("shippingCost : " + shippingResult.getShippingCost());

        // 총액 계산
        Money totalCost = itemPrice.plus(shippingResult.getShippingCost());
        //TODO: 주문생성 데이터 생성 필요함.
        OrderItem newOrderItem = new OrderItem(
                orderRequest.getEmailAddress(),
                orderRequest.getAddress().toString(),
                orderRequest.getCreditCardInfo().toString(),
                cartItems,
                shippingResult.getId()
        );
        OrderItem order = orderService.createOrder(newOrderItem);
        shippingResult.setOrderId(order.getId());


        // Order를 생성하고 Shipping 데이터를 저장하기때문에 이후 Shipping 상태에서 에러가 발생할 수 있음 이때는 Order 상태를변경해놓아야 한다.
        shippingServiceClient.saveShipping(shippingResult); // Shipping 데이터 저장

        // 카트 비우기
        cartService.emptyCart();
        return ResponseEntity.ok(new OrderResult(orderId, shippingResult.getShippingTrackingId(),
                shippingResult.getShippingCost(), totalCost));
    }

    private Product[] getProducts(CartItem[] cartItems) {
        String[] cartItemIds = Stream.of(cartItems).map(CartItem::getId).toArray(String[]::new);
        return productService.fetchProductsByIds(cartItemIds).getProducts();
    }
}
