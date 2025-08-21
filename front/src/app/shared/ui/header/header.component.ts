import { Component, Input, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ToolbarModule } from 'primeng/toolbar';
import { BadgeModule } from 'primeng/badge';
import { ButtonModule } from 'primeng/button';
import { TooltipModule } from 'primeng/tooltip';
import { ThemeToggleComponent } from '../theme-toggle/theme-toggle.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, ToolbarModule, ThemeToggleComponent, BadgeModule, ButtonModule, TooltipModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  private readonly router = inject(Router);
  
  @Input() title: string = '';
  
  // Valeurs temporaires pour les tests
  cartItemCount = 3;
  isAuthenticated = false;
  isAdmin = false;

  onCartClick() {
    console.log('Ouverture du panier');
  }

  onLogin() {
    this.router.navigate(['/sign-in']);
  }

  onRegister() {
    this.router.navigate(['/sign-up']);
  }

  onLogout() {
    console.log('Déconnexion');
    this.isAuthenticated = false;
  }
}
