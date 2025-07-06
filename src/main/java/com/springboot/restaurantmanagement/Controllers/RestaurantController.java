package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.CustomerDto;
import com.springboot.restaurantmanagement.DTOs.RestaurantDto;
import com.springboot.restaurantmanagement.Entities.Customer;
import com.springboot.restaurantmanagement.Entities.Restaurant;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import com.springboot.restaurantmanagement.Services.RestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@RequiredArgsConstructor
@Tag(name = "RestaurantAPI" , description = "this is api responsible to manage all routes for restaurant")
public class RestaurantController {
    private final RestaurantService restaurantService;

    @PostMapping
    @Operation(
            summary = "Create a new restaurant",
            description = "Adds a new restaurant to the system using the provided restaurant details"
    )
    public ResponseEntity<Restaurant> createRestaurant(@Valid @RequestBody RestaurantDto dto) {
        Restaurant restaurant = restaurantService.saveRestaurant(dto);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @PostMapping("/{id}")
    @Operation(
            summary = "Add a customer to a restaurant",
            description = "Associates a new customer with the restaurant identified by the given ID"
    )
    public ResponseEntity<Restaurant> addCustomers(@PathVariable long id, @Valid @RequestBody CustomerDto dto) {
        Restaurant restaurant = restaurantService.saveCustomer(id, dto);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }


    @GetMapping
    @Operation(
            summary = "Retrieve all restaurants",
            description = "Returns a list of all restaurants stored in the system"
    )
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/filter")
    @Operation(
            summary = "Filter restaurants by status and/or address",
            description = "Allows filtering of restaurants by operational status and/or address. Both parameters are optional and can be combined."
    )
    public ResponseEntity<List<Restaurant>> filterRestaurants(@RequestParam(required = false) RestaurantStatus status, @RequestParam(required = false) String address) {
        List<Restaurant> restaurants = restaurantService.filterRestaurants(status, address);
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "Get a restaurant by ID",
            description = "Retrieves the restaurant details for the given restaurant ID"
    )
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable long id) {
        Restaurant restaurant = restaurantService.getRestaurantById(id);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }


    @GetMapping("/{id}/customers")
    @Operation(
            summary = "List customers of a restaurant",
            description = "Returns a list of customers associated with a specific restaurant ID"
    )
    public ResponseEntity<List<Customer>> allCustomers(@PathVariable long id) {
        List<Customer> customers = restaurantService.getCustomers(id);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update restaurant by name",
            description = "Updates the details of the restaurant identified by the given name"
    )
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id,@Valid @RequestBody RestaurantDto dto) {
        Restaurant restaurant = restaurantService.updateRestaurant(id, dto);
        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a restaurant by ID",
            description = "Removes the restaurant record with the specified ID from the system"
    )
    public ResponseEntity<?> deleteRestaurant(@PathVariable long id) {
        restaurantService.deleteRestaurant(id);
        return new ResponseEntity<>("Restaurant with id: "+ id+ " is successful deleted",HttpStatus.OK);
    }



    @DeleteMapping
    @Operation(
            summary = "Delete all restaurants",
            description = "Removes all restaurant records from the system"
    )
    public ResponseEntity<?> deleteAll() {
        restaurantService.deleteAllRestaurant();
        return new ResponseEntity<>("All restaurants are successful deleted",HttpStatus.OK);
    }


}
