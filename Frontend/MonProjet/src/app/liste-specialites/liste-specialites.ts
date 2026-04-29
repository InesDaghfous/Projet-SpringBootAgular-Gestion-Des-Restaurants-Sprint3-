import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Specialite } from '../model/specialite.model';
import { RestaurantService } from '../service/restaurant.service';
import { UpdateSpecialiteComponent } from '../update-specialite/update-specialite';

@Component({
  selector: 'app-liste-specialites',
  imports: [CommonModule, UpdateSpecialiteComponent],
  templateUrl: './liste-specialites.html',
})
export class ListeSpecialitesComponent implements OnInit {
  specialites: Specialite[] = [];
  updatedSpec: Specialite = { idSpec: 0, nomSpec: '' };
  ajout: boolean = true;

  constructor(private restaurantService: RestaurantService) {}

  ngOnInit(): void {
    this.chargerSpecialites();
  }

  chargerSpecialites() {
    this.restaurantService.listeSpecialites().subscribe((specs) => {
      this.specialites = specs;
      console.log('Spécialités chargées :', specs);
    });
  }

  specialiteUpdated(spec: Specialite) {
    console.log('specialiteUpdated event reçu :', spec);
    if (this.ajout) {
      // Ajout
      this.restaurantService.ajouterSpecialite(spec).subscribe(() => {
        this.ajout = true;
        this.updatedSpec = { idSpec: 0, nomSpec: '' };
        this.chargerSpecialites();
      });
    } else {
      // Modification
      this.restaurantService.modifierSpecialite(spec).subscribe(() => {
        this.ajout = true;
        this.updatedSpec = { idSpec: 0, nomSpec: '' };
        this.chargerSpecialites();
      });
    }
  }

  updateSpec(spec: Specialite) {
    this.updatedSpec = { ...spec };
    this.ajout = false;
  }

  annuler() {
    this.updatedSpec = { idSpec: 0, nomSpec: '' };
    this.ajout = true;
  }
}
