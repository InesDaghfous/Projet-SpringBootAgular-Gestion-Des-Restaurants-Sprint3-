import { Routes } from '@angular/router';
import { RestaurantsComponent } from './restaurants/restaurants';
import { AddRestaurant } from './add-restaurant/add-restaurant';
import { UpdateRestaurant } from './update-restaurant/update-restaurant';
import { RechercheParSpecialite } from './recherche-par-specialite/recherche-par-specialite';
import { RechercheParNom } from './recherche-par-nom/recherche-par-nom';

export const routes: Routes = [
    {path: "restaurants", component : RestaurantsComponent
    },
    {path: "add-restaurants", component : AddRestaurant
    },
    {path: "updateRestaurant/:id",  component: UpdateRestaurant},
    {path: "rechercheParSpecialite", component : RechercheParSpecialite},
    {path: "rechercheParNom", component : RechercheParNom},
    {path: "", redirectTo: "restaurants", pathMatch: "full"}
];
