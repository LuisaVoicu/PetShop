import { Component } from '@angular/core';
import { ProductService } from '../../services/product/product.service';
import { Product } from '../../models/Product';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-create',
  templateUrl: './product-create.component.html',
  styleUrl: './product-create.component.css'
})
export class ProductCreateComponent {

  products: Product[] = [];
  newProduct: Product = {
    id:0,
    name: '',
    description: '',
    price: 0,
    imageUrl: ''
  };

  constructor(private productService : ProductService, private route:ActivatedRoute, private router: Router){

  }

  fetchProducts(): void{
    this.productService.getAllProducts().subscribe(
      products => {
        this.products = products;
      },
      error => {
        console.error('Error fetching products:', error);
      }
    );
  }


  create(): void {
    this.productService.createProduct(this.newProduct).subscribe(
      createdProduct => {
        console.log('Product created successfully:', createdProduct);
        this.newProduct = {
          id:0,
          name: '',
          description: '',
          price: 0,
          imageUrl: ''
        };

      },
      error => {
        console.error('Error creating product:', error);
      }
    );

  }

  createProduct(): void {
    // Call createProduct method when the button is clicked
    this.create();
    this.router.navigateByUrl('/product');

  }

}
