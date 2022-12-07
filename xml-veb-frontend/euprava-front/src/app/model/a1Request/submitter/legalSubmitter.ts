import { Address } from "../address";
import { Submitter } from "./submitter";

export class LegalSubmitter extends Submitter{
  legalName : string;
  address : Address;

  constructor(email:string, phone:string, legalName:string, address: Address){
    super(email, phone);
    this.legalName = legalName;
    this.address = address;
  }

  public ToString(): string {
    return "\nPravni podnosilac: "  + this.email+" "+this.phoneNumber + " " + this.legalName + " " + this.address.ToString();
  }
}
