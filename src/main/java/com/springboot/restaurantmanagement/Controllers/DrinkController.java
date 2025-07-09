package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.DrinkDto;
import com.springboot.restaurantmanagement.DTOs.DrinksUpdateDto;
import com.springboot.restaurantmanagement.Entities.DrinksManagement;
import com.springboot.restaurantmanagement.Enums.DrinksCategory;
import com.springboot.restaurantmanagement.Enums.MenuStatus;
import com.springboot.restaurantmanagement.Services.DrinkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
@Tag(name = "Drink management api" , description = "Handles all operations related to Drinks management")
public class DrinkController {

    private final DrinkService service;

    @PostMapping("/{name}")
    @Operation(
            summary = "Add new drink to a restaurant",
            description = "Creates and saves a new drink item under the restaurant identified by its name."
    )
    public ResponseEntity<DrinksManagement> addFood(@PathVariable String name, @Valid @RequestBody DrinkDto dto) {
        DrinksManagement drinks = service.createNew(dto, name);
        return new ResponseEntity<>(drinks, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all drinks items",
            description = "Retrieves a list of all available drink items across all restaurants."
    )
    public ResponseEntity<List<DrinksManagement>> getAllDrinks() {
        List<DrinksManagement> drinks = service.findAll();
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get drink by ID",
            description = "Fetches the drink item identified by the given ID."
    )
    public ResponseEntity<DrinksManagement> getDrinkById(@PathVariable Long id) {
        DrinksManagement drink = service.findById(id);
        return new ResponseEntity<>(drink, HttpStatus.OK);
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Filter drink by category, status and restaurantname ,volume",
            description = "Returns drinks items filtered by DrinkCategory, MenuStatus, and restaurant name, volume."
    )
    public ResponseEntity<List<DrinksManagement>> find(
            @RequestParam(required = false) DrinksCategory category,
            @RequestParam(required = false) MenuStatus status,
            @RequestParam(required = false) String restaurantName,
            @RequestParam(required = false) Double volume,
            @RequestParam(required = false) Double price1,
            @RequestParam(required = false) Double price2) {
        List<DrinksManagement> drinks = service.findBy(restaurantName,category,volume, status,price1,price2);
        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "Update drink item",
            description = "Updates the drink item identified by ID if it belongs to the specified restaurant name."
    )
    public ResponseEntity<DrinksManagement> updateDrink(@PathVariable Long id , @RequestParam String name, @Valid @RequestBody DrinksUpdateDto dto) {
        DrinksManagement updatedDrink = service.update(dto,id, name);
        return new ResponseEntity<>(updatedDrink, HttpStatus.OK);
    }

    @DeleteMapping
    @Operation(
            summary = "Delete all Drinks items",
            description = "Removes all Drinks items from the system."
    )
    public ResponseEntity<?> deleteAll() {
        service.delete();
        return new ResponseEntity<>("successful deleted all Drinks",HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete Drink by ID",
            description = "Deletes the drink item identified by the given ID."
    )
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ResponseEntity<>("successful deleted drink with ID: " + id, HttpStatus.OK);
    }




}
