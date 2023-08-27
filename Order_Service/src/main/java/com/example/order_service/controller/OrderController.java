package com.example.order_service.controller;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderDto orderDto)
    {
        orderService.placeOrder(orderDto);
    }
}
