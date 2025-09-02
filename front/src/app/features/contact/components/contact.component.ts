import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { ButtonModule } from 'primeng/button';
import { MessageModule } from 'primeng/message';
import { AuthService } from  '../../../shared';


@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, FormsModule, CardModule, InputTextModule, InputTextareaModule, ButtonModule, MessageModule],
  templateUrl: './contact.component.html',
  styleUrl: './contact.component.scss'
})
export class ContactComponent {

 private readonly authService = inject(AuthService);

  email = this.authService.currentUser()?.email || '';
  message = '';
  showSuccessMessage = false;

  onSubmit() {
    if (this.email && this.message && this.message.length <= 300) {
      // Simuler l'envoi
      this.showSuccessMessage = true;
      this.email = '';
      this.message = '';
      
      // Masquer le message après 5 secondes
      setTimeout(() => {
        this.showSuccessMessage = false;
      }, 5000);
    }
  }
}