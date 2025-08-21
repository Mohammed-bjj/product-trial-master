import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
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
  @Input() title: string = '';
  
  // Valeurs temporaires pour les tests
  cartItemCount = 3;
  isAuthenticated = false;
  isAdmin = false;

  onCartClick() {
    console.log('Ouverture du panier');
  }

  onLogin() {
    console.log('Navigation vers connexion');
    this.isAuthenticated = true;
  }

  onRegister() {
    console.log('Navigation vers inscription');
  }

  onLogout() {
    console.log('Déconnexion');
    this.isAuthenticated = false;
  }
}
