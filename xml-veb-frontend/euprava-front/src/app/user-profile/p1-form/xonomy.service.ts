import { Injectable } from '@angular/core';

declare const Xonomy: any;

@Injectable({
  providedIn: 'root'
})
export class XonomyService {

  constructor() { }

  public p1Specification = {
    elements: {
      Obrazac_P1: {
        hasText: false
      },

      Popunjava_zavod: {
        hasText: false
      },
      Broj_prijave: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Datum_prijema: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Priznati_datum_podnosenja: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Zahtev_za_priznanje_patenta: {
        hasText: false
      },

      Naziv_pronalaska: {
        hasText: false
      },
      Na_srpskom: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Na_engleskom: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Podnosilac_prijave: {
        hasText: false
      },
      Podnosilac_je_pronalazac: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Lice_podnosilac: {
        hasText: false
      },

      Adresa: {
        hasText: false
      },
      Ulica: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Broj: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Postanski_broj: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Mesto: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Drzava: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Kontakt_informacije: {
        hasText: false
      },
      Broj_telefona: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      E_posta: {
        hasText: false
      },
      Broj_faksa: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Poslovno_ime: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Pronalazac: {
        hasText: false
      },
      Pronalazac_nije_naveden: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Lice_pronalazac: {
        hasText: false
      },
      Ime: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Prezime: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Drzavljanstvo: {
        hasText: false
      },

      Tip: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      }, // should be changed

      Punomocnik_ili_predstavnik: {
        hasText: false
      },
      Tip_punomocnika_ili_predstavnika: {
        hasText: false
      },
      Lice_punomocnik_ili_predstavnik: {
        hasText: false
      },

      Adresa_za_dostavljanje: {
        hasText: false
      },

      Nacin_dostavljanja: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Povezana_prijava: {
        hasText: false
      },
      Tip_povezane_prijave: {
        hasText: false
      },
      Broj_ranije_prijave: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },
      Datum_podnosenja_ranije_prijave: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      },

      Zahtev_za_priznanje_prava_prvenstva: {
        hasText: false
      },
      Ranija_prijava: {
        hasText: false
      },
      Oznaka_drzave_ili_organizacije: {
        hasText: true,
        oneliner: true,
        asker: Xonomy.askString
      }

      // Fakultet: {
      //   menu: [
      //     {
      //       caption: "Add <Naziv>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<Naziv></Naziv>",
      //       hideIf: function(jsElement: any) {
      //         jsElement
      //         return jsElement.hasChildElement("Naziv")
      //       }
      //     },
      //     {
      //       caption: "Add <GodinaOsnivanja>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<GodinaOsnivanja></GodinaOsnivanja>",
      //       hideIf: function(jsElement: any) {
      //         return jsElement.hasChildElement("GodinaOsnivanja")
      //       }
      //     },
      //     {
      //       caption: "Add <Profesori>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<Profesori></Profesori>",
      //       hideIf: function(jsElement: any) {
      //         return jsElement.hasChildElement("Profesori")
      //       }
      //     },
      //     {
      //       caption: "Add @id",
      //       action: Xonomy.newAttribute,
      //       actionParameter: {
      //         name: "Id",
      //         value: "1"
      //       },
      //       hideIf: function (jsElement: any) {
      //         return jsElement.hasAttribute("Id");
      //       }
      //     }
      //   ],
      //   attributes: {
      //     Id: {
      //       asker: Xonomy.askString,
      //       menu: [
      //         {
      //           caption: "Obrisi me",
      //           action: Xonomy.deleteAttribute
      //         }
      //       ]
      //     }
      //   }
      // },
      // Naziv: {
      //   hasText: true,
      //   oneliner: true,
      //   asker: Xonomy.askString,
      //   // asker: Xonomy.askPicklist,
      //   // askerParameter: ["Ime1", "Ime2"]
      //   mustBeBefore: ["GodinaOsnivanja", "Profesori"],
      //   menu: [
      //     {
      //       caption: "Obrisi me",
      //       action: Xonomy.deleteElement
      //     }
      //   ]
      // },
      // GodinaOsnivanja: {
      //   hasText: true,
      //   oneliner: true,
      //   asker: Xonomy.askString,
      //   mustBeBefore: ["Profesori"]
      // },
      // Profesori: {
      //   menu: [
      //     {
      //       caption: "Add <Profesor>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<Profesor></Profesor>"
      //     }
      //   ]
      // },
      // Profesor: {
      //   menu: [
      //     {
      //       caption: "Add <Ime>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<Ime></Ime>",
      //       hideIf: function(jsElement: any) {
      //         return jsElement.hasChildElement("Ime")
      //       }
      //     },
      //     {
      //       caption: "Add <Prezime>",
      //       action: Xonomy.newElementChild,
      //       actionParameter: "<Prezime></Prezime>",
      //       hideIf: function(jsElement: any) {
      //         return jsElement.hasChildElement("Prezime")
      //       }
      //     }
      //   ]
      // },
      // Ime: {
      //   mustBeBefore: ["Prezime"],
      //   hasText: true,
      //   oneliner: true,
      //   asker: Xonomy.askString
      // },
      // Prezime: {
      //   hasText: true,
      //   oneliner: true,
      //   asker: Xonomy.askString
      // }
    }
  }
}
