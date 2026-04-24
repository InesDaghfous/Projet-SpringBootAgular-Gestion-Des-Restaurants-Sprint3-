package com.ines.restaurants.repos;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.entities.Restaurant;

@RepositoryRestResource(path = "rest")
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	List<Restaurant> findByNomRestaurant(String nom);
	List<Restaurant> findByNomRestaurantContains(String nom);   
	
	@Query("select p from Restaurant p where p.nomRestaurant like %:nom and p.noteRestaurant > :note")
	List<Restaurant> findByNomNote (@Param("nom") String nom,@Param("note") Double note);
    
	@Query("select p from Restaurant p where p.specialite = ?1")
	List<Restaurant> findBySpecialite (Specialite specialite);
	
	List<Restaurant> findBySpecialiteIdSpec(Long id);
	
	List<Restaurant> findByOrderByNomRestaurantAsc();
	
	@Query("select p from Restaurant p order by p.nomRestaurant ASC, p.noteRestaurant DESC")
	List<Restaurant> trierRestaurantsNomsNote ();
	              




}
