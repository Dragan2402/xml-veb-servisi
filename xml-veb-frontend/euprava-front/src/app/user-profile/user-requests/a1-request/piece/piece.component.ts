import { Component, Input, OnInit } from '@angular/core';
import { Piece } from 'src/app/model/a1Request/piece/piece';

@Component({
  selector: 'euprava-piece',
  templateUrl: './piece.component.html',
  styleUrls: ['./piece.component.css']
})
export class PieceComponent implements OnInit {

  @Input() piece! : Piece;

  constructor() { }

  ngOnInit(): void {
  }

}
