import { Citizenship } from "./citizenship";

export class DomesticCitizenship extends Citizenship{

  jmbg : string;

  constructor(jmbg:string){
    super();
    this.jmbg = jmbg;
  }

  public ToString(): string {
    return "Jmbg: " + this.jmbg;
  }
}
