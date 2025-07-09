package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Enums.MenuStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.springboot.restaurantmanagement.Entities.DrinksManagement}
 */
@Value
public class DrinksUpdateDto {
    @Min(message = "this field must not be null", value = 0)
    double price;
    @NotNull
    MenuStatus status;
    int weight;
}