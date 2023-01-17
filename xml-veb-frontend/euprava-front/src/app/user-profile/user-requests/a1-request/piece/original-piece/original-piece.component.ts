import { Component, Input, OnInit } from '@angular/core';
import { PodaciOriginalnoDjelo } from 'src/app/model/a1Request/piece/podaciOriginalnoDjelo';

@Component({
  selector: 'euprava-original-piece',
  templateUrl: './original-piece.component.html',
  styleUrls: ['./original-piece.component.css']
})
export class OriginalPieceComponent implements OnInit {

  @Input() originalPiece! : PodaciOriginalnoDjelo;

  constructor() { }

  ngOnInit(): void {
  }

}
