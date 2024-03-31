import { Component } from '@angular/core';
import { ProductService } from '../../services/product/product.service';
import { Product } from '../../models/Product';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {
  products: Product[] = [];
  newProduct: Product = {
    id:0,
    name: '',
    description: '',
    price: 0,
    imageUrl: ''
  };

  constructor(private productService : ProductService, private route:ActivatedRoute){

  }

  ngOnInit(): void {
    this.productService.getAllProducts().subscribe(
      products => {
         this.products = products;
        console.log("HELLOOOOOOOO " + this.products.length)

      },
      error => {
        console.error('Error fetching products:', error);
      }
    );
  }

}
