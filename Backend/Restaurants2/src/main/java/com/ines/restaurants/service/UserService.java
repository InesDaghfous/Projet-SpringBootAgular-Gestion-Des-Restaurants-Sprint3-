package com.ines.restaurants.service;

import java.util.List;

import com.ines.restaurants.entities.Role;
import com.ines.restaurants.entities.User;

public interface UserService {
	void deleteAllusers();
	void deleteAllRoles();
	User saveUser(User user);
	User findUserByUsername(String username);
	Role addRole(Role role);
	User addRoleToUser(String username, String rolename);
	List<User> findAllUsers();
}
