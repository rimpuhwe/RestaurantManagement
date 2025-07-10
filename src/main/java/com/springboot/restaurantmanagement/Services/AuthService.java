package com.springboot.restaurantmanagement.Services;

import com.springboot.restaurantmanagement.DTOs.*;
import com.springboot.restaurantmanagement.Entities.User;
import com.springboot.restaurantmanagement.Enums.Role;
import com.springboot.restaurantmanagement.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final OtpService otpService;

//    public User registerUser(SignupDto dto) {
//        User user = new User();
//        user.setEmail(dto.getEmail());
//        user.setPassword(dto.getPassword());
//        user.setFullName(dto.getFullName());
//        return userRepository.save(user);
//    }
    /**
     * Register a new user and send OTP for verification
     */
    public AuthResponseDto registerUser(SignupDto dto) {
        // Check if user already exists
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User with this email already exists");
        }

        // Validate role and branch
        validateRoleAndBranch(dto.getRole(), dto.getBranchId());

        // Create new user
        User user = new User();
        user.setFullName(dto.getFullName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // In production, hash this
        user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
        user.setBranchId(dto.getBranchId());
        user.setIsVerified(false);
        user.setProvider("LOCAL");

        // Generate OTP
        String otp = otpService.generateOtp();
        user.setVerificationToken(otp);
        user.setVerificationTokenExpiry(otpService.getOtpExpiryTime());

        // Save user
        userRepository.save(user);

        // Send OTP email
        emailService.sendOtpEmail(user.getEmail(), otp, "SIGNUP");

        log.info("User registered successfully: {}", user.getEmail());

        return new AuthResponseDto(
                "Registration successful! Please check your email for OTP verification.",
                null,
                user.getRole().toString(),
                user.getBranchId(),
                false
        );
    }

    /**
     * Verify OTP and activate user account
     */
    public AuthResponseDto verifyOtp(OtpVerificationDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getIsVerified()) {
            throw new RuntimeException("Account is already verified");
        }

        if (!otpService.validateOtp(dto.getOtp(), user.getVerificationToken(), user.getVerificationTokenExpiry())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Activate user account
        user.setIsVerified(true);
        user.setVerificationToken(null);
        user.setVerificationTokenExpiry(null);
        userRepository.save(user);

        // Send welcome email
        emailService.sendWelcomeEmail(user.getEmail(), user.getFullName(), user.getRole().toString());

        log.info("User verified successfully: {}", user.getEmail());

        return new AuthResponseDto(
                "Account verified successfully! Welcome to Restaurant Management System.",
                null, // TODO: Generate JWT token
                user.getRole().toString(),
                user.getBranchId(),
                true
        );
    }

    /**
     * Resend OTP for verification
     */
    public AuthResponseDto resendOtp(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getIsVerified()) {
            throw new RuntimeException("Account is already verified");
        }

        // Generate new OTP
        String otp = otpService.generateOtp();
        user.setVerificationToken(otp);
        user.setVerificationTokenExpiry(otpService.getOtpExpiryTime());
        userRepository.save(user);

        // Send OTP email
        emailService.sendOtpEmail(user.getEmail(), otp, "SIGNUP");

        log.info("OTP resent successfully: {}", user.getEmail());

        return new AuthResponseDto("OTP resent successfully!", null, null, null, false);
    }

    /**
     * Login user
     */
    public AuthResponseDto loginUser(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!user.getIsVerified()) {
            throw new RuntimeException("Please verify your email first");
        }

        log.info("User logged in successfully: {}", user.getEmail());

        return new AuthResponseDto(
                "Login successful!",
                null, // TODO: Generate JWT token
                user.getRole().toString(),
                user.getBranchId(),
                true
        );
    }

    /**
     * Request password reset
     */
    public AuthResponseDto requestPasswordReset(PasswordResetRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Generate reset OTP
        String otp = otpService.generateOtp();
        user.setResetToken(otp);
        user.setResetTokenExpiry(otpService.getOtpExpiryTime());
        userRepository.save(user);

        // Send reset OTP email
        emailService.sendOtpEmail(user.getEmail(), otp, "RESET");

        log.info("Password reset OTP sent: {}", user.getEmail());

        return new AuthResponseDto("Password reset OTP sent to your email!", null, null, null, false);
    }

    /**
     * Reset password with OTP
     */
    public AuthResponseDto resetPassword(PasswordResetDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!otpService.validateOtp(dto.getOtp(), user.getResetToken(), user.getResetTokenExpiry())) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // Update password
        user.setPassword(dto.getNewPassword()); // In production, hash this
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);

        log.info("Password reset successfully: {}", user.getEmail());

        return new AuthResponseDto("Password reset successfully!", null, null, null, false);
    }

    /**
     * Validate role and branch combination
     */
    private void validateRoleAndBranch(String role, Long branchId) {
        Role userRole = Role.valueOf(role.toUpperCase());

        switch (userRole) {
            case SUPER_ADMIN:
                if (branchId != null) {
                    throw new RuntimeException("Super admin should not have a branch ID");
                }
                break;
            case BRANCH_MANAGER:
            case WAITER:
                if (branchId == null) {
                    throw new RuntimeException("Branch ID is required for " + role);
                }
                break;
        }
    }
}

