package com.ines.restaurants.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ines.restaurants.entities.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Optional<User> findByEmail(String email);
}