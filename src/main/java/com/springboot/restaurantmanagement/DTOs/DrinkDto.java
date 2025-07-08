package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Enums.DrinksCategory;
import jakarta.validation.constraints.Min;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.springboot.restaurantmanagement.Entities.DrinksManagement}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DrinkDto extends MenuDto{
    @Min(message = "it measured in milliliters", value = 0)
    int weight;
    @Min(message = "provide the alcoholic volume", value = 0)
    double volume;
    DrinksCategory category;
}