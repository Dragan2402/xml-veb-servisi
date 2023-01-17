import { Adresa } from "./adresa";
import { TDrzavljanstvo } from "./citizenship/tdrzavljanstvo";

export class TOsoba {
  ime: string;
  prezime: string;
  adresa: Adresa;
  drzavljanstvo: TDrzavljanstvo;

  constructor(ime:string, prezime:string, adresa:Adresa, drzavljanstvo:TDrzavljanstvo){
    this.ime = ime;
    this.prezime = prezime;
    this.adresa = adresa;
    this.drzavljanstvo = drzavljanstvo;
  }
}
