import { TDrzavljanstvo } from "./tdrzavljanstvo";

export class TStranoDrzavljanstvo extends TDrzavljanstvo{
  broj_pasosa : string;

  constructor(brojPasosa :string){
    super();
    this.broj_pasosa = brojPasosa;
  }
}
