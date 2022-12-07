import { Component, Input, OnInit } from '@angular/core';
import { AliveAuthor } from 'src/app/model/a1Request/author/aliveAuthor';
import { Author } from 'src/app/model/a1Request/author/author';
import { DeadAuthor } from 'src/app/model/a1Request/author/deadAuthor';
import { UnknownAuthor } from 'src/app/model/a1Request/author/unknownAuthor';

@Component({
  selector: 'euprava-author',
  templateUrl: './author.component.html',
  styleUrls: ['./author.component.css']
})
export class AuthorComponent implements OnInit {

  @Input() author! : Author;

  isUnknown : boolean = false;

  isAlive : boolean = false;

  aliveAuthor! : AliveAuthor;

  deadAuthor! : DeadAuthor;

  constructor() { }

  ngOnInit(): void {

    this.isUnknown = this.author instanceof UnknownAuthor;

    if(!this.isUnknown){
      this.isAlive = this.author instanceof AliveAuthor;

      if(this.isAlive){
        this.aliveAuthor = <AliveAuthor> this.author;
      }else{
        this.deadAuthor = <DeadAuthor> this.author;
      }
    }

  }

}
