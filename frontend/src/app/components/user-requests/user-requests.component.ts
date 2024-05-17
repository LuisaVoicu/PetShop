import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AxiosService } from '../../axios.service';
import { AdminRequest } from '../../models/AdminRequest';

@Component({
  selector: 'app-user-requests',
  templateUrl: './user-requests.component.html',
  styleUrl: './user-requests.component.css'
})
export class UserRequestsComponent {
  
  userRequests: AdminRequest[] = [];
  username: string = '';

  constructor(private axiosService: AxiosService, private route:ActivatedRoute){
  }


  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.username = params['user'];
    });
    this.fetchRequests();
  }

  fetchRequests():void{

    this.axiosService.request(
      "POST",
      "/user-requests",
      {username: this.username}).then(
      (response) => {
        this.userRequests = response.data;
      })
      .catch((error) => {
        console.error(error);
      });


  }

  handleRequest(selectedRole: string) {
    if (selectedRole === 'seller') {
      this.requestRole("ROLE_SELLER");
      this.fetchRequests();
    } else if (selectedRole === 'foster') {
      this.requestRole("ROLE_FOSTER");
      this.fetchRequests();
    }
  }
  requestRole(role: string){

    this.axiosService.request('POST', '/request-role', {username: this.username	, request: role})
    .then(response => {
      this.fetchRequests();
    })
    .catch(error => {
      console.log("Error occurred while creating seller/foster request:", error);
    });

  }

  withdrawalRequest(request: AdminRequest):void{
    this.axiosService.request(
      "POST",
      "/delete-request",request).then(
      (response) => {
        console.log("deleted succesfully!!")
        this.fetchRequests();
      })
      .catch((error) => {
        console.error(error);
      });
  }
}
