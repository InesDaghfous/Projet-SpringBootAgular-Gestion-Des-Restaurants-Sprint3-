package com.ines.restaurants;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.ines.restaurants.entities.Restaurant;
import com.ines.restaurants.entities.Specialite;

/**
 * Expose les IDs des entités dans les réponses Spring Data REST.
 * Sans cela, idSpec et idRestaurant seraient cachés dans le JSON.
 */
@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(Specialite.class, Restaurant.class);
    }
}
