package com.ines.restaurants;

import jakarta.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.ines.restaurants.entities.Restaurant;
import com.ines.restaurants.entities.Role;
import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.entities.User;
import com.ines.restaurants.service.UserService;

@SpringBootApplication
public class RestaurantsApplication implements CommandLineRunner {

    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Exposer les IDs des entités dans les réponses REST
        repositoryRestConfiguration.exposeIdsFor(Restaurant.class, Specialite.class);
    }

    // ⚠️ Décommenter UNE SEULE FOIS pour initialiser les utilisateurs et leurs rôles,
    // puis re-commenter après le premier démarrage.
    /*
    @PostConstruct
    void init_users() {
        // Ajouter les rôles
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "USER"));

        // Ajouter les utilisateurs
        userService.saveUser(new User(null, "admin", "123", true, null));
        userService.saveUser(new User(null, "user1", "123", true, null));
        userService.saveUser(new User(null, "user2", "123", true, null));

        // Ajouter les rôles aux utilisateurs
        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("admin", "USER");
        userService.addRoleToUser("user1", "USER");
        userService.addRoleToUser("user2", "USER");
    }
    */

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}