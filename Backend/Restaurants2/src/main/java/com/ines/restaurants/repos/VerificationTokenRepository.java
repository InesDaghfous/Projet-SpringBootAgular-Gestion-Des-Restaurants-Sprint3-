package com.ines.restaurants.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ines.restaurants.entities.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}
