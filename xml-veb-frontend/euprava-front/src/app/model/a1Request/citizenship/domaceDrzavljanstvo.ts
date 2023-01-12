import { TDrzavljanstvo } from "./tdrzavljanstvo";

export class TDomaceDrzavljanstvo extends TDrzavljanstvo{

  jmbg : string;

  constructor(jmbg:string){
    super();
    this.jmbg= jmbg;
  }
}
