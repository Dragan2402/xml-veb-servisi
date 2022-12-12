import { Data } from "@angular/router";
import { AxiosResponse } from "axios";
import { Address } from "./address";
import { AliveAuthor } from "./author/aliveAuthor";
import { Author } from "./author/author";
import { DeadAuthor } from "./author/deadAuthor";
import { UnknownAuthor } from "./author/unknownAuthor";
import { Citizenship } from "./citizenship/citizenship";
import { DomesticCitizenship } from "./citizenship/domesticCitizenship";
import { ForeignCitizenship } from "./citizenship/foreignCitizenship";
import { Person } from "./person";
import { OriginalPiece } from "./piece/originalPiece";
import { Piece } from "./piece/piece";
import { PieceType } from "./piece/pieceType";
import { WriteForm } from "./piece/writeFrom";
import { IndividualSubmitter } from "./submitter/individualSubmitter";
import { LegalSubmitter } from "./submitter/legalSubmitter";
import { Submitter } from "./submitter/submitter";

export class A1Request{

  id : string;
  submitter : Submitter;
  attorney? : Person;
  piece : Piece;
  signature: string;
  descriptionId? : string;
  exampleId? : string;
  submissionNumber : number;
  submissionDate : Date;

  // constructor(id:string, submitter: Submitter, attorney: Person, piece:Piece, signature:string , descriptionId: string, exampleId: string, submissionNumber: number, submissionDate:Date){
  //   this.id = id;
  //   this.submitter= submitter;
  //   this.attorney = attorney;
  //   this.piece = piece;
  //   this.signature = signature;
  //   this.descriptionId = descriptionId;
  //   this.exampleId = exampleId;
  //   this.submissionNumber = submissionNumber;
  //   this.submissionDate = submissionDate;

  constructor();
  constructor(data:any)
  constructor(data? : any){

    if(data !== undefined){
      this.id = data.id;

      this.submitter = this.GetSubmitter(data.podnosilac);


      if(data.punomocnik === null){
        this.attorney = undefined;
      }else{
        this.attorney = this.GetPerson(data.punomocnik);
      }

      this.piece = this.GetPiece(data.djelo);

      this.signature = data.potpis;

      if(data.sifraOpisa === null){
        this.descriptionId = undefined;
      }else{
        this.descriptionId = data.sifraOpisa;
      }

      if(data.sifraPrimjera === null){
        this.exampleId = undefined;
      }else{
        this.exampleId = data.sifraPrimjera;
      }

      this.submissionNumber = data.brojPrijave;

      this.submissionDate = new Date(data.datumPodnosenja);
    }else{
      this.id = '0';
      this.submitter = new LegalSubmitter('','','',new Address('','','',0));
      this.piece = new Piece('',PieceType.INFORMACIONE_TEHNOLOGIJE,WriteForm.AUDIO,[],false,'',new OriginalPiece('',[]));
      this.signature = '';
      this.submissionDate= new Date();
      this.submissionNumber = 0;
    }
  }



  private GetSubmitter(podnosilac : any): Submitter{
    if(podnosilac.podaciOsoba === undefined){
      return new LegalSubmitter(podnosilac.email,podnosilac.telefon,podnosilac.poslovnoIme,this.GetAddress(podnosilac.adresa));
    }else{
      return new IndividualSubmitter(podnosilac.email,podnosilac.telefon, this.GetPerson(podnosilac.podaciOsoba));
    }
  }

  private GetPerson(podaciOsoba : any): Person{
    return new Person(podaciOsoba.ime,podaciOsoba.prezime,this.GetAddress(podaciOsoba.adresa),this.GetCitizenship(podaciOsoba.drzavljanstvo));
  }

  private GetAddress(a : any):Address{
    return new Address(a.mjesto,a.postanskiBroj,a.ulica,a.broj);
  }

  private GetPiece(d : any): Piece{
    return new Piece(d.naslov, this.GetType(d.vrstaDjela), this.GetForm(d.formaZapisa),this.GetAuthors(d.podaciAutor
      ),d.stvorenoURadnomOdnosu,d.nacinKoriscenja,this.GetOriginalPiece(d.podaciOriginalnoDjelo));
  }

