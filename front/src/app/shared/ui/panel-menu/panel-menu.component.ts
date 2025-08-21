import { Component, computed, inject } from "@angular/core";
import { MenuItem } from "primeng/api";
import { PanelMenuModule } from 'primeng/panelmenu';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: "app-panel-menu",
  standalone: true,
  imports: [PanelMenuModule],
  template: `
    <div class="modern-sidebar">
      <p-panelMenu [model]="items()" styleClass="modern-panel-menu" />
    </div>
  `,
  styleUrls: ['./panel-menu.component.scss']
})
export class PanelMenuComponent {
  private readonly authService = inject(AuthService);

  public readonly items = computed(() => {
    const isAuthenticated = this.authService.isAuthenticated();

    const baseItems: MenuItem[] = [
      {
        label: 'Accueil',
        icon: 'pi pi-home',
        routerLink: ['/home']
      },

    ];

    if (isAuthenticated) {
      baseItems.push(
        {
          label: 'Produits',
          icon: 'pi pi-shopping-bag',
          routerLink: ['/products/list']
        },
        {
          label: 'Contact',
          icon: 'pi pi-envelope',
          routerLink: ['/contact']
        },
        {
          label: 'Mon Panier',
          icon: 'pi pi-shopping-cart',
          routerLink: ['/cart']
        },
        {
          label: 'Mes Favoris',
          icon: 'pi pi-heart',
          routerLink: ['/wishlist']
        }
      );
    } else {
      baseItems.push(
        {
          label: 'Connexion',
          icon: 'pi pi-sign-in',
          routerLink: ['/sign-in']
        },
        {
          label: 'Inscription',
          icon: 'pi pi-user-plus',
          routerLink: ['/sign-up']
        }
      );
    }

    return baseItems;
  });
}
