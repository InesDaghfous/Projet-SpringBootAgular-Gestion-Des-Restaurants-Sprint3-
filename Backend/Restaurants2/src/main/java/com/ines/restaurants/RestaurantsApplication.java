package com.ines.restaurants;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ines.restaurants.entities.Restaurant;

@SpringBootApplication
public class RestaurantsApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Exposer les IDs des entités Restaurant dans les réponses REST
        repositoryRestConfiguration.exposeIdsFor(Restaurant.class);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // Plus de BCryptPasswordEncoder ni de hash à l'affichage
}