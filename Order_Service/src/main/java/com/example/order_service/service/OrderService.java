package com.example.order_service.service;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.dto.OrderItemDtoMapper;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderDto orderDTO)
    {
        Order order = new Order();
        order.setOrderNumber(LocalDate.now() + "-" + UUID.randomUUID());
        order.setOrderItemList(orderDTO.getOrderItemDtoList()
                .stream()
                .map(OrderItemDtoMapper::toOrderItem)
                .collect(Collectors.toList()));

        if (allOrderItemsAreAvailable(order))
        {
            orderRepository.save(order);
            return "Order saved successfully";
        }
        else throw new RuntimeException("Some of requested items are unavailable");
    }

    private Boolean allOrderItemsAreAvailable(Order order)
    {
        return webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory/isAvailable", uriBuilder ->
                        uriBuilder.queryParam("skuCode",
                                        order.getOrderItemList()
                                                .stream()
                                                .map(orderItem -> orderItem.getSkuCode())
                                                .collect(Collectors.toList()))
                                .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
