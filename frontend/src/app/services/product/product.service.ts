import { Injectable } from '@angular/core';
import { Product } from '../../models/Product';
import { AxiosService } from '../../axios.service';
import { Observable } from 'rxjs';
import { User } from '../../models/User';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

    selectedProduct : Product = {
      id:0,
      name: '',
      description: '',
      price: 0,
      imageUrl: ''
    };
  

    constructor(private axiosService: AxiosService) {}

    getAllProducts(): Observable<Product[]> {
      return new Observable<Product[]>(observer => {
        this.axiosService.request('GET', '/product', {})
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

    // getAllGoodProducts(): Promise<Product[]> {
    //   return new Promise<Product[]>((resolve, reject) => {
    //     this.axiosService.request('GET', '/product', {})
    //       .then(response => {
    //         resolve(response.data);
    //       })
    //       .catch(error => {
    //         reject(error);
    //       });
    //   });
    // }

    createProduct(productData: any): Observable<Product> {
      return new Observable<Product>(observer => {
        this.axiosService.request('POST', '/product-create', productData)
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

    deleteProduct(productData: any): Observable<Product> {
      return new Observable<Product>(observer => {
        this.axiosService.request('POST', '/product-delete', productData)
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

    setProductToEdit(productData: Product):void{
      this.selectedProduct = productData;
    }
    editProduct(productData: any): Observable<Product> {
      return new Observable<Product>(observer => {
        this.axiosService.request('POST', '/product-edit', productData)
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

    addToCart(productId: number, username: string): Observable<User> {
      return new Observable<User>(observer => {
        console.log("!!!!!!!!!!" + productId);
        this.axiosService.request('POST', '/add-cart',  { productId: productId, username: username } ) // Pass parameters as an object
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

    // deleteProduct(productData: Product): Observable<Product> {
    //   return from(this.axiosService.request('POST', '/product-delete', productData));
    // }

  }

