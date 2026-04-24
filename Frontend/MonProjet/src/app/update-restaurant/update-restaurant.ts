import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';
import { RestaurantService } from '../service/restaurant.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Specialite } from '../model/specialite.model';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-update-restaurant',
  imports: [FormsModule, CommonModule],
  templateUrl: './update-restaurant.html',
  styleUrl: './update-restaurant.css',
})
export class UpdateRestaurant implements OnInit {
  currentRestaurant = new Restaurant();
  specialites: Specialite[] = [];
  selectedSpecialite!: Specialite;

  constructor(
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private restaurantService: RestaurantService
  ) {}

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params['id'];
    forkJoin({
      specialites: this.restaurantService.listeSpecialites(),
      restaurant: this.restaurantService.consulterRestaurant(id)
    }).subscribe(({ specialites, restaurant }) => {
      this.specialites = specialites;
      this.currentRestaurant = restaurant;
      const currentIdSpec = restaurant.idSpec;
      this.selectedSpecialite = specialites.find(s => s.idSpec == currentIdSpec)!;
      console.log('Spécialités:', specialites);
      console.log('Restaurant:', restaurant);
      console.log('Spécialité sélectionnée:', this.selectedSpecialite);
    });
  }

  compareSpecialites(s1: Specialite, s2: Specialite): boolean {
    return s1 && s2 ? s1.idSpec === s2.idSpec : s1 === s2;
  }

  updateRestaurant() {
    this.currentRestaurant.idSpec = this.selectedSpecialite?.idSpec;
    console.log('Envoi update - idSpec:', this.currentRestaurant.idSpec);

    this.restaurantService.updateRestaurant(this.currentRestaurant)
      .subscribe({
        next: (res) => {
          console.log('Update réussi:', res);
          this.router.navigate(['restaurants']);
        },
        error: err => console.error('Erreur lors de la modification', err)
      });
  }
}