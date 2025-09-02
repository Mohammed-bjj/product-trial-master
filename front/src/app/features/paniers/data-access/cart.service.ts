import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Product } from '../../index';
import { AuthService } from '../../../shared';
import { Observable, of } from 'rxjs';

export interface CartItem {
  product: Product;
  quantity: number;
}

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private readonly http = inject(HttpClient);
  private readonly authService = inject(AuthService);
  private readonly _cartItems = signal<CartItem[]>([]);

  public readonly cartItems = this._cartItems.asReadonly();
  public readonly totalItems = computed(() => 
    this._cartItems().reduce((sum, item) => sum + item.quantity, 0)
  );
  public readonly totalPrice = computed(() => 
    this._cartItems().reduce((sum, item) => sum + (item.product.price * item.quantity), 0)
  );

  addToCart(product: Product, quantity: number = 1): Observable<boolean> {
    if (!this.authService.canAccessCart()) {
      throw new Error('Vous devez être connecté pour ajouter au panier');
    }

    const existingItem = this._cartItems().find(item => item.product.id === product.id);
    
    if (existingItem) {
      this.updateQuantity(product.id, existingItem.quantity + quantity);
    } else {
      this._cartItems.update(items => [...items, { product, quantity }]);
    }

    return of(true);
  }

  removeFromCart(productId: number): Observable<boolean> {
    this._cartItems.update(items => items.filter(item => item.product.id !== productId));
    return of(true);
  }

  updateQuantity(productId: number, quantity: number): Observable<boolean> {
    if (quantity <= 0) {
      return this.removeFromCart(productId);
    }

    this._cartItems.update(items => 
      items.map(item => 
        item.product.id === productId 
          ? { ...item, quantity } 
          : item
      )
    );
    return of(true);
  }

  clearCart(): Observable<boolean> {
    this._cartItems.set([]);
    return of(true);
  }
}