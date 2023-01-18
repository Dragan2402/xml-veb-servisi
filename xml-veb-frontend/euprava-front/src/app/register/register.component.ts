import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';
import { SnackbarComponent } from '../snackbar/snackbar.component';

@Component({
  selector: 'euprava-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email]);

  password = new FormControl('',[Validators.required]);

  firstName = new FormControl('', [Validators.required]);

  lastName = new FormControl('',[Validators.required]);

  isClient:boolean;

  constructor(private authService:AuthenticationService,private _snackBar: MatSnackBar, private router:Router) {
    this.isClient = true;
  }

  ngOnInit(): void {
  }


  getEmailErrorMessage() {
    return this.email.hasError('required') ? 'Molimo vas unesite vasu email adresu' :
        this.email.hasError('email') ? 'Neispravna email adresa' :
            '';
  }

  getPasswordErrorMessage() {
    return this.password.hasError('required') ? 'Molimo vas unesite vasu lozinku': '';
  }

  signUp(){
    if(this.email.valid && this.lastName.valid && this.password.valid && this.firstName.valid){
      let role = this.isClient ? "Klijent":"Sluzbenik";
      this.authService.signUp(<string>this.email.value,<string> this.password.value,<string> this.firstName.value,<string> this.lastName.value,role).subscribe({
        next:(v)=>{
          console.log(v);
          this._snackBar.openFromComponent(SnackbarComponent, {data:"Successfully registered. Please sign in."});
          this.router.navigate(["/"]);
        },
        error:(err)=>{
          console.log(err);
          this._snackBar.openFromComponent(SnackbarComponent, {data:"Email already taken."});
        }
      })
    }
  }

}
