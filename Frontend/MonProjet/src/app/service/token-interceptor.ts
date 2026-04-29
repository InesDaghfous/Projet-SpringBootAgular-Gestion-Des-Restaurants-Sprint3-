import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from './auth.service';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const toExclude = '/login';

  // Si c'est une requête vers /login, on n'ajoute pas le header Authorization
  // (pas encore de token JWT à ce moment-là)
  if (req.url.search(toExclude) === -1) {
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
