import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../index';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  private readonly http = inject(HttpClient);
  private readonly _wishlistItems = signal<Product[]>([]);

  public readonly wishlistItems = this._wishlistItems.asReadonly();
  public readonly totalItems = computed(() => this._wishlistItems().length);

  public addToWishlist(product: Product): Observable<boolean> {
    const currentItems = this._wishlistItems();
    if (!currentItems.find(item => item.id === product.id)) {
      this._wishlistItems.set([...currentItems, product]);
    }
    return of(true);
  }

  public removeFromWishlist(productId: number): Observable<boolean> {
    const currentItems = this._wishlistItems();
    this._wishlistItems.set(currentItems.filter(item => item.id !== productId));
    return of(true);
  }

  public isInWishlist(productId: number): boolean {
    return this._wishlistItems().some(item => item.id === productId);
  }

  public clearWishlist(): Observable<boolean> {
    this._wishlistItems.set([]);
    return of(true);
  }
}