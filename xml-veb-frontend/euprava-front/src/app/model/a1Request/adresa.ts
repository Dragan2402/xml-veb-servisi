export class Adresa{
    mjesto : string;
    postanski_broj : string;
    ulica : string;
    broj : number;

    constructor(place:string, zipCode:string, street: string, number:number){
      this.mjesto = place;
      this.postanski_broj= zipCode;
      this.ulica = street;
      this.broj = number;
    }

    public ToString(): string{
      return "Adresa:" +this.mjesto+" "+ this.postanski_broj + " "+" " + this.ulica+ " "+ this.broj.toString();
    }
}
