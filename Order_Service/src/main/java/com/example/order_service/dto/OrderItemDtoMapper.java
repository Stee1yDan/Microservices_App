package com.example.order_service.dto;

import com.example.order_service.model.Order;
import com.example.order_service.model.OrderItem;
import org.springframework.beans.BeanUtils;

public class OrderItemDtoMapper
{
    public static OrderItem toOrderItem(OrderItemDto orderItemDto)
    {
        OrderItem orderItem = new OrderItem();
        BeanUtils.copyProperties(orderItemDto, orderItem);
        return orderItem;
    }

    public static OrderItemDto fromOrderItem(OrderItem orderItem)
    {
        OrderItemDto orderItemDto = new OrderItemDto();
        BeanUtils.copyProperties(orderItem, orderItemDto);
        return orderItemDto;
    }
}
