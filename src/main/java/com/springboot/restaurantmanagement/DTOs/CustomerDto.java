package com.springboot.restaurantmanagement.DTOs;

import com.springboot.restaurantmanagement.Entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link Customer}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto{
    @NotNull(message = "this field is required")
    @Length(message = "at least 5 characters", min = 5, max = 20)
    String name;

    @Pattern(regexp = "^\\+?\\d{7,15}$", message = "+250.....")
    String phone_number;

    @NotNull(message = "this field is required")
    @Email(regexp = "^[\\w.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z]{2,}$" , message = "provide valid email")
    String email;
}