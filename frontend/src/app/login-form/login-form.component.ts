import { EventEmitter, Component, Output } from '@angular/core';
import { AxiosService } from '../axios.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  })
export class LoginFormComponent {

  @Output() onSubmitLoginEvent = new EventEmitter();
  @Output() onSubmitRegisterEvent = new EventEmitter();

	active: string = "login";
  firstName: string = "";
  lastName: string = "";
  password: string = "";
  emailAddress: string = "";
	username: string ="";

  constructor(private axiosService: AxiosService){}

  onLoginTab(): void {
		this.active = "login";
	}

	onRegisterTab(): void {
		this.active = "register";
	}

  //todo - modified from login to username
  onSubmitLogin(): void {
    this.onSubmitLoginEvent.emit({"username": this.username, "password": this.password});
  }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit({"firstName": this.firstName, "lastName": this.lastName, "username": this.username, "password": this.password});
  }

}