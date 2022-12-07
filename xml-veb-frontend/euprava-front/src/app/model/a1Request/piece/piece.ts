import { Author } from "../author/author";
import { OriginalPiece } from "./originalPiece";
import { PieceType } from "./pieceType";
import { WriteForm } from "./writeFrom";

export class Piece{
  title : string;
  type: PieceType;
  writeFrom : WriteForm;
  authors : Author[];
  inWorkRelationship : boolean;
  typeOfUse : string;
  originalPiece : OriginalPiece;

  constructor(title:string, type:PieceType, form :WriteForm, authors: Author[], inWorkRelationship:boolean, typeOfUse:string, orignalPiece:OriginalPiece){
    this.title = title;
    this.type = type;
    this.writeFrom = form;
    this.authors = authors;
    this.inWorkRelationship = inWorkRelationship;
    this.typeOfUse = typeOfUse;
    this.originalPiece = orignalPiece;
  }

  public ToString():string{
    let authorsString = "";
    this.authors.forEach(a => authorsString = authorsString +"\n\t\t\t"+ a.ToString());
    return "\n\t\tNaslov: " + this.title + "\n\t\tVrsta: "+this.type.toString() + "\n\t\tForma: " + this.writeFrom.toString() + "\n\t\tAutori: " +
    authorsString+ "\n\t\tKreirano u radnom odnosu: " + this.inWorkRelationship + "\n\t\tNacin koriscenja: " + this.typeOfUse + "\n\t\tOriginalno djelo:" + this.originalPiece.ToString();
  }
}
