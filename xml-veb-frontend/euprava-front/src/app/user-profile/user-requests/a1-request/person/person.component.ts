import { Component, Input, OnInit } from '@angular/core';
import { TOsoba } from 'src/app/model/a1Request/osoba';

@Component({
  selector: 'euprava-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  @Input() person! : TOsoba

  constructor() { }

  ngOnInit(): void {
  }

}
