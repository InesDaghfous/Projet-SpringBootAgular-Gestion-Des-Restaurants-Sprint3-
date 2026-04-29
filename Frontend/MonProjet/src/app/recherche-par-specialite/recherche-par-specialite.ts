import { Component } from '@angular/core';
import { RestaurantService } from '../service/restaurant.service';
import { Restaurant } from '../model/restaurant.model';
import { Specialite } from '../model/specialite.model';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recherche-par-specialite',
  imports: [FormsModule, CommonModule],
  templateUrl: './recherche-par-specialite.html',
  styles: ``,
})
export class RechercheParSpecialite {
   restaurants!: Restaurant[];
  IdSpecialite!: number;
  specialites!: Specialite[];

  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.restaurantService.listeSpecialites().subscribe(specs => {
      this.specialites = specs;  // retourne Specialite[] directement
      console.log(specs);
    });
  }

  onChange() {
    this.restaurantService.rechercherParSpecialite(this.IdSpecialite)
      .subscribe(rests => {
        this.restaurants = rests;
      });
  }

}
