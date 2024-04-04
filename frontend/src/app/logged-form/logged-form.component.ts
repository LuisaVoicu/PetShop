import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../models/User';
import { AxiosService } from '../axios.service';
@Component({
  selector: 'app-logged-form',
  templateUrl: './logged-form.component.html',
  styleUrl: './logged-form.component.css'
})
export class LoggedFormComponent {
  loggedUser: User = {
    id:0,
    firstName: '',
    lastName: '',
    username: '',
    emailAddress: '',
    imageUrl: ''
  };


  username: string = '';

  constructor(private axiosService: AxiosService, private route: ActivatedRoute) { }

  ngOnInit(): void {

   this.route.params.subscribe(params => {
    this.username = params['username'];
    console.log('Usernameu vietii:', this.username);
  });

   this.axiosService.request(
    "POST",
    "/logged",
    this.username).then(
    (response) => {
        this.loggedUser = response.data;

    }).catch(
    (error) => {
        if (error.response.status === 401) {
            this.axiosService.setAuthToken(null);
        } else {
            this.loggedUser = error.response.code;
        }

    }
);
  }


}
