package com.ines.restaurants.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ines.restaurants.dto.RestaurantDTO;
import com.ines.restaurants.entities.Restaurant;
import com.ines.restaurants.service.RestaurantService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestaurantRESTController {

    @Autowired
    RestaurantService restaurantService;

    // GET /api
    @GetMapping
    public List<RestaurantDTO> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    // GET /api/{id}
    @GetMapping("/{id}")
    public RestaurantDTO getRestaurantById(@PathVariable("id") Long id) {
        return restaurantService.getRestaurant(id);
    }

    // POST /api
    @PostMapping
    public RestaurantDTO createRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        System.out.println("=== POST reçu ===");
        System.out.println("idSpec reçu: " + restaurantDTO.getIdSpec());

        return restaurantService.saveRestaurant(restaurantDTO);
    }

    // PUT /api
    @PutMapping
    public RestaurantDTO updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        return restaurantService.updateRestaurant(restaurantDTO);
    }

    // DELETE /api/{id}
    @DeleteMapping("/{id}")
    public void deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurantById(id);
    }

    // GET /api/restosspec/{idCat}
    @GetMapping("/restosspec/{idSpec}")
    public List<Restaurant> getRestaurantsBySpecId(@PathVariable("idSpec") Long idSpec) {
        return restaurantService.findBySpecialiteIdSpec(idSpec);
    }

    // GET /api/restsByName/{nom}
    @GetMapping("/restsByName/{nom}")
    public List<Restaurant> findByNomRestaurantContains(@PathVariable("nom") String nom) {
        return restaurantService.findByNomRestaurantContains(nom);
    }

}