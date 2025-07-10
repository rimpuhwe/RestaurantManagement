package com.springboot.restaurantmanagement.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CUSTOMER,
    WAITER,
    MANAGER,
    ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
