export abstract class TAutor{
  pseudonim_znak_autora? : string;

  constructor(znak?:string){
    this.pseudonim_znak_autora =znak
  }

  abstract TableString():string;
}
