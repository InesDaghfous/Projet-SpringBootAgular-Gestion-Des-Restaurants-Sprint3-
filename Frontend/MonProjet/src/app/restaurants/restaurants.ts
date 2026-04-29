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
      this.restaurants = restos;
      this.restaurants.forEach((rest) => {
        // Si le restaurant a au moins une image
        if (rest.images && rest.images.length > 0) {
          // On appelle loadImage pour récupérer le contenu réel de la première image
          this.restaurantService.loadImage(rest.images[0].idImage).subscribe((img: any) => {
            rest.imageStr = 'data:' + img.type + ';base64,' + img.image;
          });
        } else {
          rest.imageStr = 'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVR42mNkYAAAAAYAAjCB0C8AAAAASUVORK5CYII=';
        }
      });
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
