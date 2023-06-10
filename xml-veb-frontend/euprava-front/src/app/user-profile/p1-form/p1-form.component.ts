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
        Na_srpskom: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Na_engleskom: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ]
      }),

      Podnosilac: this.formBuilder.group({
        pronalazac: [false],
        TPodnosilac: ["TFizicki_podnosilac"],
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ]+$/) ] ],
        Drzavljanstvo: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž ]+$/) ] ],
        Poslovno_ime: ['', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ ]+$/) ] ],
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

      Pronalazac: this.formBuilder.group({
        ne_zeli_da_bude_naveden: [false],
        Ime: ['', [ Validators.required, Validators.pattern(/^[A-Za-zŠĐČĆŽšđčćž]+$/) ] ],
        Prezime: ['', [ Validators.required, Validators.pattern(/^[A-ZŠĐČĆŽ]+$/) ] ],

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
    console.log(this.Zahtev_za_priznanje_patenta.value['Pronalazac']);
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

  submitForm() {
    console.log(this.Zahtev_za_priznanje_patenta);
  }
}
