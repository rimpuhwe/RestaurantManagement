package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.OrderDto;
import com.springboot.restaurantmanagement.DTOs.OrderItemDTO;
import com.springboot.restaurantmanagement.Entities.*;
import com.springboot.restaurantmanagement.Enums.OrderStatus;
import com.springboot.restaurantmanagement.Repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderDto createOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer with ID " + orderDto.getCustomerId() + " not found"));

        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + orderDto.getRestaurantId() + " not found"));

        Order order = new Order();
        order.setCustomer(customer);
        order.setRestaurant(restaurant);

        // Default to PENDING if not set
        order.setOrderStatus(orderDto.getOrderStatus() != null
                ? orderDto.getOrderStatus()
                : OrderStatus.PENDING);

        List<OrderItem> items = new ArrayList<>();
        double totalPrice = 0;

        for (OrderItemDTO itemDto : orderDto.getOrderItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProductId(itemDto.getProductId());
            item.setQuantity(itemDto.getQuantity());
            totalPrice += itemDto.getPrice() * itemDto.getQuantity();
            items.add(item);
        }

        order.setOrderItems(items);
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        // Fill out OrderDto for response
        orderDto.setOrderId(savedOrder.getId());
        orderDto.setOrderDate(savedOrder.getOrderDate());
        orderDto.setTotalPrice(savedOrder.getTotalPrice());

        return orderDto;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(this::convertToDto).toList();
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        return convertToDto(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    // 🧠 DTO converter helper
    private OrderDto convertToDto(Order order) {
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream().map(item -> {
            OrderItemDTO dto = new OrderItemDTO();
            dto.setProductId(item.getProductId());
            dto.setQuantity(item.getQuantity());
            return dto;
        }).toList();

        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setCustomerId(order.getCustomer().getId());
        dto.setRestaurantId(order.getRestaurant().getId());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderItems(itemDTOs);

        return dto;
    }
}
