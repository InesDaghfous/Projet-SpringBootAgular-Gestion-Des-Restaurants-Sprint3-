import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';

import { CommonModule } from '@angular/common';
import { RestaurantService } from '../service/restaurant.service';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-restaurants',
  imports: [CommonModule,RouterLink],
  templateUrl: './restaurants.html',
})
export class RestaurantsComponent implements OnInit {
  restaurants: Restaurant[] = []; // initialisé vide pour éviter l'erreur au premier rendu

  constructor(private restaurantService: RestaurantService) {
    
  }

    supprimerRestaurant(r: Restaurant)
    {
       const id = r.idRestaurant;
       if (id == null) {
      return;
    }
      let conf = confirm("Etes-vous sûr ?");
      if (conf)
      this.restaurantService.supprimerRestaurant(id).subscribe(() => {
        console.log("restaurant supprimé");
        this.chargerRestaurants();
           });
    } 

  ngOnInit(): void {
     this.chargerRestaurants();
   
}
 chargerRestaurants(){
 this.restaurantService.listeRestaurant().subscribe(restos => {
console.log(restos);
this.restaurants = restos;
  });
 }
}

