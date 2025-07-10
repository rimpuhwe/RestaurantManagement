package com.springboot.restaurantmanagement.Entities;

import com.springboot.restaurantmanagement.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "full_name")
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // For branch managers
    @Column(name = "branch_id")
    private Long branchId;

    // Email verification
    @Column(name = "is_verified")
    private Boolean isVerified = false;

    @Column(name = "verification_token")
    private String verificationToken;

    @Column(name = "verification_token_expiry")
    private LocalDateTime verificationTokenExpiry;

    // Password reset
    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expiry")
    private LocalDateTime resetTokenExpiry;

    // Google OAuth
    @Column(name = "google_id")
    private String googleId;

    @Column(name = "provider")
    private String provider = "LOCAL"; // LOCAL, GOOGLE

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}


