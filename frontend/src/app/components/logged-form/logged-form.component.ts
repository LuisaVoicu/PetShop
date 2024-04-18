import { Component, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/User';
import { AxiosService } from '../../axios.service';
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
    email_address: '',
    imageurl: '',
    loginTime: new Date()
  };

  someUsers : User[]=[];

  username: string = '';

  constructor(private axiosService: AxiosService, private route: ActivatedRoute) { }

  ngOnInit(): void {

        this.route.params.subscribe(params => {
          this.username = params['username'];
          console.log('Usernameu vietii:', this.username);
          console.log('token in logged::::', this.axiosService.getAuthToken());

        });

        const requestBody = { username: this.username };

        this.axiosService.request(
        "POST",
        "/logged",
        this.username).then(
        (response) => {
            this.loggedUser = response.data;
            console.log("logged firstname---->"+this.loggedUser.firstName);

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
