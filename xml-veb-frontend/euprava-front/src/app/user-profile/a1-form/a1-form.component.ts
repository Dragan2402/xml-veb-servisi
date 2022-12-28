import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { interval, firstValueFrom } from 'rxjs';
import { TPodnosilac } from 'src/app/model/a1Request/submitter/podnosilac';
import {TPravniPodnosilac} from 'src/app/model/a1Request/submitter/pravniPodnosilac';
import {TFizickiPodnosilac} from 'src/app/model/a1Request/submitter/fizickiPodnosilac';
import { Adresa } from 'src/app/model/a1Request/adresa';
import { TOsoba } from 'src/app/model/a1Request/osoba';

import { TStranoDrzavljanstvo } from 'src/app/model/a1Request/citizenship/stranoDrzavljanstvo';
import { ObrazacA1 } from 'src/app/model/a1Request/obrazacA1';
import { HttpClient, HttpEvent, HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VrstaDjela } from 'src/app/model/a1Request/piece/vrstaDjela';
import { FormaZapisa } from 'src/app/model/a1Request/piece/formaZapisa';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AuthorFormModalComponent } from './author-form-modal/author-form-modal.component';
import { TAutor } from 'src/app/model/a1Request/author/autor';
import { TDjelo } from 'src/app/model/a1Request/piece/djelo';
import { PodaciOriginalnoDjelo } from 'src/app/model/a1Request/piece/podaciOriginalnoDjelo';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { TDrzavljanstvo } from 'src/app/model/a1Request/citizenship/tdrzavljanstvo';




@Component({
  selector: 'euprava-a1-form',
  templateUrl: './a1-form.component.html',
  styleUrls: ['./a1-form.component.css']
})
export class A1FormComponent implements OnInit {

  isLegalSubmitter: boolean;
  isForeignCitizenship:boolean;
  hasAttorney : boolean;
  attorneyIsForeignCitizenship : boolean;
  authorType:number;
  isInWorkRelationship: boolean;
  isPieceOriginal: boolean;
  originalAuthorType:number;

  descriptionFile?: File;

  exampleFile? : File;



