package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Enums.MenuStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.springboot.restaurantmanagement.Entities.Menu}
 */
@Data
public class MenuDto {
    @NotNull
    @NotBlank
    String name;
    @Min(message = "price must be a non-negative ", value = 0)
    double price;
    @NotNull
    MenuStatus status;
}