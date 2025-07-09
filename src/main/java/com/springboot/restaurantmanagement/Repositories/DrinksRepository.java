package com.springboot.restaurantmanagement.Repositories;

import com.springboot.restaurantmanagement.Entities.DrinksManagement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinksRepository extends JpaRepository<DrinksManagement, Long> {
  DrinksManagement findByNameContainingIgnoreCase(String name);

  List<DrinksManagement> findAllByPriceBetween(Double price1, Double price2);

}