package com.ines.restaurants.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ines.restaurants.dto.RestaurantDTO;
import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.entities.Restaurant;

public interface RestaurantService {
	
	 RestaurantDTO saveRestaurant(RestaurantDTO p);
	 RestaurantDTO updateRestaurant(RestaurantDTO p);
	 
	 RestaurantDTO getRestaurant(Long id);
	 List<RestaurantDTO> getAllRestaurants();
	 
	 

	 void deleteRestaurant(Restaurant p);
     void deleteRestaurantById(Long id);
	
    
	 
	 List<Restaurant> findByNomRestaurant(String nom);
	 List<Restaurant> findByNomRestaurantContains(String nom);
	 List<Restaurant> findByNomNote (String nom, Double note);
	 List<Restaurant> findBySpecialite (Specialite categorie);
	 List<Restaurant> findBySpecialiteIdSpec(Long id);
	 List<Restaurant> findByOrderByNomRestaurantAsc();
	 List<Restaurant> trierRestaurantNomsNote();

	 RestaurantDTO convertEntityToDto(Restaurant r);
	 Restaurant convertDtoToEntity(RestaurantDTO restaurantDto);
	 
	 // Méthodes ajoutées pour ProduitController
	 List<Specialite> getAllSpecialites();
	 Page<Restaurant> getAllRestaurantsParPage(int page, int size);
}