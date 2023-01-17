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
import { TZiviAutor } from 'src/app/model/a1Request/author/ziviAutor';
import { TPreminuliAutor } from 'src/app/model/a1Request/author/preminuliAutor';
import { TDomaceDrzavljanstvo } from 'src/app/model/a1Request/citizenship/domaceDrzavljanstvo';




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

  pieceTypes= ["Pisano","Scensko","Likovno","Primjenjeno", "Patent","Informacione Tehnologije","Naucna Teorija","Naucna Djelatnost"];

  selectedPieceType : String = "Informacione Tehnologije";

  writeForms = ["Pisana", "Audio", "Vizuelna", "AudioVizuelna","Opticki Disk"];
  selectedWriteFrom : String = "Pisana";

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

    let request = '';

    request = request + this.GetHeader();
    const submitter = this.isLegalSubmitter? this.GetLegalSubmitter(): this.GetIndividualSubmitter();

    if(submitter === null){
      console.log("INVALID INPUT SUBMITTER");
      return;
    }
    // a1.SetSubmitter(submitter);
    request = request + submitter;


    const attorney = this.hasAttorney? this.GetAttorney() : undefined;
    if(attorney !== undefined){
      if(attorney === null){
        console.log("INVALID INPUT ATTORNEY");
        return;
      }else{
        request = request + attorney;
    }}

    const piece = this.GetPiece();

    if(piece === null){
      this.ResetAuthors();
      console.log("INVALID INPUT PIECE");
      return;
    }

    request = request + piece;

    if(!this.submitterSignature.valid){
      console.log("INVALID INPUT SIGNATURE");
      return;
    }

    request = request + "<Potpis>"+this.submitterSignature.value+"</Potpis>";


    this.userService.SubmitA1Request(request, this.descriptionFile, this.exampleFile);


  }

  private GetHeader():String{
    return '<?xml version="1.0" encoding="UTF-8" standalone="yes"?><obrazacA1 xmlns="http://euprava.euprava.com/model/a1Sertifikat" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">';
  }


  private GetLegalSubmitter():any{
    if(this.submitterLegalName.valid && this.submitterEmail.valid &&
      this.submitterPhoneNumber.valid && this.submitterPlace.valid &&
      this.submitterZipCode.valid && this.submitterStreet.valid &&
      this.submitterStreetNumber.valid){
        let legalSubmitter = '<Podnosilac xsi:type="TPravni_Podnosilac">';
        legalSubmitter = legalSubmitter + this.GetPhone();
        legalSubmitter = legalSubmitter + this.GetEmail();
        legalSubmitter = legalSubmitter + this.GetLegalName();
        legalSubmitter = legalSubmitter + this.GetAddress(1);
        legalSubmitter = legalSubmitter + "</Podnosilac>";
        return legalSubmitter;
      }
    return null;

  }

  private GetPhone():String{
    return "<Telefon>"+this.submitterPhoneNumber.value+"</Telefon>";
  }

  private GetEmail():String{
    return "<Email>"+this.submitterEmail.value+"</Email>";
  }

  private GetLegalName():String{
    return '<Poslovno_Ime>'+this.submitterLegalName.value+'</Poslovno_Ime>';
  }

  private GetAddress(type:number):String{
    let Place;
    let ZipCode;
    let Street;
    let Number;
    if(type===1){
      Place = this.submitterPlace.value;
      ZipCode = this.submitterZipCode.value;
      Street = this.submitterStreet.value;
      Number = this.submitterStreetNumber.value;
    }
    else if(type===2){
      Place = this.attorneyPlace.value;
      ZipCode = this.attorneyZipCode.value;
      Street = this.attorneyStreet.value;
      Number = this.attorneyStreetNumber.value;
    }

    let address = '<Adresa>';
    address = address + "<Mjesto>"+Place+"</Mjesto>";
    address = address + "<Postanski_Broj>"+ZipCode+"</Postanski_Broj>";
    address = address + "<Ulica>"+Street+"</Ulica>";
    address = address + "<Broj>"+Number+"</Broj>";
    address = address + "</Adresa>";
    return address;
  }

  private GetIndividualSubmitter():any{
    if(this.submitterIndividualFirstName.valid && this.submitterIndividualLastName.valid && this.submitterEmail.valid &&
      this.submitterPhoneNumber.valid && this.submitterPlace.valid &&
      this.submitterZipCode.valid && this.submitterStreet.valid &&
      this.submitterStreetNumber.valid && ((this.submitterPassport.valid && this.isForeignCitizenship) || (this.submitterJmbg.valid && !this.isForeignCitizenship))){
        let individualSubmitter = '<Podnosilac xsi:type="TFizicki_Podnosilac">';
        individualSubmitter = individualSubmitter + this.GetPhone();
        individualSubmitter = individualSubmitter + this.GetEmail();
        individualSubmitter = individualSubmitter + this.GetPerson(1);
        individualSubmitter = individualSubmitter + "</Podnosilac>";
        return individualSubmitter;

      }
    return null;

  }

  private GetPerson(type : number):String{
    let firstName;
    let lastName;
    let tag;
    if(type ===1){
      firstName = this.submitterIndividualFirstName.value;
      lastName = this.submitterIndividualLastName.value;
      tag = "Podaci_Osoba";
    }
    else if(type === 2){
      firstName = this.attorneyFirstName.value;
      lastName = this.attorneyLastName.value;
      tag = "Punomocnik";
    }
    let person = "<"+tag+">";
    person = person + "<Ime>"+firstName+"</Ime>";
    person = person + "<Prezime>"+lastName+"</Prezime>";
    person = person + this.GetAddress(type);
    person = person + this.GetCitizenship(type);
    person = person + "</"+tag+">";
    return person;
  }

  private GetCitizenship(type:number):String{
    let citizenship;
    let isForeign = type===1 ? this.isForeignCitizenship : this.attorneyIsForeignCitizenship;
    if(isForeign){
      citizenship = citizenship + '<Drzavljanstvo xsi:type="TStrano_Drzavljanstvo">';
      citizenship = citizenship + this.GetPassport(type);
      citizenship = citizenship + "</Drzavljanstvo>";
      return citizenship;
    }else{
      citizenship = citizenship + '<Drzavljanstvo xsi:type="TDomace_Drzavljanstvo">';
      citizenship = citizenship + this.GetJmbg(type);
      citizenship = citizenship + "</Drzavljanstvo>";
      return citizenship;
    }
  }

  private GetJmbg(type:number):String{
    if(type === 1){
      return "<Jmbg>"+this.submitterJmbg.value+"</Jmbg>";
    }
    else{
      return "<Jmbg>"+this.attorneyJmbg.value+"</Jmbg>";
    }
  }

  private GetPassport(type:number):String{
    if(type === 1){
      return "<Broj_Pasosa>"+this.submitterPassport.value+"</Broj_Pasosa>";
    }
    else{
      return "<Broj_Pasosa>"+this.attorneyPassport.value+"</Broj_Pasosa>";
    }
  }

  private GetAttorney():any{
    if(this.attorneyFirstName.valid && this.attorneyLastName.valid && this.attorneyPlace.valid &&
       this.attorneyZipCode.valid && this.attorneyStreet.valid && this.attorneyStreetNumber.valid &&
       ((this.attorneyPassport.valid && this.attorneyIsForeignCitizenship) || (this.attorneyJmbg.valid && !this.attorneyIsForeignCitizenship))){
        return this.GetPerson(2);
    }else{
      return null;
    }
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
    if(this.pieceTittle.valid && this.IsAuthorsValid() && this.IsOriginalPieceValid()){
      if(this.authorType===0){
        this.pieceAuthors =[];
      }else if(this.authorType ===1){
        this.pieceAuthors =[];
      }
      let piece = "<Djelo>";
      piece = piece + "<Naslov>"+this.pieceTittle.value+"</Naslov>";
      piece = piece + "<Vrsta_Djela>"+this.selectedPieceType+"</Vrsta_Djela>";
      piece = piece + "<Forma_Zapisa>" + this.selectedWriteFrom + "</Forma_Zapisa>";
      piece = piece + this.GetAuthors();
      piece = piece + this.GetInWorkRelationship();
      piece = piece + this.GetWayOfUse();
      if(!this.isPieceOriginal && this.IsOriginalPieceValid()){
        piece = piece + this.GetOriginalPiece();
      }
      piece = piece +"</Djelo>";
      return piece;
    }else{
      return null;
    }
  }

  private GetInWorkRelationship():String{
    if(this.isInWorkRelationship){
      return "<Stvoreno_U_Radnom_Odnosu>true</Stvoreno_U_Radnom_Odnosu>";
    }
    return "<Stvoreno_U_Radnom_Odnosu>false</Stvoreno_U_Radnom_Odnosu>";
  }

  private GetWayOfUse():String{
    let typeOfUse = <String>this.pieceTypeOfUse.value;
    if(typeOfUse.length > 1){
      return "<Nacin_koriscenja>"+typeOfUse+"</Nacin_koriscenja>"
    }
    return "";
  }

  private GetAuthors():String{
    if(this.authorType === 0){
      this.pieceAuthors = [];
      return "";
    }else if(this.authorType === 1){
      this.pieceAuthors = [];
      return "<Podaci_Autor><Nepoznati_Autor>true</Nepoznati_Autor></Podaci_Autor>";
    }else{
      let authors = "<Podaci_Autor>";
      this.pieceAuthors.forEach(author => {
        if(author instanceof TZiviAutor){
          authors = authors + this.GetAliveAuthor(author);
        }else if (author instanceof TPreminuliAutor){
          authors = authors + this.GetDeadAuthor(author);
        }
      });
      authors = authors + "</Podaci_Autor>";
      return authors;
    }
  }

  private GetAliveAuthor(author:TZiviAutor):string{
    let element = '<Poznati_Autor xsi:type="TZivi_Autor">';
    if(author.pseudonim_znak_autora !== undefined){
      element = element + "<Pseudonim_Znak_Autora>"+author.pseudonim_znak_autora+"</Pseudonim_Znak_Autora>";
    }
    element = element + "<Podaci_Autor>";
    element = element + "<Ime>"+author.podaci_autor.ime+"</Ime>";
    element = element + "<Prezime>"+author.podaci_autor.prezime+"</Prezime>";
    element = element + this.GetAddressAuthor(author.podaci_autor.adresa);
    element = element + this.GetCitizenshipAuthor(author.podaci_autor.drzavljanstvo);
    element = element + "</Podaci_Autor></Poznati_Autor>";
    return element;
  }

  private GetAliveAuthorOriginalPiece(author:TZiviAutor):string{
    let element = '<Poznati_Originalni_Autor xsi:type="TZivi_Autor"';
    if(author.pseudonim_znak_autora !== undefined){
      element = element + "<Pseudonim_Znak_Autora>"+author.pseudonim_znak_autora+"</Pseudonim_Znak_Autora>";
    }
    element = element + "<Podaci_Autor>";
    element = element + "<Ime>"+author.podaci_autor.ime+"</Ime>";
    element = element + "<Prezime>"+author.podaci_autor.prezime+"</Prezime>";
    element = element + this.GetAddressAuthor(author.podaci_autor.adresa);
    element = element + this.GetCitizenshipAuthor(author.podaci_autor.drzavljanstvo);
    element = element + "</Podaci_Autor></Poznati_Autor>";
    return element;
  }

  private GetDeadAuthor(author:TPreminuliAutor):string{
    let element = '<Poznati_Autor xsi:type="TPreminuli_Autor">';
    if(author.pseudonim_znak_autora !== undefined){
      element = element + "<Pseudonim_Znak_Autora>"+author.pseudonim_znak_autora+"</Pseudonim_Znak_Autora>";
    }
    element = element + "<Ime>"+author.ime+"</Ime>";
    element = element + "<Prezime>"+author.prezime+"</Prezime>";

    var isoString = author.datum_smrti.toISOString();
    var dateInFormat = isoString.split("T")[0];
    console.log(dateInFormat);
    element = element + '<Datum_Smrti>'+dateInFormat+'</Datum_Smrti>';
    element = element + "</Poznati_Autor>";
    return element;
  }

  private GetDeadAuthorOriginalPiece(author:TPreminuliAutor):string{
    let element = '<Poznati_Originalni_Autor xsi:type="TPreminuli_Autor">';
    if(author.pseudonim_znak_autora !== undefined){
      element = element + "<Pseudonim_Znak_Autora>"+author.pseudonim_znak_autora+"</Pseudonim_Znak_Autora>";
    }
    element = element + "<Ime>"+author.ime+"</Ime>";
    element = element + "<Prezime>"+author.prezime+"</Prezime>";

    var isoString = author.datum_smrti.toISOString();
    var dateInFormat = isoString.split("T")[0];
    console.log(dateInFormat);
    element = element + '<Datum_Smrti>'+dateInFormat+'</Datum_Smrti>';
    element = element + "</Poznati_Originalni_Autor>";
    return element;
  }

  private GetAddressAuthor(address:Adresa):string{
    let adresa= "<Adresa>";
    adresa = adresa + "<Mjesto>"+address.mjesto+"</Mjesto>";
    adresa = adresa + "<Postanski_Broj>"+address.postanski_broj+"</Postanski_Broj>";
    adresa = adresa + "<Ulica>"+address.ulica+"</Ulica>";
    adresa = adresa + "<Broj>"+address.broj+"</Broj>";
    adresa = adresa + "</Adresa>";
    return adresa;
  }

  private GetCitizenshipAuthor(citizenship:TDrzavljanstvo):string{
    if(citizenship instanceof TDomaceDrzavljanstvo){
      return '<Drzavljanstvo xsi:type="TDomace_Drzavljanstvo"><Jmbg>'+citizenship.jmbg+'</Jmbg></Drzavljanstvo>'
    }else if(citizenship instanceof TStranoDrzavljanstvo){
      return '<Drzavljanstvo xsi:type="TStrano_Drzavljanstvo"><Broj_Pasosa>'+citizenship.broj_pasosa+'</Broj_Pasosa></Drzavljanstvo>';
    }
    return "";
  }

  private GetOriginalPiece():String{
    let originalPiece = "<Podaci_Originalno_Djelo>";
    originalPiece = originalPiece + "<Naslov_Originalnog_Djela>"+this.originalPieceTitle.value+"</Naslov_Originalnog_Djela>";
    if(this.originalAuthorType === 0){
      this.originalPieceAuthors = [];
      originalPiece = originalPiece + "<Nepoznati_Autor>true</Nepoznati_Autor>";
    }else{
      let authorsElement = ''
      this.originalPieceAuthors.forEach(author => {
        if(author instanceof TZiviAutor){
          authorsElement = authorsElement + this.GetAliveAuthorOriginalPiece(author);
        }else if(author instanceof TPreminuliAutor){
          authorsElement = authorsElement + this.GetDeadAuthorOriginalPiece(author);
        }
      });
      originalPiece = originalPiece + authorsElement;
    }
    originalPiece = originalPiece + "</Podaci_Originalno_Djelo>";
    return originalPiece;
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
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = true;
    dialogConfig.id = "author-form-modal";
    dialogConfig.height = "90%";
    dialogConfig.width = "70%";
    // https://material.angular.io/components/dialog/overview
    const modalDialog = this.matDialog.open(AuthorFormModalComponent, dialogConfig);

    modalDialog.afterClosed().subscribe(result =>{
      if(result instanceof TAutor){
        this.pieceAuthors.push(result);
        console.log(result);
      }
    })
  }

  OpenOriginalAuthorFormModal(){
    const dialogConfig = new MatDialogConfig();
    // The user can't close the dialog by clicking outside its body
    dialogConfig.disableClose = true;
    dialogConfig.id = "author-form-modal";
    dialogConfig.height = "90%";
    dialogConfig.width = "70%";
    // https://material.angular.io/components/dialog/overview
    const modalDialog = this.matDialog.open(AuthorFormModalComponent, dialogConfig);

    modalDialog.afterClosed().subscribe(result =>{
      if(result instanceof TAutor){
        this.originalPieceAuthors.push(result);
      }
    })
  }


  DeleteAuthor(author:TAutor){
    const index = this.pieceAuthors.indexOf(author, 0);
    if (index > -1) {
      this.pieceAuthors.splice(index, 1);
    }
  }

  DeleteOriginalAuthor(author:TAutor){
    const index = this.originalPieceAuthors.indexOf(author, 0);
    if (index > -1) {
      this.originalPieceAuthors.splice(index, 1);
    }
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
