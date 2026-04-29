import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const toExclude = ['/login', '/register', '/verifyEmail'];

  // Vérifier si l'URL contient l'un des termes à exclure
  const isExcluded = toExclude.some(path => req.url.includes(path));

  if (!isExcluded) {
    let jwt = authService.getToken();

    // N'ajouter le header que si un token existe
    if (jwt) {
      let reqWithToken = req.clone({
        setHeaders: { Authorization: 'Bearer ' + jwt }
      });
      return next(reqWithToken);
    }
  }

  return next(req);
};
