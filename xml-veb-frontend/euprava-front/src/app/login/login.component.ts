import { Component, OnInit } from '@angular/core';

import {FormControl, Validators} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../authentication/authentication.service';

@Component({
  selector: 'euprava-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email]);

  password = new FormControl('',[Validators.required]);

  hide: boolean = true;

  constructor(private route: Router, private authenticationService: AuthenticationService) { }

  ngOnInit(): void {
  }

  login(){
    if(this.email.valid && this.password.valid){
      this.authenticationService.login(<string>this.email.value,<string> this.password.value).subscribe();
    }
  }

  getEmailErrorMessage() {
    return this.email.hasError('required') ? 'Molimo vas unesite vasu email adresu' :
        this.email.hasError('email') ? 'Neispravna email adresa' :
            '';
  }

  getPasswordErrorMessage() {
    return this.password.hasError('required') ? 'Molimo vas unesite vasu lozinku': '';
  }

}
