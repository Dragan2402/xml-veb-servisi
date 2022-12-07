export abstract class Submitter{

  phoneNumber : string;
  email : string;

  constructor(email:string, phone:string){
    this.phoneNumber = phone;
    this.email = email;
  }

  public abstract ToString():string;

}
