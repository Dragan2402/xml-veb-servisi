import { Component, OnInit } from '@angular/core';

import {FormControl, Validators} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'euprava-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  email = new FormControl('', [Validators.required, Validators.email]);

  password = new FormControl('',[Validators.required]);

  hide: boolean = true;

  constructor(private route: Router) { }

  ngOnInit(): void {
  }

  login(){
    this.route.navigate(['/userProfile']);
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
