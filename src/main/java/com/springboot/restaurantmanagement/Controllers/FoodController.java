package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.FoodDto;
import com.springboot.restaurantmanagement.Entities.FoodManagement;
import com.springboot.restaurantmanagement.Enums.FoodCategory;
import com.springboot.restaurantmanagement.Enums.MenuStatus;
import com.springboot.restaurantmanagement.Repositories.FoodRepository;
import com.springboot.restaurantmanagement.Services.FoodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods")
@RequiredArgsConstructor
@Tag(name = "Food management api" , description = "Handles all operations related to food management")
public class FoodController {
    private final FoodService service;


    @PostMapping("/{name}")
    public ResponseEntity<FoodManagement> addFood(@PathVariable String name, @Valid @RequestBody FoodDto dto) {
        FoodManagement food = service.saveFood(name, dto);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<FoodManagement>> getAllFood() {
        List<FoodManagement> foods = service.getAllFood();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/food")
    public ResponseEntity<List<FoodManagement>> name(@RequestParam String name) {
        List<FoodManagement> foods = service.getFoodByName(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/filter")
    public ResponseEntity<List<FoodManagement>> name(@RequestParam FoodCategory category, @RequestParam MenuStatus status, @RequestParam String restaurantName) {
        List<FoodManagement> foods = service.getFoodBy(category, status, restaurantName);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<FoodManagement> getFoodById(@PathVariable Long id) {
        FoodManagement food = service.getFoodById(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<FoodManagement> updateFood(@PathVariable Long id ,@RequestParam String name,@Valid @RequestBody FoodDto dto) {
        FoodManagement updatedFood = service.updateFood(id, name, dto);
        return new ResponseEntity<>(updatedFood, HttpStatus.OK);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        service.deleteAllFood();
        return new ResponseEntity<>("successful deleted all food",HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable Long id) {
        service.deleteFood(id);
        return new ResponseEntity<>("successful deleted food with ID: " + id, HttpStatus.OK);
    }
}
