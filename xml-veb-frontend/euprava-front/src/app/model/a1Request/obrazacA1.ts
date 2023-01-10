import { TOsoba } from "./osoba";
import { TDjelo } from "./piece/djelo";
import { TPodnosilac } from "./submitter/podnosilac";

export interface ObrazacA1{

  id : number;
  podnosilac : TPodnosilac;
  punomocnik? : TOsoba;
  djelo : TDjelo;
  potpis: string;
  sifra_opisa? : string;
  sifra_primjera? : string;
  broj_prijave : number;
  datum_podnosenja : Date;

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

  // constructor();
  // constructor(data:any)
  // constructor(data? : any){

  //   if(data !== undefined){
  //     this.id = data.id;

  //     this.podnosilac = this.GetSubmitter(data.podnosilac);


  //     if(data.punomocnik === null){
  //       this.punomocnik = undefined;
  //     }else{
  //       this.punomocnik = this.GetPerson(data.punomocnik);
  //     }

  //     this.djelo = this.GetPiece(data.djelo);

  //     this.potpis = data.potpis;

  //     if(data.sifraOpisa === null){
  //       this.sifraOpisa = undefined;
  //     }else{
  //       this.sifraOpisa = data.sifraOpisa;
  //     }

  //     if(data.sifraPrimjera === null){
  //       this.sifraPrimjera = undefined;
  //     }else{
  //       this.sifraPrimjera = data.sifraPrimjera;
  //     }

  //     this.brojPrijave = data.brojPrijave;

  //     this.datumPodnosenja = new Date(data.datumPodnosenja);
  //   }else{
  //     this.id = 0;
  //     this.podnosilac = new LegalSubmitter('','','',new Adresa('','','',0));
  //     this.djelo = new Piece('',PieceType.INFORMACIONE_TEHNOLOGIJE,WriteForm.AUDIO,[],false,'',new OriginalPiece('',[]));
  //     this.potpis = '';
  //     this.datumPodnosenja= new Date();
  //     this.brojPrijave = 0;
  //   }
  // }



  // private GetSubmitter(podnosilac : any): Submitter{
  //   if(podnosilac.podaciOsoba === undefined){
  //     return new LegalSubmitter(podnosilac.email,podnosilac.telefon,podnosilac.poslovnoIme,this.GetAddress(podnosilac.adresa));
  //   }else{
  //     return new IndividualSubmitter(podnosilac.email,podnosilac.telefon, this.GetPerson(podnosilac.podaciOsoba));
  //   }
  // }

  // private GetPerson(podaciOsoba : any): TOsoba{
  //   return new TOsoba(podaciOsoba.ime,podaciOsoba.prezime,this.GetAddress(podaciOsoba.adresa),this.GetCitizenship(podaciOsoba.drzavljanstvo));
  // }

  // private GetAddress(a : any):Adresa{
  //   return new Adresa(a.mjesto,a.postanskiBroj,a.ulica,a.broj);
  // }

  // private GetPiece(d : any): Piece{
  //   return new Piece(d.naslov, this.GetType(d.vrstaDjela), this.GetForm(d.formaZapisa),this.GetAuthors(d.podaciAutor
  //     ),d.stvorenoURadnomOdnosu,d.nacinKoriscenja,this.GetOriginalPiece(d.podaciOriginalnoDjelo));
  // }

  // private GetAuthors(authors : any):Author[]{

  //   let authorsArray :Author[] = [];
  //   if(authors === null){
  //     return authorsArray;
  //   }
  //   if(authors.nepoznatiAutor !== null){
  //     authorsArray.push( new UnknownAuthor());
  //     return authorsArray;
  //   }else{
  //     for(let i = 0; i< authors.poznatiAutor.length; i++){
  //       let singleAuthor : any = authors.poznatiAutor[i];
  //       if(singleAuthor.podaciAutor===undefined){
  //         authorsArray.push(new DeadAuthor(singleAuthor.pseudonimZnakAutora,singleAuthor.ime,singleAuthor.prezime,singleAuthor.datumSmrti));
  //       }else{
  //         authorsArray.push(new AliveAuthor(singleAuthor.pseudonimZnakAutora,this.GetPerson(singleAuthor.podaciAutor)));
  //       }
  //     }
  //     return authorsArray;
  //   }
  // }

