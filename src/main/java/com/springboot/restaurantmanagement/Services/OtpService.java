package com.springboot.restaurantmanagement.Services;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    private static final int OTP_LENGTH = 6;
    private static final int OTP_EXPIRY_MINUTES = 10;
    private final Random random = new SecureRandom();

    /**
     * Generate a 6-digit OTP
     */
    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    /**
     * Get OTP expiry time (10 minutes from now)
     */
    public LocalDateTime getOtpExpiryTime() {
        return LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES);
    }

    /**
     * Check if OTP is expired
     */
    public boolean isOtpExpired(LocalDateTime expiryTime) {
        return LocalDateTime.now().isAfter(expiryTime);
    }

    /**
     * Validate OTP
     */
    public boolean validateOtp(String providedOtp, String storedOtp, LocalDateTime expiryTime) {
        if (storedOtp == null || expiryTime == null) {
            return false;
        }

        if (isOtpExpired(expiryTime)) {
            return false;
        }

        return storedOtp.equals(providedOtp);
    }

    /**
     * Generate a secure random token (for longer tokens like verification tokens)
     */
    public String generateSecureToken() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder token = new StringBuilder();

        for (int i = 0; i < 32; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }

        return token.toString();
    }
}
