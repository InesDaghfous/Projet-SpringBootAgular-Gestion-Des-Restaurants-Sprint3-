import { Routes } from '@angular/router';
import { RestaurantsComponent } from './restaurants/restaurants';
import { AddRestaurant } from './add-restaurant/add-restaurant';
import { UpdateRestaurant } from './update-restaurant/update-restaurant';
import { RechercheParSpecialite } from './recherche-par-specialite/recherche-par-specialite';
import { RechercheParNom } from './recherche-par-nom/recherche-par-nom';
import { ListeSpecialitesComponent } from './liste-specialites/liste-specialites';
import { LoginComponent } from './login/login';
import { ForbiddenComponent } from './forbidden/forbidden';
import { restaurantGuard } from './restaurant.guard';

export const routes: Routes = [
    { path: 'login', component: LoginComponent },
    { path: 'app-forbidden', component: ForbiddenComponent },
    { path: 'restaurants', component: RestaurantsComponent },
    { path: 'add-restaurants', component: AddRestaurant, canActivate: [restaurantGuard] },
    { path: 'updateRestaurant/:id', component: UpdateRestaurant, canActivate: [restaurantGuard] },
    { path: 'rechercheParSpecialite', component: RechercheParSpecialite },
    { path: 'rechercheParNom', component: RechercheParNom },
    { path: 'listeSpecialites', component: ListeSpecialitesComponent, canActivate: [restaurantGuard] },
    { path: '', redirectTo: 'restaurants', pathMatch: 'full' }
];
