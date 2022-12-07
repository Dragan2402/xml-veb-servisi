import { Component, Input, OnInit } from '@angular/core';
import { OriginalPiece } from 'src/app/model/a1Request/piece/originalPiece';

@Component({
  selector: 'euprava-original-piece',
  templateUrl: './original-piece.component.html',
  styleUrls: ['./original-piece.component.css']
})
export class OriginalPieceComponent implements OnInit {

  @Input() originalPiece! : OriginalPiece;

  constructor() { }

  ngOnInit(): void {
  }

}
