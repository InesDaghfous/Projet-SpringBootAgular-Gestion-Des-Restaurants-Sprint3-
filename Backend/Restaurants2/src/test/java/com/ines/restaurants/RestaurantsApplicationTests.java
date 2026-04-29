package com.ines.restaurants;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ines.restaurants.entities.Specialite;
import com.ines.restaurants.repos.RestaurantRepository;
import com.ines.restaurants.entities.Restaurant;

@SpringBootTest
class RestaurantsApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private RestaurantRepository restaurantRepository;

	@Test
	 public void testCreateRestaurant() {
		Restaurant prod = new Restaurant("McDonals",2200.500,new Date());
		restaurantRepository.save(prod);
	}	
	
	   @Test
		public void testFindRestaurant()
		 {
			 Restaurant r = restaurantRepository.findById(1L).get();		  
			 System.out.println(r);
		 }

		@Test
		public void testUpdateRestaurant()
		 {
			 Restaurant r = restaurantRepository.findById(1L).get();
			 r.setNoteRestaurant(1000.0);
			 restaurantRepository.save(r);
		 }
		
		
	@Test
		public void testDeleteRestaurant()
		 {
		restaurantRepository.deleteById(1L);;
			 
		 }

	   
	@Test
		public void testListerTousRestaurants()
		 {
			 List<Restaurant>  restos = restaurantRepository.findAll();	 
				for (Restaurant r : restos)
				{
					System.out.println(r);
				}	 
		 }
	
	@Test
	public void testFindByNomRestaurant()
	 {
	List<Restaurant>  restos = restaurantRepository.findByNomRestaurant("McDonals");
			for (Restaurant r : restos)
			{
				System.out.println(r);
			}
		
	 }
	
	@Test
	public void testFindByNomRestaurantContains ()
	 {
	List<Restaurant> prods=restaurantRepository.findByNomRestaurantContains("McDonals");
			for (Restaurant p : prods)
			{
				System.out.println(p);
			} 
	}
	
	@Test
	public void testfindByNomNote()
		 {
		List<Restaurant>  prods = restaurantRepository.findByNomNote("McDonals", 2200.0);
			for (Restaurant r : prods)
				{
					System.out.println(r);
				}
			
		 }
	
	@Test
	public void testfindBySpecialite()
	 {
		Specialite cat = new Specialite();
		cat.setIdSpec(1L);			
		List<Restaurant>  restos = restaurantRepository.findBySpecialite(cat);
			for (Restaurant r : restos)
			{
				System.out.println(r);
			}
	 }
	
	@Test
	public void findBySpecialiteIdSpec()
		 {			
			List<Restaurant>  restos = restaurantRepository.findBySpecialiteIdSpec(1L);
				for (Restaurant r : restos)
				{
					System.out.println(r);
				}
	       }
	
	@Test
	public void testfindByOrderByNomRestaurantAsc()
	 {
		List<Restaurant>  prods =    restaurantRepository.findByOrderByNomRestaurantAsc();	 
			for (Restaurant p : prods)
			{
				System.out.println(p);
			}
	 }
	
	@Test
	public void testTrierRestaurantsNomsNote()
	 {
		List<Restaurant>  restos = restaurantRepository.trierRestaurantsNomsNote();	 
			for (Restaurant r : restos)
			{
				System.out.println(r);
			}
	 }









	

}
