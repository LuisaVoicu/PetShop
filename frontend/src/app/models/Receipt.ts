import { Product } from "./Product";

export class Receipt{
    
    firstName!:string;
    lastName!:string;
    username?:string;
    boughtProducts?:Product[];
    date!:Date;

}