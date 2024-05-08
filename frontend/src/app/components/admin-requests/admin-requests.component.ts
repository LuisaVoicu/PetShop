import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AxiosService } from '../../axios.service';
import { AdminRequest } from '../../models/AdminRequest';

@Component({
  selector: 'app-admin-requests',
  templateUrl: './admin-requests.component.html',
  styleUrl: './admin-requests.component.css'
})
export class AdminRequestsComponent {

  constructor(private axiosService: AxiosService, private route:ActivatedRoute){

  }

  adminRequests: AdminRequest[] = [];

  ngOnInit(): void {


    this.axiosService.request(
      "GET",
      "/admin-requests",
      {}).then(
      (response) => {
        this.adminRequests = response.data;
      })
      .catch((error) => {
        console.error(error);
      });



  }

  approveRequest(request: AdminRequest){
    this.axiosService.request('POST', '/admin-aprove', request)
    .then(response => {
      
    })
    .catch(error => {
      console.log("Error occurred while creating seller/foster request:", error);
    });
  }

}
