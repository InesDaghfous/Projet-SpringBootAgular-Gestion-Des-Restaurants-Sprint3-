package com.ines.restaurants.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ines.restaurants.dto.RestaurantDTO;
import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.service.RestaurantService;
import com.ines.restaurants.entities.Restaurant;

import jakarta.validation.Valid;

@Controller
public class RestaurantController {
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping(value = "/")
	public String welcome() {
	    return "index";
	}
	
	
		   @RequestMapping("/ListeRestaurants")
			public String listeRestaurants(ModelMap modelMap,@RequestParam (name="page",defaultValue = "0") int page,
										@RequestParam (name="size", defaultValue = "2") int size)
			{
			Page<Restaurant> restos = restaurantService.getAllRestaurantsParPage(page, size);
				modelMap.addAttribute("restaurants", restos);
		         modelMap.addAttribute("pages", new int[restos.getTotalPages()]);	
				modelMap.addAttribute("currentPage", page);			
				return "listeRestaurants";	
			}


		   @RequestMapping("/showCreate")
			public String showCreate(ModelMap modelMap)
			{
				modelMap.addAttribute("restaurant", new Restaurant());
				List<Specialite> spec = restaurantService.getAllSpecialites();
				modelMap.addAttribute("mode", "new");
				modelMap.addAttribute("specialites", spec);
				return "formRestaurant";
			}
		   
		   
			


		   @RequestMapping("/saveRestaurant")
			public String saveRestaurant(@Valid Restaurant restaurant, BindingResult bindingResult,
					@RequestParam (name="page",defaultValue = "0") int page,
					@RequestParam (name="size", defaultValue = "2") int size)
			{
				int currentPage;
				boolean isNew = false;
			   if (bindingResult.hasErrors()) return "formRestaurant";				  
				
			   if(restaurant.getIdRestaurant()==null) //ajout
				    isNew=true;
			  			   
			  	restaurantService.saveRestaurant(restaurantService.convertEntityToDto(restaurant));
			  	if (isNew) //ajout
			  	{
			  		Page<Restaurant> restos = restaurantService.getAllRestaurantsParPage(page, size);
			  		currentPage = restos.getTotalPages()-1;
			  	}
			  	else //modif
			  		currentPage=page;
			  	
			  	
				//return "formRestaurant";
				return ("redirect:/ListeRestaurants?page="+currentPage+"&size="+size);
			}


	  @RequestMapping("/supprimerRestaurant")
		public String supprimerRestaurant(@RequestParam("id") Long id,
				ModelMap modelMap,
				@RequestParam (name="page",defaultValue = "0") int page,
				@RequestParam (name="size", defaultValue = "2") int size)
		{

			restaurantService.deleteRestaurantById(id);
			Page<Restaurant> restos = restaurantService.getAllRestaurantsParPage(page, size);
			modelMap.addAttribute("restaurants", restos);		
			modelMap.addAttribute("pages", new int[restos.getTotalPages()]);	
			modelMap.addAttribute("currentPage", page);	
			modelMap.addAttribute("size", size);	
			return "listeRestaurants";	
		}


	@RequestMapping("/modifierRestaurant")
	public String editerRestaurant(@RequestParam("id") Long id, ModelMap modelMap,
			@RequestParam (name="page",defaultValue = "0") int page,
			@RequestParam (name="size", defaultValue = "2") int size) {
		RestaurantDTO p = restaurantService.getRestaurant(id);
		List<Specialite> specs = restaurantService.getAllSpecialites();
		modelMap.addAttribute("mode", "edit");
		modelMap.addAttribute("restaurant", p);
		modelMap.addAttribute("specialites", specs);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("size", size);
		
		return "formRestaurant";
	}
	  
	

	@RequestMapping("/updateRestaurant")
	public String updateRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, @RequestParam("date") String date,
			ModelMap modelMap) throws ParseException {
		// conversion de la date
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date dateCreation = dateformat.parse(String.valueOf(date));
		restaurant.setDateOuverture(dateCreation);

		restaurantService.updateRestaurant(restaurantService.convertEntityToDto(restaurant));
		List<RestaurantDTO> restos = restaurantService.getAllRestaurants();
		modelMap.addAttribute("restaurants", restos);
		return "listeRestaurants";
	}
}