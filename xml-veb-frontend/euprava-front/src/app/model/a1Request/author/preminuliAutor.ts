import { TAutor } from "./autor";

export interface TPreminuliAutor extends TAutor{

  ime : string;
  prezime:string;
  datum_smrti: Date;

}
