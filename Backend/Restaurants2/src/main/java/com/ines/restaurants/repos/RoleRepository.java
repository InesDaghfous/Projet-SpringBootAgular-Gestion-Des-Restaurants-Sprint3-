package com.ines.restaurants.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ines.restaurants.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
	
	
}
