import { Injectable, signal } from '@angular/core';
import { Product } from '../../products/data-access/product.model';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private readonly _wishlistItems = signal<Product[]>([]);

  public readonly wishlistItems = this._wishlistItems.asReadonly();

  public addToWishlist(product: Product): void {
    const currentItems = this._wishlistItems();
    if (!currentItems.find(item => item.id === product.id)) {
      this._wishlistItems.set([...currentItems, product]);
    }
  }

  public removeFromWishlist(productId: number): void {
    const currentItems = this._wishlistItems();
    this._wishlistItems.set(currentItems.filter(item => item.id !== productId));
  }

  public isInWishlist(productId: number): boolean {
    return this._wishlistItems().some(item => item.id === productId);
  }

  public clearWishlist(): void {
    this._wishlistItems.set([]);
  }
}