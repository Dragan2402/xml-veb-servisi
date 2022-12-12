import { Person } from "../person";
import { Author } from "./author";

export class AliveAuthor extends Author{

  authorSign? : string;

  author: Person;

  constructor(sign:any, author:Person){
    super();
    this.authorSign = sign;
    this.author = author;
  }


  public ToString(): string {

    let authorSingString = this.authorSign===null ? "": this.authorSign;
    return authorSingString+" "+ this.author.ToString();
  }

  public ToTableData():string{
    return this.author.firstName + " "+this.author.lastName;
  };

}
