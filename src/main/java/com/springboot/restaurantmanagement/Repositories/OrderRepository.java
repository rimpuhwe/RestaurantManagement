package com.springboot.restaurantmanagement.Repositories;

import com.springboot.restaurantmanagement.Entities.Order;
import com.springboot.restaurantmanagement.Enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
    List<Order> findByRestaurantId(Long restaurantId);
    List<Order> findByOrderStatus(OrderStatus status);
}
