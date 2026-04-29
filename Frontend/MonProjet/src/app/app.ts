import { Component, OnInit, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink, CommonModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('MonProjet');

  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.loadToken();
    if (this.authService.isloggedIn) {
        // Optionnel: on reste sur la page actuelle
    } else {
        this.router.navigate(['/login']);
    }
  }

  onLogout() {
    this.authService.logout();
  }
}
