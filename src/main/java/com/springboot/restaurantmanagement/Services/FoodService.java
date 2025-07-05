package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.FoodDto;
import com.springboot.restaurantmanagement.Entities.FoodManagement;
import com.springboot.restaurantmanagement.Enums.FoodCategory;
import com.springboot.restaurantmanagement.Enums.MenuStatus;
import com.springboot.restaurantmanagement.Repositories.FoodRepository;
import com.springboot.restaurantmanagement.Repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;

    public FoodManagement saveFood(String name,FoodDto dto) {
        var restaurant = restaurantRepository.findByNameContainingIgnoreCase(name);
        FoodManagement food = new FoodManagement();
        food.setName(dto.getName());
        food.setPrice(dto.getPrice());
        food.setCategory(dto.getCategory());
        food.setStatus(dto.getStatus());
        food.setRestaurant(restaurant);
        return foodRepository.save(food);
    }
    public List<FoodManagement> getAllFood() {
        return foodRepository.findAll();
    }
    public List<FoodManagement> getFoodByName(String name) {
        return foodRepository.findByNameContainingIgnoreCase(name);
    }
    public List<FoodManagement> getFoodBy(FoodCategory category, MenuStatus status, String restaurantName) {
        List<FoodManagement> allFoods = foodRepository.findAll(); // get all first

        return allFoods.stream()
                .filter(food -> category == null || food.getCategory() == category)
                .filter(food -> status == null || food.getStatus() == status)
                .filter(food -> restaurantName == null ||
                        (food.getRestaurant() != null &&
                                food.getRestaurant().getName().equalsIgnoreCase(restaurantName)))
                .collect(Collectors.toList());
    }
    public FoodManagement getFoodById(Long id) {
        return foodRepository.findById(id).orElseThrow(()-> new RuntimeException("Could not find food with id: " + id));
    }
    public FoodManagement updateFood(Long id,FoodDto food) {
        var updatedFood = foodRepository.findById(id).orElseThrow(()-> new RuntimeException("Could not find food with id: " + id));
        updatedFood.setName(food.getName());
        updatedFood.setPrice(food.getPrice());
        updatedFood.setCategory(food.getCategory());
        updatedFood.setStatus(food.getStatus());
        return foodRepository.save(updatedFood);
    }
}
