xquery version "3.1";
for $document in collection('/db/a1')
    where fn:contains($document//*:Id_Klijenta/text(),"%2$s") and (fn:contains($document//*:Ime/text(),"%1$s") or
          fn:contains($document//*:Prezime/text(),"%1$s") or
          fn:contains($document//*:Poslovno_Ime/text(),"%1$s") or
          fn:contains($document//*:Email/text(),"%1$s") or
          fn:contains($document//*:Telefon/text(),"%1$s") or
          fn:contains($document//*:Mjest/text(),"%1$s") or
          fn:contains($document//*:Postanski_Broj/text(),"%1$s") or
          fn:contains($document//*:Ulica/text(),"%1$s") or
          fn:contains($document//*:Broj/text(),"%1$s") or
          fn:contains($document//*:Jmbg/text(),"%1$s") or
          fn:contains($document//*:Broj_Pasosa/text(),"%1$s") or
          fn:contains($document//*:Naslov/text(),"%1$s") or
          fn:contains($document//*:Vrsta_Djela/text(),"%1$s") or
          fn:contains($document//*:Forma_Zapisa/text(),"%1$s") or
          fn:contains($document//*:Pseudonim_Znak_Autora/text(),"%1$s") or
          fn:contains($document//*:Nepoznati_Autor/text(),"%1$s") or
          fn:contains($document//*:Stvoreno_U_Radnom_Odnosu/text(),"%1$s") or
          fn:contains($document//*:Nacin_koriscenja/text(),"%1$s") or
          fn:contains($document//*:Naslov_Originalnog_Djela/text(),"%1$s") or
          fn:contains($document//*:Datum_Smrti/text(),"%1$s") or
          fn:contains($document//*:Potpis/text(),"%1$s") or
          fn:contains($document//*:Sifra_Primjera/text(),"%1$s") or
          fn:contains($document//*:Sifra_Opisa/text(),"%1$s") or
          fn:contains($document//*:Broj_Prijave/text(),"%1$s") or
          fn:contains($document//*:Datum_Podnosenja/text(),"%1$s") or
          fn:contains($document//*:Status/text(),"%1$s"))
    return $document