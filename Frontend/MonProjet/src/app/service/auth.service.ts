import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Liste des utilisateurs en dur (simulation frontend)
  users: User[] = [
    { username: 'admin', password: '123', roles: ['ADMIN'] },
    { username: 'user', password: '123', roles: ['USER'] },
    { username: 'ines', password: '123', roles: ['USER'] },
    { username: 'salah', password: '123', roles: ['USER'] }
  ];

  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];

  constructor(private router: Router) { }

  /** Connexion */
  SignIn(user: User): Boolean {
    let validUser: Boolean = false;
    this.users.forEach((curUser) => {
      if (user.username === curUser.username && user.password === curUser.password) {
        validUser = true;
        this.loggedUser = curUser.username;
        this.isloggedIn = true;
        this.roles = curUser.roles;
        localStorage.setItem('loggedUser', this.loggedUser);
        localStorage.setItem('isloggedIn', String(this.isloggedIn));
        // Stocker un token simulé = username (le vrai token viendra du backend JWT)
        localStorage.setItem('jwt-token', user.username);
      }
    });
    return validUser;
  }

  /** Récupère le token JWT depuis le LocalStorage */
  getToken(): string | null {
    return localStorage.getItem('jwt-token');
  }

  /** Déconnexion */
  logout() {
    this.isloggedIn = false;
    this.loggedUser = undefined!;
    this.roles = undefined!;
    localStorage.removeItem('loggedUser');
    localStorage.removeItem('jwt-token');
    localStorage.setItem('isloggedIn', String(this.isloggedIn));
    this.router.navigate(['/login']);
  }

  /** Vérifie si l'utilisateur a le rôle ADMIN */
  isAdmin(): Boolean {
    if (!this.roles) return false;
    return this.roles.indexOf('ADMIN') > -1;
  }

  /** Restaure la session depuis le LocalStorage au démarrage */
  setLoggedUserFromLocalStorage(login: string) {
    this.loggedUser = login;
    this.isloggedIn = true;
    this.getUserRoles(login);
  }

  /** Récupère les rôles à partir du nom d'utilisateur */
  getUserRoles(username: string) {
    this.users.forEach((curUser) => {
      if (curUser.username === username) {
        this.roles = curUser.roles;
      }
    });
  }
}
