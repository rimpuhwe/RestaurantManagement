package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.Entities.OrderItem;
import com.springboot.restaurantmanagement.Repositories.OrderItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> getItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }

    public OrderItem saveItem(OrderItem item) {
        return orderItemRepository.save(item);
    }

    public void deleteItem(Long id) {
        orderItemRepository.deleteById(id);
    }
}

