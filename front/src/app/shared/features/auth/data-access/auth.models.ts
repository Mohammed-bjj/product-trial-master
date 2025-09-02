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