import { Component, Input, OnInit, AfterViewInit } from '@angular/core';
import { A1Request } from 'src/app/model/a1Request/a1Request';

@Component({
  selector: 'euprava-a1-request',
  templateUrl: './a1-request.component.html',
  styleUrls: ['./a1-request.component.css']
})
export class A1RequestComponent implements OnInit {

  @Input() a1request! : A1Request;

  constructor() { }


  ngOnInit(): void {

  }

}
