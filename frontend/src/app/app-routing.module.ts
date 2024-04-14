import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthContentComponent } from './auth-content/auth-content.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ProductComponent } from './products/product/product.component';
import { HomeComponent } from './home/home.component';
import { ProductCreateComponent } from './products/product-create/product-create.component';
import { ProductDeleteComponent } from './products/product-delete/product-delete.component';
import { ProductEditComponent } from './products/product-edit/product-edit.component';
import { PetComponent } from './pets/pet/pet.component';
import { PetCreateComponent } from './pets/pet-create/pet-create.component';
import { PetDeleteComponent } from './pets/pet-delete/pet-delete.component';
import { PetEditComponent } from './pets/pet-edit/pet-edit.component';
import { UserComponent } from './users/user/user.component';
import { UserDeleteComponent } from './users/user-delete/user-delete.component';
import { UserEditComponent } from './users/user-edit/user-edit.component';
import { CategoryComponent } from './categories/category/category.component';
import { CategoryEditComponent } from './categories/category-edit/category-edit.component';
import { CategoryDeleteComponent } from './categories/category-delete/category-delete.component';
import { LoggedFormComponent } from './logged-form/logged-form.component';
import { CartComponent } from './cart/cart.component';
import { FavProdComponent } from './fav-prod/fav-prod.component';
import { ReceiptComponent } from './receipt/receipt.component';
import { LoginActivityComponent } from './login-activity/login-activity.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  //{ path: 'product-create', component: ProductCreateComponent, canActivate: [AuthGuard], data: { roles: ['ADMIN', 'SELLER'] } },
  { path: 'product', component: ProductComponent, canActivate: [AuthGuard]},
  { path: 'product-create', component: ProductCreateComponent,canActivate: [AuthGuard] },
  { path: 'product-delete', component: ProductDeleteComponent },
  { path: 'product-edit', component: ProductEditComponent },
  { path: 'pet', component: PetComponent },
  { path: 'pet-create', component: PetCreateComponent },
  { path: 'pet-delete', component: PetDeleteComponent },
  { path: 'pet-edit', component: PetEditComponent },
  { path: 'user', component: UserComponent},// canActivate: [AuthGuard], data: { roles: ['ADMIN', 'SELLER'] }},
  { path: 'user-delete', component: UserDeleteComponent},
  { path: 'user-edit', component: UserEditComponent},
  { path: 'category', component: CategoryComponent},
  { path: 'category-delete', component: CategoryDeleteComponent},
  { path: 'category-edit', component: CategoryEditComponent},
  { path: 'home', component: HomeComponent },
  { path: 'logged/:username', component: LoggedFormComponent,canActivate: [AuthGuard] },
  { path: 'search/:searchTerm', component:HomeComponent},
  { path: 'cart-product', component: CartComponent},
  { path: 'receipt', component: ReceiptComponent},
  { path: 'fav-prod', component: FavProdComponent, canActivate: [AuthGuard]},
  { path: 'login-activity', component: LoginActivityComponent},
  { path: '', redirectTo: '/home', pathMatch: 'full' }, 
  { path: '**', redirectTo: '/home' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
