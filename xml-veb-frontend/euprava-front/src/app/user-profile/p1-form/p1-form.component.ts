import { Component, OnInit, AfterViewInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Entity } from 'src/app/model/p1Request/Entity';
import { UserService } from '../user.service';
import { XonomyService } from './xonomy.service';

declare const Xonomy: any;

@Component({
  selector: 'euprava-p1-form',
  templateUrl: './p1-form.component.html',
  styleUrls: ['./p1-form.component.css']
})
export class P1FormComponent implements OnInit, AfterViewInit {
  xonomyService: XonomyService;
  userService: UserService;
  documentId = new FormControl<string>('',[Validators.required]);
  loadedXml: string;

  constructor(xonomyService: XonomyService, userService: UserService) {
    this.xonomyService = xonomyService;
    this.userService = userService;
    this.loadedXml = "";
  }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    let element = document.getElementById("editor");
    let specification = this.xonomyService.p1Specification;

    let template = `<Obrazac_P1 xmlns="http://euprava.com/p1/model"><Popunjava_zavod><Broj_prijave>2/22</Broj_prijave><Datum_prijema>2022-11-30+01:00</Datum_prijema><Priznati_datum_podnosenja>2022-11-30+01:00</Priznati_datum_podnosenja></Popunjava_zavod><Zahtev_za_priznanje_patenta><Naziv_pronalaska><Na_srpskom>Automatski sistemi kočenja</Na_srpskom><Na_engleskom>Autonomous braking system</Na_engleskom></Naziv_pronalaska><Podnosilac_prijave><Podnosilac_je_pronalazac>false</Podnosilac_je_pronalazac><Lice xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="TPravni_podnosilac"><Adresa><Ulica>Glavna</Ulica><Broj>23</Broj><Postanski_broj>11080</Postanski_broj><Mesto>Zemun</Mesto><Drzava>Srbija</Drzava></Adresa><Kontakt_informacije><Broj_telefona>011/2194-492</Broj_telefona><Broj_faksa>011/2194-492</Broj_faksa></Kontakt_informacije><Poslovno_ime>BRAKING ASSISSTANT</Poslovno_ime></Lice></Podnosilac_prijave><Pronalazac><Pronalazac_nije_naveden>false</Pronalazac_nije_naveden><Lice><Adresa><Ulica>4. Sremski Bataljon</Ulica><Broj>10</Broj><Postanski_broj>11080</Postanski_broj><Mesto>Zemun</Mesto><Drzava>Srbija</Drzava></Adresa><Kontakt_informacije><Broj_telefona>066/936-8575</Broj_telefona><Broj_faksa></Broj_faksa></Kontakt_informacije><Ime>Dragutin</Ime><Prezime>ILIĆ</Prezime></Lice></Pronalazac><Punomocnik_ili_predstavnik><Tip>Punomocnik_za_prijem_pismena</Tip><Lice xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="TFizicko_lice"><Adresa><Ulica>4. Sremski bataljon</Ulica><Broj>6</Broj><Postanski_broj>11080</Postanski_broj><Mesto>Zemun</Mesto></Adresa><Kontakt_informacije><Broj_telefona>066/936-8581</Broj_telefona></Kontakt_informacije><Ime>Jovan</Ime><Prezime>RAJAČIĆ</Prezime></Lice></Punomocnik_ili_predstavnik><Adresa_za_dostavljanje><Broj>0</Broj><Postanski_broj>0</Postanski_broj></Adresa_za_dostavljanje><Nacin_dostavljanja>Papirni_dokument</Nacin_dostavljanja><Povezana_prijava/><Zahtev_za_priznanje_prava_prvenstva><Ranija_prijava><Datum_podnosenja_ranije_prijave>2021-12-12+01:00</Datum_podnosenja_ranije_prijave><Broj_ranije_prijave>3/21</Broj_ranije_prijave><Oznaka_drzave_ili_organizacije>rs</Oznaka_drzave_ili_organizacije></Ranija_prijava><Ranija_prijava><Datum_podnosenja_ranije_prijave>2021-12-12+01:00</Datum_podnosenja_ranije_prijave><Broj_ranije_prijave>4/21</Broj_ranije_prijave><Oznaka_drzave_ili_organizacije>rs</Oznaka_drzave_ili_organizacije></Ranija_prijava></Zahtev_za_priznanje_prava_prvenstva></Zahtev_za_priznanje_patenta></Obrazac_P1>`

    Xonomy.render(template, element, specification);
  }

  save() {
    let text: string = Xonomy.harvest();
    this.userService.sendXml(text);
  }

  load() {
    let id: string | null | undefined = this.documentId.value;
    id = id?.replace(/\//g, "-");
    this.userService.loadXml(id).subscribe((data: string) => {
      this.loadedXml = data;
      let element = document.getElementById("xml-view");
      let specification = this.xonomyService.p1Specification;
      Xonomy.render(this.loadedXml, element, specification);
    });
  }
}