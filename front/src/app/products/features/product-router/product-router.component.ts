import { Component, inject } from '@angular/core';

import { AuthService } from '../../../shared/services/auth.service';
import { ProductListComponent } from '@app/products/features/product-list/admin/product-list.component';
import { UserProductViewComponent } from '@app/products/features/product-list/user/user-product-view.component';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-product-router',
  template: `
    @if(isAdmin) {
      <app-product-list />
    } @else {
      <app-user-product-list />
    }
  `,
  standalone: true,
  imports: [CommonModule, ProductListComponent, UserProductViewComponent]
})
export class ProductRouterComponent  {
  private readonly authService = inject(AuthService);


  get isAdmin() {
      console.log('isAdmin called', this.authService.isAuthenticated(), this.authService.isAdmin());
    return  this.authService.isAuthenticated()  &&   this.authService.isAdmin() ;
  }
}
