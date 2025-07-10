package com.springboot.restaurantmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto{
    private String message;
    private String token; // JWT token (we'll add this later)
    private String role;
    private Long branchId;
    private boolean isVerified;
}
