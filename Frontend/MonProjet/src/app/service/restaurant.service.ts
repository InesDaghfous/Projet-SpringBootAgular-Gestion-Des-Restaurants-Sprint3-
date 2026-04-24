import { Injectable } from '@angular/core'; 
import { Restaurant } from '../model/restaurant.model';
import { Specialite } from '../model/specialite.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {

  restaurants!: Restaurant[];
  restaurant!: Restaurant;
  specialites!: Specialite[];

  apiURL: string = 'http://localhost:8080/restaurants/api';
  // Utilise le contrôleur REST custom qui retourne idSpec directement
  apiURLSpec: string = 'http://localhost:8080/restaurants/api/spec';

  constructor(private http: HttpClient) {}

  // 📌 Liste des restaurants
  listeRestaurant(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.apiURL);
  }

  // 📌 Ajouter
  ajouterRestaurant(rest: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.apiURL, rest, httpOptions);
  }

  // 📌 Supprimer
  supprimerRestaurant(id: number) {
    const url = `${this.apiURL}/${id}`;
    return this.http.delete(url, httpOptions);
  }

  // 📌 Consulter
  consulterRestaurant(id: number): Observable<Restaurant> {
    const url = `${this.apiURL}/${id}`;
    return this.http.get<Restaurant>(url);
  }

  // 📌 Liste des spécialités (retourne idSpec directement)
  listeSpecialites(): Observable<Specialite[]> {
    return this.http.get<Specialite[]>(this.apiURLSpec);
  }

  // 📌 Consulter spécialité (local)
  consulterSpecialite(id: number): Specialite {
    return this.specialites.find((spec) => spec.idSpec == id)!;
  }

  // 📌 Update
  updateRestaurant(rest: Restaurant): Observable<Restaurant> {
    return this.http.put<Restaurant>(this.apiURL, rest, httpOptions);
  }

 rechercherParSpecialite(idSpec: number): Observable<Restaurant[]> {
    const url = `${this.apiURL}/restosspec/${idSpec}`; // ← corriger la casse
    return this.http.get<Restaurant[]>(url);
}

  // 📌 Recherche par nom
  rechercherParNom(nom: string): Observable<Restaurant[]> {
    const url = `${this.apiURL}/restsByName/${nom}`;
    return this.http.get<Restaurant[]>(url);
  }

  // 📌 Ajouter une spécialité
  ajouterSpecialite(spec: Specialite): Observable<Specialite> {
    return this.http.post<Specialite>(this.apiURLSpec, spec, httpOptions);
  }

  // 📌 Modifier une spécialité
  modifierSpecialite(spec: Specialite): Observable<Specialite> {
    return this.http.put<Specialite>(this.apiURLSpec, spec, httpOptions);
  }
}