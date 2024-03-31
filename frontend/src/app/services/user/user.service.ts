import { Injectable } from '@angular/core';
import { User } from '../../models/User';
import { AxiosService } from '../../axios.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  selectedUser:  User = {
    id:0,
    firstName: '',
    lastName: '',
    username: '',
    emailAddress: '',
    imageUrl: ''
  };


  constructor(private axiosService: AxiosService) {}

  getAllUsers(): Observable<User[]> {
    return new Observable<User[]>(observer => {
      this.axiosService.request('GET', '/user', {})
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  createUser(userData: any): Observable<User> {
    return new Observable<User>(observer => {
      this.axiosService.request('POST', '/user-create', userData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  deleteUser(userData: any): Observable<User> {
    return new Observable<User>(observer => {
      this.axiosService.request('POST', '/user-delete', userData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  setUserToEdit(userData: User):void{
    this.selectedUser = userData;
  }
  editUser(userData: any): Observable<User> {
    return new Observable<User>(observer => {
      this.axiosService.request('POST', '/user-edit', userData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }}
