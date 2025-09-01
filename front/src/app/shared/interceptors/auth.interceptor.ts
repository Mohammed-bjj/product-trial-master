import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../services/auth.service';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  
  // Routes exclues de l'injection du token
  const excludedRoutes = [
    '/products/search', // getProduct pour users
    '/auth/login',
    '/auth/register'
  ];
  
  // Vérifier si l'URL contient une route exclue
  const isExcluded = excludedRoutes.some(route => req.url.includes(route));
  
  if (isExcluded || !authService.getToken()) {
    return next(req);
  }
  
  // Ajouter le token à l'en-tête Authorization
  const authReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${authService.getToken()}`
    }
  });
  
  return next(authReq);
};