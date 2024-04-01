export class User{
    
    id!:number;
    firstName!:string;
    lastName!:string;
    username!:string;
    emailAddress!:string;
    birthdate?:object;
    roles?:object;
    ownProducts?:object;
    saleProducts?:object;
    favoriteProducts?:object;
    cartProducts?:object;
    imageUrl!:string;

}