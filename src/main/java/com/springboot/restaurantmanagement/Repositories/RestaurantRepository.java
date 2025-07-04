package com.springboot.restaurantmanagement.Repositories;

import com.springboot.restaurantmanagement.Entities.Restaurant;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByStatusIgnoreCase(RestaurantStatus status);

    List<Restaurant> findByAddressContainingIgnoreCase(String address);

    Restaurant findByNameContainingIgnoreCase(String name);
}