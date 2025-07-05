package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Enums.FoodCategory;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DTO for {@link com.springboot.restaurantmanagement.Entities.FoodManagement}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FoodDto extends MenuDto {
    FoodCategory category;
}