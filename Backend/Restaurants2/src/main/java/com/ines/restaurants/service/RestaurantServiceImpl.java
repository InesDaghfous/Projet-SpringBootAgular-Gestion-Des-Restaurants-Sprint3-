package com.ines.restaurants.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ines.restaurants.dto.RestaurantDTO;
import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.repos.SpecialiteRepository;
import com.ines.restaurants.repos.RestaurantRepository;
import com.ines.restaurants.entities.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	SpecialiteRepository specialiteRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	
	@Override
	public RestaurantDTO saveRestaurant(RestaurantDTO r) {
	    Restaurant restaurant = convertDtoToEntity(r);
	    return convertEntityToDto(restaurantRepository.save(restaurant));
	}

	@Override
	public RestaurantDTO updateRestaurant(RestaurantDTO r) {
	    Restaurant restaurant = convertDtoToEntity(r);
	    return convertEntityToDto(restaurantRepository.save(restaurant));
	}

	@Override
	public void deleteRestaurant(Restaurant r) {
		restaurantRepository.delete(r);

	}

   @Override
	public void deleteRestaurantById(Long id) {
		restaurantRepository.deleteById(id);
		
	}

	@Override
	public RestaurantDTO getRestaurant(Long id) {
		return   convertEntityToDto( restaurantRepository.findById(id).get() );
	
	}

	@Override
	public List<RestaurantDTO> getAllRestaurants() {
		return restaurantRepository.findAll().stream()
				.map(this::convertEntityToDto)
				.collect(Collectors.toList());
	}
	
    @Override
	public List<Restaurant> findByNomRestaurant(String nom) {
		return restaurantRepository.findByNomRestaurant(nom);
	}

	@Override
	public List<Restaurant> findByNomRestaurantContains(String nom) {
		return restaurantRepository.findByNomRestaurantContains(nom);
	}

	@Override
	public List<Restaurant> findByNomNote(String nom, Double note) {
		return restaurantRepository.findByNomNote(nom, note);
	}
	
	@Override
	public List<Restaurant> findBySpecialite(Specialite specialite) {
		return restaurantRepository.findBySpecialite(specialite);
	}

	@Override
	public List<Restaurant> findBySpecialiteIdSpec(Long id) {
		return restaurantRepository.findBySpecialiteIdSpec(id);
	}

	@Override
	public List<Restaurant> findByOrderByNomRestaurantAsc() {
		return restaurantRepository.findByOrderByNomRestaurantAsc();
	}

	@Override
	public List<Restaurant> trierRestaurantNomsNote() {
		return restaurantRepository.trierRestaurantsNomsNote();
	}

	// Méthodes ajoutées
	@Override
	public List<Specialite> getAllSpecialites() {
		return specialiteRepository.findAll();
	}

	@Override
	public Page<Restaurant> getAllRestaurantsParPage(int page, int size) {
		return restaurantRepository.findAll(PageRequest.of(page, size));
	}

	@Override
	public RestaurantDTO convertEntityToDto(Restaurant restaurant) {
	    RestaurantDTO dto = new RestaurantDTO();
	    dto.setIdRestaurant(restaurant.getIdRestaurant());
	    dto.setNomRestaurant(restaurant.getNomRestaurant());
	    dto.setNoteRestaurant(restaurant.getNoteRestaurant());
	    dto.setDateOuverture(restaurant.getDateOuverture());
	    if (restaurant.getSpecialite() != null) {
	        dto.setIdSpec(restaurant.getSpecialite().getIdSpec());
	        dto.setNomSpec(restaurant.getSpecialite().getNomSpec());
	    }
	    return dto;
	}

	@Override
	public Restaurant convertDtoToEntity(RestaurantDTO dto) {
	    Restaurant restaurant = new Restaurant();
	    restaurant.setIdRestaurant(dto.getIdRestaurant());
	    restaurant.setNomRestaurant(dto.getNomRestaurant());
	    restaurant.setNoteRestaurant(dto.getNoteRestaurant());
	    restaurant.setDateOuverture(dto.getDateOuverture());
	    // Recharger la Specialite depuis la BDD par son ID
	    if (dto.getIdSpec() != null) {
	        Specialite spec = specialiteRepository.findById(dto.getIdSpec())
	            .orElseThrow(() -> new RuntimeException("Spécialité introuvable: " + dto.getIdSpec()));
	        restaurant.setSpecialite(spec);
	    }
	    return restaurant;
	}

}