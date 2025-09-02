import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { CardModule } from 'primeng/card';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';
import { CartService } from '../data-access/cart.service';

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

}