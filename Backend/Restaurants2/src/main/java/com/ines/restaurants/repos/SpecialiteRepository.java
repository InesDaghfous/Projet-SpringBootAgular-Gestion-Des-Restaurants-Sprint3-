package com.ines.restaurants.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.ines.restaurants.entities.Specialite;
@RepositoryRestResource(path = "spec")
@CrossOrigin(origins="http://localhost:4200/") //pour autoriser angular
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {

}
