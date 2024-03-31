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

const routes: Routes = [
  { path: 'product', component: ProductComponent },
  { path: 'product-create', component: ProductCreateComponent },
  { path: 'product-delete', component: ProductDeleteComponent },
  { path: 'product-edit', component: ProductEditComponent },
  { path: 'pet', component: PetComponent },
  { path: 'pet-create', component: PetCreateComponent },
  { path: 'pet-delete', component: PetDeleteComponent },
  { path: 'pet-edit', component: PetEditComponent },
  { path: 'home', component: HomeComponent },
  { path: 'user', component: UserComponent},
  { path: 'user-delete', component: UserDeleteComponent},
  { path: 'user-edit', component: UserEditComponent}
  //{ path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route
  //{ path: '**', redirectTo: '/home' }, // Redirect to home for any other route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
