import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersItemsComponent} from "./users/users-items/users-items.component";
import {UsersNewComponent} from "./users/users-new/users-new.component";

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'users'
  },
  {
    path: 'users',
    component: UsersItemsComponent
  },
  {
    path: 'users-new',
    component: UsersNewComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
