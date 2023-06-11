xquery version "3.1";
for $document in collection('/db/a1')
    where fn:contains($document//*:Ime/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Prezime/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Poslovno_Ime/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Email/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Telefon/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Mjest/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Postanski_Broj/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Ulica/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Broj/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Jmbg/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Broj_Pasosa/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Naslov/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Vrsta_Djela/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Forma_Zapisa/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Pseudonim_Znak_Autora/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Nepoznati_Autor/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Stvoreno_U_Radnom_Odnosu/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Nacin_koriscenja/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Naslov_Originalnog_Djela/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Datum_Smrti/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Potpis/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Sifra_Primjera/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Sifra_Opisa/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Broj_Prijave/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Datum_Podnosenja/fn:lower-case(text()),fn:lower-case("%1$s")) or
                    fn:contains($document//*:Status/fn:lower-case(text()),fn:lower-case("%1$s"))
    return $document