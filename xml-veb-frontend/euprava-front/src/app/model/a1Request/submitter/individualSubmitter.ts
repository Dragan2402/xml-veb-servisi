import { Person } from "../person";
import { Submitter } from "./submitter";

export class IndividualSubmitter extends Submitter{

  person : Person;

  constructor(email:string, phone:string, person:Person){
    super(email, phone);
    this.person = person;
  }

  public ToString(): string {
    return "\n\tFizicki podnosilac: "  + "\n\t\temail: "+this.email+"\n\t\ttelefon: "+this.phoneNumber + "\n\t\t" + this.person.ToString();
  }

}
