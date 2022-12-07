import { Component, Input, OnInit } from '@angular/core';
import { Citizenship } from 'src/app/model/a1Request/citizenship/citizenship';
import { DomesticCitizenship } from 'src/app/model/a1Request/citizenship/domesticCitizenship';
import { ForeignCitizenship } from 'src/app/model/a1Request/citizenship/foreignCitizenship';

@Component({
  selector: 'euprava-citizenship',
  templateUrl: './citizenship.component.html',
  styleUrls: ['./citizenship.component.css']
})
export class CitizenshipComponent implements OnInit {

  @Input() citizenship! : Citizenship;

  isForeign : boolean = false;

  foreign! : ForeignCitizenship;

  domestic!: DomesticCitizenship;

  constructor() { }

  ngOnInit(): void {

    this.isForeign = this.citizenship instanceof ForeignCitizenship;
    if(this.isForeign){
      this.foreign = <ForeignCitizenship> this.citizenship;
    }else{
      this.domestic = <DomesticCitizenship> this.citizenship;
    }
  }

}
