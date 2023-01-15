import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { A1FormComponent } from './user-profile/a1-form/a1-form.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserRequestsComponent } from './user-profile/user-requests/user-requests.component';
import { P1FormComponent } from './user-profile/p1-form/p1-form.component';

const routes: Routes = [
  {path : 'login', component : LoginComponent},
  {path : 'userProfile', component : UserProfileComponent},
  {path : 'a1-from', component: A1FormComponent},
  {path : 'user-requests', component: UserRequestsComponent},
  {path : 'p1-form', component: P1FormComponent},
  {path: '' , redirectTo : '/login', pathMatch : 'full'}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
