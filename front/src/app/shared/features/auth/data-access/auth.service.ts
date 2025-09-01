import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';


import { environment } from "../../../../../environments/environment";

export interface User {
  id?: string;
  email: string;
  username: string;
  firstname?: string;
  isAdmin: boolean;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  lastName: string;
  firstName: string;
  email: string;
  password: string;
}

export interface LoginResponse {
  access_token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly http = inject(HttpClient);
  private readonly _currentUser = signal<User | null>(null);
  private readonly _token = signal<string | null>(null);

  public readonly currentUser = this._currentUser.asReadonly();
  public readonly isAuthenticated = computed(() => !!this._currentUser());
  public readonly isAdmin = computed(() => this._currentUser()?.isAdmin ?? false);
  public readonly canManageProducts = computed(() => this.isAdmin());
  public readonly canAccessCart = computed(() => this.isAuthenticated());
  public readonly canAccessWishlist = computed(() => this.isAuthenticated());

  constructor() {
    this.loadFromStorage();
  }

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.apiBaseUrl}/account/token`, credentials).pipe(
      tap((response: LoginResponse) => {
        this._token.set(response.access_token);
        this.decodedToken(response.access_token); 
        this.saveToStorage();
      })
    );
  }



  decodedToken(token: string): void {
    const decodedToken: any = jwtDecode(token);
    this._currentUser.set({
      email: decodedToken.sub,
      username: decodedToken.lastName,
      isAdmin: decodedToken.scope === 'ROLE_ADMIN',
      id: decodedToken.id,
      firstname: decodedToken.firstName
    });
  }


  register(userData: RegisterRequest): Observable<User> {
    return this.http.post<User>(`${environment.apiBaseUrl}/account/register`, userData);
  }

  logout(): void {
    this._currentUser.set(null);
    this._token.set(null);
    this.clearStorage();
  }

  getToken(): string | null {
    return this._token();
  }

  private loadFromStorage(): void {
    const token = localStorage.getItem('auth_token');
    if (token) {
      this._token.set(token);
    }
  }

  private saveToStorage(): void {
    const token = this._token();
    if (token ) {
      localStorage.setItem('auth_token', token);
    }
  }

  private clearStorage(): void {
    localStorage.removeItem('auth_token');
  }
}