import { Component } from '@angular/core';
import { ProductService } from '../../services/product/product.service';
import { Product } from '../../models/Product';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/User';
import { SourceTextModule } from 'vm';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  products: Product[] = [];

  username: string = '';

  constructor(private productService : ProductService, private route:ActivatedRoute){

  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.username = params['user'];
      console.log("IM HEREEE!!" + this.username);
    });
    
    this.productService.getAllProducts().subscribe(
      products => {
         this.products = products;
        console.log("HELLOOOOOOOO " + this.username)

      },
      error => {
        console.error('Error fetching products:', error);
      }
    );

    // this.route.params.subscribe(params => {
    //   if (params['searchTerm']){
    //     this.productService.getAllProducts().subscribe(
    //       products => {
    //          this.products = products;
    //         console.log("HELLOOOOOOOO " + this.products.length)
    //          this.products = this.products.filter(prod=> prod.name.toLocaleLowerCase().includes(params['searchTerm'].toLowerCase()))
    //       },
    //       error => {
    //         console.error('Error fetching products:', error);
    //       }
    //     );
    //   }
    //   else{
    //     this.productService.getAllProducts().subscribe(
    //       products => {
    //         this.products = products;
    //         console.log("HELLOOOOOOOO " + this.products.length)
    //         this.products = this.products.filter(prod=> prod.name.toLocaleLowerCase().includes(params['searchTerm'].toLowerCase()))
    //       },
    //       error => {
    //         console.error('Error fetching products:', error);
    //       }
    //     );
    //   }
    // })
  }

  // fetchProducts(): void{
  //   this.productService.getAllProducts().subscribe(
  //     products => {
  //       this.products = products;
  //     },
  //     error => {
  //       console.error('Error fetching products:', error);
  //     }
  //   );
  // }

  addToCart(product:Product, username: string): any{
    this.productService.addToCart(product.id, username).subscribe(
      user => {
        console.log("ADDED succesfully");
      },
      error => {
        console.error('Error adding product to cart:', error);
      }
    );
  }

}
