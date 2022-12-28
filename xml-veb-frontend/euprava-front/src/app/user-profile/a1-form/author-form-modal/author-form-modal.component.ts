import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Adresa } from 'src/app/model/a1Request/adresa';
import { TZiviAutor } from 'src/app/model/a1Request/author/ziviAutor';
import { TPreminuliAutor } from 'src/app/model/a1Request/author/preminuliAutor';
import { TStranoDrzavljanstvo } from 'src/app/model/a1Request/citizenship/stranoDrzavljanstvo';
import { TOsoba } from 'src/app/model/a1Request/osoba';

@Component({
  selector: 'euprava-author-form-modal',
  templateUrl: './author-form-modal.component.html',
  styleUrls: ['./author-form-modal.component.css']
})
export class AuthorFormModalComponent implements OnInit {

  isAuthorAlive : boolean;
  authorIsForeignCitizenship : boolean;

  authorSign = new FormControl<string>('');

  authorFirstName = new FormControl<string>('',[Validators.required]);
  authorLastName = new FormControl<string>('',[Validators.required]);
  authorPlace = new FormControl<string>('',[Validators.required]);
  authorStreet = new FormControl<string>('',[Validators.required]);
  authorZipCode = new FormControl<number>(21000,[Validators.required, Validators.min(1000),Validators.max(100000)]);
  authorStreetNumber = new FormControl<number>(0,[Validators.required, Validators.min(0)])
  authorJmbg = new FormControl<string>('',[Validators.required,Validators.pattern(/^[0-9]{13}$/)])
  authorPassport = new FormControl<string>('',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]{3,20}$/)])
  authorDod = new FormControl<Date>(new Date(),[Validators.required]);


  constructor(public dialogRef: MatDialogRef<AuthorFormModalComponent>) {
    this.isAuthorAlive = true;
    this.authorIsForeignCitizenship =false;
  }

  ngOnInit(): void {
  }

  addAuthor(){
    const author = this.isAuthorAlive ? this.GetAliveAuthor() : this.GetDeadAuthor();
    if(author !== null){
      this.dialogRef.close(author);
    }

  }

  closeDialog(){
    this.dialogRef.close();
  }

  private GetAliveAuthor():any{
    // if(this.authorFirstName.valid && this.authorLastName.valid && this.authorPlace.valid && this.authorStreet.valid &&
    //    this.authorZipCode.valid && this.authorStreetNumber.valid &&
    //    ((this.authorPassport.valid && this.authorIsForeignCitizenship) || (this.authorJmbg.valid && !this.authorIsForeignCitizenship))){
    //     let sign = this.authorSign.value==="" ? null:<string>this.authorSign.value;
    //     return new AliveAuthor(sign,this.GetAliveAuthorPerson());
    // }else{
    //   return null;
    // }
  }

  private GetDeadAuthor(){
    // if(this.authorFirstName.valid && this.authorLastName.valid && this.authorSign.valid && this.authorDod.valid){
    //   let sign = this.authorSign.value==="" ? null:<string>this.authorSign.value;
    //   return new DeadAuthor(sign,<string>this.authorFirstName.value, <string>this.authorLastName.value,<Date>this.authorDod.value);
    // }else{
    //   return null;
    // }

  }

  private GetAliveAuthorPerson(){
    // const address = new Adresa(<string>this.authorPlace.value,<string>this.authorZipCode.value?.toString(),<string>this.authorStreet.value,<number>this.authorStreetNumber.value);
    // const citizenship = this.authorIsForeignCitizenship ? new TStranoDrzavljanstvo(<string>this.authorPassport.value): new DomesticCitizenship(<string>this.authorJmbg.value);
    // return new TOsoba(<string>this.authorFirstName.value,<string>this.authorLastName.value,address,citizenship);
  }
}
