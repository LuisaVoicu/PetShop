import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
	@Input() pageTitle!: string;
	@Input() logoSrc!: string;
  @Input() loggedUser: User | null = null;  // Define input property for loggedUser

  // constructor(private router: Router) {}

  // navigateTo(route: string) {
  //   this.router.navigate([route]);
  // }
}