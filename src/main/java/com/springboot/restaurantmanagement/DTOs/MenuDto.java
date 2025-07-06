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
public class MenuDto{
    @NotNull
    @NotBlank(message = "it must be either food's name or drink's name")
    String name;

    @NotNull
    @Min(message = "price must be non-negative number", value = 0)
    double price;

    @NotNull
    MenuStatus status;
}