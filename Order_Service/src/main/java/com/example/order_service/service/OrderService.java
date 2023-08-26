package com.example.order_service.service;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.dto.OrderItemDtoMapper;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderDto orderDTO)
    {
        Order order = new Order();
        order.setOrderNumber(LocalDate.now() + "-" + UUID.randomUUID());
        order.setOrderItemList(orderDTO.getOrderItemDtoList()
                .stream()
                .map(OrderItemDtoMapper::toOrderItem)
                .collect(Collectors.toList()));

        if (allItemsAvailable(order.getOrderItemList().stream().map(orderItem -> orderItem.getSkuCode()).collect(Collectors.toList())))
            orderRepository.save(order);
        else throw new RuntimeException("Some of requested items are unavailable");
    }

    private Boolean allItemsAvailable(List<String> skuCodes)
    {
        return webClient.get()
                .uri("http://localhost:8082/api/inventory/isAvailable", uriBuilder ->
                        uriBuilder.queryParam("skuCode",skuCodes).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
