export class Address{
    place : string;
    zipCode : string;
    street : string;
    number : number;

    constructor(place:string, zipCode:string, street: string, number:number){
      this.place = place;
      this.zipCode= zipCode;
      this.street = street;
      this.number = number;
    }

    public ToString(): string{
      return "Adresa:" +this.place+" "+ this.zipCode + " "+" " + this.street+ " "+ this.number.toString();
    }
}
