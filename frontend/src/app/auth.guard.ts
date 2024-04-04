import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): boolean {
    const roles = next.data['roles'] as Array<string>;
    if (roles && !this.authService.hasRoles(roles)) {
      // Redirect the user to the home page or an unauthorized page
      this.router.navigate(['/']);
      return false;
    }
    return true;
  }
}
