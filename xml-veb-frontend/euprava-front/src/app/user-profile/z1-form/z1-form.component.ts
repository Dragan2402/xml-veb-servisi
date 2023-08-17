import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {UserService} from "../user.service";

const EMPTY_PERSON = {
  Ime: "",
  Prezime: "",
  Poslovno_ime: "",
  Adresa: {
    Ulica: "",
    Broj: "",
    Postanski_broj: "",
    Mesto: "",
    Drzava: ""
  },
  Kontakt: {
    Broj_telefona: "",
    Broj_faksa: "",
    E_posta: ""
  }
};


@Component({
  selector: 'euprava-z1-form',
  templateUrl: './z1-form.component.html',
  styleUrls: ['./z1-form.component.css']
})
export class Z1FormComponent implements OnInit {
  Zahtev_za_priznanje_ziga: FormGroup;
  checkboxValues: any;

  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.checkboxValues = Array.from(Array(46), (_, i) => i.toString());
    this.Zahtev_za_priznanje_ziga = this.formBuilder.group({

      Vise_podnosilaca: false,

      Podnosilac: this.formBuilder.group({
        Ime: ['Djuro', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['Pucar', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['Stari', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]*$/) ] ],
        Adresa: this.formBuilder.group({
          Ulica: ['Partizanskih baza', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Broj: ['88', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['14880', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['Subotica', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Drzava: ['Srbija', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        }),
        Kontakt: this.formBuilder.group({
          Broj_telefona: ['065848984', [ Validators.required ] ],
          Broj_faksa: ['551225654', [ Validators.required ] ],
          E_posta: ['djuropucar@maildrop.cc', [ Validators.required, Validators.email ] ]
        })
      }),

      Zajednicki_predstavnik: this.formBuilder.group({
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]*$/) ] ],
        Adresa: this.formBuilder.group({
          Ulica: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Broj: ['', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Drzava: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]
        }),
        Kontakt: this.formBuilder.group({
          Broj_telefona: ['', [ Validators.required ] ],
          Broj_faksa: ['', [ Validators.required ] ],
          E_posta: ['', [ Validators.required, Validators.email ] ]
        })
      }),

      Punomocnik: this.formBuilder.group({
        Ime: ['Miro', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['Semberac', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['Lepi Momcilo', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]*$/) ] ],
        Adresa: this.formBuilder.group({
          Ulica: ['Semberske brigade', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Broj: ['92', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['19926', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['Bijeljina', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Drzava: ['Bosna', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        }),
        Kontakt: this.formBuilder.group({
          Broj_telefona: ['065111589', [ Validators.required ] ],
          Broj_faksa: ['484899849', [ Validators.required ] ],
          E_posta: ['miro@maildrop.cc', [ Validators.required, Validators.email ] ]
        })
      }),

      Tip_ziga: 'INDIVIDUALNI',
      Tip_znaka: 'VERBALNI',

      Naznacenje_boje: ['Crvena i zuta', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
      Opis_znaka: ['Znak je poprilicno ruzan', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
      Brojevi_klasa_robe: this.formBuilder.group({
        Broj_klase_robe: [[]]
      }),
      Pravo_prvenstva: ['Zatrazeno prvenstvo', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
      Placene_takse: this.formBuilder.group({
        Osnovna_taksa: ['55', [Validators.required, Validators.pattern(/^[1-9][0-9]*$/)]],
        Graficko_resenje: ['45', [Validators.required, Validators.pattern(/^[1-9][0-9]*$/)]],
        Ukupno: ['100', [Validators.required, Validators.pattern(/^[1-9][0-9]*$/)]]
      }),

      Prilozi: this.formBuilder.group({
        Primerak_znaka: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Spisak_robe_i_usluga: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Punomocje: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Generalno_punomocje: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Punomocje_naknadno_dostavljeno: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Opsti_akt_o_kolektivnom_zigu: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Dokaz_o_pravu_prvenstva: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Dokaz_o_uplati_takse: ['', [ Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]
      })

    });

    this.updatePodnosilacValidators();
  }

  ngOnInit() {
  }

  rangeValidator(control: AbstractControl): ValidationErrors | null {
    const value = Number(control.value);

    if (value >= 11000 && value <= 40000) {
      return null;
    } else {
      return { range: true };
    }
  }

  updatePodnosilacValidators() {
    if (!this.Zahtev_za_priznanje_ziga.value['Vise_podnosilaca']) {
      this.Zahtev_za_priznanje_ziga.get('Podnosilac')?.enable();
      this.Zahtev_za_priznanje_ziga.get('Zajednicki_predstavnik')?.disable();
    } else {
      this.Zahtev_za_priznanje_ziga.get('Podnosilac')?.disable();
      this.Zahtev_za_priznanje_ziga.get('Zajednicki_predstavnik')?.enable();
    }
  }

  dodajBrojKlaseRobe(value: string) {
    const list = this.Zahtev_za_priznanje_ziga.value['Brojevi_klasa_robe']['Broj_klase_robe']
    list?.includes(value) ? list?.splice(list?.indexOf(value), 1) : list?.push(value)
  }

  convertToXML(data: any) {
    let xml = '<?xml version="1.0" encoding="UTF-8"?>\n' +
      '<Z1 xmlns="http://euprava.com/z1/model"\n' +
      '    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"\n' +
      '    xsi:schemaLocation="http://euprava.com/z1/model ../schemas/z1_schema.xsd">';

    function createXML(obj: any) {
      Object.entries(obj).forEach(([key, value]) => {
        if (Array.isArray(value)) {
          value.forEach((item) => {
            xml += `<${key}>${item}</${key}>`;
          });
        } else if (typeof value === 'object') {
          xml += `<${key}>`;
          createXML(value);
          xml += `</${key}>`;
        } else {
          xml += `<${key}>${value}</${key}>`;
        }
      });
    }

    createXML(data);
    xml += '</Z1>';
    return xml;
  }



  submitForm() {
    const request = this.Zahtev_za_priznanje_ziga.value
    if (!this.Zahtev_za_priznanje_ziga.valid) {
      console.log('INVALID FORM')
      console.log(this.convertToXML(request))
      console.log(request)
      return
    }
    delete request['Vise_podnosilaca']
    if (!request['Podnosilac']) request['Podnosilac'] = structuredClone(EMPTY_PERSON)
    if (!request['Zajednicki_predstavnik']) request['Zajednicki_predstavnik'] = structuredClone(EMPTY_PERSON)
    this.userService.submitZ1Request(this.convertToXML(request))
    console.log(this.convertToXML(request))
    console.log(request)
  }

}
