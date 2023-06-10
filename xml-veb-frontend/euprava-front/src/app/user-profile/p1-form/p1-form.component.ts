import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';

declare const Xonomy: any;

@Component({
  selector: 'euprava-p1-form',
  templateUrl: './p1-form.component.html',
  styleUrls: ['./p1-form.component.css']
})
export class P1FormComponent implements OnInit {
  Zahtev_za_priznanje_patenta: FormGroup;

  constructor(private formBuilder: FormBuilder) {

    this.Zahtev_za_priznanje_patenta = this.formBuilder.group({

      Naziv_pronalaska: this.formBuilder.group({
        Na_srpskom: ['Automatski sistemi kočenja', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Na_engleskom: ['Autonomous braking system', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]
      }),

      Podnosilac: this.formBuilder.group({
        pronalazac: [false],
        TPodnosilac: ["TPravni_podnosilac"],
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ]+$/) ] ],
        Drzavljanstvo: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Poslovno_ime: ['BRAKING ASSISTANT', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ ]+$/) ] ],
        Adresa: this.formBuilder.group({

          Ulica: ['Glavna', [ Validators.required, Validators.pattern(/^[A-Za-z0-9ŠĐČĆŽšđčćž. ]+$/) ] ],
          Broj: ['23', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['11080', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['Zemun', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Drzava: ['Srbija', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        }),
        Kontakt: this.formBuilder.group({

          Broj_telefona: ['011/2194-492', [ Validators.required ] ],
          Broj_faksa: ['011/2194-492', [ Validators.required ] ],
          E_posta: ['br_assistant@gmail.com', [ Validators.required, Validators.email ] ]

        })
      }),

      Pronalazac: this.formBuilder.group({
        ne_zeli_da_bude_naveden: [false],
        Ime: ['Dragutin', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['ILIĆ', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ]+$/) ] ],

        Adresa: this.formBuilder.group({

          Ulica: ['4. Sremski Bataljon', [ Validators.required, Validators.pattern(/^[A-Za-z0-9ŠĐČĆŽšđčćž. ]+$/) ] ],
          Broj: ['10', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['11080', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['Zemun', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
          Drzava: ['Srbija', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        }),

        Kontakt: this.formBuilder.group({

          Broj_telefona: ['066/936-8575', [ Validators.required ] ],
          Broj_faksa: ['066/936-8575', [ Validators.required ] ],
          E_posta: ['dilic@gmail.com', [ Validators.required, Validators.email ] ]

        })
      }),

      Punomocnik: this.formBuilder.group({
        tip: ['Punomocnik_za_prijem_pismena'],
        TPunomocnik: ["TFizicki_punomocnik"],
        Ime: ['Jovan', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['RAJAČIĆ', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ]+$/) ] ],
        Poslovno_ime: ['', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ ]+$/) ] ],

        Adresa: this.formBuilder.group({

          Ulica: ['4. Sremski bataljon', [ Validators.required, Validators.pattern(/^[A-Za-z0-9ŠĐČĆŽšđčćž. ]+$/) ] ],
          Broj: ['6', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['11080', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['Zemun', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        }),

        Kontakt: this.formBuilder.group({

          Broj_telefona: ['066/936-8581', [ Validators.required ] ],
          E_posta: ['jovanr@gmail.com', [ Validators.required, Validators.email ] ]

        })

      }),

      Adresa_za_dostavljanje: this.formBuilder.group({
        podrazumevana_adresa: [true],
        Adresa: this.formBuilder.group({

          Ulica: ['', [ Validators.required, Validators.pattern(/^[A-Za-z0-9ŠĐČĆŽšđčćž. ]+$/) ] ],
          Broj: ['', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/) ] ],
          Postanski_broj: ['', [ Validators.required, Validators.pattern(/^[1-9][0-9]*$/), this.rangeValidator ] ],
          Mesto: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]

        })
      }),

      Nacin_dostavljanja: ['Papirna_forma'],
      osnovna_prijava: [false],
      Povezana_prijava: this.formBuilder.group({
        tip: ['Dopunska_prijava'],
        Broj_prijave: ['1/22', [ Validators.required, Validators.pattern(/^[1-9]\d*\/\d{2}$/) ] ],
        Datum_podnosenja: ['2022-01-10', [ Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/) ] ]
      })

    });

    this.updateAllValidators();
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

  updateAllValidators() {
    this.updatePodnosilacValidators();
    this.updatePronalazacValidators();
    this.updatePunomocnikValidators();
    this.updateAdresaZaDostavljanjeValidators();
    this.updatePovezanaPrijavaValidators();
  }

  updatePodnosilacValidators() {
    if (this.Zahtev_za_priznanje_patenta.value['Podnosilac']['TPodnosilac'] == 'TFizicki_podnosilac') {
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Ime')?.enable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Prezime')?.enable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Drzavljanstvo')?.enable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Poslovno_ime')?.disable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Ime')?.disable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Prezime')?.disable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Drzavljanstvo')?.disable();
      this.Zahtev_za_priznanje_patenta.get('Podnosilac')?.get('Poslovno_ime')?.enable();
    }
  }

  updatePronalazacValidators() {
    console.log()
    if (this.Zahtev_za_priznanje_patenta.value['Podnosilac']['pronalazac'] == true) {
      this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.disable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.enable();

      if (this.Zahtev_za_priznanje_patenta.value['Pronalazac']['ne_zeli_da_bude_naveden'] == true) {
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Ime')?.disable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Prezime')?.disable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Adresa')?.disable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Kontakt')?.disable();
      } else {
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Ime')?.enable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Prezime')?.enable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Adresa')?.enable();
        this.Zahtev_za_priznanje_patenta.get('Pronalazac')?.get('Kontakt')?.enable();
      }
    }
  }

  updatePunomocnikValidators() {
    if (this.Zahtev_za_priznanje_patenta.value['Punomocnik']['TPunomocnik'] == 'TFizicki_punomocnik') {
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Ime')?.enable();
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Prezime')?.enable();
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Poslovno_ime')?.disable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Ime')?.disable();
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Prezime')?.disable();
      this.Zahtev_za_priznanje_patenta.get('Punomocnik')?.get('Poslovno_ime')?.enable();
    }
  }

  updateAdresaZaDostavljanjeValidators() {
    if (this.Zahtev_za_priznanje_patenta.value['Adresa_za_dostavljanje']['podrazumevana_adresa']) {
      this.Zahtev_za_priznanje_patenta.get('Adresa_za_dostavljanje')?.get('Adresa')?.disable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Adresa_za_dostavljanje')?.get('Adresa')?.enable();
    }
  }

  updatePovezanaPrijavaValidators() {
    if (this.Zahtev_za_priznanje_patenta.value['osnovna_prijava']) {
      this.Zahtev_za_priznanje_patenta.get('Povezana_prijava')?.disable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Povezana_prijava')?.enable();
    }
  }

  submitForm() {
    if (this.Zahtev_za_priznanje_patenta.valid) {
      let zahtev: String = this.buildXMLZahtevZaPriznanjePatenta();
      console.log(zahtev);
    }
  }

  buildXMLZahtevZaPriznanjePatenta(): string {
    let zahtevZaPriznanjePatenta: string = '<?xml version="1.0" encoding="UTF-8"?>\n'
    zahtevZaPriznanjePatenta += '<Zahtev_za_priznanje_patenta xmlns="http://p1.euprava.com/model" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">\n'

    zahtevZaPriznanjePatenta += this.buildNazivPronalaska();
    zahtevZaPriznanjePatenta += this.buildPodnosilac();
    zahtevZaPriznanjePatenta += this.buildPronalazac();
    zahtevZaPriznanjePatenta += this.buildPunomocnik();
    zahtevZaPriznanjePatenta += this.buildAdresaZaDostavljanje();
    zahtevZaPriznanjePatenta += this.buildNacinDostavljanja();
    zahtevZaPriznanjePatenta += this.buildPovezanaPrijava();
    zahtevZaPriznanjePatenta += this.buildZahtevZaPriznanjePravaPrvenstva();

    zahtevZaPriznanjePatenta += '</Zahtev_za_priznanje_patenta>';
    return zahtevZaPriznanjePatenta;
  }

  buildNazivPronalaska(): string {
    let nazivPronalaska = '<Naziv_pronalaska>\n'

    nazivPronalaska += this.buildNaSrpskom();
    nazivPronalaska += this.buildNaEngleskom();

    nazivPronalaska += '</Naziv_pronalaska>\n'
    return nazivPronalaska;
  }

  buildPodnosilac(): string {
    // <Podnosilac pronalazac="false" xsi:type="TPravni_podnosilac">
    //   <Adresa>
    //     <Ulica>Glavna</Ulica>
    //   <Broj>23</Broj>
    //   <Postanski_broj>11080</Postanski_broj>
    //   <Mesto>Zemun</Mesto>
    //   <Drzava>Srbija</Drzava>
    //   </Adresa>
    //   <Kontakt>
    //   <Broj_telefona>011/2194-492</Broj_telefona>
    //   <E_posta>br_assistant@gmail.com</E_posta>
    // <Broj_faksa>011/2194-492</Broj_faksa>
    // </Kontakt>
    // <Poslovno_ime>BRAKING ASSISTANT</Poslovno_ime>
    // </Podnosilac>

    let pronalazac: boolean = this.Zahtev_za_priznanje_patenta.value['Podnosilac']['pronalazac'];
    let tPodnosilac: string = this.Zahtev_za_priznanje_patenta.value['Podnosilac']['TPodnosilac'];
    let podnosilac: string = `<Podnosilac pronalazac="${pronalazac}" xsi:type="${tPodnosilac}">\n`;

    podnosilac += this.buildPodnosilacAdresa();
    podnosilac += this.buildPodnosilacKontakt();

    if (tPodnosilac == 'TFizicki_podnosilac') {
      podnosilac += this.buildFizickiPodnosilac();
    } else {
      podnosilac += this.buildPravniPodnosilac();
    }

    podnosilac += '</Podnosilac>\n';
    return podnosilac;
  }

  buildPronalazac(): string {
    return '';
  }

  buildPunomocnik(): string {
    return '';
  }

  buildAdresaZaDostavljanje(): string {
    return '';
  }

  buildNacinDostavljanja(): string {
    return '';
  }

  buildPovezanaPrijava(): string {
    return '';
  }

  buildZahtevZaPriznanjePravaPrvenstva(): string {
    return '';
  }

  buildNaSrpskom(): string {
    let naSrpskom: string = '<Na_srpskom>';

    naSrpskom += this.Zahtev_za_priznanje_patenta.value['Naziv_pronalaska']['Na_srpskom'];

    naSrpskom += '</Na_srpskom>\n';
    return naSrpskom
  }

  buildNaEngleskom() {
    let naEngleskom: string = '<Na_engleskom>';

    naEngleskom += this.Zahtev_za_priznanje_patenta.value['Naziv_pronalaska']['Na_engleskom'];

    naEngleskom += '</Na_engleskom>\n';
    return naEngleskom
  }

  buildPodnosilacAdresa(): string {
    let adresa: string = '<Adresa>\n';

    adresa += '</Adresa>\n';
    return adresa
  }

  buildPodnosilacKontakt(): string {
    return '';
  }

  buildFizickiPodnosilac(): string {
    return '';
  }

  buildPravniPodnosilac(): string {
    return '';
  }
}
