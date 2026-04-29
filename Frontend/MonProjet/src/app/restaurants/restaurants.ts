import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';
import { CommonModule } from '@angular/common';
import { RestaurantService } from '../service/restaurant.service';
import { RouterLink } from '@angular/router';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-restaurants',
  imports: [CommonModule, RouterLink],
  templateUrl: './restaurants.html',
})
export class RestaurantsComponent implements OnInit {
  restaurants: Restaurant[] = [];

  constructor(
    private restaurantService: RestaurantService,
    public authService: AuthService
  ) {}

  ngOnInit(): void {
    this.chargerRestaurants();
  }

  chargerRestaurants() {
    this.restaurantService.listeRestaurant().subscribe(restos => {
      console.log(restos);
      this.restaurants = restos;
    });
  }

  supprimerRestaurant(r: Restaurant) {
    const id = r.idRestaurant;
    if (id == null) return;
    let conf = confirm('Etes-vous sûr ?');
    if (conf)
      this.restaurantService.supprimerRestaurant(id).subscribe(() => {
        console.log('restaurant supprimé');
        this.chargerRestaurants();
      });
  }
}
