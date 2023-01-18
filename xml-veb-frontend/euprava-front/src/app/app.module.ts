import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { AppComponent } from './app.component';
import { AppNavComponent } from './app-nav/app-nav.component';
import { LoginComponent } from './login/login.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatBadgeModule } from '@angular/material/badge';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {MatCard, MatCardModule} from '@angular/material/card';
import {MatTableModule} from '@angular/material/table';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatDialogModule} from '@angular/material/dialog';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatMenuModule} from '@angular/material/menu';
import { A1FormComponent } from './user-profile/a1-form/a1-form.component';
import {MatSelectModule} from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { UserRequestsComponent } from './user-profile/user-requests/user-requests.component';
import { A1RequestComponent } from './user-profile/user-requests/a1-request/a1-request.component';
import { SubmitterComponent } from './user-profile/user-requests/a1-request/submitter/submitter.component';
import { PersonComponent } from './user-profile/user-requests/a1-request/person/person.component';
import { AddressComponent } from './user-profile/user-requests/a1-request/address/address.component';
import { CitizenshipComponent } from './user-profile/user-requests/a1-request/person/citizenship/citizenship.component';
import { PieceComponent } from './user-profile/user-requests/a1-request/piece/piece.component';
import { OriginalPieceComponent } from './user-profile/user-requests/a1-request/piece/original-piece/original-piece.component';
import { AuthorComponent } from './user-profile/user-requests/a1-request/piece/author/author.component';
import {MatRadioModule} from '@angular/material/radio';
import { AuthorFormModalComponent } from './user-profile/a1-form/author-form-modal/author-form-modal.component';
import { P1FormComponent } from './user-profile/p1-form/p1-form.component';
import { RegisterComponent } from './register/register.component';
import { EmployeeProfileComponent } from './employee-profile/employee-profile.component';
import { SnackbarComponent } from './snackbar/snackbar.component';
import {MatSnackBarModule, MAT_SNACK_BAR_DEFAULT_OPTIONS} from '@angular/material/snack-bar';
import { HandleRequestComponent } from './employee-profile/handle-request/handle-request.component';

@NgModule({
  declarations: [
    AppComponent,
    AppNavComponent,
    LoginComponent,
    UserProfileComponent,
    A1FormComponent,
    UserRequestsComponent,
    A1RequestComponent,
    SubmitterComponent,
    PersonComponent,
    AddressComponent,
    CitizenshipComponent,
    PieceComponent,
    OriginalPieceComponent,
    AuthorComponent,
    AuthorFormModalComponent,
    P1FormComponent,
    RegisterComponent,
    EmployeeProfileComponent,
    SnackbarComponent,
    HandleRequestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    NoopAnimationsModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatBadgeModule,
    MatSnackBarModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatMenuModule,
    MatSelectModule,
    MatCardModule,
    MatRadioModule,
    MatCheckboxModule,
    MatDialogModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatTableModule,
    HttpClientModule,
    MatSnackBarModule

  ],
  providers: [    { provide: MAT_SNACK_BAR_DEFAULT_OPTIONS, useValue: {horizontalPosition: 'center',
  verticalPosition: 'top', panelClass : "snackbar", duration:1500}},],
  bootstrap: [AppComponent]
})
export class AppModule { }
