package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Entities.Restaurant;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Restaurant}
 */
@Data
public class RestaurantDto{
    @NotNull
    @NotBlank(message = "provide the restaurant's address")
    String address;

    @NotNull
    @NotBlank(message = "Restaurant's name is required")
    String name;

    @NotNull
    @Pattern(regexp = "^\\+[1-9][0-9]{7,14}$", message = "Phone number must be in international format")
    @NotBlank(message = "this field is required")
    String phone;

    @NotNull(message = "status field required ")
    RestaurantStatus status;
}