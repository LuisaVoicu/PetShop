export class Product{
    
    id!:number;
    name!:string;
    description?:string;
    tags?:string[];
    favorite?:boolean = false;
    stars?:number = 0;
    price!:number;
    imageUrl!:string;

}