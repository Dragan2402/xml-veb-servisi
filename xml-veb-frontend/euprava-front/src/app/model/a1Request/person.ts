import { Address } from "./address";
import { Citizenship } from "./citizenship/citizenship";

export class Person{
  firstName : string ;
  lastName: string;
  address: Address;
  citizenship: Citizenship;

  constructor(firstName: string, lastName: string, address: Address, citizenship: Citizenship){
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.citizenship = citizenship;
  }

  public ToString(){
    return this.firstName + " " +this.lastName+ "\n\t\t" + this.address.ToString() + "\n\t\t" + this.citizenship.ToString();
  }
}
