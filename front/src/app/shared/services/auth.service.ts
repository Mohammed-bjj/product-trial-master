import { Injectable, signal, computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject, tap } from 'rxjs';

export interface User {
  id: string;
  email: string;
  username: string;
  firstname: string;
  isAdmin: boolean;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  username: string;
  firstname: string;
  email: string;
  password: string;
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

  login(credentials: LoginRequest): Observable<{ token: string; user: User }> {
    return this.http.post<{ token: string; user: User }>('/api/token', credentials).pipe(
      tap((response: { token: string; user: User }) => {
        this._token.set(response.token);
        this._currentUser.set(response.user);
        this.saveToStorage();
      })
    );
  }

  register(userData: RegisterRequest): Observable<User> {
    return this.http.post<User>('/api/account', userData);
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
    const user = localStorage.getItem('current_user');
    
    if (token && user) {
      this._token.set(token);
      this._currentUser.set(JSON.parse(user));
    }
  }

  private saveToStorage(): void {
    const token = this._token();
    const user = this._currentUser();
    
    if (token && user) {
      localStorage.setItem('auth_token', token);
      localStorage.setItem('current_user', JSON.stringify(user));
    }
  }

  private clearStorage(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('current_user');
  }
}