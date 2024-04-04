import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  // Implement your logic to check user roles here
  hasRoles(roles: string[]): boolean {
    // Example implementation: Check if the user's role matches any of the required roles
    const userRoles = ['ADMIN', 'SELLER']; // Example user roles
    return roles.some(role => userRoles.includes(role));
  }
}
