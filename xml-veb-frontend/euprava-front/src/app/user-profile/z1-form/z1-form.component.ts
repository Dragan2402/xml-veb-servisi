import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, ValidationErrors, Validators} from '@angular/forms';

@Component({
  selector: 'euprava-z1-form',
  templateUrl: './z1-form.component.html',
  styleUrls: ['./z1-form.component.css']
})
export class Z1FormComponent implements OnInit {
  Zahtev_za_priznanje_ziga: FormGroup;
  checkboxValues: any;

  constructor(private formBuilder: FormBuilder) {
    this.checkboxValues = Array.from(Array(46), (_, i) => i.toString());
    this.Zahtev_za_priznanje_ziga = this.formBuilder.group({

      Vise_podnosilaca: false,

      Podnosilac: this.formBuilder.group({
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
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

      Zajednicki_predstavnik: this.formBuilder.group({
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
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
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Poslovno_ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
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

      Podnosi_za_a: 'INDIVIDUALNI',
      Podnosi_za_b: 'VERBALNI',

      Naznacenje_boje: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
      Opis_znaka: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
      Brojevi_klasa_robe: [[]],
      Pravo_prvenstva: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
      Placene_takse: this.formBuilder.group({
        Osnovna_taksa: ['', [Validators.required, Validators.pattern(/^[1-9]+$/)]],
        Graficko_resenje: ['', [Validators.required, Validators.pattern(/^[1-9]+$/)]],
        Ukupno: ['', [Validators.required, Validators.pattern(/^[1-9]+$/)]]
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
    const list = this.Zahtev_za_priznanje_ziga.value['Brojevi_klasa_robe']
    list?.includes(value) ? list.splice(list?.indexOf(value), 1) : list?.push(value)
  }

  submitForm() {
    console.log(this.Zahtev_za_priznanje_ziga);
  }
}
