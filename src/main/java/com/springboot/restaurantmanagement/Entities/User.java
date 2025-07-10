package com.springboot.restaurantmanagement.Entities;

import com.springboot.restaurantmanagement.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
    @Column(nullable = false)
    private boolean isVerified = false;
    
    @Column(nullable = false)
    private boolean isEnabled = true;
    
    @Column(nullable = false)
    private boolean isAccountNonExpired = true;
    
    @Column(nullable = false)
    private boolean isAccountNonLocked = true;
    
    @Column(nullable = false)
    private boolean isCredentialsNonExpired = true;
    
    @Column(nullable = false)
    private LocalDateTime createdAt;
    
    @Column(nullable = false)
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
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }
    
    @Override
    public String getPassword() {
        return password;
    }
    
    @Override
    public String getUsername() {
        return email;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }
    
    @Override
    public boolean isEnabled() {
        return isEnabled && isVerified;
    }
}
