package com.example.order_service.dto;

import com.example.order_service.model.Order;
import org.springframework.beans.BeanUtils;

public class OrderDtoMapper
{
    public static Order toOrder(OrderDto orderDTO)
    {
        Order order = new Order();
        BeanUtils.copyProperties(orderDTO, order);
        return order;
    }

    public static OrderDto fromOrder(Order order)
    {
        OrderDto orderDTO = new OrderDto();
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

}
