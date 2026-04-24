package com.ines.restaurants.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomResto", types = { Restaurant.class })
public interface RestaurantProjection {
	public String getNomRestaurant();
}

