package com.springboot.restaurantmanagement.DTOs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.restaurantmanagement.Enums.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @JsonIgnore
    private Long orderId;
    @NotNull(message = "Customer ID is required")
    private Long customerId;
    @NotNull
    private Long restaurantId;
    @NotNull(message = "Order status is required")
    private OrderStatus orderStatus;

    @NotNull(message = "Order items are required")
    private List<OrderItemDTO> orderItems;
    @JsonIgnore
    private LocalDateTime orderDate;
    private double totalPrice;

}