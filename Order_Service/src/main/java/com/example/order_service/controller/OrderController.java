package com.example.order_service.controller;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;
    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethod")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderDto orderDto)
    {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderDto));
    }
    public CompletableFuture<String> fallBackMethod(OrderDto orderDto, RuntimeException exception)
    {
        return CompletableFuture.supplyAsync(() -> "Something went wrong");
    }
}
