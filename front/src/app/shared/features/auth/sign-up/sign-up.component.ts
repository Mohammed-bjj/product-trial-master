import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { MessageModule } from 'primeng/message';
import { AuthService, RegisterRequest } from '../../../services/auth.service';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    CardModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    MessageModule,
    RouterLink
  ],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.scss']
})
export class SignUpComponent {
  private readonly authService = inject(AuthService);
  private readonly router = inject(Router);

  userData: RegisterRequest = {
    username: '',
    firstname: '',
    email: '',
    password: ''
  };

  isLoading = false;
  errorMessage = '';
  successMessage = '';

  onSubmit() {
    if (!this.isFormValid()) {
      return;
    }

    this.isLoading = true;
    this.errorMessage = '';
    this.successMessage = '';

    this.authService.register(this.userData).subscribe({
      next: () => {
        this.successMessage = 'Compte créé avec succès ! Vous pouvez maintenant vous connecter.';
        setTimeout(() => {
          this.router.navigate(['/sign-in']);
        }, 2000);
      },
      error: () => {
        this.errorMessage = 'Erreur lors de la création du compte. Vérifiez vos informations.';
        this.isLoading = false;
      },
      complete: () => {
        this.isLoading = false;
      }
    });
  }

  private isFormValid(): boolean {
    return !!(
      this.userData.username &&
      this.userData.firstname &&
      this.userData.email &&
      this.userData.password &&
      this.userData.password.length >= 6
    );
  }
}