  private GetAuthors(authors : any):Author[]{

    let authorsArray :Author[] = [];
    if(authors === null){
      return authorsArray;
    }
    if(authors.nepoznatiAutor !== null){
      authorsArray.push( new UnknownAuthor());
      return authorsArray;
    }else{
      for(let i = 0; i< authors.poznatiAutor.length; i++){
        let singleAuthor : any = authors.poznatiAutor[i];
        if(singleAuthor.podaciAutor===undefined){
          authorsArray.push(new DeadAuthor(singleAuthor.pseudonimZnakAutora,singleAuthor.ime,singleAuthor.prezime,singleAuthor.datumSmrti));
        }else{
          authorsArray.push(new AliveAuthor(singleAuthor.pseudonimZnakAutora,this.GetPerson(singleAuthor.podaciAutor)));
        }
      }
      return authorsArray;
    }
  }

  private GetOriginalAuthors(o : any):Author[]{
    let authorsArray :Author[] = [];
    if(o.nepoznatiAutor !== null){
      authorsArray.push( new UnknownAuthor());
      return authorsArray;
    }else{
      for(let i = 0; i< o.poznatiOriginalniAutor.length; i++){
        let singleAuthor : any = o.poznatiOriginalniAutor[i];
        if(singleAuthor.podaciAutor===undefined){
          authorsArray.push(new DeadAuthor(singleAuthor.pseudonimZnakAutora,singleAuthor.ime,singleAuthor.prezime,singleAuthor.datumSmrti));
        }else{
          authorsArray.push(new AliveAuthor(singleAuthor.pseudonimZnakAutora,this.GetPerson(singleAuthor.podaciAutor)));
        }
      }
      return authorsArray;
    }
  }


  private GetOriginalPiece(o : any):any{
    if(o !== null){
    return new OriginalPiece(o.naslovOriginalnogDjela,this.GetOriginalAuthors(o));
    }
    return undefined;
  }

  private GetType(type : string):PieceType{
    switch(type){
      case "PISANO":
        return PieceType.PISANO;
      case "SCENSKO":
        return PieceType.SCENSKO;
      case "LIKOVNO":
        return PieceType.LIKOVNO;
      case "PRIMJENJENO":
        return PieceType.PRIMJENJENO;
      case "PATENT":
        return PieceType.PATENT;
      case "INFORMACIONE_TEHNOLOGIJE":
        return PieceType.INFORMACIONE_TEHNOLOGIJE;
      case "NAUCNA_TEORIJA":
        return PieceType.NAUCNA_TEORIJA;
      case "NAUCNA_DJELATNOST":
        return PieceType.NAUCNA_DJELATNOST;
      default:
        return PieceType.PISANO;
    }
  }

  private GetForm(form : string):WriteForm{
    switch(form){
      case "PISANA":
        return WriteForm.PISANA;
      case "AUDIO":
        return WriteForm.AUDIO;
      case "VIZUELNA":
        return WriteForm.VIZUELNA;
      case "AUDIOVIZUELNA":
        return WriteForm.AUDIOVIZUELNA;
      case "OPTICKI_DISK":
        return WriteForm.OPTICKI_DISK;
      default:
        return WriteForm.PISANA;
    }
  }

  private GetCitizenship(c : any):Citizenship{
    if(c.jmbg === undefined){
      return new ForeignCitizenship(c.brojPasosa);
    }else{
      return new DomesticCitizenship(c.jmbg);
    }
  }

  public ToString():string{
    const attoreneyString = this.attorney===undefined? "Bez punomocnika":this.attorney.ToString();
    const a1String = "A1 Zahtjev\nID: " + this.id + "\nPodnosilac: " + this.submitter.ToString()+ "\nPunomocnik: " +attoreneyString+ "\nDjelo: "+this.piece.ToString() +"\nPotpis: " +this.signature
    + "\nSifra opisa: " +this.descriptionId+ "\nSifra primjera: " +this.exampleId+ "\nBroj prijave: " +this.submissionNumber.toString()+ "\nDatum prijave: " +this.submissionDate.toString();
    return a1String;
  }


  public SetSubmitter(submitter : Submitter):void{
    this.submitter = submitter;
  }

  public SetAttorney(attorney: Person):void {
    this.attorney = attorney;
  }

  public SetSignature(signature:string):void{
    this.signature = signature;
  }

  public SetPiece(piece:Piece):void{
    this.piece = piece;
  }

  public SetDescriptionId(id:string){
    this.descriptionId = id;
  }

  public SetExampleId(id:string){
    this.exampleId = id;
  }
}
