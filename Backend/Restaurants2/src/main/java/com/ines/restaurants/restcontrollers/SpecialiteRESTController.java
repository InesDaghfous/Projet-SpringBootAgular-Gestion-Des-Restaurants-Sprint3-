package com.ines.restaurants.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.repos.SpecialiteRepository;

@RestController
@RequestMapping("/api/spec")
@CrossOrigin("*")
public class SpecialiteRESTController {

    @Autowired
    SpecialiteRepository specialiteRepository;

    // GET /api/spec - Lister toutes les spécialités
    @RequestMapping(method = RequestMethod.GET)
    public List<Specialite> getAllSpecialites() {
        return specialiteRepository.findAll();
    }

    // GET /api/spec/{id} - Consulter une spécialité
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Specialite getSpecialiteById(@PathVariable("id") Long id) {
        return specialiteRepository.findById(id).get();
    }

    // POST /api/spec - Ajouter une spécialité
    @RequestMapping(method = RequestMethod.POST)
    public Specialite ajouterSpecialite(@RequestBody Specialite specialite) {
        return specialiteRepository.save(specialite);
    }

    // PUT /api/spec - Modifier une spécialité
    @RequestMapping(method = RequestMethod.PUT)
    public Specialite modifierSpecialite(@RequestBody Specialite specialite) {
        return specialiteRepository.save(specialite);
    }
}
