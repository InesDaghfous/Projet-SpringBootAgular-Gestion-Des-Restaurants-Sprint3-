import { Injectable } from '@angular/core';
import { Restaurant } from '../model/restaurant.model';
import { Specialite } from '../model/specialite.model';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

// L'intercepteur tokenInterceptor ajoute automatiquement le header Authorization (JWT).
// On garde seulement Content-Type pour les requêtes avec body (POST/PUT).
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root',
})
export class RestaurantService {

  apiURL: string = 'http://localhost:8080/restaurants/api';
  apiURLSpec: string = 'http://localhost:8080/restaurants/api/spec';

  constructor(private http: HttpClient) {}

  // ─── Restaurants ──────────────────────────────────────────────

  listeRestaurant(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.apiURL);
  }

  ajouterRestaurant(rest: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.apiURL, rest, httpOptions);
  }

  supprimerRestaurant(id: number): Observable<void> {
    const url = `${this.apiURL}/${id}`;
    return this.http.delete<void>(url);
  }

  consulterRestaurant(id: number): Observable<Restaurant> {
    const url = `${this.apiURL}/${id}`;
    return this.http.get<Restaurant>(url);
  }

  updateRestaurant(rest: Restaurant): Observable<Restaurant> {
    return this.http.put<Restaurant>(this.apiURL, rest, httpOptions);
  }

  rechercherParSpecialite(idSpec: number): Observable<Restaurant[]> {
    const url = `${this.apiURL}/restosspec/${idSpec}`;
    return this.http.get<Restaurant[]>(url);
  }

  rechercherParNom(nom: string): Observable<Restaurant[]> {
    const url = `${this.apiURL}/restsByName/${nom}`;
    return this.http.get<Restaurant[]>(url);
  }

  // ─── Spécialités ──────────────────────────────────────────────

  listeSpecialites(): Observable<Specialite[]> {
    return this.http.get<Specialite[]>(this.apiURLSpec);
  }

  consulterSpecialite(id: number, specialites: Specialite[]): Specialite {
    return specialites.find((spec) => spec.idSpec == id)!;
  }

  ajouterSpecialite(spec: Specialite): Observable<Specialite> {
    return this.http.post<Specialite>(this.apiURLSpec, spec, httpOptions);
  }

  modifierSpecialite(spec: Specialite): Observable<Specialite> {
    return this.http.put<Specialite>(this.apiURLSpec, spec, httpOptions);
  }
}