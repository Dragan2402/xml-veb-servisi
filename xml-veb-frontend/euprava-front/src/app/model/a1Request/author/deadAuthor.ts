import { Author } from "./author";

export class DeadAuthor extends Author{

  authorSign : string;
  firstName : string;
  lastName:string;
  dod: Date;

  constructor(sign:string, firstName:string,lastName:string,dod:Date){

    super();
    this.authorSign = sign;
    this.firstName = firstName;
    this.lastName= lastName;
    this.dod=dod;
  }

  public ToString(): string {
    let authorSingString = this.authorSign===null ? "": this.authorSign;
    return authorSingString+" "+this.firstName+" "+this.lastName + " "+this.dod.toString();
  }

}
