package com.example.orderPublisher.functions;

import com.example.orderPublisher.domains.Order;
import com.example.orderPublisher.service.OrderService;
import com.example.orderPublisher.util.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class OrderPublish {
    @Autowired
    OrderService orderService;

    @Bean("order")
    public Function<Order, ResponseDTO> order() {
        return user -> orderService.orderPublishWithServiceBus(user);
    }

}
