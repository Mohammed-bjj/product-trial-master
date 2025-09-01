import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { WishlistService } from '../shared/services/wishlist.service';
import { CartService } from '../shared/services/cart.service';
import { Product } from '../products/data-access/product.model';
import { MockDataHelper, MOCK_PRODUCTS } from '../shared/data/mock-data';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss'],
  standalone: true,
  imports: [CommonModule, ButtonModule, TableModule, CardModule]
})
export class WishlistComponent {
  private readonly wishlistService = inject(WishlistService);
  private readonly cartService = inject(CartService);

  public readonly wishlistItems = this.wishlistService.wishlistItems;

  public removeFromWishlist(productId: number): void {
    this.wishlistService.removeFromWishlist(productId);
  }

  public addToCart(product: Product): void {
    this.cartService.addToCart(product, 1).subscribe();
  }

  public clearWishlist(): void {
    this.wishlistService.clearWishlist();
  }

  // Méthodes de test avec données fictives
  public loadTestData(): void {
    MockDataHelper.initializeWishlistWithMockData(this.wishlistService);
  }

  public addRandomProduct(): void {
    const randomProduct = MockDataHelper.getRandomProduct();
    this.wishlistService.addToWishlist(randomProduct);
  }

  public addAllProducts(): void {
    MOCK_PRODUCTS.forEach(product => {
      this.wishlistService.addToWishlist(product);
    });
  }
}