import { Component, Input, OnInit } from '@angular/core';
import { TZiviAutor } from 'src/app/model/a1Request/author/ziviAutor';
import { TPreminuliAutor } from 'src/app/model/a1Request/author/preminuliAutor';
import { TAutor } from 'src/app/model/a1Request/author/autor';

@Component({
  selector: 'euprava-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  @Input() author! : TAutor;

  isUnknown : boolean = false;

  isAlive : boolean = false;

  aliveAuthor! : TZiviAutor;

  deadAuthor! : TPreminuliAutor;

  constructor() { }

  ngOnInit(): void {

    //this.isUnknown = this.author instanceof UnknownAuthor;

    // if(!this.isUnknown){
    //   this.isAlive = this.author instanceof AliveAuthor;

    //   if(this.isAlive){
    //     this.aliveAuthor = <TZiviAutor> this.author;
    //   }else{
    //     this.deadAuthor = <TPreminuliAutor> this.author;
    //   }
    // }

  }

}
