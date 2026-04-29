package com.ines.restaurants.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ines.restaurants.entities.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
