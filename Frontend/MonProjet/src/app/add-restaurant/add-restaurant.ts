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
  uploadedImage!: File;
  imagePath: any;

  onImageUpload(event: any) {
    this.uploadedImage = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.uploadedImage);
    reader.onload = (_event) => { this.imagePath = reader.result; }
  }

  addRestaurant() {
    this.newRestaurant.idSpec = +this.newIdSpec;

    // Étape 1 : Upload de l'image (TP page 6)
    if (this.uploadedImage) {
      this.restaurantService
        .uploadImage(this.uploadedImage, this.uploadedImage.name)
        .subscribe((img: any) => {
          this.newRestaurant.images = [img]; // On assigne l'image reçue
          this.newRestaurant.specialite = this.specialites.find(spec => spec.idSpec == this.newIdSpec)!;

          // Étape 2 : Ajout du restaurant
          this.restaurantService.ajouterRestaurant(this.newRestaurant)
            .subscribe({
              next: rest => {
                this.router.navigate(['restaurants']);
              },
              error: err => console.error("Erreur lors de l'ajout", err)
            });
        });
    } else {
      // Si pas d'image, on ajoute directement
      this.newRestaurant.specialite = this.specialites.find(spec => spec.idSpec == this.newIdSpec)!;
      this.restaurantService.ajouterRestaurant(this.newRestaurant)
        .subscribe({
          next: rest => {
            this.router.navigate(['restaurants']);
          },
          error: err => console.error("Erreur lors de l'ajout", err)
        });
    }
  }
}