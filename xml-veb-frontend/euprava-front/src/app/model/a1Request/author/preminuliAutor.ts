import { TAutor } from "./autor";

export class TPreminuliAutor extends TAutor{


  ime : string;
  prezime:string;
  datum_smrti: Date;

  constructor(ime:string, prezime:string, datum:Date, znak?:string){
    super(znak);
    this.ime =ime;
    this.prezime = prezime;
    this.datum_smrti = datum;
  }

  TableString(): string {
    return this.ime+" "+this.prezime;
  }
}
