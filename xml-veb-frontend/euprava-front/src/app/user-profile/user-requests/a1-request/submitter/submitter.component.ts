import { Component,  Input, OnInit } from '@angular/core';
import { IndividualSubmitter } from 'src/app/model/a1Request/submitter/individualSubmitter';
import { LegalSubmitter } from 'src/app/model/a1Request/submitter/legalSubmitter';
import { Submitter } from 'src/app/model/a1Request/submitter/submitter';

@Component({
  selector: 'euprava-submitter',
  templateUrl: './submitter.component.html',
  styleUrls: ['./submitter.component.css']
})
export class SubmitterComponent implements OnInit {

  @Input() submitter! : Submitter;

  isIndividual : boolean = false;

  individual! : IndividualSubmitter;
  legal! : LegalSubmitter;


  constructor() { }

  ngOnInit(): void {
    console.log(this.submitter.ToString());
    this.isIndividual = this.submitter instanceof IndividualSubmitter;
    if(this.isIndividual){
      this.individual =<IndividualSubmitter>this.submitter;
    }else{
      this.legal = <LegalSubmitter> this.submitter;
    }
  }

}
