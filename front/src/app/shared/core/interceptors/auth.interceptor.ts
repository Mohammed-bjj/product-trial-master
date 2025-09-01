import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../../features/auth/data-access/auth.service';
import { environment } from '../../../../environments/environment';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  
  // Routes exclues de l'injection du token
  const excludedRoutes = [
    `${environment.apiBaseUrl}/products/search`,
    `${environment.apiBaseUrl}/auth/token`,
    `${environment.apiBaseUrl}/auth/register`
  ];
  console.log('Auth Interceptor - Original Request:', req.url);
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