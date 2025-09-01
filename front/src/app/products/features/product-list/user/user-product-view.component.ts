import { Component, OnInit, inject } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ProductsService } from "../../../data-access/products.service";
import { Product } from "../../../data-access/product.model";
import { ButtonModule } from "primeng/button";
import { TagModule } from 'primeng/tag';
import { TooltipModule } from 'primeng/tooltip';
import { CardModule } from 'primeng/card';
import { CartService } from '../../../../paniers/data-access/cart.service';
import { WishlistService } from '../../../../wishList/data-access/wishlist.service';
import { AuthService } from '../../../../shared/services/auth.service';

@Component({
  selector: "app-user-product-list",
  templateUrl: "./user-product-view.component.html",
  styleUrls: ["./user-product-view.component.scss"],
  standalone: true,
  imports: [CommonModule, ButtonModule, TagModule, TooltipModule, CardModule],
})
export class UserProductViewComponent implements OnInit {
  private readonly productsService = inject(ProductsService);
  private readonly authService = inject(AuthService);
  private readonly cartService = inject(CartService);
  private readonly wishlistService = inject(WishlistService);

  public readonly products = this.productsService.products;
  public readonly isAuthenticated = this.authService.isAuthenticated;

  ngOnInit() {
    this.productsService.get().subscribe();
  }

  public getStatusSeverity(status: string): 'success' | 'warning' | 'danger' {
    switch (status) {
      case 'INSTOCK': return 'success';
      case 'LOWSTOCK': return 'warning';
      case 'OUTOFSTOCK': return 'danger';
      default: return 'success';
    }
  }

  public getStatusLabel(status: string): string {
    return status === 'OUTOFSTOCK' ? 'Rupture' : 'En Stock';
  }

  public onAddToCart(product: Product) {
    this.cartService.addToCart(product).subscribe();
  }

  public onAddToWishlist(product: Product) {
    this.wishlistService.addToWishlist(product).subscribe();
  }
}
