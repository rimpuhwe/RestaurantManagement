package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Enums.FoodCategory;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.springboot.restaurantmanagement.Entities.FoodManagement}
 */
@Data
public class FoodManagementDto extends MenuDto {

    @NotNull
    FoodCategory category;
}