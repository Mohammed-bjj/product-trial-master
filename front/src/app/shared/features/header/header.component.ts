import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ToolbarModule } from 'primeng/toolbar';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { ThemeToggleComponent } from '../theme-toggle/theme-toggle.component';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, ToolbarModule, ThemeToggleComponent, BadgeModule, ButtonModule, TooltipModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  private readonly router = inject(Router);
  private readonly authService = inject(AuthService);

  @Input() title: string = '';

  get isAuthenticated() {
    return this.authService.isAuthenticated();
  }

  get isAdmin() {
    return this.authService.isAdmin();
  }

  get getUsername(){
      return this.authService.currentUser()?.username ;
  }

  // Valeurs temporaires pour les tests
  cartItemCount = 3;

  onCartClick() {
    if (this.isAuthenticated  && !this.isAdmin) {

    } else {
      this.router.navigate(['/sign-in']);
    }
  }

  onLogin() {
    this.router.navigate(['/sign-in']);
  }

  onRegister() {
    this.router.navigate(['/sign-up']);
  }

  onLogout() {
    this.authService.logout();
    this.router.navigate(['/home']);
  }
}
