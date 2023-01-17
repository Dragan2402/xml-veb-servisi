import { Component, Input, OnInit } from '@angular/core';
import { TDjelo } from 'src/app/model/a1Request/piece/djelo';

@Component({
  selector: 'euprava-piece',
  templateUrl: './piece.component.html',
  styleUrls: ['./piece.component.css']
})
export class PieceComponent implements OnInit {

  @Input() piece! : TDjelo;

  constructor() { }

  ngOnInit(): void {
  }

}