  // private GetOriginalAuthors(o : any):Author[]{
  //   let authorsArray :Author[] = [];
  //   if(o.nepoznatiAutor !== null){
  //     authorsArray.push( new UnknownAuthor());
  //     return authorsArray;
  //   }else{
  //     for(let i = 0; i< o.poznatiOriginalniAutor.length; i++){
  //       let singleAuthor : any = o.poznatiOriginalniAutor[i];
  //       if(singleAuthor.podaciAutor===undefined){
  //         authorsArray.push(new DeadAuthor(singleAuthor.pseudonimZnakAutora,singleAuthor.ime,singleAuthor.prezime,singleAuthor.datumSmrti));
  //       }else{
  //         authorsArray.push(new AliveAuthor(singleAuthor.pseudonimZnakAutora,this.GetPerson(singleAuthor.podaciAutor)));
  //       }
  //     }
  //     return authorsArray;
  //   }
  // }


  // private GetOriginalPiece(o : any):any{
  //   if(o !== null){
  //   return new OriginalPiece(o.naslovOriginalnogDjela,this.GetOriginalAuthors(o));
  //   }
  //   return undefined;
  // }

  // private GetType(type : string):PieceType{
  //   switch(type){
  //     case "PISANO":
  //       return PieceType.PISANO;
  //     case "SCENSKO":
  //       return PieceType.SCENSKO;
  //     case "LIKOVNO":
  //       return PieceType.LIKOVNO;
  //     case "PRIMJENJENO":
  //       return PieceType.PRIMJENJENO;
  //     case "PATENT":
  //       return PieceType.PATENT;
  //     case "INFORMACIONE_TEHNOLOGIJE":
  //       return PieceType.INFORMACIONE_TEHNOLOGIJE;
  //     case "NAUCNA_TEORIJA":
  //       return PieceType.NAUCNA_TEORIJA;
  //     case "NAUCNA_DJELATNOST":
  //       return PieceType.NAUCNA_DJELATNOST;
  //     default:
  //       return PieceType.PISANO;
  //   }
  // }

  // private GetForm(form : string):WriteForm{
  //   switch(form){
  //     case "PISANA":
  //       return WriteForm.PISANA;
  //     case "AUDIO":
  //       return WriteForm.AUDIO;
  //     case "VIZUELNA":
  //       return WriteForm.VIZUELNA;
  //     case "AUDIOVIZUELNA":
  //       return WriteForm.AUDIOVIZUELNA;
  //     case "OPTICKI_DISK":
  //       return WriteForm.OPTICKI_DISK;
  //     default:
  //       return WriteForm.PISANA;
  //   }
  // }

  // private GetCitizenship(c : any):Citizenship{
  //   if(c.jmbg === undefined){
  //     return new TStranoDrzavljanstvo(c.brojPasosa);
  //   }else{
  //     return new DomesticCitizenship(c.jmbg);
  //   }
  // }

  // public ToString():string{
  //   const attoreneyString = this.punomocnik===undefined? "Bez punomocnika":this.punomocnik.ToString();
  //   const a1String = "A1 Zahtjev\nID: " + this.id + "\nPodnosilac: " + this.podnosilac.ToString()+ "\nPunomocnik: " +attoreneyString+ "\nDjelo: "+this.djelo.ToString() +"\nPotpis: " +this.potpis
  //   + "\nSifra opisa: " +this.sifraOpisa+ "\nSifra primjera: " +this.sifraPrimjera+ "\nBroj prijave: " +this.brojPrijave.toString()+ "\nDatum prijave: " +this.datumPodnosenja.toString();
  //   return a1String;
  // }


  // public SetSubmitter(submitter : Submitter):void{
  //   this.podnosilac = submitter;
  // }

  // public SetAttorney(attorney: TOsoba):void {
  //   this.punomocnik = attorney;
  // }

  // public SetSignature(signature:string):void{
  //   this.potpis = signature;
  // }

  // public SetPiece(piece:Piece):void{
  //   this.djelo = piece;
  // }

  // public SetDescriptionId(id:string){
  //   this.sifraOpisa = id;
  // }

  // public SetExampleId(id:string){
  //   this.sifraPrimjera = id;
  // }
}
