import { Component, Input, OnInit, AfterViewInit } from '@angular/core';
import { ObrazacA1 } from 'src/app/model/a1Request/obrazacA1';

@Component({
  selector: 'euprava-a1-request',
  templateUrl: './a1-request.component.html',
  styleUrls: ['./a1-request.component.css']
})
export class A1RequestComponent implements OnInit {

  @Input() a1request! : ObrazacA1;

  constructor() { }


  ngOnInit(): void {

  }

}
