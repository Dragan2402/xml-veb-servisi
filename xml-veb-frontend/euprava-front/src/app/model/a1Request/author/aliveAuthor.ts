import { Person } from "../person";
import { Author } from "./author";

export class AliveAuthor extends Author{

  authorSign : string;

  author: Person;

  constructor(sign:string, author:Person){
    super();
    this.authorSign = sign;
    this.author = author;
  }


  public ToString(): string {

    let authorSingString = this.authorSign===null ? "": this.authorSign;
    return authorSingString+" "+ this.author.ToString();
  }

}
