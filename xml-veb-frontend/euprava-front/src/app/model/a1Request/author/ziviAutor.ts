import { TOsoba } from "../osoba";
import { TAutor } from "./autor";

export class TZiviAutor extends TAutor{

  podaci_autor: TOsoba;

  constructor(osoba:TOsoba, znak?:string){
    super(znak);
    this.podaci_autor = osoba;
  }

  TableString(): string {
    return this.podaci_autor.ime+" "+this.podaci_autor.prezime;
  }
}