  submitterIndividualFirstName = new FormControl<string>('',[Validators.required]);
  submitterIndividualLastName = new FormControl<string>('',[Validators.required]);
  submitterLegalName = new FormControl<string>('',[Validators.required]);
  submitterEmail = new FormControl('', [Validators.required, Validators.email]);
  submitterPhoneNumber = new FormControl<string>('',[Validators.required,Validators.pattern(/^[+]?[0-9]{8,13}$/)]);
  submitterPlace = new FormControl<string>('',[Validators.required]);
  submitterStreet = new FormControl<string>('',[Validators.required]);
  submitterZipCode = new FormControl<number>(21000,[Validators.required, Validators.min(1000),Validators.max(100000)]);
  submitterStreetNumber = new FormControl<number>(0,[Validators.required, Validators.min(0)])
  submitterJmbg = new FormControl<string>('',[Validators.required,Validators.pattern(/^[0-9]{13}$/)])
  submitterPassport = new FormControl<string>('',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]{3,20}$/)])

  submitterSignature = new FormControl<string>('',[Validators.required]);

  attorneyFirstName = new FormControl<string>('',[Validators.required]);
  attorneyLastName = new FormControl<string>('',[Validators.required]);
  attorneyPlace = new FormControl<string>('',[Validators.required]);
  attorneyStreet = new FormControl<string>('',[Validators.required]);
  attorneyZipCode = new FormControl<number>(21000,[Validators.required, Validators.min(1000),Validators.max(100000)]);
  attorneyStreetNumber = new FormControl<number>(0,[Validators.required, Validators.min(0)])
  attorneyJmbg = new FormControl<string>('',[Validators.required,Validators.pattern(/^[0-9]{13}$/)])
  attorneyPassport = new FormControl<string>('',[Validators.required,Validators.pattern(/^[a-zA-Z0-9]{3,20}$/)])

  pieceTittle = new FormControl<string>('',[Validators.required]);
  pieceTypeOfUse = new FormControl<string>('');

  pieceTypes= Object.values(VrstaDjela);
  selectedPieceType : VrstaDjela = VrstaDjela.INFORMACIONE_TEHNOLOGIJE;

  writeForms = Object.values(FormaZapisa);
  selectedWriteFrom : FormaZapisa = FormaZapisa.AUDIO;

  pieceAuthors: TAutor[];

  originalPieceAuthors: TAutor[];
  originalPieceTitle = new FormControl<string>('',[Validators.required]);

  constructor(private http: HttpClient, public matDialog: MatDialog, private userService : UserService,private route: Router) {

    this.descriptionFile = undefined;
    this.exampleFile = undefined;

    this.isLegalSubmitter = false;
    this.isForeignCitizenship = false;
    this.hasAttorney =  false;
    this.attorneyIsForeignCitizenship = false;
    this.authorType = 0;
    this.originalAuthorType = 0;
    this.isInWorkRelationship = false;
    this.isPieceOriginal = true;
    this.pieceAuthors =[];
    this.originalPieceAuthors = [];
  }

  ngOnInit(): void {

  }

  SubmitRequest(){

    // const a1:ObrazacA1;

    // const submitter = this.isLegalSubmitter? this.GetLegalSubmitter(): this.GetIndividualSubmitter();
    // if(submitter === null){
    //   console.log("INVALID INPUT SUBMITTER");
    //   return;
    // }
    // a1.SetSubmitter(submitter);

    // const attorney = this.hasAttorney? this.GetAttorney() : undefined;
    // if(attorney !== undefined){
    //   if(attorney === null){
    //     console.log("INVALID INPUT ATTORNEY");
    //     return;
    //   }else{
    //     a1.SetAttorney(attorney);
    // }}

    // const piece = this.GetPiece();

    // if(piece === null){
    //   //this.ResetAuthors();
    //   console.log("INVALID INPUT PIECE");
    //   return;
    // }

    // a1.SetPiece(piece);

    // if(!this.submitterSignature.valid){
    //   console.log("INVALID INPUT SIGNATURE");
    //   return;
    // }
    // a1.SetSignature(<string>this.submitterSignature.value);

    // this.userService.SubmitA1Request(a1, this.descriptionFile, this.exampleFile);


  }


  private GetLegalSubmitter():any{
    // if(this.submitterLegalName.valid && this.submitterEmail.valid &&
    //   this.submitterPhoneNumber.valid && this.submitterPlace.valid &&
    //   this.submitterZipCode.valid && this.submitterStreet.valid &&
    //   this.submitterStreetNumber.valid){
    //     return new LegalSubmitter(<string>this.submitterEmail.value,<string>this.submitterPhoneNumber.value,<string>this.submitterLegalName.value,this.GetSubmitterAddress());
    //   }
    // return null;

  }

  private GetSubmitterAddress():Adresa{
    return new Adresa(<string>this.submitterPlace.value, <string> this.submitterZipCode.value?.toString(),<string> this.submitterStreet.value,
    <number>this.submitterStreetNumber.value);
  }

  private GetIndividualSubmitter():any{
    // if(this.submitterIndividualFirstName.valid && this.submitterIndividualLastName.valid && this.submitterEmail.valid &&
    //   this.submitterPhoneNumber.valid && this.submitterPlace.valid &&
    //   this.submitterZipCode.valid && this.submitterStreet.valid &&
    //   this.submitterStreetNumber.valid && ((this.submitterPassport.valid && this.isForeignCitizenship) || (this.submitterJmbg.valid && !this.isForeignCitizenship))){
    //     return new IndividualSubmitter(<string>this.submitterEmail.value,<string>this.submitterPhoneNumber.value, this.GetSubmitterPerson());

    //   }
    // return null;

  }

  private GetSubmitterPerson(){
    // return new TOsoba(<string>this.submitterIndividualFirstName.value, <string>this.submitterIndividualLastName.value,
    //   this.GetSubmitterAddress(),this.GetSubmitterCitizenship());
  }

  private GetSubmitterCitizenship(){
    // if(this.isForeignCitizenship){
    //   return new TStranoDrzavljanstvo(<string>this.submitterPassport.value);
    // }else{
    //   return new DomesticCitizenship(<string>this.submitterJmbg.value);
    // }
  }

  private GetAttorney():any{
    // if(this.attorneyFirstName.valid && this.attorneyLastName.valid && this.attorneyPlace.valid &&
    //    this.attorneyZipCode.valid && this.attorneyStreet.valid && this.attorneyStreetNumber.valid &&
    //    ((this.attorneyPassport.valid && this.attorneyIsForeignCitizenship) || (this.attorneyJmbg.valid && !this.attorneyIsForeignCitizenship))){
    //     const address = new Adresa(<string>this.attorneyPlace.value,<string>this.attorneyZipCode.value?.toString(),<string>this.attorneyStreet.value,<number>this.attorneyStreetNumber.value);
    //     const citizenship = this.attorneyIsForeignCitizenship ? new TStranoDrzavljanstvo(<string>this.attorneyPassport.value): new DomesticCitizenship(<string>this.attorneyJmbg.value);
    //     return new TOsoba(<string>this.attorneyFirstName.value,<string>this.attorneyLastName.value,address,citizenship);
    // }else{
    //   return null;
    // }
  }

  private IsAuthorsValid():boolean{
    if(this.authorType===2){
      return this.pieceAuthors.length > 0;
    }
    return true;
  }

  private IsOriginalPieceValid():boolean{
    if(!this.isPieceOriginal){
        if(this.originalAuthorType===1){
          return (this.originalPieceTitle.valid && this.originalPieceAuthors.length > 0);
        }
        return this.originalPieceTitle.valid;
    }
    return true;
  }

  private GetPiece():any{
    // if(this.pieceTittle.valid && this.IsAuthorsValid() && this.IsOriginalPieceValid()){
    //   if(this.authorType===0){
    //     this.pieceAuthors =[];
    //   }else if(this.authorType ===1){
    //     this.pieceAuthors =[];
    //     this.pieceAuthors.push(new UnknownAuthor());
    //   }

    //   let pieceUse = <string>this.pieceTypeOfUse.value==="" ? undefined : <string>this.pieceTypeOfUse.value;

    //   let originalPiece = this.isPieceOriginal ? undefined : this.GetOriginalPiece();

    //   return new Piece(<string> this.pieceTittle.value, this.selectedPieceType, this.selectedWriteFrom, this.pieceAuthors,
    //     this.isInWorkRelationship, pieceUse, originalPiece);
    // }else{
    //   return null;
    // }
  }

  private GetOriginalPiece(){
    // if(this.originalAuthorType === 0){
    //   this.originalPieceAuthors = [];
    //   this.originalPieceAuthors.push(new UnknownAuthor());
    // }
    // return new OriginalPiece(<string> this.originalPieceTitle.value, this.originalPieceAuthors);
  }

  getEmailErrorMessage() {
    return this.submitterEmail.hasError('email') ? 'Neispravna email adresa' :
            '';
  }

  getPhoneNumberErrorMessage(){
    return this.submitterPhoneNumber.hasError('pattern') ? 'Neispravan broj telefona': '';
  }

  onDescriptionFilechange(event: any) {
    this.descriptionFile = event.target.files[0]
  }
  deleteDescriptionFile() {
    (document.getElementById("formDescriptionFile") as HTMLInputElement).value = "";
    this.descriptionFile = undefined;
  }

  onExampleFilechange(event: any) {
    this.exampleFile = event.target.files[0]
  }
  deleteExampleFile() {
    (document.getElementById("formExampleFile") as HTMLInputElement).value = "";
    this.exampleFile = undefined;
  }

  openAuthorFormModal(){
    // const dialogConfig = new MatDialogConfig();
    // // The user can't close the dialog by clicking outside its body
    // dialogConfig.disableClose = true;
    // dialogConfig.id = "author-form-modal";
    // dialogConfig.height = "90%";
    // dialogConfig.width = "70%";
    // // https://material.angular.io/components/dialog/overview
    // const modalDialog = this.matDialog.open(AuthorFormModalComponent, dialogConfig);

    // modalDialog.afterClosed().subscribe(result =>{
    //   if(result instanceof Author){
    //     this.pieceAuthors.push(result);
    //   }
    // })
  }

  OpenOriginalAuthorFormModal(){
    // const dialogConfig = new MatDialogConfig();
    // // The user can't close the dialog by clicking outside its body
    // dialogConfig.disableClose = true;
    // dialogConfig.id = "author-form-modal";
    // dialogConfig.height = "90%";
    // dialogConfig.width = "70%";
    // // https://material.angular.io/components/dialog/overview
    // const modalDialog = this.matDialog.open(AuthorFormModalComponent, dialogConfig);

    // modalDialog.afterClosed().subscribe(result =>{
    //   if(result instanceof Author){
    //     this.originalPieceAuthors.push(result);
    //   }
    // })
  }


  DeleteAuthor(author:TAutor){
    // const index = this.pieceAuthors.indexOf(author, 0);
    // if (index > -1) {
    //   this.pieceAuthors.splice(index, 1);
    // }
  }

  DeleteOriginalAuthor(author:TAutor){
    // const index = this.originalPieceAuthors.indexOf(author, 0);
    // if (index > -1) {
    //   this.originalPieceAuthors.splice(index, 1);
    // }
  }

  public ResetAuthors(){
      this.pieceAuthors = [];
  }

  public ResetOriginalAuthors(){
    this.originalPieceAuthors = [];
  }

  public CancelRequest(){
    this.route.navigate(["/userProfile"]);
  }

}
