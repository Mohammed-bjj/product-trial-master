import { Component } from "@angular/core";
import { MenuItem } from "primeng/api";
import { PanelMenuModule } from 'primeng/panelmenu';

@Component({
  selector: "app-panel-menu",
  standalone: true,
  imports: [PanelMenuModule],
  template: `
    <div class="modern-sidebar">
      <p-panelMenu [model]="items" styleClass="modern-panel-menu" />
    </div>
  `,
  styles: [`
    .modern-sidebar {
      height: 100%;
      background: linear-gradient(135deg, var(--surface-0) 0%, var(--surface-50) 50%, var(--surface-100) 100%);
      box-shadow: 4px 0 20px rgba(220, 53, 69, 0.1);
      backdrop-filter: blur(10px);
    }

    :host ::ng-deep .modern-panel-menu {
      border: none;
      background: transparent;
      padding: 1rem 0;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-panel {
      margin-bottom: 0.75rem;
      border: none;
      background: transparent;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header {
      border: none;
      background: transparent;
      border-radius: 0 25px 25px 0;
      margin-right: 1rem;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      overflow: hidden;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header:hover {
      background: var(--surface-100);
      transform: translateX(12px) scale(1.02);
      box-shadow: 0 8px 25px rgba(220, 53, 69, 0.2);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link {
      padding: 1.25rem 1.5rem;
      border: none;
      background: var(--surface-0);
      color: var(--text-color);
      font-weight: 600;
      border-radius: 0 25px 25px 0;
      position: relative;
      border: 2px solid transparent;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link::before {
      content: '';
      position: absolute;
      left: 0;
      top: 0;
      height: 100%;
      width: 4px;
      background: transparent;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link:hover::before {
      background: linear-gradient(135deg, #dc3545, #ffc107);
      width: 6px;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link:hover {
      color: #dc3545;
      background: var(--surface-100);
      border-color: rgba(220, 53, 69, 0.2);
      box-shadow: 0 4px 16px rgba(220, 53, 69, 0.3);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link .p-menuitem-icon {
      color: var(--text-color-secondary);
      margin-right: 1.25rem;
      font-size: 1.25rem;
      transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
      filter: contrast(1.2) saturate(1.1);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link:hover .p-menuitem-icon {
      color: #dc3545;
      transform: scale(1.15) rotate(5deg);
      filter: contrast(1.3) saturate(1.2) brightness(1.1);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link .p-menuitem-text {
      font-size: 0.95rem;
      letter-spacing: -0.025em;
      transition: all 0.3s ease;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header-link:hover .p-menuitem-text {
      font-weight: 700;
      text-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-content {
      border: none;
      background: transparent;
      padding: 0;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header.p-highlight {
      background: linear-gradient(135deg, rgba(220, 53, 69, 0.1) 0%, rgba(220, 53, 69, 0.05) 100%);
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header.p-highlight .p-panelmenu-header-link {
      color: #dc3545;
      background: linear-gradient(135deg, rgba(220, 53, 69, 0.1) 0%, rgba(220, 53, 69, 0.05) 100%);
      border-color: #ffc107;
      font-weight: 700;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header.p-highlight .p-panelmenu-header-link::before {
      background: linear-gradient(135deg, #dc3545, #ffc107);
      width: 6px;
    }

    :host ::ng-deep .modern-panel-menu .p-panelmenu-header.p-highlight .p-panelmenu-header-link .p-menuitem-icon {
      color: #dc3545;
      transform: scale(1.1);
    }
  `]
})
export class PanelMenuComponent {
  public readonly items: MenuItem[] = [
    {
      label: 'Accueil',
      icon: 'pi pi-home',
      routerLink: ['/home']
    },
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
  ];
}
