import { Author } from "../author/author";

export class OriginalPiece{
  title:string;
  authors : Author[];

  constructor(title:string, authors: Author[]){
    this.title = title;
    this.authors = authors;
  }

  public ToString() : string{
    let authorsString = "";
    this.authors.forEach(a => authorsString= authorsString+ a.ToString())
    return this.title + " Autori: " + authorsString;
  }
}
