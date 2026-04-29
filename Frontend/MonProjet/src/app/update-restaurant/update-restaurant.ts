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

  uploadedImage!: File;
  myImage!: string;
  isImageUpdated: boolean = false;

  onImageUpload(event: any) {
    if (event.target.files && event.target.files.length) {
      this.uploadedImage = event.target.files[0];
      this.isImageUpdated = true;
      const reader = new FileReader();
      reader.readAsDataURL(this.uploadedImage);
      reader.onload = () => { this.myImage = reader.result as string; };
    }
  }

  onAddImageProduit() {
    if (!this.uploadedImage || !this.currentRestaurant.idRestaurant) return;
    this.restaurantService
      .uploadImageProd(this.uploadedImage, this.uploadedImage.name, this.currentRestaurant.idRestaurant)
      .subscribe((img: any) => {
        if (!this.currentRestaurant.images) {
           this.currentRestaurant.images = [];
        }
        this.currentRestaurant.images.push(img);
      });
  }

  supprimerImage(img: any) {
    let conf = confirm("Etes-vous sûr ?");
    if (conf) {
      this.restaurantService.supprimerImage(img.idImage).subscribe(() => {
        const index = this.currentRestaurant.images?.indexOf(img, 0);
        if (index !== undefined && index > -1) {
          this.currentRestaurant.images?.splice(index, 1);
        }
      });
    }
  }

  updateRestaurant() {
    this.currentRestaurant.idSpec = this.selectedSpecialite?.idSpec;

    // Si une nouvelle image a été sélectionnée via le champ parcourir
    if (this.isImageUpdated) {
      this.restaurantService
        .uploadImageProd(this.uploadedImage, this.uploadedImage.name, this.currentRestaurant.idRestaurant!)
        .subscribe((img: any) => {
          // On l'ajoute à la liste avant de sauvegarder le reste
          if (!this.currentRestaurant.images) {
            this.currentRestaurant.images = [];
          }
          this.currentRestaurant.images.push(img);
          this.saveRestaurant();
        });
    } else {
      this.saveRestaurant();
    }
  }

  saveRestaurant() {
    this.restaurantService.updateRestaurant(this.currentRestaurant)
      .subscribe({
        next: (res) => {
          this.router.navigate(['restaurants']);
        },
        error: err => console.error('Erreur lors de la modification', err)
      });
  }
}