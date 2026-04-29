package com.ines.restaurants.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ines.restaurants.entities.User;
import com.ines.restaurants.service.UserService;

@RestController
@CrossOrigin(origins = "*")
public class UserRestController {

    @Autowired
    UserService userService;

    // GET /restaurants/all -> accessible uniquement aux utilisateurs ADMIN
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }
}
