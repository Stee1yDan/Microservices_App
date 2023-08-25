package com.example.order_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "t_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotEmpty
    @Column(unique = true)
    private String orderNumber;
    @OneToMany(cascade = CascadeType.ALL)
    @NotEmpty
    private List<OrderItem> orderItemList;

}
