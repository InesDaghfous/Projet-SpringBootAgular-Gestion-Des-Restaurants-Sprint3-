import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiURL: string = 'http://localhost:8080/restaurants';
  
  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];
  
  // Utilisateur en cours d'inscription
  public registredUser: User = new User();

  constructor(private router: Router, private http: HttpClient) { }

  /** Stocke l'utilisateur en cours d'inscription */
  setRegistredUser(user: User) {
    this.registredUser = user;
  }

  /** Inscription au Backend */
  registerUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiURL + '/register', user);
  }

  /** Enregistre le token et extrait les infos */
  saveToken(jwt: string) {
    localStorage.setItem('jwt-token', jwt);
    this.decodeJWT(jwt);
  }

  /** Décode le token pour récupérer l'utilisateur et ses rôles */
  decodeJWT(jwt: string) {
    if (jwt == undefined) return;
    
    // Décodage manuel simple du payload JWT (base64)
    const payload = JSON.parse(atob(jwt.split('.')[1]));
    
    this.roles = payload.roles;
    this.loggedUser = payload.sub;
    this.isloggedIn = true;
  }

  /** Connexion au Backend */
  login(user: User) {
    return this.http.post<User>(this.apiURL + '/login', user, { observe: 'response' });
  }

  /** Validation de l'email */
  validateEmail(code: string): Observable<any> {
    return this.http.get<any>(this.apiURL + '/verifyEmail/' + code);
  }

  /** Récupère le token */
  getToken(): string | null {
    return localStorage.getItem('jwt-token');
  }

  /** Déconnexion */
  logout() {
    this.isloggedIn = false;
    this.loggedUser = undefined!;
    this.roles = undefined!;
    localStorage.removeItem('jwt-token');
    this.router.navigate(['/login']);
  }

  /** Vérifie si ADMIN */
  isAdmin(): Boolean {
    if (!this.roles) return false;
    return this.roles.indexOf('ADMIN') > -1;
  }

  /** Initialise l'état au chargement de l'app */
  loadToken() {
    const token = this.getToken();
    if (token) {
      this.decodeJWT(token);
    }
  }
}
