package com.springboot.restaurantmanagement.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OtpVerificationDto {
    private String email;
    private String otp;
}
