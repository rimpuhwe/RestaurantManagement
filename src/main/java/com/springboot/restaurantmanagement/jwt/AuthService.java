package com.springboot.restaurantmanagement.jwt;

import com.springboot.restaurantmanagement.DTOs.SignupDto;
import com.springboot.restaurantmanagement.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.springboot.restaurantmanagement.Repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public User registerUser(SignupDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        return userRepository.save(user);
    }
}

