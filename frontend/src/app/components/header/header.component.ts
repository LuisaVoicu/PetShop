import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../../models/User';
import { AxiosService } from '../../axios.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {
	@Input() pageTitle!: string;
	@Input() logoSrc!: string;
  @Input() loggedUser: User | null = null;  



  roles: string[] = [];
  authenticated: boolean = false

  constructor(private axiosService: AxiosService){}

  
  ngOnInit(): void {
    this.roles = this.axiosService.getUserRoles();
    this.authenticated  = this.axiosService.isAuthenticated();
  }

}