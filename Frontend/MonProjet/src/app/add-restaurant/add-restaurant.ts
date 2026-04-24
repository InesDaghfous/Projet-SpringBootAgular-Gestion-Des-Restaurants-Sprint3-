import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';
import { RestaurantService } from '../service/restaurant.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Specialite } from '../model/specialite.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-restaurant',
  imports: [FormsModule,CommonModule],
  templateUrl: './add-restaurant.html'
})
export class AddRestaurant implements OnInit {
newRestaurant = new Restaurant();
  message!: string;
  specialites!: Specialite[];
  newIdSpec!: number;
  newSpecialite!: Specialite;

  constructor(
    private restaurantService: RestaurantService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.restaurantService.listeSpecialites()
      .subscribe(specs => {
        console.log(specs);
        this.specialites = specs;  // retourne Specialite[] directement
        if (this.specialites && this.specialites.length > 0) {
          this.newIdSpec = this.specialites[0].idSpec;
        }
      });
  }
addRestaurant() {
    console.log("newIdSpec avant envoi:", this.newIdSpec);
    console.log("type:", typeof this.newIdSpec);
    
    this.newRestaurant.idSpec = +this.newIdSpec;
    this.newRestaurant.specialite = this.specialites.find(spec => spec.idSpec == this.newIdSpec)!;
    
    console.log("newRestaurant complet:", JSON.stringify(this.newRestaurant));
    
    this.restaurantService.ajouterRestaurant(this.newRestaurant)
        .subscribe({
            next: rest => {
                console.log(rest);
                this.router.navigate(['restaurants']);
            },
            error: err => console.error("Erreur lors de l'ajout", err)
        });
}
}