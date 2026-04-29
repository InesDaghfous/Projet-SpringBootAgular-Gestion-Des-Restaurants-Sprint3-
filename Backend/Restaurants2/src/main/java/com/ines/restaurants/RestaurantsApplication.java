package com.ines.restaurants;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.ines.restaurants.entities.Restaurant;
import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.entities.User;
import com.ines.restaurants.entities.Role;
import com.ines.restaurants.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@SpringBootApplication
public class RestaurantsApplication implements RepositoryRestConfigurer {

    @Autowired
    private UserService userService;

    @Autowired
    private com.ines.restaurants.repos.VerificationTokenRepository verificationTokenRepo;

    public static void main(String[] args) {
        SpringApplication.run(RestaurantsApplication.class, args);
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration repositoryRestConfiguration,
            CorsRegistry cors) {
        repositoryRestConfiguration.exposeIdsFor(Restaurant.class, Specialite.class);
    }

    @PostConstruct
    void init_users() {
        // Supprimer les anciens utilisateurs/rôles/tokens pour repartir à zéro
        verificationTokenRepo.deleteAll();
        userService.deleteAllusers();
        userService.deleteAllRoles();

        // Ajouter les rôles
        userService.addRole(new Role(null, "ADMIN"));
        userService.addRole(new Role(null, "USER"));

        // Ajouter les utilisateurs (id, username, password, email, enabled, roles)
        userService.saveUser(new User(null, "admin", "123", "admin@gmail.com", true, new java.util.ArrayList<>()));
        userService.saveUser(new User(null, "ines", "123", "ines@gmail.com", true, new java.util.ArrayList<>()));
        userService.saveUser(new User(null, "salah", "123", "salah@gmail.com", true, new java.util.ArrayList<>()));

        // Associer les rôles
        userService.addRoleToUser("admin", "ADMIN");
        userService.addRoleToUser("admin", "USER");
        userService.addRoleToUser("ines", "USER");
        userService.addRoleToUser("salah", "USER");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}