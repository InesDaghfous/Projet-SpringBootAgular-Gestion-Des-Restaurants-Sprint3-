package com.ines.restaurants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ines.restaurants.entities.Role;
import com.ines.restaurants.entities.User;
import com.ines.restaurants.repos.RoleRepository;
import com.ines.restaurants.repos.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRep;

    @Autowired
    RoleRepository roleRep;

    // Suppression de BCryptPasswordEncoder

    @Override
    public User saveUser(User user) {
        // Plus d'encodage des mots de passe
        return userRep.save(user);
    }

    @Override
    public User addRoleToUser(String username, String rolename) {
        User usr = userRep.findByUsername(username);
        Role r = roleRep.findByRole(rolename);

        if (usr != null && r != null) {
            usr.getRoles().add(r);
        }
        return usr;
    }

    @Override
    public void deleteAllusers() {
        userRep.deleteAll();
    }

    @Override
    public void deleteAllRoles() {
        roleRep.deleteAll();
    }

    @Override
    public Role addRole(Role role) {
        return roleRep.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRep.findByUsername(username);
    }
}