import { Component,  Input, OnInit } from '@angular/core';
import { TFizickiPodnosilac } from 'src/app/model/a1Request/submitter/fizickiPodnosilac';
import { TPravniPodnosilac } from 'src/app/model/a1Request/submitter/pravniPodnosilac';
import { TPodnosilac } from 'src/app/model/a1Request/submitter/podnosilac';

@Component({
  selector: 'euprava-submitter',
  templateUrl: './submitter.component.html',
  styleUrls: ['./submitter.component.css']
})
export class SubmitterComponent implements OnInit {

  @Input() submitter! : TPodnosilac;

  isIndividual : boolean = false;

  individual! : TFizickiPodnosilac;
  legal! : TPravniPodnosilac;


  constructor() { }

  ngOnInit(): void {
    // console.log(this.submitter.ToString());
    // this.isIndividual = this.submitter instanceof TFizickiPodnosilac;
    // if(this.isIndividual){
    //   this.individual =<TFizickiPodnosilac>this.submitter;
    // }else{
    //   this.legal = <TPravniPodnosilac> this.submitter;
    // }
  }

}
