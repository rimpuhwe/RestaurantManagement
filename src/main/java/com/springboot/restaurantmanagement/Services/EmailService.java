package com.springboot.restaurantmanagement.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp, String purpose) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(getSubject(purpose));
            message.setText(getEmailBody(otp, purpose));
            message.setFrom("noreply@restaurantmanagement.com");

            mailSender.send(message);
            log.info("OTP email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send OTP email to: {}", to, e);
            throw new RuntimeException("Failed to send OTP email");
        }
    }

    private String getSubject(String purpose) {
        switch (purpose.toUpperCase()) {
            case "SIGNUP":
                return "Verify Your Email - Restaurant Management";
            case "RESET":
                return "Reset Your Password - Restaurant Management";
            default:
                return "OTP Verification - Restaurant Management";
        }
    }

    private String getEmailBody(String otp, String purpose) {
        StringBuilder body = new StringBuilder();
        body.append("Dear User,\n\n");

        switch (purpose.toUpperCase()) {
            case "SIGNUP":
                body.append("Welcome to Restaurant Management System!\n");
                body.append("Please use the following OTP to verify your email address:\n\n");
                break;
            case "RESET":
                body.append("You requested to reset your password.\n");
                body.append("Please use the following OTP to reset your password:\n\n");
                break;
            default:
                body.append("Your OTP for verification:\n\n");
        }

        body.append("OTP: ").append(otp).append("\n\n");
        body.append("This OTP is valid for 10 minutes.\n");
        body.append("If you didn't request this, please ignore this email.\n\n");
        body.append("Best regards,\n");
        body.append("Restaurant Management Team");

        return body.toString();
    }

    public void sendWelcomeEmail(String to, String fullName, String role) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Welcome to Restaurant Management System!");
            message.setText(getWelcomeEmailBody(fullName, role));
            message.setFrom("noreply@restaurantmanagement.com");

            mailSender.send(message);
            log.info("Welcome email sent successfully to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send welcome email to: {}", to, e);
        }
    }

    private String getWelcomeEmailBody(String fullName, String role) {
        return String.format(
                "Dear %s,\n\n" +
                        "Welcome to Restaurant Management System!\n" +
                        "Your account has been successfully created with the role: %s\n\n" +
                        "You can now log in and start managing your restaurant operations.\n\n" +
                        "Best regards,\n" +
                        "Restaurant Management Team",
                fullName, role
        );
    }
}
