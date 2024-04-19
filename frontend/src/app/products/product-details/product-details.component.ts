import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../../models/Product';
import { ProductComponent } from '../product/product.component';
import { ProductReview } from '../../models/ProductReview';
import { AxiosService } from '../../axios.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {


  productId: number=0;
  product: Product= new Product();
  reviews: ProductReview[] = []; 

  constructor(private axiosService: AxiosService, private activatedRoute: ActivatedRoute){}
  
  ngOnInit(): void{
    this.activatedRoute.params.subscribe(params =>{
      this.productId = +params['id'];
      console.log("aici am ajuns!!!" + this.productId);
    });

    this.setProductDetails();
    this.setReviews();

  }

  setProductDetails(){


    this.axiosService.request('POST', '/product-id', this.productId)
      .then(response => {
        console.log("Received response: PRODUCT from id", response.data);
        this.product = response.data;
      })
      .catch(error => {
        console.log("Error occurred while creating review:", error);

      });

  }
  setReviews(){

    this.axiosService.request('POST', '/review-id', this.productId)
    .then(response => {
      console.log("Received response: PRODUCT from id", response.data);
      this.reviews = response.data;
    })
    .catch(error => {
      console.log("Error occurred while creating review:", error);

    });
undefined
  }

  getStarIcons(stars: number | undefined) : string {
    if (stars === undefined) return ''; 
    let starIcons = '';
    for (let i = 1; i <= 5; i++) {
      if (i <= stars) {
        starIcons += '★'; 
      } else {
        starIcons += '☆'; 
      }
    }
    return starIcons;
  }

}
