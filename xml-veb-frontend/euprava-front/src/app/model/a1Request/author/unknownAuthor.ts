import { Author } from "./author";

export class UnknownAuthor extends Author{

  constructor(){
    super();
  }

  public ToString(): string {
    return "Nepoznati autor";
  }

  public ToTableData():string{
    return "";
  };
}
