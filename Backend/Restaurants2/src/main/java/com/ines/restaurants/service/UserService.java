package com.ines.restaurants.service;

import java.util.List;
import com.ines.restaurants.entities.Role;
import com.ines.restaurants.entities.User;
import com.ines.restaurants.entities.VerificationToken;

public interface UserService {
    void deleteAllusers();
    void deleteAllRoles();
    User saveUser(User user);
    User findUserByUsername(String username);
    Role addRole(Role role);
    User addRoleToUser(String username, String rolename);
    List<User> findAllUsers();
    
    // Nouvelles méthodes pour l'inscription
    User registerUser(com.ines.restaurants.entities.RegistationRequest request);
    User validateToken(String code);
}
