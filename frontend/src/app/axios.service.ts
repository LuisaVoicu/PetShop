import { Injectable } from '@angular/core';
import axios from 'axios';
import { Receipt } from './models/Receipt';
@Injectable({
  providedIn: 'root'
})
export class AxiosService {

  constructor() {
    axios.defaults.baseURL = 'http://localhost:8080';
    axios.defaults.headers.post['Content-Type'] = 'application/json';
  }

  getAuthToken(): string | null {
  // return window.localStorage.getItem("auth_token");

   if (typeof window !== 'undefined') {
    return window.localStorage.getItem("auth_token");
  }
  return null;
  
  }

  setAuthToken(token: string | null): void {
    if (token !== null) {
      window.localStorage.setItem("auth_token", token);
    } else {
      window.localStorage.removeItem("auth_token");
    }
  }


  request(method: string, url: string, data: any): Promise<any> {
      let headers: any = {};

      if (this.getAuthToken() !== null) {
        console.log("im here");
          headers = {"Authorization": "Bearer" + this.getAuthToken()};
      }

      console.log("----> m:" + method + " u:" + url + " d:" + data + " h:" + headers);
      return axios({
          method: method,
          url: url,
          data: data,
          headers: headers
      });
  }

  
}