package com.example.order_service.service;

import com.example.order_service.dto.OrderDto;
import com.example.order_service.dto.OrderItemDtoMapper;
import com.example.order_service.model.Order;
import com.example.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    public void placeOrder(OrderDto orderDTO)
    {
        Order order = new Order();
        order.setOrderNumber(LocalDate.now().toString() + " " + UUID.randomUUID().toString());
        order.setOrderItemList(orderDTO.getOrderItemDtoList()
                .stream()
                .map(OrderItemDtoMapper::toOrderItem)
                .collect(Collectors.toList()));
        orderRepository.save(order);
    }
}
