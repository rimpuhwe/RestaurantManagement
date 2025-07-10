package com.springboot.restaurantmanagement.Controllers;

import com.springboot.restaurantmanagement.DTOs.*;
import com.springboot.restaurantmanagement.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody SignupDto signupDto) {
        try {
            AuthResponseDto response = authService.registerUser(signupDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<AuthResponseDto> verifyOtp(@RequestBody OtpVerificationDto otpDto) {
        try {
            AuthResponseDto response = authService.verifyOtp(otpDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<AuthResponseDto> resendOtp(@RequestParam String email) {
        try {
            AuthResponseDto response = authService.resendOtp(email);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {
        try {
            AuthResponseDto response = authService.loginUser(loginDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<AuthResponseDto> forgotPassword(@RequestBody PasswordResetRequestDto resetRequest) {
        try {
            AuthResponseDto response = authService.requestPasswordReset(resetRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<AuthResponseDto> resetPassword(@RequestBody PasswordResetDto resetDto) {
        try {
            AuthResponseDto response = authService.resetPassword(resetDto);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto(e.getMessage(), null, null, null, false));
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth Controller is working!");
    }
}
