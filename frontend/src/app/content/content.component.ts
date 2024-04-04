import { Component } from '@angular/core';
import { AxiosService } from '../axios.service';
import { User } from '../models/User';
import { Router } from '@angular/router';
import { SourceTextModule } from 'vm';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrls: ['./content.component.css']
})
export class ContentComponent {
	componentToShow: string = "welcome";

	loggedUser: User = {
		id:0,
		firstName: '',
		lastName: '',
		username: '',
		emailAddress: '',
		imageUrl: ''
	  };

	username:string='';
	
	constructor(private axiosService: AxiosService, private router: Router) {

	 }

	showComponent(componentToShow: string): void {
    this.componentToShow = componentToShow;
  }

	onLogin(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/login",
		    {
		        login: input.login,
		        password: input.password
		    }).then(
		    response => {
				console.log("parola:"+input.password);
		        this.axiosService.setAuthToken(response.data.token);
		        this.componentToShow = "logged";
				this.username = response.data.username;
				console.log("@@@@@@@@@@@@@@@@@@@@@@@@: "+this.username + " --- "+response.data.username);

				this.loggedUser = response.data;
				this.router.navigate(['/logged',this.username]);

		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);

	}

	onRegister(input: any): void {
		this.axiosService.request(
		    "POST",
		    "/register",
		    {
		        firstName: input.firstName,
		        lastName: input.lastName,
		        login: input.login,
				emailAddress: input.emailAddress,
				username: input.username,
		        password: input.password
		    }).then(
		    response => {
		        this.axiosService.setAuthToken(response.data.token);
		        this.componentToShow = "messages";
		    }).catch(
		    error => {
		        this.axiosService.setAuthToken(null);
		        this.componentToShow = "welcome";
		    }
		);
	}

}