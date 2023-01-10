import { Adresa } from "./adresa";
import { TDrzavljanstvo } from "./citizenship/tdrzavljanstvo";

export interface TOsoba {
  ime: string;
  prezime: string;
  adresa: Adresa;
  drzavljanstvo: TDrzavljanstvo;
}
