package com.springboot.restaurantmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    private String fullName;
    private String email;
    private String password;
    private String role; // "SUPER_ADMIN", "BRANCH_MANAGER", "WAITER"
    private Long branchId; // null for SUPER_ADMIN, required for others
}
