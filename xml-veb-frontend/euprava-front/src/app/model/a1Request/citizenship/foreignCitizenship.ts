import { Citizenship } from "./citizenship";

export class ForeignCitizenship  extends Citizenship{

  passport! : string;

  constructor(passport: string){
    super();
    this.passport = passport;
  }

  public ToString(): string {
    return "Broj pasosa: "+this.passport;
  }

}
