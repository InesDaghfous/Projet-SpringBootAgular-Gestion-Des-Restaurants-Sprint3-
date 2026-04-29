import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RestaurantService } from '../service/restaurant.service';
import { Restaurant } from '../model/restaurant.model';
import { SearchFilterPipe } from '../search-filter-pipe';

@Component({
  selector: 'app-recherche-par-nom',
  imports: [FormsModule, CommonModule, SearchFilterPipe],
  templateUrl: './recherche-par-nom.html',
  styles: ``,
})
export class RechercheParNom implements OnInit {
  // Pour la recherche via bouton (API)
  nomRestaurant!: string;

  // Pour le pipe (filtre en temps réel)
  searchTerm: string = '';

  // Tous les restaurants (chargés au démarrage)
  restaurants: Restaurant[] = [];

  constructor(private restaurantService: RestaurantService) {}

  // Étape 25 (PDF) : charger tous les restaurants au démarrage
  ngOnInit(): void {
    this.restaurantService.listeRestaurant().subscribe(rests => {
      console.log(rests);
      this.restaurants = rests;
    });
  }

  // Étape 16/17 : recherche via API avec bouton (avec amélioration si vide → liste complète)
  rechercherRests() {
    if (this.nomRestaurant) {
      this.restaurantService
        .rechercherParNom(this.nomRestaurant)
        .subscribe((rests) => {
          console.log(rests);
          this.restaurants = rests;
        });
    } else {
      this.restaurantService.listeRestaurant().subscribe((rests) => {
        console.log(rests);
        this.restaurants = rests;
      });
    }
  }

  // Étape 19 : recherche via keyup (filtre local sans API)
  onKeyUp(filterText: string) {
    this.searchTerm = filterText;
  }
}
