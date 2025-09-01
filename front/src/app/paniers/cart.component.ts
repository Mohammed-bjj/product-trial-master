import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';
import { CartService } from '../shared/services/cart.service';
import { MockDataHelper, MOCK_PRODUCTS } from '../shared/data/mock-data';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
  standalone: true,
  imports: [CommonModule, ButtonModule, TableModule, CardModule, InputNumberModule, FormsModule]
})
export class CartComponent {
  private readonly cartService = inject(CartService);

  public readonly cartItems = this.cartService.cartItems;
  public readonly totalPrice = this.cartService.totalPrice;

  public updateQuantity(productId: number, quantity: number) {
    this.cartService.updateQuantity(productId, quantity);
  }

  public removeFromCart(productId: number) {
    this.cartService.removeFromCart(productId);
  }

  public clearCart() {
    this.cartService.clearCart();
  }

  // Méthodes de test avec données fictives
  public loadTestData() {
    MockDataHelper.initializeCartWithMockData(this.cartService);
  }

  public addRandomProduct() {
    const randomProduct = MockDataHelper.getRandomProduct();
    this.cartService.addToCart(randomProduct, 1).subscribe();
  }

  public addAllProducts() {
    MOCK_PRODUCTS.forEach(product => {
      this.cartService.addToCart(product, Math.floor(Math.random() * 3) + 1).subscribe();
    });
  }
}