package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Entities.Restaurant;
import com.springboot.restaurantmanagement.Enums.RestaurantStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Restaurant}
 */
@Value
public class RestaurantDto{
    @NotNull
    @NotBlank(message = "provide the restaurant's address")
    String address;
    @NotNull
    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$\n", message = "phone number must be valid ")
    @NotBlank(message = "this field is required")
    String phone;
    @NotNull(message = "status field required ")
    RestaurantStatus status;
}