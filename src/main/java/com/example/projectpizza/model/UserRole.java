package com.example.projectpizza.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN("ROLE_ADMIN"),
    GLOBAL_MANAGER("ROLE_GLOBAL_MANAGER"),
    CAFE_MANAGER("ROLE_CAFE_MANAGER");
    private final String authority;

    UserRole(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
