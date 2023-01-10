import { Adresa } from "../adresa";
import { TPodnosilac } from "./podnosilac";

export interface TPravniPodnosilac extends TPodnosilac{
  poslovno_ime : string;
  adresa : Adresa;

}
