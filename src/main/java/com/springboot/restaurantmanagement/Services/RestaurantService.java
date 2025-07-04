package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.CustomerDto;
import com.springboot.restaurantmanagement.DTOs.RestaurantDto;
import com.springboot.restaurantmanagement.Entities.Customer;
import com.springboot.restaurantmanagement.Entities.Restaurant;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import com.springboot.restaurantmanagement.Repositories.CustomerRepository;
import com.springboot.restaurantmanagement.Repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final CustomerRepository customerRepository;

    public Restaurant saveRestaurant(RestaurantDto dto) {
        Restaurant  restaurant = new Restaurant();
        restaurant.setAddress(dto.getAddress());
        restaurant.setName(dto.getName());
        restaurant.setPhone(dto.getPhone());
        restaurant.setStatus(dto.getStatus());
        return restaurantRepository.save(restaurant);
    }
    public Restaurant saveCustomer(Long restaurantId, CustomerDto customerDto) {
        var restaurant = restaurantRepository.findById(restaurantId).orElseThrow(()->new RuntimeException("Restaurant not found"));
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone_number(customerDto.getPhone_number());

        restaurant.getCustomers().add(customer);

        return restaurantRepository.save(restaurant);
    }
    public List<Restaurant> getAllRestaurants() {
        return  restaurantRepository.findAll();
    }
    public List<Restaurant> filterRestaurants(RestaurantStatus status, String address) {
        if (status != null && address != null) {
            return restaurantRepository.findByStatusAndAddressContainingIgnoreCase(status, address);
        } else if (status != null) {
            return restaurantRepository.findByStatus(status);
        } else if (address != null) {
            return restaurantRepository.findByAddressContainingIgnoreCase(address);
        } else {
            return restaurantRepository.findAll();
        }
    }
    public List<Customer> getCustomers(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return restaurant.getCustomers();
    }
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
    }
    public Restaurant updateRestaurant(String name,RestaurantDto dto) {
        var restaurant = restaurantRepository.findByNameContainingIgnoreCase(name);
        restaurant.setName(dto.getName());
        restaurant.setPhone(dto.getPhone());
        restaurant.setStatus(dto.getStatus());
        restaurant.setAddress(dto.getAddress());
        return restaurantRepository.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        var restaurant = restaurantRepository.findById(id).orElseThrow(()->new RuntimeException("Restaurant not found"));
        restaurantRepository.delete(restaurant);
    }
    public void deleteRestaurant(String name) {
        var restaurant = restaurantRepository.findByNameContainingIgnoreCase(name);
        restaurantRepository.delete(restaurant);
    }
    public void deleteAllRestaurant() {
        restaurantRepository.deleteAll();
    }


}

