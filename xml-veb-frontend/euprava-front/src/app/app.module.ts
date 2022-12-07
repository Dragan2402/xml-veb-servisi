import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AppRoutingModule } from './app-routing.module';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { APP_CONFIG, APP_SERVICE_CONFIG } from './AppConfig/appconfig.service';
import { AppComponent } from './app.component';
import { AppNavComponent } from './app-nav/app-nav.component';
import { LoginComponent } from './login/login.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatBadgeModule } from '@angular/material/badge';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatFormFieldModule} from '@angular/material/form-field';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { UserProfileComponent } from './user-profile/user-profile.component';
import {MatCard, MatCardModule} from '@angular/material/card';

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
    AuthorComponent
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
    HttpClientModule

  ],
  providers: [{
    provide: APP_SERVICE_CONFIG,
    useValue: APP_CONFIG,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
