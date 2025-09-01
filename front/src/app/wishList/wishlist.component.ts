import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { WishlistService } from './data-access/wishlist.service';
import { CartService } from '../paniers/data-access/cart.service';
import { Product } from '../products/data-access/product.model';

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
    this.wishlistService.removeFromWishlist(productId).subscribe();
  }

  public addToCart(product: Product): void {
    this.cartService.addToCart(product, 1).subscribe();
  }

  public clearWishlist(): void {
    this.wishlistService.clearWishlist().subscribe();
  }

}