package com.springboot.restaurantmanagement.Repositories;

import com.springboot.restaurantmanagement.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Customer findByNameContainingIgnoreCase(String name);
  Customer findByName(String name);
  List<Customer> findByCreatedAtBetween(LocalDateTime from, LocalDateTime to);
  void deleteByName(String name);

}