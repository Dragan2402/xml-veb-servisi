import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { A1FormComponent } from './user-profile/a1-form/a1-form.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserRequestsComponent } from './user-profile/user-requests/user-requests.component';
import { P1FormComponent } from './user-profile/p1-form/p1-form.component';
import { LoginGuard } from './guard/login.guard';
import { RegisterComponent } from './register/register.component';
import { EmployeeProfileComponent } from './employee-profile/employee-profile.component';

const routes: Routes = [
  {path : 'login', component : LoginComponent},
  {path : 'userProfile', component : UserProfileComponent,  canActivate:[LoginGuard]},
  {path : 'employeeProfile', component : EmployeeProfileComponent,  canActivate:[LoginGuard]},
  {path : 'a1-from', component: A1FormComponent ,  canActivate:[LoginGuard]},
  {path : 'user-requests', component: UserRequestsComponent,  canActivate:[LoginGuard]},
  {path : 'p1-form', component: P1FormComponent,  canActivate:[LoginGuard]},
  {path : 'register', component:RegisterComponent},
  {path: '' , redirectTo : '/login', pathMatch : 'full'}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
