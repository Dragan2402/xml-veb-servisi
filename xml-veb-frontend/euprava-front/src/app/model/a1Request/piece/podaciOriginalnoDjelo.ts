import { TAutor } from "../author/autor";

export interface PodaciOriginalnoDjelo{
  naslov_originalnog_djela:string;
  poznati_originalni_autor : TAutor[];
  nepoznati_autor:boolean;

}
