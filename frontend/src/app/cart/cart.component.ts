import { Component } from '@angular/core';
import { UserService } from '../services/user/user.service';
import { User } from '../models/User';
import { AxiosService } from '../axios.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../models/Product';
@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  products: Product[] = [];

  newUser: User = {
    id:0,
    firstName: '',
    lastName: '',
    username: '',
    emailAddress: '',
    imageUrl: ''
  };

  username: string = '';

  constructor(private axiosService: AxiosService, private userService : UserService, private route:ActivatedRoute){}

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.username = params['user'];
      console.log("[cart]IM HEREEE!!" + this.username);
    });
  //   this.axiosService.request(
  //     "GET",
  //     "/cart-product",
  //     this.username).then(
  //     (response) => {
  //       console.log(" work!!!!!!!!!!!!")

  //         this.products = response.data;
  
  //     }).catch(
  //     (error) => {
  //         if (error.response.status === 401) {
  //             this.axiosService.setAuthToken(null);
  //         } else {
  //             this.products = error.response.code;
  //         }
  
  //     }
  // );

      this.userService.getCartProducts(this.username).subscribe(
      products => {
        this.products = products;
        console.log("HELLOOOOOOOO FROM CART " + this.username)

      },
      error => {
        console.error('Error fetching products:', error);
      }
    );

  }

  buyProducts(username: string):void{

  }
    
}
