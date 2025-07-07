package com.springboot.restaurantmanagement.Repositories;

import com.springboot.restaurantmanagement.Entities.FoodManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<FoodManagement, Long> {
  List<FoodManagement> findByNameContainingIgnoreCase(String name);

}