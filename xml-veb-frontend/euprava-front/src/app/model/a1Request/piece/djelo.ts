import { PodaciOriginalnoDjelo } from "./podaciOriginalnoDjelo";
import { VrstaDjela } from "./vrstaDjela";
import { FormaZapisa } from "./formaZapisa";
import { PodaciAutor } from "../author/podaciAutor";

export interface TDjelo{
  naslov : string;
  vrsta_djela: VrstaDjela;
  forma_zapisa : FormaZapisa;
  podaci_autor : PodaciAutor
  stvoreno_u_radnom_odnosu : boolean;
  nacin_koriscenja? : string;
  podaci_originalno_djelo? : PodaciOriginalnoDjelo;
}
