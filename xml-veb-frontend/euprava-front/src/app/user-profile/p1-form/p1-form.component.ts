import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormArray, FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';
import {UserService} from "../user.service";

@Component({
  selector: 'euprava-p1-form',
  templateUrl: './p1-form.component.html',
  styleUrls: ['./p1-form.component.css']
})
export class P1FormComponent implements OnInit {
  Zahtev_za_priznanje_patenta: FormGroup;

  constructor(private formBuilder: FormBuilder, private userService: UserService) {

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
      }),

      podnosi_se_zahtev_za_priznanje_prava_prvenstva: [false],
      Zahtev_za_priznanje_prava_prvenstva: this.formBuilder.group({
        Ranija_prijava: this.formBuilder.array([])
      })

    });

    this.updateAllValidators();
  }

  ngOnInit() {

  }

  get ranijaPrijavaControls() {
    return this.Zahtev_za_priznanje_patenta.get('Zahtev_za_priznanje_prava_prvenstva.Ranija_prijava') as FormArray;
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
    this.updateZahtevZaPriznanjePravaPrvenstva();
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

  updateZahtevZaPriznanjePravaPrvenstva() {
    if (this.Zahtev_za_priznanje_patenta.value['podnosi_se_zahtev_za_priznanje_prava_prvenstva']) {
      this.Zahtev_za_priznanje_patenta.get('Zahtev_za_priznanje_prava_prvenstva')?.enable();
    } else {
      this.Zahtev_za_priznanje_patenta.get('Zahtev_za_priznanje_prava_prvenstva')?.disable();
    }
  }

  addRanijaPrijava() {
    let ranijaPrijava = this.formBuilder.group({
      Broj_prijave: ['', [ Validators.required, Validators.pattern(/^[1-9]\d*\/\d{2}$/) ] ],
      Datum_podnosenja: ['', [ Validators.required, Validators.pattern(/^\d{4}-\d{2}-\d{2}$/) ] ],
      oznaka_drzave: ['', [ Validators.required, Validators.pattern(/^[A-Z]{2}$/) ] ]
    });

    this.ranijaPrijavaControls.push(ranijaPrijava);
  }

  removeRanijaPrijava(index: number) {
    this.ranijaPrijavaControls.removeAt(index);
  }

  submitForm() {
    if (this.Zahtev_za_priznanje_patenta.valid) {
      let zahtev: String = this.buildXMLZahtevZaPriznanjePatenta();
      this.userService.submitP1Request(zahtev);
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
    zahtevZaPriznanjePatenta += `\t<Nacin_dostavljanja>${this.Zahtev_za_priznanje_patenta.value['Nacin_dostavljanja']}</Nacin_dostavljanja>\n`
    zahtevZaPriznanjePatenta += this.buildPovezanaPrijava();
    zahtevZaPriznanjePatenta += this.buildZahtevZaPriznanjePravaPrvenstva();

    zahtevZaPriznanjePatenta += '</Zahtev_za_priznanje_patenta>';
    return zahtevZaPriznanjePatenta;
  }

  buildNazivPronalaska(): string {
    let nazivPronalaska: string = '\t<Naziv_pronalaska>\n'

    nazivPronalaska += `\t\t<Na_srpskom>${this.Zahtev_za_priznanje_patenta.value['Naziv_pronalaska']['Na_srpskom']}</Na_srpskom>\n`;
    nazivPronalaska += `\t\t<Na_engleskom>${this.Zahtev_za_priznanje_patenta.value['Naziv_pronalaska']['Na_engleskom']}</Na_engleskom>\n`;

    nazivPronalaska += '\t</Naziv_pronalaska>\n'
    return nazivPronalaska;
  }

  buildPodnosilac(): string {
    let pronalazac: boolean = this.Zahtev_za_priznanje_patenta.value['Podnosilac']['pronalazac'];
    let tPodnosilac: string = this.Zahtev_za_priznanje_patenta.value['Podnosilac']['TPodnosilac'];
    let podnosilac: string = `\t<Podnosilac pronalazac="${pronalazac}" xsi:type="${tPodnosilac}">\n`;

    podnosilac += this.buildAdresa('Podnosilac');
    podnosilac += this.buildKontakt('Podnosilac');

    if (tPodnosilac == 'TFizicki_podnosilac') {
      podnosilac += `\t\t<Ime>${this.Zahtev_za_priznanje_patenta.value['Podnosilac']['Ime']}</Ime>\n`;
      podnosilac += `\t\t<Prezime>${this.Zahtev_za_priznanje_patenta.value['Podnosilac']['Prezime']}</Prezime>\n`;
      podnosilac += `\t\t<Drzavljanstvo>${this.Zahtev_za_priznanje_patenta.value['Podnosilac']['Drzavljanstvo']}</Drzavljanstvo>\n`;
    } else {
      podnosilac += `\t\t<Poslovno_ime>${this.Zahtev_za_priznanje_patenta.value['Podnosilac']['Poslovno_ime']}</Poslovno_ime>\n`;
    }

    podnosilac += '\t</Podnosilac>\n';
    return podnosilac;
  }

  buildPronalazac(): string {
    if (this.Zahtev_za_priznanje_patenta.value['Podnosilac']['pronalazac'] || this.Zahtev_za_priznanje_patenta.value['Pronalazac']['ne_zeli_da_bude_naveden']) {
      return '';
    }

    let pronalazac: string = `\t<Pronalazac>\n`;

    pronalazac += this.buildAdresa('Pronalazac');
    pronalazac += this.buildKontakt('Pronalazac');
    pronalazac += `\t\t<Ime>${this.Zahtev_za_priznanje_patenta.value['Pronalazac']['Ime']}</Ime>\n`;
    pronalazac += `\t\t<Prezime>${this.Zahtev_za_priznanje_patenta.value['Pronalazac']['Prezime']}</Prezime>\n`;

    pronalazac += '\t</Pronalazac>\n';
    return pronalazac;
  }

  buildPunomocnik(): string {
    let tip: string = this.Zahtev_za_priznanje_patenta.value['Punomocnik']['tip'];
    let tPunomocnik: string = this.Zahtev_za_priznanje_patenta.value['Punomocnik']['TPunomocnik'];
    let punomocnik: string = `\t<Punomocnik tip="${tip}" xsi:type="${tPunomocnik}">\n`;

    punomocnik += this.buildAdresa('Punomocnik');
    punomocnik += this.buildKontakt('Punomocnik');

    if (tPunomocnik == 'TFizicki_punomocnik') {
      punomocnik += `\t\t<Ime>${this.Zahtev_za_priznanje_patenta.value['Punomocnik']['Ime']}</Ime>\n`;
      punomocnik += `\t\t<Prezime>${this.Zahtev_za_priznanje_patenta.value['Punomocnik']['Prezime']}</Prezime>\n`;
    } else {
      punomocnik += `\t\t<Poslovno_ime>${this.Zahtev_za_priznanje_patenta.value['Punomocnik']['Poslovno_ime']}</Poslovno_ime>\n`;
    }

    punomocnik += '\t</Punomocnik>\n';
    return punomocnik;
  }

  buildAdresaZaDostavljanje(): string {
    if (this.Zahtev_za_priznanje_patenta.value['Adresa_za_dostavljanje']['podrazumevana_adresa']) {
      return '';
    }

    let adresaZaDostavljanje: string = `\t<Adresa_za_dostavljanje>\n`;

    adresaZaDostavljanje += this.buildAdresa('Adresa_za_dostavljanje');

    adresaZaDostavljanje += '\t</Adresa_za_dostavljanje>\n';
    return adresaZaDostavljanje;
  }

  buildPovezanaPrijava(): string {
    if (this.Zahtev_za_priznanje_patenta.value['osnovna_prijava']) {
      return '';
    }

    let tip = this.Zahtev_za_priznanje_patenta.value['Povezana_prijava']['tip'];
    let povezanaPrijava: string = `\t<Povezana_prijava tip="${tip}">\n`;

    povezanaPrijava += `\t\t<Broj_prijave>${this.Zahtev_za_priznanje_patenta.value['Povezana_prijava']['Broj_prijave']}</Broj_prijave>\n`;
    povezanaPrijava += `\t\t<Datum_podnosenja>${this.Zahtev_za_priznanje_patenta.value['Povezana_prijava']['Datum_podnosenja']}</Datum_podnosenja>\n`;

    povezanaPrijava += '\t</Povezana_prijava>\n';
    return povezanaPrijava;
  }

  buildZahtevZaPriznanjePravaPrvenstva(): string {
    if (!this.Zahtev_za_priznanje_patenta.value['podnosi_se_zahtev_za_priznanje_prava_prvenstva']) {
      return '';
    }

    let zahtevZaPriznanjePravaPrvenstva = '\t<Zahtev_za_priznanje_prava_prvenstva>\n'

    let ranijePrijave = this.Zahtev_za_priznanje_patenta.value.Zahtev_za_priznanje_prava_prvenstva.Ranija_prijava;
    for (let i = 0; i < ranijePrijave.length; i++) {
      zahtevZaPriznanjePravaPrvenstva += this.buildRanijaPrijava(ranijePrijave[i]);
    }

    zahtevZaPriznanjePravaPrvenstva += '\t</Zahtev_za_priznanje_prava_prvenstva>\n';
    return zahtevZaPriznanjePravaPrvenstva
  }

  buildAdresa(parent: string): string {
    let adresa: string = '\t\t<Adresa>\n';

    adresa += `\t\t\t<Ulica>${this.Zahtev_za_priznanje_patenta.value[parent]['Adresa']['Ulica']}</Ulica>\n`;
    adresa += `\t\t\t<Broj>${this.Zahtev_za_priznanje_patenta.value[parent]['Adresa']['Broj']}</Broj>\n`;
    adresa += `\t\t\t<Postanski_broj>${this.Zahtev_za_priznanje_patenta.value[parent]['Adresa']['Postanski_broj']}</Postanski_broj>\n`;
    adresa += `\t\t\t<Mesto>${this.Zahtev_za_priznanje_patenta.value[parent]['Adresa']['Mesto']}</Mesto>\n`;
    if (parent == 'Podnosilac' || parent == 'Pronalazac') {
      adresa += `\t\t\t<Drzava>${this.Zahtev_za_priznanje_patenta.value[parent]['Adresa']['Drzava']}</Drzava>\n`;
    }

    adresa += '\t\t</Adresa>\n';
    return adresa
  }

  buildKontakt(parent: string): string {
    let kontakt: string = '\t\t<Kontakt>\n';

    kontakt += `\t\t\t<Broj_telefona>${this.Zahtev_za_priznanje_patenta.value[parent]['Kontakt']['Broj_telefona']}</Broj_telefona>\n`;
    kontakt += `\t\t\t<E_posta>${this.Zahtev_za_priznanje_patenta.value[parent]['Kontakt']['E_posta']}</E_posta>\n`;
    if (parent == 'Podnosilac' || parent == 'Pronalazac') {
      kontakt += `\t\t\t<Broj_faksa>${this.Zahtev_za_priznanje_patenta.value[parent]['Kontakt']['Broj_faksa']}</Broj_faksa>\n`;
    }

    kontakt += '\t\t</Kontakt>\n';
    return kontakt
  }

  private buildRanijaPrijava(ranijePrijaveElement: any) {
    let ranijaPrijava = `\t\t<Ranija_prijava oznaka_drzave="${ranijePrijaveElement['oznaka_drzave']}">\n`

    ranijaPrijava += `\t\t\t<Broj_prijave>${ranijePrijaveElement['Broj_prijave']}</Broj_prijave>\n`;
    ranijaPrijava += `\t\t\t<Datum_podnosenja>${ranijePrijaveElement['Datum_podnosenja']}</Datum_podnosenja>\n`;

    ranijaPrijava += '\t\t</Ranija_prijava>\n'
    return ranijaPrijava;
  }
}
