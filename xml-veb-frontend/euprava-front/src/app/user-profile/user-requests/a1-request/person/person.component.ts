import { Component, Input, OnInit } from '@angular/core';
import { Person } from 'src/app/model/a1Request/person';

@Component({
  selector: 'euprava-person',
  templateUrl: './person.component.html',
  styleUrls: ['./person.component.css']
})
export class PersonComponent implements OnInit {

  @Input() person! : Person

  constructor() { }

  ngOnInit(): void {
  }

}
