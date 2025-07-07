package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.OrderDto;
import com.springboot.restaurantmanagement.Services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Tag(name = "Order API", description = "this api is for controlling the orders flow")
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @Operation(summary = "Create a new order",
            description = "This route accepts an OrderDto and saves the corresponding order to the system"
    )
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        OrderDto savedOrder = orderService.createOrder(orderDto);
        return ResponseEntity.ok(savedOrder);
    }
    @GetMapping
    @Operation(
            summary = "Fetch all orders",
            description = "Retrieves a list of all orders placed in the system"
    )
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<OrderDto> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/{id}")
    @Operation(
            summary = "Fetch specific order by ID",
            description = "Retrieves the details of a single order using its unique identifier"
    )
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete specific order by ID",
            description = "Removes an order from the system using its unique identifier"
    )
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}


