package com.springboot.restaurantmanagement.Enums;

public enum Role {
    SUPER_ADMIN,    // Can manage all branches
    BRANCH_MANAGER, // Can manage single branch
    WAITER          // Can manage assigned tables/orders

}
