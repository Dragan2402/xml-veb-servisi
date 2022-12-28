import { Component, Input, OnInit } from '@angular/core';
import { Adresa } from 'src/app/model/a1Request/adresa';

@Component({
  selector: 'euprava-address',
  templateUrl: './address.component.html',
  styleUrls: ['./address.component.css']
})
export class AddressComponent implements OnInit {

  @Input() address! : Adresa

  constructor() { }

  ngOnInit(): void {
  }

}
