package com.springboot.restaurantmanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByEmailAndOtpAndIsUsedFalse(String email, String otp);
    Optional<VerificationToken> findByEmail(String email);
    void deleteByEmail(String email);
}
