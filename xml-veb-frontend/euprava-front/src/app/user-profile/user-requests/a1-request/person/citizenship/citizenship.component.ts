import { Component, Input, OnInit } from '@angular/core';
import { TDomaceDrzavljanstvo } from 'src/app/model/a1Request/citizenship/domaceDrzavljanstvo';
import { TStranoDrzavljanstvo } from 'src/app/model/a1Request/citizenship/stranoDrzavljanstvo';
import { TDrzavljanstvo } from 'src/app/model/a1Request/citizenship/tdrzavljanstvo';

@Component({
  selector: 'euprava-citizenship',
  templateUrl: './citizenship.component.html',
  styleUrls: ['./citizenship.component.css']
})
export class CitizenshipComponent implements OnInit {

  @Input() citizenship! : TDrzavljanstvo;

  isForeign : boolean = false;

  foreign! : TStranoDrzavljanstvo;

  domestic!: TDomaceDrzavljanstvo;

  constructor() { }

  ngOnInit(): void {

    // this.isForeign = this.citizenship instanceof TStranoDrzavljanstvo;
    // if(this.isForeign){
    //   this.foreign = <TStranoDrzavljanstvo> this.citizenship;
    // }else{
    //   this.domestic = <DomesticCitizenship> this.citizenship;
    // }
  }

}
